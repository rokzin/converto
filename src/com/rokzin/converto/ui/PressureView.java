package com.rokzin.converto.ui;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.rokzin.converto.core.ICustomView;
import com.rokzin.converto.units.Pressure;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.Formatting;
import com.rokzin.converto.utils.PreferenceSet;
public class PressureView extends CustomView implements ICustomView {

	private Pressure rPressure;
	
	public PressureView(Context context) {
		super(context);
		initialize();

	}
		private void initialize() {
			super.initialize(PreferenceSet.PRESSURE);

			setSpinnerValues(ConversionTypes.getPressureTypes());

			rSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long id) {
					if (!Formatting.isEmptyOrNull(rInput)) {
						Integer type = (int) (long) id;
						rPressure = new Pressure(type, Double.valueOf(rInput.getText().toString()));
						setResults(rPressure.getValues());
					}

				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					rPressure = new Pressure(0, Double.valueOf(rInput.getText().toString()));
					setResults(rPressure.getValues());

				}
			});

			rInput.addTextChangedListener(new TextWatcher() {
				public void afterTextChanged(Editable s) {
					if (s.toString().equals("")) {
					}
					else {
						rPressure = new Pressure(rSpinner.getSelectedItemPosition(), Double.parseDouble(s.toString()));
						setResults(rPressure.getValues());
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
			rPressure = new Pressure(rSpinner.getSelectedItemPosition(), Double.parseDouble(rInput.getText().toString()));
			setResults(rPressure.getValues());

		}

	


}