package com.rokzin.converto.utils;

import java.text.DecimalFormat;

public class Formatting {

	public static double roundToFourDec(double value) {

		DecimalFormat fourDForm = new DecimalFormat("#.##########");
		return Double.valueOf(fourDForm.format(value));

	}
}
