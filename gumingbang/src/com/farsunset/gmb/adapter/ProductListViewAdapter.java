package com.farsunset.gmb.adapter;


import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.farsunset.gmb.bean.Product;
import com.farsunset.gmb.ui.R;
import com.farsunset.gmb.util.AppTools;

public class ProductListViewAdapter extends BaseAdapter {
       private Context context;
       ArrayList<Product> dataList;
	   public ProductListViewAdapter(Context context, ArrayList<Product> data)
       {
    	   this.context = context;
    	   this.dataList = data;
       }

        public View getView(int index, View arg1, ViewGroup arg2) {
 		         View itemView = LayoutInflater.from(context).inflate(R.layout.item_product, null);
 		        ((TextView) itemView.findViewById(R.id.name)).setText(dataList.get(index).name);;
 		        ((TextView) itemView.findViewById(R.id.forecaster)).setText(String.valueOf(dataList.get(index).oprname));;
 		        ((TextView) itemView.findViewById(R.id.gail)).setText(dataList.get(index).oprgail);;
 		        ((TextView) itemView.findViewById(R.id.price)).setText(dataList.get(index).price);
 		        ((TextView) itemView.findViewById(R.id.payNum)).setText(dataList.get(index).sale);
 		        ((TextView) itemView.findViewById(R.id.button)).setText(dataList.get(index).button);
 		        return itemView;
        }

		@Override
		public int getCount() {
			if(dataList == null)
			{
				return 0;
			}
			return dataList.size();
		}
		 public Product getItem(int index) {
             // TODO Auto-generated method stub
             return dataList.get(index);
     }

     public long getItemId(int arg0) {
             // TODO Auto-generated method stub
             return 0;
     }

	public ArrayList<Product> getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList<Product> dataList) {
		this.dataList = dataList;
	}
	
 
	 
}