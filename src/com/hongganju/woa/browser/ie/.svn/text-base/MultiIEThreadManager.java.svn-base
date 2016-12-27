package com.hongganju.woa.browser.ie;

import weblech.spider.SpiderThread;



public class MultiIEThreadManager {
	SpiderThread[] synObjects;
	public MultiIEThreadManager(SpiderThread[] threads)
	{
		this.synObjects = threads;
	}
	
	public void notify(BrowseResult result)
	{
		int i = result.getThreadNo();
		if(i >= this.synObjects.length)
			return;
		synchronized(this.synObjects[i])
		{
			this.synObjects[i].result = result;
			this.synObjects[i].notify();
		}
	}
}