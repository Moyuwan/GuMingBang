package com.farsunset.gmb.net;

import java.util.HashMap;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.widget.HeaderViewListAdapter;

import com.farsunset.gmb.adapter.OptionalListViewAdapter;
import com.farsunset.gmb.component.SuperListView;

public class OptionalStockTask extends AsyncTask<String, Void, JSONObject> {
	
	SuperListView listView;
	
	public OptionalStockTask(SuperListView lv)
	{
		listView = lv;
	}
    @Override
    protected void onPreExecute() {
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        try {
             HashMap<String,String> map = new HashMap<String,String>();
            // map.put("name", params[0]);
            // map.put("psw", params[1]);
            // String data = API.httpPost(Constant.SERVER_URL+"/login.php", map);
            return new JSONObject("{a:1,b:2}");
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONObject result) {
    	
    	listView.refreshComplete();
    	
    	HeaderViewListAdapter ha = (HeaderViewListAdapter) listView.getAdapter();  
    	for(int i=0;i<5;i++)
    	{
    		 HashMap<String ,String> map = new HashMap<String ,String>();
    		 map.put("pname", "工商银行");
    		 map.put("hot", "55");
    		 map.put("price", "43.9");
    		((OptionalListViewAdapter)ha.getWrappedAdapter()).getDataList().add(map);
    	}
    	((OptionalListViewAdapter)ha.getWrappedAdapter()).notifyDataSetChanged();
    }
}