package com.rokzin.converto.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rokzin.converto.units.Currency;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.Formatting;
import com.rokzin.converto.utils.PreferenceSet;

public class CurrencyView extends RelativeLayout{
	
	LinearLayout box1;
	LinearLayout box2;

	private Context rContext;
	private EditText rInput1;
	private EditText rInput2;
	private Currency rCurrency;
	private TextView rType1;
	private TextView rType2;
	private CustomTextWatcher rTextListener = new CustomTextWatcher();
	

	
	private TextView rEqualSign;
	

	private class CustomOnclickListener implements OnClickListener{
		
		@Override
		public void onClick(View v) {
			
			final TextView view = (TextView)v;
			final String options[] = ConversionTypes.getCurrencyTypes();
			
			AlertDialog.Builder builder = new AlertDialog.Builder(rContext);
			
			builder.setItems(options, new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			    	view.setText(options[which]);
			    	convert(view.getId());		
			    }

				
			});
			builder.show();
		}
		
	}
	
	private class CustomTextWatcher implements TextWatcher{

		String savedText;
		
		@Override
		public void afterTextChanged(Editable s) {
			
				if (s.toString().equals("")) {
			
				}
				else {
					if(rType1.getText().toString().equals(rType2.getText().toString())){
						if(!rInput1.getText().toString().equals(savedText)){
							rInput2.removeTextChangedListener(rTextListener);
							rInput2.setText(Formatting.roundOff(rInput1.getText().toString()));
							rInput2.addTextChangedListener(rTextListener);
							
						}
						if(!rInput2.getText().toString().equals(savedText)){
							rInput1.removeTextChangedListener(rTextListener);
							rInput1.setText(Formatting.roundOff(rInput2.getText().toString()));
							rInput1.addTextChangedListener(rTextListener);
							
						}
					}
					else{
						if(!rInput1.getText().toString().equals(savedText)){
							convert(0);	
						}
						else{
							convert(1);	
						}
						
					
					}
				}
	
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			savedText = s.toString();
			
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			
		}
		
	}
	
	public CurrencyView(Context context) {
		super(context);
		rContext = context;
		initialize();
	}

	public CurrencyView(Context context, AttributeSet attrs, int theme) {
		super(context, attrs, theme);
		
	}
	
	@Override
	public String toString() {
		return PreferenceSet.CURRENCY;
	}

	private void initialize() {
		
		box1 = new LinearLayout(rContext);
		box1.setOrientation(LinearLayout.VERTICAL);
		box1.setId(12);
		
		rInput1 = getCustomInputBox(); 
		rInput1.setId(2);
		
		rInput2 = getCustomInputBox();
		rInput2.setId(3);
		
		rType1 = getCustomTextView();
		rType1.setId(0);

		rType2 = getCustomTextView();
		rType2.setId(1);
		
		RelativeLayout.LayoutParams rInputLP = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 120);
		rInputLP.addRule(RelativeLayout.CENTER_IN_PARENT);
		box1.addView(rInput1, rInputLP);
		
		RelativeLayout.LayoutParams rSpinnerLP = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 120);
		rSpinnerLP.addRule(RelativeLayout.CENTER_IN_PARENT);
		rSpinnerLP.addRule(RelativeLayout.BELOW, rInput1.getId());
		box1.addView(rType1,rSpinnerLP);
		
		//===============
		
		rEqualSign = new TextView(rContext);
		rEqualSign.setText("=");
		rEqualSign.setTextSize(45);
		rEqualSign.setId(11);
		rEqualSign.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
				
		RelativeLayout.LayoutParams rEqLP = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 100
				);
		rEqLP.addRule(RelativeLayout.CENTER_IN_PARENT);
		rEqLP.addRule(RelativeLayout.BELOW, box1.getId());
		
		
		//================
		
		box2 = new LinearLayout(rContext);
		box2.setOrientation(LinearLayout.VERTICAL);
		

		RelativeLayout.LayoutParams rInput2LP = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 120);
		rInput2LP.addRule(RelativeLayout.CENTER_IN_PARENT);
		box2.addView(rInput2, rInput2LP);
		
		RelativeLayout.LayoutParams rSpinner2LP = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 120);
		rSpinner2LP.addRule(RelativeLayout.CENTER_IN_PARENT);
		rSpinner2LP.addRule(RelativeLayout.BELOW, rInput2.getId());
		box2.addView(rType2,rSpinner2LP);
		
	
		RelativeLayout.LayoutParams box1LP = new RelativeLayout.LayoutParams(rContext.getResources().getDisplayMetrics().widthPixels - 20, 240);
		box1LP.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		box1LP.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		RelativeLayout.LayoutParams box2LP = new RelativeLayout.LayoutParams(rContext.getResources().getDisplayMetrics().widthPixels - 20, 240);
		box2LP.addRule(RelativeLayout.BELOW,rEqualSign.getId());
		box2LP.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		this.addView(box1,box1LP);
		this.addView(rEqualSign,rEqLP);
		this.addView(box2,box2LP);

	}
	
	private TextView getCustomTextView() {
		
		TextView tv = new TextView(rContext);
		tv.setBackgroundColor(Color.parseColor("#63879F"));
		tv.setOnClickListener(new CustomOnclickListener());
		tv.setGravity(Gravity.CENTER);
		tv.setText(ConversionTypes.getCurrencyTypes()[136]);
		tv.setTextColor(Color.WHITE);
		tv.setTextSize(16);
		return tv;
		
	}

	private EditText getCustomInputBox() {
		EditText et = new EditText(rContext);
		et.setGravity(Gravity.CENTER);
		et.setTextSize(35);
		et.setHint("Enter a number");
		et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		et.setText("1");
		et.addTextChangedListener(rTextListener);
		return et;
	}

	private void convert(int i) {
		
		if(i == 0 ){
			rCurrency = new Currency(rType1.getText().toString(),rType2.getText().toString(), Double.parseDouble(rInput1.getText().toString()));
			rInput2.removeTextChangedListener(rTextListener);
			rInput2.setText(String.valueOf(Formatting.roundOff(rCurrency.getResults().get(0).getValue())));
			rInput2.addTextChangedListener(rTextListener);
		}
		
		if(i == 1 ){
			rCurrency = new Currency(rType2.getText().toString(),rType1.getText().toString(), Double.parseDouble(rInput2.getText().toString()));
			rInput1.removeTextChangedListener(rTextListener);
			rInput1.setText(String.valueOf(Formatting.roundOff(rCurrency.getResults().get(0).getValue())));
			rInput1.addTextChangedListener(rTextListener);
		}
		
		
		
	}
	
	

 }
