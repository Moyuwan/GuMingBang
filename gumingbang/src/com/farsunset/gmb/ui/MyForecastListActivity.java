package com.farsunset.gmb.ui;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.farsunset.gmb.adapter.ProductListViewAdapter;
import com.farsunset.gmb.bean.Product;
import com.farsunset.gmb.component.SuperListView;
import com.farsunset.gmb.component.SuperListView.OnRefreshListener;
import com.farsunset.gmb.component.WebImageView;
import com.farsunset.gmb.fragment.PayProductFragment;
import com.farsunset.gmb.net.ProductTask;
import com.farsunset.gmb.ui.base.BaseFragmentActivity;
import com.farsunset.gmb.util.GlobalExceptionListener;

public class MyForecastListActivity extends BaseFragmentActivity implements OnClickListener,OnRefreshListener{
	SuperListView  listView;
	ProductListViewAdapter listAdapter;
	ArrayList<Product> dataList = new ArrayList<Product>();
	String order = "new";
	int page = 1;
	WebImageView webImageView;
	PayProductFragment payProductFragment ;
	//FctReportFragment reportFragment ;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_forecastlist);
		GlobalExceptionListener.getInstance().init(this);
		initViews();
	} 
	
	void initViews()
	{
		listView = (SuperListView) findViewById(R.id.matterListtView);
		
		payProductFragment = (PayProductFragment) MyForecastListActivity.this.getSupportFragmentManager().findFragmentById(R.id.payProductFragment);
		//reportFragment = (FctReportFragment) WeekStarActivity.this.getSupportFragmentManager().findFragmentById(R.id.reportFragment);
		MyForecastListActivity.this.getSupportFragmentManager().beginTransaction().hide(payProductFragment).commit();
		//WeekStarActivity.this.getSupportFragmentManager().beginTransaction().hide(reportFragment).commit();
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				Product p =listAdapter.getItem(index-1);
				
				payProductFragment.setData(p);
				MyForecastListActivity.this.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).show(payProductFragment).commit();
				
			}});
	    
		listAdapter = new ProductListViewAdapter(this,dataList);
		listView.setAdapter(listAdapter);
		listView.setOnRefreshListener(this);
		listView.doRefresh();
		
		this.getParent().findViewById(R.id.RIGHT_BUTTON).setOnClickListener(this);
	}

	 
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
            case R.id.RIGHT_BUTTON:
			Intent intent = new Intent(this,StockSearchActivity.class);  
			startActivity(intent);
			break;
		  
		}
		
	}
	
	@Override
	public void onRefresh() {
		
		new ProductTask(listView).execute("");
		
	}

	@Override
	public void onShowNextPage() {
		// TODO Auto-generated method stub
		
	}
 
	 @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if (keyCode == KeyEvent.KEYCODE_BACK) {
	            
	             if(payProductFragment.getReportFragment()!=null&&!payProductFragment.getReportFragment().isHidden())
	             {
	            	 getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).hide(payProductFragment.getReportFragment()).commit();
	            	 return true;
	             }
	             if(!payProductFragment.isHidden())
	             {
	            	 getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).hide(payProductFragment).commit();
	            	 return true;
	             }
	        }
	        return false;
	    }
	 
	 
	

 
}
