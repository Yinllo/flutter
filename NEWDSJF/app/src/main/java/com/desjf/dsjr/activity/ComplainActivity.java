package com.desjf.dsjr.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.GridViewAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.biz.task.EasyTask;
import com.desjf.dsjr.cymodel.Constant;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.util.HttpAssist;
import com.desjf.dsjr.util.MyDialogUtil;
import com.desjf.dsjr.utils.ToastUtils;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.desjf.dsjr.R.id.gridView;
import static com.desjf.dsjr.R.id.t1;

/**
 *    @author YL
 */
public class ComplainActivity extends BaseActivity {

    @BindView(R.id.iv_ljtz_back)
    ImageView back;
    @BindView(gridView)
    GridView list_gv;
    @BindView(R.id.t1)
    ImageView tousu;
    @BindView(R.id.t2)
    ImageView jianyi;
    @BindView(R.id.t3)
    ImageView jubao;
    @BindView(R.id.t4)
    ImageView zhengyi;
    @BindView(R.id.et_content)
    EditText content;
    @BindView(R.id.tijiao)
    TextView tijiao;
    @BindView(R.id.number)
    TextView number;//当前输入的字数

    private boolean oenIsCheck=true;//按钮是否被选中
    private boolean twoIsCheck=false;
    private boolean threeIsCheck=false;
    private boolean fourIsCheck=false;
    String ifile="";//需要上传的文件流
    private String type="1";//当前被选中的是哪个按钮
    private String mContent="";//用户输入的内容
    String picPath;
    private static final String TAG = "uploadImage";
//    private static String requestURL = "http://t.dsp2p.com/IloanAdmin/complaintAdvice/intComplaintAdvice";

    public static final String KEY_PHOTO_PATH = "photo_path";

    private HashMap<Integer,Bitmap> imageMap = new HashMap<Integer, Bitmap>();
    private HashMap<Integer,String> file = new HashMap<Integer, String>();
    private ArrayList<String> mPicList =null; //上传的图片凭证的数据源
    private ArrayList<File> mPicFileList = new ArrayList<>(); //上传的图片凭证的数据源
    private GridViewAdapter mGridViewAddImgAdapter; //展示上传的图片的适配器
    File files[]=null;
    private String urlString = null;
    private EasyTask asyncTask;
    Map<String, String> param;//存储所有文本数据
    private AccountModel accountModels;

    public static final int ALBUM_WRITE_EXTERNAL_PEMISSION = 102;//相机存储请求码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);
        ButterKnife.bind(this);

        mPicList = new ArrayList<>();

        BaseApplication baseApplication = (BaseApplication) getApplication();
        accountModels= baseApplication.getAccountModel();

           initView();

    }

    //判断当前是否拥有存储权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        onMyRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initView() {
        //文字描述内容不能超过500
        content.setFilters( new InputFilter[]{new InputFilter.LengthFilter(500) });
        //设置EditText监听 实时计算输入的字数
        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int detailLength = content.length();
                number.setText(detailLength+"");
            }
        });

        //当文字描述内容不为空时才能提交
            tijiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!("").equals(content.getText().toString())){
                    initTask();
                    //防止重复点击
                        tijiao.setText("正在提交...");
                    tijiao.setClickable(false);
                    }
                    else{
                        MyDialogUtil.showSimpleDialog(ComplainActivity.this,"请您填写描述内容","知道了");
                    }
                }
            });

        mGridViewAddImgAdapter = new GridViewAdapter(ComplainActivity.this, mPicList);
        list_gv.setAdapter(mGridViewAddImgAdapter);

        //判断当前是否有存储权限，没有则申请，拥有权限才能选择图片
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, ALBUM_WRITE_EXTERNAL_PEMISSION);
        }else {
            list_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    if (position == parent.getChildCount() - 1) {
                        //如果“增加按钮形状的”图片的位置是最后一张，且添加了的图片的数量不超过5张，才能点击
                        if (mPicList.size() == 5) {
                            //最多添加5张图片
                        } else {
                            //添加凭证图片
//                            Intent  intent = new Intent(ComplainActivity.this,SelectPicActivity.class);
//                            startActivityForResult(intent, 0);
                            Album.image(ComplainActivity.this)
                                    .multipleChoice()
                                    .requestCode(200)
                                    .selectCount(5)
                                    .onResult(new Action<ArrayList<AlbumFile>>() {
                                        @Override
                                        public void onAction(int requestCode, @NonNull ArrayList<AlbumFile> result) {
                                            // 拿到用户选择的图片路径List：
                                            for (int i = 0; i < result.size(); i++) {
                                                mPicList.add(result.get(i).getPath());
                                            }
                                            //获取对应照片的文件
                                            if (mPicList != null) {
                                                int imgNum = mPicList.size();
                                                files = new File[imgNum];
                                                for (int i = 0; i < imgNum; i++) {
                                                    String imgPath = mPicList.get(i);
                                                    files[i] = new File(imgPath);
                                                }
                                            }
                                            mGridViewAddImgAdapter.notifyDataSetChanged();
                                        }
                                    }).start();

                        }
                    }
                }
            });
        }
    }


    //权限匹配（可以加入其它权限  比如相机等）
    public void onMyRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constant.ALBUM_WRITE_EXTERNAL_PEMISSION://相册存储请求返回
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 存储权限被用户同意，可以去放肆了。
                    list_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            if (position == parent.getChildCount() - 1) {
                                //如果“增加按钮形状的”图片的位置是最后一张，且添加了的图片的数量不超过5张，才能点击
                                if (mPicList.size() == 5) {
                                    //最多添加5张图片
                                } else {
                                    //添加凭证图片
//                                    Intent  intent = new Intent(ComplainActivity.this,SelectPicActivity.class);
//                                    startActivityForResult(intent, 0);
                                    Album.image(ComplainActivity.this)
                                            .multipleChoice()
                                            .requestCode(200)
                                            .selectCount(5)
                                            .onResult(new Action<ArrayList<AlbumFile>>() {
                                                @Override
                                                public void onAction(int requestCode, @NonNull ArrayList<AlbumFile> result) {
                                                    // 拿到用户选择的图片路径List：
                                                    for (int i = 0; i < result.size(); i++) {
                                                        mPicList.add(result.get(i).getPath());
                                                    }
                                                    //获取对应照片的文件
                                                    if (mPicList != null) {
                                                        int imgNum = mPicList.size();
                                                        files = new File[imgNum];
                                                        for (int i = 0; i < imgNum; i++) {
                                                            String imgPath = mPicList.get(i);
                                                            files[i] = new File(imgPath);
                                                        }
                                                    }
                                                    mGridViewAddImgAdapter.notifyDataSetChanged();
                                                }
                                            }).start();

                                }
                            }
                        }
                    });
                } else {
                    ToastUtils.showShortTost(ComplainActivity.this,"存储权限被禁用，您将无法选择图片！");
                }
                break;
            case Constant.CRASH_WRITE_EXTERNAL_PEMISSION://异常捕获存储请求返回
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 存储权限被用户同意，可以去放肆了。
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            startAppcompatActivity(MainActivity.class);
                            finish();
                        }
                    },3000);
                } else {
                    showToast("存储权限被禁用，您将无法使用上传照片功能!");
                    //异常捕获存储权限
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            picPath = data.getStringExtra(KEY_PHOTO_PATH);
            Bitmap bm = BitmapFactory.decodeFile(picPath);
            //Bitmap tempBitmap = BitmapUtil.createImageThumbnail(picPath,128);//压缩图片
            //Bitmap saveBitmap = BitmapUtil.createImageThumbnail(picPath,2048);

            Bitmap tempBitmap = getimage(picPath);//压缩图片
            Bitmap saveBitmap = getimage(picPath);//上传服务器的bitmap 手机横着拍照
            String path = Environment.getExternalStorageDirectory() + "/pos/" + requestCode + ".JPEG";
            file.put(requestCode, path);
            imageMap.put(requestCode, tempBitmap);
            mPicList.add(picPath);
            //获取对应照片的文件
            if (mPicList != null) {
                int imgNum = mPicList.size();
                files = new File[imgNum];
                for (int i = 0; i < imgNum; i++) {
                    String imgPath = mPicList.get(i);
                    files[i] = new File(imgPath);
                }
            }
            mGridViewAddImgAdapter.notifyDataSetChanged();
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    //图片压缩
    private Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return bitmap;//压缩好比例大小后再进行质量压缩
    }



    @OnClick({R.id.t1,R.id.t2,R.id.t3,R.id.t4,R.id.iv_ljtz_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case t1:
                if(oenIsCheck){
                  tousu.setImageResource(R.mipmap.tsuchoose);
                  oenIsCheck=!oenIsCheck;
                }else{
                    tousu.setImageResource(R.mipmap.tschoose);
                    type="1";
                    oenIsCheck=!oenIsCheck;
                }
                jianyi.setImageResource(R.mipmap.jyuchoose);
                threeIsCheck=false;
                twoIsCheck=false;
                fourIsCheck=false;
                jubao.setImageResource(R.mipmap.jbuchoose);
                zhengyi.setImageResource(R.mipmap.zyuchoose);
                break;
            case R.id.t2:
                if(twoIsCheck){
                    jianyi.setImageResource(R.mipmap.jyuchoose);
                    twoIsCheck=!twoIsCheck;
                }else{
                    jianyi.setImageResource(R.mipmap.jychoose);
                    type="2";
                    twoIsCheck=!twoIsCheck;
                }
                tousu.setImageResource(R.mipmap.tsuchoose);
                jubao.setImageResource(R.mipmap.jbuchoose);
                zhengyi.setImageResource(R.mipmap.zyuchoose);
                oenIsCheck=false;
                threeIsCheck=false;
                fourIsCheck=false;
                break;
            case R.id.t3:
                if(threeIsCheck){
                    jubao.setImageResource(R.mipmap.jbuchoose);
                    threeIsCheck=!threeIsCheck;
                }else{
                    jubao.setImageResource(R.mipmap.jbchoose);
                    type="3";
                    threeIsCheck=!threeIsCheck;
                }
                tousu.setImageResource(R.mipmap.tsuchoose);
                jianyi.setImageResource(R.mipmap.jyuchoose);
                zhengyi.setImageResource(R.mipmap.zyuchoose);
                oenIsCheck=false;
                twoIsCheck=false;
                fourIsCheck=false;
                break;
            case R.id.t4:
                if(fourIsCheck){
                    zhengyi.setImageResource(R.mipmap.zyuchoose);
                    fourIsCheck=!fourIsCheck;
                }else{
                    zhengyi.setImageResource(R.mipmap.zychoose);
                    type="4";
                    fourIsCheck=!fourIsCheck;
                }
                tousu.setImageResource(R.mipmap.tsuchoose);
                jianyi.setImageResource(R.mipmap.jyuchoose);
                jubao.setImageResource(R.mipmap.jbuchoose);
                oenIsCheck=false;
                twoIsCheck=false;
                threeIsCheck=false;
                break;
            case R.id.iv_ljtz_back:
                finish();
                break;

        }

    }

    private void initTask(){
        //获得文本数据
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type",type);//反馈类型
            jsonObject.put("explain",content.getText().toString());
            jsonObject.put("userPhone",accountModels.getPHONENUMBER());
            jsonObject.put("sourceTerminal","2");//反馈终端  2代表安卓端
        } catch (JSONException e) {
            e.printStackTrace();
        }
        param = new HashMap<>();
        //设置编码类型为utf-8
        param.put("mapInfo", String.valueOf(jsonObject));
        ToastUtils.showShortTost(ComplainActivity.this, ("正在提交，请稍后..."));
        BizDataAsyncTask<String> upload = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return HttpAssist.uploadFile(files,param);
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                //将内容清空 并让按钮恢复可点击
                content.setText("");
                mPicList.clear();
                files=null;
                tijiao.setText("提交");
                tijiao.setClickable(true);
                mGridViewAddImgAdapter.notifyDataSetChanged();
                MyDialogUtil.showSimpleDialog(ComplainActivity.this,s,"知道了");
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(ComplainActivity.this,error);
                tijiao.setText("提交");
                tijiao.setClickable(true);
            }
        };
        upload.execute();

    }

}
