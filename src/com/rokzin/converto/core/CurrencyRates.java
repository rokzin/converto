package com.rokzin.converto.core;

import java.util.ArrayList;

import android.util.Log;

import com.rokzin.converto.utils.ConversionTypes;

public class CurrencyRates {
	
	public static ArrayList<Double> conversionRatio = new ArrayList<Double>();

	public CurrencyRates() {
		conversionRatio.clear();
		initialize();

	}

	private void initialize() {

    	getCurrencyRates();
    	
		
	}

	private void getCurrencyRates() {
		String conversions="";
		for (int i = 0; i < ConversionTypes.getCurrencyTypes().length; i++) {
		

				conversions = conversions + ConversionTypes.getCurrencyTypes()[133]+ ConversionTypes.getCurrencyTypes()[i]+"=X,";
				//Log.d("ConverToLog", ConversionTypes.getCurrencyTypes()[133]+ConversionTypes.getCurrencyTypes()[i]);
			
			
		}
		String source = "http://finance.yahoo.com/d/quotes.csv?e=.csv&f=c4l1&s=" +conversions;
		Log.d("ConverToLog", source);
		new HttpURLRequest(source);
		
//		for (int i = 0; i < conversionRatio.size(); i++) {
//			Log.d("ConverToLog", String.valueOf(conversionRatio.get(i)));
//			
//		}
		
	}
	

	
	public static class CurrencyConversion{
		
		private String rConverTo;
		
		private String rConvertFrom;
		
		private double rValue;
		
		public String getConverTo() {
			return rConverTo;
		}

		public void setConverTo(String converTo) {
			this.rConverTo = converTo;
		}

		public String getConvertFrom() {
			return rConvertFrom;
		}

		public void setConvertFrom(String convertFrom) {
			this.rConvertFrom = convertFrom;
		}

		public double getValue() {
			return rValue;
		}

		public void setValue(double value) {
			this.rValue = value;
		}

	}
	

}
