package com.rokzin.converto.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class CustomObject {

	/**
	 * @param w : Width
	 * @param h : Height
	 * @param rules : Layout rules
	 * @return LayourParam object with custom rules and width and height
	 */
	public static LayoutParams getCustomParams(int w, int h, int[] rules) {
		RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(w, h);
		for (int i = 0; i < rules.length; i++) {
			rl.addRule(rules[i]);
		}
		return rl;
	}
	
	/**
	 * @param context
	 * @param id ID for the textview
	 * @param backgroundcolor Background color
	 * @param textColor Color of the text
	 * @param textSize Text Size
	 * @param text
	 * @return TextView object with specified properties
	 */
	public static TextView getCustomTextView(Context context, int id, int backgroundcolor, int textColor, int textSize, String text) {
		
		TextView tv = new TextView(context);
		tv.setBackgroundColor(backgroundcolor);
		tv.setGravity(Gravity.CENTER);
		tv.setText(text);
		tv.setTextColor(textColor);
		tv.setTextSize(textSize);
		tv.setId(id);
		return tv;
		
	}

	/**
	 * @param context
	 * @param textSize Size of the text displayed
	 * @param text Setting the textvalue 
	 * @param hint : Placeholder
	 * @param inputTypes Types of input 
	 * @param id
	 * @return Returns a edittext object with the specified preferences
	 */
	
	public static EditText getCustomInputBox(Context context, int textSize, String text, String hint, int[] inputTypes, int id) {
		EditText et = new EditText(context);
		et.setGravity(Gravity.CENTER);
		et.setTextSize(textSize);
		et.setHint(hint);
		
		for (int i = 0; i < inputTypes.length; i++) {
			et.setInputType(inputTypes[i]);
		}

		et.setText(text);
		et.setId(id);
		return et;
	}
	
}
