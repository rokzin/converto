package com.rokzin.converto.currency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpURLRequest  {
	
	private static double result;
	

	public double getResult() {
		return result;
	}
	public HttpURLRequest(String URL) {
		connect(URL);
	}

	public void connect(String url)
	{

	    HttpClient httpclient = new DefaultHttpClient();
	    HttpGet httpget = new HttpGet(url); 

	    HttpResponse response;
	    try {
	        response = httpclient.execute(httpget);
	        //Log.i("ConverToLog",response.getStatusLine().toString());
	        HttpEntity entity = response.getEntity();

	        if (entity != null) {

	            InputStream instream = entity.getContent();
	            convertStreamToString(instream);
	            
	            instream.close();
	        }


	    } catch (Exception e) {
	    	 Log.i("ConverToLog",e.toString());
	    }
	}

	private void convertStreamToString(InputStream is) {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    String line = null;
	    try {
	    	int i = 0;
	        while ((line = reader.readLine()) != null) {
	        	i++;
	            String splitLine[] = line.split(",");
				String convertedValue = splitLine[1].replace("\"", "");
				Log.i("ConverToLog","Adding "+convertedValue + " "+ i);
				if(Double.valueOf(convertedValue)==0.0){
					Currency.RATES.add(Double.valueOf(1.0));
				}
				else{
				
					Currency.RATES.add(Double.valueOf(convertedValue));
				}
	        }

	    } catch (IOException e) {
	        e.printStackTrace();

	    } finally {
	        try {
	            is.close();

	        } catch (IOException e) {
	            e.printStackTrace();

	        }
	    }
	}


}