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
import com.hongganju.db.entity.center.Dbmanager;


/**
 * A data access object (DAO) providing persistence and search support for
 * Dbmanager entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gwi.ncms.persistence.Dbmanager
 * @author MyEclipse Persistence Tools
 */
@Service("DBManagerDao")
public class DBManagerDao extends DAOBase {
	private static final Log log = LogFactory.getLog(DBManagerDao.class);
	// property constants


	 public void save(Dbmanager dbmanager) throws HibernateException {
	        Session session = this.getSession();
	        Transaction t = (Transaction) session.beginTransaction();
	        try {
	            session.save(dbmanager);
	        } catch (HibernateException e) {
	            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            throw e;
	        }
			t.commit();
	    }


	    public void delete(Dbmanager dbmanager) throws HibernateException {
	        Session session = this.getSession();
	        Transaction t = (Transaction) session.beginTransaction();
	        try {
	            session.delete(dbmanager);
	        } catch (HibernateException e) {
	            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            throw e;
	        }
	        t.commit();
	    }

	    public void update(Dbmanager dbmanager) throws HibernateException {
	        Session session = this.getSession();
	        Transaction t = (Transaction) session.beginTransaction();
	        try {
	            session.saveOrUpdate(dbmanager);
	        } catch (HibernateException e) {
	            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            throw e;
	        }
	        t.commit();
	    }

	    public Dbmanager getDbmanagerByID(Short id) throws HibernateException {
	        Session session = this.getSession();
	        Transaction t = (Transaction) session.beginTransaction();
	        Dbmanager dbmanager = null;
	        try {
	        	dbmanager = (Dbmanager) session.get(Dbmanager.class, id);
	        } catch (HibernateException e) {
	            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            throw e;
	        }
	        t.commit();
	        return dbmanager;
	    }
		public List findByProperty(String propertyName, Object value) {
			log.debug("finding Dbmanager instance with property: " + propertyName
					+ ", value: " + value);
			try {
				String queryString = "from Dbmanager as model where model."
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