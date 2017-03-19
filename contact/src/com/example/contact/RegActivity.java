package com.example.contact;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.TextView;

import com.android.contact.api.ServerApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class RegActivity extends FragmentActivity implements OnClickListener {

	public ImageView imageUser;
	ImageButton btn_back;
	public RelativeLayout btnreg;

	public TextView title_back;
	public EditText txt_userFname;
	public EditText txt_userLname;
	public EditText txt_userEmail;
	public EditText txt_userPwd;
	public EditText txt_userPwd_again;

	private ProgressDialog ProgressDialog1; // 加载对话框

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		title_back = (TextView) findViewById(R.id.title_back);

		title_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RegActivity.this.finish();
			}
		});

		txt_userFname = (EditText) findViewById(R.id.txt_userFname);
		txt_userLname = (EditText) findViewById(R.id.txt_userLname);
		txt_userEmail = (EditText) findViewById(R.id.txt_userEmail);
		txt_userPwd = (EditText) findViewById(R.id.txt_userPwd);
		txt_userPwd_again = (EditText) findViewById(R.id.txt_userPwd_again);
		btnreg = (RelativeLayout) findViewById(R.id.btnreg);
		btnreg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// showWaitDialog("正在提交注册信息,请稍后....");

				String userName = txt_userFname.getText().toString();
				String lname = txt_userLname.getText().toString();
				String email = txt_userEmail.getText().toString();
				String userPwd = txt_userPwd.getText().toString();
				String userPwdAgain = txt_userPwd_again.getText().toString();
				if (userName.equals("")) {
					Toast.makeText(RegActivity.this, "请输入用户账号！", 3).show();
					return;
				}
				if (userPwd.equals("")) {
					Toast.makeText(RegActivity.this, "请输入用户密码！", 3).show();
					return;
				}
				if (userPwdAgain.equals("")) {
					Toast.makeText(RegActivity.this, "请再次确认密码！", 3).show();
					return;
				}
				if (!userPwdAgain.equals(userPwd)) {
					Toast.makeText(RegActivity.this, "两次输入密码不一致！", 3).show();
					return;
				}
				ProgressDialog1 = new ProgressDialog(RegActivity.this);
				ProgressDialog1.setMessage("数据加载中，请稍后...");
				ProgressDialog1.show();
				try {
					ServerApi.addUser(userName, lname, email, userPwd,
							userPwdAgain, mHandler);
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

				if (obj.getBoolean("isok")) {
					Toast.makeText(RegActivity.this, "注册成功！", 3).show();
					// RegActivity.this.finish();
				} else {
					String mes = obj.getString("data");
					Toast.makeText(RegActivity.this, mes, 3).show();
				}
			} catch (Exception e) {
				e.printStackTrace();
				onFailure(arg0, arg1, arg2, e);
			}
			ProgressDialog1.dismiss();
		}

		@Override
		public void onFailure(int arg0, Header[] arg1, byte[] arg2,
				Throwable arg3) {
			// TODO Auto-generated method stub
			// Toast.makeText(RegActivity.this, mes, 3).show();
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
