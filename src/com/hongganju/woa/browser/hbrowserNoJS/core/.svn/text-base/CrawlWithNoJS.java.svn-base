package com.hongganju.woa.browser.hbrowserNoJS.core;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import lotus.crawl.SimpleCrawler;
import lotus.crawl.SimpleCrawler.SimpleCrawlerListener;
import lotus.crawl.SimpleCrawler.SimpleCrawlerSetting;
import lotus.http.download.EnumDataType;
import lotus.http.download.HttpDownloadDataEx;
import lotus.http.download.HttpDownloadUrl;
import lotus.http.download.HttpHeadNames;
import lotus.http.download.util.HttpHeadUtil;
import lotus.net.DnsServers;


public class CrawlWithNoJS implements SimpleCrawlerListener {
	

	CrawlWithNoJSListener mListener;
	SimpleCrawler mInnerCrawler;
	Map<String, String> mCnameCache;

	public CrawlWithNoJS(CrawlWithNoJSListener crawForLiQiangListener) {
		mListener = crawForLiQiangListener;
		mCnameCache = new HashMap<String, String>();
		mInnerCrawler = new SimpleCrawler(this);
		mInnerCrawler.start();
	}

	/**
	 * 得到配置
	 */
	public SimpleCrawlerSetting getSetting() {
		return mInnerCrawler.getSetting();
	}

	/**
	 * 添加下载url
	 */
	public void add(String url) throws Exception {
		HttpDownloadUrl u = new HttpDownloadUrl();
		u.url = url;
		mInnerCrawler.download(u);
	}

	/**
	 * 添加下载url
	 */
	public void add(HttpDownloadUrl url) throws Exception {
		mInnerCrawler.download(url);
	}

	@Override
	public void start(HttpDownloadUrl url, long timepoint) {
		if (mListener != null)
			mListener.start(url);
	}

	@Override
	public void complete(HttpDownloadDataEx data, long timepoint) {
		if (mListener != null) {
			DownloadData d = new DownloadData();
			d.ContentLenFromHead = HttpHeadUtil.getContentLen(data.respHeaders);
			d.ContentRealBytes = data.body.length;
			if (data.dataType == EnumDataType.TextCSS)
				d.Css = data.dataOfText;
			else if (data.dataType == EnumDataType.TextHtml)
				d.Html = data.dataOfText;
			else if (data.dataType == EnumDataType.TextJs)
				d.Js = data.dataOfText;
			d.DownloadTime = (int) data.totalTime;
			d.HttpCode = data.statusCode;
			d.HttpRetHead = data.respHead;
			d.Ip = data.ip;
			d.NetWorkBytes = (int) (data.inputBytes + data.outputBytes);
			d.Type = HttpHeadUtil.getHeader(HttpHeadNames.CONTENT_TYPE, data.respHeaders);
			d.Url = data.url;
			//转换为s
			d.RedirectTime = ((float)data.redirectTime)/1000;
			d.ConnectionTime = ((float)data.connTime)/1000;
			d.FirstByteTime = ((float)data.respFirstTime)/1000;
			d.dnsResolveTotalTime = data.dnsTime;
			try{
				URL u = new URL(d.Url.url);
				String host = u.getHost();
				if(mCnameCache.containsKey(host))
					d.CName = mCnameCache.get(host);
				else
				{
					d.CName = DnsServers.getCnames(host);
					mCnameCache.put(host, d.CName);
				}
			}
			catch(Exception e)
			{
			}

			mListener.complete(d);
		}
	}

	@Override
	public void error(HttpDownloadUrl url, String errMsg, long timepoint) {
		if (mListener != null)
			mListener.error(url, errMsg);
	}


}

