package com.mycourse.dialog;

import java.util.Calendar;

import com.example.mycourse.R;
import com.mycourse.util.NoteUtils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class TimeSetDialog extends Dialog {

	Button dateSetButton,positiveButton,negativeButton;  
	TimePicker timePicker;
	public Calendar calendar;
	String date;
	public String alerttime = null;
	private TimeSetDialog timeSetDialog = null;
	
	//��ʼ��ʱ������
	private void init(){
		calendar.setTimeInMillis(System.currentTimeMillis());
		dateSetButton.setText(NoteUtils.toDateString(calendar));
	    int hour = calendar.get(Calendar.HOUR_OF_DAY);
	    int minute = calendar.get(Calendar.MINUTE);
	    timePicker.setIs24HourView(true);
	    timePicker.setCurrentHour(hour);
	    timePicker.setCurrentMinute(minute);
	}
	public TimeSetDialog(Context context) {
		super(context);
		setContentView(R.layout.timeset_view);
		
		timeSetDialog = this;
		this.setTitle("����ʱ��������");
		
		calendar = Calendar.getInstance();
		timePicker = (TimePicker)findViewById(R.id.timePicker);
		dateSetButton = (Button)findViewById(R.id.dateButton);
		positiveButton = (Button)findViewById(R.id.positiveButton);
		negativeButton = (Button)findViewById(R.id.negativeButton);
		
		init();
		
		dateSetButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new DatePickerDialog(getContext(), new OnDateSetListener(){
					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						//��������
						calendar.set(year, monthOfYear, dayOfMonth);
						date = NoteUtils.toDateString(calendar);
						dateSetButton.setText(date);
					}}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		positiveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
				calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
				alerttime = calendar.getTimeInMillis()+"";
				System.out
						.println("-----"+alerttime);
				timeSetDialog.cancel();
 			}
		});
		negativeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				alerttime = null;
				timeSetDialog.cancel();
			}
		});
	}

}
