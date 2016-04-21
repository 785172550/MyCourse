package com.mycourse;

import com.example.mycourse.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.mycourse.base.BaseActivity;
import com.mycourse.fragment.Scheduel;
import com.mycourse.fragment.SlideFragment;
import android.graphics.Canvas;
import android.os.Bundle;




public class MainActivity extends BaseActivity {
	
	private static SlidingMenu slidingMenu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		initview();
		
	}

	private Scheduel scheduel;
	
	private void initview() {
		//侧滑菜单初始化
		initslideview();
		//默认课表界面		
		if(null == scheduel)
		{
			scheduel = new Scheduel(MainActivity.this);
			getSupportFragmentManager().beginTransaction().replace(R.id.main, scheduel).commit();
		}
		else {
			getSupportFragmentManager().beginTransaction().show(scheduel);
		}
	}
	
	private void initslideview() {

		
		slidingMenu = new SlidingMenu(MainActivity.this);		
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		//值越大，侧滑越窄
		slidingMenu.setBehindOffset(170);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.frag_slide);
		
		//slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT); 		
		//slidingMenu.setSecondaryMenu(R.layout.menusecond);
		
		//设置背景图片
		slidingMenu.setBackgroundResource(R.drawable.biz_pc_main_bg);
		// 设置专场动画效果  
        slidingMenu.setBehindCanvasTransformer(new SlidingMenu.CanvasTransformer() {  
            @Override  
            public void transformCanvas(Canvas canvas, float percentOpen) {  
                float scale = (float) (percentOpen * 0.25 + 0.75);  
                canvas.scale(scale, scale, -canvas.getWidth()/2,  
                        canvas.getHeight() /2);  
            }  
        });           
        
       slidingMenu.setAboveCanvasTransformer(new SlidingMenu.CanvasTransformer() {              @Override  
           public void transformCanvas(Canvas canvas, float percentOpen) {  
                float scale = (float) (1 - percentOpen * 0.25);  
                canvas.scale(scale, scale, 0, canvas.getHeight() / 2);  
           }  
        });  
        
        
        android.support.v4.app.Fragment slideFragment = new SlideFragment(MainActivity.this);  
            
        getSupportFragmentManager().beginTransaction()
        	.replace(R.id.frag_slide, slideFragment).commit();
//        getFragmentManager().beginTransaction()  
//                 .replace(R.id.frag_slide,slideFragment).commit();   
         
	}		
	
	
	public static void showslide() {
		slidingMenu.showMenu();
	}
	
	private static long firstTime;
	/**
	 * 连续按两次返回键就退出
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (firstTime + 2000 > System.currentTimeMillis()) {
			super.onBackPressed();
		} else {
			ShowToast("再按一次退出程序");
		}
		firstTime = System.currentTimeMillis();
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}
}
