package com.example.contact;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.app.App;
import com.android.contact.api.ServerApi;
import com.example.contact.R;
import com.example.model.User;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

public class LoginActivity extends FragmentActivity implements OnClickListener {
	ImageButton btn_back;
	public Button btnlogin;
	public EditText userAccount;
	public EditText userPwd;
	private ProgressDialog ProgressDialog1; // 加载对话框
	public TextView zhuce;
	public TextView right_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		right_text = (TextView) findViewById(R.id.right_text);
		zhuce = (TextView) findViewById(R.id.zhuce);
		right_text.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UIHelper.showHostSetActivity(LoginActivity.this);
			}
		});
		zhuce.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UIHelper.showRegActivity(LoginActivity.this);
			}
		});
		// btn_back.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// LoginActivity.this.finish();
		// }
		// });

		if (App.getApp().islogin()) {
			UIHelper.showIndexActivity(LoginActivity.this);

		}

		btnlogin = (Button) findViewById(R.id.login_bt);
		userAccount = (EditText) findViewById(R.id.txtusername);
		userPwd = (EditText) findViewById(R.id.txtpassword);
		btnlogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ProgressDialog1 = new ProgressDialog(LoginActivity.this);
				ProgressDialog1.setMessage("正在登录，请稍后...");
				ProgressDialog1.show();
				try {
					ServerApi.login(userAccount.getText().toString(), userPwd
							.getText().toString(), mHandler);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {

		@Override
		public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
			try {
				String json = new String(arg2);

				JSONObject obj = new JSONObject(json);
				String isok = obj.getString("res");
				if (isok.equals("ok")) {
					Toast.makeText(LoginActivity.this, "登录成功！", 3).show();
					// App.showToast("成功！", LoginActivity.this);
					User user = new User();

					user.setUId(obj.getString("uid"));

					user.setUTel(userAccount.getText().toString());

					App.getApp().updateLoginInfo(user);

					user = App.getApp().getLoginUser();

					UIHelper.showIndexActivity(LoginActivity.this);

				} else {
					String mes = "";
					mes = "用户名或密码错误!";
					Toast.makeText(LoginActivity.this, mes, 3).show();

				}
				ProgressDialog1.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
				onFailure(arg0, arg1, arg2, e);
			}
		}

		@Override
		public void onFailure(int arg0, Header[] arg1, byte[] arg2,
				Throwable arg3) {
			ProgressDialog1.dismiss();
			// AppContext.showToast(R.string.tip_login_error_for_network);
		}
	};

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	public void onClick(View v) {

	}
}
