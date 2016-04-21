package com.mycourse.adaper;

import java.util.Arrays;
import java.util.List;
import com.example.mycourse.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 
 * @author Administrator
 * 自定义adapter，侧滑界面的
 *
 */
public class SlideAdaper extends BaseAdapter {

	private int iconId[] = {
		R.drawable.biz_navigation_tab_news,
		R.drawable.biz_navigation_tab_pics,
		R.drawable.biz_navigation_tab_read,
		R.drawable.biz_navigation_tab_ties,
		R.drawable.biz_navigation_tab_voted,
		R.drawable.biz_navigation_tab_voted
	};
	
	private List<String> ListItems = Arrays  
            .asList("课表", "云笔记", "小秘书", "论坛","设置", "退出登陆");
	
	private Context context;
	private LayoutInflater layoutInflater;
	
	public SlideAdaper(Context context) {
		super();
		this.context = context;
		this.layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		
		return 6;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null)
        {    
            convertView = layoutInflater.inflate(R.layout.slide_item, null);
        }
        //得到条目中的子组件
       ImageView icon = (ImageView) convertView.findViewById(R.id.item_icon);
       TextView name = (TextView) convertView.findViewById(R.id.item_name);
        
       name.setText(ListItems.get(position));
       icon.setImageResource(iconId[position]);
        
       return convertView;
	}

}
