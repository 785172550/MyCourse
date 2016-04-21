package com.mycourse.base;


import com.example.mycourse.R;
import com.mycourse.view.HeaderLayout;
import com.mycourse.view.HeaderLayout.HeaderStyle;
import com.mycourse.view.HeaderLayout.onLeftImageButtonClickListener;
import com.mycourse.view.HeaderLayout.onRightImageButtonClickListener;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/*
 *  Fragment 基类
  * @ClassName: FragmentBase
  * @author: SEAN
  * @date: 2015-5-20
 */
public class BaseFragment extends android.support.v4.app.Fragment {

	/**
	 * 公用的Header布局
	 */
	public static HeaderLayout mHeaderLayout;
	public LayoutInflater mInflater;
	private Handler handler = new Handler();

	public void runOnWorkThread(Runnable action) {
		new Thread(action).start();
	}

	public void runOnUiThread(Runnable action) {
		handler.post(action);
	}
	
	public void onCreate(android.os.Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		mInflater = LayoutInflater.from(getActivity());
	};
	Toast mToast;

	public void ShowToast(String text) {
		if (mToast == null) {
			mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
		}
		mToast.show();
	}

	public void ShowToast(int text) {
		if (mToast == null) {
			mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_LONG);
		} else {
			mToast.setText(text);
		}
		mToast.show();
	}

	
	/** 打Log
	  * ShowLog
	  * @return void
	  * @throws
	  */
	public void ShowLog(String msg){
		Log.i("BaseFragment", msg);
	}
	
	public View findViewById(int paramInt) {
		return getView().findViewById(paramInt);
	}
	
	/**
	 * 只有title initTopBarLayoutByTitle
	 * @Title: initTopBarLayoutByTitle
	 * @throws
	 */
	public void initTopBarForOnlyTitle(String titleName) {
		mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.DEFAULT_TITLE);
		mHeaderLayout.setDefaultTitle(titleName);
	}

	/**
	 * 初始化标题栏-带左右按钮 
	 * @param titleName 
	 * @param rightDrawableId 右边按钮 Drawable 资源
	 * @param listener1 右边按钮监听器 
	 * @param leftDrawableId  左边按钮 Drawable 资源
	 * @param listener2 左边按钮监听器
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
	 *   左边默认是返回键
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
	 * 只有左边按钮和Title initTopBarLayout
	 * 
	 * @throws
	 */
	public void initTopBarForLeft(String titleName) {
		mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.TITLE_LIFT_IMAGEBUTTON);
		mHeaderLayout.setTitleAndLeftImageButton(titleName,
				R.drawable.base_action_bar_back_bg_selector,
				new OnLeftButtonClickListener());
	}
	
	public void initTopBarForLeft(String titleName,int leftDrawableId,
			onLeftImageButtonClickListener listener)
	{
		mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.TITLE_LIFT_IMAGEBUTTON);
		mHeaderLayout.setTitleAndLeftImageButton(titleName, leftDrawableId,
				listener);
//		if (flag) {
//			setNearsSex(nearsSex);
//		}
	}
	
	/** 右边+title
	  * initTopBarForRight
	  * @return void
	  * @throws
	  */
	public void initTopBarForRight(String titleName,int rightDrawableId,
			onRightImageButtonClickListener listener) {
		mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.TITLE_RIGHT_IMAGEBUTTON);
		mHeaderLayout.setTitleAndRightImageButton(titleName, rightDrawableId,
				listener);
//		
//		if (flag) {
//			setNearsSex(nearsSex);
//		}
	}
	
	// 左边按钮的点击事件
	public class OnLeftButtonClickListener implements
			onLeftImageButtonClickListener {

		@Override
		public void onClick() {
			getActivity().finish();
		}
	}
}
