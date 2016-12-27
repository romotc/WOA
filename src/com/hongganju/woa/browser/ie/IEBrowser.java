package com.hongganju.woa.browser.ie;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import lotus.alg.lang.Pair;
import weblech.spider.SpiderConfig;
import weblech.spider.SpiderThread;
import weblech.spider.URLToDownload;

import com.hongganju.common.socket.DCSocketConfig;
import com.hongganju.common.util.DBUtil;
import com.hongganju.communication.data.reportData.CDNPageReportData;
import com.hongganju.db.entity.task.Woacrawledurllog;
import com.hongganju.woa.browser.DBSaver;
import com.hongganju.woa.browser.WOAFilter;
import com.hongganju.woa.browser.WOASaver;
import com.hongganju.woa.browser.WebBrowserBase;
import com.hongganju.woa.util.WOABase;
import com.pt.client.crawl.Crawler;


public class IEBrowser extends WebBrowserBase{
	private static Logger logger = Logger.getLogger(IEBrowser.class); 
	public static String htmlBase = null;
	public static int number = 1;

	//减少并发访问，
//	private static long lastRunTime = 0;
	private static Object synObj = new Object();
	//单进程
//	private static Crawler c  = new Crawler();
	public IEBrowser(SpiderConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
		
	}
	protected void runHttpGet2(URLToDownload url, SpiderThread synObj) {
		//
		this.urls = new ArrayList<URL>();
		for(int i=0; i<40; i++)
		{
			try {
				this.urls.add(new URL(htmlBase + number + ".html"));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			number++;
		}
		//过滤多余的
		synchronized(woa)
		{
			int left = woa.totalUrl - woa.crawledUrls;
			if(left < 0)
				left = 0;
			if(left == 0)
			{
				this.urls = new ArrayList<URL>();
			}else if(left < this.urls.size())
			{
				this.urls = this.urls.subList(0, left);
			}
		}

		return;
	}

	protected void runHttpGet(URLToDownload url, SpiderThread synObj) {
		System.out.println("using IE");
		// TODO Auto-generated method stub
		Pair<CDNPageReportData, List<String>> data = new Pair<CDNPageReportData, List<String>>();
		String error;
		String u = null;
		try {
			//爬取
			String target = url.getURL().toString();
			System.out.println(target);
			
			//延迟一点，避免并发
			synchronized(synObj)
			{
				long t = System.currentTimeMillis();
				long m = t - this.woa.lastBrowseTime;
				//3s
				if(m < this.config.getCrawlInterval())
				{
					Thread.sleep(this.config.getCrawlInterval() - m);
				}
				this.woa.lastBrowseTime = System.currentTimeMillis();
			}
			//下载
//			data = c.download(target);
			//启动进程
			startProcess(synObj.getThreadNo(), target);
			System.out.println("thread:" + synObj.getName() + " wait for crawling");
			//wait，等待进程执行完毕
			synchronized(synObj)
			{
				//10分钟超时
				synObj.wait(300000);
			}
			u = url.getURL().toString();
			Woacrawledurllog urlLog = new Woacrawledurllog();
			urlLog.setUrl(u);
			urlLog.setInstanceId(this.woa.getParam().taskInstanceId);
			urlLog.setTaskid(this.woa.getParam().taskId);
			urlLog.setUploadTime(new Date());
			if(u.length() > 1024)
				u = u.substring(0,1024);
			BrowseResult result = synObj.result;
			if(result == null)
			{
				System.out.println("超时/内部错误，进程返回结果为null");
				//记录在timeout表里
				urlLog.setStatus(2);
				try{
					logger.info("超时错误：" + urlLog.getUrl());
					DBUtil.saveJDBC(urlLog, this.woa.getParam().dbid);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				return;
			}
			data.first = result.data;
			data.second = result.urls;
			
			String refer = null;
			if(url.getReferer() != null)
				refer = url.getReferer().toString();
			
			//保存结果
//			Ipregion re = WOASaver.save(data.first, url.getURL().getHost(), refer, this.woa);
			
			System.out.println(url + "有URL:" + data.second.size() + "个");
			logger.debug(url + "有URL:" + data.second.size() + "个");
			if(data.first != null)
			{
				this.mimeType = data.first.getFileType();
				this.httpCode = data.first.getHttpCode();
			}
			
			
			//成功
			if(data.first != null && data.first.getItems() != null && data.first.getItems().size() > 0)
			{
				String httpcode = data.first.getItems().get(0).getHttpCode();
				if(httpcode != null && httpcode.equals("200"))
					urlLog.setStatus(0);
				else
				{
					urlLog.setStatus(1);
					urlLog.setErrorDescription(data.first.getErrorDescription());
				}
			}
			else
			{
				if(data.first.getItems() != null && data.first.getItems().size() > 0)
				{
					urlLog.setErrorDescription(data.first.getErrorDescription());
				}
				urlLog.setStatus(1);
			}
			if(urlLog.getErrorDescription() != null && urlLog.getErrorDescription().length() > 1024)
				urlLog.setErrorDescription(urlLog.getErrorDescription().substring(0,1024));
			try{
//				System.out.println("record：" + t.getUrl() + " dbid=" + this.woa.getParam().dbid);
				logger.info("运行结果：" + urlLog.getStatus() + ", url=" + urlLog.getUrl());
				DBUtil.saveJDBC(urlLog, this.woa.getParam().dbid);
//				System.out.println("saved：" + t.getUrl() + " dbid=" + this.woa.getParam().dbid);
			}catch(Exception e)
			{
				
			}

			//保存item
			if(data.first != null && data.first.getItems() != null)
			{
				logger.info("要保存的元素: " + data.first.getItems().size() + "个");
				//过滤爬过的和不在domain里面的
//				List filtered = WOAFilter.filterPageitems(data.first.getItems(), this.woa);
				List filtered = data.first.getItems();
//				System.out.println("过滤后元素: " + data.first.getItems().size() + "个");
				if(filtered != null)
				{
					WOASaver.saveItems(filtered, woa, refer);
				}
			}
			
			//保存错误到错误表中
			DBSaver.saveError(data.first, url.getReferer(), url.getURL().toExternalForm(), woa.spider.param);
			
			//解析新的url
			//过滤爬过的
			if(data.second != null)
				logger.info("过滤前url有：" + data.second.size() + "个");
			this.urls = WOAFilter.filterURLs((List<String>)data.second, woa);
			logger.info("过滤后url有：" + this.urls + "个");
			return;
			//System.out.println(LogUtil.toString(data));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = e.getMessage();
		}
		Woacrawledurllog urlLog = new Woacrawledurllog();
		urlLog.setUrl(u);
		urlLog.setInstanceId(this.woa.getParam().taskInstanceId);
		urlLog.setTaskid(this.woa.getParam().taskId);
		urlLog.setUploadTime(new Date());
		if(u.length() > 1024)
			u = u.substring(0,1024);
		urlLog.setErrorDescription(error);
		urlLog.setStatus(1);
		try{
			DBUtil.saveJDBC(urlLog, this.woa.getParam().dbid);
		}catch(Exception e)
		{
			
		}
		//如果出错就保存错误
//		WOASaver.saveError(url.getURL(), error, this.woa);
		this.urls = new ArrayList<URL>();
	}
	private static void startProcess(int threadNo, String url)
	{
//		String cmd = "start java -cp lib/*;./config/;./xml com.hongganju.browser.multiprocess.client.IEBrowserMain " 
//			+ threadNo + " " + url;
		url = WOABase.encodeBase64UTF8(url);
		String cmd = "crawlUrl.bat " + threadNo + " " + url + " " + DCSocketConfig.innerPort;
		System.out.println("IEBrowser" + cmd);
		System.out.println("noBase64: url=" + WOABase.decodeBase64UTF8(url));
		try {
			Process p = Runtime.getRuntime().exec(cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception
	{
		Crawler c  = new Crawler();
		Pair<CDNPageReportData, List<String>> data = c.download("http://www.sina.com.cn");
		System.out.println("ok");
	}
}
