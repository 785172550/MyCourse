package com.mycourse.view;

import com.example.mycourse.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HeaderLayout extends LinearLayout {
	private LayoutInflater mInflater;
	private View mHeader;
	private LinearLayout mLayoutLeftContainer;
	private LinearLayout mLayoutRightContainer;
	private TextView mHtvSubTitle;
	private LinearLayout mLayoutRightImageButtonLayout;
	private Button mRightImageButton;
	private onRightImageButtonClickListener mRightImageButtonClickListener;
	
	//private RelativeLayout root_layout;

	private LinearLayout mLayoutLeftImageButtonLayout;
	private ImageButton mLeftImageButton;
	private onLeftImageButtonClickListener mLeftImageButtonClickListener;
	private ImageView nearsSex;
	public final static int[] mLocation = new int[2];

	public enum HeaderStyle {// ͷ��������ʽ
		DEFAULT_TITLE, TITLE_LIFT_IMAGEBUTTON, TITLE_RIGHT_IMAGEBUTTON, TITLE_DOUBLE_IMAGEBUTTON;
	}

	public HeaderLayout(Context context) {
		super(context);
		init(context);
	}

	public HeaderLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	@SuppressLint("InflateParams")
	public void init(Context context) {
		mInflater = LayoutInflater.from(context);
		mHeader = mInflater.inflate(R.layout.common_header, null);
		addView(mHeader);
		initViews();
	}

	public void initViews() {
		
		//root_layout = (RelativeLayout) findViewByHeaderId(R.id.header_root_layout);
		mLayoutLeftContainer = (LinearLayout) findViewByHeaderId(R.id.header_layout_leftview_container);
		// mLayoutMiddleContainer = (LinearLayout)
		// findViewByHeaderId(R.id.header_layout_middleview_container);�м䲿�������������������ťʱ�ɴ�
		mLayoutRightContainer = (LinearLayout) findViewByHeaderId(R.id.header_layout_rightview_container);
		mHtvSubTitle = (TextView) findViewByHeaderId(R.id.header_htv_subtitle);
		//nearsSex = (ImageView) findViewByHeaderId(R.id.header_title_img);

	}
	
	public void showNearsSex(Boolean show) {
		if (show) {
			nearsSex.setVisibility(View.VISIBLE);
		}
		else {
			nearsSex.setVisibility(View.INVISIBLE);
		}
	}
	
	public void setNearsSexImg(int sex) {
		if (sex == 0) {
			nearsSex.setImageResource(R.drawable.female);
		}else if (sex == 1) {
			nearsSex.setImageResource(R.drawable.male);
		}
	}

	public View findViewByHeaderId(int id) {
		return mHeader.findViewById(id);
	}

	public void init(HeaderStyle hStyle) {
		switch (hStyle) {
		case DEFAULT_TITLE:
			defaultTitle();
			break;

		case TITLE_LIFT_IMAGEBUTTON:
			defaultTitle();
			titleLeftImageButton();
			break;

		case TITLE_RIGHT_IMAGEBUTTON:
			defaultTitle();
			titleRightImageButton();
			break;

		case TITLE_DOUBLE_IMAGEBUTTON:
			defaultTitle();
			titleLeftImageButton();
			titleRightImageButton();
			break;
		}
	}

	// Ĭ�����ֱ���
	private void defaultTitle() {
		mLayoutLeftContainer.removeAllViews();
		mLayoutRightContainer.removeAllViews();
	}

	// ����Զ��尴ť
	private void titleLeftImageButton() {
		View mleftImageButtonView = mInflater.inflate(
				R.layout.common_header_button, null);
		mLayoutLeftContainer.addView(mleftImageButtonView);
		mLayoutLeftImageButtonLayout = (LinearLayout) mleftImageButtonView
				.findViewById(R.id.header_layout_imagebuttonlayout);
		mLeftImageButton = (ImageButton) mleftImageButtonView
				.findViewById(R.id.header_ib_imagebutton);
		mLayoutLeftImageButtonLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (mLeftImageButtonClickListener != null) {
					mLeftImageButtonClickListener.onClick();
				}
			}
		});
	}

	// �Ҳ��Զ��尴ť
	private void titleRightImageButton() {
		View mRightImageButtonView = mInflater.inflate(
				R.layout.common_header_rightbutton, null);
		mLayoutRightContainer.addView(mRightImageButtonView);
		mLayoutRightImageButtonLayout = (LinearLayout) mRightImageButtonView
				.findViewById(R.id.header_layout_imagebuttonlayout);
		mRightImageButton = (Button) mRightImageButtonView
				.findViewById(R.id.header_ib_imagebutton);
		
		mRightImageButtonView.getLocationOnScreen(mLocation);
		mLayoutRightImageButtonLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (mRightImageButtonClickListener != null) {
					mRightImageButtonClickListener.onClick(arg0);
				}
			}
		});
	}

	/** ��ȡ�ұ߰�ť
	  * @Title: getRightImageButton
	  * @Description: TODO
	  * @param @return 
	  * @return Button
	  * @throws
	  */
	public Button getRightImageButton(){
		if(mRightImageButton!=null){
			return mRightImageButton;
		}
		return null;
	}
	public void setDefaultTitle(CharSequence title) {
		if (title != null) {
			mHtvSubTitle.setText(title);
		} else {
			mHtvSubTitle.setVisibility(View.GONE);
		}
		
	}

	public void setTitleAndRightButton(CharSequence title, int backid,String text,
			onRightImageButtonClickListener onRightImageButtonClickListener) {
		setDefaultTitle(title);
		mLayoutRightContainer.setVisibility(View.VISIBLE);
		if (mRightImageButton != null && backid > 0) {		
			mRightImageButton.setBackgroundResource(backid);
			mRightImageButton.setText(text);
			setOnRightImageButtonClickListener(onRightImageButtonClickListener);
		}
	}
	
	public void setTitleAndRightImageButton(CharSequence title, int backid,
			onRightImageButtonClickListener onRightImageButtonClickListener) {
		setDefaultTitle(title);
		mLayoutRightContainer.setVisibility(View.VISIBLE);
		if (mRightImageButton != null && backid > 0) {
			mRightImageButton.setTextColor(getResources().getColor(R.color.transparent));
			mRightImageButton.setBackgroundResource(backid);
			setOnRightImageButtonClickListener(onRightImageButtonClickListener);
		}
	}

	public void setTitleAndLeftImageButton(CharSequence title, int id,
			onLeftImageButtonClickListener listener) {
		setDefaultTitle(title);
		if (mLeftImageButton != null && id > 0) {
			mLeftImageButton.setImageResource(id);
			setOnLeftImageButtonClickListener(listener);
		}
		mLayoutRightContainer.setVisibility(View.INVISIBLE);
	}

	public void setOnRightImageButtonClickListener(
			onRightImageButtonClickListener listener) {
		mRightImageButtonClickListener = listener;
	}

	public interface onRightImageButtonClickListener {
		void onClick(View v);
	}

	public void setOnLeftImageButtonClickListener(
			onLeftImageButtonClickListener listener) {
		mLeftImageButtonClickListener = listener;
	}

	public interface onLeftImageButtonClickListener {
		void onClick();
	}
	
//	public void setActionBarBgForFemale() {
//		root_layout.setBackgroundResource(R.drawable.common_action_bar_bg_female);
//	}
//	
//	public void setActionBarRightBtnForFemale() {
//		mRightImageButton.setBackgroundResource(R.drawable.base_action_bar_send_female_selector);
//	}

}