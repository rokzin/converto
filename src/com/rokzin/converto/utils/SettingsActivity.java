package com.rokzin.converto.utils;

import java.io.FileWriter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import com.rokzin.converto.ConvertoActivity;
import com.rokzin.converto.R;

public class SettingsActivity extends PreferenceActivity {
	
	private Preference clearHistory;
	public static String preferencesName;
	private Preference contactDeveloper;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		preferencesName = this.getPreferenceManager().getSharedPreferencesName();

		addPreferencesFromResource(R.xml.preferences);
		clearHistory = findPreference(PreferenceSet.PREF_CLEAR_HISTORY);
		clearHistory.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				AlertDialog.Builder alertBuilder = new AlertDialog.Builder(SettingsActivity.this);
				alertBuilder.setTitle("Saved History");
				alertBuilder.setMessage("Are you sure you want to clear all saved conversions?");
				alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						try {
					    	FileWriter fw = new FileWriter(ConvertoActivity.file);
					    	fw.write("");
					    	fw.close();
					    } catch (Exception e) {
					    	Toast.makeText(SettingsActivity.this, "History was not cleared due to an error.", Toast.LENGTH_SHORT).show();
					    }finally{
					    	Toast.makeText(SettingsActivity.this, "Saved conversions were cleared successfully.", Toast.LENGTH_SHORT).show();

					    }
					}
				});
				alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});

				
				Dialog alert = alertBuilder.create();
				alert.show();
				return true;
			}
		});
		
		contactDeveloper = findPreference(PreferenceSet.PREF_CONTACT_DEV);
		contactDeveloper.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822");
				i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"cnnovation@gmail.com"});
				i.putExtra(Intent.EXTRA_SUBJECT, "ConverTo Issue");
				i.putExtra(Intent.EXTRA_TEXT   , "Hello, \nThe issue I am facing with the app is described below : ");
				try {
				    startActivity(Intent.createChooser(i, "Send mail."));
				} catch (android.content.ActivityNotFoundException ex) {
				    Toast.makeText(SettingsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
				}
				return true;
			}
		});
		
	}
	
	
	
	

}