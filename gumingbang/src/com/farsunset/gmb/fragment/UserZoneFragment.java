package com.farsunset.gmb.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.farsunset.gmb.adapter.OptionalListViewAdapter;
import com.farsunset.gmb.bean.Forecaster;
import com.farsunset.gmb.component.SuperListView;
import com.farsunset.gmb.component.SuperListView.OnRefreshListener;
import com.farsunset.gmb.component.WebImageView;
import com.farsunset.gmb.net.OptionalStockTask;
import com.farsunset.gmb.ui.AddOptionalActivity;
import com.farsunset.gmb.ui.BuyOrderActivity;
import com.farsunset.gmb.ui.R;
import com.farsunset.gmb.ui.StockSearchActivity;
import com.farsunset.gmb.ui.base.BaseFragment;

public class UserZoneFragment  extends BaseFragment implements OnClickListener,OnRefreshListener{

	
	 SuperListView  listView;
	 OptionalListViewAdapter listAdapter;
	 ArrayList<HashMap<String,String>> dataList = new ArrayList<HashMap<String,String>>();
	 
	 PaymentFragment paymentFragment ;
	 View header;
	 @Override
     public void onCreate(Bundle savedInstanceState) {
             // TODO Auto-generated method stub
             super.onCreate(savedInstanceState);
     }
     public View onCreateView(LayoutInflater inflater, ViewGroup container, 
                          Bundle savedInstanceState) {
             View root = inflater.inflate(R.layout.fragment_uzone, container, false);
             
             header = inflater.inflate(R.layout.header_uzone, container, false);
             
             
             paymentFragment = (PaymentFragment) this.getFragmentManager().findFragmentById(R.id.paymentFragment);
             this.getFragmentManager().beginTransaction().hide(paymentFragment).commit();
             
             
             listView = (SuperListView) root.findViewById(R.id.productListView);
             ((LinearLayout)listView.getHeader().findViewById(R.id.bannerView)).addView(header);
             listAdapter = new OptionalListViewAdapter(this.getActivity(),dataList);
     		 listView.setAdapter(listAdapter);
     		 listView.setOnRefreshListener(this);
     		 listView.doRefresh();
     		 
     		 header.findViewById(R.id.paymentButton).setOnClickListener(this);
     		 header.findViewById(R.id.optionalButton).setOnClickListener(this);
     		 header.findViewById(R.id.orderButton).setOnClickListener(this);
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
	
	
	
	@Override
	public void onRefresh() {
	   
		new OptionalStockTask(listView).execute("");
	}
	@Override
	public void onShowNextPage() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View v) {
		 switch(v.getId())
		 {
		 
		    case R.id.paymentButton:
				 getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).show(paymentFragment).commit();
                 break;
		    case R.id.orderButton:
		    	
		    	 Intent intent = new Intent(this.getActivity(),BuyOrderActivity.class);  
				 startActivity(intent);
                 break;
		    case R.id.optionalButton:
		    	 Intent intent1 = new Intent(this.getActivity(),AddOptionalActivity.class);  
				 startActivity(intent1);
                 break;
		 }
		
	}
	 
}
