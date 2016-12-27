package com.hongganju.woa.factory;

import weblech.spider.SpiderConfig;

import com.hongganju.woa.browser.WebBrowserBase;
import com.hongganju.woa.browser.hbrowser.WOAHBrowser;
import com.hongganju.woa.browser.hbrowserNoJS.WOAHBrowserNoJS;
import com.hongganju.woa.browser.ie.IEBrowser;
import com.hongganju.woa.browser.simple.SimpleBrowser;

public class BrowserFactory {
	public final static int SIMPLE = 1;
	public final static int HBROWSER = 2;
	public final static int WEBBROWSER = 3;
	public final static int IE = 4;
	public final static int HBROWSERNOJS = 5;
	public static WebBrowserBase createBrowser(int type, SpiderConfig config)
	{
		System.out.println("createBrowser type=" + type);
		if(type == SIMPLE)
			return new SimpleBrowser(config);
		else if(type == HBROWSER)
			return new WOAHBrowser(config);
		else if(type == IE)
			return new IEBrowser(config);
		else if(type == HBROWSERNOJS)
			return new WOAHBrowserNoJS(config);
		else
			return null;
	}
	
}
