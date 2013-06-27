package com.rokzin.converto.units;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.os.StrictMode;
import android.util.Log;

import com.rokzin.converto.utils.ConversionTypes;

public class Currency {

	private List<String> results = new ArrayList<String>();

	private String rConvertFrom;

	public Currency(String convertFrom) {
		rConvertFrom = convertFrom;
		new Thread(new Runnable() {
			public void run() {
				getdata(rConvertFrom);

			}
		}).start();

	}

	public List<String> getResults() {
		return results;
	}

	private String buildURL(String convertFrom) {

		String conversions = "";
		boolean doOnce = true;
		for (String convertto : ConversionTypes.getCurrencyTypes()) {
			if (doOnce) {
				conversions = conversions + convertFrom + convertto + "=X";

			}
			else {
				conversions = conversions + "," + convertFrom + convertto + "=X";
			}
			doOnce = false;
		}

		String source = "http://finance.yahoo.com/d/quotes.csv?e=.csv&f=c4l1&s=" + conversions;

		return source;

	}

	public void getdata(String convertFrom) {

		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			URL url = new URL(buildURL(convertFrom));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			readStream(con.getInputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void readStream(InputStream in) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null) {
				results.add(line);
				Log.d("debuggin", line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
