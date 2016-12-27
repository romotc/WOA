package com.hongganju.woa.browser.simple;

import java.util.ArrayList;

import java.net.URL;

import com.hongganju.woa.browser.WebBrowserBase;

import weblech.spider.SpiderConfig;
import weblech.spider.SpiderThread;
import weblech.spider.URLObject;
import weblech.spider.URLToDownload;


//要处理下载url计数问题
//退出终止线程问题
//这个要重写，因为接口没有定下来
public class SimpleBrowser extends WebBrowserBase{
	
	HTMLParser htmlParser;
	//URLGetter urlGetter;

	
	public SimpleBrowser(SpiderConfig config)
	{
		super(config);
		this.htmlParser = new HTMLParser(config);
		//this.urlGetter = new URLGetter(config);
	}

	@Override
	//主要接口
	public void runHttpGet(URLToDownload url, SpiderThread synObj) {
		// TODO Auto-generated method stub
		// Bail out early if image and already on disk
		URLObject obj = new URLObject(url.getURL(), this.config);
		//obj = urlGetter.getURL(url);
		System.out.println("obj=" + obj);
		if (obj == null) {
			this.urls = new ArrayList<URL>();
			return;
		}

		
		// by liqiang
		// if(!obj.existsOnDisk())
		// {
		// obj.writeToFile();
		// }
		//解析html
		if (obj == null)
		{
			this.urls = new ArrayList<URL>();
			return;
		}

		if (obj.isHTML() || obj.isXML()) {
			this.urls = htmlParser.parseLinksInDocument(url.getURL(),
					obj.getStringContent());
			return;
		} else if (obj.isImage()) {
			this.urls = new ArrayList<URL>();
			return;
		} else {
			this.urls = new ArrayList<URL>();
			return;
		}
	}

}
