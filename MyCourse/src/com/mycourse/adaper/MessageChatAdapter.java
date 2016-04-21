package com.mycourse.adaper;

import java.util.List;
import com.example.mycourse.R;
import com.mycourse.util.FaceTextUtils;
import android.content.Context;
import android.os.Message;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MessageChatAdapter extends BaseListAdapter<Message> {


	//文本  发出接收
	private final int TYPE_RECEIVER_TXT = 0;
	private final int TYPE_SEND_TXT = 1;
	
	public MessageChatAdapter(Context context, List<Message> list) {
		super(context, list);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public View bindView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final String item = list.get(position).obj.toString();		
		convertView = createViewByType(item, position);		
		
		//文本类型
		final ImageView iv_fail_resend = ViewHolder.get(convertView, R.id.iv_fail_resend);//失败重发
		final TextView tv_send_status = ViewHolder.get(convertView, R.id.tv_send_status);//发送状态
		TextView tv_time = ViewHolder.get(convertView, R.id.tv_time);       // 发送时间
		TextView tv_message = ViewHolder.get(convertView, R.id.tv_message); // 发送的信息
		final ProgressBar progress_load = ViewHolder.get(convertView, R.id.progress_load);//进度条
		
		tv_time.setVisibility(View.INVISIBLE);
		
		if(getItemViewType(position)==TYPE_SEND_TXT)
		{
			progress_load.setVisibility(View.INVISIBLE);
			iv_fail_resend.setVisibility(View.INVISIBLE);
			tv_send_status.setVisibility(View.VISIBLE);
			tv_send_status.setText("已发送");
		}
	
		try {
			SpannableString spannableString = FaceTextUtils
					.toSpannableString(mContext, item);
			tv_message.setText(spannableString);
		} catch (Exception e) {
		}		
		return convertView;
	}
	
	//判断 发出接收数据
	
	public int getItemViewType(int position) 
	{

		if(list.get(position).what == 1)		
			return TYPE_SEND_TXT;		
		else
			return TYPE_RECEIVER_TXT;
		//if
		//return (list.get(position).what == 0) ? TYPE_SEND_TXT: TYPE_RECEIVER_TXT;
		
	}
	
	private View createViewByType(String message, int position) {
		
		System.out.println(message + " : " 
		+list.get(position).what+ ":" +getItemViewType(position));
		
		return getItemViewType(position) == TYPE_RECEIVER_TXT ? 
				mInflater.inflate(R.layout.item_chat_received_message, null) 
				:
				mInflater.inflate(R.layout.item_chat_sent_message, null);
	}

}
