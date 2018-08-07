package com.desjf.dsjr.utils;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

/**
 * Created by YL on 2017/12/12 0012.   投诉建议部分的 文件上传类
 */

public class HttpAssist {

    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 10 * 10000000; // 超时时间
    private static final String CHARSET = "utf-8"; // 设置编码
    public static final String SUCCESS = "提交成功，感谢您的反馈！";
    public static final String FAILURE = "提交失败，请您重新尝试！";
    public static  DataOutputStream dos;
    public static String uploadFile(File[] file, Map<String,String> paramMap) {
        Log.e("fff","进入了uploadFile");
        String BOUNDARY = UUID.randomUUID().toString(); // 边界标识 随机生成
        String PREFIX = "--", LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data"; // 内容类型
//        String RequestURL = "https://www.dsp2p.com/IloanFront/complaintAdvice/intComplaintAdvice";
        String RequestURL = "http://t.dsp2p.com/IloanFront/complaintAdvice/intComplaintAdvice";
        try {
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true); // 允许输入流
            conn.setDoOutput(true); // 允许输出流
            conn.setUseCaches(false); // 不允许使用缓存
            conn.setRequestMethod("POST"); // 请求方式
            conn.setRequestProperty("Charset", CHARSET); // 设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
                    + BOUNDARY);

            OutputStream outputSteam = conn.getOutputStream();

            dos = new DataOutputStream(outputSteam);
            if (file != null) {
                /**
                 * 当文件不为空，把文件包装并且上传
                 */
                for(int i=0;i<file.length;i++) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(PREFIX);
                    sb.append(BOUNDARY);
                    sb.append(LINE_END);
                    /**
                     * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
                     * filename是文件的名字，包含后缀名的 比如:abc.png
                     */

                    sb.append("Content-Disposition: form-data; name=\"file\"; filename=\""
                            + file[i].getName() + "\"" + LINE_END);
                    sb.append("Content-Type: application/octet-stream; charset="
                            + CHARSET + LINE_END);
                    sb.append(LINE_END);
                    dos.write(sb.toString().getBytes());
                    InputStream is = new FileInputStream(file[i]);
                    byte[] bytes = new byte[1024*10];
                    int len = 0;
                    while ((len = is.read(bytes)) != -1) {
                        dos.write(bytes, 0, len);
                    }
                    is.close();
                    dos.write(LINE_END.getBytes());
                }
            }
//                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
//                        .getBytes();
//                dos.write(end_data);
//                dos.flush();
                /**
                 * 上传文字内容
                 */
                StringBuilder text = new StringBuilder();
                for(Map.Entry<String,String> entry : paramMap.entrySet()) { //在for循环中拼接报文，上传文本数据
                    text.append("--");
                    text.append(BOUNDARY);
                    text.append("\r\n");
                    text.append("Content-Disposition: form-data; name=\""+ entry.getKey() + "\"\r\n\r\n");
                    text.append(entry.getValue());
                    text.append("\r\n");
                }
                dos.write(text.toString().getBytes("utf-8")); //写入文本数据

                // 请求结束标志
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
                        .getBytes();
                dos.write(end_data);
                dos.flush();

                /**
                 * 获取响应码 200=成功 当响应成功，获取响应的流
                 */
                int res = conn.getResponseCode();
                if (res == 200) {
                    conn.getResponseMessage();

                    conn.getResponseMessage();
                    return SUCCESS;
                }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FAILURE;
    }

}
