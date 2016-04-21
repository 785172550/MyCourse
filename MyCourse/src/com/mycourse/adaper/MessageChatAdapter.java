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


	//�ı�  ��������
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
		
		//�ı�����
		final ImageView iv_fail_resend = ViewHolder.get(convertView, R.id.iv_fail_resend);//ʧ���ط�
		final TextView tv_send_status = ViewHolder.get(convertView, R.id.tv_send_status);//����״̬
		TextView tv_time = ViewHolder.get(convertView, R.id.tv_time);       // ����ʱ��
		TextView tv_message = ViewHolder.get(convertView, R.id.tv_message); // ���͵���Ϣ
		final ProgressBar progress_load = ViewHolder.get(convertView, R.id.progress_load);//������
		
		tv_time.setVisibility(View.INVISIBLE);
		
		if(getItemViewType(position)==TYPE_SEND_TXT)
		{
			progress_load.setVisibility(View.INVISIBLE);
			iv_fail_resend.setVisibility(View.INVISIBLE);
			tv_send_status.setVisibility(View.VISIBLE);
			tv_send_status.setText("�ѷ���");
		}
	
		try {
			SpannableString spannableString = FaceTextUtils
					.toSpannableString(mContext, item);
			tv_message.setText(spannableString);
		} catch (Exception e) {
		}		
		return convertView;
	}
	
	//�ж� ������������
	
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
