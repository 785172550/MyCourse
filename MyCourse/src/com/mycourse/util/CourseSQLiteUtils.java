package com.mycourse.util;

import com.mycourse.data.CourseInfo;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CourseSQLiteUtils {

	public static final String DATABASE_NAME = "wanghang_course_db";

	public static CourseDataHelper createCourseDataHelper(Context context) {
		//创建一个DatabaseHelper对象
		CourseDataHelper dbHelper = new CourseDataHelper(context,"wanghang_course_db");
		return dbHelper;
	}
	
	//添加课程
	public void insert(CourseDataHelper dbHelper,CourseInfo courseInfo)
	{
		//生成ContentValues对象
		ContentValues values = new ContentValues();
		//想该对象当中插入键值对，其中键是列名，值是希望插入到这一列的值，值必须和数据库当中的数据类型一致
		values.put("coursename", courseInfo.getName());
		values.put("adress", courseInfo.getAdress());
		values.put("week", courseInfo.getWeek());
		values.put("Period", courseInfo.getPeriod());
		values.put("start", courseInfo.getstart());
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.insert("course", null, values);
		db.close();
	}
	
	//删除课程
	public void delete(CourseDataHelper dbHelper,String coursename)
	{
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		db.execSQL("DELETE FROM " + "course" +"WHERE coursename="+coursename);
		db.close();
	}
	
	//删除所有课程
	public void deleteall(CourseDataHelper dbHelper)
	{
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		db.delete("course",null,null);
	}
}
