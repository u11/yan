package com.example.contact;
 

import com.example.contact.R;
import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;

public class IndexActivity extends ActivityGroup {
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_index);
		super.onCreate(savedInstanceState);
		// if (Config.getIns().getServerUID().equals("")) {
		//
		// Intent search = new Intent(IndexActivity.this,
		// UserInfoActivity.class);
		// IndexActivity.this.finish();
		// startActivity(search);
		// }
		final TabHost tabhost = (TabHost) findViewById(android.R.id.tabhost);// 获取TabHost
		tabhost.setup(this.getLocalActivityManager());// 启动TabHost
		TabWidget tabwidget = tabhost.getTabWidget();
		LayoutInflater inflater = LayoutInflater.from(IndexActivity.this);

		View mainTabItem = inflater.inflate(R.layout.tabitem, null);
		TabHost.TabSpec nearbyTabSpec = tabhost.newTabSpec("main")
				.setIndicator(mainTabItem);
		tabhost.addTab(nearbyTabSpec.setContent(new Intent(IndexActivity.this,
				ContactListActivity.class)));

		View mainTabItem1 = inflater.inflate(R.layout.tabitecp, null);
		TabHost.TabSpec nearbyTabSpec1 = tabhost.newTabSpec("main1")
				.setIndicator(mainTabItem1);

		Intent search = new Intent(IndexActivity.this, UserListActivity.class);

		tabhost.addTab(nearbyTabSpec1.setContent(search));

		View mainTabItem2 = inflater.inflate(R.layout.tabitemuser, null);
		TabHost.TabSpec nearbyTabSpec2 = tabhost.newTabSpec("main2")
				.setIndicator(mainTabItem2);

		tabhost.addTab(nearbyTabSpec2.setContent(new Intent(IndexActivity.this,
				SListActivity.class)));
 
	}

	long waitTime = 2000;
	long touchTime = 0;

	@Override
	public void onBackPressed() {
		long currentTime = System.currentTimeMillis();
		if ((currentTime - touchTime) >= waitTime) {
			Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
			touchTime = currentTime;
		} else {
			finish();
		}
	}
}
