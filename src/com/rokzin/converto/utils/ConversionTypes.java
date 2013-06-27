package com.rokzin.converto.utils;

import java.util.ArrayList;
import java.util.List;

public class ConversionTypes {
	public static List<String> currencyTypes;

	public static List<String> getCurrencyTypes() {

		currencyTypes = new ArrayList<String>();
		currencyTypes.add("USD");
		currencyTypes.add("EUR");
		currencyTypes.add("GBP");
		currencyTypes.add("CNY");
		currencyTypes.add("INR");

		return currencyTypes;

	}
}
