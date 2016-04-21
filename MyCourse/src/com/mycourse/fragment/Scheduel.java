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
	//颜色
	int colors[] = {
			R.drawable.course_back1,
			R.drawable.course_back2,
			R.drawable.course_back3,
			R.drawable.course_back4,
	};
	
	 /**课程表布局*/
    private RelativeLayout r_layout;
    /**屏幕宽度*/
    int width;
    /**屏幕高度*/
    int height;
    /**定义每天课程的节数*/
    int numOfCour = 13;
    // 课程数据库
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
    	
    	dbHelper = new CourseDataHelper(context,"wanghang_course_db");
    	courseInfos = new ArrayList<CourseInfo>();
    	//初始化课表界面
    	initscheduel();
    	//加载课程数据
    	loadcourse();
	}
    	
       /* 初始化课表 */	     
	   public void initscheduel()
	   {
		   	 r_layout = (RelativeLayout) findViewById(R.id.r_layout);
	  	 
		  	 WindowManager wManager = ((Activity) context).getWindowManager();
		  	 
		  	 DisplayMetrics dm = new DisplayMetrics();
		       wManager.getDefaultDisplay().getMetrics(dm);
		       width = dm.widthPixels;
		       height = dm.heightPixels;		       
		       /**
		        * 嵌套循环用于将布局铺满textview
		        */
		       for(int i=1; i<9; i++){
		           RelativeLayout relativeLayout = new RelativeLayout(context);
		           relativeLayout.setId(i * 100);
		           //设置一个间隙，让显示的课程分开更好看
		           relativeLayout.setPadding(2, 2, 2, 2);
		           RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width/8, ViewGroup.LayoutParams.WRAP_CONTENT);
		           if(i != 1){
		               params.addRule(RelativeLayout.RIGHT_OF, (i-1)*100);
		           }
		           for(int j=1; j<numOfCour+1; j++){
		
		               //生成每个格子的textview，想改成啥样的格子就改成啥样
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
	   
	   //从数据列表中取出课程信息加入到界面
	   public void getCourse(ArrayList<CourseInfo> courseLists)
	   {
		    int i = 0;
		   	while(i<courseLists.size())
		   	{
		   	 // addCorse -- 课程名   星期几  第几节  总共几节  颜色
		   		addCourse(courseLists.get(i).getName(),courseLists.get(i).adress, courseLists.get(i).getWeek(), 
		   				courseLists.get(i).getstart(), courseLists.get(i).getPeriod(), i%4+1);
		   		i++;
		   	}

	   }
	   	
    /**
     * @param courseName 课程的名字
     * @param week 课程上课周数（周一~周日）
     * @param period 课程在第几节
     * @param numOfPeriod 课程总共几节
     */
     public void addCourse(String courseName,String adress, int week, int period, int numOfPeriod,int color){

        //生成课程的view，想咋改咋改，换成其他view也可
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
