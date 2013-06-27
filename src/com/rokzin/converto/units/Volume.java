package com.rokzin.converto.units;

import java.util.ArrayList;
import java.util.List;

import com.rokzin.converto.utils.Formatting;

public class Volume {
	List<String> rValues = new ArrayList<String>();

	public Volume(int type, double value) {

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
		double rLiter = 0;
		switch (type) {

		case 0: {

			rLiter = Formatting.roundOff(value / 1000);
			convertFromLitre(rLiter);
			break;
		}
		case 1: {

			convertFromLitre(value);
			break;

		}
		case 2: {
			rLiter = Formatting.roundOff(value * 3.78541);
			convertFromLitre(rLiter);
			break;
		}
		case 3: {
			rLiter = Formatting.roundOff(value * 4.54609);
			convertFromLitre(rLiter);
			break;
		}
		case 4: {
			// fl oz to liter
			rLiter = Formatting.roundOff(value * 0.0295735);
			convertFromLitre(rLiter);
			break;
		}
		case 5: {
			// fl oz uk to liter
			rLiter = Formatting.roundOff(value * 0.028413);
			convertFromLitre(rLiter);
			break;
		}
		case 6: {
			// cc to liter
			rLiter = Formatting.roundOff(value * 0.001);
			convertFromLitre(rLiter);
			break;
		}
		case 7: {
			rLiter = Formatting.roundOff(value * 0.946353);
			convertFromLitre(rLiter);
			break;
		}
		case 8: {
			rLiter = Formatting.roundOff(value * 1.136523);
			convertFromLitre(rLiter);
			break;
		}
		case 9: {
			rLiter = Formatting.roundOff(value * 0.473176);
			convertFromLitre(rLiter);
			break;
		}
		case 10: {
			rLiter = Formatting.roundOff(value * 0.568261);
			convertFromLitre(rLiter);
			break;
		}
		case 11: {
			rLiter = Formatting.roundOff(value * 0.000001);
			convertFromLitre(rLiter);
			break;
		}
		case 12: {
			rLiter = Formatting.roundOff(value * 1000);
			convertFromLitre(rLiter);
			break;
		}
		case 13: {
			convertFromLitre(value);
			break;
		}
		case 14: {
			rLiter = Formatting.roundOff(value * 28.316847);
			convertFromLitre(rLiter);
			break;
		}
		case 15: {
			rLiter = Formatting.roundOff(value * 0.016387);
			convertFromLitre(rLiter);
			break;
		}
		case 16: {
			rLiter = Formatting.roundOff(value * 0.118294);
			convertFromLitre(rLiter);
			break;
		}
		case 17: {
			rLiter = Formatting.roundOff(value * 0.141953);
			convertFromLitre(rLiter);
			break;
		}
		case 18: {
			rLiter = Formatting.roundOff(value * 158.987295);
			convertFromLitre(rLiter);
			break;
		}
		case 19: {
			rLiter = Formatting.roundOff(value * 158.987223);
			convertFromLitre(rLiter);
			break;
		}
		case 20:
		case 21: {
			rLiter = Formatting.roundOff(value * 0.0148);
			convertFromLitre(rLiter);
			break;
		}
		case 22:
		case 23: {
			rLiter = Formatting.roundOff(value * 0.004929);
			convertFromLitre(rLiter);
			break;
		}
		case 24: {
			rLiter = Formatting.roundOff(value * 8.80976754172);
			convertFromLitre(rLiter);
			break;
		}
		case 25: {
			rLiter = Formatting.roundOff(value * 9.09218);
			convertFromLitre(rLiter);
			break;
		}
		default: {
			break;
		}

		}
	}

	private void convertFromLitre(double value) {

		double milliLiter = Formatting.roundOff(value * 1000);
		double litre = Formatting.roundOff(value);
		double gallonUS = Formatting.roundOff(value * 0.264172);
		double imperialGallonUK = Formatting.roundOff(value * 0.219969);
		double flOzUS = Formatting.roundOff(value * 33.814023);
		double flOzUK = Formatting.roundOff(value * 35.19508);
		double CC = Formatting.roundOff(value * 1000);
		double quartUS = Formatting.roundOff(value * 1.056688);
		double quartUK = Formatting.roundOff(value * 0.879877);
		double pintUS = Formatting.roundOff(value * 2.113376);
		double pintUK = Formatting.roundOff(value * 1.759754);
		double cubicMMs = Formatting.roundOff(value * 1000000);
		double cubicMeters = Formatting.roundOff(value * 0.001);
		double cubicDecimeter = Formatting.roundOff(value);
		double cubicFeet = Formatting.roundOff(value * 0.035315);
		double cubicInches = Formatting.roundOff(value * 61.023744);
		double gillUS = Formatting.roundOff(value * 8.453506);
		double gillUK = Formatting.roundOff(value * 7.039016);
		double oilBarrelUS = Formatting.roundOff(value * 0.00629);
		double oilBarrelUK = Formatting.roundOff(value * 0.00611);
		double tablespoonUS = Formatting.roundOff(value * 67.628045);
		double tablespoonUK = Formatting.roundOff(value * 56.312128);
		double teaspoonUS = Formatting.roundOff(value * 202.884136);
		double teaspoonUK = Formatting.roundOff(value * 168.936383);
		double peckUS = Formatting.roundOff(value * 0.11351);
		double peckUK = Formatting.roundOff(value * 0.109985);

		rValues.add(String.valueOf(milliLiter) + " ml");
		rValues.add(String.valueOf(litre + " L"));
		rValues.add(String.valueOf(gallonUS + " Gal.(US)"));
		rValues.add(String.valueOf(imperialGallonUK + " Gal.(UK)"));
		rValues.add(String.valueOf(flOzUS + " Fl. Oz."));
		rValues.add(String.valueOf(flOzUK + " Fl.Oz(UK)"));
		rValues.add(String.valueOf(CC + " cc"));
		rValues.add(String.valueOf(quartUS + " quart"));
		rValues.add(String.valueOf(quartUK + " quart (UK)"));
		rValues.add(String.valueOf(pintUS + " pint"));
		rValues.add(String.valueOf(pintUK + " pint (UK)"));
		rValues.add(String.valueOf(cubicMMs + " cu. mm"));
		rValues.add(String.valueOf(cubicMeters + " cu. m"));
		rValues.add(String.valueOf(cubicDecimeter + " cu. dec."));
		rValues.add(String.valueOf(cubicFeet + " cu. ft"));
		rValues.add(String.valueOf(cubicInches + " cu. in"));
		rValues.add(String.valueOf(gillUS + " gill (US)"));
		rValues.add(String.valueOf(gillUK + " gill (UK)"));
		rValues.add(String.valueOf(oilBarrelUS + " Barell (US)"));
		rValues.add(String.valueOf(oilBarrelUK + " Barell (UK)"));
		rValues.add(String.valueOf(tablespoonUS + " Tbspn (US)"));
		rValues.add(String.valueOf(tablespoonUK + " Tbspn (UK)"));
		rValues.add(String.valueOf(teaspoonUS + " Tea spn (US)"));
		rValues.add(String.valueOf(teaspoonUK + " Tea spn (UK)"));
		rValues.add(String.valueOf(peckUS + " Peck (US)"));
		rValues.add(String.valueOf(peckUK + " Peck (UK)"));

	}

	public List<String> getValues() {
		return rValues;
	}

}