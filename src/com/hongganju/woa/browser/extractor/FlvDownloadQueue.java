package com.hongganju.woa.browser.extractor;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hongganju.common.util.DBUtil;
import com.hongganju.db.entity.task.Woacrawledflvurl;
import com.hongganju.db.sessionFactory.TaskFactory;
import com.hongganju.woa.browser.DBSaver;
import com.hongganju.woa.spider.WOASpiderParam;

//下载的线程
public class FlvDownloadQueue extends Thread{
	private static Logger logger = Logger.getLogger(FlvDownloadQueue.class);
	private LinkedList<Woacrawledflvurl> list = new LinkedList<Woacrawledflvurl>();
	private LinkedList<WOASpiderParam> paramList = new LinkedList<WOASpiderParam>();
	static FlvDownloadQueue _instance;
	private boolean isRunning = false;
	static public FlvDownloadQueue getInstance()
	{
		if(_instance == null)
			_instance = new FlvDownloadQueue();
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
			System.out.println("download flvFile thread wakeup");
			while(!list.isEmpty() && this.isRunning == true)
			{
				long t1 = System.currentTimeMillis();
				Woacrawledflvurl flvUrl = list.pop();
				String url = flvUrl.getUrl();
				String refer = flvUrl.getRefer();
				WOASpiderParam param = paramList.pop();
				
				//下载
				FlvFileDownloader.download(url, flvUrl.getFlvurl(), refer, flvUrl.getFlvTitle(), param);
				
				try{
					DBUtil.update(flvUrl, param.dbid);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				//更新instance
				try{
					DBSaver.updateFlvInstance(param);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				param.currentFlvUrl++;
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
//	public void addUrls(List<Woacrawledflvurl> strs, WOASpiderParam woaparam)
//	{
//		if(strs == null || strs.size() == 0)
//			return;
//		synchronized(list)
//		{
//			for(int i=0; i<strs.size(); i++)
//			{
//				list.add(strs.get(i));
//			}
//			list.notify();
//		}
//	}
	public void addUrl(Woacrawledflvurl url, WOASpiderParam woaparam)
	{
		if(url == null)
			return;
		synchronized(list)
		{
			list.add(url);
			list.notify();
		}
	}
}

