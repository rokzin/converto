package com.rokzin.converto.utils;

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

	public static String[] getCurrencyTypes() {

		String currencyTypes[] = {"AUD","ALL","DZD","ARS","ALL","XAL","AWG","GBP","BSD","BHD","BDT","BBD","BYR","BZD","BMD","BTN","BOB","BWP","BRL","BND","BGN","BIF","CAD","CNY","KHR","CVE","KYD","XOF","XAF","CLP","COP","KMF","XCP","CRC","HRK","CUP","CZK","EUR","DKK","DJF","DOP","XCD","ECS","EGP","SVC","ERN","EEK","ETB","FKP","FJD","HKD","IDR","INR","GMD","GHC","GIP","XAU","GTQ","GNF","GYD","HTG","HNL","HUF","ISK","IRR","IQD","ILS","JPY","JMD","JPD","KZT","KES","KWD","LAK","LVL","LBP","LSL","LRD","LYD","LTL","MOP","MKD","MWK","MYR","MVR","MTL","MRO","MUR","MXN","MDL","MNT","MAD","MMK","NAD","NPR","ANG","NZD","NIO","NGN","KPW","NOK","OMR","XPF","PKR","XPD","PAB","PGK","PYG","PEN","PHP","XPT","PLN","QAR","RON","RUB","RWF","CHF","KRW","WST","STD","SAR","SCR","SLL","XAG","SGD","SKK","SIT","SBD","SOS","ZAR","LKR","SHP","SDG","SEK","SZL","SYP","USD","THB","TRY","TWD","TZS","TOP","TTD","TND","AED","UGX","UAH","UYU","VUV","VEF","VND","YER","ZMK","ZWD"};

		return currencyTypes;

	}
}
