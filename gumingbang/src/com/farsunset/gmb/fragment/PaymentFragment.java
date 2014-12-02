package com.farsunset.gmb.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.farsunset.gmb.bean.Forecaster;
import com.farsunset.gmb.component.WebImageView;
import com.farsunset.gmb.ui.R;
import com.farsunset.gmb.ui.base.BaseFragment;

public class PaymentFragment  extends BaseFragment  implements OnClickListener{

	
	
	 @Override
     public void onCreate(Bundle savedInstanceState) {
             // TODO Auto-generated method stub
             super.onCreate(savedInstanceState);
     }
     public View onCreateView(LayoutInflater inflater, ViewGroup container, 
                          Bundle savedInstanceState) {
             View root = inflater.inflate(R.layout.fragment_payment, container, false);
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
	public void onClick(View v) {
		 switch(v.getId())
		 {
		    
		    
		 }
	}
	 
}
