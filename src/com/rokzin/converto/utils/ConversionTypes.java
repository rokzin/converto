package com.rokzin.converto.utils;


public class ConversionTypes {

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

	public static String[] getCurrencyTypes() {

		String currencyTypes[] = {"AED","ALL","ANG","ARS","AUD","AWG","BBD","BDT","BGN","BHD","BIF","BMD","BND","BOB","BRL","BSD","BTN","BWP","BYR","BZD","CAD","CHF","CLP","CNY","COP","CRC","CUP","CVE","CZK","DJF","DKK","DOP","DZD","ECS","EEK","EGP","ERN","ETB","EUR","FJD","FKP","GBP","GHC","GIP","GMD","GNF","GTQ","GYD","HKD","HNL","HRK","HTG","HUF","IDR","ILS","INR","IQD","IRR","ISK","JMD","JPD","JPY","KES","KHR","KMF","KPW","KRW","KWD","KYD","KZT","LAK","LBP","LKR","LRD","LSL","LTL","LVL","LYD","MAD","MDL","MKD","MMK","MNT","MOP","MRO","MTL","MUR","MVR","MWK","MXN","MYR","NAD","NGN","NIO","NOK","NPR","NZD","OMR","PAB","PEN","PGK","PHP","PKR","PLN","PYG","QAR","RON","RUB","RWF","SAR","SBD","SCR","SDG","SEK","SGD","SHP","SIT","SKK","SLL","SOS","STD","SVC","SYP","SZL","THB","TND","TOP","TRY","TTD","TWD","TZS","UAH","UGX","USD","UYU","VEF","VND","VUV","WST","XAF","XAG","XAL","XAU","XCD","XCP","XOF","XPD","XPF","XPT","YER","ZAR","ZMK"};
		return currencyTypes;

	}
	
	public static String[] getSpeedTypes() {

		String speedTypes[] = { "m/s", "mi/hr", "mi/min", "mi/s", "c", "s", "km/hr", "in/s", "in/min", "in/hr", "ft/s", "ft/min", "ft/hr" };

		return speedTypes;
	}
	/*
	 * allowed type parameter values
	 * 0. m/s
	 * 1. mile/hr
	 * 2. mile/min
	 * 3. mile/sec
	 * 4. Speed of light (Vac)
	 * 5. Speed of sound
	 * 6. km/hr
	 * 7. inch/sec
	 * 8. inch/min
	 * 9. inch/hr
	 * 10. ft/sec
	 * 11. ft/min
	 * 12. ft/hr
	 */

	
	
}
