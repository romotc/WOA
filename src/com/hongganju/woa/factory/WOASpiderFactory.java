package com.hongganju.woa.factory;

import com.hongganju.woa.browser.hbrowserNoJS.WOAHBrowserNoJS;
import com.hongganju.woa.browser.ie.IEBrowser;
import com.hongganju.woa.browser.simple.SimpleBrowser;
import com.hongganju.woa.spider.WOAConfig;
import com.hongganju.woa.spider.WOASpider;
import com.hongganju.woa.spider.WOASpiderParam;

//组合不同的实现
public class WOASpiderFactory {
	public static WOASpider createSimpleSpider(WOASpiderParam param)
	{
		System.out.println("create createSimpleSpider");
		WOASpider woa = new WOASpider(param);
		
		woa.spider.browserType = BrowserFactory.SIMPLE;
		woa.spider.postType = PostProcessorFactory.SIMPLE;
		
		return woa;
	}
	
	public static WOASpider createHSpider(WOASpiderParam param)
	{
		System.out.println("create createHSpider");
		WOASpider woa = new WOASpider(param);
		
		woa.spider.browserType = BrowserFactory.HBROWSER;
		woa.spider.postType = PostProcessorFactory.SIMPLE;
		
		return woa;
	}
	public static WOASpider createIESpider(WOASpiderParam param)
	{
		System.out.println("create createIESpider");
		WOASpider woa = new WOASpider(param);
		IEBrowser browser = new IEBrowser(WOAConfig.config);
		
		woa.spider.browserType = BrowserFactory.IE;
		woa.spider.postType = PostProcessorFactory.SIMPLE;
		
		return woa;
	}
	
	public static WOASpider createHnoJsSpider(WOASpiderParam param)
	{
		System.out.println("create createHnoJsSpider");
		WOASpider woa = new WOASpider(param);
		WOAHBrowserNoJS browser = new WOAHBrowserNoJS(WOAConfig.config);
		
		woa.spider.browserType = BrowserFactory.HBROWSERNOJS;
		woa.spider.postType = PostProcessorFactory.SIMPLE;
		
		return woa;
	}
	
	public static WOASpider createMSSpider(WOASpiderParam param)
	{
		System.out.println("create createMSSpider");
		WOASpider woa = new WOASpider(param);
		SimpleBrowser browser = new SimpleBrowser(WOAConfig.config);
		
		woa.spider.browserType = BrowserFactory.WEBBROWSER;
		woa.spider.postType = PostProcessorFactory.SIMPLE;
		
		return woa;
	}
}
