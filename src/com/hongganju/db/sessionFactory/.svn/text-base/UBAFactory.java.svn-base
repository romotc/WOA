package com.hongganju.db.sessionFactory;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

public class UBAFactory extends DBFactoryBase{
	public UBAFactory()
	{
		this.entityList = new ArrayList<String>();
		entityList.add("com/hongganju/db/entity/uba");
	}
	static UBAFactory _instance = null;
	static UBAFactory getInstance()
	{
		if(_instance == null)
			_instance = new UBAFactory();
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

