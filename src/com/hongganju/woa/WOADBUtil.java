package com.hongganju.woa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hongganju.common.util.SpringUtil;
import com.hongganju.db.entity.center.Woainstance;
import com.hongganju.db.entity.center.Woatask;

public class WOADBUtil {
	public static Woatask getTaskById(Integer taskId)
	{
		SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from Woatask where taskid = " + taskId;
		Transaction t = session.beginTransaction();
		Query query = session.createQuery(hql);
		List<Woatask> list = query.list();
		t.commit();
		if(list.size() > 0)
			return list.get(0);
		else
			return null;
	}
	
	public static Woainstance getTaskInstanceById(Integer instanceId)
	{
		SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from Woainstance where taskinstanceId = " + instanceId;
		Transaction t = session.beginTransaction();
		Query query = session.createQuery(hql);
		List<Woainstance> list = query.list();
		t.commit();
		if(list.size() > 0)
			return list.get(0);
		else
			return null;
	}
}
