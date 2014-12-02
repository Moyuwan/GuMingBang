package com.farsunset.gmb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.farsunset.gmb.bean.Product;
import com.farsunset.gmb.ui.R;
import com.farsunset.gmb.ui.SellOrderActivity;
import com.farsunset.gmb.ui.base.BaseFragment;

public class PayProductFragment  extends BaseFragment  implements OnClickListener{

	
	 FctReportFragment reportFragment ;
	 @Override
     public void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
     }
     public View onCreateView(LayoutInflater inflater, ViewGroup container, 
                          Bundle savedInstanceState) {
             View root = inflater.inflate(R.layout.fragment_pay_product, container, false);
             reportFragment = (FctReportFragment) this.getFragmentManager().findFragmentById(R.id.reportFragment);
             this.getFragmentManager().beginTransaction().hide(reportFragment).commit();
		    // super.on
            		// reportFragment = (FctReportFragment) WeekStarActivity.this.getSupportFragmentManager().findFragmentById(R.id.reportFragment);
             root.findViewById(R.id.paybutton).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 
					//if(fctReportListener!=null)
					//fctReportListener.doShowReportFragment();
					PayProductFragment.this.getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).show(reportFragment).commit();

				}
			});
             return root; 
 }
	public void setData(Product p) {
		    ((TextView) this.getView().findViewById(R.id.name)).setText(p.name);;
	        ((TextView) this.getView().findViewById(R.id.forecaster)).setText(String.valueOf(p.oprname));;
	        ((TextView) this.getView().findViewById(R.id.gail)).setText(p.oprgail);;
	        ((TextView) this.getView().findViewById(R.id.price)).setText(p.price);
	        ((TextView) this.getView().findViewById(R.id.payNum)).setText(p.sale);
	}
	public FctReportFragment getReportFragment() {
		return reportFragment;
	}
	@Override
	public void onClick(View v) {
		 switch(v.getId())
		 {
		    
		    
		 }
		
	}

	
	
 
}
