package com.desjf.dsjr.base;


public class ExtraConfig {

	public static final int DEFAULT_PAGE_COUNT = 20;

	public static class BaseReceiverAction {
		public static final String ACTION_PREFIX = DSJRConfig.ACTION_BASE_PREFIX;

		/**
		 * token过期
		 */
		public static final String ACTION_TOKEN_EXPIRE = ACTION_PREFIX
				+ "token.expire";
		
	}

	public static class IntentExtraKey {
		public static final String PASSWORD = "password";
		public static final String RETURN_MONEY_DETAIL_ONE_ID = "return_money_detail_one_id";
		public static final String RETURN_MONEY_DETAIL_ONE_TYPR = "return_money_detail_one_type";
		public static final String RED_ID = "redId";
		public static final String TICKET_ID = "ticketId";
		public static final String TRANSFER_ID = "transferId";
		public static final String TRANSFER_FULL_STATUS = "TRANSFER_FULL_STATUS";
		public static final String MY_ACCOUNT = "my_account";
		public static final String LOGIN_FROM_MAIN = "login_from_main";
		public static final String WEB_VIEW_FROM = "web_view_from";
		public static final String AMOUNT = "amount";
		public static final String ACCOUNT_MSG = "account_msg";
		public static final String MY_TRANSFER_OID_TENDER_ID = "my_transfer_oid_tender_id";
		public static final String MY_TRANSFER_TENDER_FROM = "my_transfer_tender_from";
		public static final String MY_TRANSFER_FINACE_NAME = "my_transfer_finace_name";
		public static final String SECURITY_CENTER_ADDRESS_CHANGE = "security_center_address_change";
		public static final String MY_TRANSFER_DETAIL_BEAN = "my_transfer_detail_bean";
		public static final String MY_TRANSFER_DETAIL_ID = "my_transfer_detail_ID";
		public static final String ACCOUNT_TELE_NUM = "account_tele_num";
		public static final String USER_AMOUNT = "user_amount";
		public static final String PRODUCT_ID = "productId";
		public static final String FLG = "flag";
		public static final String DIRECTIONAL_PWD_FLG = "DIRECTIONAL_PWD_FLG";
		public static final String DEBTDETAILMODEL = "DEBTDETAILMODEL";



	}

	public static class RequestCode {
		public static final int REQUEST_CODE_FOR_LOGIN = 0x01;
		public static final int REQUEST_CODE_FOR_MESSAGE_INFO = 0x02;
		public static final int REQUEST_CODE_FOR_MY_TRANSFER = 0x03;
		public static final int REQUEST_CODE_FOR_MY_DEAL_PWD_CHANGE = 0x04;
		public static final int REQUEST_CODE_FOR_MY_ADDRESS_CHANGE = 0x05;
		public static final int REQUEST_CODE_FOR_MESSAGE = 0x06;
		public static final int REQUEST_CODE_SCREEN = 0x07;
	}
}
