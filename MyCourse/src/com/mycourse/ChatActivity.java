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
 *  聊天界面能和图灵机器人发送接收String 和 URL 的信息
 *  不能保存聊天记录
 * 
 */

public class ChatActivity extends BaseActivity implements OnClickListener ,IXListViewListener {

	XListView mListView;
	
	networktask n1;
	
	//发送按钮
	private Button btn_chat_send;	
	// 可输入表情的输入框
	EmoticonsEditText edit_user_comment;
	// 适配器
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
		
		initTopBarForLeft("秘书小白");
		
		// 初始化输入文本框
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
					ShowToast("请输入发送消息!");
					return false;
				}					
				// 刷新界面
				refreshMessage(msg,sendmsg);				
				return false;
			}
		});
		
	     // 初始化发送按钮
		btn_chat_send = (Button) findViewById(R.id.btn_chat_send);
		btn_chat_send.setOnClickListener(this);
		
		// 初始化聊天纪录的XListView 
		mListView = (XListView) findViewById(R.id.mListView);		
		initxlistview();
	}
	
	
	public void initxlistview()
	{
		// 首先不允许加载更多
		mListView.setPullLoadEnable(false);
		// 允许下拉
		mListView.setPullRefreshEnable(true);
		// 设置监听器
		mListView.setXListViewListener(this);
		mListView.pullRefreshing();
		mListView.setDividerHeight(0);
		
		// 加载数据
		initOrRefresh();
				
		// 设置当前选定的item
		mListView.setSelection(mAdapter.getCount() - 1);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.edit_user_comment:// 点击文本输入框			
			mListView.setSelection(mListView.getCount() - 1);			
			break;
		case R.id.btn_chat_send:// 发送文本
			
			final String msg = edit_user_comment.getText().toString();
			if (msg.equals("")) {
				ShowToast("请输入发送消息!");
				return;
			}		
			// 刷新页面
			refreshMessage(msg,sendmsg);
			// 发送消息
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
				
				public void handleMessage (Message msg) {//3、定义处理消息的方法
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
						    // 取得输入流，并使用Reader读取 
						    BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8"));
						    StringBuffer sb = new StringBuffer(); 
						    String line = ""; 
						    while ((line = reader.readLine()) != null) { 
						        sb.append(line); 
						    } 
						    reader.close(); 
						    // 断开连接 
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
	 *  发送信息
	 */
	public void sendTextMessage (String msg) throws IOException
	{
		
		//发送消息到子线程
		
		Message message = new Message();
		message.what = 0;
		Bundle data = new Bundle();
		data.putString("send", msg);
		message.setData(data);
		
		n1.mHandler1.sendMessage(message);
		
	}
	
	/**
	 * 每次发生或者接受一条消息之后需要刷新界面
	 * @Title: refreshMessage
	 * @Description: TODO
	 * @param @param message
	 * @return void
	 * @throws
	 */
	
	private void refreshMessage(String msg,int i) {
		// 更新界面
		Message msgw = new Message();
		msgw.obj = msg;
		msgw.what = i;
		mAdapter.add(msgw);		
		mListView.setSelection(mAdapter.getCount() - 1);
		edit_user_comment.setText("");
	}
	
	/**
	 * 界面刷新
	 * @Title: initOrRefresh
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initOrRefresh() {
		if (mAdapter != null) {
			// 设定XListView选定的item
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
				ShowToast("聊天记录加载完了哦!");
				mListView.stopRefresh();
			}
		}, 1000);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		
	}
	
	
}
