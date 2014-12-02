package com.farsunset.gmb.component;

import java.util.Date;
import java.util.Timer;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.farsunset.gmb.ui.R;

public class TimingView extends LinearLayout  {

 
	ImageView fHour;
	ImageView lHour;
	ImageView fMinute;
	ImageView lMinute;
	ImageView fSecond;
	ImageView lSecond;
	Context context;
	Timer timer = new Timer();
	Date endTime;
    public TimingView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}
    
    public TimingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}
    
    
    
    private void init() {  
    	 
    	LinearLayout ll1 = new LinearLayout(context);
    	ll1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,3.33f));
    	ll1.setGravity(Gravity.CENTER_HORIZONTAL);
    	ll1.setOrientation(LinearLayout.HORIZONTAL);
    	fHour = new ImageView(context);
    	fHour.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    	fHour.setImageResource(R.drawable.numbers);
    	
    	lHour = new ImageView(context);
    	lHour.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    	lHour.setImageResource(R.drawable.numbers);
    	ll1.addView(fHour);
    	ll1.addView(lHour);
    	
    	this.addView(ll1);
    	
    	
    	
    	LinearLayout ll2 = new LinearLayout(context);
    	ll2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,3.33f));
    	ll2.setGravity(Gravity.CENTER_HORIZONTAL);
    	ll2.setOrientation(LinearLayout.HORIZONTAL);
    	fMinute = new ImageView(context);
    	fMinute.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    	fMinute.setImageResource(R.drawable.numbers);
    	
    	lMinute = new ImageView(context);
    	lMinute.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    	lMinute.setImageResource(R.drawable.numbers);
    	ll2.addView(fMinute);
    	ll2.addView(lMinute);
    	
    	this.addView(ll2);
    	
    	LinearLayout ll3 = new LinearLayout(context);
    	ll3.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,3.33f));
    	ll3.setGravity(Gravity.CENTER_HORIZONTAL);
    	ll3.setOrientation(LinearLayout.HORIZONTAL);
    	fSecond = new ImageView(context);
    	fSecond.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    	fSecond.setImageResource(R.drawable.numbers);
    	
    	lSecond = new ImageView(context);
    	lSecond.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    	lSecond.setImageResource(R.drawable.numbers);
    	ll3.addView(fSecond);
    	ll3.addView(lSecond);
    	
    	this.addView(ll3);
    }
   
    Handler handler=new Handler(){
    	
    	
    	@Override
		public void handleMessage(Message msg) {
    		
    		
    		fHour.getDrawable().setLevel(msg.getData().getInt("fHourLevel"));
    		lHour.getDrawable().setLevel(msg.getData().getInt("lHourLevel"));
    		
    		fMinute.getDrawable().setLevel(msg.getData().getInt("fMinuteLevel"));
    		lMinute.getDrawable().setLevel(msg.getData().getInt("lMinuteLevel"));
    		
    		fSecond.getDrawable().setLevel(msg.getData().getInt("fSecondLevel"));
    		lSecond.getDrawable().setLevel(msg.getData().getInt("lSecondLevel"));
    		 
    	}
    }; 
    
    public class TimeTask extends AsyncTask<String, Void, String> {
    	
    	 
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... params) {
        	
        	if(endTime==null)
        	{
        		return "";
        	}
        	 while(true)
        	 {
        		 
        		 
        		 Message msg = new Message();
        		 Bundle  data = new Bundle ();
        		
        		 long c =( endTime.getTime() - System.currentTimeMillis()) /1000;
        		 long h = c / 3600;
        		 long m = (c %3600) / 60;
        		 long s = (c %3600) %60;
        		 if(h>=10)
        		 {
        			 data.putInt("fHourLevel",(int)h/10);
        			 data.putInt("lHourLevel",(int)h%10);
        			 
        		 }else
        		 {
        			 
        			 
        			 data.putInt("fHourLevel", 0);
        			 data.putInt("lHourLevel", (int)h);
        			 
        			 
        		 }
        		 if(m>=10)
        		 {
        			 
        			 data.putInt("fMinuteLevel", (int)m/10);
        			 data.putInt("lMinuteLevel", (int)m%10);
        			 
        			 
        		 }else
        		 {
        			 data.putInt("fMinuteLevel", 0);
        			 data.putInt("lMinuteLevel",(int)m);
        			 
        			 
        		 }
        		 if(s>=10)
        		 {
        			 
        			 data.putInt("fSecondLevel",  (int)s/10);
        			 data.putInt("lSecondLevel",  (int)s%10);
        			
        			 
        		 }else
        		 {
        			 data.putInt("fSecondLevel", 0);
        			 data.putInt("lSecondLevel", (int) s);
        			 
        			 
        		 }
        		 msg.setData(data);
        		 handler.sendMessage(msg);
        		 try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
        	 }
        	 
        }

        @Override
        protected void onPostExecute(String result) {
        	
        	
        }
    }
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}  

	public void start() {
	
		new TimeTask().execute("");
		
	}  
}
