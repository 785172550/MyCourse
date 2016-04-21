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
 *  Fragment ����
  * @ClassName: FragmentBase
  * @author: SEAN
  * @date: 2015-5-20
 */
public class BaseFragment extends android.support.v4.app.Fragment {

	/**
	 * ���õ�Header����
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

	
	/** ��Log
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
	 * ֻ��title initTopBarLayoutByTitle
	 * @Title: initTopBarLayoutByTitle
	 * @throws
	 */
	public void initTopBarForOnlyTitle(String titleName) {
		mHeaderLayout = (HeaderLayout)findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.DEFAULT_TITLE);
		mHeaderLayout.setDefaultTitle(titleName);
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
	 * ֻ����߰�ť��Title initTopBarLayout
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
	
	/** �ұ�+title
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
	
	// ��߰�ť�ĵ���¼�
	public class OnLeftButtonClickListener implements
			onLeftImageButtonClickListener {

		@Override
		public void onClick() {
			getActivity().finish();
		}
	}
}
