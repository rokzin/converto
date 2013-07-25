package com.rokzin.converto;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.rokzin.converto.ui.PowerView;
import com.rokzin.converto.ui.PressureView;
import com.rokzin.converto.ui.SpeedView;
import com.rokzin.converto.ui.TemperatureView;
import com.rokzin.converto.ui.VolumeView;
import com.rokzin.converto.utils.Formatting;
import com.rokzin.converto.utils.PreferenceSet;
import com.rokzin.converto.utils.SettingsActivity;

//Robin - Rohit
public class ConvertoActivity extends Activity {

	private SlideHolder mSlideHolder;
	private ViewSwitcher viewSwitcher;

	private ListView sideBar;
	private Menu options;
	private MenuItem type;
	
	
	public static int APP_HEIGHT;
	public static int APP_WIDTH;
	public SharedPreferences rPreferences;
	private OnSharedPreferenceChangeListener rPreferenceListener;

	private Context rContext;
	public static File file;
	
	public String savedInputValue;
	
	private List<View> allViews = new ArrayList<View>();
	
	private class CustomMenuItemListener implements OnMenuItemClickListener{

		@Override
		public boolean onMenuItemClick(MenuItem item) {
			mSlideHolder.toggle();
			return true;
		}
		
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

	private void initialize() {
		// setting the roundoff on load
		Formatting.setRoundOff(Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(this).getString(PreferenceSet.PREF_ROUND_OFF, "2")));
		createViews();
		setupSidebar();
		setPreferenceChangeListener();
		createStorageFile();

	}

	private void setPreferenceChangeListener() {
		
;

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

	private void createStorageFile() {
		File rDir = new File("/sdcard/converto/");
		
		rDir.mkdirs();
		file = new File(rDir, "ConverTo.txt");
		((StoreView)allViews.get(10)).refresh();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("View ID", viewSwitcher.getCurrentView().getId());
		outState.putString("InputText", ((CustomView)viewSwitcher.getCurrentView()).getText());
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		int viewID = savedInstanceState.getInt("View ID");
	initialize();
	checkOrientationAndLoadView(1, allViews.get(viewID));
		
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
		//menu.getItem(0).setTitle(currentView.toString());// set title
		viewSwitcher.addView(currentView);
		
		int orientation = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
		if (orientation == Surface.ROTATION_0 || orientation == Surface.ROTATION_180) {
			
			if (currentView instanceof ICustomView) {
				((ICustomView) currentView).loadPotraitView();
				((ICustomView) currentView).reinitialize();
			}
		}
		else {
			
			if (currentView instanceof ICustomView) {
				((ICustomView) currentView).loadLandscapeView();
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
