package com.farsunset.gmb.env;

import android.os.Environment;

  


/**
 * 
 * @author track
 * @version 1.0
 */
public interface Constant { 
		public static final String CODE_SUCCESS = "0";
		
		public static final String WEIBO_TYPE_2 ="2";
		public static final int VALID_FAILED =408;
		public static final int ID_NO_EXIST =0;
		public static final int SUCCESS_FULL = 1;
		public static final int STATE_RECEIVED = 3;
		public static final int STATE_ON_PASSAGE = 0;
		public static final String FORMAT_TYPE = "UTF-8";
		public static final  String APP_NAME="gmb";
		public static final  StringBuffer draft= new StringBuffer();
		//public static final String SERVER_URL ="http://gmbapi.sinaapp.com";
		//public static final String SERVER_URL ="http://192.168.1.104:8080/gmb-webapp";
		public static final String SERVER_URL ="http://192.121.21.2:8080/a";
		
		public static final  String CACHE_DIR=Environment.getExternalStorageDirectory().getPath()+"/guminbang/cache";

		
 
}
