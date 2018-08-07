package com.desjf.dsjr.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.BankWebViewActivity;
import com.desjf.dsjr.activity.LoginActivity;
import com.desjf.dsjr.activity.NewInvestmentDetailsActivity;
import com.desjf.dsjr.activity.ProjectDetailsActivity;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.bean.NewRegBean;
import com.desjf.dsjr.biz.ProjectInfoBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.retrofit.BankHttpUtil;
import com.desjf.dsjr.biz.retrofit.CallUtils;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.InvestmentInfoModel;
import com.desjf.dsjr.model.ProjectInfoModel;
import com.desjf.dsjr.utils.PreferenceCache;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author YinL  2018-2-28
 *  新的证件合同   使用新的显示方式
 */
public class NewBrrowDataFragment extends BaseFragment {


//    @BindView(R.id.iv_toleft)
//    ImageView ivToleft;
//    @BindView(R.id.iv_photo)
//    ImageView ivPhoto;
//    @BindView(R.id.iv_toright)
//    ImageView ivToright;
//    @BindView(R.id.ll_photo)
//    LinearLayout llPhoto;
//    @BindView(R.id.tv_content)
//    WebView tvContent;
//    @BindView(R.id.tv_empty)
//    TextView tvEmpty;
//    @BindView(R.id.tv_details_login)
//    TextView tvDetailsLogin;
//    @BindView(R.id.sl_hua)
//    ScrollView slHua;

    @BindView(R.id.img)
    ImageView LargeImg;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.tv_details_login)
    TextView tvDetailsLogin;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;

    private InvestmentInfoModel investmentInfoModels;
    private String id;
    private ArrayList<String> img = new ArrayList<>();
//    private ArrayList<String> content = new ArrayList<>();
    private int i = 0;
    private ProjectInfoModel projectInfoModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_brrow_data, container, false);
        ButterKnife.bind(this, view);
        initData();
        getDetails();
        return view;
    }

    private void initData() {
        if (PreferenceCache.getToken().isEmpty()) {
            tvDetailsLogin.setText("立即登录");
            tvDetailsLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (PreferenceCache.getToken().isEmpty()) {
                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        startActivity(i);
                    } else {
//                            if (projectInfoModel.getID_CARD_VERIFY_FLG().equals("0")) {
//                                goBank();
//                            } else {
                                Intent i = new Intent(getActivity(), NewInvestmentDetailsActivity.class);
                                i.putExtra("Info", projectInfoModel);
                                i.putExtra("ID", id);
                                startActivity(i);
                            }

//                    }
                }
            });
        } else {
            if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("未开始")) {
                tvDetailsLogin.setText("即将发布");
                tvDetailsLogin.setClickable(false);
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("立即投资")) {
                tvDetailsLogin.setText("立即投资");
                tvDetailsLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (PreferenceCache.getToken().isEmpty()) {
                            Intent i = new Intent(getActivity(), LoginActivity.class);
                            startActivity(i);
                        } else {
//                                if (projectInfoModel.getID_CARD_VERIFY_FLG().equals("0")) {
//                                   goBank();
//                                } else {
                                    Intent i = new Intent(getActivity(), NewInvestmentDetailsActivity.class);
                                    i.putExtra("Info", projectInfoModel);
                                    i.putExtra("ID", id);
                                    startActivity(i);
//                                }

                        }
                    }
                });
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("满标待审")) {
                tvDetailsLogin.setText("已满标");
                tvDetailsLogin.setClickable(false);
                tvDetailsLogin.setBackgroundColor(getResources().getColor(R.color.navy_gray));
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("已完成")) {
                tvDetailsLogin.setText("已还清");
                tvDetailsLogin.setClickable(false);
                tvDetailsLogin.setBackgroundColor(getResources().getColor(R.color.navy_gray));
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("还款中")) {
                tvDetailsLogin.setText("收益中");
                tvDetailsLogin.setClickable(false);
                tvDetailsLogin.setBackgroundColor(getResources().getColor(R.color.navy_gray));
            }

        }

    }

    private void getDetails() {
        showLoadingDialog();
        BizDataAsyncTask<InvestmentInfoModel> getInfo = new BizDataAsyncTask<InvestmentInfoModel>() {
            @Override
            protected InvestmentInfoModel doExecute() throws ZYException, BizFailure {
                return ProjectInfoBiz.getInvestInfo(id);
            }

            @Override
            protected void onExecuteSucceeded(InvestmentInfoModel investmentInfoModel) {
                hideLoadingDialog();
                investmentInfoModels = investmentInfoModel;
                initDetails(investmentInfoModel);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
            }
        };
        getInfo.execute();

    }

    private void initDetails(InvestmentInfoModel investmentInfoModel) {
        img.clear();
//        content.clear();
        for (int i = 0; i < investmentInfoModel.getFINANCEDESCRIPTIONLIST().size(); i++) {
            img.add(investmentInfoModel.getFINANCEDESCRIPTIONLIST().get(i).getIMGURL());
            Log.e("img",img.size()+"============"+investmentInfoModel.getFINANCEDESCRIPTIONLIST().get(i).getIMGURL());
//            content.add(investmentInfoModel.getFINANCEDESCRIPTIONLIST().get(i).getIMGTITLE());
        }
        if (img.size() != 0) {


            Glide.with(getActivity()).load(img.get(0)).crossFade().into(LargeImg);

            setGridView();

        } else {
            //显示 无数据
            gridView.setVisibility(View.GONE);
            LargeImg.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
        }

    }

    /**设置GirdView参数，绑定数据*/
    private void setGridView() {
        int size = img.size();
        int length = 80;
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(5); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数

        GridViewAdapter adapter = new GridViewAdapter(getActivity(),
                img);
        gridView.setAdapter(adapter);

        //添加点击事件  点击显示大图
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //可以显示图片大图
                Glide.with(getActivity()).load(img.get(position)).crossFade().into(LargeImg);
//                ImageView iv=view.findViewById(R.id.pic_iv);
//                iv.setBackgroundResource(R.drawable.text_view_type_border_red);
            }
        });

    }

    /**GirdView 数据适配器*/
    public class GridViewAdapter extends BaseAdapter {
        Context context;
        List<String> list;
        public GridViewAdapter(Context _context, List<String> _list) {
            this.list = _list;
            this.context = _context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.another_grid_item, null);
            //加载图片
            ImageView iv=convertView.findViewById(R.id.pic_iv);
            Glide.with(getActivity()).load(img.get(position)).crossFade().into(iv);

            return convertView;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        id = ((ProjectDetailsActivity) activity).getId();
        projectInfoModel = ((ProjectDetailsActivity) activity).getProjectInfoModel();
    }

    private void goBank(){
        //前往开通存管
        CallUtils.call(getActivity(), BankHttpUtil.getHttpRequestService().bankReg(projectInfoModel.getMOBILE()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                NewRegBean newRegBean= JSON.parseObject(jsonString,NewRegBean.class);
                Intent i=new Intent(getActivity(),BankWebViewActivity.class);
                i.putExtra("type",1);
                i.putExtra("newRegBean",newRegBean);
                startActivity(i);

            }
        });
    }

}
