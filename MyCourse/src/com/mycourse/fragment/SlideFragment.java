package com.mycourse.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mycourse.R;
import com.mycourse.ChatActivity;
import com.mycourse.NoteActivity;
import com.mycourse.adaper.SlideAdaper;
import com.mycourse.ui.Luntan;
import com.mycourse.ui.SetingActivity;

public class SlideFragment extends android.support.v4.app.Fragment {

	private View mView;  
	private ListView myListView;
	private Context context;
		
	private Scheduel scheduel;
	public SlideFragment()
	{
		super();
	}
	public SlideFragment(Context context) {
		super();
		this.context = context;		
	}
	
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	            Bundle savedInstanceState)  
	    {  
	        if (mView == null)  
	        {  
	            initView(inflater, container);  
	        }  	       
	        return mView;  
	        
	    }  
	 
	 private void initView(LayoutInflater inflater, ViewGroup container)
	 {
		 mView = inflater.inflate(R.layout.slide_main, container,false);
		 
		 myListView = (ListView) mView.findViewById(R.id.slide_listview);
		 //�Զ���adapter
		 SlideAdaper slideAdaper = new SlideAdaper(context);
		 myListView.setAdapter(slideAdaper);	 

		 myListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int positon,
					long arg3) {
				// TODO Auto-generated method stub
				switch (positon) {//�α�
				case 0:				
					if(null == scheduel)
					{
						scheduel = new Scheduel(context);
						((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main, scheduel).commit();
					}
					else {
						((FragmentActivity) context).getSupportFragmentManager().beginTransaction().show(scheduel);
					}
					break;
				case 1://�Ʊʼ�
					Intent noteIntent = new Intent(context,NoteActivity.class);
					startActivity(noteIntent);
					break;
				case 2://������
					Intent chatIntent = new Intent(context,ChatActivity.class);
					startActivity(chatIntent);
					break;
				case 3://��̳
					Intent luntanIntent = new Intent(context,Luntan.class);
					startActivity(luntanIntent);
					break;
				case 4://����
					Intent setIntent = new Intent(context,SetingActivity.class);
					startActivity(setIntent);
					break;
				case 5://�˳�
					Toast.makeText(context, "�������η��ؼ��˳�", Toast.LENGTH_LONG).show();
					break;
				
				}
				
			}
		});
	 }

}
