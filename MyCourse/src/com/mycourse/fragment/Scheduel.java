package com.mycourse.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.mycourse.R;
import com.mycourse.ImportCourseActivity;
import com.mycourse.MainActivity;
import com.mycourse.base.BaseFragment;
import com.mycourse.data.CourseInfo;
import com.mycourse.util.CourseDataHelper;
import com.mycourse.view.HeaderLayout.onLeftImageButtonClickListener;
import com.mycourse.view.HeaderLayout.onRightImageButtonClickListener;

public class Scheduel extends BaseFragment {

	private Context context;
	public Scheduel() {
		// TODO Auto-generated constructor stub
	}
	public Scheduel(Context context)
	{
		super();
		this.context = context;		
	}
	//��ɫ
	int colors[] = {
			R.drawable.course_back1,
			R.drawable.course_back2,
			R.drawable.course_back3,
			R.drawable.course_back4,
	};
	
	 /**�γ̱���*/
    private RelativeLayout r_layout;
    /**��Ļ���*/
    int width;
    /**��Ļ�߶�*/
    int height;
    /**����ÿ��γ̵Ľ���*/
    int numOfCour = 13;
    // �γ����ݿ�
    CourseDataHelper dbHelper;
    
    ArrayList<CourseInfo> courseInfos;
    
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.frag_scheduel, container, false);		
	}
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onActivityCreated(savedInstanceState);
    	initview();
    }
    
    public void initview() {
    	
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
    	
    	dbHelper = new CourseDataHelper(context,"wanghang_course_db");
    	courseInfos = new ArrayList<CourseInfo>();
    	//��ʼ���α����
    	initscheduel();
    	//���ؿγ�����
    	loadcourse();
	}
    	
       /* ��ʼ���α� */	     
	   public void initscheduel()
	   {
		   	 r_layout = (RelativeLayout) findViewById(R.id.r_layout);
	  	 
		  	 WindowManager wManager = ((Activity) context).getWindowManager();
		  	 
		  	 DisplayMetrics dm = new DisplayMetrics();
		       wManager.getDefaultDisplay().getMetrics(dm);
		       width = dm.widthPixels;
		       height = dm.heightPixels;		       
		       /**
		        * Ƕ��ѭ�����ڽ���������textview
		        */
		       for(int i=1; i<9; i++){
		           RelativeLayout relativeLayout = new RelativeLayout(context);
		           relativeLayout.setId(i * 100);
		           //����һ����϶������ʾ�Ŀγ̷ֿ����ÿ�
		           relativeLayout.setPadding(2, 2, 2, 2);
		           RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width/8, ViewGroup.LayoutParams.WRAP_CONTENT);
		           if(i != 1){
		               params.addRule(RelativeLayout.RIGHT_OF, (i-1)*100);
		           }
		           for(int j=1; j<numOfCour+1; j++){
		
		               //����ÿ�����ӵ�textview����ĳ�ɶ���ĸ��Ӿ͸ĳ�ɶ��
		               final TextView textView = new TextView(context);
		               textView.setGravity(Gravity.CENTER);
		               textView.setBackgroundResource(R.drawable.course_back);
		
		               textView.setId(i * 100 + j);
		               RelativeLayout.LayoutParams tv_params = new RelativeLayout.LayoutParams(width/8, width/8);
		               if(j != 1){
		                   tv_params.addRule(RelativeLayout.BELOW, i*100+j-1);
		               }
		               if(i == 1)
		                   textView.setText(j+"");
		               relativeLayout.addView(textView, tv_params);
		           }
		           r_layout.addView(relativeLayout, params);
		       }
		       		      		       
	   }
          
	   public void loadcourse() {
		  
		   String name;
		   String adress;
		   int week;
		   int Period;
		   int start;
		   SQLiteDatabase db = dbHelper.getReadableDatabase();		  
		   Cursor cursor = db.query("course", null, null,null, null, null,null);
		   while (cursor.moveToNext()) {
			for(int i = 0; i < cursor.getCount(); i++)
			{				
				cursor.moveToPosition(i);
				name = cursor.getString(cursor.getColumnIndex("coursename"));
				adress =  cursor.getString(cursor.getColumnIndex("adress"));
				week = cursor.getInt(cursor.getColumnIndex("week"));
				Period = cursor.getInt(cursor.getColumnIndex("Period"));
				start = cursor.getInt(cursor.getColumnIndex("start"));
				CourseInfo courseInfo = new CourseInfo(name,adress,week,Period,start);
				courseInfos.add(courseInfo);
			}
			
		}
		  db.close();
		  getCourse(courseInfos);
		   
	}
	   
	/*   @Override
	   public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == 2)
		{			
			ArrayList<CourseInfo> courseInfos = (ArrayList<CourseInfo>) data.getSerializableExtra("key");
			getCourse(courseInfos);
		}
	   }*/
	   
	   //�������б���ȡ���γ���Ϣ���뵽����
	   public void getCourse(ArrayList<CourseInfo> courseLists)
	   {
		    int i = 0;
		   	while(i<courseLists.size())
		   	{
		   	 // addCorse -- �γ���   ���ڼ�  �ڼ���  �ܹ�����  ��ɫ
		   		addCourse(courseLists.get(i).getName(),courseLists.get(i).adress, courseLists.get(i).getWeek(), 
		   				courseLists.get(i).getstart(), courseLists.get(i).getPeriod(), i%4+1);
		   		i++;
		   	}

	   }
	   	
    /**
     * @param courseName �γ̵�����
     * @param week �γ��Ͽ���������һ~���գ�
     * @param period �γ��ڵڼ���
     * @param numOfPeriod �γ��ܹ�����
     */
     public void addCourse(String courseName,String adress, int week, int period, int numOfPeriod,int color){

        //���ɿγ̵�view����զ��զ�ģ���������viewҲ��
        final TextView textView = new TextView(context);
        
        textView.setText(courseName+adress);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(width / 65);   
        
        textView.setBackgroundResource(colors[color-1]);
        
        RelativeLayout.LayoutParams tv_params = new RelativeLayout.LayoutParams(width/8, width/8*numOfPeriod);
        tv_params.addRule(RelativeLayout.BELOW, (week+1) * 100 + period - 1);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById((week+1)*100);
        relativeLayout.addView(textView, tv_params);
    }
    
    public void importCourse() {
    	
		Intent courseiIntent = new Intent(context,ImportCourseActivity.class);		
		startActivity(courseiIntent);
	}
    
}
