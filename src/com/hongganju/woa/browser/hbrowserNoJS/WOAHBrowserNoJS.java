package com.hongganju.woa.browser.hbrowserNoJS;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import lotus.alg.lang.Pair;
import lotus.alg.util.LogUtil;
import weblech.spider.SpiderConfig;
import weblech.spider.SpiderThread;
import weblech.spider.URLToDownload;

import com.hongganju.common.socket.DCSocketConfig;
import com.hongganju.common.util.DBUtil;
import com.hongganju.communication.data.reportData.CDNPageReportData;
import com.hongganju.db.entity.center.Ipregion;
import com.hongganju.db.entity.task.Woacrawledflvurl;
import com.hongganju.db.entity.task.Woacrawledurllog;
import com.hongganju.woa.WOASystemUtil;
import com.hongganju.woa.browser.DBSaver;
import com.hongganju.woa.browser.WOAFilter;
import com.hongganju.woa.browser.WOASaver;
import com.hongganju.woa.browser.WebBrowserBase;
import com.hongganju.woa.browser.extractor.ExtractQueue;
import com.hongganju.woa.browser.ie.BrowseResult;
import com.hongganju.woa.util.WOABase;

//后台进程调用
public class WOAHBrowserNoJS extends WebBrowserBase{
	private static Logger logger = Logger.getLogger(WOAHBrowserNoJS.class); 
	public static String htmlBase = null;
	public static int number = 1;

	//减少并发访问，
//	private static long lastRunTime = 0;
	private static Object synObj = new Object();
	//单进程
//	private static Crawler c  = new Crawler();
	public WOAHBrowserNoJS(SpiderConfig config) {
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
			startProcess(synObj.getThreadNo(), target, this.woa.param.isDownloadObject);
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
			
//			System.out.println(LogUtil.toString(data));
			
			System.out.println(url + "有URL:" + data.second.size() + "个");
			logger.debug(url + "有URL:" + data.second.size() + "个");
			if(data.first != null)
			{
				this.mimeType = data.first.getFileType();
				this.httpCode = data.first.getHttpCode();
			}
			
			
			//成功
			if(data.first != null)
			{
				if(data.first.getItems() != null && data.first.getItems().size() > 0)
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
			}
			else
			{
				urlLog.setErrorDescription("crawler.first = null");
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
			this.urls = WOAFilter.filterURLs(data.second, woa);
			logger.info("过滤后url有：" + this.urls.size() + "个");
			
			//流媒体，交给后面的流媒体处理器处理
			if(this.woa.param.taskType == 2)
			{
				//要处理所有的视频原始连接
				if(data.second != null)
				{
					List<String> newFlvUrls = this.woa.param.extractNewFlvUrls(data.second);
					logger.info("放入flv队列：" + newFlvUrls.size());
					boolean add = false;
					//完成视频计数
					synchronized(this.woa.param.flvCountSynObj)
					{
						if(newFlvUrls.size() > 0)
						{
							this.woa.param.flvCount = this.woa.param.flvCount + newFlvUrls.size();
							add = true;
						}
					}
					if(add == true)
					{
						//保存到下载队列
						//转换对象
						List<Woacrawledflvurl> urls = new ArrayList<Woacrawledflvurl>();
						if(newFlvUrls != null)
						{
							for(int i=0; i<newFlvUrls.size(); i++)
							{
								Woacrawledflvurl fu = new Woacrawledflvurl();
								fu.setUrl(newFlvUrls.get(i));
								fu.setRefer(refer);
								urls.add(fu);
							}
//							ExtractQueue.getInstance().addUrls(data.second, refer);
							System.out.println("得到新的视频原始连接：" + urls.size() + "个， from:" + refer);
							//这里是保存到db中，这里是同步方法，每个任务结束会换一个param，不过，安全第一
							ExtractQueue.getInstance().addUrls(urls, this.woa.param);
						}
					}
				}
			}
			
//			logger.info("过滤后url有：" + this.urls + "个");
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
	
	private static void startProcess(int threadNo, String url, boolean downloadObjects)
	{
//		String cmd = "start java -cp lib/*;./config/;./xml com.hongganju.browser.multiprocess.client.IEBrowserMain " 
//			+ threadNo + " " + url;
		//由于bat传参不支持=等特殊字符，需要在这里变成base64
		url = WOABase.encodeBase64UTF8(url);
		//区分linux和windows
		if(WOASystemUtil.isWin())
		{
			String cmd = "crawlUrl.bat " + threadNo + " " + url + " " + DCSocketConfig.innerPort + " " + downloadObjects;
			System.out.println("hbrowser:" + cmd);
			System.out.println("noBase64: url=" + WOABase.decodeBase64UTF8(url));
			try {
				Process p = Runtime.getRuntime().exec(cmd);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			String cmd = "./crawlUrl.sh " + threadNo + " " + url + " " + DCSocketConfig.innerPort + " " + downloadObjects;
			System.out.println(cmd);
			try {
				Process p = Runtime.getRuntime().exec(cmd);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	public static void main(String[] args)
	{

	}
}
