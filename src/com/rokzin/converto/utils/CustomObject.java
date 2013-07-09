package com.rokzin.converto.utils;

import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

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
}
