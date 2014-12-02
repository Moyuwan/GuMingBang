package com.farsunset.gmb.net;

import java.util.HashMap;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.widget.HeaderViewListAdapter;

import com.farsunset.gmb.adapter.ProductListViewAdapter;
import com.farsunset.gmb.bean.Product;
import com.farsunset.gmb.component.SuperListView;

public class ProductTask extends AsyncTask<String, Void, JSONObject> {
	
	SuperListView listView;
	
	public ProductTask(SuperListView lv)
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
    		Product p = new Product();
    		p.name ="招商银行";
    		p.button ="购买";
    		p.oprgail ="87%";
    		p.oprname ="股道中人";
    		p.oprtype ="激进型";
    		p.price ="1.0";
    		p.sale ="322";
    		p.stockid ="600000";
    		((ProductListViewAdapter)ha.getWrappedAdapter()).getDataList().add(p);
    	}
    	
    	((ProductListViewAdapter)ha.getWrappedAdapter()).notifyDataSetChanged();
    }
}