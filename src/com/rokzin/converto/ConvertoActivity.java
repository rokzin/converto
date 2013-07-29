package com.rokzin.converto;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.rokzin.converto.core.ICustomView;
import com.rokzin.converto.core.SlideHolder;
import com.rokzin.converto.storage.StoreView;
import com.rokzin.converto.ui.AngleView;
import com.rokzin.converto.ui.AreaView;
import com.rokzin.converto.ui.CurrencyView;
import com.rokzin.converto.ui.CustomView;
import com.rokzin.converto.ui.LengthView;
import com.rokzin.converto.ui.MassView;
import com.rokzin.converto.ui.PowerView;
import com.rokzin.converto.ui.PressureView;
import com.rokzin.converto.ui.SpeedView;
import com.rokzin.converto.ui.TemperatureView;
import com.rokzin.converto.ui.VolumeView;
import com.rokzin.converto.utils.CustomStringBuilder;
import com.rokzin.converto.utils.Formatting;
import com.rokzin.converto.utils.PreferenceSet;
import com.rokzin.converto.utils.SettingsActivity;

//Robin - Rohit
public class ConvertoActivity extends Activity {

	private class CustomMenuItemListener implements OnMenuItemClickListener{

		@Override
		public boolean onMenuItemClick(MenuItem item) {
			mSlideHolder.toggle();
			return true;
		}
		
	}
	public static int APP_HEIGHT;

	public static int APP_WIDTH;
	public static File file;
	private List<View> allViews = new ArrayList<View>();
	
	
	private SlideHolder mSlideHolder;
	private Menu options;
	private Context rContext;
	private OnSharedPreferenceChangeListener rPreferenceListener;

	public SharedPreferences rPreferences;
	public String savedInputValue;
	
	private ListView sideBar;
	
	private MenuItem type;
	
	private ViewSwitcher viewSwitcher;

	int showToast;
	
	private void checkOrientationAndLoadView(int state, View currentView) {

		if(currentView instanceof CurrencyView){
			
			if(!setupCurrencyRates(currentView)){
				currentView = allViews.get(0);
			}
		}

		/*
		 * 0: toggle silder
		 * 1: don't toggle slider
		 */
		if (state == 0) {
			mSlideHolder.toggle();
		}

		
		viewSwitcher.removeAllViews(); // clear any previous views
		viewSwitcher.addView(currentView);
		
		int orientation = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
		if (orientation == Surface.ROTATION_0 || orientation == Surface.ROTATION_180) {
			
				((ICustomView) currentView).loadPotraitView();
				((ICustomView) currentView).reinitialize();

		}
		else {
			
				((ICustomView) currentView).loadLandscapeView();
				((ICustomView) currentView).reinitialize();
		}


	}

	@SuppressLint("SdCardPath")
	private void createStorageFile() {
		File rDir = new File("/sdcard/converto/");
		
		rDir.mkdirs();
		file = new File(rDir, "ConverTo.txt");
		((StoreView)allViews.get(10)).reinitialize();
	}

	private void createViews() {
		
		TemperatureView temperatureView = new TemperatureView(ConvertoActivity.this);
		temperatureView.setId(0);
		LengthView lengthView = new LengthView(ConvertoActivity.this);
		lengthView.setId(1);
		MassView massView = new MassView(ConvertoActivity.this);
		massView.setId(2);
		PowerView powerView = new PowerView(ConvertoActivity.this);
		powerView.setId(3);
		PressureView pressureView = new PressureView(ConvertoActivity.this);
		pressureView.setId(4);
		SpeedView speedView = new SpeedView(ConvertoActivity.this);
		speedView.setId(5);
		VolumeView volumeView = new VolumeView(ConvertoActivity.this);
		volumeView.setId(6);
		AreaView areaView = new AreaView(ConvertoActivity.this);
		areaView.setId(7);
		AngleView angleView = new AngleView(ConvertoActivity.this);
		angleView.setId(8);
		CurrencyView currencyView = new CurrencyView(ConvertoActivity.this);
		currencyView.setId(9);
		StoreView storeView = new StoreView(ConvertoActivity.this);
		storeView.setId(10);
		
		allViews.add(temperatureView);
		allViews.add(lengthView);
		allViews.add(massView);
		allViews.add(powerView);
		allViews.add(pressureView);
		allViews.add(speedView);
		allViews.add(volumeView);
		allViews.add(areaView);
		allViews.add(angleView);
		allViews.add(currencyView);
		allViews.add(storeView);
			
	}

	private boolean setupCurrencyRates(View v) {
		if(shouldBeRefreshed()){
			if(isOnline()){
				CustomStringBuilder results = ((CurrencyView)v).getCurrencyRates();
				
				
		        if(results.getSize()<278){
		            Toast.makeText(rContext, "There was an error getting rates from the yahoo server.", Toast.LENGTH_SHORT).show();
		            return false;
		        } 
		        else{
		        	Toast.makeText(rContext, "Currency rates refreshed", Toast.LENGTH_SHORT).show();
		        	
		        	SharedPreferences.Editor prefEditor = rPreferences.edit();
			        prefEditor.putString("CurrencyRates", results.toString());
			        prefEditor.commit();
	
			        //saving the last refreshed date to shared preferences
			        Date date = new Date(System.currentTimeMillis());
			        prefEditor.putLong("LastRefreshed", date.getTime());
			        prefEditor.commit();
			        //========================================================
			        return true;
		        }
			}
			else{
				 Toast.makeText(rContext, "Network connection not detected.", Toast.LENGTH_SHORT).show();	
				 return false;
			}
		}
		else{
			return true;
		}
		
	}


	private void initialize() {
		// setting the roundoff on load
		Formatting.setRoundOff(Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(this).getString(PreferenceSet.PREF_ROUND_OFF, "2")));
		createViews();
		setupSidebar();
		setPreferenceChangeListener();
		createStorageFile();

	}

	private boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) rContext.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		checkOrientationAndLoadView(1, viewSwitcher.getCurrentView());

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_converto);
		rContext = getApplicationContext();
		mSlideHolder = (SlideHolder) findViewById(R.id.slideHolder);
		rPreferences = PreferenceManager.getDefaultSharedPreferences(ConvertoActivity.this);

		initialize();
		viewSwitcher.addView(allViews.get(0));
	}
	
	@Override
	public boolean onCreateOptionsMenu( Menu menu) {
		this.options = menu;
		type = options.add("Type");
		options.add("Settings");
		type.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		options.getItem(0).setTitle(viewSwitcher.getCurrentView().toString());
		type.setOnMenuItemClickListener(new CustomMenuItemListener());
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item == options.getItem(1)) {
			Intent i = new Intent(rContext, SettingsActivity.class);
			startActivityForResult(i, 1);
		}

		return true;
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		//super.onRestoreInstanceState(savedInstanceState);
		
		initialize();
		int viewID = savedInstanceState.getInt("View ID");
		
		if(viewID != 99){
			((CustomView)allViews.get(viewID)).setText(savedInstanceState.getString("InputText"));
			checkOrientationAndLoadView(1, allViews.get(viewID));
		}
		else{
			checkOrientationAndLoadView(1, allViews.get(0));
		}

		
		
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		//super.onSaveInstanceState(outState);
		if(viewSwitcher.getCurrentView() instanceof StoreView  || viewSwitcher.getCurrentView() instanceof CurrencyView){
			outState.putInt("View ID", 99);
		}
		else{
		outState.putInt("View ID", viewSwitcher.getCurrentView().getId());
		outState.putString("InputText", ((CustomView)viewSwitcher.getCurrentView()).getText());
		}
	}

	
	private void setPreferenceChangeListener() {
		
;

		rPreferenceListener = new SharedPreferences.OnSharedPreferenceChangeListener() {

			@Override
			public void onSharedPreferenceChanged(SharedPreferences sP, String key) {

				if (key.equals(PreferenceSet.PREF_ROUND_OFF)) {
					Formatting.setRoundOff(Integer.parseInt(sP.getString(PreferenceSet.PREF_ROUND_OFF, "2")));
					View v = viewSwitcher.getCurrentView();
					if(v instanceof StoreView || v instanceof CurrencyView){
						
					}
					else{
					((CustomView) v).reEnterText();
					}
				}

			}

		};

		rPreferences.registerOnSharedPreferenceChangeListener(rPreferenceListener);
		
		
		
	}

	private void setupSidebar() {
		
		sideBar = (ListView) findViewById(R.id.menu_list);
		viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher1);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(rContext, R.layout.menu_item, R.id.menu_item, PreferenceSet.getMenuItems());
		sideBar.setAdapter(adapter);
		sideBar.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View v, int id, long id2) {
				checkOrientationAndLoadView(0, allViews.get(id));
				options.getItem(0).setTitle(viewSwitcher.getCurrentView().toString());
							}

		});
		
	}
	
	private boolean shouldBeRefreshed() {
		Date now = new Date();
		Date lr = new Date(rPreferences.getLong("LastRefreshed", 0));

		
		if(now.after(lr)){

			if(now.getDay() == lr.getDay() && (now.getHours() - lr.getHours()<2)){
				return false;
			}
		}
		return true;
	}




}
