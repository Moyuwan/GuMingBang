package com.farsunset.gmb.ui;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;

import com.farsunset.gmb.adapter.ForecasterListViewAdapter;
import com.farsunset.gmb.bean.Forecaster;
import com.farsunset.gmb.bean.Product;
import com.farsunset.gmb.component.KeywordsFlow;
import com.farsunset.gmb.component.KeywordsFlow.OnKeywordClickListener;
import com.farsunset.gmb.component.SuperListView;
import com.farsunset.gmb.component.SuperListView.OnRefreshListener;
import com.farsunset.gmb.fragment.ForecasterFragment;
import com.farsunset.gmb.fragment.PayProductFragment;
import com.farsunset.gmb.ui.base.BaseFragmentActivity;
import com.farsunset.gmb.util.GlobalExceptionListener;


public class TabForecasterActivity extends BaseFragmentActivity implements OnClickListener,OnRefreshListener,OnKeywordClickListener{
	SuperListView  listView;
	ForecasterListViewAdapter listAdapter;
	ArrayList<Forecaster> dataList = new ArrayList<Forecaster>();
	String order = "new";
	int page = 1;
	
	ForecasterFragment forecasterFragment ;
	KeywordsFlow keywordsFlow;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forecaster);
		GlobalExceptionListener.getInstance().init(this);
		initViews();
	} 
	
	void initViews()
	{
		listView = (SuperListView) findViewById(R.id.forecasterListView);
		
		keywordsFlow = new KeywordsFlow(this);
		keywordsFlow.setBackgroundResource(R.drawable.red_wall);
		((LinearLayout)listView.getHeader().findViewById(R.id.bannerView)).addView(keywordsFlow);
        keywordsFlow.setOnKeywordClickListener(this);
        keywordsFlow.feedKeywordsFlow( new String[]{"非股莫属","东北十三狼","股道中人","股票达人","爱股老张","真我本色","西南股家","股神欧阳"});  
        keywordsFlow.go2Show(KeywordsFlow.ANIMATION_IN);
        
        
		forecasterFragment = (ForecasterFragment) TabForecasterActivity.this.getSupportFragmentManager().findFragmentById(R.id.forecasterFragment);

	     this.getSupportFragmentManager().beginTransaction();
	    this.getSupportFragmentManager().beginTransaction().hide(forecasterFragment).commit();
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				Forecaster p =listAdapter.getItem(index-1);
				
				forecasterFragment.setData(p);
				TabForecasterActivity.this.getSupportFragmentManager().beginTransaction()
				.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).show(forecasterFragment).commit();
				
			}});
	    
		listAdapter = new ForecasterListViewAdapter(this,dataList);
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
			
			Intent intent = new Intent(this,ForecasterSearchActivity.class);  
			startActivity(intent);
			break;
		}
		
	}
	
	@Override
	public void onRefresh() {
		
		new LoadTask().execute("");
		
	}

	@Override
	public void onShowNextPage() {
		// TODO Auto-generated method stub
		
	}
 
	 @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if (keyCode == KeyEvent.KEYCODE_BACK) {
	             if(forecasterFragment.getPayaProductFragment()!=null)
	             {
	            	 
	            	 if(forecasterFragment.getPayaProductFragment().getReportFragment()!=null
	            			 &&!forecasterFragment.getPayaProductFragment().getReportFragment().isHidden())
	            	 {
		            	 this.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).hide(forecasterFragment.getPayaProductFragment().getReportFragment()).commit();
		            	 return true;
	            	 }
	            	 
	            	 if(forecasterFragment.getPayaProductFragment()!=null
	            			 &&!forecasterFragment.getPayaProductFragment().isHidden())
	            	 {
		            	 this.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).hide(forecasterFragment.getPayaProductFragment()).commit();
		            	 return true;
	            	 }
	            	 
	             }
	             if(!forecasterFragment.isHidden())
	             {
	            	 this.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).hide(forecasterFragment).commit();
	            	 return true;
	             }
	        }
	        return false;
	    }
	 
	 
	class LoadTask extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            try {
                 HashMap<String,String> map = new HashMap<String,String>();
                // map.put("name", params[0]);
	            // map.put("psw", params[1]);
                // String data = API.httpPost(Constant.SERVER_URL+"/login.php", map);
                return new JSONObject("{a:1,b:2}");
            } catch (Exception e) {
            	e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONObject result) {
        	
        	listView.refreshComplete();
        	
        	listAdapter.getDataList().clear();
        	
        	for(int i=0;i<5;i++)
        	{
        		Forecaster p = new Forecaster();
        		p.name ="股道中人";
        		p.oprgail ="87%";
        		p.oprgul ="9年";
        		p.oprtype ="激进型";
        		p.uid ="1";
        		p.fans="22344";
        		p.image="http://t2.qlogo.cn/mbloghead/2143270a579c0b5f1436/120";
        		p.note ="股市有风险，入市需谨慎!";
        		listAdapter.getDataList().add(p);
        	}
        	listAdapter.notifyDataSetChanged();
        }
    }


	@Override
	public void onKeywordClick(String text) {
		
		for(Forecaster f:dataList)
		{
			if(f.name.equals(text))
			{
				forecasterFragment.setData(f);
			    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).show(forecasterFragment).commit();
		       return ;
			}
			
		}
	}
	 
}
