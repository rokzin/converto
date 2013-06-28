//package com.rokzin.converto.ui;
//
//import android.content.Context;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemSelectedListener;
//
//import com.rokzin.converto.units.Currency;
//import com.rokzin.converto.utils.ConversionTypes;
//import com.rokzin.converto.utils.Formatting;
//import com.rokzin.converto.utils.PreferenceSet;
//
//public class CurrencyView extends CustomView {
//
//	private String selectedType;
//
//	public CurrencyView(Context context) {
//		super(context);
//		initialize();
//
//	}
//
//	private void initialize() {
//		super.initialize(PreferenceSet.CURRENCY);
//
//		setSpinnerValues(ConversionTypes.getCurrencyTypes());
//
//		rSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long id) {
//				if (!Formatting.isEmptyOrNull(rInput)) {
//					Integer type = (int) (long) id;
//					selectedType = rSpinner.getItemAtPosition(type).toString();
//					Currency rCurrency = new Currency(selectedType, Double.valueOf(rInput.getText().toString()));
//					setResults(rCurrency.getResults());
//				}
//
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//
//				Currency rCurrency = new Currency(rSpinner.getItemAtPosition(0).toString(), Double.valueOf(rInput.getText().toString()));
//				setResults(rCurrency.getResults());
//			}
//		});
//
//		rInput.addTextChangedListener(new TextWatcher() {
//			public void afterTextChanged(Editable s) {
//				if (s.toString().equals("")) {
//				}
//				else {
//					Currency rCurrency = new Currency(rSpinner.getItemAtPosition(0).toString(), Double.valueOf(rInput.getText().toString()));
//					setResults(rCurrency.getResults());
//				}
//			}
//
//			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//			}
//
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//			}
//		});
//	}
//
// }
