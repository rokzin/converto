package com.rokzin.converto.currency;


public class Currency {

	
	private Double result;
	
	public Currency(double from,double to, double value) {


		result =  value * to / from;
	}

	
	public Double getResult(){
		return result;
	}


}
