package weblech.spider;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.hongganju.woa.browser.DBSaver;
import com.hongganju.woa.browser.WebBrowserBase;
import com.hongganju.woa.browser.ie.BrowseResult;
import com.hongganju.woa.factory.BrowserFactory;


//队列为空时就算结束
public class SpiderThread extends Thread{
	private static Logger logger = Logger.getLogger(SpiderThread.class); 
	// 线程号
	private int threadNo;
	private Spider spider;
	private boolean alive = false;
	public BrowseResult result;
	public SpiderThread(Spider spider, int threadNo) {
		this.spider = spider;
		this.threadNo = threadNo;
		this.setName("Spider-Thread-" + threadNo);
	}
	public int getThreadNo()
	{
		return this.threadNo;
	}
	
	//执行爬行算法
	//多线程并行
	public void run() {
//		HTMLParser htmlParser = new HTMLParser(config);
//		URLGetter urlGetter = new URLGetter(config);
		//爬行线程
		while (spider.quit == false) {
			try{
				mainLoop();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			if(spider.isEnd())
			{
				logger.debug("End task, taskid=" + this.spider.param.taskId + " instanceId=" + this.spider.param.taskInstanceId);
				spider.quit = true;
				break;
			}
		}

		synchronized(spider)
		{
			spider.runningThread--;
		}
		logger.debug(this.getName() + ":线程结束");
		System.out.println(this.getName() + ":线程结束");
	}
	
	private void mainLoop()
	{
		System.out.println(this.getName() + " status: queque1 size = " + spider.queue.size()
				+ ", aliveThreads=" + spider.aliveThreads + ", quit="
				+ spider.quit);
		
//		spider.checkpointIfNeeded();
//		spider.aliveThreads--;
		
		if(this.alive == true)
			spider.aliveThreads--;
		this.alive = false;
		
		//队列空了，但是还有任务没有下载完
		if (spider.queueSize() == 0 && spider.aliveThreads > 0) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException ignored) {
			}
			return;
		} else if (spider.queueSize() == 0) {
			return;
		}
		
		if(this.alive == false)
			spider.aliveThreads++;
		this.alive = true;

		URLToDownload nextURL;
		
		// 队列中取出下一个
		synchronized (spider.queue) {
			nextURL = spider.queue.getNextInQueue();
			if (nextURL == null)
				return;
		}
		
		//把爬取到的url记录到db里（种子url不用记）
//		if (nextURL.getDepth() == 0) {
//			URLToDownload todownload = new URLToDownload(nextURL.getURL(),
//					null, 0);
//			// 记录新的节点
//			DBSaver.saveInDB(todownload, this.spider.woa.param);
//		}
//		synchronized (spider.urlsDownloading) {
//			spider.urlsDownloading.add(nextURL);
//		}
		
		//处理单个url
		processNextURL(nextURL);
	}
	
	public void processNextURL(URLToDownload nextURL)
	{
		System.out.println("WOA Spider process: " + nextURL.getURL());
		logger.info("WOA Spider process: " + nextURL.getURL());
		int newDepth = nextURL.getDepth() + 1;
		int currentDepth = nextURL.getDepth();
		//使用配置的下载深度
//		int maxDepth = config.getMaxDepth();
		int maxDepth = spider.param.getDepth();
		if(maxDepth < 0)
			maxDepth = 99999;
		
//		synchronized (spider.urlsDownloading) {
//			spider.urlsDownloading.remove(nextURL);
//		}
		//下载
//		List newURLs = downloadURL(nextURL, urlGetter, htmlParser);
		
		WebBrowserBase browser = BrowserFactory.createBrowser(spider.browserType, spider.config);
		browser.setWOA(spider.woa);
		
		
		//运行httpGet
		browser.httpGet(nextURL, this);
		List<URL> newURLs = browser.getUrls();
		logger.info("WOA Spider process end: " + nextURL.getURL() + " instance.currentUrl=" + this.spider.woa.currentUrl);
		DBSaver.updateWOAInstance(this.spider.woa);
		
		
		//把新的url写入db
		if(spider.isEnd())
			return;
		
		System.out.println(nextURL + " 执行完毕，新的url:" + newURLs.size() + "进入队列，等待执行");
		//放入队列准备执行
		ArrayList filteredUrls = new ArrayList();
		//放入过滤器
		synchronized (spider.urlsDownloadedOrScheduled) {
			List<URLToDownload> dbList = new ArrayList<URLToDownload>();
			for (Iterator i = newURLs.iterator(); i.hasNext();) {
				URL u = (URL) i.next();
				// Download if not yet downloaded, and the new depth is less
				// than the maximum
				if (maxDepth == 0 || newDepth <= maxDepth) {
					// 记录新的节点
					URLToDownload todownload = new URLToDownload(u, nextURL
						.getURL(), newDepth);
					filteredUrls.add(todownload);
					spider.urlsDownloadedOrScheduled.add(u);
				}
			}
		}
		
		System.out.println("queque size = " + spider.queue.size());
//		_logClass.debug(queue.size());
		//放入队列等待执行
		synchronized (spider.queue) {
			spider.queue.queueURLs(filteredUrls);
			DBSaver.saveCrawledUrlInDB(filteredUrls, this.spider.woa);
//			spider.downloadsInProgress--;
		}
//		_logClass.debug(queue.size());
		System.out.println("queque2 size = " + spider.queue.size());
	}
}
