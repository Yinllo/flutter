package com.desjf.dsjr.util;

import android.app.Activity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.desjf.dsjr.R;
import com.yitong.android.keyboard.AllKeyBoard;
import com.yitong.android.keyboard.CryptoKeyUtil;
import com.yitong.android.keyboard.KeyBoardJsImpl;
import com.yitong.android.keyboard.util.MyProgressBar;
import com.yitong.android.keyboard.util.RandomUtils;
import com.yitong.android.keyboard.util.Sm2EncryptUtil;
import com.yitong.entity.KeyVo;
import com.yitong.service.APPResponseHandler;
import com.yitong.service.APPRestClient;
import com.yitong.service.YTRequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Author: wdy@yitong.com.cn
 * Date: 2018/3/15
 * Description: webview键盘插件类
 */

public class KeyboardPlugin implements KeyBoardJsImpl {

	private Activity activity;
	private WebView webView;


	private String smPubKey;

	private String input_id;
	private String input_callback;
	private AllKeyBoard keyBoard;

	private String mInputValue = "";
	private MyProgressBar myProgress;
	public KeyboardPlugin(Activity activity, WebView webView) {
		this.activity = activity;
		this.webView = webView;
	}

	public Object getClass(Object o) {
		return null;
	}


	/**
	 * 打开全键盘
	 *
	 * @param json json串 { "length":"当前长度", "id":"输入框id","callback":"回调函数" }
	 */
	@JavascriptInterface
	public void callAllKeyboard(final String json) {
		Log.d("yt_keyboard", "callAllKeyboard:" + json);
		showProgress();

		YTRequestParams reqParams = new YTRequestParams(
				YTRequestParams.PARAM_TYPE_JSON);
		reqParams.put("EncryType","1");
		String key = YTRequestParams.genRandomKey();

		APPRestClient.post( activity,Constans.URL,
				reqParams, new APPResponseHandler<KeyVo>(KeyVo.class,key) {
					@Override
					public void onSuccess(KeyVo result) {
						if(result != null && "1".equals(result.getSTATUS())){
							 smPubKey = result.getPubKey();
							Log.d("yt_keyboard", "请求公钥成功：" + smPubKey);
							JSONObject jsonObject = null;
							try {
								jsonObject = new JSONObject(json);
								// 当前长度
								int length = jsonObject.getInt("length");
								// 输入框id
								input_id = (String) jsonObject.get("id");
								// 回调方法名
								input_callback = (String) jsonObject.get("callback");
								if (keyBoard != null) {
									// 隐藏上一个键盘
									keyBoard.hideKeyboard(false);
								}
								keyBoard = new AllKeyBoard(activity, true, 1,
										KeyboardPlugin.this, true, "0");
								keyBoard.clear();
								keyBoard.showKeyboard(length);
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					}

					@Override
					public void onFailure(String errorCode, String errorMsg) {
						Log.d("yt_keyboard",errorMsg );
					}

					@Override
					public void onFinish() {
						super.onFinish();
						dismissProgress();
					}
				},key);


	}

	/**
	 * 打开数字键盘
	 *
	 * @param json json串 { "length":"当前长度", "id":"输入框id","callback":"回调函数" }
	 */
	@JavascriptInterface
	public void callNumKeyboard(final String json){
		Log.d("yt_keyboard", "callNumKeyboard:" + json);
		showProgress();

		YTRequestParams reqParams = new YTRequestParams(
				YTRequestParams.PARAM_TYPE_JSON);
		reqParams.put("EncryType","1");
		String key = YTRequestParams.genRandomKey();

		APPRestClient.post( activity,Constans.URL,
				reqParams, new APPResponseHandler<KeyVo>(KeyVo.class,key) {
					@Override
					public void onSuccess(KeyVo result) {
						if(result != null && "1".equals(result.getSTATUS())){
							smPubKey = result.getPubKey();
							Log.d("yt_keyboard", "请求公钥成功：" + smPubKey);

							JSONObject jsonObject = null;
							try {
								jsonObject = new JSONObject(json);
								// 当前长度
								int length = jsonObject.getInt("length");
								// 输入框id
								input_id = (String) jsonObject.get("id");
								// 回调方法名
								input_callback = (String) jsonObject.get("callback");
								if (keyBoard != null) {
									// 隐藏上一个键盘
									keyBoard.hideKeyboard(false);
								}
								keyBoard = new AllKeyBoard(activity, true, 2, KeyboardPlugin.this, true, "12");
								keyBoard.clear();
								keyBoard.showKeyboard(length);

							} catch (JSONException e) {
								e.printStackTrace();
							}

						}
					}

					@Override
					public void onFailure(String errorCode, String errorMsg) {
						Log.d("yt_keyboard",errorMsg );
					}

					@Override
					public void onFinish() {
						super.onFinish();
						dismissProgress();
					}
				},key);

	}



	@Override
	public void updateDigitNumber(final StringBuffer inputValue, final int currentLength, boolean b) {
		Log.d("yt_keyboard", "更新字符" );
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mInputValue = inputValue.toString();
				// 参数回显的点
				StringBuilder point = new StringBuilder("");
				for (int i = 0; i < currentLength; i++) {
					point.append("*");
				}

				webView.loadUrl("javascript:" + input_callback
						+ "('" + point.toString() + "','"
						+ point.toString() + "')");
			}
		});

	}

	@Override
	public void hideKeyboard() {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (mInputValue != null && mInputValue.length() > 0) {
					Log.d("yt_keyboard", "隐藏键盘时上送密码" );
					StringBuffer pwd = CryptoKeyUtil.decrypt(mInputValue);
					String smPassword = Sm2EncryptUtil.encryptWithFormat(pwd.toString(), smPubKey);
					String randomKey = RandomUtils.generateNumberString(8);
					if(randomKey != null){
						String smRandomKey = Sm2EncryptUtil.encrypt(randomKey, smPubKey);
						StringBuilder data = new StringBuilder("");
						String password = randomKey + smPassword + smRandomKey;
						for (int i = 0; i < pwd.length(); i++) {
							data.append("*");
						}
						String uri = "javascript:" + input_callback + "('"
								+ data.toString() + "','" + password + "')";
						webView.loadUrl(uri);
					}
					mInputValue = "";
				}
			}
		});

	}

	@Override
	public void preShowKeyboard() {

	}

	private void showProgress(){
		if (myProgress == null) {
			myProgress = new MyProgressBar(activity, R.style.CustomProgressDialog);
		}
		myProgress.show();
	}
	private void dismissProgress(){
		if (null != myProgress && myProgress.isShowing()) {
			myProgress.dismiss();
		}
	}

}
