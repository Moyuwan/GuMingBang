package com.farsunset.gmb.ui;



import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.farsunset.gmb.adapter.ViewPaperAdapter;
import com.farsunset.gmb.ui.base.BaseActivity;

public class TabAboutAcityvity extends BaseActivity  implements OnClickListener{
	
	private ViewPager pager;
	List<View> pageViews;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		initViews();
		 
	}
	private void initViews() {
		pageViews = new ArrayList<View>();
		View cells = LayoutInflater.from(this).inflate(R.layout.about_cells, null);
		pageViews.add(cells.findViewById(R.id.aboutView));
		pageViews.add(cells.findViewById(R.id.rateView));
		pageViews.add(cells.findViewById(R.id.payTipView));
		pager  = (ViewPager)this.findViewById(R.id.view_pager);
		pager.removeAllViews();
		pager.setAdapter( new ViewPaperAdapter(pageViews));
		pager.setOnPageChangeListener(new OnPageChangeListener(){
			@Override
			public void onPageScrollStateChanged(int arg0) {}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			@Override
			public void onPageSelected(int index) {
				 if(index==0)
				 {
					 onClick(R.id.tab_about_gmb,false);
				 }
				 if(index==1)
				 {
					 onClick(R.id.tab_rate,false);
				 }
				 if(index==2)
				 {
					 onClick(R.id.tab_pay_tip,false);
				 }
				
			}});
		
		pager.setOffscreenPageLimit(3);
		onClick(R.id.tab_about_gmb,false);
		
		 findViewById(R.id.tab_about_gmb).setOnClickListener(this);
		 findViewById(R.id.tab_rate).setOnClickListener(this);
		 findViewById(R.id.tab_pay_tip).setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		
		onClick(v.getId(),true);
	}
	
   public void onClick(int id,boolean f)
   {
	   switch(id)
		{
		   case R.id.tab_about_gmb:
			   findViewById(R.id.tab_about_gmb).setSelected(true);
			   findViewById(R.id.tab_rate).setSelected(false);
			   findViewById(R.id.tab_pay_tip).setSelected(false);
			   if(f){
				   pager.setCurrentItem(0);
			   }
			   break;
		   case R.id.tab_rate:
			   findViewById(R.id.tab_rate).setSelected(true);
			   findViewById(R.id.tab_about_gmb).setSelected(false);
			   findViewById(R.id.tab_pay_tip).setSelected(false);
			   if(f){
				   pager.setCurrentItem(1);
			   }
			   break;
		   case R.id.tab_pay_tip:
			   findViewById(R.id.tab_pay_tip).setSelected(true);
			   findViewById(R.id.tab_rate).setSelected(false);
			   findViewById(R.id.tab_about_gmb).setSelected(false);
			   if(f){
				   pager.setCurrentItem(2);
			   }
			   break;
		   
		 
		}
	   
   }
}
