package com.rokzin.converto.units;

import java.util.ArrayList;

import com.rokzin.converto.core.ResultItem;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.Formatting;

public class Power {

	ArrayList<ResultItem> rValues = new ArrayList<ResultItem>();
	double cVals[] = { 1000000000 ,
						1000000 ,
						1000 ,
						1,
						4.1868 ,
						4186.8 ,
						745.6998715823 ,
						1.355817948331  ,
						1};

	public Power(int type, double value) {

		/*
		 * allowed type parameter values
		 * 0. GW (Gigawatt)
		 * 1. MW (Megawatt) 
		 * 2. KW (Kilowatt) 
		 * 3. W (Watt) 
		 * 4. Calories/s 
		 * 5. KCal/s
		 * 6. Horsepower int. 
		 * 7. ft-Lb-F/sec 
		 * 8. Joules/sec 
		 */

		double power = 0;

		power = (value * cVals[type]);
		convertFromPower(power);

	}

	private void convertFromPower(double power) {
		// calculate results
		int cindex = 0, uindex = 0;
		for (cindex = 0, uindex = 0; cindex < ConversionTypes.getPowerTypes().length && uindex < ConversionTypes.getPowerTypes().length ; cindex++, uindex++) {
			ResultItem rI = new ResultItem();
			rI.setValue(Formatting.roundOff(power / cVals[cindex]));
			rI.setUnitType(ConversionTypes.getPowerTypes()[uindex]);
			rValues.add(rI);
		}

	}

	public ArrayList<ResultItem> getValues() {
		return rValues;
	}

}
