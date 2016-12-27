package com.hongganju.woa.browser;

import java.util.ArrayList;
import java.util.List;

import java.net.URL;

import com.hongganju.woa.spider.WOASpider;

import weblech.spider.SpiderConfig;
import weblech.spider.SpiderThread;
import weblech.spider.URLToDownload;

//用来适配各种浏览器（h，微软，http浏览器等）
public abstract class WebBrowserBase {
	protected SpiderConfig config = null;
	protected String mimeType;
	protected String httpCode;
	protected List<URL> urls = new ArrayList<URL>();
	
	protected WOASpider woa = null;


	public void setWOA(WOASpider woa)
	{
		this.woa = woa;
	}
	
	public WebBrowserBase(SpiderConfig config)
	{
		this.config = config;
	}
	
	//执行
//	protected abstract void runHttpGet(URLToDownload url);
	protected abstract void runHttpGet(URLToDownload url, SpiderThread thread);
	public void httpGet(URLToDownload url, SpiderThread thread)
	{
		this.runHttpGet(url, thread);
		//计数
		synchronized(this.woa)
		{
			if(this.urls != null)
				this.woa.crawledUrls += this.urls.size();
			this.woa.currentUrl++;
		}
		//计数满足，返回
//		if(this.woa.crawledUrls > this.woa.totalUrl)
		if(this.woa.totalUrl > 0)
		{
			if(this.woa.currentUrl > this.woa.totalUrl)
			{
				System.out.println("任务taskId=" + woa.param.taskId + ", taskinstanceId=" + woa.param.taskInstanceId + " 执行完毕，爬虫终止!");
				this.woa.spider.stop();
			}
		}
		
	}

	public List<URL> getUrls() {
		return urls;
	}
	public String getMimeType() {
		return mimeType;
	}
	public String getHttpCode() {
		return httpCode;
	}
	
}
