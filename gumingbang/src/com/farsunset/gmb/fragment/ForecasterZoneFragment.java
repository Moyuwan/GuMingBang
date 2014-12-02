package com.farsunset.gmb.fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.farsunset.gmb.bean.Forecaster;
import com.farsunset.gmb.component.TimingView;
import com.farsunset.gmb.component.WebImageView;
import com.farsunset.gmb.ui.R;
import com.farsunset.gmb.ui.SellOrderActivity;
import com.farsunset.gmb.ui.base.BaseFragment;

public class ForecasterZoneFragment  extends BaseFragment  implements OnClickListener{

	
	TimingView timingView;
	 @Override
     public void onCreate(Bundle savedInstanceState) {
             // TODO Auto-generated method stub
             super.onCreate(savedInstanceState);
     }
     public View onCreateView(LayoutInflater inflater, ViewGroup container, 
                          Bundle savedInstanceState) {
             View root = inflater.inflate(R.layout.fragment_fzone, container, false);
             
             timingView = (TimingView) root.findViewById(R.id.timingView);
             
             timingView.setEndTime(getNextDate());
             timingView.start();
             
             root.findViewById(R.id.orderButton).setOnClickListener(this);
             return root; 
 }
	public void setData(Forecaster f) {
		    ((TextView) this.getView().findViewById(R.id.name)).setText(f.name);;
	        ((TextView) this.getView().findViewById(R.id.oprtype)).setText(String.valueOf(f.oprtype));;
	        ((TextView) this.getView().findViewById(R.id.gail)).setText(f.oprgail);;
	        ((TextView) this.getView().findViewById(R.id.oprgul)).setText(f.oprgul);
	        ((TextView) this.getView().findViewById(R.id.fans)).setText(f.fans);
	        ((WebImageView) this.getView().findViewById(R.id.headImage)).load(f.image);
	        ((TextView) this.getView().findViewById(R.id.signature)).setText(f.note);
	} 
	
	
	private Date getNextDate()
	{
		
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(now.getTime());
		Calendar calendar = new GregorianCalendar();
		if(now.getHours() > 18 )
		{
			//c.set(now.getYear(), now.getMonth(), now.getDay()+1, 15,0);
			calendar.add(Calendar.DAY_OF_MONTH,1);
			 
		} 
		calendar.set(Calendar.HOUR_OF_DAY, 15);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		now = calendar.getTime();
		
		System.out.println(now.getTime());
		return now;
	}
	@Override
	public void onClick(View v) {
		 switch(v.getId())
		 {
		 
		    
		    case R.id.orderButton:
		    	
		    	 Intent intent = new Intent(this.getActivity(),SellOrderActivity.class);  
				 startActivity(intent);
                 break;
		     
		 }
		
	}
 
	
}
