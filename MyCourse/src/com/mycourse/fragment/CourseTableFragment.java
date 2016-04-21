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
		 * fragment oncreatview ������һ��Ҫ���� һ������
		 * ��� Ҫ initTopBarForBoth ��������Ҫ�����
		 *  <include..../> ����Ȼ���п�ָ���쳣
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
		
		//��ʼ������
		public  void init() {
			
			initTopBarForBoth("�α�", R.drawable.base_action_bar_add_bg_selector,
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
	        
	        //ÿ��Ŀγ�����
	        //��һ��
	        setClass(ll1, "", "", "", "", 1, 2);
	        setClass(ll1, "", "", "", "", 1, 0);	        
	        setClass(ll1, "windows���ʵ��", "����  4-503", "1-9�ܣ�ÿһ��", "9:50-11:25", 2, 1);
	        //��
	        setClass(ll1, "", "", "", "", 1, 0);
	        setClass(ll1, "", "", "", "", 1, 0);
	        setClass(ll1, "", "", "", "", 1, 0);	       
	        setClass(ll1, "������������ͳ��", "����  4-304", "1-15�ܣ�ÿһ��", "14:55-17:25", 3, 2);
	        
	        setClass(ll1, "��","" ,"","",1, 0);
	        setClass(ll1, "���Ļ�ѧ", "һ�� 3-404", "3-13�ܣ�ÿһ��", "19:00-20:30", 2, 4);
	        setClass(ll1,"��","" ,"","", 1, 0);
	        
	        //�ܶ�
	        setClass(ll2, "��ѧӢ��", "���� 4-302", "1-18�ܣ�ÿһ��", "8:00-9:35", 2, 3);
	        setClass(ll2, "�������֯��ϵ��ṹ", "���� 4-204", "1-15��ÿһ��", "9:50-12:15", 3, 5);
	        
	        setClass(ll2, "", "", "", "", 1, 0);
	        setClass(ll2, "", "", "", "", 1, 0);
	        setClass(ll2, "", "", "", "", 1, 0);
	        setClass(ll2, "�ŶӼ����͹�ͨ", "���� 4-204", "1-9�ܣ�ÿһ��", "15:45-17:25", 2, 6);
	        setClass(ll2,"","" ,"","", 1, 0);
	        setClass(ll2, "�й����ִ�ʷ��Ҫ", "3�� 1-327", "1-9�ܣ�ÿһ��", "19:00-21:25", 3, 1);
	        
	        setClass(ll3, "","" ,"","",2, 0);
	        setClass(ll3, "�й����ִ�ʷ��Ҫ", "3�� 1-328", "1-9�ܣ�ÿһ��", "9:50-12:15", 3, 1);
	        setClass(ll3, "","" ,"","",1, 0);
	        setClass(ll3, "����������", "��Ϣѧ�� �ٳ�", "6-18�ܣ�ÿһ��", "14:00-15:40", 2, 2);
	        setClass(ll3,"","" ,"","", 3, 0);
	        setClass(ll3, "���������뾭��", "3�� 1-501", "1-7�ܣ�ÿһ��", "19:00-21:25", 3, 3);
	        
	        setClass(ll4, "�������֯��ϵ��ṹ", "���� 4-204", "1-15��ÿһ��", "8:00-9:35", 2, 5);
	        setClass(ll4, "���ݽṹ���㷨", "���� 4-304", "1-18�ܣ�ÿһ��", "9:50-12:15", 3, 4);
	        setClass(ll4, "","" ,"","",1, 0);
	        setClass(ll4, "������������ƣ�JAVA��", "���� 1-103", "1-18�ܣ�ÿһ��", "14:00-16:30", 3, 5);
	        setClass(ll4,"","" ,"","", 2, 0);
	        setClass(ll4,"","" ,"","", 3, 0);
	        
	        setClass(ll5, "c#�������", "���� 4-102", "1-9�ܣ�ÿһ��", "8:00-9:35", 2, 6);
	        setClass(ll5, "��ѧӢ��", "���� 4-302", "1-18�ܣ�ÿһ��", "9:50-11:25", 2, 3);
	        setClass(ll5,"","" ,"","", 2, 0);
	        setClass(ll5, "��������", "���� 4-304", "1-18�ܣ�ÿһ��", "14:00-16:30", 3, 1);
	        setClass(ll5, "","" ,"","",2, 0);
	        setClass(ll5, "�ֻ�Ӧ�÷����봴��", "1�� 5-103", "1-7�ܣ�ÿһ��", "19:00-21:2", 3, 2);
	        
	        setClass(ll6,"","" ,"","", 14, 0);
	        
	        setClass(ll7,"","" ,"","", 14, 0);
		}
		
		/**
	     * ���ÿγ̵ķ���
	     * @param ll
	     * @param title �γ�����
	     * @param place �ص�
	     * @param last ʱ��
	     * @param time �ܴ�
	     * @param classes ����
	     * @param color ����ɫ
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
	        
	        //Ϊ�γ�View���õ���ļ�����
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
	     * �����޿Σ��հ٣�
	     * @param ll
	     * @param classes �޿εĽ��������ȣ�
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
	   
	    //����γ̵ļ�����
	    class OnClickClassListener implements OnClickListener{
	    	
	    	public void onClick(View v) {
	    		// TODO Auto-generated method stub
	    		String title;
	    		title = (String) ((TextView)v.findViewById(R.id.title)).getText();
	    		Toast.makeText(context, "��������:" + title, 
	    				Toast.LENGTH_SHORT).show();
	    	}
		}
	    
	    public static int dip2px(Context context, float dpValue) {        
	    	final float scale = context.getResources().getDisplayMetrics().density;        
	    	return (int) (dpValue * scale + 0.5f);} /** * �����ֻ��ķֱ��ʴ� px(����) �ĵ�λ ת��Ϊ dp */
	  
	    public static int px2dip(Context context, float pxValue) {        
	    	final float scale = context.getResources().getDisplayMetrics().density;        
	    	return (int) (pxValue / scale + 0.5f);
	    }
}
