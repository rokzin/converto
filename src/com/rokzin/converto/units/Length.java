package com.rokzin.converto.units;

import java.util.ArrayList;
import java.util.List;

import com.rokzin.converto.utils.Formatting;

public class Length {

	List<String> rValues = new ArrayList<String>();

	public Length(int type, double value) {

		/**
		 * 0 : Millimeter 1 : Centimeter 2 : Meter 3 : Kilometer 4 : inch 5 :
		 * feet 6 : yard 7 : mile 8 : microinch
		 */

		switch (type) {

		case 0: {

			double rCentimeter = Formatting.roundOff(value / 10);
			convertFromCentimeter(rCentimeter);
			break;
		}
		case 1: {

			convertFromCentimeter(value);
			break;

		}
		case 2: {
			double rCentimeter = Formatting.roundOff(value * 100);
			convertFromCentimeter(rCentimeter);
			break;
		}
		case 3: {
			double rCentimeter = Formatting.roundOff(value * 100000);
			convertFromCentimeter(rCentimeter);
			break;
		}
		case 4: {
			double rCentimeter = Formatting.roundOff(value * 2.5);
			convertFromCentimeter(rCentimeter);
			break;
		}
		case 5: {
			double rCentimeter = Formatting.roundOff(value * 30.48);
			convertFromCentimeter(rCentimeter);
			break;
		}
		case 6: {
			double rCentimeter = Formatting.roundOff(value * 91.44);
			convertFromCentimeter(rCentimeter);
			break;
		}
		case 7: {
			double rCentimeter = Formatting.roundOff(value * 160934);
			convertFromCentimeter(rCentimeter);
			break;
		}
		case 8: {
			double rCentimeter = Formatting.roundOff(value * 2.5 / 1000000);
			convertFromCentimeter(rCentimeter);
			break;
		}
		default: {
			break;
		}

		}

	}

	private void convertFromCentimeter(double value) {

		double rMillimeter = Formatting.roundOff(value * 10);
		double rCentimeter = Formatting.roundOff(value);
		double rMeter = Formatting.roundOff(value / 100);
		double rKilometer = Formatting.roundOff(rMeter / 1000);
		double rInch = Formatting.roundOff(value / 2.54);
		double rFeet = Formatting.roundOff(rInch / 12);
		double rYard = Formatting.roundOff(rFeet / 3);
		double rMile = Formatting.roundOff(rYard / 1760);

		rValues.add(String.valueOf(rMillimeter) + "\nmm(s)");
		rValues.add(String.valueOf(rCentimeter) + "\ncm(s)");
		rValues.add(String.valueOf(rMeter) + "\nm(s)");
		rValues.add(String.valueOf(rKilometer) + "\nkm(s)");
		rValues.add(String.valueOf(rInch) + "\nin(s)");
		rValues.add(String.valueOf(rFeet) + "\nft(s)");
		rValues.add(String.valueOf(rYard) + "\nyard(s)");
		rValues.add(String.valueOf(rMile) + "\n miles(s)");

	}

	public List<String> getValues() {
		return rValues;
	}

}
