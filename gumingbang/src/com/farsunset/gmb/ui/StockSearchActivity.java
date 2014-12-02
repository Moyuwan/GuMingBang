package com.farsunset.gmb.ui;


import java.util.ArrayList;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.farsunset.gmb.adapter.ProductListViewAdapter;
import com.farsunset.gmb.bean.Product;
import com.farsunset.gmb.fragment.PayProductFragment;
import com.farsunset.gmb.ui.base.BaseFragmentActivity;
import com.farsunset.gmb.util.GlobalExceptionListener;


public class StockSearchActivity extends BaseFragmentActivity implements OnClickListener{
	ListView  listView;
	ProductListViewAdapter listAdapter;
	ArrayList<Product> dataList = new ArrayList<Product>();
	PayProductFragment payProductFragment ;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock_search);
		GlobalExceptionListener.getInstance().init(this);
		initViews();
	} 
	
	void initViews()
	{
		listView = (ListView) findViewById(R.id.matterListtView);
		payProductFragment = (PayProductFragment) getSupportFragmentManager().findFragmentById(R.id.payProductFragment);
		getSupportFragmentManager().beginTransaction().hide(payProductFragment).commit();
     
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				Product p =listAdapter.getItem(index-1);
				payProductFragment.setData(p);
				StockSearchActivity.this.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).show(payProductFragment).commit();
			}});
	    
		listAdapter = new ProductListViewAdapter(this,dataList);
		listView.setAdapter(listAdapter);
		
		this.findViewById(R.id.button_return).setOnClickListener(this);
		((EditText)this.findViewById(R.id.TITLE_EDIT_TEXT)).setHint("请输入股票代码或名称");
		
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
	        	return true;
	        }
	        return false;
	    }

	 
 
	 
	 
}
