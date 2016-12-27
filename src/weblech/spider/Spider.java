/*
  * This is the MIT license, see also http://www.opensource.org/licenses/mit-license.html
 *
 * Copyright (c) 2001 Brian Pitcher
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

// $Header: /cvsroot/weblech/weblech/src/weblech/spider/Spider.java,v 1.8 2002/06/09 11:34:38 weblech Exp $

package weblech.spider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hongganju.common.socket.DCSocketConfig;
import com.hongganju.common.socket.DCSocketServer;
import com.hongganju.db.entity.task.Woacrawledflvurl;
import com.hongganju.db.entity.task.Woacrawledurl;
import com.hongganju.db.sessionFactory.TaskFactory;
import com.hongganju.woa.browser.extractor.ExtractQueue;
import com.hongganju.woa.browser.extractor.FlvDownloadQueue;
import com.hongganju.woa.browser.ie.MultiIEThreadManager;
import com.hongganju.woa.browser.ie.WOAIEProvider;
import com.hongganju.woa.factory.BrowserFactory;
import com.hongganju.woa.factory.PostProcessorFactory;
import com.hongganju.woa.spider.WOASpider;
import com.hongganju.woa.spider.WOASpiderParam;


//根据Config文件中的配置，启动n个线程访问web
//注意，该类的数据是被共享使用的
//一个爬虫只执行一个任务，这里要并行
public class Spider implements Constants {
	private static Logger logger = Logger.getLogger(Spider.class); 
	//多个任务共享
	static DCSocketServer socketserver = null;
	static{
		socketserver = new DCSocketServer(DCSocketConfig.innerPort);
		socketserver.start();
	}
	
	/** Config for the spider */
//	private WebBrowserBase browser;
//	private WOAPostProcessURLInterface prostProcessor = new SimplePostProcessURL();
	public SpiderConfig config;
	public MultiIEThreadManager threadManager;
	public WOASpiderParam param = null;
	public WOASpider woa = null;
	public int postType = PostProcessorFactory.SIMPLE;
	public int browserType = BrowserFactory.HBROWSERNOJS;
	//正在运行的线程，只有aliveThreads和队列urlsDownloadedOrScheduled都为空，一个爬行任务才结束
	public int aliveThreads = 0;
	public void setWOA(WOASpider woa)
	{
		this.woa = woa;
	}
	/**
	 * Download queue. Thread safety: To access the queue, first synchronize on
	 * it.
	 */
	//下载队列
	public DownloadQueue queue;
	/**
	 * Set of URLs downloaded or scheduled, so we don't download a URL more than
	 * once. Thread safety: To access the set, first synchronize on it.
	 */
	//已经访问过的队列
	public Set urlsDownloadedOrScheduled;
	/**
	 * Set of URLs currently being downloaded by Spider threads. Thread safety:
	 * To access the set, first synchronize on it.
	 */
	
	//正在访问的队列，（原来的checkpoint使用，现在已经不用了）
//	public Set urlsDownloading;
	/**
	 * Number of downloads currently taking place. Thread safety: To modify this
	 * value, first synchronize on the download queue.
	 */
//	public int downloadsInProgress;
	/** Whether the spider should quit */
	public boolean quit;
	/** Count of running Spider threads. */
	public int runningThread;
	/** Time we last checkpointed. */
//	public long lastCheckpoint;
	
	private List<Woacrawledurl> readCrawledUrls()
	{
		String hql = "from Woacrawledurl where instanceId="
				+ this.param.instance.getTaskinstanceId() + "order by id";
		SessionFactory sessionFactory = TaskFactory.getSessionFactory(this.param.dbid);
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Query q = session.createQuery(hql);
		List<Woacrawledurl> list = q.list();
		t.commit();
		return list;
	}
	private void processCrawledUrls(List<Woacrawledurl> list)
	{
		this.woa.crawledUrls = list.size();
//		urlTotalCount = list.size();
		this.woa.currentUrl = this.param.instance.getCurrentCrawledUrl();
		this.woa.crawledUrlsCounter = this.woa.currentUrl;
		int crawled = this.woa.currentUrl - 1;
		ArrayList u2dsToQueue = new ArrayList();
		for (int i = 1; i <= list.size(); i++) {
			if (i <= crawled) {
				// 已经访问过
				try {
					System.out.println("already visit: "
							+ list.get(i).getUrl());
					URL u = new URL(list.get(i).getUrl());
					urlsDownloadedOrScheduled.add(u);
				} catch (Exception e) {
				}
			} else {
				try {
					URL u = new URL(list.get(i).getUrl());
					URL ref = null;
					if (list.get(i).getRefer() != null)
						ref = new URL(list.get(i).getRefer());
					URLToDownload todownload = new URLToDownload(u, ref,
							list.get(i).getDepth());
					u2dsToQueue.add(todownload);
				} catch (Exception e) {
				}
			}
		}
		synchronized (queue) {
			queue.queueURLs(u2dsToQueue);
		}
	}
	private List<Woacrawledflvurl> readCrawledFlvUrls()
	{
		String hql = "from Woacrawledflvurl where instanceId="
				+ this.param.instance.getTaskinstanceId() + "order by id";
		SessionFactory sessionFactory = TaskFactory.getSessionFactory(this.param.dbid);
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Query q = session.createQuery(hql);
		List<Woacrawledflvurl> list = q.list();
		t.commit();
		return list;
	}
	//不用更新instance，这里记录了爬行的位置（实时更新）
	//已经采集到的url数量(在WebBrowserBase里计数)
//	public int crawledFlvUrls = 0;
	//下一个url，当前爬到的位置(用于断点续传)(在WebBrowserBase里计数)
//	public int currentFlvUrl = 0;
	//写crawledUrl的计数器(在写db时计数，DBSaver)
//	public int crawledFlvUrlsCounter = 0;
	private void processCrawledFlvUrls(List<Woacrawledflvurl> list)
	{
		if(list == null || list.size() == 0)
			return;
		//计数
		this.param.crawledFlvUrls = list.size();
		if(this.param.instance.getCurrentFlvUrl() != null)
		{
			this.param.currentFlvUrl = this.param.instance.getCurrentFlvUrl();
			this.param.initCrawledFlvUrlId(this.param.instance.getCurrentFlvUrl());
		}
		//初始化过滤器
		this.param.addToFilter(list);
//		FlvExtractor.addToFilter(list);
		
		List<Woacrawledflvurl> newList = new ArrayList<Woacrawledflvurl>();
		for(int i=0; i<list.size(); i++)
		{
			Woacrawledflvurl u = list.get(i);
			if(u.getStatus() != null && u.getStatus() == 0)
				newList.add(u);
		}
		
		//初始化待处理队列
		logger.debug("新增视频爬虫"+ newList.size() + "个");
		ExtractQueue.getInstance().initUrls(newList);
		
	}
	private void initInstance() {
		if (this.param.instance != null) {
			//查找以前爬过的url
			List<Woacrawledurl> list = this.readCrawledUrls();

			//处理每个url
			//1，已经访问过的加入filter
			//2，未访问的进入队列
			processCrawledUrls(list);
		}
	}
	public Spider(SpiderConfig config, WOASpiderParam param, WOASpider woa) {
		this.config = config;
		this.param = param;
		this.woa = woa;
		//默认是简单的浏览器
//		browser = new SimpleBrowser(config);
		// 得到全部的过滤关键字

	
		queue = new DownloadQueue(config);
		urlsDownloadedOrScheduled = new HashSet();
//		urlsDownloading = new HashSet();
//		downloadsInProgress = 0;
//		lastCheckpoint = 0;
		initInstance();

		//如果是空的导入新的节点
		if(queue.size() == 0)
		{
			String domain = param.getSeed();
			if (!domain.startsWith("http://")) {
				domain = "http://" + domain;
			}
			URL url = null;
			try {
				url = new URL(domain);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//放进任务队列中
			if (url != null)
				queue.queueURL(new URLToDownload(url, 0));
		}
	}
	
	//根据配置文件启动n个线程，并发访问web
	//流媒体队列在这里处理，因为处理线程是在这之前启动的
	public void start() {
		quit = false;
		runningThread = 0;
//		ExtractQueue.getInstance().setWOAParam(this.param);
		ExtractQueue.getInstance().startThread();
//		FlvDownloadQueue.getInstance().setWOAParam(this.param);
		FlvDownloadQueue.getInstance().startThread();
		//处理流媒体的队列
		if(this.woa.param.taskType == 2)
		{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Woacrawledflvurl> list2 = readCrawledFlvUrls();
			this.processCrawledFlvUrls(list2);
		}
		
		SpiderThread[] threads = new SpiderThread[config.getSpiderThreads()];
		for(int i = 0; i < config.getSpiderThreads(); i++)
        {
//            Thread t = new Thread(this, "Spider-Thread-" + (i + 1));
            SpiderThread t = new SpiderThread(this, i);
            threads[i] = t;
        }
		this.threadManager = new MultiIEThreadManager(threads);
		WOAIEProvider.threadmanager = this.threadManager;
		
		List<Thread> runningThreads = new LinkedList<Thread>();
		try{
			for (int i = 0; i < threads.length; i++) {
				logger.info("Starting Spider thread");
				Thread t = threads[i];
				runningThreads.add(t);
				t.start();
				runningThread++;
			}
		// 等待所有线程结束
		/*
		for(Thread t : runningThreads)
			t.join();*/
		} catch (Exception nouse) {
			nouse.printStackTrace();
			
			// 退出所有线程
//			for(Thread t : runningThreads)
//				t.interrupt();
		}
		
		while(true)
		{
			if(quit == true && this.runningThread == 0)
				break;
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("结束，开始统计");
		//统计
//		this.woa.statAll();
	}
//	public void setBrowser(WebBrowserBase browser)
//	{
//		this.browser = browser;
//	}
//	public void setPostProcessor(WOAPostProcessURLInterface prostProcessor)
//	{
//		this.prostProcessor = prostProcessor;
//	}
	public void stop() {
		quit = true;
	}

	//没用了，依靠任务判断
//	public boolean isRunning() {
//		if (runningThread == 0)
//			return false;
//		else
//			return true;
//	}

//	public void checkpointIfNeeded() {
//		if (config.getCheckpointInterval() == 0) {
//			return;
//		}
//
//		if (System.currentTimeMillis() - lastCheckpoint > config
//				.getCheckpointInterval()) {
//			synchronized (queue) {
//				if (System.currentTimeMillis() - lastCheckpoint > config
//						.getCheckpointInterval()) {
//					writeCheckpoint();
//					lastCheckpoint = System.currentTimeMillis();
//				}
//			}
//		}
//	}

//	private void writeCheckpoint() {
//		_logClass.debug("writeCheckpoint()");
//		try {
//			FileOutputStream fos = new FileOutputStream("spider.checkpoint",
//					false);
//			ObjectOutputStream oos = new ObjectOutputStream(fos);
//			oos.writeObject(queue);
//			oos.writeObject(urlsDownloading);
//			oos.close();
//		} catch (IOException ioe) {
//			_logClass.warn(
//					"IO Exception attempting checkpoint: " + ioe.getMessage(),
//					ioe);
//		}
//	}

//	public void readCheckpoint() {
//		try {
//			FileInputStream fis = new FileInputStream("spider.checkpoint");
//			ObjectInputStream ois = new ObjectInputStream(fis);
//			queue = (DownloadQueue) ois.readObject();
//			urlsDownloading = (Set) ois.readObject();
//			queue.queueURLs(urlsDownloading);
//			urlsDownloading.clear();
//		} catch (Exception e) {
//			_logClass
//					.error("Caught exception reading checkpoint: "
//							+ e.getMessage(), e);
//		}
//	}

//	public int stopCount = 0;
	

	/**
	 * Get the size of the download queue in a thread-safe manner.
	 */
	public int queueSize() {
		synchronized (queue) {
			return queue.size();
		}
	}
	//所有线程都停止，而且队列是空的
	public boolean isEnd()
	{
//		int left = this.woa.totalUrl -  this.woa.crawledUrls;
		
//		int left = this.woa.totalUrl -  this.woa.currentUrl;
//		if(left <= 0)
//			return true;
//		else
//			return false;
		logger.debug("alivethread=" + this.aliveThreads + " queueSize=" + this.queueSize());
		if(this.aliveThreads == 0 && this.queue.isEmpty())
			return true;
		else
			return false;
	}
	public List<URL> filterCrawled(List<String> urls)
	{
		List<URL> list = new ArrayList<URL>();
		for (int i = 0; i < urls.size(); i++) {
			
			try {
				URL u = new URL(urls.get(i));
				list.add(u);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

		}
		List<URL> list2 = new ArrayList<URL>();
		synchronized(urlsDownloadedOrScheduled)
		{
			for(int i=0; i<list.size(); i++)
			{
//				logger.info(i);
				URL u = list.get(i);
				if(!urlsDownloadedOrScheduled.contains(u))
					list2.add(u);
			}
		}
		return list2;
	}
	
	
	public boolean isCrawled(URL u)
	{
		synchronized(urlsDownloadedOrScheduled)
		{
			return urlsDownloadedOrScheduled.contains(u);
		}
	}
}
