package com.rokzin.converto.ui;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.rokzin.converto.core.ICustomView;
import com.rokzin.converto.units.Power;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.Formatting;
import com.rokzin.converto.utils.PreferenceSet;
public class PowerView extends CustomView implements ICustomView {
	
	Power rPower;

	public PowerView(Context context) {
		super(context);
		initialize();

	}

	private void initialize() {
		super.initialize(PreferenceSet.POWER);


		setSpinnerValues(ConversionTypes.getPowerTypes());

		rSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long id) {
				if (!Formatting.isEmptyOrNull(rInput)) {
					Integer type = (int) (long) id;
					Log.d("fdsgsfedgksfdjgfdshg", "" + type);
					rPower = new Power(type, Double.valueOf(rInput.getText().toString()));
					setResults(rPower.getValues());
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				rPower = new Power(0, Double.valueOf(rInput.getText().toString()));
				setResults(rPower.getValues());

			}
		});

		rInput.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				if (s.toString().equals("")) {
				}
				else {
					rPower = new Power(rSpinner.getSelectedItemPosition(), Double.parseDouble(s.toString()));
					setResults(rPower.getValues());
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
		rPower = new Power(rSpinner.getSelectedItemPosition(), Double.parseDouble(rInput.getText().toString()));
		setResults(rPower.getValues());

	}


}