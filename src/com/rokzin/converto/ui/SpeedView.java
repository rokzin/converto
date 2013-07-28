package com.rokzin.converto.ui;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.rokzin.converto.core.ICustomView;
import com.rokzin.converto.units.Speed;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.Formatting;
import com.rokzin.converto.utils.PreferenceSet;

public class SpeedView extends CustomView implements ICustomView {

	private Speed rSpeed;
	public SpeedView(Context context) {
		super(context);
		initialize();

	}

	
		private void initialize() {
			super.initialize(PreferenceSet.SPEED);

			setSpinnerValues(ConversionTypes.getSpeedTypes());

			rSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long id) {
					if (!Formatting.isEmptyOrNull(rInput)) {
						Integer type = (int) (long) id;
						rSpeed = new Speed(type, Double.valueOf(rInput.getText().toString()));
						setResults(rSpeed.getValues());
					}

				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					rSpeed = new Speed(0, Double.valueOf(rInput.getText().toString()));
					setResults(rSpeed.getValues());

				}
			});

			rInput.addTextChangedListener(new TextWatcher() {
				public void afterTextChanged(Editable s) {
					if (s.toString().equals("")) {
					}
					else {
						rSpeed = new Speed(rSpinner.getSelectedItemPosition(), Double.parseDouble(s.toString()));
						setResults(rSpeed.getValues());
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
			rSpeed = new Speed(rSpinner.getSelectedItemPosition(), Double.parseDouble(rInput.getText().toString()));
			setResults(rSpeed.getValues());
		}
	

}