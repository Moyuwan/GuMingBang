package com.farsunset.gmb.ui;


import java.util.HashMap;
import java.util.Random;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.farsunset.gmb.component.SuperListView.OnRefreshListener;
import com.farsunset.gmb.fragment.ForecasterZoneFragment;
import com.farsunset.gmb.fragment.PayProductFragment;
import com.farsunset.gmb.fragment.UserZoneFragment;
import com.farsunset.gmb.ui.base.BaseFragmentActivity;
import com.farsunset.gmb.util.GlobalExceptionListener;


public class TabUserZoneActivity extends BaseFragmentActivity implements OnClickListener,OnRefreshListener{
	
	PayProductFragment payProductFragment ;
	ForecasterZoneFragment fzoneFragment ;
	UserZoneFragment uzoneFragment ;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uzone);
		GlobalExceptionListener.getInstance().init(this);
		initViews();
	} 
	
	void initViews()
	{
		payProductFragment = (PayProductFragment) TabUserZoneActivity.this.getSupportFragmentManager().findFragmentById(R.id.payProductFragment);
		fzoneFragment = (ForecasterZoneFragment) TabUserZoneActivity.this.getSupportFragmentManager().findFragmentById(R.id.fzoneFragment);
		uzoneFragment = (UserZoneFragment) TabUserZoneActivity.this.getSupportFragmentManager().findFragmentById(R.id.uzoneFragment);

		//ft.add(R.id.payProductFragment, payProductFragment).commit();
		//ft.add(R.id.forecasterFragment, forecasterFragment).commit();
		 
	    this.getSupportFragmentManager().beginTransaction().hide(fzoneFragment).commit();
	    this.getSupportFragmentManager().beginTransaction().hide(payProductFragment).commit();
		 
	}

	 
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		   
		    
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
	            /* if(!payProductFragment.isHidden())
	             {
	            	 this.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).hide(payProductFragment).commit();
	            	 return true;
	             }*/
	             if(!fzoneFragment.isHidden())
	             {
	            	 this.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).hide(fzoneFragment).commit();
	            	 this.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).show(uzoneFragment).commit();

	            	 return true;
	             }
	             if(!uzoneFragment.isHidden())
	             {
	            	 this.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).hide(uzoneFragment).commit();
	            	 this.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).show(fzoneFragment).commit();

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
        	
         
        }
    }
	 
}
