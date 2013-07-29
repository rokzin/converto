package com.rokzin.converto.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rokzin.converto.core.ICustomView;
import com.rokzin.converto.currency.Currency;
import com.rokzin.converto.currency.HttpURLRequest;
import com.rokzin.converto.storage.StoreItem;
import com.rokzin.converto.storage.StoreItemBaseAdapter;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.CustomObject;
import com.rokzin.converto.utils.CustomStringBuilder;
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
	
	private class TextWatcher1 implements TextWatcher{

		@Override
		public void afterTextChanged(Editable s) {
			convert(2);
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {}
		
	}
	
	private class TextWatcher2 implements TextWatcher{

		@Override
		public void afterTextChanged(Editable s) {
			convert(1);
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
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
	private TextWatcher1 tw1 = new TextWatcher1();
	private TextWatcher2 tw2 = new TextWatcher2();
	
	private ArrayList<Double> rates = new ArrayList<Double>();
	private Context rContext;
	private int screenWidth;
	
	private ListView rPopularConversions;
	private long lastRefreshed;
	private TextView lastRefeshedView;
	

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
		type1Value.addTextChangedListener(tw1);
		type2Value.addTextChangedListener(tw2);
	}

	private void convert(int i) {
		removeAllListeners();

			if(i == 1 ){
				if(!Formatting.isEmptyOrNull(type2Value)){
					double from = rates.get(getLocation(currencyType2));
					double to = rates.get(getLocation(currencyType1));
					Currency c = new Currency(from,to, Double.valueOf(type2Value.getText().toString()));
					type1Value.setText(String.valueOf(Formatting.roundOff(c.getResult())));
				}
				
			}
			
			if(i==2 ){
				if(!Formatting.isEmptyOrNull(type1Value)){
					double from = rates.get(getLocation(currencyType1));
					double to = rates.get(getLocation(currencyType2));
					Currency c = new Currency(from, to, Double.valueOf(type1Value.getText().toString()));
					type2Value.setText(String.valueOf(Formatting.roundOff(c.getResult())));
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

	
	private void getSavedRates() {
		
		String curSavedValues = rContext.getSharedPreferences("com.rokzin.converto_preferences", 0).getString("CurrencyRates", "Error");

		
		if(!curSavedValues.equals("Error")){

			StringTokenizer st = new StringTokenizer(curSavedValues, ",");
			while(st.hasMoreElements()){
				rates.add(Double.valueOf(st.nextToken()));
			}
		}
	}



	private void initialize() {
		
		
		
		currencyType1 = CustomObject.getCustomTextView(rContext, 1, Color.parseColor("#63879F"), Color.WHITE, 16, ConversionTypes.getCurrencyTypes()[121]);
		currencyType2 = CustomObject.getCustomTextView(rContext, 2, Color.parseColor("#63879F"), Color.WHITE, 16, ConversionTypes.getCurrencyTypes()[121]);
		currencyType1.setMinimumHeight(140);
		currencyType2.setMinimumHeight(140);
		
		type1Value = CustomObject.getCustomInputBox(rContext, 35, "1", "Enter value", new int[]{}, 11);
		type1Value.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		type2Value  = CustomObject.getCustomInputBox(rContext, 35, "1", "Enter value", new int[]{}, 22);
		type2Value.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		type1Value.setMinimumHeight(140);
		type2Value.setMinimumHeight(140);
		
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
		lastRefreshed = rContext.getSharedPreferences("com.rokzin.converto_preferences", 0).getLong("LastRefreshed", 0);
		screenWidth = rContext.getResources().getDisplayMetrics().widthPixels;
	
		removeAllViews();
		
		rPopularConversions = new ListView(rContext);
		RelativeLayout.LayoutParams listLP = CustomObject.getCustomParams(screenWidth, LayoutParams.WRAP_CONTENT, new int[]{});
		listLP.addRule(RelativeLayout.BELOW, type2Value.getId());
		
		currencyType1LP = CustomObject.getCustomParams(screenWidth * 9/20, LayoutParams.WRAP_CONTENT, new int[]{RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.ALIGN_PARENT_TOP});
		currencyType2LP = CustomObject.getCustomParams(screenWidth * 9/20, LayoutParams.WRAP_CONTENT, new int[]{RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.ALIGN_PARENT_TOP});
		
		type1ValueLP = CustomObject.getCustomParams(screenWidth * 9/20, LayoutParams.WRAP_CONTENT, new int[]{RelativeLayout.ALIGN_PARENT_LEFT});
		type2ValueLP = CustomObject.getCustomParams(screenWidth * 9/20, LayoutParams.WRAP_CONTENT, new int[]{RelativeLayout.ALIGN_PARENT_RIGHT});
		
		type1ValueLP.addRule(RelativeLayout.BELOW, currencyType1.getId());
		type2ValueLP.addRule(RelativeLayout.BELOW, currencyType2.getId());
		
		this.addView(currencyType1, currencyType1LP);
		
			TextView equalTo = CustomObject.getCustomTextView(rContext, 111, Color.TRANSPARENT, Color.parseColor("#63879F"), 24, "=");
			RelativeLayout.LayoutParams params = CustomObject.getCustomParams(screenWidth*2/20, 240, new int[]{RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.CENTER_HORIZONTAL});
			equalTo.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
			this.addView(equalTo, params);
			
		this.addView(currencyType2, currencyType2LP);
		this.addView(type1Value,type1ValueLP);
		this.addView(type2Value,type2ValueLP);
		
		this.addView(rPopularConversions,listLP);
		removeAllListeners();
		addListeners();
		createBottomSection();
		
	}
	
	private void createBottomSection() {
		lastRefeshedView = new TextView(rContext);
		
		RelativeLayout.LayoutParams params = CustomObject.getCustomParams(screenWidth, 70, new int[]{RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.CENTER_HORIZONTAL});
		
		lastRefeshedView.setText(new Date(lastRefreshed).toString());
		lastRefeshedView.setBackgroundColor(Color.parseColor("#63879F"));
		lastRefeshedView.setTextColor(Color.WHITE);
		lastRefeshedView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
		this.addView(lastRefeshedView, params);
		
	}
	
	public void setLastRefreshedDate(Date date){
		lastRefeshedView.setText(date.toString());
	}

	@Override
	public void loadPotraitView() {
		lastRefreshed = rContext.getSharedPreferences("com.rokzin.converto_preferences", 0).getLong("LastRefreshed", 0);
		screenWidth = rContext.getResources().getDisplayMetrics().widthPixels;
	
		removeAllViews();

		rPopularConversions = new ListView(rContext);
		RelativeLayout.LayoutParams listLP = CustomObject.getCustomParams(screenWidth, LayoutParams.WRAP_CONTENT, new int[]{});
		listLP.addRule(RelativeLayout.BELOW, currencyType2.getId());
		
		currencyType1LP = CustomObject.getCustomParams(screenWidth * 1/3, LayoutParams.WRAP_CONTENT, new int[]{RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.ALIGN_PARENT_TOP});
		currencyType2LP = CustomObject.getCustomParams(screenWidth * 1/3, LayoutParams.WRAP_CONTENT, new int[]{});
		
		type1ValueLP = CustomObject.getCustomParams(screenWidth * 2/3, LayoutParams.WRAP_CONTENT, new int[]{RelativeLayout.ALIGN_PARENT_TOP});
		type2ValueLP = CustomObject.getCustomParams(screenWidth * 2/3, LayoutParams.WRAP_CONTENT, new int[]{});
		
		type1ValueLP.addRule(RelativeLayout.RIGHT_OF, currencyType1.getId());
		type2ValueLP.addRule(RelativeLayout.BELOW, currencyType1.getId());
		currencyType2LP.addRule(RelativeLayout.RIGHT_OF, type2Value.getId());
		currencyType2LP.addRule(RelativeLayout.BELOW, type1Value.getId());
		
		
		this.addView(currencyType1, currencyType1LP);
		this.addView(type1Value,type1ValueLP);
		this.addView(type2Value,type2ValueLP);
		this.addView(currencyType2, currencyType2LP);
		this.addView(rPopularConversions,listLP);
		removeAllListeners();
		addListeners();
		createBottomSection();

		
		
	}

	@Override
	public void reinitialize() {
		getSavedRates();
		
		
		Date lrdate = new Date(lastRefreshed);
		ArrayList<StoreItem> l = new ArrayList<StoreItem>();
		
		Currency c = new Currency(rates.get(121), rates.get(38), 1);
		StoreItem s = new StoreItem("1 USD = "+Formatting.roundOff(c.getResult())+" GBP", lrdate);
		l.add(s);
		
		Currency c2 = new Currency(rates.get(121), rates.get(35), 1);
		StoreItem s2 = new StoreItem("1 USD = "+Formatting.roundOff(c2.getResult())+" EUR", lrdate);
		l.add(s2);
		
		Currency c3 = new Currency(rates.get(121), rates.get(51), 1);
		StoreItem s3 = new StoreItem("1 USD = "+Formatting.roundOff(c3.getResult())+" INR", lrdate);
		l.add(s3);
		
		Currency c4 = new Currency(rates.get(121), rates.get(23), 1);
		StoreItem s4 = new StoreItem("1 USD = "+Formatting.roundOff(c4.getResult())+" CNY", lrdate);
		l.add(s4);
		
		rPopularConversions.setAdapter(new StoreItemBaseAdapter(rContext, l));


	}

	@Override
	public String toString() {
		return PreferenceSet.CURRENCY;
	}
	
	public void removeAllListeners(){
		type1Value.removeTextChangedListener(tw1);
		type2Value.removeTextChangedListener(tw2);
		
	}

	public CustomStringBuilder getCurrencyRates() {

			ExecutorService executor = Executors.newFixedThreadPool(1);


			FutureTask<CustomStringBuilder> future = new FutureTask<CustomStringBuilder>(
	                new Callable<CustomStringBuilder>()
	                {

						@Override
						public CustomStringBuilder call() throws Exception {

							String conversions="";
							for (int i = 0; i < ConversionTypes.getCurrencyTypes().length; i++) {
								conversions = conversions + "USD"+ ConversionTypes.getCurrencyTypes()[i]+"=X,";
							}
							String source = "http://finance.yahoo.com/d/quotes.csv?e=.csv&f=c4l1&s=" +conversions;
							Log.d("ConverToLog", source);
							HttpURLRequest httpRequest = new HttpURLRequest();
							CustomStringBuilder results = httpRequest.connect(source);
						return results;
						}
	                   
	                });
			executor.execute(future);

		
	        try {
				return future.get();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
	        return new CustomStringBuilder();
		}

	
	

 }
