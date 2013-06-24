package com.rokzin.converto.units;

import java.util.ArrayList;
import java.util.List;

import com.rokzin.converto.utils.Formatting;

public class Temperature {

	private List<String> rValues = new ArrayList<String>();

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

	public List<String> getValues() {

		return rValues;
	}

	private void convertFromCelcius(double value) {
		double rCToFahrenheit = Formatting.roundToFourDec(value * 9 / 5 + 32);
		double rCToKelvin = Formatting.roundToFourDec(value + 273.15);
		double rCToRankine = Formatting.roundToFourDec((value + 273.15) * 9 / 5);
		double rCToDelisle = Formatting.roundToFourDec((100 - value) * 3 / 2);
		double rCToNewton = Formatting.roundToFourDec(value * 33 / 100);
		double rCToReaumur = Formatting.roundToFourDec(value * 4 / 5);
		double rCToRomer = Formatting.roundToFourDec(value * 21 / 40 + 7.5);

		// rValues.add(rInputValue+TemperatureView.rSelectedUnit+" = "+String.valueOf(value)+"\u00B0C");
		// rValues.add(rInputValue+TemperatureView.rSelectedUnit+" = "+String.valueOf(rCToFahrenheit)+"\u00B0F");
		// rValues.add(rInputValue+TemperatureView.rSelectedUnit+" = "+String.valueOf(rCToKelvin)+" K");
		// rValues.add(rInputValue+TemperatureView.rSelectedUnit+" = "+String.valueOf(rCToRankine)+"\u00B0R");
		// rValues.add(rInputValue+TemperatureView.rSelectedUnit+" = "+String.valueOf(rCToDelisle)+"\u00B0De");
		// rValues.add(rInputValue+TemperatureView.rSelectedUnit+" = "+String.valueOf(rCToNewton)+"\u00B0N");
		// rValues.add(rInputValue+TemperatureView.rSelectedUnit+" = "+String.valueOf(rCToReaumur)+"\u00B0Re");
		// rValues.add(rInputValue+TemperatureView.rSelectedUnit+" = "+String.valueOf(rCToRomer)+"\u00B0R");

		rValues.add(String.valueOf(value) + "\u00B0C");
		rValues.add(String.valueOf(rCToFahrenheit) + "\u00B0F");
		rValues.add(String.valueOf(rCToKelvin) + " K");
		rValues.add(String.valueOf(rCToRankine) + "\u00B0R");
		rValues.add(String.valueOf(rCToDelisle) + "\u00B0De");
		rValues.add(String.valueOf(rCToNewton) + "\u00B0N");
		rValues.add(String.valueOf(rCToReaumur) + "\u00B0Re");
		rValues.add(String.valueOf(rCToRomer) + "\u00B0R");
	}

	private void convertFromFahrenheit(double value) {
		double rFToC = Formatting.roundToFourDec((value - 32) * 5 / 9);
		convertFromCelcius(rFToC);
	}

	private void convertFromKelvin(double value) {
		double rKToC = Formatting.roundToFourDec(value - 273.15);
		convertFromCelcius(rKToC);
	}

	private void convertFromRankine(double value) {
		double rRToC = Formatting.roundToFourDec((value - 491.67) * 5 / 9);
		convertFromCelcius(rRToC);
	}

	private void convertoFromDelisle(double value) {
		double rDToC = Formatting.roundToFourDec(100 - value * 2 / 3);
		convertFromCelcius(rDToC);

	}

	private void convertFromNewton(double value) {
		double rNToC = Formatting.roundToFourDec(value * 3.0303);
		convertFromCelcius(rNToC);
	}

	private void convertFromReaumur(double value) {
		double rR1ToC = Formatting.roundToFourDec(value * 5 / 4);
		convertFromCelcius(rR1ToC);
	}

	private void convertFromRomer(double value) {
		double rR2ToC = Formatting.roundToFourDec((value - 7.5) * 40 / 21);
		convertFromCelcius(rR2ToC);
	}

}
