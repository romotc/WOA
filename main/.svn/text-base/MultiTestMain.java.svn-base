import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hongganju.common.util.Log4JIniter;
import com.hongganju.common.util.SpringUtil;
import com.hongganju.db.entity.center.Woacluster;
import com.hongganju.db.entity.center.Woatask;
import com.hongganju.node.NodeConfig;


public class MultiTestMain {
	private static Logger logger = Logger.getLogger(MultiTestMain.class);
	public static void main(String[] args) throws UnknownHostException
	{
//		args = new String[]{"2", "CN1100", "CN0002", "1"};
		if(args.length < 2)
		{
			System.out.println("请输入 类型（1-网页+断链， 2-视频（只支持windows））AreaId, ISPId， 浏览器类型（1-hbrowser(默认), 2-IE）");
			return;
		}
		Integer type = null;
		try{
			type = Integer.parseInt(args[0]);
		}catch(Exception e)
		{
			System.out.println("请输入 类型（1-网页+断链， 2-视频（只支持windows））AreaId, ISPId， 浏览器类型（1-hbrowser(默认), 2-IE）");
			return;
		}
		if(type != 1 && type != 2)
		{
			System.out.println("请输入 类型（1-网页+断链， 2-视频（只支持windows））AreaId, ISPId， 浏览器类型（1-hbrowser(默认), 2-IE）");
			return;
		}
		Log4JIniter.init("src");
		
		//初始化节点
		
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
		if(!System.getProperty("os.name").trim().toLowerCase().startsWith("win"))
		{
			if(type == 2)
			{
				System.out.println("Linux/Unix系统不支持视频爬虫，请在windows下执行");
				return;
			}
		}
//		String ss = "1";
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
		
		Integer clusterId = SingleTestMain.initCluser(area, isp);
		NodeConfig.setClusterId(clusterId);
		Integer instanceId = null;
		Integer taskId = null;
		Integer nodeId = SingleTestMain.initNode(clusterId, NodeConfig.getIp());
		List<Woatask> tasks = getAllTasks(type);
		for(int i=0; i<tasks.size(); i++)
		{
			taskId = tasks.get(i).getTaskid();
			try{
				SingleTestMain.runOne(taskId, instanceId, browser, null);
			}catch(Exception e)
			{
				e.printStackTrace();
				logger.error("出错，任务：" + taskId + "终止：" + e.getMessage());
			}
		}
		System.out.println("所有任务执行完毕");
		
	}
	private static List<Woatask> getAllTasks(Integer type)
	{
		SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from Woatask where status=0 and taskType=" + type;
		Transaction t = session.beginTransaction();
		Query query = session.createQuery(hql);
		List<Woatask> list = query.list();
		t.commit();
		return list;
	}
}
