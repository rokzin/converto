package com.rokzin.converto.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.rokzin.converto.units.Length;
import com.rokzin.converto.utils.Formatting;
import com.rokzin.converto.utils.PreferenceSet;

public class LengthView extends CustomView {

	Length rLength;

	public LengthView(Context context) {
		super(context);
		initialize();

	}

	private void initialize() {
		super.initialize(PreferenceSet.LENGTH);

		/**
		 * 0 : Millimeter 1 : Centimeter 2 : Meter 3 : Kilometer 4 : inch 5 :
		 * feet 6 : yard 7 : mile 8 : microinch
		 */

		List<String> lengthTypes = new ArrayList<String>();
		lengthTypes.add("mms");
		lengthTypes.add("cms");
		lengthTypes.add("Ms");
		lengthTypes.add("Kms");
		lengthTypes.add("inches");
		lengthTypes.add("ft");
		lengthTypes.add("Yard");
		lengthTypes.add("Miles");
		lengthTypes.add("microIn(s)");

		setSpinnerValues(lengthTypes);

		rSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long id) {
				if (!Formatting.isEmptyOrNull(rInput)) {
					Integer type = (int) (long) id;
					rLength = new Length(type, Double.valueOf(rInput.getText().toString()));
					setResults(rLength.getValues());
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				rLength = new Length(0, Double.valueOf(rInput.getText().toString()));
				setResults(rLength.getValues());

			}
		});

		rInput.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				if (s.toString().equals("")) {
				}
				else {
					rLength = new Length(rSpinner.getSelectedItemPosition(), Double.parseDouble(s.toString()));
					setResults(rLength.getValues());
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
		});
	}

}