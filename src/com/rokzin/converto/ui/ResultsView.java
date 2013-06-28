package com.rokzin.converto.ui;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.rokzin.converto.core.ResultBaseAdapter;
import com.rokzin.converto.core.ResultItem;

public class ResultsView extends ListView {

	private Context rContext;

	public ResultsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	public ResultsView(Context context) {
		super(context);
		rContext = context;
	}

	public void setResults(ArrayList<ResultItem> results) {

		this.setAdapter(new ResultBaseAdapter(rContext, results));

	}
}
