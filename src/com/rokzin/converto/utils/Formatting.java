package com.rokzin.converto.utils;

import java.text.DecimalFormat;

import android.widget.EditText;

public class Formatting {
	private static DecimalFormat decimalFormat;

	public static void setRoundOff(int parseInt) {
		switch (parseInt) {
		case 2: {
			decimalFormat = new DecimalFormat("#.##");
			break;
		}
		case 4: {
			decimalFormat = new DecimalFormat("#.####");
			break;
		}

		case 6: {
			decimalFormat = new DecimalFormat("#.######");
			break;
		}

		case 8: {
			decimalFormat = new DecimalFormat("#.########");
			break;

		}
		default: {
			decimalFormat = new DecimalFormat("#.####");
			break;
		}
		}
	}

	public static Double roundOff(double value) {
		return Double.valueOf(decimalFormat.format(value));
	}

	public static boolean isEmptyOrNull(EditText editText) {

		String test = editText.getText().toString();

		if (test.equals("") || test.equals(null)) {
			return true;
		}

		return false;
	}
}
