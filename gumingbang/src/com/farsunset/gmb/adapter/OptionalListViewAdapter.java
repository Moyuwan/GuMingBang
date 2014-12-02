package com.farsunset.gmb.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.farsunset.gmb.ui.R;

public class OptionalListViewAdapter extends BaseAdapter {
	private Context context;
	ArrayList<HashMap<String, String>> dataList;

	public OptionalListViewAdapter(Context context,
			ArrayList<HashMap<String, String>> data) {
		this.context = context;
		this.dataList = data;
	}

	public View getView(int index, View itemView, ViewGroup arg2) {

		ViewHolder holder;
		if (itemView == null) {
			itemView = LayoutInflater.from(context).inflate(
					R.layout.item_optional, null);
			holder = new ViewHolder();
			holder.pname = ((TextView) itemView.findViewById(R.id.pname));
			holder.hot = ((TextView) itemView.findViewById(R.id.hot));
			holder.price = ((TextView) itemView.findViewById(R.id.price));
			holder.del = ((TextView) itemView .findViewById(R.id.deleteButton));
			itemView.setTag(holder);
		} else {
			holder = (ViewHolder) itemView.getTag();
		}
		holder.pname.setText(dataList.get(index).get("pname").toString());
		;
		holder.hot.setText(dataList.get(index).get("hot").toString());
		holder.price.setText(dataList.get(index).get("price").toString());
		 
		return itemView;
	}

	@Override
	public int getCount() {
		if (dataList == null) {
			return 0;
		}
		return dataList.size();
	}

	public HashMap<String, String> getItem(int index) {
		// TODO Auto-generated method stub
		return dataList.get(index);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<HashMap<String, String>> getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList<HashMap<String, String>> dataList) {
		this.dataList = dataList;
	}

	static class ViewHolder {
		TextView pname;
		TextView hot;
		TextView price;
		TextView del;
	}

}