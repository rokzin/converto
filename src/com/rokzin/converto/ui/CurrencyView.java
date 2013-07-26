package com.rokzin.converto.ui;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rokzin.converto.core.ICustomView;
import com.rokzin.converto.currency.Currency;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.CustomObject;
import com.rokzin.converto.utils.Formatting;
import com.rokzin.converto.utils.PreferenceSet;

public class CurrencyView extends RelativeLayout implements ICustomView{
	
	private class CustomOnClick implements OnClickListener{
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

		int ID;
		@Override
		public void afterTextChanged(Editable s) {
			convert(ID);
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			if(s.toString().equals(type1Value.getText().toString())){
				ID=11;
			}
			else{
				ID=22;
			}
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {}
		
	}
	
	private TextView currencyType1;
	private LayoutParams currencyType1LP;
	private TextView currencyType2;
	private LayoutParams currencyType2LP;
	private EditText type1Value;
	private LayoutParams type1ValueLP;
	private EditText type2Value;
	private LayoutParams type2ValueLP;
	private CustomTextWatcher fCustomTextWatcher = new CustomTextWatcher();
	private ArrayList<Double> rates = new ArrayList<Double>();
	private Context rContext;
	private int screenWidth;

	public CurrencyView(Context context) {
		super(context);
		rContext = context;
		
		initialize();
	}
	
	public CurrencyView(Context context, AttributeSet attrs, int theme) {
		super(context, attrs, theme);
		
	}
	
	private void addListeners() {
		currencyType1.setOnClickListener(new CustomOnClick());
		currencyType2.setOnClickListener(new CustomOnClick());
		addEditTextListeners();

	}

	private void addEditTextListeners() {
		type1Value.addTextChangedListener(fCustomTextWatcher);
		type2Value.addTextChangedListener(fCustomTextWatcher);
	}

	private void convert(int i) {
		type1Value.removeTextChangedListener(fCustomTextWatcher);
		type2Value.removeTextChangedListener(fCustomTextWatcher);

			if(i == 11 || i == 1 ){
				if(!Formatting.isEmptyOrNull(type1Value)){
					double from = rates.get(getLocation(currencyType1));
					double to = rates.get(getLocation(currencyType2));
					Currency c = new Currency(from,to, Double.valueOf(type1Value.getText().toString()));
					type2Value.setText(String.valueOf(Formatting.roundOff(c.getResult())));
				}
				
			}
			
			if(i == 22 || i==2 ){
				if(!Formatting.isEmptyOrNull(type2Value)){
					double from = rates.get(getLocation(currencyType2));
					double to = rates.get(getLocation(currencyType1));
					Currency c = new Currency(from, to, Double.valueOf(type2Value.getText().toString()));
					type1Value.setText(String.valueOf(Formatting.roundOff(c.getResult())));
				}
			}
	
			addEditTextListeners();
	}
	
	


	private int getLocation(TextView tv) {
		for (int i = 0; i < ConversionTypes.getCurrencyTypes().length; i++) {
			if(tv.getText().toString().equals(ConversionTypes.getCurrencyTypes()[i])){
				return i;
			}
		}
		return 0;
	}

	
	public void getSavedRates() {
		
		String curSavedValues = rContext.getSharedPreferences("com.rokzin.converto_preferences", 0).getString("CurrencyRates", "Error");

		
		if(!curSavedValues.equals("Error")){

			StringTokenizer st = new StringTokenizer(curSavedValues, ",");
			while(st.hasMoreElements()){
				rates.add(Double.valueOf(st.nextToken()));
			}
		}
	}



	private void initialize() {
		
	
		int orientation = ((WindowManager) rContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
		if (orientation == Surface.ROTATION_0 || orientation == Surface.ROTATION_180) {
			loadPotraitView();
		}
		else {
			loadLandscapeView();
		}
		

	}

	@Override
	public void loadLandscapeView() {
		screenWidth = rContext.getResources().getDisplayMetrics().widthPixels;
	
		removeAllViews();
		
		currencyType1 = CustomObject.getCustomTextView(rContext, 1, Color.parseColor("#63879F"), Color.WHITE, 16, ConversionTypes.getCurrencyTypes()[133]);
		currencyType2 = CustomObject.getCustomTextView(rContext, 2, Color.parseColor("#63879F"), Color.WHITE, 16, ConversionTypes.getCurrencyTypes()[133]);
		
		
		type1Value = CustomObject.getCustomInputBox(rContext, 35, "1", "Enter value", new int[]{InputType.TYPE_CLASS_NUMBER,InputType.TYPE_NUMBER_FLAG_DECIMAL}, 11);
		type2Value  = CustomObject.getCustomInputBox(rContext, 35, "1", "Enter value", new int[]{InputType.TYPE_CLASS_NUMBER,InputType.TYPE_NUMBER_FLAG_DECIMAL}, 22);
		type1Value.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		type2Value.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		
		currencyType1LP = CustomObject.getCustomParams(screenWidth * 2/5, 120, new int[]{RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.ALIGN_PARENT_TOP});
		currencyType2LP = CustomObject.getCustomParams(screenWidth * 2/5, 120, new int[]{RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.ALIGN_PARENT_TOP});
		
		type1ValueLP = CustomObject.getCustomParams(screenWidth * 2/5, 120, new int[]{});
		type2ValueLP = CustomObject.getCustomParams(screenWidth * 2/5, 120, new int[]{});
		
		currencyType2LP.addRule(RelativeLayout.RIGHT_OF, currencyType1.getId());
		type1ValueLP.addRule(RelativeLayout.BELOW, currencyType1.getId());
		type2ValueLP.addRule(RelativeLayout.RIGHT_OF, type1Value.getId());
		type2ValueLP.addRule(RelativeLayout.BELOW, currencyType2.getId());
		
		this.addView(currencyType1, currencyType1LP);
		this.addView(type1Value,type1ValueLP);
		this.addView(type2Value,type2ValueLP);
		this.addView(currencyType2, currencyType2LP);
		
		addListeners();
		
	}
	@Override
	public void loadPotraitView() {
		screenWidth = rContext.getResources().getDisplayMetrics().widthPixels;
	
		removeAllViews();
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
		addListeners();
		
	}

	@Override
	public void reinitialize() {
		getSavedRates();

	}

	@Override
	public String toString() {
		return PreferenceSet.CURRENCY;
	}
	
	

 }
