package com.rokzin.converto.storage;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.rokzin.converto.storage.Store.StoreItem;
import com.rokzin.converto.utils.CustomObject;
import com.rokzin.converto.utils.PreferenceSet;

public class StoreView extends RelativeLayout{


	private static Context rContext;
	private static ListView list;

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
		
		list = new ListView(rContext);
		RelativeLayout.LayoutParams lLP = CustomObject.getCustomParams(rContext.getResources().getDisplayMetrics().widthPixels, LayoutParams.WRAP_CONTENT, new int[]{RelativeLayout.ALIGN_PARENT_TOP});
		this.addView(list,lLP);
	}
	
	public void refresh() {
		Store s = new Store();
		ArrayList<StoreItem> l = s.retrieveValues();
		list.setAdapter(new StoreItemBaseAdapter(rContext, l));
		
	}
	public static void addAndRefresh(String value) {
		Store s = new Store(value,rContext);
		ArrayList<StoreItem> l = s.retrieveValues();
		list.setAdapter(new StoreItemBaseAdapter(rContext, l));
	}

}
