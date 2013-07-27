package com.rokzin.converto.storage;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rokzin.converto.R;

public class StoreItemBaseAdapter extends BaseAdapter {
	private static ArrayList<StoreItem> resultsList;

	private LayoutInflater mInflater;

	public StoreItemBaseAdapter(Context context, ArrayList<StoreItem> results) {
		resultsList = results;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return resultsList.size();
	}

	public Object getItem(int position) {
		return resultsList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.store_item, null);
			holder = new ViewHolder();
			holder.txtValue = (TextView) convertView.findViewById(R.id.store_value);
			holder.txtDate = (TextView) convertView.findViewById(R.id.store_date);

			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtValue.setText(String.valueOf(resultsList.get(position).getValue()));
		holder.txtDate.setText(String.valueOf(resultsList.get(position).getDate()));

		return convertView;
	}

	static class ViewHolder {
		TextView txtValue;
		TextView txtDate;
	}
}