package com.mycourse.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycourse.R;
import com.mycourse.ImportCourseActivity;
import com.mycourse.MainActivity;
import com.mycourse.base.BaseFragment;
import com.mycourse.view.HeaderLayout.onLeftImageButtonClickListener;
import com.mycourse.view.HeaderLayout.onRightImageButtonClickListener;

public class CourseTableFragment extends BaseFragment {

		private Context context;
		
		public CourseTableFragment(){
			super();
		}
		public CourseTableFragment(Context context) {
			super();
			this.context = context;
		}
		
		private int colors[] = {
				Color.rgb(0xee,0xff,0xff),
				Color.rgb(0xf0,0x96,0x09),
				Color.rgb(0x8c,0xbf,0x26),
				Color.rgb(0x00,0xab,0xa9),
				Color.rgb(0x99,0x6c,0x33),
				Color.rgb(0x3b,0x92,0xbc),
				Color.rgb(0xd5,0x4d,0x34),
				Color.rgb(0xcc,0xcc,0xcc)
		};
		/*
		 * fragment oncreatview 方法里一定要加载 一个布局
		 * 如果 要 initTopBarForBoth ，布局里要有组件
		 *  <include..../> ，不然会有空指针异常
		 *        		
		 */
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			return inflater.inflate(R.layout.frag_course, container, false);		
		}
		
	
	
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			init();
		}
		
		//初始化界面
		public  void init() {
			
			initTopBarForBoth("课表", R.drawable.base_action_bar_add_bg_selector,
	                      new onRightImageButtonClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							importCourse();
						}
					}, R.drawable.base_action_bar_slide3, new onLeftImageButtonClickListener() {
						
						@Override
						public void onClick() {
							// TODO Auto-generated method stub
							MainActivity.showslide();
						}
					});
		
			initCourse();
		}
		
		public void importCourse() {
			Intent courseiIntent = new Intent(context,ImportCourseActivity.class);
			startActivity(courseiIntent);
		}
		
		public void initCourse(){
			LinearLayout ll1 = (LinearLayout)findViewById(R.id.ll1);
	        LinearLayout ll2 = (LinearLayout)findViewById(R.id.ll2);
	        LinearLayout ll3 = (LinearLayout)findViewById(R.id.ll3);
	        LinearLayout ll4 = (LinearLayout)findViewById(R.id.ll4);
	        LinearLayout ll5 = (LinearLayout)findViewById(R.id.ll5);
	        LinearLayout ll6 = (LinearLayout)findViewById(R.id.ll6);
	        LinearLayout ll7 = (LinearLayout)findViewById(R.id.ll7);
	        
	        //每天的课程设置
	        //周一上
	        setClass(ll1, "", "", "", "", 1, 2);
	        setClass(ll1, "", "", "", "", 1, 0);	        
	        setClass(ll1, "windows编程实践", "国软  4-503", "1-9周，每一周", "9:50-11:25", 2, 1);
	        //下
	        setClass(ll1, "", "", "", "", 1, 0);
	        setClass(ll1, "", "", "", "", 1, 0);
	        setClass(ll1, "", "", "", "", 1, 0);	       
	        setClass(ll1, "概率论与数理统计", "国软  4-304", "1-15周，每一周", "14:55-17:25", 3, 2);
	        
	        setClass(ll1, "空","" ,"","",1, 0);
	        setClass(ll1, "人文化学", "一区 3-404", "3-13周，每一周", "19:00-20:30", 2, 4);
	        setClass(ll1,"空","" ,"","", 1, 0);
	        
	        //周二
	        setClass(ll2, "大学英语", "国软 4-302", "1-18周，每一周", "8:00-9:35", 2, 3);
	        setClass(ll2, "计算机组织体系与结构", "国软 4-204", "1-15，每一周", "9:50-12:15", 3, 5);
	        
	        setClass(ll2, "", "", "", "", 1, 0);
	        setClass(ll2, "", "", "", "", 1, 0);
	        setClass(ll2, "", "", "", "", 1, 0);
	        setClass(ll2, "团队激励和沟通", "国软 4-204", "1-9周，每一周", "15:45-17:25", 2, 6);
	        setClass(ll2,"","" ,"","", 1, 0);
	        setClass(ll2, "中国近现代史纲要", "3区 1-327", "1-9周，每一周", "19:00-21:25", 3, 1);
	        
	        setClass(ll3, "","" ,"","",2, 0);
	        setClass(ll3, "中国近现代史纲要", "3区 1-328", "1-9周，每一周", "9:50-12:15", 3, 1);
	        setClass(ll3, "","" ,"","",1, 0);
	        setClass(ll3, "体育（网球）", "信息学部 操场", "6-18周，每一周", "14:00-15:40", 2, 2);
	        setClass(ll3,"","" ,"","", 3, 0);
	        setClass(ll3, "当代政治与经济", "3区 1-501", "1-7周，每一周", "19:00-21:25", 3, 3);
	        
	        setClass(ll4, "计算机组织体系与结构", "国软 4-204", "1-15，每一周", "8:00-9:35", 2, 5);
	        setClass(ll4, "数据结构与算法", "国软 4-304", "1-18周，每一周", "9:50-12:15", 3, 4);
	        setClass(ll4, "","" ,"","",1, 0);
	        setClass(ll4, "面向对象程序设计（JAVA）", "国软 1-103", "1-18周，每一周", "14:00-16:30", 3, 5);
	        setClass(ll4,"","" ,"","", 2, 0);
	        setClass(ll4,"","" ,"","", 3, 0);
	        
	        setClass(ll5, "c#程序设计", "国软 4-102", "1-9周，每一周", "8:00-9:35", 2, 6);
	        setClass(ll5, "大学英语", "国软 4-302", "1-18周，每一周", "9:50-11:25", 2, 3);
	        setClass(ll5,"","" ,"","", 2, 0);
	        setClass(ll5, "基础物理", "国软 4-304", "1-18周，每一周", "14:00-16:30", 3, 1);
	        setClass(ll5, "","" ,"","",2, 0);
	        setClass(ll5, "手机应用分析与创意", "1区 5-103", "1-7周，每一周", "19:00-21:2", 3, 2);
	        
	        setClass(ll6,"","" ,"","", 14, 0);
	        
	        setClass(ll7,"","" ,"","", 14, 0);
		}
		
		/**
	     * 设置课程的方法
	     * @param ll
	     * @param title 课程名称
	     * @param place 地点
	     * @param last 时间
	     * @param time 周次
	     * @param classes 节数
	     * @param color 背景色
	     */
	    void setClass(LinearLayout ll, String title, String place,
	    		String last, String time, int classes, int color)
	    {
	    	
	    	View view = LayoutInflater.from(context).inflate(R.layout.course_item, null);
	    	int height = classes*50 - 2;
	    	LayoutParams params = new 
	    			LayoutParams(LayoutParams.MATCH_PARENT,dip2px(context,height));
	    	//view.setMinimumHeight(dip2px(this,classes * 48));
	    	
	    	view.setLayoutParams(params);
	    	view.setBackgroundColor(colors[color]);
	    	((TextView)view.findViewById(R.id.title)).setText(title);
	        ((TextView)view.findViewById(R.id.place)).setText(place);
//	        ((TextView)view.findViewById(R.id.last)).setText(last);
//	        ((TextView)view.findViewById(R.id.time)).setText(time);
	        
	        //为课程View设置点击的监听器
	        view.setOnClickListener(new OnClickClassListener());
	        TextView blank1 = new TextView(context);
	        TextView blank2 = new TextView(context);
	        blank1.setHeight(dip2px(context,1));
	        blank2.setHeight(dip2px(context,1));
	        ll.addView(blank1);
	        ll.addView(view); 
	        ll.addView(blank2);
	       
	    }
	   
	    /**
	     * 设置无课（空百）
	     * @param ll
	     * @param classes 无课的节数（长度）
	     * @param color
	     */
	    void setNoClass(LinearLayout ll,int classes, int color)
	    {
	    	TextView blank = new TextView(context);
	    	if(color == 0)
	    		blank.setMinHeight(dip2px(context,classes * 50));
	    	blank.setBackgroundColor(colors[color]);
	    	ll.addView(blank);
	    }
	   
	    //点击课程的监听器
	    class OnClickClassListener implements OnClickListener{
	    	
	    	public void onClick(View v) {
	    		// TODO Auto-generated method stub
	    		String title;
	    		title = (String) ((TextView)v.findViewById(R.id.title)).getText();
	    		Toast.makeText(context, "你点击的是:" + title, 
	    				Toast.LENGTH_SHORT).show();
	    	}
		}
	    
	    public static int dip2px(Context context, float dpValue) {        
	    	final float scale = context.getResources().getDisplayMetrics().density;        
	    	return (int) (dpValue * scale + 0.5f);} /** * 根据手机的分辨率从 px(像素) 的单位 转成为 dp */
	  
	    public static int px2dip(Context context, float pxValue) {        
	    	final float scale = context.getResources().getDisplayMetrics().density;        
	    	return (int) (pxValue / scale + 0.5f);
	    }
}
