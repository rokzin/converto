package com.rokzin.converto.currency;

import java.util.ArrayList;

import android.util.Log;

import com.rokzin.converto.utils.ConversionTypes;

public class Currency {
	
	public static ArrayList<Double> RATES = new ArrayList<Double>();

	private int rFrom;
	private int rTo;
	private String result;
	private double rValue =0 ;
	
	public Currency(int from,int to, double value) {
		rTo = to;
		rFrom = from;

		rValue = value * RATES.get(rTo);
		convertFromCurrency(rValue);

	}

	private void convertFromCurrency(double rAngle) {
		result = String.valueOf(rAngle / RATES.get(rFrom));
	}
	
	public String getResult(){
		return result;
	}



	public static void getRates() {
		RATES.clear();
		String conversions="";
		for (int i = 0; i < ConversionTypes.getCurrencyTypes().length; i++) {
				conversions = conversions + ConversionTypes.getCurrencyTypes()[133]+ ConversionTypes.getCurrencyTypes()[i]+"=X,";
		}
		String source = "http://finance.yahoo.com/d/quotes.csv?e=.csv&f=c4l1&s=" +conversions;
		Log.d("ConverToLog", source);
		new HttpURLRequest(source);

	}

}
