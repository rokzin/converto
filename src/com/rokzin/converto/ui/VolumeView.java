package com.rokzin.converto.ui;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.rokzin.converto.core.ICustomView;
import com.rokzin.converto.units.Volume;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.Formatting;
import com.rokzin.converto.utils.PreferenceSet;

public class VolumeView extends CustomView implements ICustomView {

	Volume rVolume;

	public VolumeView(Context context) {
		super(context);
		initialize();

	}

	private void initialize() {
		super.initialize(PreferenceSet.VOLUME);

		/*
		 * 0. milliLiter
		 * 1. Liter
		 * 2. Gallon (US)
		 * 3. Imperial Gallon (UK)
		 * 4. fl Oz (US)
		 * 5. fl Oz (UK)
		 * 6. CC
		 * 7. Quart (US)
		 * 8. Quart (UK)
		 * 9. Pint (US)
		 * 10. Pint (UK)
		 * 11. Cubic MMs
		 * 12. Cubic Meters
		 * 13. Cubic Decimeter
		 * 14. Cubic Feet
		 * 15. Cubic Inches
		 * 16. Gill (US)
		 * 17. Gill (UK)
		 * 18. Oil Barrel US
		 * 19. Oil Barrel UK
		 * 20. Tablespoon US
		 * 21. Tablespoon UK
		 * 22. Teaspoon US
		 * 23. Teaspoon UK
		 * 24. Peck US
		 * 25. Peck UK
		 */

		setSpinnerValues(ConversionTypes.getVolumeTypes());

		rSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long id) {
				if (!Formatting.isEmptyOrNull(rInput)) {
					Integer type = (int) (long) id;
					rVolume = new Volume(type, Double.valueOf(rInput.getText().toString()));
					setResults(rVolume.getValues());
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				rVolume = new Volume(0, Double.valueOf(rInput.getText().toString()));
				setResults(rVolume.getValues());

			}
		});

		rInput.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				if (s.toString().equals("")) {
				}
				else {
					rVolume = new Volume(rSpinner.getSelectedItemPosition(), Double.parseDouble(s.toString()));
					setResults(rVolume.getValues());
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
		rVolume = new Volume(rSpinner.getSelectedItemPosition(), Double.parseDouble(rInput.getText().toString()));
		setResults(rVolume.getValues());

	}

}