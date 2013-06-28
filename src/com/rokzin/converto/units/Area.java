package com.rokzin.converto.units;

import java.util.ArrayList;

import com.rokzin.converto.core.ResultItem;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.Formatting;

public class Area {
	ArrayList<ResultItem> rValues = new ArrayList<ResultItem>();

	public Area(int type, double value) {

		/**
		 * 0 : Square-Millimeter 1 : Square-Centimeter 2 : Square-Meter 3 :
		 * Square-Kilometer 4 : Square-inch 5 : Square-Feet 6 : Square-Yard 7 :
		 * Square-Mile 8 : Acre 9 : Are 10: Decare 11: Square Rod
		 */

		switch (type) {

		case 0: {

			double rSqMillimeter = Formatting.roundOff(value / 100);
			convertFromSquareCentimeter(rSqMillimeter);
			break;
		}
		case 1: {

			convertFromSquareCentimeter(value);
			break;

		}
		case 2: {
			double rSqMeter = Formatting.roundOff(value * 100);
			convertFromSquareCentimeter(rSqMeter);
			break;
		}
		case 3: {
			double rSqKilometer = Formatting.roundOff(value * 100000);
			convertFromSquareCentimeter(rSqKilometer);
			break;
		}
		case 4: {
			double rSqInch = Formatting.roundOff(value * 2.5);
			convertFromSquareCentimeter(rSqInch);
			break;
		}
		case 5: {
			double rSqFeet = Formatting.roundOff(value * 30.48);
			convertFromSquareCentimeter(rSqFeet);
			break;
		}
		case 6: {
			double rSqYard = Formatting.roundOff(value * 91.44);
			convertFromSquareCentimeter(rSqYard);
			break;
		}
		case 7: {
			double rSqMile = Formatting.roundOff(value * 160934);
			convertFromSquareCentimeter(rSqMile);
			break;
		}
		case 8: {
			double rAcre = Formatting.roundOff(value * 2.5 / 1000000);
			convertFromSquareCentimeter(rAcre);
			break;
		}
		case 9: {

			// TODO
			convertFromSquareCentimeter(0.0);
			break;
		}
		case 10: {

			// TODO
			convertFromSquareCentimeter(0.0);
			break;
		}

		case 11: {

			// TODO
			convertFromSquareCentimeter(0.0);
			break;
		}
		default: {
			break;
		}

		}
	}

	private void convertFromSquareCentimeter(double value) {

		double rSqMillimeter = Formatting.roundOff(value * 100);
		double rSqCentimeter = value;
		double rSqMeter = Formatting.roundOff(value / 10000);
		double rSqKilometer = Formatting.roundOff(rSqMeter / 1000000);
		double rSqInch = Formatting.roundOff(value * 0.155);
		double rSqFeet = Formatting.roundOff(value * 0.001076);
		double rSqYard = Formatting.roundOff(value * 0.000119599);
		double rSqMile = Formatting.roundOff(rSqKilometer * 0.386102);
		double rAcre = Formatting.roundOff(rSqMile * 639.9999);
		double rAre = 0.0;// TODO
		double rDecare = 0.0;// TODO
		double rSquareRod = 0.0;// TODO

		ArrayList<Double> results = new ArrayList<Double>();
		results.add(rSqMillimeter);
		results.add(rSqCentimeter);
		results.add(rSqMeter);
		results.add(rSqKilometer);
		results.add(rSqInch);
		results.add(rSqFeet);
		results.add(rSqYard);
		results.add(rSqMile);
		results.add(rAcre);
		results.add(rAre);
		results.add(rDecare);
		results.add(rSquareRod);

		for (int i = 0; i < results.size(); i++) {
			ResultItem ri = new ResultItem();
			ri.setValue(results.get(i));
			ri.setUnitType(ConversionTypes.getAreaTypes()[i]);
			rValues.add(ri);
		}

	}

	public ArrayList<ResultItem> getValues() {
		return rValues;
	}

}