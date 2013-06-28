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

		String angleTypes[] = { "Sq mm", "Sq Cm", "Sq M", "Sq Km", "Sq In", "Sq Ft", "Sq Yd", "Sq Miles", "Acre", "Are", "Decare", "Sq Rod" };

		return angleTypes;
	}

	public static String[] getVolumeTypes() {

		String angleTypes[] = { "ml", "L", "Gal", "Imp. Gal", "Fl Oz", "Fl Oz (UK)", "CC", "Quart", "Quart (UK)", "Pint", "Pint (UK)", "Cubic mms",
				"Cubic Mts", "Cubic Dec(s)", "Cubic Ft", "Cubic In(s)", "Gill", "Gill (UK)", "Oil Barrel", "Oil Barrel (UK)", "Tbspn", "Tbspn (UK)", "Teaspn",
				"Teaspn (UK)", "Peck", "Peck (UK)" };

		return angleTypes;
	}

	public static String[] getTemperatureTypes() {

		String angleTypes[] = { "C", "F", "K", "R", "De", "N", "Re", "Ro" };

		return angleTypes;
	}

	public static String[] getLengthTypes() {

		String angleTypes[] = { "mm(s)", "cm(s)", "m(s)", "km(s)", "in(s)", "ft(s)", "yard(s)", "miles(s)" };

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
