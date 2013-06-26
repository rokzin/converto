package com.rokzin.converto.utils;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.rokzin.converto.R;

public class SettingsActivity extends PreferenceActivity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.preferences);

	}

}