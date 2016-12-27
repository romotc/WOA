package com.hongganju.db.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import com.hongganju.db.DAOBase;
import com.hongganju.db.Constant;
import org.springframework.stereotype.Service;
import com.hongganju.db.entity.center.Woacluster;


/**
 * A data access object (DAO) providing persistence and search support for
 * Woacluster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gwi.ncms.persistence.Woacluster
 * @author MyEclipse Persistence Tools
 */
@Service("WoaclusterDao")
public class WoaclusterDao extends DAOBase {
	private static final Log log = LogFactory.getLog(WoaclusterDao.class);
	// property constants


	 public void save(Woacluster Woacluster) throws HibernateException {
	        Session session = this.getSession();
	        Transaction t = (Transaction) session.beginTransaction();
	        try {
	            session.save(Woacluster);
	        } catch (HibernateException e) {
	            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            throw e;
	        }
			t.commit();
	    }


	    public void delete(Woacluster Woacluster) throws HibernateException {
	        Session session = this.getSession();
	        Transaction t = (Transaction) session.beginTransaction();
	        try {
	            session.delete(Woacluster);
	        } catch (HibernateException e) {
	            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            throw e;
	        }
	        t.commit();
	    }

	    public void update(Woacluster Woacluster) throws HibernateException {
	        Session session = this.getSession();
	        Transaction t = (Transaction) session.beginTransaction();
	        try {
	            session.saveOrUpdate(Woacluster);
	        } catch (HibernateException e) {
	            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            throw e;
	        }
	        t.commit();
	    }

	    public Woacluster getWoaclusterByID(Integer id) throws HibernateException {
	        Session session = this.getSession();
	        Transaction t = (Transaction) session.beginTransaction();
	        Woacluster Woacluster = null;
	        try {
	        	Woacluster = (Woacluster) session.get(Woacluster.class, id);
	        } catch (HibernateException e) {
	            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            throw e;
	        }
	        t.commit();
	        return Woacluster;
	    }
		public List findByProperty(String propertyName, Object value) {
			log.debug("finding Woacluster instance with property: " + propertyName
					+ ", value: " + value);
			try {
				String queryString = "from Woacluster as model where model."
						+ propertyName + "= ?";
				Query queryObject = getSession().createQuery(queryString);
				queryObject.setParameter(0, value);
				return queryObject.list();
			} catch (RuntimeException re) {
				log.error("find by property name failed", re);
				throw re;
			}
		}
	   
}