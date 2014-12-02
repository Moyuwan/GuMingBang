package com.farsunset.gmb.ui;
import java.util.HashMap;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.farsunset.gmb.env.Constant;
import com.farsunset.gmb.net.API;
import com.farsunset.gmb.ui.base.BaseActivity;

public class UserRegisterActivity extends BaseActivity  implements OnClickListener{
	
	EditText account;
	EditText password;
	EditText phonenumber;
	CheckBox checkBoxGuming;
	CheckBox checkBoxForecast;
	Button registerButton;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		account = (EditText) this.findViewById(R.id.account);
		password = (EditText) this.findViewById(R.id.password);
		phonenumber = (EditText) this.findViewById(R.id.phonenumber);
		registerButton = (Button) this.findViewById(R.id.registerButton);
		checkBoxGuming = (CheckBox) this.findViewById(R.id.checkBoxGuming);
		checkBoxForecast = (CheckBox) this.findViewById(R.id.checkBoxForecast);
		registerButton.setOnClickListener(this);
		checkBoxGuming.setOnClickListener(this);
		checkBoxForecast.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		   case R.id.registerButton:
			   if (TextUtils.isEmpty(account.getText())) {
                   Toast.makeText(this, "请输入用户名", Toast.LENGTH_LONG).show();

               } else if (TextUtils.isEmpty(password.getText())) {
                   Toast.makeText(this, "请输入密码", Toast.LENGTH_LONG).show();
               } else if (TextUtils.isEmpty(phonenumber.getText()) && checkBoxForecast.isChecked()){
            	   Toast.makeText(this, "请输入手机号码", Toast.LENGTH_LONG).show();
               }else
               {
            	   new RegisterTask().execute(account.getText().toString(), password.getText().toString(),phonenumber.getText().toString());
               }
               break;
		   case R.id.checkBoxForecast:
			   if(checkBoxForecast.isChecked())
			   {
				   checkBoxGuming.setChecked(false);
			   }
			   break;
			   
		   case R.id.checkBoxGuming:
			   if(checkBoxGuming.isChecked())
			   {
				   checkBoxForecast.setChecked(false);
			   }
			   break;
		}
		
	}
	
	 class RegisterTask extends AsyncTask<String, Void, JSONObject> {
	        @Override
	        protected void onPreExecute() {
	        	UserRegisterActivity.this.showProgressDialog();
	        }

	        @Override
	        protected JSONObject doInBackground(String... params) {
	            try {
	              HashMap<String,String> map = new HashMap<String,String>();
	              map.put("name", params[0]);
	              map.put("psw", params[1]);
	              map.put("phone", params[2]);
                  String data = API.httpPost(Constant.SERVER_URL+"/reg.php", map);
	              return new JSONObject(new String(data));
	            } catch (Exception e) {
	            	e.printStackTrace();
	                return null;
	            }
	        }

	        @Override
	        protected void onPostExecute(JSONObject result) {
	        	UserRegisterActivity.this.hideProgressDialog();
	        }
	    }
	 
}
