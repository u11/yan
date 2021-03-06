package com.example.contact;

import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.maxwin.view.PullListView;
import me.maxwin.view.PullListView.IXListViewListener;

import com.android.app.App;
import com.android.contact.api.ServerApi;

import com.example.model.User;

import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SListActivity extends ActivityGroup implements IXListViewListener {
	private PullListView listview1;// 仓库列表list
	public Button search_btn;
	public EditText search_editer;
	private ProgressDialog ProgressDialog1; // 加载对话框
	private LayoutInflater mInflater;
	private View mHeaderView;
	private Thread Thread1;
	private List<User> houseList;
	private List<User> list1 = null;// 主页推荐List
	private UserAdapter adapter;
	private String sid;
	private String stitle;
	private ListView areaCheckListView;
	public TextView title_back;
	private TextView right_text;
	Button buttonSearch = null;
	EditText searchtext = null;
	private String cklx = "";
	private int page = 0;
	// private static AppContext app = new AppContext();
	private List<HashMap<String, Object>> houseInfoList = new ArrayList<HashMap<String, Object>>();

	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchlist);
		ProgressDialog1 = new ProgressDialog(SListActivity.this);
		listview1 = (PullListView) findViewById(R.id.ck_list);// 获取订单列表

		listview1.setPullLoadEnable(true);
		search_btn = (Button) findViewById(R.id.search_btn);// 获取订单列表
		search_editer = (EditText) findViewById(R.id.search_editer);// 获取订单列表
		listview1.setXListViewListener(this);

		search_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				threadGetOrderFirst();
			}
		});
		List<User> list = new ArrayList<User>();
		adapter = new UserAdapter(SListActivity.this, list);
		adapter.addlist(list);
		listview1.setAdapter(adapter);
		threadGetOrderFirst();

	}

	private final AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {

		@Override
		public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
			try {
				ProgressDialog1.dismiss();
				String json = new String(arg2);
				List<User> list = new ArrayList<User>();
				JSONObject object = new JSONObject(json);

				JSONArray arr = object.getJSONArray("data");

				for (int i = 0; i < arr.length(); i++) {
					JSONObject obj = arr.getJSONObject(i);
					User user = new User();
					user.setUId(obj.getString("USER_ID"));
					user.setUName(obj.getString("USER_NAME"));
					user.ulname = obj.getString("USER_LNAME");
					user.setUMail(obj.getString("USER_MAIL"));
					user.setUTel(obj.getString("USER_TEL"));
					user.ulx = obj.getString("USER_TYPE");
					list.add(user);
				}
				if (page == 0) {
					adapter.Clearlist();
				}
				adapter.addlist(list);
				adapter.notifyDataSetChanged();
				listview1.stopRefresh();
				listview1.stopLoadMore();
				ProgressDialog1.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
				onFailure(arg0, arg1, arg2, e);
			}
		}

		@Override
		public void onFailure(int arg0, Header[] arg1, byte[] arg2,
				Throwable arg3) {

			// AppContext.showToast(R.string.tip_login_error_for_network);
		}
	};

	/* activity加载后起一个新的线程获取订单数据数据 */
	public void threadGetOrderFirst() {
		try {

			ProgressDialog1.setMessage("数据加载中，请稍后...");
			ProgressDialog1.show();
			String filter = search_editer.getText().toString();
			ServerApi.getUserListS(filter, String.valueOf(page), handler);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 继承接口刷新的事件
	@Override
	public void onRefresh() {
		page = 0;
		threadGetOrderFirst();
	}

	// 继承接口加载更多的事件
	@Override
	public void onLoadMore() {
		page += 1;
		threadGetOrderFirst();
	}

}
