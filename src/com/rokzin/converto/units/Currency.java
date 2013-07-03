package com.rokzin.converto.units;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.os.StrictMode;
import android.util.Log;

import com.rokzin.converto.core.ResultItem;

public class Currency {

	private ArrayList<ResultItem> results = new ArrayList<ResultItem>();
	private String rConvertTo;
	private String rConvertFrom;

	public Currency(String convertFrom, String ConvertTo, double value) {
		rConvertFrom = convertFrom;
		rConvertTo = ConvertTo;
	
		getdata(value);
		
	
	}


	public ArrayList<ResultItem> getResults() {
		return results;
	}

	private String buildURL() {

		String source = "http://finance.yahoo.com/d/quotes.csv?e=.csv&f=c4l1&s=" + rConvertFrom+rConvertTo+"=X";

		return source;

	}

	public void getdata(double value) {

		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			URL url = new URL(buildURL());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			readStream(con.getInputStream(), value);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void readStream(InputStream in, double value) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null) {
				String splitLine[] = line.split(",");
				splitLine[1] = splitLine[1].replace("\"", "");
				ResultItem ri = new ResultItem();
				ri.setValue(Double.valueOf(splitLine[1])*value);
				ri.setUnitType(rConvertTo);
				results.add(ri);
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
