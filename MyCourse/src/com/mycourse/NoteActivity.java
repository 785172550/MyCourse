package com.mycourse;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.mycourse.R;
import com.mycourse.ui.EditActivity;
import com.mycourse.util.DatabaseHelper;
import com.mycourse.util.NoteUtils;
import com.mycourse.util.SQLiteUtils;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class NoteActivity  extends ListActivity implements SearchView.OnQueryTextListener {

	SimpleAdapter listAdapter;
	int index = 0;// 长按指定数据的索引
	PopupWindow mPopupWindow = null;
	ArrayList<HashMap<String,String>> showlist,list = NoteUtils.getList();
	DatabaseHelper dbHelper = new DatabaseHelper(NoteActivity.this, "zhaochen_memorandum_db");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
		findViewById(R.id.action_settings);
		SearchView searchview = (SearchView)findViewById(R.id.searchView);
		searchview.setOnQueryTextListener(this);
		//初始化载入数据库的数据
		list = NoteUtils.getList();
		if(list.isEmpty())   
			loadFromDatabase(list);      //先检查缓存，若没有数据再从数据库加载
		
		NoteUtils.MillisToDate(list);
		listAdapter = new SimpleAdapter(this,list,R.layout.list_item,new String[]{"datetime","content"},
				new int[]{R.id.datetime,R.id.content});
		setListAdapter(listAdapter);                      //将备忘录数据显示出来
		Button button = (Button)findViewById(R.id.createButton);
		button.setOnClickListener(new ClickListener());
		getListView().setOnItemClickListener(new ListItemClickListener());
		getListView().setOnItemLongClickListener( new ItemLongClickListener());
	}
	
	//button clicklistener
	class ClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			NoteUtils.DateToMillis(list);
			Intent intent = new Intent(NoteActivity.this, EditActivity.class);
			Bundle b = new Bundle();
			b.putString("datetime", "");
			b.putString("content", "");
			b.putString("alerttime","");
			intent.putExtra("android.intent.extra.INTENT", b);
			startActivity(intent);                                //启动转到的Activity
		}
	}
	// 列表项长按
	class ItemLongClickListener implements OnItemLongClickListener{

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			index = position; 
			
			View popupView = getLayoutInflater().inflate(R.layout.popupwindow,null);
			mPopupWindow = new PopupWindow(popupView,LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
			mPopupWindow.setAnimationStyle(R.style.popupAnimation);
			mPopupWindow.setFocusable(true);
			mPopupWindow.setBackgroundDrawable(new BitmapDrawable());//这里必须设置这句，使得touch弹窗以外的地方或者按返回键才会消失而且Drawable不能用null代替
			mPopupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
			
			Button deleteButton = (Button)popupView.findViewById(R.id.deleteButton);
			Button shareButton = (Button)popupView.findViewById(R.id.shareButton);
			
			deleteButton.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					deleteItem(index);
					mPopupWindow.dismiss();
				}
			});
			shareButton.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					shareItem(index);
					mPopupWindow.dismiss();
				}
			});
			return true;
		}
	}
	// 列表项点击
	class ListItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent itemintent = new Intent(NoteActivity.this,EditActivity.class);
			NoteUtils.DateToMillis(list);
			Bundle b = new Bundle();
			b.putString("datetime", NoteUtils.getItem(position).get("datetime"));
			b.putString("content", NoteUtils.getItem(position).get("content"));
			b.putString("alerttime", NoteUtils.getItem(position).get("alerttime"));
			b.putInt("index", position);
			itemintent.putExtra("android.intent.extra.INTENT", b);
			startActivity(itemintent);                                //启动转到的Activity
		}
	}
	
	//此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。返回true则显示该menu,false 则不显示;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
		return false;
	}
	
	private void loadFromDatabase(ArrayList<HashMap<String,String>> list){
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("user", new String[] { "datetime", "content","alerttime" }, null,
				null, null, null,"datetime desc");
		while (cursor.moveToNext()) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToPosition(i);
				String datetime = cursor.getString(0);
				String content = cursor.getString(1);
				String alerttime = cursor.getString(2);
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("datetime", datetime);
				map.put("content", content);
				map.put("alerttime", alerttime);
				list.add(map);
			}
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return true;
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		NoteUtils.DateToMillis(list);
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		NoteUtils.sort();
		NoteUtils.MillisToDate(list);
		
		getListView().setOnItemClickListener(new ListItemClickListener());
		listAdapter = new SimpleAdapter(this,list,R.layout.list_item,new String[]{"datetime","content"},
				new int[]{R.id.datetime,R.id.content});
		setListAdapter(listAdapter);
 //       listAdapter.notifyDataSetChanged();                           //更新ListView的数据显示
//        Utils.DateToMillis();
	}
	@Override
	public void onBackPressed() {
			
		    NoteUtils.DateToMillis(list);
			this.finish();
			super.onBackPressed();
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		list = NoteUtils.getList();
		if(newText != null){
			showlist = new ArrayList<HashMap<String,String>>();
			for(int i=0;i<list.size();i++){
				String content = list.get(i).get("content");
				if(content.contains(newText)){
					HashMap<String,String> map = list.get(i);
					map.put("id", String.valueOf(i));
					showlist.add(map);
				}
			}
//			listAdapter.notifyDataSetChanged();
			listAdapter = new SimpleAdapter(this,showlist,R.layout.list_item,new String[]{"datetime","content"},
					new int[]{R.id.datetime,R.id.content});
			setListAdapter(listAdapter);
			getListView().setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent searchintent = new Intent(NoteActivity.this,EditActivity.class);
					NoteUtils.DateToMillis(list);
					Bundle b = new Bundle();
					b.putString("datetime", showlist.get(position).get("datetime"));
					b.putString("content", showlist.get(position).get("content"));
					b.putString("alerttime",showlist.get(position).get("alerttime"));
					b.putInt("index", Integer.parseInt(showlist.get(position).get("id")));
					searchintent.putExtra("android.intent.extra.INTENT", b);
					startActivity(searchintent);                                //启动转到的Activity
				}
			});
		}
		return false;
	}
	
	private boolean deleteItem(int position){
		NoteUtils.DateToMillis(list);
		ListView listview = getListView();
		String deleteDatetime = ((HashMap<String, String>)(listview.getItemAtPosition(index))).get("datetime").toString();
		NoteUtils.getList().remove(index);
		
//		String deleteContent = ((HashMap)(list.getItemAtPosition(index))).get("content").toString();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		SQLiteUtils sqlite = new SQLiteUtils();
		sqlite.delete(dbHelper, deleteDatetime);
		 
		NoteUtils.sort();
		NoteUtils.MillisToDate(list);
        listAdapter.notifyDataSetChanged();                           //更新ListView的数据显示
        return true;
	}

	private void shareItem(int index) {
		Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "hi,我是zhaochen，来自备忘录分享："+NoteUtils.getItem(index).get("content"));
        startActivity(Intent.createChooser(intent, "分享到"));
	}

}
