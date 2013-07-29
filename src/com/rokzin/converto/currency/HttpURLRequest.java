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

import com.rokzin.converto.utils.CustomStringBuilder;

public class HttpURLRequest  {
	
	public CustomStringBuilder connect(String url)
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
	            CustomStringBuilder results = convertStreamToString(instream);
	            
	            instream.close();
	            return results;
	        }


	    } catch (Exception e) {
	    	 Log.i("ConverToLog",e.toString());
	    }
		return new CustomStringBuilder();
	}

	private CustomStringBuilder convertStreamToString(InputStream is) {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    String line = null;
	    CustomStringBuilder csb = new CustomStringBuilder();
	    try {
	    	int i =0;
	    	
	        while ((line = reader.readLine()) != null) {
	            String splitLine[] = line.split(",");
				String convertedValue = splitLine[1].replace("\"", "");
				Log.i("ConverToLog","Adding "+convertedValue + " "+i);
				
				if(Double.parseDouble(convertedValue)==0.0){
					csb.append(1);
					csb.append(",");
					
				}
				else{
					csb.append(convertedValue);
					csb.append(",");
				}
				i++;
	        
	        }

	    } catch (IOException e) {
	        e.printStackTrace();

	    } finally {
	        try {
	            is.close();
	            return csb;
	            

	        } catch (IOException e) {
	            e.printStackTrace();

	        }
	    }
		return new CustomStringBuilder();
	}


}
