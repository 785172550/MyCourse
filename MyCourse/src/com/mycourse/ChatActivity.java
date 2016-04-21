package com.mycourse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.example.mycourse.R;
import com.mycourse.adaper.MessageChatAdapter;
import com.mycourse.base.BaseActivity;
import com.mycourse.view.EmoticonsEditText;
import com.mycourse.view.xlist.XListView;
import com.mycourse.view.xlist.XListView.IXListViewListener;

/*
 *  ��������ܺ�ͼ������˷��ͽ���String �� URL ����Ϣ
 *  ���ܱ��������¼
 * 
 */

public class ChatActivity extends BaseActivity implements OnClickListener ,IXListViewListener {

	XListView mListView;
	
	networktask n1;
	
	//���Ͱ�ť
	private Button btn_chat_send;	
	// ���������������
	EmoticonsEditText edit_user_comment;
	// ������
	MessageChatAdapter mAdapter;
	
	final public  int sendmsg = 1;
	final public  int recmsg  = 0;
		
	
	public Handler mHandler2 = new Handler(){
		
		public void handleMessage(Message msg) {
			
			String recString =msg.getData().getString("rec");
			String rec="";
			try {
				
				JSONObject object = new JSONObject(recString);
				rec= (String) object.get("text");
				if(object.getString("url") != null)
				{
					URL url = new URL(object.getString("url"));
					rec = rec+" , "+url;
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
			refreshMessage(rec,recmsg);
			
		};
	};
	
	@Override	
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_chat);
		 n1 = new networktask();
		 n1.start();
		 initview();
	}
	
	private void initview() {
		// TODO Auto-generated method stub
		
		initTopBarForLeft("����С��");
		
		// ��ʼ�������ı���
		edit_user_comment = (EmoticonsEditText) findViewById(R.id.edit_user_comment);
		edit_user_comment.setOnClickListener(this);
		edit_user_comment.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub		
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});
		
	     edit_user_comment.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				
				final String msg = edit_user_comment.getText().toString();
				if (msg.equals("")) {
					ShowToast("�����뷢����Ϣ!");
					return false;
				}					
				// ˢ�½���
				refreshMessage(msg,sendmsg);				
				return false;
			}
		});
		
	     // ��ʼ�����Ͱ�ť
		btn_chat_send = (Button) findViewById(R.id.btn_chat_send);
		btn_chat_send.setOnClickListener(this);
		
		// ��ʼ�������¼��XListView 
		mListView = (XListView) findViewById(R.id.mListView);		
		initxlistview();
	}
	
	
	public void initxlistview()
	{
		// ���Ȳ�������ظ���
		mListView.setPullLoadEnable(false);
		// ��������
		mListView.setPullRefreshEnable(true);
		// ���ü�����
		mListView.setXListViewListener(this);
		mListView.pullRefreshing();
		mListView.setDividerHeight(0);
		
		// ��������
		initOrRefresh();
				
		// ���õ�ǰѡ����item
		mListView.setSelection(mAdapter.getCount() - 1);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.edit_user_comment:// ����ı������			
			mListView.setSelection(mListView.getCount() - 1);			
			break;
		case R.id.btn_chat_send:// �����ı�
			
			final String msg = edit_user_comment.getText().toString();
			if (msg.equals("")) {
				ShowToast("�����뷢����Ϣ!");
				return;
			}		
			// ˢ��ҳ��
			refreshMessage(msg,sendmsg);
			// ������Ϣ
			try {
				sendTextMessage(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			break;
		}
	}
	
	class  networktask extends Thread  {
		
		public Handler mHandler1;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Looper.prepare();			
			mHandler1 = new Handler() {
				
				public void handleMessage (Message msg) {//3�����崦����Ϣ�ķ���
					//System.out.println(msg.getData().getString("send")); 
					if(msg.what == 0)
					{
						try {
							String APIKEY = "ee62e6718ddbc290ab902b0dae9e21dd"; 
							String sendString = msg.getData().getString("send");
						    String INFO = URLEncoder.encode(sendString, "utf-8"); 
						    String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO; 
						    URL getUrl = new URL(getURL); 
						    HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
						    connection.connect(); 
						    System.out.println(msg.getData().getString("send"));
						    // ȡ������������ʹ��Reader��ȡ 
						    BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8"));
						    StringBuffer sb = new StringBuffer(); 
						    String line = ""; 
						    while ((line = reader.readLine()) != null) { 
						        sb.append(line); 
						    } 
						    reader.close(); 
						    // �Ͽ����� 
						    connection.disconnect(); 						    
						   // System.out.println(sb); 
						   
						    Message msg2 = new Message();
						    Bundle data2 = new Bundle();
							data2.putString("rec", sb.toString());
						    msg2.setData(data2);						  						    
						    mHandler2.sendMessage(msg2);
						    
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						
					}
				}
			};		
		    Looper.loop();
		}
	};
	
	/*
	 *  ������Ϣ
	 */
	public void sendTextMessage (String msg) throws IOException
	{
		
		//������Ϣ�����߳�
		
		Message message = new Message();
		message.what = 0;
		Bundle data = new Bundle();
		data.putString("send", msg);
		message.setData(data);
		
		n1.mHandler1.sendMessage(message);
		
	}
	
	/**
	 * ÿ�η������߽���һ����Ϣ֮����Ҫˢ�½���
	 * @Title: refreshMessage
	 * @Description: TODO
	 * @param @param message
	 * @return void
	 * @throws
	 */
	
	private void refreshMessage(String msg,int i) {
		// ���½���
		Message msgw = new Message();
		msgw.obj = msg;
		msgw.what = i;
		mAdapter.add(msgw);		
		mListView.setSelection(mAdapter.getCount() - 1);
		edit_user_comment.setText("");
	}
	
	/**
	 * ����ˢ��
	 * @Title: initOrRefresh
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initOrRefresh() {
		if (mAdapter != null) {
			// �趨XListViewѡ����item
			mListView.setSelection(mAdapter.getCount() - 1);			
			// Do nothing
			mAdapter.notifyDataSetChanged();
			
		} else {
			mAdapter = new MessageChatAdapter(this, initMsgData());
			mListView.setAdapter(mAdapter);
		}
	}
	
	private List<Message> initMsgData()
	{
		return new ArrayList<Message>();
	}
	
	private Handler handler = new Handler();
	
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ShowToast("�����¼��������Ŷ!");
				mListView.stopRefresh();
			}
		}, 1000);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		
	}
	
	
}
