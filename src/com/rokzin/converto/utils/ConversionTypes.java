package com.rokzin.converto.utils;

import java.util.ArrayList;
import java.util.List;

public class ConversionTypes {
	public static List<String> currencyTypes;

	public static String[] getAngleTypes() {

		String angleTypes[] = { "arcminute", "arcsecond", "circle", "degree", "gon", "grad", "mil (nano)", "mil (soviet union)", "mil (sweden)", "octant",
				"quadrant", "radian", "revolution", "sextant", "sign", "turn" };

		return angleTypes;
	}

	public static String[] getAreaTypes() {

		String angleTypes[] = { "Sq mm", "Sq Cm", "Sq M", "Sq Km", "Sq In", "Sq Ft", "Sq Yd", "Sq Miles", "Acre", "Are", "Sq Rod" };

		return angleTypes;
	}

	public static String[] getTemperatureTypes() {

		String angleTypes[] = { "C", "F", "K", "R", "De", "N", "Re", "Ro" };

		return angleTypes;
	}

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
