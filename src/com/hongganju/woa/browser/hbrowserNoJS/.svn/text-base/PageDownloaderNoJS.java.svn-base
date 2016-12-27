package com.hongganju.woa.browser.hbrowserNoJS;

import java.util.ArrayList;
import java.util.List;

import lotus.alg.lang.Pair;

import com.hongganju.communication.data.reportData.CDNPageReportData;
import com.hongganju.communication.data.reportData.PageItem;
import com.hongganju.woa.browser.hbrowserNoJS.core.CrawlWithNoJS;
import com.hongganju.woa.browser.hbrowserNoJS.core.DownloadData;
import com.hongganju.woa.browser.hbrowserNoJS.core.HBrowserNoJSUtil;

//需求：
//1, 不执行js    OK
//2, 过滤，某些后缀的元素不下载   不实现
//3, 过滤，根据域名下载元素    不实现
//4, 返回页面上的元素和已经下载的元素    没必要
//5, 不渲染
//6, 事件驱动的下载
//7, 自动处理各种弹出窗口
public class PageDownloaderNoJS {
	long maxDownloadBytes = -1;
	public void setMaxDownloadBytes(long maxDownloadBytes)
	{
		this.maxDownloadBytes = maxDownloadBytes;
	}
	public Pair<CDNPageReportData, List<String>> download(String url, boolean downloadObjects)
	{
		System.out.println("using HBrowserCrawlerWithNoJS downloadObjects=" + downloadObjects);
//		Pair<CDNPageReportData, List<String>> pair = new Pair<CDNPageReportData, List<String>>();
//		return pair;
		return downloadPage(url, downloadObjects);
	}
	
	//1, 下载主页
	//2, 下载所有元素
	//3, 出错和超时
	private Pair<CDNPageReportData, List<String>> downloadPage(String url, boolean downloadObjects)
	{
		Pair<CDNPageReportData, List<String>> pair = new Pair<CDNPageReportData, List<String>>();
		CDNPageReportData data = new CDNPageReportData();
		data.setItems(new ArrayList<PageItem>());
		pair.first = data;
		//下载主页
		SingleUrlDownloader downloader = new SingleUrlDownloader();
		CrawlWithNoJS crawler = new CrawlWithNoJS(downloader);
		DownloadData mainUrlDownloadData = downloader.download(crawler, url);
		//填充主页数据
		HBrowserNoJSUtil.setUrlParams(data, mainUrlDownloadData, url);
		data.setErrorDescription(downloader.getErrormsg());
		
		//第一个Item，暂时没有保存
		PageItem mainItem = new PageItem();
		mainItem.setErrorCode(downloader.getErrormsg());
		HBrowserNoJSUtil.setUrlItemParam(mainItem, mainUrlDownloadData, url);
		data.getItems().add(mainItem);
		
		if(mainUrlDownloadData != null && mainUrlDownloadData.Html != null)
		{
			//抽取主页的链接
			List<String> urls = HBrowserNoJSUtil.extractUrls(mainUrlDownloadData.Html, url);
			pair.second = urls;
			
			//下载元素
			if(downloadObjects == true)
			{
				//抽取主页元素
				List<String> itemUrls = HBrowserNoJSUtil.extractObjects(mainUrlDownloadData.Html, url);
				//下载主页元素
				ItemsDownloader itemDownloader = new ItemsDownloader();
				CrawlWithNoJS crawler2 = new CrawlWithNoJS(itemDownloader);
				//下载并填充主页元素
				List<PageItem> items = itemDownloader.downloadItems(crawler2, itemUrls);
				for(int i=0; i<items.size(); i++)
				{
					data.getItems().add(items.get(i));
				}
			}
		}
		else
		{
			pair.second = new ArrayList<String>();
		}
		
		
		return pair;
		
	}
	public static void main(String[] args)
	{
		PageDownloaderNoJS c = new PageDownloaderNoJS();
		boolean downloadObject = true;
		Pair<CDNPageReportData, List<String>> list = c.download("http://www.sina.com.cn", downloadObject);
		System.out.println("ok");
	}
}
