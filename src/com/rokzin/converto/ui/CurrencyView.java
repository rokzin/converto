package com.rokzin.converto.ui;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rokzin.converto.core.CurrencyRates;
import com.rokzin.converto.units.Currency;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.CustomObject;
import com.rokzin.converto.utils.Formatting;
import com.rokzin.converto.utils.PreferenceSet;

public class CurrencyView extends RelativeLayout{
	
	LinearLayout box1;
	LinearLayout box2;
	RelativeLayout box3;

	private Context rContext;
	private EditText rInput1;
	private EditText rInput2;
	private TextView rType1;
	private TextView rType2;
	private CustomTextWatcher rTextListener = new CustomTextWatcher();
	private TextView rEqualSign;
	private TextView lastRefreshed;
	

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

	private void getRates() {
		
		ProgressDialog pd = new ProgressDialog(rContext);
		pd.setTitle("Processing");
		pd.setMessage("Getting real time currency rates");
		pd.setCancelable(false);
		
		ExecutorService executor = Executors.newFixedThreadPool(1);

		FutureTask<String> future = new FutureTask<String>(
                new Callable<String>()
                {

					@Override
					public java.lang.String call() throws Exception {
						new CurrencyRates();
						
						return "";
					}
                   
                });
		
        executor.execute(future);
        while (!future.isDone()) {
			pd.show();
		}
        pd.dismiss();
        
        Toast.makeText(rContext, "Currency rates refreshed", Toast.LENGTH_SHORT).show();
		
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
		
		
		
		RelativeLayout.LayoutParams rInputLP = CustomObject.getCustomParams(LayoutParams.MATCH_PARENT, 120,new int[]{RelativeLayout.CENTER_IN_PARENT});
		box1.addView(rInput1, rInputLP);
		
		RelativeLayout.LayoutParams rSpinnerLP = CustomObject.getCustomParams(LayoutParams.MATCH_PARENT, 120, new int[]{RelativeLayout.CENTER_IN_PARENT});
		rSpinnerLP.addRule(RelativeLayout.BELOW, rInput1.getId());
		box1.addView(rType1,rSpinnerLP);
		
		//===============
		
		rEqualSign = new TextView(rContext);
		rEqualSign.setText("=");
		rEqualSign.setTextSize(45);
		rEqualSign.setId(11);
		rEqualSign.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
				
		RelativeLayout.LayoutParams rEqLP = CustomObject.getCustomParams(LayoutParams.MATCH_PARENT, 100, new int[]{RelativeLayout.CENTER_IN_PARENT});
		rEqLP.addRule(RelativeLayout.BELOW, box1.getId());
		
		
		//================
		
		box2 = new LinearLayout(rContext);
		box2.setOrientation(LinearLayout.VERTICAL);
		

		RelativeLayout.LayoutParams rInput2LP = CustomObject.getCustomParams(LayoutParams.MATCH_PARENT, 120, new int[]{RelativeLayout.CENTER_IN_PARENT});
		box2.addView(rInput2, rInput2LP);
		
		RelativeLayout.LayoutParams rSpinner2LP = CustomObject.getCustomParams(LayoutParams.MATCH_PARENT, 120, new int[]{RelativeLayout.CENTER_IN_PARENT});
		rSpinner2LP.addRule(RelativeLayout.BELOW, rInput2.getId());
		box2.addView(rType2,rSpinner2LP);
		
	
		
		box3 = new RelativeLayout(rContext);
		box3.setBackgroundColor(Color.parseColor("#0099cc"));
		box3.setPadding(15, 5, 10, 5);
		box3.setGravity(Gravity.CENTER_VERTICAL);
		
		lastRefreshed = new TextView(rContext);
		lastRefreshed.setText("Not refreshed");
		lastRefreshed.setTextColor(Color.WHITE);
		
		lastRefreshed.setTextSize(12);
		
		RelativeLayout.LayoutParams rLP = CustomObject.getCustomParams(LayoutParams.WRAP_CONTENT, 60, new int[]{RelativeLayout.ALIGN_PARENT_RIGHT});
		
		Button refresh = new Button(rContext);
		refresh.setBackgroundColor(Color.WHITE);
		refresh.setText("Get Rates");
		refresh.setHeight(80);
		refresh.setTextSize(12);
		refresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getRates();
				refreshDate();
				
			}

		});
		

		
		box3.addView(lastRefreshed);
		box3.addView(refresh,rLP);
		
		RelativeLayout.LayoutParams box1LP = CustomObject.getCustomParams(rContext.getResources().getDisplayMetrics().widthPixels - 20, 240, new int[]{RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.CENTER_HORIZONTAL});

		
		RelativeLayout.LayoutParams box2LP = CustomObject.getCustomParams(rContext.getResources().getDisplayMetrics().widthPixels - 20, 240, new int[]{RelativeLayout.CENTER_HORIZONTAL});
		box2LP.addRule(RelativeLayout.BELOW,rEqualSign.getId());
		
		RelativeLayout.LayoutParams box3LP = CustomObject.getCustomParams(rContext.getResources().getDisplayMetrics().widthPixels, 90, new int[]{RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.ALIGN_PARENT_BOTTOM});
		
		this.addView(box1,box1LP);
		this.addView(rEqualSign,rEqLP);
		this.addView(box2,box2LP);
		this.addView(box3,box3LP);
		
		

	}
	


	private void refreshDate() {
		lastRefreshed.setText("Last Refreshed : " + new Date().toString());
		
	}
	private TextView getCustomTextView() {
		
		TextView tv = new TextView(rContext);
		tv.setBackgroundColor(Color.parseColor("#63879F"));
		tv.setOnClickListener(new CustomOnclickListener());
		tv.setGravity(Gravity.CENTER);
		tv.setText(ConversionTypes.getCurrencyTypes()[133]);
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
		
		if(isOnline()){
		
			if(i == 0 ){
				Currency c = new Currency(getLocation(rType1), getLocation(rType2), Double.valueOf(rInput1.getText().toString()));
				
				rInput2.removeTextChangedListener(rTextListener);
				rInput2.setText(c.getResult());
				rInput2.addTextChangedListener(rTextListener);
			}
			
			if(i == 1 ){
				Currency c = new Currency(getLocation(rType2), getLocation(rType1), Double.valueOf(rInput2.getText().toString()));
				rInput1.removeTextChangedListener(rTextListener);
				rInput1.setText(c.getResult());
				rInput1.addTextChangedListener(rTextListener);
			}
		
		}
		else{
			Toast.makeText(rContext, "No Internet connection.", Toast.LENGTH_SHORT).show();
		}
	
	}

	
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
