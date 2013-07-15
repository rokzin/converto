package com.rokzin.converto;

import java.io.File;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Configuration;
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
import com.rokzin.converto.currency.Currency;
import com.rokzin.converto.storage.StoreView;
import com.rokzin.converto.ui.AngleView;
import com.rokzin.converto.ui.AreaView;
import com.rokzin.converto.ui.CurrencyView;
import com.rokzin.converto.ui.CustomView;
import com.rokzin.converto.ui.LengthView;
import com.rokzin.converto.ui.MassView;
import com.rokzin.converto.ui.TemperatureView;
import com.rokzin.converto.ui.VolumeView;
import com.rokzin.converto.utils.Formatting;
import com.rokzin.converto.utils.PreferenceSet;
import com.rokzin.converto.utils.SettingsActivity;

//Robin - Rohit
public class ConvertoActivity extends Activity {

	private SlideHolder mSlideHolder;
	private ViewSwitcher viewSwitcher;

	private ListView menu_items;
	private Menu menu;
	private LengthView lengthView;
	private VolumeView volumeView;
	private AreaView areaView;
	private CurrencyView currencyView;
	private MassView massView;
	private TemperatureView temperatureView;
	private AngleView angleView;
	public static int APP_HEIGHT;
	public static int APP_WIDTH;
	public SharedPreferences rPreferences;
	private OnSharedPreferenceChangeListener rPreferenceListener;
	private StoreView saveForLaterView;
	private Context rContext;
	public static File file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_converto);
		rContext = getApplicationContext();
		mSlideHolder = (SlideHolder) findViewById(R.id.slideHolder);
		rPreferences = PreferenceManager.getDefaultSharedPreferences(ConvertoActivity.this);

		initialize();

	}

	private void initialize() {
		
;
		// setting the roundoff on load
		Formatting.setRoundOff(Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(this).getString(PreferenceSet.PREF_ROUND_OFF, "2")));

		rPreferenceListener = new SharedPreferences.OnSharedPreferenceChangeListener() {

			@Override
			public void onSharedPreferenceChanged(SharedPreferences sP, String key) {

				if (key.equals(PreferenceSet.PREF_ROUND_OFF)) {
					Formatting.setRoundOff(Integer.parseInt(sP.getString(PreferenceSet.PREF_ROUND_OFF, "2")));
					View v = viewSwitcher.getCurrentView();
					((CustomView) v).reEnterText();
				}
			}

		};

		rPreferences.registerOnSharedPreferenceChangeListener(rPreferenceListener);
		menu_items = (ListView) findViewById(R.id.menu_list);
		viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher1);
		massView = new MassView(rContext);
		temperatureView = new TemperatureView(rContext);
		lengthView = new LengthView(rContext);
		volumeView = new VolumeView(rContext);
		areaView = new AreaView(rContext);
		currencyView = new CurrencyView(rContext);
		angleView = new AngleView(rContext);
		saveForLaterView = new StoreView(rContext);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(rContext, R.layout.menu_item, R.id.menu_item, PreferenceSet.getMenuItems());
		menu_items.setAdapter(adapter);
		menu_items.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View v, int id, long id2) {
				String selected_item = (String) menu_items.getItemAtPosition(id);
				if (selected_item == PreferenceSet.TEMPERATURE) {
					checkOrientationAndLoadView(0, temperatureView);

				}
				if (selected_item == PreferenceSet.MASS) {
					checkOrientationAndLoadView(0, massView);

				}
				if (selected_item == PreferenceSet.LENGTH) {
					checkOrientationAndLoadView(0, lengthView);

				}
				if (selected_item == PreferenceSet.VOLUME) {
					checkOrientationAndLoadView(0, volumeView);

				}
				if (selected_item == PreferenceSet.AREA) {
					checkOrientationAndLoadView(0, areaView);

				}
				 if (selected_item == PreferenceSet.CURRENCY) {
				 checkOrientationAndLoadView(0, currencyView);
				
				 }
				if (selected_item == PreferenceSet.ANGLE) {
					checkOrientationAndLoadView(0, angleView);
				}
				if (selected_item == PreferenceSet.SAVEFORLATER) {
					checkOrientationAndLoadView(0, saveForLaterView);
				}

			}

		});
		
		viewSwitcher.addView(angleView);
		createStorageFile();

	}

	private void createStorageFile() {
		File rDir = new File("/sdcard/converto/");
		
		rDir.mkdirs();
		file = new File(rDir, "ConverTo.txt");
		saveForLaterView.refresh();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
	//TODO
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
	//TODO
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem item_type = menu.add("Type");
		menu.add("Settings");
		item_type.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		menu.getItem(0).setTitle(angleView.toString());
		this.menu = menu;

		item_type.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				mSlideHolder.toggle();
				return true;
			}
		});

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item == menu.getItem(1)) {
			Intent i = new Intent(rContext, SettingsActivity.class);
			startActivityForResult(i, 1);
		}

		return true;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		checkOrientationAndLoadView(1, viewSwitcher.getCurrentView());

	}

	private void checkOrientationAndLoadView(int state, View currentView) {

		/*
		 * 0: toggle silder
		 * 1: don't toggle slider
		 */
		if (state == 0) {
			mSlideHolder.toggle();
		}

		
		viewSwitcher.removeAllViews(); // clear any previous views
		menu.getItem(0).setTitle(currentView.toString());// set title
		viewSwitcher.addView(currentView);
		
		int orientation = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
		if (orientation == Surface.ROTATION_0 || orientation == Surface.ROTATION_180) {
			
			if (currentView instanceof ICustomView) {
				((CustomView) currentView).loadPotraitView();
				((ICustomView) currentView).reinitialize();
			}
		}
		else {
			
			if (currentView instanceof ICustomView) {
				((CustomView) currentView).loadLandscapeView();
				((ICustomView) currentView).reinitialize();
			}
		}
		
		
		if(currentView instanceof CurrencyView && shouldBeRefreshed()){
			
			getCurrencyRates();
			
		}

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

	private void getCurrencyRates() {

		ExecutorService executor = Executors.newFixedThreadPool(1);

		FutureTask<String> future = new FutureTask<String>(
                new Callable<String>()
                {

					@Override
					public java.lang.String call() throws Exception {
						Currency.getRates();
						
						return "";
					}
                   
                });
		
        executor.execute(future);
 
        //saving the last refreshed date to shared preferences
        Date date = new Date(System.currentTimeMillis());
        SharedPreferences.Editor prefEditor = rPreferences.edit();
        prefEditor.putLong("LastRefreshed", date.getTime());
        prefEditor.commit();
        //========================================================
        
        Toast.makeText(rContext, "Currency rates refreshed", Toast.LENGTH_SHORT).show();
		
	}

}
