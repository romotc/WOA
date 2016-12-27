package com.hongganju.db.sessionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.hongganju.common.util.SpringUtil;
import com.hongganju.db.dao.DBManagerreadDao;
import com.hongganju.db.entity.center.Dbconfig;
import com.hongganju.db.entity.center.Dbmanagerread;

public class TaskReadFactory extends DBFactoryBase{
	public TaskReadFactory()
	{
		this.entityList = new ArrayList<String>();
		entityList.add("com/hongganju/db/entity/task");
	}
	static TaskReadFactory _instance = null;
	static TaskReadFactory getInstance()
	{
		if(_instance == null)
			_instance = new TaskReadFactory();
		return _instance;
	}
	
	public static synchronized SessionFactory getReadSessionFactory(Short dbid)
	{
		return getInstance().getSessionFactoryImpl(dbid, null, null);
	}
	
	public static synchronized SessionFactory getReadSessionFactory(Short dbid, Integer max, Integer min)
	{
		return getInstance().getSessionFactoryImpl(dbid, max, min);
	}
	
}
