package com.rokzin.converto.units;

import java.util.ArrayList;

import com.rokzin.converto.core.ResultItem;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.Formatting;

public class Angle {

	ArrayList<ResultItem> rValues = new ArrayList<ResultItem>();
	double cVals[] = { 0.016667, 0.000278, 360, 1, 0.9, 0.9, 0.05625, 0.06, 0.057143, 45, 90, 57.29578, 360, 60, 30, 360 };

	public Angle(int type, double value) {

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

		double rAngle = 0;

		rAngle = (value * cVals[type]);
		convertFromAngle(rAngle);

	}

	private void convertFromAngle(double rAngle) {
		// calculate results
		int cindex = 0, uindex = 0;
		for (cindex = 0, uindex = 0; cindex < 16 && uindex < 16; cindex++, uindex++) {
			ResultItem rI = new ResultItem();
			rI.setValue(Formatting.roundOff(rAngle / cVals[cindex]));
			rI.setUnitType(ConversionTypes.getAngleTypes()[uindex]);
			rValues.add(rI);
		}

	}

	public ArrayList<ResultItem> getValues() {
		return rValues;
	}

}
