package com.rokzin.converto.currency;

import com.rokzin.converto.utils.Formatting;


public class Currency {

	
	private Double result;
	
	public Currency(double from,double to, double value) {


		result =  value * to / from;
	}

	
	public Double getResult(){
		return Formatting.roundOff(result);
	}


}
