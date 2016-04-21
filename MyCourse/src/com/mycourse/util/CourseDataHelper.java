package com.mycourse.util;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CourseDataHelper extends SQLiteOpenHelper {

	private static final int VERSION = 1;	
	
	public  CourseDataHelper(Context context, String name) {
		this(context, name, null, VERSION);
		
	}
	
	public  CourseDataHelper(Context context, String name,int version) {
		this(context, name, null, version);
		
	}
	
	public CourseDataHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public CourseDataHelper(Context context, String name,
			CursorFactory factory, int version,
			DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL
		("create table course(coursename varchar(20) not null,adress varchar(20) not null,"
				+ " week int not null,Period int not null,start int not null)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
