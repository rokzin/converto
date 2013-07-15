package com.rokzin.converto.ui;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.CustomObject;
import com.rokzin.converto.utils.PreferenceSet;

public class CurrencyView extends RelativeLayout{
	
	private Context rContext;
	private TextView currencyType1;
	private TextView currencyType2;
	private EditText type2Value;
	private EditText type1Value;
	private LayoutParams currencyType1LP;
	private LayoutParams currencyType2LP;
	private LayoutParams type1ValueLP;
	private LayoutParams type2ValueLP;

	public CurrencyView(Context context, AttributeSet attrs, int theme) {
		super(context, attrs, theme);
		
	}
	
	public CurrencyView(Context context) {
		super(context);
		rContext = context;
		
		initialize();
	}
	
	private void initialize() {
		
		int screenWidth = rContext.getResources().getDisplayMetrics().widthPixels;
		int screenHeight = rContext.getResources().getDisplayMetrics().heightPixels;
		
		currencyType1 = CustomObject.getCustomTextView(rContext, 1, Color.parseColor("#63879F"), Color.WHITE, 16, ConversionTypes.getCurrencyTypes()[133]);
		currencyType2 = CustomObject.getCustomTextView(rContext, 2, Color.parseColor("#63879F"), Color.WHITE, 16, ConversionTypes.getCurrencyTypes()[133]);
		
		
		type1Value = CustomObject.getCustomInputBox(rContext, 35, "1", "Enter value", new int[]{InputType.TYPE_CLASS_NUMBER,InputType.TYPE_NUMBER_FLAG_DECIMAL}, 11);
		type2Value  = CustomObject.getCustomInputBox(rContext, 35, "1", "Enter value", new int[]{InputType.TYPE_CLASS_NUMBER,InputType.TYPE_NUMBER_FLAG_DECIMAL}, 22);
		
		currencyType1LP = CustomObject.getCustomParams(screenWidth * 1/3, 120, new int[]{RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.ALIGN_PARENT_TOP});
		currencyType2LP = CustomObject.getCustomParams(screenWidth * 1/3, 120, new int[]{});
		
		type1ValueLP = CustomObject.getCustomParams(screenWidth * 2/3, 120, new int[]{RelativeLayout.ALIGN_PARENT_TOP});
		type2ValueLP = CustomObject.getCustomParams(screenWidth * 2/3, 120, new int[]{});
		
		type1ValueLP.addRule(RelativeLayout.RIGHT_OF, currencyType1.getId());
		type2ValueLP.addRule(RelativeLayout.BELOW, currencyType1.getId());
		currencyType2LP.addRule(RelativeLayout.RIGHT_OF, type2Value.getId());
		currencyType2LP.addRule(RelativeLayout.BELOW, type1Value.getId());
		
		
		this.addView(currencyType1, currencyType1LP);
		this.addView(type1Value,type1ValueLP);
		this.addView(type2Value,type2ValueLP);
		this.addView(currencyType2, currencyType2LP);

	}

	@Override
	public String toString() {
		return PreferenceSet.CURRENCY;
	}
	
	


//	private void convert(int i) {
//		
//		if(isOnline()){
//		
//			if(i == 0 ){
//				Currency c = new Currency(getLocation(rType1), getLocation(rType2), Double.valueOf(rInput1.getText().toString()));
//				
//				rInput2.removeTextChangedListener(rTextListener);
//				rInput2.setText(c.getResult());
//				rInput2.addTextChangedListener(rTextListener);
//			}
//			
//			if(i == 1 ){
//				Currency c = new Currency(getLocation(rType2), getLocation(rType1), Double.valueOf(rInput2.getText().toString()));
//				rInput1.removeTextChangedListener(rTextListener);
//				rInput1.setText(c.getResult());
//				rInput1.addTextChangedListener(rTextListener);
//			}
//		
//		}
//		else{
//			Toast.makeText(rContext, "No Internet connection.", Toast.LENGTH_SHORT).show();
//		}
//	
//	}
//
//	
	private int getLocation(TextView tv) {
		for (int i = 0; i < ConversionTypes.getCurrencyTypes().length; i++) {
			if(tv.getText().toString().equals(ConversionTypes.getCurrencyTypes()[i])){
				return i;
			}
		}
		return 0;
	}

	public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) rContext.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	

 }
