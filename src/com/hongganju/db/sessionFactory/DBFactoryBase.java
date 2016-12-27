package com.hongganju.db.sessionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.hongganju.common.util.SpringUtil;
import com.hongganju.common.util.hibernate.HibernateDBInfo;
import com.hongganju.common.util.hibernate.HibernateDBInfoManager;
import com.hongganju.db.dao.DBManagerDao;
import com.hongganju.db.entity.center.Dbconfig;
import com.hongganju.db.entity.center.Dbmanager;

public class DBFactoryBase {
	protected static Logger logger = Logger.getLogger(DBFactoryBase.class);
	protected HashMap<Short, SessionFactory> map = new
		HashMap<Short, SessionFactory>();
	static protected HashMap<Short, LocalSessionFactoryBean> map2 = new 
		HashMap<Short, LocalSessionFactoryBean>();
	//过滤器，用来确认dburl-username是否被加载过
	static protected HashMap<String, HibernatePair> dbfilter = new HashMap<String, HibernatePair>();
	protected static HashMap<String, HibernateDBInfo> dbInfoFilter = new HashMap<String, HibernateDBInfo>();
	//Entity映射类，在实例化时被加载
	protected List<String> entityList = new ArrayList<String>();

	protected LocalSessionFactoryBean getSpringSessionFactoryBeanImpl(Short dbid)
	{
		Object obj = SpringHibernateUtil.getSynObj();
		SessionFactory sessionFactory = null;
		synchronized(obj)
		{
			if(map2.containsKey(dbid))
				return map2.get(dbid);
		}
		return null;
	}
	
	

	//加静态锁
	protected SessionFactory getSessionFactoryImpl(Short dbid, Integer max, Integer min)
	{
		Object obj = SpringHibernateUtil.getSynObj();
		SessionFactory sessionFactory = null;
		synchronized(obj)
		{
			sessionFactory = createSessionFactoryImpl(dbid, null, null);
		}
		return sessionFactory;
		
	}
	
	protected SessionFactory createSessionFactoryImpl(Short dbid, Integer max, Integer min)
	{
		if(map.containsKey(dbid))
			return map.get(dbid);

		//build new sessionFactory
		DBManagerDao dao = new DBManagerDao();
		if(dbid == null || dbid == -1)
			logger.debug("DBID:" + dbid + ",不存在，数据库有误");
		Dbmanager manager = dao.getDbmanagerByID(dbid);
		if(manager == null)
			return null;
		String db_url = manager.getUrl();
		String password = manager.getPassword();
		String username = manager.getUsername();
		
		//直接从缓存中取
		String db_identifier = db_url + "-" + username;
		HibernatePair pair = null;
		//TaskDB直接写sql语句使用
		HibernateDBInfo dbInfo = null;
		
		pair = dbfilter.get(db_identifier);
		dbInfo = dbInfoFilter.get(db_identifier);
		
		//需要生成一个sessionFactory(DB连接池)
		if(pair == null)
		{
			Dbconfig config = SpringHibernateUtil.getDBConfig(db_url, username);
			if(max == null)
			{
				if(config == null || config.getMaxConnection() == null)
					max = manager.getMaxConnection();
				else
					max = config.getMaxConnection();
				
			}
	
			if(min == null)
			{
				if(config == null || config.getMinConnection() == null)
					min = manager.getMinConnection();
				else
					min = config.getMinConnection();
			}
			//创建sessionFactory
			try{
				pair = SpringHibernateUtil.createSessionFactroy(
					db_url, username, password, max, min, entityList);
				//创建HibernateInfo
				dbInfo = HibernateDBInfo.createHibernateDBInfo(pair.sessionFactoryBean);
			}catch(Exception e)
			{
				e.printStackTrace();
			}

			if(pair == null || dbInfo == null)
				return null;
			
			dbfilter.put(db_identifier,pair);
			dbInfoFilter.put(db_identifier, dbInfo);
		}
		
		
		//写入到Map
		map.put(dbid, pair.sessionFactory);
		map2.put(dbid, pair.sessionFactoryBean);
		
		
		//写入到HibernateDBManager
		HibernateDBInfoManager.addDBInfo(dbid, dbInfo);
		
		//resotre data
//		config.setProperty("hibernate.connection.url", connection_url);
//		config.setProperty("hibernate.connection.password", "");
//		config.setProperty("hibernate.connection.user", "");
		
		return pair.sessionFactory;
	}

}
