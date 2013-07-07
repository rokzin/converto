package com.rokzin.converto.units;

import com.rokzin.converto.core.CurrencyRates;

public class Currency {

	private int rFrom;
	private int rTo;
	private String result;
	private double rValue =0 ;
	public Currency(int from,int to, double value) {
		rTo = to;
		rFrom = from;

		rValue = value * CurrencyRates.conversionRatio.get(rTo);
		convertFromAngle(rValue);

	}

	private void convertFromAngle(double rAngle) {
		

			result = String.valueOf(rAngle / CurrencyRates.conversionRatio.get(rFrom));
		
	}
	
	public String getResult(){
		return result;
	}



}
