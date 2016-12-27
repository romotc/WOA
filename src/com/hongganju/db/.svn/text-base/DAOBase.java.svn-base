package com.hongganju.db;

import org.apache.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongganju.common.util.SpringUtil;

@Repository
public class DAOBase {
	public static final Logger logger = Logger.getLogger(DAOBase.class);

    public Session getSession(){
    	if(_sessionFactory == null)
    		_sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
    	//return _sessionFactory.openSession();
    	return _sessionFactory.getCurrentSession();
    }

    private static final Logger _log = Logger.getLogger(DAOBase.class);

    //private SessionFactory _sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
    protected SessionFactory _sessionFactory;
    public SessionFactory getSessionFactory() {
        return _sessionFactory;
    }

	public void setSessionFactory(SessionFactory sessionFactory) {
		this._sessionFactory = sessionFactory;
	}
}
