package com.hongganju.woa.browser.hbrowserNoJS.core;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lotus.alg.dom.NodeUtil;
import lotusroots.browser.html.parser.NekoHtmlParser;

import org.w3c.dom.Node;

import com.hongganju.communication.data.reportData.CDNPageReportData;
import com.hongganju.communication.data.reportData.PageItem;
import com.hongganju.woa.util.ObjectExtractor;

public class HBrowserNoJSUtil {
	static public void setUrlParams(CDNPageReportData data, DownloadData download, String url)
	{
		if(data == null || download ==null)
			return;
		data.setPOPIP(download.Ip);
		data.setURL(url);
		data.setHttpCode(Integer.toString(download.HttpCode));
		data.setFileSize(Integer.toString(download.ContentRealBytes));
		data.setFileType(download.Type);
		data.setNetwordBytes(Integer.toString(download.NetWorkBytes));
//		data.setDownloadTime(Integer.toString(download.DownloadTime));
		data.setCname(download.CName);
		data.setTotalDownLoadTime(Float.toString(((float)download.DownloadTime) / 1000));
//		data.setErrorCode("0");
		data.setRedirectTime(Float.toString(download.RedirectTime));
		data.setConnectionTime(Float.toString(download.ConnectionTime));
		data.setFirstByteTime(Float.toString(download.FirstByteTime));
		data.setDNSTime(Long.toString(download.dnsResolveTotalTime));
	}
	
	static public void setUrlItemParam(PageItem data, DownloadData download, String url)
	{
		if(data == null || download ==null)
			return;
		data.setPOPIP(download.Ip);
		if(download.Url != null)
		if(url != null)
			data.setURL(url);
		else if(download.Url != null)
			data.setURL(download.Url.url);
		data.setHttpCode(Integer.toString(download.HttpCode));
		data.setFileSize(Integer.toString(download.ContentRealBytes));
		data.setFileType(download.Type);
		data.setNetwordBytes(Integer.toString(download.NetWorkBytes));
		data.setContentBytes(Integer.toString(download.ContentRealBytes));
//		data.setDownloadTime(Integer.toString(download.DownloadTime));
		data.setCname(download.CName);
//		data.setTotalDownLoadTime(Integer.toString(download.DownloadTime));
		data.setTotalDownLoadTime(Float.toString(((float)download.DownloadTime) / 1000));
		data.setErrorCode("0");
		data.setRedirectTime(Float.toString(download.RedirectTime));
		data.setConnectionTime(Float.toString(download.ConnectionTime));
		data.setFirstByteTime(Float.toString(download.FirstByteTime));
		data.setDNSTime(Long.toString(download.dnsResolveTotalTime));
	}
	
	static public List<String> extractUrls(String html, String url)
	{
		if(html == null)
			return new ArrayList<String>();
		
		// 提取页面内的url
		NekoHtmlParser parser = new NekoHtmlParser();
		Node root;
		try {
			root = parser.parseStandard(html);
			List<String> list1 = NodeUtil.getHtmlSubUrl(new URL(url), root, true, true, true, false);
			List<String> list2 = new ArrayList<String>();
			HashMap<String, String> filter = new HashMap<String, String>();
			for(int i=0; i<list1.size(); i++)
			{
				String key = list1.get(i);
				if(!filter.containsKey(key))
				{
					list2.add(key);
					filter.put(key, null);
				}
				
			}
			return list2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}
	static public List<String> extractObjects(String html, String url)
	{
//		List<String> items = new ArrayList<String>();
		
//		items.add("http://img02.taobaocdn.com/tps/i2/T16WJqXaXeXXXXXXXX-32-32.gif");
//		items.add("http://a.tbcdn.cn/p/fp/2012/misc/lbs/??flashstorage.js,lbs.js?t=20120802.js");
//		items.add("http://img02.taobaocdn.com/tps/i2/T1lcyQXnJaXXXXXXXX-16-16.png");
//		items.add("http://img02.taobaocdn.com/tps/i2/T1gD6wXoxlXXaokqjm-70-40.gif");
//		items.add("http://img03.taobaocdn.com/tps/i3/T1ZeuRXaFtXXXXXXXX-300-429.png");
//		items.add("http://img03.taobaocdn.com/tps/i3/T1lni5XhXXXXXXXXXX-9-14.gif");
		
		
//		return items;
		
		return ObjectExtractor.extractObjects(html, url);
	}
}
