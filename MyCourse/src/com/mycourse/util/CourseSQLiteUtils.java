package com.mycourse.util;

import com.mycourse.data.CourseInfo;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CourseSQLiteUtils {

	public static final String DATABASE_NAME = "wanghang_course_db";

	public static CourseDataHelper createCourseDataHelper(Context context) {
		//����һ��DatabaseHelper����
		CourseDataHelper dbHelper = new CourseDataHelper(context,"wanghang_course_db");
		return dbHelper;
	}
	
	//��ӿγ�
	public void insert(CourseDataHelper dbHelper,CourseInfo courseInfo)
	{
		//����ContentValues����
		ContentValues values = new ContentValues();
		//��ö����в����ֵ�ԣ����м���������ֵ��ϣ�����뵽��һ�е�ֵ��ֵ��������ݿ⵱�е���������һ��
		values.put("coursename", courseInfo.getName());
		values.put("adress", courseInfo.getAdress());
		values.put("week", courseInfo.getWeek());
		values.put("Period", courseInfo.getPeriod());
		values.put("start", courseInfo.getstart());
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.insert("course", null, values);
		db.close();
	}
	
	//ɾ���γ�
	public void delete(CourseDataHelper dbHelper,String coursename)
	{
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		db.execSQL("DELETE FROM " + "course" +"WHERE coursename="+coursename);
		db.close();
	}
	
	//ɾ�����пγ�
	public void deleteall(CourseDataHelper dbHelper)
	{
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		db.delete("course",null,null);
	}
}
