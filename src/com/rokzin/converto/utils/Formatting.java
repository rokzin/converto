package com.rokzin.converto.utils;

import java.text.DecimalFormat;

import android.widget.EditText;

public class Formatting {

	public static double roundOff(double value) {

		DecimalFormat fourDForm = new DecimalFormat("#.##########");
		return Double.valueOf(fourDForm.format(value));

	}

	public static boolean isEmptyOrNull(EditText editText) {

		String test = editText.getText().toString();

		if (test.equals("") || test.equals(null)) {
			return true;
		}

		return false;
	}
}
