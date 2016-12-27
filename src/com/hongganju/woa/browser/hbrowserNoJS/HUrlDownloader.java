package com.hongganju.woa.browser.hbrowserNoJS;

import java.io.IOException;
import java.util.List;

import lotus.http.download.HttpDownloadUrl;

import com.hongganju.common.util.FileUtil;
import com.hongganju.woa.browser.hbrowserNoJS.core.CrawlWithNoJS;
import com.hongganju.woa.browser.hbrowserNoJS.core.CrawlWithNoJSListener;
import com.hongganju.woa.browser.hbrowserNoJS.core.DownloadData;
import com.hongganju.woa.browser.hbrowserNoJS.core.HBrowserNoJSUtil;

public class HUrlDownloader implements CrawlWithNoJSListener{
	DownloadData data = null;
	String errorMsg = null;
	String url = null;
	long maxDownloadBytes = 999999999;
	public void setMaxDownloadBytes(long maxDownloadBytes)
	{
		this.maxDownloadBytes = maxDownloadBytes;
	}
	public String getErrormsg()
	{
		return errorMsg;
	}
	@Override
	public void start(HttpDownloadUrl url) {
		// TODO Auto-generated method stub
		
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
	public DownloadData download(String url)
	{
		CrawlWithNoJS crawl = new CrawlWithNoJS(this);
		System.out.println("maxDownloadBytes=" + maxDownloadBytes);
		crawl.getSetting().httpDownloadSetting.maxDownLoadLen = (int)this.maxDownloadBytes;
		this.url = url;
		try {
			crawl.add(url);
			//最多20Min
			synchronized(this){
				this.wait(1200000);
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
	public static void main(String[] args) throws IOException
	{
		SingleUrlDownloader single = new SingleUrlDownloader();
		CrawlWithNoJS crawler = new CrawlWithNoJS(single);
		crawler.getSetting().httpDownloadSetting.maxDownLoadLen = 10 * 1024 * 1024;
		String url = "http://www.youku.com";
		url = "http://f.youku.com/player/getFlvPath/sid/13558874252551041_00/st/flv/fileid/030002010050CFF9F0733804E9D2A796F8C70C-5B5B-16AA-BD1B-7988E09234C3?K=7b097235a7486247261cbc28&hd=0|||http://f.youku.com/player/getFlvPath/sid/13558874252551041_00/st/mp4/fileid/030008010050CFFBE1733804E9D2A796F8C70C-5B5B-16AA-BD1B-7988E09234C3?K=aca920edcd4030ca24113db2&hd=1|||";
		DownloadData data = single.download(crawler, url);
		System.out.println(data.DownloadTime);
		System.out.println(data.ContentRealBytes);
		System.out.println(data.isOK);
//		System.out.println(data.Html);
//		FileUtil.write("youku.html", data.Html);
		List<String> urls = HBrowserNoJSUtil.extractUrls(data.Html, url);
		System.out.println(urls.size());
		
		for(int i=0; i<urls.size(); i++)
			System.out.println(urls.get(i));
		System.exit(0);
	}

}
