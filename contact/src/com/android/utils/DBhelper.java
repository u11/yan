package com.android.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBhelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME_STRING = "datastorage";
	private static final int DATABASE_VERSION = 4;

	public DBhelper(Context context) {
		super(context, DATABASE_NAME_STRING, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE [dt_cart] ("
				+ "[id] INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL,"
				+ "[spid] 	VARCHAR(500)  NULL," 
				+ "[skuid] 		VARCHAR(500)  NULL,"
				+ "[skuname] 		VARCHAR(500)  NULL,"
				+ "[count] 		VARCHAR(500)  NULL"
				+ ")");
//		db.execSQL("insert into [dt_cart] (id,spid,skuid,count) "
//				+ "values('001','334402b9-43a3-4133-9873-3b0806ba6ab1','001','2')");
//		db.execSQL("insert into [dt_cost] (lb,time,year,month,bz,je) "
//				+ "values('信用卡','2015-03-04','2015','03','刷卡买羽绒服','368.00')");
//		db.execSQL("insert into [dt_cost] (lb,time,year,month,bz,je) "
//				+ "values('信用卡','2015-03-10','2015','03','刷卡买羽绒服','368.00')");

		System.out.println("调用onCreate初始据库");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists [dt_cart]");
		onCreate(db);
		System.out.println("调用onUpgrade 升级数据库版本：");
	}
}
