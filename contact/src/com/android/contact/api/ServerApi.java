package com.android.contact.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.utils.StringUtils;
import com.android.app.App;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ServerApi {
	/**
	 * ��½
	 * 
	 * @param username
	 * @param password
	 * @param handler
	 * @throws JSONException
	 */
	public static void login(String username, String password,
			AsyncHttpResponseHandler handler) throws JSONException {
		RequestParams params = new RequestParams();
		params.put("op", "CheckUser");// �豸ID
		params.put("name", username);// �豸ID
		params.put("pwd", password);// �豸ID
		String loginurl = "contact/android/login.ashx";
		ApiHttpClient.post(loginurl, params, handler);
	}

	public static void addUser(String fname, String lname, String mail,
			String password, String cpwd, AsyncHttpResponseHandler handler)
			throws JSONException {
		RequestParams params = new RequestParams();

		params.put("uid", App.getApp().getLoginUser().getUId());// �豸ID
		params.put("type", "1");// �豸ID
		params.put("fname", fname);// �豸ID
		params.put("lname", lname);// �豸ID
		params.put("email", mail);// �豸ID
		params.put("pwd", password);// �豸ID
		params.put("cpwd", cpwd);// �豸ID

		String loginurl = "contact/android/adduser.ashx?type=1";
		ApiHttpClient.post(loginurl, params, handler);
	}

	public static void addContact(String fname, String lname, String mail,
			String dw, String add, String tel, String birth,
			AsyncHttpResponseHandler handler) throws JSONException {
		RequestParams params = new RequestParams();

		params.put("uid", App.getApp().getLoginUser().getUId());// �豸ID
		params.put("type", "0");// �豸ID
		params.put("fname", fname);// �豸ID
		params.put("lname", lname);// �豸ID
		params.put("email", mail);// �豸ID
		params.put("tel", tel);// �豸ID
		params.put("birth", birth);// �豸ID
		params.put("add", add);// �豸ID
		params.put("dw", dw);// �豸ID

		String loginurl = "contact/android/adduser.ashx?type=0";
		ApiHttpClient.post(loginurl, params, handler);
	}

	public static void Ts(String usercode, String title, String content,
			String xmmc, String xmbh, AsyncHttpResponseHandler handler)
			throws JSONException {
		RequestParams params = new RequestParams();
		params.put("op", "InsertTSData");// �豸ID
		params.put("title", title);// �豸ID
		params.put("content", content);// �豸ID
		params.put("xmmc", xmmc);// �豸ID
		params.put("xmbh", xmbh);// �豸ID
		params.put("usercode", usercode);// �豸ID
		String loginurl = "bid_web/AppWebservice/BidWebService.ashx";

		// http://dev.dachkj.com:50111/bid_web/AppWebservice/BidWebService.ashx?op=InsertTSData&usercode=TJHDGS003&title
		ApiHttpClient.get(loginurl, params, handler);
	}

	public static void getZBXQ(String id, AsyncHttpResponseHandler handler)
			throws JSONException {
		RequestParams params = new RequestParams();
		params.put("op", "getOnTenderNoticeInfo");// �豸ID
		params.put("noticeID", id);// �豸ID
		String loginurl = "bid_web/AppWebservice/BidWebService.ashx";
		ApiHttpClient.get(loginurl, params, handler);
	}

	public static void getTenderInfo(String cid,
			AsyncHttpResponseHandler handler) throws JSONException {
		RequestParams params = new RequestParams();
		params.put("op", "getTenderNoticeInfo");// �豸ID
		params.put("noticeID", cid);

		String loginurl = "bid_web/AppWebservice/BidWebService.ashx";
		ApiHttpClient.get(loginurl, params, handler);

		/*
		 * {"isOK":"true","total":1,"rows":[{"cid":
		 * "b662e639-fb63-4489-a605-a6c6012b10eb"
		 * ,"c_user_cid":"258745c8-83f1-4c77-8cf9-a6ab00a1009d"
		 * ,"c_user_code":"TJrongchuangcheng"
		 * ,"c_user_name":"����ڴ���","c_date":"2016/11/22 18:08:51"
		 * ,"u_user_cid":"",
		 * "u_user_code":"","u_user_name":"","u_date":"","info_status"
		 * :"2","n_title"
		 * :"TestMessage","n_cid":"","n_type":"","n_pub_date":"","n_pub_cid"
		 * :"","n_pub_code"
		 * :"","n_pub_name":"","n_is_remind":"��","n_is_stick":"��"
		 * ,"n_sort":"9","n_newtime":"","n_stt":"��","n_profile":"","n_content":
		 * "��Ŀ���ƣ�TestMessage�����ۿ�ʼʱ�䣺2016-11-22 18:07������ʱ�䣺2016-11-23 18:07���б����ϣ����ϱ��룺TJHD-001���������ƣ���˹���������ͺţ�8��������,����λ��ƽ�ף�������100.000|"
		 * ,
		 * "n_keywords":"","n_click":"0","n_status":"����","n_attach":"","companyType"
		 * :"���","deliveryArea":"���"}]}
		 */
	}

	public static void getOnTenderInfo(String cid,
			AsyncHttpResponseHandler handler) throws JSONException {
		RequestParams params = new RequestParams();
		params.put("op", "getOnTenderNoticeInfo");// �豸ID
		params.put("noticeID", cid);

		String loginurl = "bid_web/AppWebservice/BidWebService.ashx";
		ApiHttpClient.get(loginurl, params, handler);
		/*
		 * {"isOK":"true","total":1,"rows":[{"REV_CID":
		 * "85ef4aa7-c3c9-4129-a728-0ca084423198"
		 * ,"XMMC":"20161117��ʾ����ڴ�����Ŀ","WLNAME"
		 * :"�۵���","GYSNAME":"����м���ӡˢ���޹�˾","gyscode"
		 * :"TJYSGS003","ZBJG":"650","SBSL"
		 * :"10","TOTAL":"-","ZSFBTIME":"2016/11/17 15:09:00"
		 * ,"ENDTIME":"2016/11/17 15:13:10"
		 * ,"FKFS":"","FKTJ":"","CHOOSEJHQ":"","Price"
		 * :"89.00","lowPrice":"6700.00"
		 * ,"WLCODE":"TJHD-005","NAME":"����м���ӡˢ���޹�˾"}]}
		 */
	}

	public static void getNoticeInfo(String cid,
			AsyncHttpResponseHandler handler) throws JSONException {
		RequestParams params = new RequestParams();
		params.put("op", "getCompanyNoticeInfo");// �豸ID
		params.put("CID", cid);

		String loginurl = "bid_web/AppWebservice/BidWebService.ashx";
		ApiHttpClient.get(loginurl, params, handler);
		/*
		 * {"isOK":"true","total":1,"rows":[{"cid":
		 * "19f75c6b-c680-475b-9db2-a6c000fbf862"
		 * ,"N_PUB_NAME":"ϵͳ����Ա","N_TITLE":
		 * "����","N_PUB_DATE":"2016/11/16 15:17:00"
		 * ,"N_PROFILE":"���","N_CONTENT":"<p> ��ϸ����
		 * </p>","N_KEYWORDS":"�ؼ���","DA_SERVERFILE
		 * ":"/Files/notice/826d09af-d33e-4516-ac60-a6c1012a5deb��Ӧ�̵���ģ��.xlsx"}]}
		 */
	}

	public static void getUserList(String type, String page,
			AsyncHttpResponseHandler handler) throws JSONException {
		RequestParams params = new RequestParams();
		params.put("type", type);// �豸ID
		params.put("page", page);// �豸ID
		params.put("uid", App.getApp().getLoginUser().getUId());

		String loginurl = "contact/android/user.ashx";
		ApiHttpClient.get(loginurl, params, handler);

	}
	public static void getUserListS(String Conditon, String page,
			AsyncHttpResponseHandler handler) throws JSONException {
		RequestParams params = new RequestParams();
		params.put("f", Conditon);// �豸ID
		params.put("page", page);// �豸ID
		params.put("uid", App.getApp().getLoginUser().getUId());

		String loginurl = "contact/android/users.ashx";
		ApiHttpClient.get(loginurl, params, handler);

	}
}
