import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import statistic.StaticWork;

import com.hongganju.common.util.DBUtil;
import com.hongganju.common.util.Log4JIniter;
import com.hongganju.common.util.SpringUtil;
import com.hongganju.db.dao.WoaclusterDao;
import com.hongganju.db.dao.WoainstanceDao;
import com.hongganju.db.entity.center.Woacluster;
import com.hongganju.db.entity.center.Woainstance;
import com.hongganju.db.entity.center.Woanode;
import com.hongganju.db.entity.center.Woatask;
import com.hongganju.node.NodeConfig;
import com.hongganju.woa.WOADBUtil;
import com.hongganju.woa.browser.ie.IEBrowser;
import com.hongganju.woa.factory.WOASpiderFactory;
import com.hongganju.woa.spider.WOASpider;
import com.hongganju.woa.spider.WOASpiderParam;

//验证cluster
public class SingleTestMain {
	private static Logger logger = Logger.getLogger(SingleTestMain.class);
	public static void main(String[] args) throws UnknownHostException {
		
		// TODO Auto-generated method stub
		System.out.println(args.length);
		if(args.length < 3)
		{
			System.out.println("请输入 taskId(数字)/要继续的instanceId（前缀i，如i2000）, AreaId, ISPId, 浏览器类型（1-hbrowser(默认), 2-IE）");
			return;
		}
		Log4JIniter.init("src");
		String ss = args[0];
		String area = args[1];
		String isp = args[2];
		Integer browser = 1;
		try{
			Integer b = Integer.parseInt(args[3]);
			if(b == 2)
				browser = 2;
		}catch(Exception e)
		{
			
		}
//		ss = "http://www.sina.com.cn";
//		String ss = "http://www.sina.com.cn";
//		String area = "CN1100";
//		String isp = "CN0002";
//		System.out.println(ss);
//		NodeConfig.setIsp("CN0002");
//		NodeConfig.setIp("119.57.54.29");
//		NodeConfig.setArea("CN1100");
//		NodeConfig.setClusterId(2);
		NodeConfig.setIsp(isp);
		NodeConfig.setIp(InetAddress.getLocalHost().getHostAddress());
		NodeConfig.setArea(area);
		
		Integer clusterId = initCluser(area, isp);
		NodeConfig.setClusterId(clusterId);
		Integer instanceId = null;
		Integer taskId = null;
		try{
			if(ss.startsWith("i"))
			{
				String s1 = ss.substring(1);
				System.out.println(s1);
				instanceId = Integer.parseInt(s1);
			}else
			{
				taskId = Integer.parseInt(ss);
			}
			
		}catch(Exception e)
		{
			System.out.println("请输入 taskId(数字)");
			return;
		}
		Integer nodeId = initNode(clusterId, NodeConfig.getIp());
		runOne(taskId, instanceId, browser, null);
	}
	
	public static Integer initNode(Integer clusterId, String ip) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from Woanode where ip='" + ip + "' and clusterId=" + clusterId;
		Transaction t = session.beginTransaction();
		Query query = session.createQuery(hql);
		List<Woanode> list = query.list();
		t.commit();
		if(list.size() > 0)
			return list.get(0).getId();
		else
		{
			Woanode node = new Woanode();
			node.setIp(ip);
			node.setLanIp(ip);
			node.setClusterId(clusterId);
			DBUtil.save(node, sessionFactory);
			return node.getId();
		}
	}

	public static Integer initCluser(String area, String isp) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from Woacluster where areaId='" + area + "' and ispid='" + isp + "'";
		Transaction t = session.beginTransaction();
		Query query = session.createQuery(hql);
		List<Woacluster> list = query.list();
		t.commit();
		if(list.size() > 0)
			return list.get(0).getClusterId();
		else
		{
			Woacluster cluster = new Woacluster();
			cluster.setAreaId(area);
			cluster.setIspid(isp);
			WoaclusterDao dao = new WoaclusterDao();
			dao.save(cluster);
			return cluster.getClusterId();
		}
	}

	public static void runOne(Integer taskId, Integer instanceId, Integer browser, Integer batTaskId)
	{
		Woainstance instance;
		WoainstanceDao dao = new WoainstanceDao();
		Woatask task;
		if(taskId != null)
		{
			task = WOADBUtil.getTaskById(taskId);
			instance = new Woainstance();
			instance.setStartTime(new Date());
			instance.setStatus(1);
			instance.setTaskid(taskId);
			instance.setUnitId(1);
			instance.setCurrentCrawledUrl(0);
			instance.setCurrentFlvUrl(0);
			instance.setBatTaskId(batTaskId);
			dao.save(instance);
		}
		else
		{
			instance = WOADBUtil.getTaskInstanceById(taskId);
			task = WOADBUtil.getTaskById(instance.getTaskid());
		}
		
		if(task.getTaskType() != null && task.getTaskType() == 2)
		{
			if(!System.getProperty("os.name").trim().toLowerCase().startsWith("win"))
			{
				System.out.println("Linux/Unix系统不支持视频爬虫，请在windows下执行");
				return;
			}
		}
		
		WOASpiderParam param = new WOASpiderParam();
		if(task.getSeedUrl().startsWith("http"))
			param.setSeed(task.getSeedUrl());
		else
			param.setSeed("http://" + task.getSeedUrl());
		IEBrowser.htmlBase = param.getSeed();
		IEBrowser.number = 1;
		if(task.getIsDownloadObject() == null || task.getIsDownloadObject() == true)
			param.isDownloadObject = true;
		else
			param.isDownloadObject = false;
		if(task.getExtractUrl() != null && !task.getExtractUrl().trim().equals(""))
			param.setFlvPrefix(task.getExtractUrl());
		if(task.getMaxDownloadFlvUrl() != null && task.getMaxDownloadFlvUrl() > 0)
			param.maxDownloadFlvUrl = task.getMaxDownloadFlvUrl();
		param.setDomains(task.getDomainkey());
		param.taskId = task.getTaskid();
		param.taskInstanceId = instance.getTaskinstanceId();
		param.setTotalUrls(task.getTotalUrls());
		param.setDepth(task.getDepth());
		param.instance = instance;
		param.dbid = task.getDbid();
		Float maxDownloadKB = task.getMaxDownloadKB();
		if(maxDownloadKB != null || maxDownloadKB < 5000)
		{
			param.maxDownloadBytes = 5 * 1024 * 1024;
		}
		else
			param.maxDownloadBytes = maxDownloadKB;
//		FlvExtractor.setMaxDownloadSize((long)(param.maxDownloadBytes * 1024));
		
		Integer taskType = task.getTaskType();
		if(taskType == null)
			taskType = 1;
		param.taskType = taskType;
		System.out.println("ready");
		WOASpider spider = null;
		if(browser == null || browser == 1)
		{
			spider = WOASpiderFactory.createHnoJsSpider(param);
		}else
		{
			spider = WOASpiderFactory.createIESpider(param);
		}

		
		System.out.println("开始执行:" + task.getSeedUrl() + ", name=" + task.getWebName());
		logger.info("开始执行:" + task.getSeedUrl() + ", name=" + task.getWebName());
		spider.start();
		System.out.println("执行完毕");
		logger.info("执行完:" + task.getSeedUrl() + ", name=" + task.getWebName());
		instance.setEndTime(new Date());
		instance.setStatus(3);
		if(task.getTaskType() == 2)
		{
			if(instance.getCurrentFlvUrl() < 10)
			{
				instance.setStatus(4);
			}
		}else if(task.getTaskType() == 1)
		{
			if(instance.getCurrentCrawledUrl() < 10)
			{
				instance.setStatus(4);
			}
		}
		
		dao.update(instance);
		
		//资源统计
		logger.info("郭辉统计开始");
		StaticWork workrun = new StaticWork();
		//workrun.workRun(task,instance);
		logger.info("郭辉统计结束");
	}
}
