package com.dlabs.acs.util;

public class Validator
{
	public static boolean isNotNull(String s)
	{
		return !isNull(s);
	}

	public static boolean isNull(String s)
	{
		if (s == null)
		{
			return true;
		}

		if(s.trim().length() == 0)
		{
			return true;
		}
		return false;
	}
}
