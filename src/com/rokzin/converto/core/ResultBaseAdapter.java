package com.rokzin.converto.core;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rokzin.converto.R;

public class ResultBaseAdapter extends BaseAdapter {
	private static ArrayList<ResultItem> resultsList;

	private LayoutInflater mInflater;

	public ResultBaseAdapter(Context context, ArrayList<ResultItem> results) {
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
			convertView = mInflater.inflate(R.layout.result_item, null);
			holder = new ViewHolder();
			holder.txtValue = (TextView) convertView.findViewById(R.id.result_value);
			holder.txtUnitType = (TextView) convertView.findViewById(R.id.result_unit);

			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtValue.setText(String.valueOf(resultsList.get(position).getValue()));
		holder.txtUnitType.setText(String.valueOf(resultsList.get(position).getUnitType()));

		return convertView;
	}

	static class ViewHolder {
		TextView txtValue;
		TextView txtUnitType;
	}
}