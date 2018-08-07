
package com.desjf.dsjr.util;

import android.app.Activity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.desjf.dsjr.R;
import com.yitong.android.keyboard.AllKeyBoard;
import com.yitong.android.keyboard.CrptoSixKeyUtil;
import com.yitong.android.keyboard.CryptoKeyUtil;
import com.yitong.android.keyboard.KeyBoardJsImpl;
import com.yitong.android.keyboard.util.MyProgressBar;
import com.yitong.android.keyboard.util.RandomUtils;
import com.yitong.entity.KeyVo;
import com.yitong.service.APPResponseHandler;
import com.yitong.service.APPRestClient;
import com.yitong.service.YTRequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Author: wdy@yitong.com.cn
 * Date: 2018/4/3
 * Description: 卡密码 RSA加密
 */
public class KeyBoardPluginRSA implements KeyBoardJsImpl {

	private static final String TAG = "KeyBoardPluginRSA";

	private Activity activity;

	private WebView webView;

	private MyProgressBar myProgress;

	public KeyBoardPluginRSA(Activity activity, WebView webView) {
		this.activity = activity;
		this.webView = webView;
	}

	public Object getClass(Object o) {
		return null;
	}

	private String input_id;
	private String input_callback;
	private AllKeyBoard keyBoard;

	private boolean isCardBoard = false;
	private String pubKey;

	private String random8;


	/**
	 * 渤海卡密码键盘
	 *
	 * @param json
	 */
	@JavascriptInterface
	public void showBHCardPwdKey(final String json) {

		isCardBoard = true;
		showProgress();
		YTRequestParams reqParams = new YTRequestParams(
				YTRequestParams.PARAM_TYPE_JSON);

		String key = YTRequestParams.genRandomKey();

		APPRestClient.post(activity, Constans.URL,
				reqParams, new APPResponseHandler<KeyVo>(KeyVo.class, key) {
					@Override
					public void onSuccess(KeyVo result) {
						if (result != null && "1".equals(result.getSTATUS())) {
							pubKey = result.getPubKey();
							try {
								JSONObject jsonObject = new JSONObject(json);
								// 当前长度
								int length = jsonObject.getInt("length");
								// 输入框id
								input_id = (String) jsonObject.get("id");
								// 回调方法名
								input_callback = (String) jsonObject.get("callback");
								// 密钥
								// 对pubKey做处理避免加密时出错导致崩溃

								// 随机数
								random8 = RandomUtils.generateNumberString(8);

								if (keyBoard != null) {
									// 隐藏上一个键盘
									keyBoard.hideKeyboard(false);
								}
								keyBoard = new AllKeyBoard(activity, true, 2, KeyBoardPluginRSA.this, true, "12");

								keyBoard.clear();
								keyBoard.showKeyboard(length);
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}

					}

					@Override
					public void onFailure(String s, String s1) {

					}

					@Override
					public void onFinish() {
						super.onFinish();
						dismissProgress();
					}
				}, key);


	}


	/**
	 * 密码键盘回调
	 *
	 * @param inputValue
	 * @param currentLength
	 */
	@Override
	public void updateDigitNumber(final StringBuffer inputValue,
								  final int currentLength, final boolean isPwd) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (isPwd && isCardBoard) {

					// 参数回显的点
					StringBuilder point = new StringBuilder("");
					for (int i = 0; i < currentLength; i++) {
						point.append("*");
					}

					if (currentLength == 6) {
						webView.loadUrl("javascript:"
								+ input_callback
								+ "('"
								+ point.toString()
								+ "','"
								+ CrptoSixKeyUtil.decrypt(pubKey, random8,
								CryptoKeyUtil.genRandomNum(32),
								inputValue) + "')");
					} else {
						webView.loadUrl("javascript:" + input_callback
								+ "('" + point.toString() + "','"
								+ point.toString() + "')");
					}


				}
			}
		});
	}

	// 隐藏键盘时回调服务端方法
	@Override
	public void hideKeyboard() {
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				isCardBoard = false;
				// String uri = "javascript:$('#" + input_id + "').blur();";
				String uri = "javascript:_hideKeyboard(" + input_id + ");";
				webView.loadUrl(uri);
			}
		});

	}

	@Override
	public void preShowKeyboard() {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// String uri = "javascript:_preShowKeyBoard(" + input_id + ")";
				// webView.loadUrl(uri);
			}
		});
	}

	// 隐藏键盘
	@JavascriptInterface
	public void dismissKeyboard() {
		if (keyBoard != null
				&& keyBoard.getBoardView().getVisibility() == View.VISIBLE) {
			keyBoard.hideKeyboard(true);
		}
	}

	@JavascriptInterface
	public boolean hasKeyBoard() {
		if (keyBoard != null
				&& keyBoard.getBoardView().getVisibility() == View.VISIBLE) {
			return true;
		}
		return false;
	}

	private void showProgress() {
		if (myProgress == null) {
			myProgress = new MyProgressBar(activity, R.style.CustomProgressDialog);
		}
		myProgress.show();
	}

	private void dismissProgress() {
		if (null != myProgress && myProgress.isShowing()) {
			myProgress.dismiss();
		}
	}
}
