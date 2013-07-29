package com.rokzin.converto.ui;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.rokzin.converto.R;
import com.rokzin.converto.core.ResultItem;
import com.rokzin.converto.storage.Store;
import com.rokzin.converto.utils.Formatting;

public class CustomView extends RelativeLayout implements OnItemClickListener, OnItemLongClickListener{
	Context rContext;
	private String rTitle;

	protected Spinner rSpinner;
	protected EditText rInput;
	private ResultsView rResultsView;

	public CustomView(Context context) {
		super(context);
		rContext = context;
		setBackgroundColor(Color.WHITE);
	}

	public CustomView(Context context, AttributeSet attrs, int theme) {
		super(context, attrs, theme);
	}

	public void reEnterText() {
		String inputText = rInput.getText().toString();
		rInput.setText(inputText);
	}// used for roundoff preference change

	public void loadLandscapeView() {
		removeAllViews();
		RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(rContext.getResources().getDisplayMetrics().widthPixels * 1 / 3, LayoutParams.WRAP_CONTENT);
		relativeParams.addRule(RelativeLayout.BELOW, rInput.getId());

		RelativeLayout.LayoutParams relativeParams2 = new RelativeLayout.LayoutParams(rContext.getResources().getDisplayMetrics().widthPixels * 1 / 3, LayoutParams.WRAP_CONTENT);
		relativeParams2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		relativeParams2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

		RelativeLayout.LayoutParams relativeParams3 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		relativeParams3.addRule(RelativeLayout.RIGHT_OF, rInput.getId());

		this.addView(rInput, relativeParams2);
		this.addView(rSpinner, relativeParams);
		this.addView(rResultsView, relativeParams3);
	}

	public void loadPotraitView() {
		removeAllViews();
		RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(rContext.getResources().getDisplayMetrics().widthPixels * 3 / 10,  LayoutParams.WRAP_CONTENT);
		relativeParams.addRule(RelativeLayout.RIGHT_OF, rInput.getId());

		RelativeLayout.LayoutParams rInputParams = new RelativeLayout.LayoutParams(rContext.getResources().getDisplayMetrics().widthPixels * 7 / 10, LayoutParams.WRAP_CONTENT);
		rInputParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		rInputParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

		RelativeLayout.LayoutParams relativeParams3 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		relativeParams3.addRule(RelativeLayout.BELOW, rInput.getId());

		this.addView(rInput, rInputParams);
		this.addView(rSpinner, relativeParams);
		this.addView(rResultsView, relativeParams3);
	}

	public void initialize(String title) {

		rTitle = title;
		rInput = new EditText(rContext);
		rInput.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
		rInput.setTextSize(35);
		rInput.setTextColor(rContext.getResources().getColor(R.color.teal));
		rInput.setHint("Enter a number");
		rInput.setCursorVisible(true);
		rInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		rInput.setId(1);
		rSpinner = new Spinner(rContext);
		rSpinner.setId(2);
		rSpinner.setPadding(0, 20, 5, 10);
		rSpinner.setMinimumHeight(150);
		rResultsView = new ResultsView(rContext);
		rResultsView.setId(3);
		rResultsView.setOnItemClickListener(this);
		rResultsView.setOnItemLongClickListener(this);
		rInput.setText("1");
		rInput.setMinimumHeight(150);
		
		int orientation = ((WindowManager) rContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
		if (orientation == Surface.ROTATION_0 || orientation == Surface.ROTATION_180) {
			loadPotraitView();
		}
		else {
			loadLandscapeView();
		}

	}

	@Override
	public String toString() {

		return rTitle;
	}

	public void setResults(ArrayList<ResultItem> results) {
		rResultsView.setResults(results);
	}

	public void setSpinnerValues(String[] unitTypes) {
		SpinnerAdapter rSpinnerAdapter = new ArrayAdapter<String>(rContext, R.layout.spinner_item, R.id.spinner_value, unitTypes);
		rSpinner.setAdapter(rSpinnerAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> aView, View v, int i, long id) {
		double value = ((ResultItem)aView.getItemAtPosition(i)).getValue();

		ClipboardManager clipboard = (ClipboardManager)
		        rContext.getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData clip = ClipData.newPlainText("ConverTo",String.valueOf(value));
		clipboard.setPrimaryClip(clip);
		Toast.makeText(rContext,String.valueOf(value) + " copied to clipboard." , Toast.LENGTH_LONG).show();		
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> aView, View v, int i,
			long id) {
		double value = ((ResultItem)aView.getItemAtPosition(i)).getValue();
		String type = ((ResultItem)aView.getItemAtPosition(i)).getUnitType();
		final String valueWithUnit = String.valueOf(value)+" "+type; 
		final String fullyQualifiedConversion = rInput.getText().toString()+" "+rSpinner.getSelectedItem().toString()+" = "+String.valueOf(value)+" "+type; 

		CharSequence options[] = new CharSequence[] {"Copy value with Unit","Copy fully qualified conversion","Save for later"};

		AlertDialog.Builder builder = new AlertDialog.Builder(rContext);
		builder.setItems(options, new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        if(which==0){
		        	

		    		ClipboardManager clipboard = (ClipboardManager)
		    		        rContext.getSystemService(Context.CLIPBOARD_SERVICE);
		    		ClipData clip = ClipData.newPlainText("ConverTo",valueWithUnit);
		    		clipboard.setPrimaryClip(clip);
		    		Toast.makeText(rContext,valueWithUnit + " copied to clipboard." , Toast.LENGTH_LONG).show();	
		        }
		        if(which==1){
		        	

		    		ClipboardManager clipboard = (ClipboardManager)
		    		        rContext.getSystemService(Context.CLIPBOARD_SERVICE);
		    		ClipData clip = ClipData.newPlainText("ConverTo",fullyQualifiedConversion);
		    		clipboard.setPrimaryClip(clip);
		    		Toast.makeText(rContext,fullyQualifiedConversion + " copied to clipboard." , Toast.LENGTH_LONG).show();	
		        }
		        if(which==2){
		        	new Store(fullyQualifiedConversion,rContext);
		        }
		    }
		});
		builder.show();
		return false;
	}
	
	public void setText(String text){
		rInput.setText(text);
	}
	
	public String getText(){
		return rInput.getText().toString();
	}
	protected void reinitialize(){
		if(Formatting.isEmptyOrNull(rInput)){
			setText("1");
		}
	}

}
