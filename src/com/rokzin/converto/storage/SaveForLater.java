package com.rokzin.converto.storage;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.rokzin.converto.storage.Store.StoreItem;
import com.rokzin.converto.utils.CustomObject;
import com.rokzin.converto.utils.PreferenceSet;

public class SaveForLater extends RelativeLayout{


	private static Context rContext;
	private static ListView list;

	public SaveForLater(Context context) {
		super(context);
		rContext = context;
		initialize();

	}
	
	public SaveForLater(Context context, AttributeSet attrs, int theme) {
		super(context, attrs, theme);
	}

	@Override
	public String toString() {
		return PreferenceSet.SAVEFORLATER;
	}
	
	private void initialize() {
		
		list = new ListView(rContext);
		Button b = new Button(rContext);
		b.setText("get values");
		b.setId(1);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Store s = new Store();
				ArrayList<StoreItem> l = s.retrieveValues();
				
				list.setAdapter(new StoreItemBaseAdapter(rContext, l));
			}
		});
		
		RelativeLayout.LayoutParams bLP = CustomObject.getCustomParams(rContext.getResources().getDisplayMetrics().widthPixels, 70, new int[]{RelativeLayout.ALIGN_PARENT_TOP});
		this.addView(b, bLP);
		RelativeLayout.LayoutParams lLP = CustomObject.getCustomParams(rContext.getResources().getDisplayMetrics().widthPixels, LayoutParams.WRAP_CONTENT, new int[]{});
		lLP.addRule(RelativeLayout.BELOW, b.getId());
		
		this.addView(list,lLP);
	}
	
	public static void reInitialize() {
		Store s = new Store();
		list.setAdapter(new StoreItemBaseAdapter(rContext, s.retrieveValues()));
	}

}
