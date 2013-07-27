package com.rokzin.converto.units;

import java.util.ArrayList;

import com.rokzin.converto.core.ResultItem;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.Formatting;

public class Pressure {

	ArrayList<ResultItem> rValues = new ArrayList<ResultItem>();
	double cVals[] = { 101325, 98066.5, 100000, 1333.22, 98.063, 40636.66, 2988.98, 3386.389, 249.082, 
						6894757, 133.3224, 9.80638, 1 , 47.88026,6894 , 757133.322};

	public Pressure(int type, double value) {

		/*
		 * allowed type parameter values
		 * 0. atm (Std)
		 * 1. atm (Tech)
		 * 2. bar
		 * 3. cmHg
		 * 4. cmH2O
		 * 5. ftHg
		 * 6. ftH2O
		 * 7. inHg
		 * 8. inH2O
		 * 9. ksi
		 * 10. mmHg
		 * 11. mmH2O
		 * 12. Pa
		 * 13. psf
		 * 14. psi
		 * 15. torr
		 */

		double pressure = 0;

		pressure = (value * cVals[type]);
		convertFromSpeed(pressure);

	}

	private void convertFromSpeed(double pressure) {
		// calculate results
		int cindex = 0, uindex = 0;
		for (cindex = 0, uindex = 0; cindex < ConversionTypes.getSpeedTypes().length && uindex < ConversionTypes.getSpeedTypes().length ; cindex++, uindex++) {
			ResultItem rI = new ResultItem();
			rI.setValue(Formatting.roundOff(pressure / cVals[cindex]));
			rI.setUnitType(ConversionTypes.getPressureTypes()[uindex]);
			rValues.add(rI);
		}

	}

	public ArrayList<ResultItem> getValues() {
		return rValues;
	}

}
