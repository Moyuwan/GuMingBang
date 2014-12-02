package com.farsunset.gmb.ui;
import java.util.HashMap;

import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.farsunset.gmb.bean.User;
import com.farsunset.gmb.ui.base.BaseActivity;
import com.farsunset.gmb.util.SqlliteHander;
public class UserLoginActivity extends BaseActivity implements OnClickListener{
	
	EditText account;
	EditText password;
	Button loginButton;
	CheckBox showPassword;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		account = (EditText) this.findViewById(R.id.account);
		password = (EditText) this.findViewById(R.id.password);
		loginButton = (Button) this.findViewById(R.id.loginButton);
		showPassword = (CheckBox) this.findViewById(R.id.changePwd);
		loginButton.setOnClickListener(this);
		this.findViewById(R.id.freeReg).setOnClickListener(this);
		this.findViewById(R.id.changePwd).setOnClickListener(this);
	}
	

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		   case R.id.loginButton:
			   if (TextUtils.isEmpty(account.getText())) {
                   Toast.makeText(this, "请输入用户名", Toast.LENGTH_LONG).show();

               } else if (TextUtils.isEmpty(password.getText())) {
                   Toast.makeText(this, "请输入密码", Toast.LENGTH_LONG).show();
               } else {
                   new LoginTask().execute(password.getText().toString(), password.getText().toString());
               }
               break;
		   case R.id.freeReg:
			   Intent intent = new Intent(this,UserRegisterActivity.class);  
			   startActivity(intent);
			   break;
			   
		   case R.id.changePwd:
			    if(showPassword.isChecked())
			    {
			    	password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
			    }else
			    {
			    	password.setTransformationMethod(PasswordTransformationMethod.getInstance());
			    }
			   break;
		}
		
	}
	 
	 
	
	 class LoginTask extends AsyncTask<String, Void, JSONObject> {
	        @Override
	        protected void onPreExecute() {
	        	UserLoginActivity.this.showProgressDialog();
	        }

	        @Override
	        protected JSONObject doInBackground(String... params) {
	            try {
	                 HashMap<String,String> map = new HashMap<String,String>();
	                 map.put("name", params[0]);
		             map.put("psw", params[1]);
                     //String data = API.httpPost(Constant.SERVER_URL+"/login.php", map);
	                return new JSONObject(new String("{a:1,b:2}"));
	            } catch (Exception e) {
	            	e.printStackTrace();
	                return null;
	            }
	        }

	        @Override
	        protected void onPostExecute(JSONObject result) {
	        	
	        	User user = new User();
	        	user.account = account.getText().toString().trim();
	        	user.password = password.getText().toString().trim();
	        	user.type = "0";
	        	
	        	SqlliteHander.getTnstantiation(UserLoginActivity.this).saveUser(user);
	        	UserLoginActivity.this.hideProgressDialog();
	        	Intent intent = new Intent(UserLoginActivity.this,MainActivity.class);  
				startActivity(intent);
				UserLoginActivity.this.finish();
	        }
	    }
	 
}
