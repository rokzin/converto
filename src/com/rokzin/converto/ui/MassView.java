package com.rokzin.converto.ui;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.rokzin.converto.core.ICustomView;
import com.rokzin.converto.units.Mass;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.Formatting;
import com.rokzin.converto.utils.PreferenceSet;
public class MassView extends CustomView implements ICustomView {

	Mass rMass;
	public MassView(Context context) {
		super(context);
		initialize();

	}
	private void initialize() {
		super.initialize(PreferenceSet.MASS);

		setSpinnerValues(ConversionTypes.getMassTypes());

		rSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long id) {
				if (!Formatting.isEmptyOrNull(rInput)) {
					Integer type = (int) (long) id;
					rMass = new Mass(type, Double.valueOf(rInput.getText().toString()));
					setResults(rMass.getValues());
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				rMass = new Mass(0, Double.valueOf(rInput.getText().toString()));
				setResults(rMass.getValues());

			}
		});

		rInput.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				if (s.toString().equals("")) {
				}
				else {
					rMass = new Mass(rSpinner.getSelectedItemPosition(), Double.parseDouble(s.toString()));
					setResults(rMass.getValues());
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
		rMass = new Mass(rSpinner.getSelectedItemPosition(), Double.parseDouble(rInput.getText().toString()));
		setResults(rMass.getValues());

	}


}