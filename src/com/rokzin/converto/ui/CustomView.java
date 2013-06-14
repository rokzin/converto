package com.rokzin.converto.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.rokzin.converto.ConvertoActivity;
import com.rokzin.converto.R;

public class CustomView extends LinearLayout {
	Context rContext;
	private String rTitle;

	private Spinner rSpinner;
	private EditText rInput;
	private ListView rResultsList;

	public CustomView(Context context) {
		super(context);
		rContext = context;
	}

	public CustomView(Context context, AttributeSet attrs, int theme) {
		super(context, attrs, theme);
	}

	public void initialize(String title) {
		// this.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
		// LayoutParams.FILL_PARENT));
		// this.setOrientation(LinearLayout.VERTICAL);
		// this.setBackgroundColor(Color.rgb(229, 231, 230));

		rTitle = title;
		rInput = new EditText(rContext);
		rSpinner = new Spinner(rContext);
		rResultsList = new ListView(rContext);
		rInput.setText("1");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(rContext, R.layout.result_item, R.id.result_item, new String[] { "this", "that" });
		rResultsList.setAdapter(adapter);
		addViews();
	}

	@Override
	public String toString() {

		return rTitle;
	}

	private void addViews() {
		this.addView(rInput, ConvertoActivity.APP_WIDTH / 2, 100);
		this.addView(rSpinner, ConvertoActivity.APP_WIDTH / 2, 100);
		// this.addView(rResultsList, ConvertoActivity.APP_WIDTH, 300);
	}
}
