package com.mycourse;

import android.app.Application;


public class CustomApplcation extends Application {

	public static CustomApplcation mInstance;
	
	public CustomApplcation() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mInstance = this;
	}
	
	// ����ģʽ�����ܼ�ʱ��������
	public static CustomApplcation getInstance() {
		return mInstance;
	}
}
