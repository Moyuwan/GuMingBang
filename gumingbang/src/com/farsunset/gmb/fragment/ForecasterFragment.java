package com.farsunset.gmb.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.farsunset.gmb.adapter.ProductListViewAdapter;
import com.farsunset.gmb.bean.Forecaster;
import com.farsunset.gmb.bean.Product;
import com.farsunset.gmb.component.SuperListView;
import com.farsunset.gmb.component.SuperListView.OnRefreshListener;
import com.farsunset.gmb.component.WebImageView;
import com.farsunset.gmb.net.ProductTask;
import com.farsunset.gmb.ui.R;
import com.farsunset.gmb.ui.base.BaseFragment;

public class ForecasterFragment  extends BaseFragment implements OnClickListener,OnRefreshListener{

	
	 SuperListView  listView;
	 ProductListViewAdapter listAdapter;
	 ArrayList<Product> dataList = new ArrayList<Product>();
	 PayProductFragment payProductFragment ;
	 @Override
     public void onCreate(Bundle savedInstanceState) {
             // TODO Auto-generated method stub
             super.onCreate(savedInstanceState);
     }
     public View onCreateView(LayoutInflater inflater, ViewGroup container, 
                          Bundle savedInstanceState) {
             View root = inflater.inflate(R.layout.fragment_forecaster, container, false);
             
             payProductFragment = (PayProductFragment) this.getFragmentManager().findFragmentById(R.id.payProductFragment);
             this.getFragmentManager().beginTransaction().hide(payProductFragment).commit();
             
             
             listView = (SuperListView) root.findViewById(R.id.productListView);
             
             View headerView = inflater.inflate(R.layout.header_forecaster, container, false);
             ((LinearLayout)listView.getHeader().findViewById(R.id.head_reloadLayout)).addView(headerView);
             
             listView = (SuperListView) root.findViewById(R.id.productListView);
             listAdapter = new ProductListViewAdapter(this.getActivity(),dataList);
     		 listView.setAdapter(listAdapter);
     		 
     		  listView.setOnItemClickListener(new OnItemClickListener(){

    			@Override
    			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
    					long arg3) {
    				Product p =listAdapter.getItem(index-1);
    				
    				payProductFragment.setData(p);
    				ForecasterFragment.this.getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).show(payProductFragment).commit();
    		
    				
    			}});
     		
     		
     		 listView.setOnRefreshListener(this);
     		 listView.doRefresh();
     		 
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
	   
		new ProductTask(listView).execute("");
	}
	@Override
	public void onShowNextPage() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View v) {
		 switch(v.getId())
		 {
		    
		    
		 }
	} 
	public PayProductFragment getPayaProductFragment() {
		return payProductFragment;
	}
	 
	
	
}
