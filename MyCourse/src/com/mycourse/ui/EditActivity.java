package com.mycourse.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.example.mycourse.R;
import com.mycourse.data.NoteUserInfo;
import com.mycourse.dialog.TimeSetDialog;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mycourse.util.DatabaseHelper;
import com.mycourse.util.NoteUtils;
import com.mycourse.util.SQLiteUtils;

public class EditActivity extends Activity {

	private String alerttime = "";
	private String datetime;
	private String content;
	private String tempContent,tempDatetime1,tempDatetime,tempAlerttime;
	private int index=0;
	private NoteUserInfo user;
	private TimeSetDialog timeSetDialog = null;
	private Button backButton,timeSetButton;
	private TextView datetext,alertTextView;
	private EditText edittext;
	Calendar calendar = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_activity);
		
		backButton = (Button)findViewById(R.id.backButton);
		timeSetButton = (Button)findViewById(R.id.timeSetButton);
		datetext = (TextView)findViewById(R.id.dateText);
		edittext = (EditText)findViewById(R.id.editText);
		alertTextView = (TextView)findViewById(R.id.timeText);
		
		user = new NoteUserInfo();
		user.setAlerttime(alerttime);
		timeSetButton.setOnClickListener( new OnClickListener(){
			@Override
			public void onClick(View v) {
				timeSetDialog = new TimeSetDialog(EditActivity.this);
				//��Ӽ���������dialog��ʧ��ִ��cancel()����ʱ�������¼�
				timeSetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {
						alerttime = timeSetDialog.alerttime;
						if(alerttime != null)
						    alertTextView.setText(NoteUtils.timeTransfer(alerttime));
						else
						{   /** alerttime Ϊ��ָ��  �������� !alerttime.equals("") && !alerttime.equals(tempAlerttime) ���ᱨ��ָ���쳣
						                 �޸���--SEAN 
						 	**/
							alerttime = "";
							alertTextView.setText("");
						}
							
						calendar = timeSetDialog.calendar;
						user.setAlerttime(alerttime);
					}
				});
				timeSetDialog.show();
			}
		});
		
		backButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("android.intent.extra.INTENT");
		datetime = bundle.getString("datetime");
		content = bundle.getString("content");
		alerttime = bundle.getString("alerttime");
		index = bundle.getInt("index");
		tempContent = new String(content);
		tempDatetime = new String(datetime);
		tempAlerttime = new String(alerttime);
		Time time = new Time();
		//�жϸü�¼���½������޸�
		if(datetime.equals(""))
		{
			time.setToNow();
		}
		else{
			time.set(Long.parseLong(datetime));
		}
		int month = time.month+1;
		int day = time.monthDay;
		int hour = time.hour;
		int minute = time.minute;
//		String minute = time.minute<10?"0"+time.minute:String.valueOf(time.minute);
		tempDatetime1 = month+"��"+day+"��"+'\n'+NoteUtils.format(hour)+":"+NoteUtils.format(minute);
		datetext.setText(tempDatetime1);
		edittext.setText(content);
		String tempS = new String(alerttime);
		if(!alerttime.equals(""))
		   alertTextView.setText(NoteUtils.timeTransfer(tempS));
		else alertTextView.setText("");
		edittext.setSelection(content.length());  //���ù��������ĩβ
	}
	//ʱ�����ü�������
//	class TimeSetListener implements TimePickerDialog.OnTimeSetListener{
//		@Override
//		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//			TextView alertTextView = (TextView)findViewById(R.id.timeText);
//			calendar.setTimeInMillis(System.currentTimeMillis());
//		    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//		    calendar.set(Calendar.MINUTE, minute);
//		    calendar.set(Calendar.SECOND, 0);
//		    calendar.set(Calendar.MILLISECOND, 0);
//		    //��Ҫһ��ʱ���жϷ���������ʱ���С�����ʾ  
//		    tempAlrettime = Utils.format(hourOfDay) + ":" +Utils.format(minute);
//		    alerttime = tempAlrettime;
//		    user.setAlerttime(alerttime);
//		    alertTextView.setText(tempAlrettime);
//		}
//	}
	

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		edittext = (EditText)findViewById(R.id.editText);
		Time time = new Time();
		time.setToNow();

		user.setAlerttime(alerttime);
		datetime =""+time.toMillis(true);
		user.setDatetime(datetime);
		time.set(time.toMillis(true));

		content = edittext.getText().toString();
		user.setContent(content);
		
		if((!content.isEmpty() && !tempContent.equals(content)) || 
				(!alerttime.equals("") && !alerttime.equals(tempAlerttime))){                   
			//������ݷǿ����Ѿ������ģ��������ʾ�����ݿ�
			ArrayList<HashMap<String,String>> list = NoteUtils.getList();
			SQLiteUtils sqlite = new SQLiteUtils();
			System.out.println("---------------------------");
			DatabaseHelper dbHelper = sqlite.createDBHelper(EditActivity.this);
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("datetime",user.getDatetime());
			map.put("content",user.getContent());
			map.put("alerttime",user.getAlerttime());
			
			if(tempContent.isEmpty())  {					//��Ϊ�½���¼�����
				list.add(map);
				sqlite.insert(dbHelper,user);
			}
			else {
				list.set(index, map);          //��Ϊ�޸��滻��ԭ���ļ�¼
				sqlite.delete(dbHelper, tempDatetime);
				sqlite.insert(dbHelper,user);
			}
			//������������
			if(!alerttime.equals(tempAlerttime) && !alerttime.equals(""))
			{
				System.out.println("alerttime done!");
				alertSet();
			}
				
		}
	}
	
	
	private void alertSet(){
		Intent intent = new Intent("android.intent.action.ALARMRECEIVER");
		intent.putExtra("datetime", datetime);
	    intent.putExtra("content", content);
	    intent.putExtra("alerttime",alerttime);
	    PendingIntent pendingIntent = PendingIntent.getBroadcast(EditActivity.this, 0, intent, 0);
	    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
	    alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), pendingIntent);
	    //setRepeating()����ڶ��������������ó�����ʱ�䣬�������ӻ�������Ϳ���
	    //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),(24 * 60 * 60 * 1000), pendingIntent);
	}

}
