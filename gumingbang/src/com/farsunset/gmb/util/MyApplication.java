package com.farsunset.gmb.util;

import java.io.File;

import com.farsunset.gmb.env.Constant;

import android.app.Application;

public class MyApplication extends Application {


    static MyApplication instance;


    static public MyApplication getInstance() {
        return instance;
    }

   

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
       
        //创建表
        SqlliteHander.getTnstantiation(this.getApplicationContext()).createTable();
        
        //创建必要的文件夹
        File dirs = new File(Constant.CACHE_DIR);
        if(!dirs.exists())
        {
        	dirs.mkdirs();
        }
        
        GlobalExceptionListener.getInstance().init(this.getApplicationContext());

    }

    
    @Override
    public void onTerminate() {
        // TODO Auto-generated method stub
        super.onTerminate();
    }

    
}
