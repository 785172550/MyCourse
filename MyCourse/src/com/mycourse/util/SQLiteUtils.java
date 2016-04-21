package com.mycourse.util;

import com.mycourse.data.NoteUserInfo;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteUtils {

	public static final String DATABASE_NAME = "zhaochen_memorandum_db";

	public static final String DATETIME = "datetime";
    public static final String CONTENT = "content";
	
		public static DatabaseHelper createDBHelper(Context context) {
			//创建一个DatabaseHelper对象
			DatabaseHelper dbHelper = new DatabaseHelper(context,DATABASE_NAME);
			return dbHelper;
		}

		public void insert(DatabaseHelper dbHelper,NoteUserInfo user) {
			//生成ContentValues对象
			ContentValues values = new ContentValues();
			//想该对象当中插入键值对，其中键是列名，值是希望插入到这一列的值，值必须和数据库当中的数据类型一致
			values.put("datetime", user.getDatetime());
			values.put("content",user.getContent());
			values.put("alerttime",user.getAlerttime());
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			//调用insert方法，就可以将数据插入到数据库当中
			db.insert("user", null, values);
			db.close();
		}
    
		
		//更新操作就相当于执行SQL语句当中的update语句
		//UPDATE table_name SET XXCOL=XXX WHERE XXCOL=XX...
		public void update(DatabaseHelper dbHelper) {
			
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("content", "zhaochen");
			//第一个参数是要更新的表名
			//第二个参数是一个ContentValeus对象
			//第三个参数是where子句
			db.update("user", values, "id=?", new String[]{"1"});
			db.close();
		}

		public void delete(DatabaseHelper dbHelper,String datetime){
			
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			// 删除表的所有数据
			// db.delete("users",null,null);
			// 从表中删除指定的一条数据
			db.execSQL("DELETE FROM " + "user" + " WHERE datetime="+ datetime);
			db.close();
		}

}
