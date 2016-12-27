package com.hongganju.db.sessionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.hongganju.common.util.SpringUtil;
import com.hongganju.db.dao.DBManagerDao;
import com.hongganju.db.entity.center.Dbconfig;
import com.hongganju.db.entity.center.Dbmanager;


public class PeerFactory extends DBFactoryBase{
	public PeerFactory()
	{
		this.entityList = new ArrayList<String>();
		entityList.add("com/hongganju/db/entity/peer");
	}
	static PeerFactory _instance = null;
	static PeerFactory getInstance()
	{
		if(_instance == null)
			_instance = new PeerFactory();
		return _instance;
	}

	public static synchronized SessionFactory getSessionFactory(Short dbid)
	{
		return getInstance().getSessionFactoryImpl(dbid, null, null);
	}
	
	public static synchronized SessionFactory getSessionFactory(Short dbid, Integer max, Integer min)
	{
		return getInstance().getSessionFactoryImpl(dbid, max, min);
	}
	
}
