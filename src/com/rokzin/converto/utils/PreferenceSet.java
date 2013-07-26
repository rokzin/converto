package com.rokzin.converto.utils;

import java.util.ArrayList;
import java.util.List;

public class PreferenceSet {

	public final static String TEMPERATURE = "Temperature";
	public final static String LENGTH = "Length";
	public final static String MASS = "Mass";
	public final static String POWER = "Power";
	public final static String PRESSURE = "Pressure";
	public final static String SPEED = "Speed";
	public final static String VOLUME = "Volume";
	public final static String AREA = "Area";
	public final static String CURRENCY = "Currency";
	public final static String DEFAULTVIEW = "DefaultView";
	public final static String CONTACTDEV = "ContactDev";
	public final static String ANGLE = "Angle";
	public final static String SAVEFORLATER = "Saved";
	
	

	public static final String PREF_ROUND_OFF = "prefRoundOff";
	public final static String PREF_CLEAR_HISTORY = "prefClearHistory";
	
	public static List<String> getMenuItems() {
		List<String> menu_items = new ArrayList<String>();
		menu_items.add(TEMPERATURE);
		menu_items.add(LENGTH);
		menu_items.add(MASS);
		menu_items.add(POWER);
		menu_items.add(PRESSURE);
		menu_items.add(SPEED);
		menu_items.add(VOLUME);
		menu_items.add(AREA);
		menu_items.add(ANGLE);
		menu_items.add(CURRENCY);
		menu_items.add(SAVEFORLATER);
		
		return menu_items;
	}

}
