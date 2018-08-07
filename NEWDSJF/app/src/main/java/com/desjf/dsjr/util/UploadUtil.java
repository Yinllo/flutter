package com.desjf.dsjr.util;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public class UploadUtil {

    private static UploadUtil uploadUtil;
    private static final String BOUNDARY =  UUID.randomUUID().toString(); // 边界标识 随机生成
    private static final String PREFIX = "--";
    private static final String LINE_END = "\r\n";
    private static final String CONTENT_TYPE = "multipart/form-data"; // 内容类型
    private UploadUtil() {

    }

    /**
     * 单例模式获取上传工具类
     * @return
     */
    public static UploadUtil getInstance() {
        if (null == uploadUtil) {
            uploadUtil = new UploadUtil();
        }
        return uploadUtil;
    }

    private static final String TAG = "UploadUtil";
    private int readTimeOut = 10 * 10000; // 读取超时
    private int connectTimeout = 10 * 10000; // 超时时间
    /***
     * 请求使用多长时间
     */
    private static int requestTime = 0;

    private static final String CHARSET = "utf-8"; // 设置编码

    /***
     * 上传成功
     */
    public static final int UPLOAD_SUCCESS_CODE = 1;
    /**
     * 文件不存在
     */
    public static final int UPLOAD_FILE_NOT_EXISTS_CODE = 2;
    /**
     * 服务器出错
     */
    public static final int UPLOAD_SERVER_ERROR_CODE = 3;
    protected static final int WHAT_TO_UPLOAD = 1;
    protected static final int WHAT_UPLOAD_DONE = 2;



    public boolean uploadFile(HashMap<Integer,String> filePathMap, String fileKey, String RequestURL,
                              Map<String, String> param) {
        String result = null;
        requestTime= 0;
        String fileName = "";
        long requestTime = System.currentTimeMillis();
        long responseTime = 0;

        try {

//          conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            Iterator<Integer> iterator = filePathMap.keySet().iterator();
            boolean isOk = true;
            System.out.println("filePathMap.size()"+filePathMap.size());
            while(iterator.hasNext()) {
                URL url = new URL(RequestURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(readTimeOut);
                conn.setConnectTimeout(connectTimeout);
                conn.setDoInput(true); // 允许输入流
                conn.setDoOutput(true); // 允许输出流
                conn.setUseCaches(false); // 不允许使用缓存
                conn.setRequestMethod("POST"); // 请求方式
                conn.setRequestProperty("Charset", CHARSET); // 设置编码
                conn.setRequestProperty("connection", "keep-alive");
                conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
                conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
                Integer next = iterator.next();
                System.out.println(next+"");
                String filepath = filePathMap.get(next);
                switch (next.intValue()) {
                    case 0:
                        fileName = "IDPositive";
                        break;
                    case 1:
                        fileName = "IDNative";
                        break;
                    case 2:
                        fileName = "BusinessLicense";
                        break;
                    case 3:
                        fileName = "Outdoor";
                        break;
                    case 4:
                        fileName = "Indoor";
                        break;
                    default:
                        break;
                }
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                StringBuffer sb = null;
                String params = "";

                if (param != null && param.size() > 0) {
                    Iterator<String> it = param.keySet().iterator();
                    while (it.hasNext()) {
                        sb = null;
                        sb = new StringBuffer();
                        String key = it.next();
                        String value = param.get(key);
                        sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
                        sb.append("Content-Disposition: form-data; name=\"").append(key).append("\"").append(LINE_END).append(LINE_END);
                        sb.append(value).append(LINE_END);
                        params = sb.toString();
                        Log.i(TAG, key+"="+params+"##");
                        dos.write(params.getBytes());
//                      dos.flush();
                    }
                }

                sb = null;
                params = null;
                sb = new StringBuffer();
                /**
                 * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后缀名的 比如:abc.png
                 */
                sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
                sb.append("Content-Disposition:form-data; name=\"" + fileKey
                        + "\"; filename=\"" + fileName + "\"" + LINE_END);
                //+ "\"; filename=\"" + file.getName() + "\"" + LINE_END);
                sb.append("Content-Type:image/pjpeg" + LINE_END); // 这里配置的Content-type很重要的 ，用于服务器端辨别文件的类型的
                sb.append(LINE_END);
                params = sb.toString();
                sb = null;

                //Log.i(TAG, filepath.getName()+"=" + params+"##");
                //Log.i(TAG, );
                dos.write(params.getBytes());
                /**上传文件*/
                InputStream is = new FileInputStream(filepath);
                onUploadProcessListener.initUpload((int)filepath.length());
                byte[] bytes = new byte[1024];
                int len = 0;
                int curLen = 0;
                while ((len = is.read(bytes)) != -1) {
                    curLen += len;
                    dos.write(bytes, 0, len);
                    onUploadProcessListener.onUploadProcess(curLen);
                }
                is.close();
                is = null;
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
                dos.write(end_data);
                dos.flush();
                dos.close();
                dos = null;
//              dos.write(tempOutputStream.toByteArray());
                /**
                 * 获取响应码 200=成功 当响应成功，获取响应的流
                 */
                int res = conn.getResponseCode();
                responseTime = System.currentTimeMillis();
                this.requestTime = (int) ((responseTime-requestTime)/1000);

                Log.e(TAG, "response code:" + res);

                if (res == 200) {
                    Log.e(TAG, "request success");
                    InputStream input = conn.getInputStream();
                    StringBuffer sb1 = new StringBuffer();
                    int ss;
                    while ((ss = input.read()) != -1) {
                        sb1.append((char) ss);
                    }
                    result = sb1.toString();
                    Log.e(TAG, "result : " + result);
                    sb1 = null;
                    if(fileName == "Indoor") {
                        sendMessage(UPLOAD_SUCCESS_CODE, "上传结果："+ result);
                    }

                    conn.disconnect();
                    //return true;
                } else {
                    Log.e(TAG, "request error");
                    sendMessage(UPLOAD_SERVER_ERROR_CODE,"上传失败：code=" + res);
                    conn.disconnect();
                    isOk = false;

                }


            }
            return isOk;
        } catch (MalformedURLException e) {
            sendMessage(UPLOAD_SERVER_ERROR_CODE,"上传失败：error=" + e.getMessage());
            e.printStackTrace();

            return false;
        } catch (IOException e) {
            sendMessage(UPLOAD_SERVER_ERROR_CODE,"上传失败：error=" + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }


    private void sendMessage(int responseCode,String responseMessage)
    {
        onUploadProcessListener.onUploadDone(responseCode, responseMessage);
    }

    /**
     * 下面是一个自定义的回调函数，用到回调上传文件是否完成
     *
     * @author shimingzheng
     *
     */
    public static interface OnUploadProcessListener {

        void onUploadDone(int responseCode, String message);

        void onUploadProcess(int uploadSize);

        void initUpload(int fileSize);
    }
    private OnUploadProcessListener onUploadProcessListener;



    public void setOnUploadProcessListener(
            OnUploadProcessListener onUploadProcessListener) {
        this.onUploadProcessListener = onUploadProcessListener;
    }

    public int getReadTimeOut() {
        return readTimeOut;
    }

    public void setReadTimeOut(int readTimeOut) {
        this.readTimeOut = readTimeOut;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
    /**
     * 获取上传使用的时间
     * @return
     */
    public static int getRequestTime() {
        return requestTime;
    }

    public static interface uploadProcessListener{

    }



}
