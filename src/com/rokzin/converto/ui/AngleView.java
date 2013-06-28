package com.rokzin.converto.ui;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.rokzin.converto.core.ICustomView;
import com.rokzin.converto.units.Angle;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.Formatting;
import com.rokzin.converto.utils.PreferenceSet;

public class AngleView extends CustomView implements ICustomView {

	Angle rAngle;

	public AngleView(Context context) {
		super(context);
		initialize();

	}

	private void initialize() {
		super.initialize(PreferenceSet.ANGLE);

		/*
		 * allowed type parameter values
		 * 1. arcminute
		 * 2. arcsecond
		 * 3. circle
		 * 4. degree
		 * 5. gon
		 * 6. grad
		 * 7. mil (nano)
		 * 8. mil (soviet union)
		 * 9. mil (sweden)
		 * 10. octant
		 * 11. quadrant
		 * 12. radian
		 * 13. revolution
		 * 14. sextant
		 * 15. sign
		 * 16. turn
		 */

		setSpinnerValues(ConversionTypes.getAngleTypes());

		rSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long id) {
				if (!Formatting.isEmptyOrNull(rInput)) {
					Integer type = (int) (long) id;
					Log.d("fdsgsfedgksfdjgfdshg", "" + type);
					rAngle = new Angle(type, Double.valueOf(rInput.getText().toString()));
					setResults(rAngle.getValues());
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				rAngle = new Angle(0, Double.valueOf(rInput.getText().toString()));
				setResults(rAngle.getValues());

			}
		});

		rInput.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				if (s.toString().equals("")) {
				}
				else {
					rAngle = new Angle(rSpinner.getSelectedItemPosition(), Double.parseDouble(s.toString()));
					setResults(rAngle.getValues());
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
		rAngle = new Angle(rSpinner.getSelectedItemPosition(), Double.parseDouble(rInput.getText().toString()));
		setResults(rAngle.getValues());

	}

}
