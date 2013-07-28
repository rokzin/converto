package com.rokzin.converto.ui;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.rokzin.converto.core.ICustomView;
import com.rokzin.converto.units.Area;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.Formatting;
import com.rokzin.converto.utils.PreferenceSet;

public class AreaView extends CustomView implements ICustomView {

	Area rArea;

	public AreaView(Context context) {
		super(context);
		initialize();

	}

	private void initialize() {
		super.initialize(PreferenceSet.AREA);

		/**
		 * 0 : Square-Millimeter 1 : Square-Centimeter 2 : Square-Meter 3 :
		 * Square-Kilometer 4 : Square-inch 5 : Square-Feet 6 : Square-Yard 7 :
		 * Square-Mile 8 : Acre 9 : Are 10: Decare 11: Square Rod
		 */

		setSpinnerValues(ConversionTypes.getAreaTypes());

		rSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long id) {
				if (!Formatting.isEmptyOrNull(rInput)) {
					Integer type = (int) (long) id;
					rArea = new Area(type, Double.valueOf(rInput.getText().toString()));
					setResults(rArea.getValues());
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				rArea = new Area(0, Double.valueOf(rInput.getText().toString()));
				setResults(rArea.getValues());

			}
		});

		rInput.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				if (s.toString().equals("")) {
				}
				else {
					rArea = new Area(rSpinner.getSelectedItemPosition(), Double.parseDouble(s.toString()));
					setResults(rArea.getValues());
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
		});
	}

	@Override
	public void reinitialize() {
		super.reinitialize();
		rArea = new Area(rSpinner.getSelectedItemPosition(), Double.parseDouble(rInput.getText().toString()));
		setResults(rArea.getValues());

	}
	
	

}