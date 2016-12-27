package com.hongganju.woa.factory;

import com.hongganju.woa.spider.SimplePostProcessURL;
import com.hongganju.woa.spider.WOAPostProcessURLBase;

public class PostProcessorFactory {
	public final static int SIMPLE = 1;
	public static WOAPostProcessURLBase createPostProcessor(int type)
	{
		if(type == SIMPLE)
			return new SimplePostProcessURL();
		else
			return null;
	}
}
