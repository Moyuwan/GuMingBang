package com.farsunset.gmb.ui.base;



import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.farsunset.gmb.ui.R;

public class BaseActivity extends Activity{
	
	
	private ProgressDialog progressDialog;  
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	
	
	public void showProgressDialog()
	{
		if(progressDialog==null)
		{
			
			 progressDialog = ProgressDialog.show(this, "Loading...", "正在处理...", true, false);
		}
	
		progressDialog.show();
		
	}
	
	public void hideProgressDialog()
	{
	
		progressDialog.dismiss();
		
	}
	
	public void showToask(String hint){
		
		   View toaskView=LayoutInflater.from(this).inflate(R.layout.toask_view, null);
		   Toast toast=Toast.makeText(this,null,1000);
		   toast.setView(toaskView);
		   ((TextView)toaskView.findViewById(R.id.toaskMessage)).setText(hint);
		   toast.show();
	}
}
