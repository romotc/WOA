package com.hongganju.common.util;

public class TimeStampGenerator {
	static int counter = 0;
	static long lastTime = System.currentTimeMillis();
	//该方法只会被顺序调用
	static public String generate()
	{
		long t = System.currentTimeMillis();
		if(t > lastTime)
		{
			counter = 0;
			lastTime = t;
			return t + NumberFormat.format(counter);
		}
		else
		{
			counter++;
			return t + NumberFormat.format(counter);
		}
	}
	static public void main(String args[]) throws InterruptedException
	{
		for(int i=0;i<1000;i++)
		{
			String str = TimeStampGenerator.generate();
			if(str.endsWith("001"))
				System.out.println("#");
			//System.out.println(generate());
		}
		Thread.sleep(100);
		System.out.println(generate());
	}
}
