package com.rokzin.converto.storage;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.ListView;

import com.rokzin.converto.core.ICustomView;
import com.rokzin.converto.utils.PreferenceSet;

public class StoreView extends ListView implements ICustomView{


	private static Context rContext;

	public StoreView(Context context) {
		super(context);
		rContext = context;
		initialize();

	}
	
	public StoreView(Context context, AttributeSet attrs, int theme) {
		super(context, attrs, theme);
	}

	@Override
	public String toString() {
		return PreferenceSet.SAVEFORLATER;
	}
	
	private void initialize() {
		
		int orientation = ((WindowManager) rContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
		if (orientation == Surface.ROTATION_0 || orientation == Surface.ROTATION_180) {
			loadPotraitView();
		}
		else {
			loadLandscapeView();
		}
		
	}


	@Override
	public void reinitialize() {
		Store s = new Store();
		ArrayList<StoreItem> l = s.retrieveValues();
		setAdapter(new StoreItemBaseAdapter(rContext, l));
	}

	@Override
	public void loadLandscapeView() {

	}

	@Override
	public void loadPotraitView() {

		
	}

}
