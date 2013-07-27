package com.rokzin.converto.ui;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.rokzin.converto.core.ICustomView;
import com.rokzin.converto.units.Temperature;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.Formatting;
import com.rokzin.converto.utils.PreferenceSet;

public class TemperatureView extends CustomView implements ICustomView {

	private Temperature rTemperature;

	public TemperatureView(Context context) {
		super(context);
		initialize();

	}

	private void initialize() {
		super.initialize(PreferenceSet.TEMPERATURE);

		setSpinnerValues(ConversionTypes.getTemperatureTypes());

		rSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long id) {
				if (!Formatting.isEmptyOrNull(rInput)) {
					Integer type = (int) (long) id;
					rTemperature = new Temperature(type, Double.valueOf(rInput.getText().toString()));
					setResults(rTemperature.getValues());
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				rTemperature = new Temperature(0, Double.valueOf(rInput.getText().toString()));
				setResults(rTemperature.getValues());

			}
		});

		rInput.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				if (s.toString().equals("")) {
				}
				else {
					rTemperature = new Temperature(rSpinner.getSelectedItemPosition(), Double.parseDouble(s.toString()));
					setResults(rTemperature.getValues());
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
		if(Formatting.isEmptyOrNull(rInput)){
			setText("1");
		}
		rTemperature = new Temperature(rSpinner.getSelectedItemPosition(), Double.parseDouble(rInput.getText().toString()));
		setResults(rTemperature.getValues());
	}
}