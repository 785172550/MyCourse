package com.mycourse.adaper;

import java.util.List;
import java.util.zip.Inflater;

import com.example.mycourse.R;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LuntanAdapter extends BaseListAdapter<Message> {

	public LuntanAdapter(Context context, List<Message> list) {
		super(context, list);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = mInflater.inflate(R.layout.ai_item, null);
		return convertView;
	}

}
