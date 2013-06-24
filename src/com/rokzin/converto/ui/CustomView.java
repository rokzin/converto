package com.rokzin.converto.ui;

import java.util.List;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.rokzin.converto.ConvertoActivity;
import com.rokzin.converto.R;

public class CustomView extends RelativeLayout {
	Context rContext;
	private String rTitle;

	protected Spinner rSpinner;
	protected EditText rInput;
	private ResultsView rResultsView;

	public CustomView(Context context) {
		super(context);
		rContext = context;
	}

	public CustomView(Context context, AttributeSet attrs, int theme) {
		super(context, attrs, theme);
	}

	public void initialize(String title) {

		rTitle = title;
		rInput = new EditText(rContext);
		rInput.setHint("Enter a number");
		rInput.setInputType(InputType.TYPE_CLASS_NUMBER);
		rInput.setId(1);
		rSpinner = new Spinner(rContext);
		rSpinner.setId(2);
		rResultsView = new ResultsView(rContext);
		rResultsView.setId(3);
		rInput.setText("1");
		addViews();
	}

	@Override
	public String toString() {

		return rTitle;
	}

	public void setResults(List<String> results) {
		rResultsView.setResults(results);
	}

	public void setSpinnerValues(List<String> unitTypes) {
		SpinnerAdapter rSpinnerAdapter = new ArrayAdapter<String>(rContext, R.layout.result_item, R.id.result_item, unitTypes);
		rSpinner.setAdapter(rSpinnerAdapter);
	}

	private void addViews() {

		RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ConvertoActivity.APP_WIDTH * 1 / 3, 100);
		relativeParams.addRule(RelativeLayout.RIGHT_OF, rInput.getId());

		RelativeLayout.LayoutParams relativeParams2 = new RelativeLayout.LayoutParams(ConvertoActivity.APP_WIDTH * 2 / 3, 100);
		relativeParams2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		relativeParams2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

		RelativeLayout.LayoutParams relativeParams3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		relativeParams3.addRule(RelativeLayout.BELOW, rInput.getId());

		this.addView(rInput, relativeParams2);
		this.addView(rSpinner, relativeParams);
		this.addView(rResultsView, relativeParams3);

	}
}
