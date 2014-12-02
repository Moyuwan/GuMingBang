package com.farsunset.gmb.ui;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;

import com.farsunset.gmb.adapter.ProductListViewAdapter;
import com.farsunset.gmb.bean.Product;
import com.farsunset.gmb.component.KeywordsFlow;
import com.farsunset.gmb.component.KeywordsFlow.OnKeywordClickListener;
import com.farsunset.gmb.component.SuperListView;
import com.farsunset.gmb.component.SuperListView.OnRefreshListener;
import com.farsunset.gmb.fragment.PayProductFragment;
import com.farsunset.gmb.net.ProductTask;
import com.farsunset.gmb.ui.base.BaseFragmentActivity;
import com.farsunset.gmb.util.GlobalExceptionListener;


public class TabStockListActivity extends BaseFragmentActivity implements OnClickListener,OnRefreshListener,OnKeywordClickListener{
	SuperListView  listView;
	ProductListViewAdapter listAdapter;
	ArrayList<Product> dataList = new ArrayList<Product>();
	String order = "new";
	int page = 1;
    KeywordsFlow keywordsFlow;
	PayProductFragment payaProductFragment ;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stocklist);
		GlobalExceptionListener.getInstance().init(this);
		initViews();
	} 
	
	void initViews()
	{
		listView = (SuperListView) findViewById(R.id.stockListtView);
		
		keywordsFlow = new KeywordsFlow(this);
		keywordsFlow.setBackgroundResource(R.drawable.red_wall);
		((LinearLayout)listView.getHeader().findViewById(R.id.bannerView)).addView(keywordsFlow);
        keywordsFlow.setOnKeywordClickListener(this);
        keywordsFlow.feedKeywordsFlow( new String[]{"中原高速 601542","武钢股份 200542","贵州茅台 600542","华夏银行 600540","招商银行 600000","浦发银行 600522","中国铝业 600142"});  
        keywordsFlow.go2Show(KeywordsFlow.ANIMATION_IN);
        
        
        
		payaProductFragment = (PayProductFragment) getSupportFragmentManager().findFragmentById(R.id.payProductFragment);
		getSupportFragmentManager().beginTransaction().hide(payaProductFragment).commit();
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				Product p =listAdapter.getItem(index-1);
				
				payaProductFragment.setData(p);
				TabStockListActivity.this.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).show(payaProductFragment).commit();
		
				
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
	        	if(payaProductFragment.getReportFragment()!=null&&!payaProductFragment.getReportFragment().isHidden())
	             {
	            	 getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).hide(payaProductFragment.getReportFragment()).commit();
	            	 return true;
	             }
	             if(!payaProductFragment.isHidden())
	             {
	            	 getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).hide(payaProductFragment).commit();
	            	 return true;
	             }	        }
	        return false;
	    }

	 

	@Override
	public void onKeywordClick(String text) {
		
		String id =text.split(" ")[1];
		
		for(Product p:dataList)
		{
			if(p.stockid.equals(id))
			{
				payaProductFragment.setData(p);
				TabStockListActivity.this.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).show(payaProductFragment).commit();
		       return ;
			}
			
		}
		
	}
	 
	 
	 
	 
}
