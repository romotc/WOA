package com.hongganju.db.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.hongganju.db.DAOBase;
import com.hongganju.db.entity.center.Dbmanagerread;

@Service("DBManagerreadDao")
public class DBManagerreadDao extends DAOBase {
	private static final Log log = LogFactory.getLog(DBManagerreadDao.class);
	// property constants


	 public void save(Dbmanagerread dbmanagerread) throws HibernateException {
	        Session session = this.getSession();
	        Transaction t = (Transaction) session.beginTransaction();
	        try {
	            session.save(dbmanagerread);
	        } catch (HibernateException e) {
	            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            throw e;
	        }
			t.commit();
	    }


	    public void delete(Dbmanagerread dbmanagerread) throws HibernateException {
	        Session session = this.getSession();
	        Transaction t = (Transaction) session.beginTransaction();
	        try {
	            session.delete(dbmanagerread);
	        } catch (HibernateException e) {
	            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            throw e;
	        }
	        t.commit();
	    }

	    public void update(Dbmanagerread dbmanagerread) throws HibernateException {
	        Session session = this.getSession();
	        Transaction t = (Transaction) session.beginTransaction();
	        try {
	            session.saveOrUpdate(dbmanagerread);
	        } catch (HibernateException e) {
	            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            throw e;
	        }
	        t.commit();
	    }

	    public Dbmanagerread getDbmanagerByID(Short id) throws HibernateException {
	        Session session = this.getSession();
	        Transaction t = (Transaction) session.beginTransaction();
	        Dbmanagerread dbmanagerread = null;
	        try {
	        	dbmanagerread = (Dbmanagerread) session.get(Dbmanagerread.class, id);
	        } catch (HibernateException e) {
	            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	            throw e;
	        }
	        t.commit();
	        return dbmanagerread;
	    }
		public List findByProperty(String propertyName, Object value) {
			log.debug("finding Dbmanagerread instance with property: " + propertyName
					+ ", value: " + value);
			try {
				String queryString = "from Dbmanagerread as model where model."
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