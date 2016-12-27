package com.hongganju.woa.browser.hbrowser;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lotus.alg.lang.Pair;
import lotus.alg.util.LogUtil;
import weblech.spider.SpiderConfig;
import weblech.spider.SpiderThread;
import weblech.spider.URLToDownload;

import com.hongganju.communication.data.reportData.CDNPageReportData;
import com.hongganju.db.entity.center.Ipregion;
import com.hongganju.woa.browser.DBSaver;
import com.hongganju.woa.browser.WOAFilter;
import com.hongganju.woa.browser.WOASaver;
import com.hongganju.woa.browser.WebBrowserBase;
import com.pt.client.crawl.Crawler;
import com.pt.client.crawl.HBrowserCrawler;

//基于httpClient3.0实现
//目前不再使用
public class WOAHBrowser extends WebBrowserBase{
	public WOAHBrowser(SpiderConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}

	@Override
	//depth=0时，保存主url，其他时只保存item
	protected void runHttpGet(URLToDownload url, SpiderThread synObj) {
		// TODO Auto-generated method stub
		Pair<CDNPageReportData, List<String>> data = null;
		String error;
		try {
			//爬取
			String target = url.getURL().toString();
			System.out.println(target);
			
			//下载
			HBrowserCrawler c  = new HBrowserCrawler();
			data = c.download(target);
			
			String refer = null;
			if(url.getReferer() != null)
				refer = url.getReferer().toString();
			Ipregion re = WOASaver.save(data.first, url.getURL().getHost(), refer, this.woa);
			System.out.println(url + "有URL:" + data.second.size() + "个");
			this.mimeType = data.first.getFileType();
			this.httpCode = data.first.getHttpCode();
			
			//保存item
			if(data.first != null && data.first.getItems() != null)
			{
				System.out.println("过滤前: " + data.first.getItems().size() + "个");
				List filtered = WOAFilter.filterPageitems(data.first.getItems(), this.woa);
				if(filtered != null)
				{
					WOASaver.saveItems(filtered, woa, refer);
				}
			}
			
			//解析新的url
			//过滤新的
			//去掉多余的
			this.urls = WOAFilter.filterURLs((List<String>)data.second, woa);

			return;
			//System.out.println(LogUtil.toString(data));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = e.getMessage();
		}
		
//		WOASaver.saveError(url.getURL(), error, this.woa);
		//保存错误到错误表中
		if(data != null)
			DBSaver.saveError(data.first, url.getReferer(), url.getURL().toExternalForm(), woa.spider.param);
		this.urls = new ArrayList<URL>();
		
	}

	
	public static void main(String[] args)
	{
		HBrowserCrawler c  = new HBrowserCrawler();
		Pair<CDNPageReportData, List<String>> data;
		try {
//			data = c.download("http://benq.zol.com.cn/topic/2903335.html");
//			data = c.download("http://soft.zol.com.cn/296/2969721.html");
			data = c.download("http://www.sx.10086.cn");
			
			System.out.println(LogUtil.toString(data));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
