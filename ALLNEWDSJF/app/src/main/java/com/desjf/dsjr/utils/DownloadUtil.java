package com.desjf.dsjr.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;



/**
 * @Author YinL
 * @Date 2018/1/16 0016
 * @Describe   apk文件下载
 */

public class DownloadUtil {
    public static String postRespString(String uri, Object... params) throws IOException {
        try {
            return new String(post(uri, true, params), HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] post(String uri, boolean resp, Object... params) throws IOException {
        ArrayList<NameValuePair> paramList = new ArrayList<NameValuePair>();
        String paramName = null;
        for(Object param : params){
            if(paramName == null){
                paramName = param.toString();
            }else{
                paramList.add(new BasicNameValuePair(paramName, param == null ? "" : param.toString()));
                paramName = null;
            }
        }

        HttpClient client = new DefaultHttpClient();
        HttpParams httpParams = client.getParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 15000);
        HttpConnectionParams.setSoTimeout(httpParams, 15000);
        HttpPost post = new HttpPost(uri);
        post.setEntity(new UrlEncodedFormEntity(paramList, HTTP.UTF_8));
        HttpResponse response = client.execute(post);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            return resp ? EntityUtils.toByteArray(response.getEntity()) : null;
        }else{
            throw new IOException("Http status: " + response.getStatusLine().getStatusCode());
        }
    }

	/*
	public static String postBinaryRespString(String uri, Object params) throws IOException {
		return new String(postBinary(uri, true, params));
	}

	public static byte[] postBinary(String uri, boolean resp, Object... params) throws IOException {
		HttpClient client = new DefaultHttpClient();
		HttpParams httpParams = client.getParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 15000);
		HttpConnectionParams.setSoTimeout(httpParams, 15000);
		HttpPost post = new HttpPost(uri);

		MultipartEntity entity = new MultipartEntity();
		String paramName = null;
		for(Object param : params){
			if(paramName == null){
				paramName = param.toString();
			}else{
				if(param instanceof File){
					entity.addPart(paramName, new FileBody((File)param));
				}else if(param instanceof byte[]){
					entity.addPart(paramName, new InputStreamBody(new ByteArrayInputStream((byte[])param), ""));
				}else{
					entity.addPart(paramName, new StringBody(param == null ? "" : param.toString()));
				}
			}
		}
		post.setEntity(entity);

		HttpResponse response = client.execute(post);
		if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			return resp ? EntityUtils.toByteArray(response.getEntity()) : null;
		}else{
			throw new IOException("Http status: " + response.getStatusLine().getStatusCode());
		}
	}*/

    public static String getRespString(String uri, Object... params) throws IOException {
        try {
            return new String(get(uri, true, params), HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] get(String uri, Object... params) throws IOException {
        return get(uri, true, params);
    }

    public static byte[] get(String uri, boolean resp, Object... params) throws IOException {
        String paramStr = buildUrlParams(params);
        if(uri.indexOf("?") < 0){
            paramStr = paramStr.replaceFirst("&", "?");
        }

        HttpClient client = new DefaultHttpClient();
        HttpParams httpParams = client.getParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 15000);
        HttpConnectionParams.setSoTimeout(httpParams, 15000);
        HttpGet get = new HttpGet(uri + paramStr);
        HttpResponse response = client.execute(get);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            return resp ? EntityUtils.toByteArray(response.getEntity()) : null;
        }else{
            throw new IOException(uri + paramStr + " - Http status: " + response.getStatusLine().getStatusCode());
        }

    }

    public static String buildUrlParams(Object... params){
        String paramStr = "";
        String paramName = null;
        for(Object param : params){
            if(paramName == null){
                paramName = param.toString();
            }else{
                try {
                    paramStr += "&" + paramName + "=" + (param == null ? "" : URLEncoder.encode(param.toString(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                paramName = null;
            }
        }
        return paramStr;
    }

    public static void downloadFile(String url, String fileName) throws IOException {
        downloadFile(url, new File(fileName));
    }

    public static interface IDownloadCallback{
        public void onProgress(long currentSize, long totalSize);
    }

    public static void downloadFile(String url, File file) throws IOException {
        downloadFile(url, file, null);
    }

    public static void downloadFile(InputStream is, File file) throws IOException {


        if (is != null) {
            try{
                if(file.exists()){
                    file.delete();
                }
                FileOutputStream fos = new FileOutputStream(file);
                try {
                    byte[] buf = new byte[1024 * 8];
                    int len = -1;
                    while ((len = is.read(buf)) > 0) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                } finally {
                    fos.close();
                }
                if(!file.exists()){
                    throw new IOException("Downloaded file missing");
                }
            }finally{
                is.close();
            }
        }else{
            throw new IOException("Failed to open stream for reading");
        }
    }

    public static void downloadFile(String url, File file, IDownloadCallback callback) throws IOException {


        HttpClient client = new DefaultHttpClient();
        HttpParams httpParams = client.getParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 15000);
        HttpConnectionParams.setSoTimeout(httpParams, 15000);
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){


            HttpEntity entity = response.getEntity();
            long totalSize = entity.getContentLength();


            if(callback != null){
                callback.onProgress(0, totalSize);
            }
            InputStream is = entity.getContent();
            if (is != null) {
                try{
                    if(file.exists()){
                        file.delete();
                    }
                    FileOutputStream fos = new FileOutputStream(file);
                    try {
                        long currentSize = 0;
                        byte[] buf = new byte[1024 * 8];
                        int len = -1;
                        while ((len = is.read(buf)) > 0) {
                            fos.write(buf, 0, len);
                            currentSize += len;
                            if(callback != null){
                                callback.onProgress(currentSize, totalSize);
                            }

                        }
                        fos.flush();
                    } finally {
                        fos.close();
                    }
                    if(!file.exists()){
                        throw new IOException("Downloaded file missing: " + url);
                    }
                }finally{
                    is.close();
                }
            }else{
                throw new IOException("Failed to open stream for reading: " + url);
            }
        }else{

            throw new IOException("Http status " + response.getStatusLine().getStatusCode() + ": " + url);
        }
    }


}
