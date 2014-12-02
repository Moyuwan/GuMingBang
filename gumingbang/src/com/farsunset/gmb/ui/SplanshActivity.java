package com.farsunset.gmb.ui;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.farsunset.gmb.bean.User;
import com.farsunset.gmb.util.SqlliteHander;

public class SplanshActivity extends Activity{
	
	boolean initComplete = false;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		final View view = View.inflate(this, R.layout.activity_splansh, null);
		setContentView(view);
		
		AlphaAnimation aa = new AlphaAnimation(0.5f,1.0f);
		aa.setDuration(1000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {

			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				 
				checkUserInfo();
			}

			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private void checkUserInfo()
	{
		User info = SqlliteHander.getTnstantiation(this).queryUser();
		if(info == null)
		{
			Intent intent = new Intent(this,UserLoginActivity.class);  
			startActivity(intent);
			this.finish();
			
		}else
		{
			Intent intent = new Intent(this,MainActivity.class);  
			startActivity(intent);
			this.finish();
		}
 		
 	}
    
}
