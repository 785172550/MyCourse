package com.mycourse.base;


import com.example.mycourse.R;
import com.mycourse.CustomApplcation;
import com.mycourse.view.HeaderLayout;
import com.mycourse.view.HeaderLayout.HeaderStyle;
import com.mycourse.view.HeaderLayout.onLeftImageButtonClickListener;
import com.mycourse.view.HeaderLayout.onRightImageButtonClickListener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

/**
 *  ���� ,�ṩShowToast���� �� initTopBarForOnlyTitle...�ȷ���
 * @author SEAN
 * @data 2015-5-20
 * 
 */
public class BaseActivity extends FragmentActivity {
	
	protected HeaderLayout mHeaderLayout;

	CustomApplcation mApplication;
	protected int mScreenWidth;
	protected int mScreenHeight;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		mApplication = CustomApplcation.getInstance();
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;
		mScreenHeight = metric.heightPixels;
	}
	
	Toast mToast;
	//Toast ����
	public void ShowToast(final String text) {
		if (!TextUtils.isEmpty(text)) {
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if (mToast == null) {
						mToast = Toast.makeText(getApplicationContext(), text,
								Toast.LENGTH_LONG);
					} else {
						mToast.setText(text);
					}
					mToast.show();
				}
			});
			
		}
	}
	

	public void ShowToast(final int resId) {
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (mToast == null) {
					mToast = Toast.makeText(BaseActivity.this.getApplicationContext(), resId,
							Toast.LENGTH_LONG);
				} else {
					mToast.setText(resId);
				}
				mToast.show();
			}
		});
	}
	
	
	
	/** ��Log
	  * ShowLog
	  * @return void
	  * @throws
	  */
	public void ShowLog(String msg){
		Log.i("life",msg);
	}
	

	/**
	 * ֻ��title initTopBarLayoutByTitle
	 * @Title: initTopBarLayoutByTitle
	 * @throws
	 */
	public void initTopBarForOnlyTitle(String titleName) {
		mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
		//findViewById(R.id.content);
		mHeaderLayout.init(HeaderStyle.DEFAULT_TITLE);
		mHeaderLayout.setDefaultTitle(titleName);
	}

	/**
	 * ��ʼ��������-�����Ұ�ť
	 * @return void
	 * @throws
	 */
	public void initTopBarForBoth(String titleName, int rightDrawableId,String text,
			onRightImageButtonClickListener listener) {
		mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.TITLE_DOUBLE_IMAGEBUTTON);
		mHeaderLayout.setTitleAndLeftImageButton(titleName,
				R.drawable.base_action_bar_back_bg_selector,
				new OnLeftButtonClickListener());
		mHeaderLayout.setTitleAndRightButton(titleName, rightDrawableId,text,
				listener);
	}
	
	/**
	 *   ���Ĭ���Ƿ��ؼ�
	 * @param titleName
	 * @param rightDrawableId
	 * @param listener
	 */
	public void initTopBarForBoth(String titleName, int rightDrawableId,
			onRightImageButtonClickListener listener) {
		mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.TITLE_DOUBLE_IMAGEBUTTON);
		mHeaderLayout.setTitleAndLeftImageButton(titleName,
				R.drawable.base_action_bar_back_bg_selector,
				new OnLeftButtonClickListener());
		mHeaderLayout.setTitleAndRightImageButton(titleName, rightDrawableId,
				listener);
	}	

	/**
	 * ��ʼ��������-�����Ұ�ť 
	 * @param titleName 
	 * @param rightDrawableId �ұ߰�ť Drawable ��Դ
	 * @param listener1 �ұ߰�ť������ 
	 * @param leftDrawableId  ��߰�ť Drawable ��Դ
	 * @param listener2 ��߰�ť������
	 * 
	 */
	public void initTopBarForBoth(String titleName, 
			int rightDrawableId,onRightImageButtonClickListener listener1,
			int leftDrawableId ,onLeftImageButtonClickListener listener2 ) {
		mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.TITLE_DOUBLE_IMAGEBUTTON);
		mHeaderLayout.setTitleAndLeftImageButton(titleName,leftDrawableId,
				listener2);
		mHeaderLayout.setTitleAndRightImageButton(titleName, rightDrawableId,
				listener1);
	}
	
	/**
	 * ֻ����߰�ť��Title initTopBarLayout
	 * 
	 * @throws
	 */
	public void initTopBarForLeft(String titleName) {
		mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.TITLE_DOUBLE_IMAGEBUTTON);
		mHeaderLayout.setTitleAndLeftImageButton(titleName,
				R.drawable.base_action_bar_back_bg_selector,
				new OnLeftButtonClickListener());
	}
	
	// ��߰�ť�ĵ���¼�
	public class OnLeftButtonClickListener implements
			onLeftImageButtonClickListener {

		public void onClick() {
			finish();
		}
	}
	

}
