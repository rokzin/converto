package com.rokzin.converto.ui;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rokzin.converto.R;

public class ResultsView extends ListView {

	private Context rContext;

	public ResultsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	public ResultsView(Context context) {
		super(context);
		rContext = context;
	}

	public void setResults(List<String> results) {
		ArrayAdapter<String> resultsAdapter = new ArrayAdapter<String>(rContext, R.layout.result_item, R.id.result_item, results);
		setAdapter(resultsAdapter);

	}
}
