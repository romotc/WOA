package com.hongganju.woa.browser.hbrowserNoJS;

import java.util.List;

import com.hongganju.communication.data.reportData.PageItem;
import com.hongganju.woa.browser.hbrowserNoJS.core.CrawlWithNoJS;
import com.hongganju.woa.browser.hbrowserNoJS.core.CrawlWithNoJSListener;
import com.hongganju.woa.browser.hbrowserNoJS.core.DownloadData;
import com.hongganju.woa.browser.hbrowserNoJS.core.HBrowserNoJSUtil;

import lotus.http.download.HttpDownloadUrl;
//使用独立线程下载url
public class SingleUrlDownloader implements CrawlWithNoJSListener{
//	CrawlWithNoJS crawl = null;
	DownloadData data = null;
	String errorMsg = null;
	String url = null;
	public SingleUrlDownloader()
	{
//		this.crawl = new CrawlWithNoJS(this);
	}
	public String getErrormsg()
	{
		return errorMsg;
	}

	public void downloadItems(CrawlWithNoJS crawl, List<PageItem> items)
	{
		try {
			crawl.add(url);
			synchronized(this){
				this.wait(10000);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public DownloadData download(CrawlWithNoJS crawl, String url)
	{
		this.url = url;
		try {
			crawl.add(url);
			synchronized(this){
				this.wait(10000);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(this.errorMsg != null)
		{
			if(this.data == null)
				this.data = new DownloadData();
			this.data.isOK = false;
		}
		this.data.isOK = true;
		return this.data;
	}
	
	@Override
	public void complete(DownloadData data) {
		// TODO Auto-generated method stub
		this.data = data;
		synchronized(this)
		{
			this.notify();
		}
	}

	@Override
	public void error(HttpDownloadUrl url, String errMsg) {
		// TODO Auto-generated method stub
		this.errorMsg = errMsg;
		synchronized(this)
		{
			this.notify();
		}
	}

	@Override
	public void start(HttpDownloadUrl url) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args)
	{
		SingleUrlDownloader single = new SingleUrlDownloader();
		CrawlWithNoJS crawler = new CrawlWithNoJS(single);
		DownloadData data = single.download(crawler, "http://www.qq.com");
		System.out.println(data.DownloadTime);
		System.out.println(data.ContentRealBytes);
		System.out.println(data.isOK);
//		System.out.println(data.Html);
		System.out.println(HBrowserNoJSUtil.extractUrls(data.Html, "http://www.qq.com").size());
		System.exit(0);
	}
}
