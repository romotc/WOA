package com.hongganju.woa.stat;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hongganju.db.entity.task.Woadomain;
import com.hongganju.db.entity.task.Woaurl;
import com.hongganju.db.sessionFactory.TaskFactory;

public class StatZhanglei {
	public static String getISP(String domain)
	{
		SessionFactory sessionFactory = TaskFactory.getSessionFactory((short)291);
		Session session = sessionFactory.openSession();
		String hqlTmp=" FROM Woaurl WHERE InstanceID =" + instanceId + " and domain = '" + domain + "'";//获取woatask任务id
		System.out.println(hqlTmp);
		Transaction t = session.beginTransaction();
		Query queryTmp = session.createQuery(hqlTmp);
		queryTmp.setMaxResults(1);
		List<Woaurl> listTmp = queryTmp.list();
		t.commit();
		session.close();
		if(listTmp.size() > 0)
			return listTmp.get(0).getServerIspid();
		return null;
	}
	
	public static void update(Woadomain domain)
	{
		System.out.println("update domain=" + domain.getDomain() + " isp=" + domain.getLocalIsp());
		SessionFactory sessionFactory = TaskFactory.getSessionFactory((short)291);
		Session session = sessionFactory.openSession();
        Transaction t = (Transaction) session.beginTransaction();
        String sql = "update woadomain set localIsp='" + domain.getLocalIsp() + "' where Id=" + domain.getId();
        SQLQuery query = session.createSQLQuery(sql);
        try {
        	System.out.println("update result=" + query.executeUpdate());
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
        /*
        try {
        	System.out.println("update " + domain.getId() + " " + domain.getInstanceId() + " " + domain.getLocalIsp());
            session.update(domain);
        } catch (HibernateException e) {
            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw e;
        }*/
		t.commit();
		session.close();
	}
	/**
	 * @param args
	 */
	public static String instanceId;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(args.length);
		if(args.length < 1)
		{
			System.out.println("请输入instanceId");
			return;
		}
		instanceId = args[0];
		SessionFactory sessionFactory = TaskFactory.getSessionFactory((short)291);
		Session session = sessionFactory.openSession();
		String hqlTmp=" FROM Woadomain WHERE InstanceID =" + instanceId + " and localisp is null";//获取woatask任务id
		System.out.println(hqlTmp);
		Transaction t = session.beginTransaction();
		Query queryTmp = session.createQuery(hqlTmp);
		List<Woadomain> listTmp = queryTmp.list();
		t.commit();
		session.close();
		
		
		for(int i=0; i<listTmp.size(); i++)
		{
			String domain = listTmp.get(i).getDomain();
			if(domain != null && !domain.trim().equals(""))
			{
				String isp = getISP(domain);
				Woadomain ddd = listTmp.get(i);
				ddd.setLocalIsp(isp);
				update(ddd);
			}
		}
	}

}
