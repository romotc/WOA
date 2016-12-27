package com.hongganju.common.util;

public class BoolUtil {
	public static Boolean toBoolean(String str)
	{	
		if(str == null)
			return null;
		try{
			return Boolean.parseBoolean(str);
		}catch(Exception e)
		{}
		return null;
	}
}
