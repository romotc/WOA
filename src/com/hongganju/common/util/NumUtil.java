package com.hongganju.common.util;

public class NumUtil {
	public static Short parseShort(String num)
	{
		if(num == null || num.equals(""))
			return 0;
		else
			return Short.parseShort(num);
	}
	
	public static Float parseFloat(String num)
	{
		if(num == null || num.equals(""))
			return (float) 0.00;
		else
			return Float.parseFloat(num);
	}
	
	public static Integer parseInt(String num)
	{
		if(num == null || num.equals(""))
			return 0;
		else
			return Integer.parseInt(num);
	}
	public static Long parseLong(String num)
	{
		if(num == null || num.equals(""))
			return (long)0;
		else
			return Long.parseLong(num);
	}
	public static String boolToString(Boolean num)
	{
		if(num == null)
			return null;
		return Boolean.toString(num);
	}
	
	
	public static String longToString(Long num)
	{
		if(num == null)
			return null;
		return Long.toString(num);
	}
	public static String shortToString(Short num)
	{
		if(num == null)
			return null;
		return Short.toString(num);
	}
	public static String doulbeToString(Double num)
	{
		if(num == null)
			return null;
		return Double.toString(num);
	}
	public static String floatToString(Float num)
	{
		if(num == null)
			return null;
		return Float.toString(num);
	}
	public static String intToString(Integer num)
	{
		if(num == null)
			return null;
		return Integer.toString(num);
	}
}
