package com.hongganju.woa.browser.hbrowserNoJS;

import java.util.ArrayList;
import java.util.List;

import lotus.http.download.HttpDownloadUrl;

import com.hongganju.communication.data.reportData.PageItem;
import com.hongganju.woa.browser.hbrowserNoJS.core.CrawlWithNoJS;
import com.hongganju.woa.browser.hbrowserNoJS.core.CrawlWithNoJSListener;
import com.hongganju.woa.browser.hbrowserNoJS.core.DownloadData;
import com.hongganju.woa.browser.hbrowserNoJS.core.HBrowserNoJSUtil;

public class ItemsDownloader implements CrawlWithNoJSListener{
	int maxDownload = 0;
	int downloaded = 0;
	List<PageItem> items = new ArrayList<PageItem>();
	public List<PageItem> downloadItems(CrawlWithNoJS crawl, List<String> urls)
	{
		maxDownload = urls.size();
		for(int i=0; i<urls.size(); i++)
		{
			try {
				crawl.add(urls.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		synchronized(this){
			try {
				this.wait(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return items;
	}
	@Override
	public void complete(DownloadData data) {
		// TODO Auto-generated method stub
		synchronized(this)
		{
			PageItem item = new PageItem();
			items.add(item);
			HBrowserNoJSUtil.setUrlItemParam(item, data, null);
			item.setErrorCode("0");
			downloaded++;
			if(downloaded >= maxDownload)
			{
				this.notify();
			}
		}
		
	}
	@Override
	public void error(HttpDownloadUrl url, String errMsg) {
		// TODO Auto-generated method stub
		synchronized(this)
		{
			PageItem item = new PageItem();
			items.add(item);
			item.setErrorCode("0");
			item.setURL(url.url);
//			item.setErrorCode("H001");
			item.setErrorCode(errMsg);
//			HBrowserNoJSUtil.setUrlParams(item, url);
			downloaded++;
			if(downloaded >= maxDownload)
			{
				this.notify();
			}
		}
	}
	@Override
	public void start(HttpDownloadUrl url) {
		// TODO Auto-generated method stub
		
	}
}
