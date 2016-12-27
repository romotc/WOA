package com.hongganju.woa;

public class WOASystemUtil {
	static boolean isWindows = true;
	static{
		init();
	}
	
	static public void init()
	{
		System.out.println("OS=" + System.getProperty("os.name"));
		if(!System.getProperty("os.name").toLowerCase().startsWith("win"))
		{
			System.out.println("linux");
			isWindows = false;
		}
	}
	static public boolean isWin()
	{
		return isWindows;
	}
}
