package com.mycourse.ui;


import android.os.Bundle;
import android.view.View;
import com.example.mycourse.R;
import com.mycourse.adaper.LuntanAdapter;
import com.mycourse.base.BaseActivity;
import com.mycourse.view.HeaderLayout.onRightImageButtonClickListener;
import com.mycourse.view.xlist.XListView;
import com.mycourse.view.xlist.XListView.IXListViewListener;

public class Luntan extends BaseActivity implements IXListViewListener {

	//XListView mListView;
	//LuntanAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_luntan);
		//mListView = (XListView) findViewById(R.id.mListView);
		initview();
	}
	private void initview() {
		
		
		initTopBarForBoth("论坛空间", R.drawable.base_action_bar_add_bg_n, new onRightImageButtonClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent writeIntent = new Intent(Luntan.this,);
				
			}
		});
		
		//initxlistview();
		
	}
	/*public void initxlistview()
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
		
	}*/
	
	/**
	 * 界面刷新
	 * @Title: initOrRefresh
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initOrRefresh()
	{
		
	}
	
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		
	}
}
