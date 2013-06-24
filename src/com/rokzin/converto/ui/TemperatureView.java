package com.rokzin.converto.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.rokzin.converto.units.Temperature;

public class TemperatureView extends CustomView {

	private String TITLE = "Temperature";

	private Temperature rTemperature;

	public TemperatureView(Context context) {
		super(context);
		initialize();

	}

	private void initialize() {
		super.initialize(TITLE);

		List<String> temperatureTypes = new ArrayList<String>();
		temperatureTypes.add("C");
		temperatureTypes.add("F");
		temperatureTypes.add("K");
		temperatureTypes.add("R");
		temperatureTypes.add("De");
		temperatureTypes.add("N");
		temperatureTypes.add("Re");
		temperatureTypes.add("Ro");
		setSpinnerValues(temperatureTypes);

		rSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long id) {
				Integer type = (int) (long) id;
				rTemperature = new Temperature(type, Double.valueOf(rInput.getText().toString()));
				setResults(rTemperature.getValues());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				rTemperature = new Temperature(0, Double.valueOf(rInput.getText().toString()));
				setResults(rTemperature.getValues());

			}
		});

	}
}