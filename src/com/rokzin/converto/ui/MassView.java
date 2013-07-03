package com.rokzin.converto.ui;

import android.content.Context;

import com.rokzin.converto.core.ICustomView;
public class MassView extends CustomView implements ICustomView {
	private String TITLE = "Mass";

	public MassView(Context context) {
		super(context);
		initialize(TITLE);

	}

	@Override
	public void reinitialize() {
		// TODO Auto-generated method stub
		
	}

}