package com.dlabs.acs.util;

public class NumberUtil {
	public static int round(double d) {
	    if (d > 0) {
	        return (int) (d + 0.5);
	    } else {
	        return (int) (d - 0.5);
	    }
	}
}
