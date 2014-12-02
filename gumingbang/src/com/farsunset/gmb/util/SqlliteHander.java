package com.farsunset.gmb.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.farsunset.gmb.bean.Product;
import com.farsunset.gmb.bean.User;
import com.farsunset.gmb.env.Constant;

public class SqlliteHander {
	static Context context;
	private static SqlliteHander hander;
	private final static String DATABASE = Constant.APP_NAME+".db"; 
	private SqlliteHander()
	{};
	
	public static SqlliteHander getTnstantiation(Context ctx){
		context = ctx;
		if(null==hander)
		{
			return new SqlliteHander();
		}
		return hander;
	}
	
	public  void createTable()
	{
		 SQLiteDatabase db = context.openOrCreateDatabase(DATABASE, Context.MODE_PRIVATE, null);  	
		 String USQL ="create table if not exists T_gmb_USER (account varchar(64) PRIMARY KEY,password varchar(64),type varchar(2));";
		 db.execSQL(USQL);  
		 db.close();
	}
	
	public  void saveUser(User obj)
	{
		deleteUser();
		SQLiteDatabase db = context.openOrCreateDatabase(DATABASE, Context.MODE_PRIVATE, null);  
		db.execSQL("INSERT INTO T_gmb_USER(account,password,type) VALUES (?,?,?)", new String[]{obj.account,obj.password,obj.type});
		db.close();
	}
	 
	 
	public  void deleteUser()
	{
		 SQLiteDatabase db = context.openOrCreateDatabase(DATABASE, Context.MODE_PRIVATE, null);  	
		 db.execSQL("delete from T_gmb_USER", new Object[]{});
		 db.close();
	}
	 
	
	public  User queryUser()
	{
		 User info = null;
		 SQLiteDatabase db = context.openOrCreateDatabase(DATABASE, Context.MODE_PRIVATE, null);  	
		 Cursor c = db.rawQuery("SELECT * FROM T_gmb_USER", null);  
	        while (c.moveToNext()) {  
	        	info=new User();
	        	info.account = c.getString(c.getColumnIndex("account"));
	        	info.password = c.getString(c.getColumnIndex("password"));
	        	info.type = c.getString(c.getColumnIndex("type"));
	        	break;
	        }  
	        c.close(); 
	        db.close();
		return info;
	}
	 
}
