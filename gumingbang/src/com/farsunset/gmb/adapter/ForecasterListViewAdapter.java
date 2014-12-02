package com.farsunset.gmb.adapter;


import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.farsunset.gmb.bean.Forecaster;
import com.farsunset.gmb.component.WebImageView;
import com.farsunset.gmb.ui.R;

public class ForecasterListViewAdapter extends BaseAdapter {
       private Context context;
       ArrayList<Forecaster> dataList;
	   public ForecasterListViewAdapter(Context context, ArrayList<Forecaster> data)
       {
    	   this.context = context;
    	   this.dataList = data;
       }

        public View getView(int index, View arg1, ViewGroup arg2) {
 		         View itemView = LayoutInflater.from(context).inflate(R.layout.item_forecaster, null);
 		        ((TextView) itemView.findViewById(R.id.name)).setText(dataList.get(index).name);;
 		        ((TextView) itemView.findViewById(R.id.oprtype)).setText(String.valueOf(dataList.get(index).oprtype));;
 		        ((TextView) itemView.findViewById(R.id.gail)).setText(dataList.get(index).oprgail);;
 		        ((TextView) itemView.findViewById(R.id.oprgul)).setText(dataList.get(index).oprgul);
 		        ((TextView) itemView.findViewById(R.id.fans)).setText(dataList.get(index).fans);
 		        ((WebImageView) itemView.findViewById(R.id.headImage)).load(dataList.get(index).image);
 		        ((TextView) itemView.findViewById(R.id.signature)).setText(dataList.get(index).note);
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
		 public Forecaster getItem(int index) {
             // TODO Auto-generated method stub
             return dataList.get(index);
     }

     public long getItemId(int arg0) {
             // TODO Auto-generated method stub
             return 0;
     }

	public ArrayList<Forecaster> getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList<Forecaster> dataList) {
		this.dataList = dataList;
	}
	
 
	 
}