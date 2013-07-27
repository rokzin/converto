package com.rokzin.converto.units;

import java.util.ArrayList;

import com.rokzin.converto.core.ResultItem;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.Formatting;

public class Speed {

	ArrayList<ResultItem> rValues = new ArrayList<ResultItem>();
	double cVals[] = { 1, 0.44704, 26.8224, 1609.344, 299792459, 300, 1/3.6, 0.0254, 0.00042333, 0.0000070555, 0.3048, 0.00508, 0.0000846666};

	public Speed(int type, double value) {

		/*
		 * allowed type parameter values
		 * 0. m/s
		 * 1. mile/hr
		 * 2. mile/min
		 * 3. mile/sec
		 * 4. Speed of light (Vac)
		 * 5. Speed of sound
		 * 6. km/hr
		 * 7. inch/sec
		 * 8. inch/min
		 * 9. inch/hr
		 * 10. ft/sec
		 * 11. ft/min
		 * 12. ft/hr
		 */

		double speed = 0;

		speed = (value * cVals[type]);
		convertFromSpeed(speed);

	}

	private void convertFromSpeed(double speed) {
		// calculate results
		int cindex = 0, uindex = 0;
		for (cindex = 0, uindex = 0; cindex < ConversionTypes.getSpeedTypes().length && uindex < ConversionTypes.getSpeedTypes().length ; cindex++, uindex++) {
			ResultItem rI = new ResultItem();
			rI.setValue(Formatting.roundOff(speed / cVals[cindex]));
			rI.setUnitType(ConversionTypes.getSpeedTypes()[uindex]);
			rValues.add(rI);
		}

	}

	public ArrayList<ResultItem> getValues() {
		return rValues;
	}

}
