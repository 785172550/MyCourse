package com.mycourse.ui;

import com.example.mycourse.R;
import com.mycourse.base.BaseActivity;

import android.media.AudioManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

public class SetingActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seting);
		initview();
	}

	private void initview() {
		initTopBarForLeft("设置");
		
		@SuppressWarnings("static-access")
		final AudioManager manager = 
				(AudioManager) getSystemService(SetingActivity.this.AUDIO_SERVICE);
		Switch switch1 = (Switch) findViewById(R.id.switch1);
		switch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean open) {
				// TODO Auto-generated method stub
				if(open)
				{
					manager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
					ShowToast("手机铃声已关闭");
					
				}
				else {
					manager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
					ShowToast("手机铃声已开启");
				}
			}
		});
	}
}
