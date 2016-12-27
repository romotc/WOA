package com.hongganju.db.sessionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.hongganju.common.util.SpringUtil;
import com.hongganju.db.dao.DBManagerDao;
import com.hongganju.db.entity.center.Dbconfig;
import com.hongganju.db.entity.center.Dbmanager;


public class TaskSlaveFactory extends DBFactoryBase{
	public TaskSlaveFactory()
	{
		this.entityList = new ArrayList<String>();
		entityList.add("com/hongganju/db/entity/task");
	}
	static TaskSlaveFactory _instance = null;
	static TaskSlaveFactory getInstance()
	{
		if(_instance == null)
			_instance = new TaskSlaveFactory();
		return _instance;
	}
	
	//CutOff用
	public static LocalSessionFactoryBean getSpringSessionFactoryBean(Short dbid)
	{
		return getInstance().getSpringSessionFactoryBeanImpl(dbid);
	}	
	public static synchronized SessionFactory getSlaveSessionFactory(Short dbid)
	{
		return getInstance().getSessionFactoryImpl(dbid, null, null);
	}
	
	public static synchronized SessionFactory getSlaveSessionFactory(Short dbid, Integer max, Integer min)
	{
		return getInstance().getSessionFactoryImpl(dbid, max, min);
	}
}
