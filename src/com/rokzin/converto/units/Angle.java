package com.rokzin.converto.units;

import java.util.ArrayList;
import java.util.List;

import com.rokzin.converto.utils.Formatting;

public class Angle {

	List<String> rValues = new ArrayList<String>();
	double cVals[] = {0.016667, 0.000278, 360, 1, 0.9, 0.9, 0.05625, 0.06, 0.057143, 45, 90, 57.29578, 360, 60, 30, 360};
	public static String uVals[] = {"arcminute", "arcsecond", "circle", 
						"degree", "gon", "grad",
						"mil (nano)", "mil (soviet union)", "mil (sweden)",
						"octant", "quadrant", "radian", 
						"revolution", "sextant", "sign", "turn"};
	public Angle(int type, double value){
		
		/* allowed type parameter values
		 * 1.  arcminute
		 * 2.  arcsecond
		 * 3.  circle
		 * 4.  degree
		 * 5.  gon
		 * 6.  grad
		 * 7.  mil (nano)
		 * 8.  mil (soviet union)
		 * 9.  mil (sweden)
		 * 10. octant
		 * 11. quadrant
		 * 12. radian
		 * 13. revolution
		 * 14. sextant
		 * 15. sign
		 * 16. turn
		 * 
		 * */
		
		double rAngle = 0;
		
		rAngle = (value * cVals[type]);
		convertFromAngle(rAngle);
		
	}
	
	private void convertFromAngle(double rAngle){
		// calculate results 
		int cindex = 0, uindex = 0;
		for(cindex = 0, uindex = 0; cindex<16 && uindex<16; cindex++, uindex++){
			rValues.add(String.valueOf(Formatting.roundOff(rAngle / cVals[cindex])) + " " + uVals[uindex]);
		}
		
	}
	
	public List<String> getValues() {
		return rValues;
	}
	
}
