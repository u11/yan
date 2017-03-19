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
	 * 登陆
	 * 
	 * @param username
	 * @param password
	 * @param handler
	 * @throws JSONException
	 */
	public static void login(String username, String password,
			AsyncHttpResponseHandler handler) throws JSONException {
		RequestParams params = new RequestParams();
		params.put("op", "CheckUser");// 设备ID
		params.put("name", username);// 设备ID
		params.put("pwd", password);// 设备ID
		String loginurl = "contact/android/login.ashx";
		ApiHttpClient.post(loginurl, params, handler);
	}

	public static void addUser(String fname, String lname, String mail,
			String password, String cpwd, AsyncHttpResponseHandler handler)
			throws JSONException {
		RequestParams params = new RequestParams();

		params.put("uid", App.getApp().getLoginUser().getUId());// 设备ID
		params.put("type", "1");// 设备ID
		params.put("fname", fname);// 设备ID
		params.put("lname", lname);// 设备ID
		params.put("email", mail);// 设备ID
		params.put("pwd", password);// 设备ID
		params.put("cpwd", cpwd);// 设备ID

		String loginurl = "contact/android/adduser.ashx?type=1";
		ApiHttpClient.post(loginurl, params, handler);
	}

	public static void addContact(String fname, String lname, String mail,
			String dw, String add, String tel, String birth,
			AsyncHttpResponseHandler handler) throws JSONException {
		RequestParams params = new RequestParams();

		params.put("uid", App.getApp().getLoginUser().getUId());// 设备ID
		params.put("type", "0");// 设备ID
		params.put("fname", fname);// 设备ID
		params.put("lname", lname);// 设备ID
		params.put("email", mail);// 设备ID
		params.put("tel", tel);// 设备ID
		params.put("birth", birth);// 设备ID
		params.put("add", add);// 设备ID
		params.put("dw", dw);// 设备ID

		String loginurl = "contact/android/adduser.ashx?type=0";
		ApiHttpClient.post(loginurl, params, handler);
	}

	public static void Ts(String usercode, String title, String content,
			String xmmc, String xmbh, AsyncHttpResponseHandler handler)
			throws JSONException {
		RequestParams params = new RequestParams();
		params.put("op", "InsertTSData");// 设备ID
		params.put("title", title);// 设备ID
		params.put("content", content);// 设备ID
		params.put("xmmc", xmmc);// 设备ID
		params.put("xmbh", xmbh);// 设备ID
		params.put("usercode", usercode);// 设备ID
		String loginurl = "bid_web/AppWebservice/BidWebService.ashx";

		// http://dev.dachkj.com:50111/bid_web/AppWebservice/BidWebService.ashx?op=InsertTSData&usercode=TJHDGS003&title
		ApiHttpClient.get(loginurl, params, handler);
	}

	public static void getZBXQ(String id, AsyncHttpResponseHandler handler)
			throws JSONException {
		RequestParams params = new RequestParams();
		params.put("op", "getOnTenderNoticeInfo");// 设备ID
		params.put("noticeID", id);// 设备ID
		String loginurl = "bid_web/AppWebservice/BidWebService.ashx";
		ApiHttpClient.get(loginurl, params, handler);
	}

	public static void getTenderInfo(String cid,
			AsyncHttpResponseHandler handler) throws JSONException {
		RequestParams params = new RequestParams();
		params.put("op", "getTenderNoticeInfo");// 设备ID
		params.put("noticeID", cid);

		String loginurl = "bid_web/AppWebservice/BidWebService.ashx";
		ApiHttpClient.get(loginurl, params, handler);

		/*
		 * {"isOK":"true","total":1,"rows":[{"cid":
		 * "b662e639-fb63-4489-a605-a6c6012b10eb"
		 * ,"c_user_cid":"258745c8-83f1-4c77-8cf9-a6ab00a1009d"
		 * ,"c_user_code":"TJrongchuangcheng"
		 * ,"c_user_name":"天津融创城","c_date":"2016/11/22 18:08:51"
		 * ,"u_user_cid":"",
		 * "u_user_code":"","u_user_name":"","u_date":"","info_status"
		 * :"2","n_title"
		 * :"TestMessage","n_cid":"","n_type":"","n_pub_date":"","n_pub_cid"
		 * :"","n_pub_code"
		 * :"","n_pub_name":"","n_is_remind":"是","n_is_stick":"是"
		 * ,"n_sort":"9","n_newtime":"","n_stt":"是","n_profile":"","n_content":
		 * "项目名称：TestMessage，报价开始时间：2016-11-22 18:07，开标时间：2016-11-23 18:07，招标物料：物料编码：TJHD-001，物料名称：劳斯伯格棚，规格型号：8成新以上,，单位：平米，数量：100.000|"
		 * ,
		 * "n_keywords":"","n_click":"0","n_status":"发布","n_attach":"","companyType"
		 * :"活动类","deliveryArea":"天津"}]}
		 */
	}

	public static void getOnTenderInfo(String cid,
			AsyncHttpResponseHandler handler) throws JSONException {
		RequestParams params = new RequestParams();
		params.put("op", "getOnTenderNoticeInfo");// 设备ID
		params.put("noticeID", cid);

		String loginurl = "bid_web/AppWebservice/BidWebService.ashx";
		ApiHttpClient.get(loginurl, params, handler);
		/*
		 * {"isOK":"true","total":1,"rows":[{"REV_CID":
		 * "85ef4aa7-c3c9-4129-a728-0ca084423198"
		 * ,"XMMC":"20161117演示天津融创城项目","WLNAME"
		 * :"折叠椅","GYSNAME":"天津市集彩印刷有限公司","gyscode"
		 * :"TJYSGS003","ZBJG":"650","SBSL"
		 * :"10","TOTAL":"-","ZSFBTIME":"2016/11/17 15:09:00"
		 * ,"ENDTIME":"2016/11/17 15:13:10"
		 * ,"FKFS":"","FKTJ":"","CHOOSEJHQ":"","Price"
		 * :"89.00","lowPrice":"6700.00"
		 * ,"WLCODE":"TJHD-005","NAME":"天津市集彩印刷有限公司"}]}
		 */
	}

	public static void getNoticeInfo(String cid,
			AsyncHttpResponseHandler handler) throws JSONException {
		RequestParams params = new RequestParams();
		params.put("op", "getCompanyNoticeInfo");// 设备ID
		params.put("CID", cid);

		String loginurl = "bid_web/AppWebservice/BidWebService.ashx";
		ApiHttpClient.get(loginurl, params, handler);
		/*
		 * {"isOK":"true","total":1,"rows":[{"cid":
		 * "19f75c6b-c680-475b-9db2-a6c000fbf862"
		 * ,"N_PUB_NAME":"系统管理员","N_TITLE":
		 * "标题","N_PUB_DATE":"2016/11/16 15:17:00"
		 * ,"N_PROFILE":"简介","N_CONTENT":"<p> 详细内容
		 * </p>","N_KEYWORDS":"关键字","DA_SERVERFILE
		 * ":"/Files/notice/826d09af-d33e-4516-ac60-a6c1012a5deb供应商导入模板.xlsx"}]}
		 */
	}

	public static void getUserList(String type, String page,
			AsyncHttpResponseHandler handler) throws JSONException {
		RequestParams params = new RequestParams();
		params.put("type", type);// 设备ID
		params.put("page", page);// 设备ID
		params.put("uid", App.getApp().getLoginUser().getUId());

		String loginurl = "contact/android/user.ashx";
		ApiHttpClient.get(loginurl, params, handler);

	}
	public static void getUserListS(String Conditon, String page,
			AsyncHttpResponseHandler handler) throws JSONException {
		RequestParams params = new RequestParams();
		params.put("f", Conditon);// 设备ID
		params.put("page", page);// 设备ID
		params.put("uid", App.getApp().getLoginUser().getUId());

		String loginurl = "contact/android/users.ashx";
		ApiHttpClient.get(loginurl, params, handler);

	}
}
