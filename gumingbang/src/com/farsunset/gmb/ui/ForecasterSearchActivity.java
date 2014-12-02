package com.farsunset.gmb.ui;


import java.util.ArrayList;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.farsunset.gmb.adapter.ForecasterListViewAdapter;
import com.farsunset.gmb.bean.Forecaster;
import com.farsunset.gmb.fragment.ForecasterFragment;
import com.farsunset.gmb.ui.base.BaseFragmentActivity;
import com.farsunset.gmb.util.GlobalExceptionListener;


public class ForecasterSearchActivity extends BaseFragmentActivity implements OnClickListener{
	ListView  listView;
	ForecasterListViewAdapter listAdapter;
	ArrayList<Forecaster> dataList = new ArrayList<Forecaster>();
	ForecasterFragment forecasterFragment ;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forecaster_search);
		GlobalExceptionListener.getInstance().init(this);
		initViews();
	} 
	
	void initViews()
	{
		listView = (ListView) findViewById(R.id.matterListtView);
		forecasterFragment = (ForecasterFragment) getSupportFragmentManager().findFragmentById(R.id.forecasterFragment);
		getSupportFragmentManager().beginTransaction().hide(forecasterFragment).commit();
     
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				Forecaster p =listAdapter.getItem(index-1);
				forecasterFragment.setData(p);
				ForecasterSearchActivity.this.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).show(forecasterFragment).commit();
			}});
	    
		listAdapter = new ForecasterListViewAdapter(this,dataList);
		listView.setAdapter(listAdapter);
		
		this.findViewById(R.id.button_return).setOnClickListener(this);
		((EditText)this.findViewById(R.id.TITLE_EDIT_TEXT)).setHint("请输入预测人昵称");
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
