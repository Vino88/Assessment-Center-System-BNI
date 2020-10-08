package com.dlabs.acs.test.report;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MyTest {
public static void main(String[] args) {
	NumberFormat nf = new DecimalFormat("###");
	System.out.println(nf.format(1));
	System.out.println(nf.format(1.0));
	System.out.println(nf.format(123.4));
	System.out.println(nf.format(123456.4));
}
}
