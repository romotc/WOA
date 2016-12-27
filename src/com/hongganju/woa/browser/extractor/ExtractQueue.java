package com.hongganju.woa.browser.extractor;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.flv.weitang.WeitangRobot;
import com.hongganju.db.entity.task.Woacrawledflvurl;
import com.hongganju.db.sessionFactory.TaskFactory;
import com.hongganju.woa.spider.WOAConfig;
import com.hongganju.woa.spider.WOASpiderParam;
import com.hongganju.woa.util.FlvUrlExtractor;

//这是单例
//抽取视频url的方法（有些页面抽不出来就算了）
//处理模式：
//新的原始url先进入这个队列
//然后下载视频，更新内存中的Woacrawledflvurl，放入下载队列
//在下载队列中更新Woacrawledflvurl状态和写Woaflvurl表
public class ExtractQueue extends Thread{
	private static Logger logger = Logger.getLogger(ExtractQueue.class); 
//	private LinkedList<String> urlList = new LinkedList<String>();
//	private LinkedList<String> referList = new LinkedList<String>();
	private LinkedList<Woacrawledflvurl> list = new LinkedList<Woacrawledflvurl>();
	private LinkedList<WOASpiderParam> paramList = new LinkedList<WOASpiderParam>();
	static ExtractQueue _instance;
	private boolean isRunning = false;
	private int count = 0;
//	public void setWOAParam(WOASpiderParam woaparam)
//	{
//		param = woaparam;
//	}
	static public ExtractQueue getInstance()
	{
		if(_instance == null)
			_instance = new ExtractQueue();
		return _instance;
	}
	public void startThread()
	{
		if(this.isRunning == false)
		{
			this.isRunning = true;
			this.start();
		}
	}
	public void run()
	{
//		this.isRunning = true;
		while(this.isRunning)
		{
			synchronized(list)
			{
				try {
					list.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
			System.out.println("extract trueFlv thread wakeup");
			while(!list.isEmpty() && this.isRunning == true)
			{
				long t1 = System.currentTimeMillis();
				
				Woacrawledflvurl flvUrl = list.pop();
				WOASpiderParam param = paramList.pop();
				
				String url = flvUrl.getUrl();
				String refer = flvUrl.getRefer();
				
				try{
					//分析目标视频url
					FlvResult result = analysis(url, refer, param);
					flvUrl.setFlvTitle(result.title);
					flvUrl.setFlvurl(result.flvUrl);
					//交给FlvDownloadQueue队列
					FlvDownloadQueue.getInstance().addUrl(flvUrl, param);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				long t2 = System.currentTimeMillis();
				long sss = (t2 - t1)/1000;
				System.out.println("url=" + flvUrl.getUrlNo() + ", " + flvUrl.getUrl() + "处理完毕，队列还有：" + list.size() + "个");
			}
			System.out.println("flv队列为空");
		}
	}
	
	public void stopThread()
	{
		this.isRunning = false;
	}
	
	//保存起来
//	private void saveUrl(List<String> strs, String refer)
//	{
//		for(int i=0; i<strs.size(); i++)
//		{
//			this.param.crawledFlvUrls++;
//			this.param.currentFlvUrl++;
//			this.param.crawledFlvUrlsCounter++;
//		}
//	}
	
	// 先用dll抽取url，然后下载，最后保存
	private FlvResult analysis(String url, String refer, WOASpiderParam param) {
		// TODO Auto-generated method stub
		//过滤由Param在抽取完后执行
		FlvResult result = new FlvResult();
		// 开始处理
		logger.info("开始抽取流媒体下载链接: " + url);
		long t1 = System.currentTimeMillis();
//		String flvUrl = FlvUrlExtractor.getTrueUrl(url);
		Integer useWeitangRobot = WOAConfig.config.getUseWeitangRobot();
		
		if(useWeitangRobot == 0)
		{
			FlvUrlExtractor ext = new FlvUrlExtractor();
			ext.analysis(url);
			result.flvUrl = ext.getTrueUrl();
			result.title =ext.getTitle();
		}
		else
		{
			String weitangPath = WOAConfig.config.getWeitangPath();
			WeitangRobot wtRobot = new WeitangRobot();
			// String h = tc.getVideoString(TestControl.ViDownUri,
			// TestControl.videoUri);
			wtRobot.getVideoString(weitangPath, url);
			result.flvUrl = wtRobot.getUri();
			result.title =wtRobot.getVideoTitle();
		}
		
		long t2 = System.currentTimeMillis();
		logger.info("流媒体下载链接抽取结束: " + url + " 用时：" + (t2-t1)/1000 + "秒");
		return result;
	}

	
	//保存起来，外围被synchronized过了
	private void saveCrawledUrl(List<Woacrawledflvurl> urls, WOASpiderParam woaparam)
	{
//		List<Woacrawledflvurl> flvUrls = new ArrayList<Woacrawledflvurl>();
		int size = urls.size();
		woaparam.crawledFlvUrls += size;
//		this.param.currentFlvUrl += size;
		synchronized(woaparam.flvNumSynObj)
		{
			for(int i=0; i<size; i++)
			{
				Woacrawledflvurl u = urls.get(i);
				u.setInstanceId(woaparam.taskInstanceId);
				u.setTaskid(woaparam.taskId);
				u.setRefer(u.getRefer());
				u.setUploadTime(new Date());
				u.setUrl(u.getUrl());
				u.setStatus(0);
				u.setUrlNo(woaparam.getCrawledFlvUrlId());
			}
		}
		try{
//			DBUtil.saveJDBCList(urls, this.param.dbid);
			SessionFactory sf = TaskFactory.getSessionFactory(woaparam.dbid);
			Session session = sf.getCurrentSession();
	        Transaction t = (Transaction) session.beginTransaction();
	        for(int i=0; i<urls.size(); i++)
	        try {
	            session.save(urls.get(i));
	        } catch (HibernateException e) {
	            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            throw e;
	        }
	        t.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//初始化队列
	public void initUrls(List<Woacrawledflvurl> strs)
	{
		if(strs == null || strs.size() == 0)
			return;
		System.out.println("新增未下载的url：" + strs.size() + "个");
		synchronized(list){
			for(int i=0; i<strs.size(); i++)
			{
				list.add(strs.get(i));
			}
			//唤醒执行线程
			list.notify();
		}
	}
	public void addUrls(List<Woacrawledflvurl> strs, WOASpiderParam woaparam)
	{
		if(strs == null || strs.size() == 0)
			return;
		synchronized(list)
		{
			//保存得到的链接
			saveCrawledUrl(strs, woaparam);
			for(int i=0; i<strs.size(); i++)
			{
				list.add(strs.get(i));
				paramList.add(woaparam);
			}
			list.notify();
		}
	}
//	public void addUrls(List<String> strs, String refer)
//	{
//		if(strs == null || strs.size() == 0)
//			return;
//		synchronized(list)
//		{
//			saveUrl(strs, refer);
//			
//			for(int i=0; i<strs.size(); i++)
//			{
//				urlList.add(strs.get(i));
//				referList.add(refer);
//			}
//			urlList.notify();
//		}
//	}
}
