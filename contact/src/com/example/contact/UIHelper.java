package com.example.contact;

import android.content.Context;
import android.content.Intent;

public class UIHelper {

	public static void showHostSetActivity(Context context) {
		Intent intent = new Intent(context, HostSetActivity.class);
		context.startActivity(intent);
	}

	public static void showIndexActivity(Context context) {
		Intent intent = new Intent(context, IndexActivity.class);
		context.startActivity(intent);
	}

	public static void showRegActivity(Context context) {
		Intent intent = new Intent(context, RegActivity.class);
		context.startActivity(intent);
	}

	public static void showContactActivity(Context context) {
		Intent intent = new Intent(context, ContactActivity.class);
		context.startActivity(intent);
	}

}
