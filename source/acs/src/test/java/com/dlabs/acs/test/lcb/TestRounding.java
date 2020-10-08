package com.dlabs.acs.test.lcb;

import com.dlabs.acs.util.NumberUtil;

public class TestRounding {
	
	public static void main(String[] args) {
		System.out.println(NumberUtil.round(1.2));	
		System.out.println(NumberUtil.round(1.4));
		System.out.println(NumberUtil.round(1.5));
		System.out.println(NumberUtil.round(1.51));
		System.out.println(NumberUtil.round(1.6));
		System.out.println(NumberUtil.round(34.0/7.0));
							System.out.println(34.0/7.0);
	}
}
