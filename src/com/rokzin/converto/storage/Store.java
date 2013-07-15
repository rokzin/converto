package com.rokzin.converto.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.rokzin.converto.ConvertoActivity;

public class Store {
	
	public class StoreItem{
		
		private Date date;
		private String value;

		public StoreItem(String value, Date date) {
			this.value = value;
			this.date = date;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	private Context rContext;
	private BufferedReader reader;
	
	
	public Store(String value,Context context){
		rContext = context;
		storeValue(value);
	}
	
	public Store() {}
	  private void storeValue(String value) {
		 
		 
		    

		    try {
		    	FileWriter fw = new FileWriter(ConvertoActivity.file,true);
		    	fw.write(value+","+new Date().toString()+"\n");
		    	fw.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }finally{
		    	Toast.makeText(rContext, value + " stored.", Toast.LENGTH_SHORT).show();
		    }
		
	  }
	  
	  public ArrayList<StoreItem> retrieveValues() {
		
		  ArrayList<StoreItem> savedData = new ArrayList<StoreItem>();
		    try {
		    	
		    	
		    	File dir = Environment.getExternalStorageDirectory();
		    	File yourFile = new File(dir, "converto/ConverTo.txt");
		    	
		    	InputStream is = new FileInputStream(yourFile);
		    	if(is != null){
		    		InputStreamReader iSR = new InputStreamReader(is);
		    		reader = new BufferedReader(iSR);
		    		
		    		String line;
		    		while((line=reader.readLine()) != null){
		    			String sl[] = line.split(",");
			        	Date d = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(sl[1]);
			        	StoreItem si = new StoreItem(sl[0], d);
			            savedData.add(si);
			        }
		    	}
		    
	        is.close();
		    } catch (Exception e) {
		    	Log.d("convertolog", e.getMessage().toString());
		    	e.printStackTrace();
		    }
		   return savedData;
	}
}
	
	



