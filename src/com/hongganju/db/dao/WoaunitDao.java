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
import com.hongganju.db.entity.center.Woaunit;


/**
 * A data access object (DAO) providing persistence and search support for
 * Woaunit entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gwi.ncms.persistence.Woaunit
 * @author MyEclipse Persistence Tools
 */
@Service("WoaunitDao")
public class WoaunitDao extends DAOBase {
	private static final Log log = LogFactory.getLog(WoaunitDao.class);
	// property constants


	 public void save(Woaunit Woaunit) throws HibernateException {
	        Session session = this.getSession();
	        Transaction t = (Transaction) session.beginTransaction();
	        try {
	            session.save(Woaunit);
	        } catch (HibernateException e) {
	            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            throw e;
	        }
			t.commit();
	    }


	    public void delete(Woaunit Woaunit) throws HibernateException {
	        Session session = this.getSession();
	        Transaction t = (Transaction) session.beginTransaction();
	        try {
	            session.delete(Woaunit);
	        } catch (HibernateException e) {
	            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            throw e;
	        }
	        t.commit();
	    }

	    public void update(Woaunit Woaunit) throws HibernateException {
	        Session session = this.getSession();
	        Transaction t = (Transaction) session.beginTransaction();
	        try {
	            session.saveOrUpdate(Woaunit);
	        } catch (HibernateException e) {
	            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            throw e;
	        }
	        t.commit();
	    }

	    public Woaunit getWoaunitByID(Integer id) throws HibernateException {
	        Session session = this.getSession();
	        Transaction t = (Transaction) session.beginTransaction();
	        Woaunit Woaunit = null;
	        try {
	        	Woaunit = (Woaunit) session.get(Woaunit.class, id);
	        } catch (HibernateException e) {
	            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            throw e;
	        }
	        t.commit();
	        return Woaunit;
	    }
		public List findByProperty(String propertyName, Object value) {
			log.debug("finding Woaunit instance with property: " + propertyName
					+ ", value: " + value);
			try {
				String queryString = "from Woaunit as model where model."
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