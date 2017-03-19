package com.example.contact;

 

import com.android.app.App;
import com.android.utils.StringUtils;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class HostSetActivity extends FragmentActivity {

	public RelativeLayout userassistant;
	public LinearLayout ll_spgg;
	public RelativeLayout btnsavehost;
	public static LinearLayout all_choice_layout;

	public EditText txthost;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sethost);
		btnsavehost = (RelativeLayout) findViewById(R.id.btnsavehost);

		txthost = (EditText) findViewById(R.id.txthost);
		txthost.setText(App.getApp().getHostIP());
		btnsavehost.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if (StringUtils.isEmpty(txthost.getText().toString())) {
					Toast.makeText(HostSetActivity.this, "请输入有效的IP地址!", 3).show();
					// App.showToast("请输入有效的IP地址！", HostSetActivity.this);
				} else {
					App.getApp().setHostIP(txthost.getText().toString());
					Toast.makeText(HostSetActivity.this, "保存成功！", 3).show();
					// App.showToast("保存成功！", HostSetActivity.this);
					HostSetActivity.this.finish();
				}
			}
		});

		// popWindow.setOnItemClickListener(this);

	}

}
