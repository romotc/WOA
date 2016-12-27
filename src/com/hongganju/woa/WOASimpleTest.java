package com.hongganju.woa;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hongganju.common.Path;
import com.hongganju.common.util.Log4JIniter;
import com.hongganju.common.util.SpringUtil;
import com.hongganju.db.dao.WoainstanceDao;
import com.hongganju.db.entity.center.Woainstance;
import com.hongganju.db.entity.center.Woatask;
import com.hongganju.node.NodeConfig;
import com.hongganju.woa.factory.WOASpiderFactory;
import com.hongganju.woa.spider.WOASpider;
import com.hongganju.woa.spider.WOASpiderParam;

//单机版本
public class WOASimpleTest {
	public static void runTest()
	{
		new Thread(){
			public void run(){
				test();
			}
		}.start();
	}
	public static void test()
	{
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Test start");
		Log4JIniter.init(Path.getPath() + "/WEB-INF/classes/log4j.properties");
		// TODO Auto-generated method stub
		NodeConfig.setIsp("CN0002");
		NodeConfig.setIp("119.57.54.29");
		NodeConfig.setArea("CN1100");
		NodeConfig.setClusterId(2);
		List<Woatask> tasks = getTasks();
		for(int i=0; i<tasks.size(); i++)
		{
			runOne(tasks.get(i));
		}
	}
	public static void runOne(Woatask task)
	{
		Woainstance instance = new Woainstance();
		instance.setStartTime(new Date());
		instance.setStatus(1);
		instance.setTaskid(task.getTaskid());
		instance.setUnitId(1);
		
		WoainstanceDao dao = new WoainstanceDao();
		dao.save(instance);
		
		WOASpiderParam param = createParam();
		param.setSeed(task.getSeedUrl());
		param.setDomains(task.getDomainkey());
		param.taskId = task.getTaskid();
		param.taskInstanceId = instance.getTaskinstanceId();
		param.setTotalUrls(task.getTotalUrls());
		
		System.out.println("ready");
		final WOASpider spider = WOASpiderFactory.createHSpider(param);
		System.out.println("开始执行:" + task.getSeedUrl() + ", name=" + task.getWebName());
		spider.start();
		System.out.println("执行完毕");
		
		instance.setEndTime(new Date());
		instance.setStatus(3);
		dao.update(instance);
		
	}
	public static List<Woatask> getTasks()
	{
		SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from Woatask where taskid > 9";
		Transaction t = session.beginTransaction();
		Query query = session.createQuery(hql);
		List<Woatask> list = query.list();
		t.commit();
		return list;
	}
	public static WOASpiderParam createParam()
	{
		// TODO Auto-generated method stub
		WOASpiderParam param = new WOASpiderParam();
		param.setDepth(3);
//		param.setSeed("www.zol.com.cn");
//		param.setDomain("zol.com.cn");
		param.setSeed("www.sina.com.cn");
		param.setDomains("sina");
		// param.setFilterKeys("zol");
		param.setTotalUrls(3000);
		param.taskId = 1;
		param.dbid = (short) 41;
		param.taskInstanceId = 1;
		param.customerId = 13;
		System.out.println("ready");
		return param;
	}
}
