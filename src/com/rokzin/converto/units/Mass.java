package com.rokzin.converto.units;

import java.util.ArrayList;

import com.rokzin.converto.core.ResultItem;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.Formatting;

public class Mass {

	ArrayList<ResultItem> rValues = new ArrayList<ResultItem>();
	double cVals[] = { 200,
						45359237,
						10,
						10000,
						1771.845195312 ,
						64.79891,
						1000,
						50802345.44,
						1000000,
						0.001,
						1,
						101971.6212978,
						28349.523125,
						1555.17384,
						453592.37 ,
						12700586.36,
						6350293.18,
						1016046908.8,
						907184740 ,
						1000000000 ,
						31103.4768};

	public Mass(int type, double value) {

		/*
		0. carat
		1. cental
		2. centigram
		3. dekagram
		4. dram(dr)
		5. grain(gr)
		6. gram(g)
		7. hundredweight(UK)
		8. kilogram(kg)
		9. microgram(µg)
		10. milligram(mg)
		11. newton(earth)
		12. ounce(oz)
		13. pennyweight(dwt)
		14. pound(lb)
		15. quarter
		16. stone
		17. ton(UK)
		18. ton(US)
		19. tonne(t)
		20. troy ounce
		*/

		double mass = 0;

		mass = (value * cVals[type]);
		convertFromMass(mass);

	}

	private void convertFromMass(double mass) {
		// calculate results
		int cindex = 0, uindex = 0;
		for (cindex = 0, uindex = 0; cindex < ConversionTypes.getMassTypes().length && uindex < ConversionTypes.getMassTypes().length ; cindex++, uindex++) {
			ResultItem rI = new ResultItem();
			rI.setValue(Formatting.roundOff(mass / cVals[cindex]));
			rI.setUnitType(ConversionTypes.getMassTypes()[uindex]);
			rValues.add(rI);
		}

	}

	public ArrayList<ResultItem> getValues() {
		return rValues;
	}

}
