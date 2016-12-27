package com.hongganju.common.util;

public class NumberFormat {
	static String num_array[] = {"0","00","000","0000","00000","000000"};
	static public Integer getReportDate(int high, int low)
	{
		String low_str = format2(low);
		String str = high + low_str;
		return Integer.parseInt(str);
		
	}
	static public String format2(int num)
	{
		if(num  == 0)
			return "00";
		
		if(num < 10)
			return num_array[0] + Integer.toString(num);

		return Integer.toString(num);
	}
	static public String format(int num)
	{
		if(num  == 0)
			return "000";
		
		if(num < 10)
			return num_array[1] + Integer.toString(num);
		
		if(num < 100)
			return num_array[0] + Integer.toString(num);

		return Integer.toString(num);
	}
	static public String format4(int num)
	{
		if(num  == 0)
			return "0000";
		
		if(num < 10)
			return num_array[2] + Integer.toString(num);
		
		if(num < 100)
			return num_array[1] + Integer.toString(num);
		
		if(num < 1000)
			return num_array[0] + Integer.toString(num);
		return Integer.toString(num);
	}
	/*
	static public String format(int num)
	{
		if(num  == 0)
			return num_array[5];
		
		if(num < 10)
			return num_array[4] + Integer.toString(num);
		
		if(num < 100)
			return num_array[3] + Integer.toString(num);
		
		if(num < 1000)
			return num_array[2] + Integer.toString(num);
		
		if(num < 10000)
			return num_array[1] + Integer.toString(num);
		
		if(num < 10000)
			return num_array[0] + Integer.toString(num);
		
		return Integer.toString(num);
	}*/
}
