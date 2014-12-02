package com.farsunset.gmb.ui;


import java.util.ArrayList;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.farsunset.gmb.adapter.ProductListViewAdapter;
import com.farsunset.gmb.bean.Product;
import com.farsunset.gmb.fragment.PayProductFragment;
import com.farsunset.gmb.ui.base.BaseFragmentActivity;
import com.farsunset.gmb.util.GlobalExceptionListener;


public class SellOrderActivity extends BaseFragmentActivity implements OnClickListener{
	ListView  listView;
	ProductListViewAdapter listAdapter;
	ArrayList<Product> dataList = new ArrayList<Product>();
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sell_order);
		GlobalExceptionListener.getInstance().init(this);
		initViews();
	} 
	
	void initViews()
	{
		listView = (ListView) findViewById(R.id.matterListtView);
     
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				Product p =listAdapter.getItem(index-1);
			}});
	    
		listAdapter = new ProductListViewAdapter(this,dataList);
		listView.setAdapter(listAdapter);
		
		this.findViewById(R.id.button_return).setOnClickListener(this);
		
	}

	 
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		   
		  case R.id.button_return:
			  this.finish();
			  break;
		    
		}
		
	}
	
	 

	 
	 @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if (keyCode == KeyEvent.KEYCODE_BACK) {
	        	
	        	this.finish();
	        }
	        return false;
	    }

	 
 
	 
	 
}
