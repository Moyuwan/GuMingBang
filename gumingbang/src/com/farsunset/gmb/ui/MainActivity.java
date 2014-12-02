package com.farsunset.gmb.ui;
 
 import android.app.TabActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
 
 public class MainActivity extends TabActivity implements OnCheckedChangeListener{
     
     private TabHost mHost;
     private RadioGroup radioderGroup;
     int f=0;
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_home);
         //实例化TabHost
         mHost=this.getTabHost();
         
       //添加选项卡
         mHost.addTab(mHost.newTabSpec("ONE").setIndicator("ONE")
                     .setContent(new Intent(this,TabWeekStarActivity.class)));
         mHost.addTab(mHost.newTabSpec("TWO").setIndicator("TWO")
                 .setContent(new Intent(this,TabStockListActivity.class)));
         mHost.addTab(mHost.newTabSpec("THREE").setIndicator("THREE")
                 .setContent(new Intent(this,TabForecasterActivity.class)));
         mHost.addTab(mHost.newTabSpec("FOUR").setIndicator("FOUR")
                 .setContent(new Intent(this,TabUserZoneActivity.class)));
         mHost.addTab(mHost.newTabSpec("FIVE").setIndicator("FIVE")
                 .setContent(new Intent(this,TabAboutAcityvity.class)));
         radioderGroup = (RadioGroup) findViewById(R.id.main_radio);
         radioderGroup.setOnCheckedChangeListener(this);
     }
      
     public void onCheckedChanged(RadioGroup group, int checkedId) {
         switch(checkedId){
         case R.id.radio_weekstar:
             mHost.setCurrentTabByTag("ONE");
             //MediaPlayer.create(this, R.raw.ka).start();
             break;
         case R.id.radio_stock:
             mHost.setCurrentTabByTag("TWO");
             //MediaPlayer.create(this, R.raw.ka).start();
             break;
         case R.id.radio_forecaster:
             mHost.setCurrentTabByTag("THREE");
             //MediaPlayer.create(this, R.raw.ka).start();
             break;
             
         case R.id.radio_uzone:
             mHost.setCurrentTabByTag("FOUR");
             //MediaPlayer.create(this, R.raw.ka).start();
             break;
             
         case R.id.radio_about:
             mHost.setCurrentTabByTag("FIVE");
             //MediaPlayer.create(this, R.raw.ka).start();
             break;
         }
         
         
     }
     
     
     @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if (keyCode == KeyEvent.KEYCODE_BACK) {
	            if(f==0)
	            {
	               View toaskView=LayoutInflater.from(this).inflate(R.layout.toask_view, null);
	     		   Toast toast=Toast.makeText(this,null,1000);
	     		   toast.setView(toaskView);
	     		   ((TextView)toaskView.findViewById(R.id.toaskMessage)).setText("再按一次退出");
	     		   toast.show();
	               f++;
	               new Thread(){
	            	   
	            	   public void run()
	            	   {
	            		   try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            		   f=0;
	            	   }
	               }.start();
	            	return true;
	            }else
	            {
	            	this.finish();
	            }
	        }
	        return false;
	    }
 }