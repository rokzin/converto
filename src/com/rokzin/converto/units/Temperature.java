package com.rokzin.converto.units;

import java.util.ArrayList;

import com.rokzin.converto.core.ResultItem;
import com.rokzin.converto.utils.ConversionTypes;
import com.rokzin.converto.utils.Formatting;

public class Temperature {

	private ArrayList<ResultItem> rValues = new ArrayList<ResultItem>();

	String rInputValue;

	public Temperature(int type, double value) {

		rInputValue = String.valueOf(value);
		/**
		 * 0 : Celcius
		 * 1 : fahrenheit
		 * 2 : kelvin
		 * 3 : rankine
		 * 4 : delisle
		 * 5 : newton
		 * 6 : reaumur
		 * 7 : romer
		 */
		switch (type) {

		case 0: {
			convertFromCelcius(value);
			break;
		}
		case 1: {
			convertFromFahrenheit(value);
			break;
		}

		case 2: {
			convertFromKelvin(value);
			break;
		}
		case 3: {
			convertFromRankine(value);
			break;
		}
		case 4: {
			convertoFromDelisle(value);
			break;
		}
		case 5: {
			convertFromNewton(value);
			break;
		}
		case 6: {
			convertFromReaumur(value);
			break;
		}
		case 7: {
			convertFromRomer(value);
			break;
		}
		default: {
			break;
		}

		}
		// rValues.clear();
	}

	public ArrayList<ResultItem> getValues() {

		return rValues;
	}

	private void convertFromCelcius(double value) {

		double rCelcius = Formatting.roundOff(value);
		double rCToFahrenheit = Formatting.roundOff(value * 9 / 5 + 32);
		double rCToKelvin = Formatting.roundOff(value + 273.15);
		double rCToRankine = Formatting.roundOff((value + 273.15) * 9 / 5);
		double rCToDelisle = Formatting.roundOff((100 - value) * 3 / 2);
		double rCToNewton = Formatting.roundOff(value * 33 / 100);
		double rCToReaumur = Formatting.roundOff(value * 4 / 5);
		double rCToRomer = Formatting.roundOff(value * 21 / 40 + 7.5);

		ArrayList<Double> results = new ArrayList<Double>();
		results.add(rCelcius);
		results.add(rCToFahrenheit);
		results.add(rCToKelvin);
		results.add(rCToRankine);
		results.add(rCToDelisle);
		results.add(rCToNewton);
		results.add(rCToReaumur);
		results.add(rCToRomer);

		for (int i = 0; i < results.size(); i++) {
			ResultItem ri = new ResultItem();
			ri.setValue(results.get(i));
			ri.setUnitType(ConversionTypes.getTemperatureTypes()[i]);
			rValues.add(ri);
		}
	}

	private void convertFromFahrenheit(double value) {
		double rFToC = (value - 32) * 5 / 9;
		convertFromCelcius(rFToC);
	}

	private void convertFromKelvin(double value) {
		double rKToC = value - 273.15;
		convertFromCelcius(rKToC);
	}

	private void convertFromRankine(double value) {
		double rRToC = (value - 491.67) * 5 / 9;
		convertFromCelcius(rRToC);
	}

	private void convertoFromDelisle(double value) {
		double rDToC = 100 - value * 2 / 3;
		convertFromCelcius(rDToC);

	}

	private void convertFromNewton(double value) {
		double rNToC = value * 3.0303;
		convertFromCelcius(rNToC);
	}

	private void convertFromReaumur(double value) {
		double rR1ToC = value * 5 / 4;
		convertFromCelcius(rR1ToC);
	}

	private void convertFromRomer(double value) {
		double rR2ToC = (value - 7.5) * 40 / 21;
		convertFromCelcius(rR2ToC);
	}

}
