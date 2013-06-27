package com.rokzin.converto.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.rokzin.converto.units.Volume;
import com.rokzin.converto.utils.Formatting;
import com.rokzin.converto.utils.PreferenceSet;

public class VolumeView extends CustomView {

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

		List<String> volumeTypes = new ArrayList<String>();
		volumeTypes.add("ml");
		volumeTypes.add("L");
		volumeTypes.add("Gal");
		volumeTypes.add("Imp. Gal");
		volumeTypes.add("Fl Oz");
		volumeTypes.add("Fl Oz (UK)");
		volumeTypes.add("CC");
		volumeTypes.add("Quart");
		volumeTypes.add("Quart (UK)");
		volumeTypes.add("Pint");
		volumeTypes.add("Pint (UK)");
		volumeTypes.add("Cubic mms");
		volumeTypes.add("Cubic Mts");
		volumeTypes.add("Cubic Dec(s)");
		volumeTypes.add("Cubic Ft");
		volumeTypes.add("Cubic In(s)");
		volumeTypes.add("Gill");
		volumeTypes.add("Gill (UK)");
		volumeTypes.add("Oil Barrel");
		volumeTypes.add("Oil Barrel (UK)");
		volumeTypes.add("Tbspn");
		volumeTypes.add("Tbspn (UK)");
		volumeTypes.add("Teaspn");
		volumeTypes.add("Teaspn (UK)");
		volumeTypes.add("Peck");
		volumeTypes.add("Peck (UK)");

		setSpinnerValues(volumeTypes);

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

}