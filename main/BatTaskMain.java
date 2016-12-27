import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hongganju.common.util.Log4JIniter;
import com.hongganju.common.util.SpringUtil;
import com.hongganju.db.entity.center.WOATaskBatTask;
import com.hongganju.db.entity.center.Woatask;
import com.hongganju.node.NodeConfig;


public class BatTaskMain {
	private static Logger logger = Logger.getLogger(BatTaskMain.class);
	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		System.out.println(args.length);
		if(args.length < 3)
		{
			System.out.println("请输入  批量任务号, AreaId, ISPId, 浏览器类型（1-hbrowser(默认), 2-IE）");
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
		
		Integer batId = Integer.parseInt(ss);
		List<Integer> taskIds = getTaskIdByBatid(batId);
		if(taskIds.size() == 0)
		{
			System.out.println("没有对应的任务号，请重新输入");
			return;
		}
		NodeConfig.setIsp(isp);
		NodeConfig.setIp(InetAddress.getLocalHost().getHostAddress());
		NodeConfig.setArea(area);
		
		Integer clusterId = SingleTestMain.initCluser(area, isp);
		NodeConfig.setClusterId(clusterId);
		Integer instanceId = null;
		Integer taskId = null;
		Integer nodeId = SingleTestMain.initNode(clusterId, NodeConfig.getIp());
		List<Woatask> tasks = getAllTasks(taskIds);
		for(int i=0; i<tasks.size(); i++)
		{
			taskId = tasks.get(i).getTaskid();
			try{
				SingleTestMain.runOne(taskId, instanceId, browser, batId);
			}catch(Exception e)
			{
				e.printStackTrace();
				logger.error("出错，任务：" + taskId + "终止：" + e.getMessage());
			}
		}
		System.out.println("所有任务执行完毕");
	}
	private static List<Woatask> getAllTasks(List<Integer> taskIds) {
		// TODO Auto-generated method stub
		List<Woatask> tasks = new ArrayList<Woatask>();
		for(int i=0; i<taskIds.size(); i++)
		{
			Woatask task = getTaskById(taskIds.get(i));
			if(task != null)
				tasks.add(task);
		}
		return tasks;
	}
	private static Woatask getTaskById(Integer taskid)
	{
		SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from Woatask where taskid=" + taskid;
		Transaction t = session.beginTransaction();
		Query query = session.createQuery(hql);
		List<Woatask> list = query.list();
		t.commit();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	static public List<Integer> getTaskIdByBatid(int batId)
	{
		SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from WOATaskBatTask where batTaskId=" + batId;
//		hql = "from WOATaskBatTask";
		Transaction t = session.beginTransaction();
		Query query = session.createQuery(hql);
		List<WOATaskBatTask> list = query.list();
		t.commit();
		
		List<Integer> a = new ArrayList<Integer>();
		for(int i=0; i<list.size(); i++)
		{
			WOATaskBatTask tt = list.get(i);
			a.add(tt.getTaskId());
		}
		
		return a;
	}
}
