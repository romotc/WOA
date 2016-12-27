package com.hongganju.db.sessionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.hongganju.common.util.SpringUtil;
import com.hongganju.db.dao.DBManagerDao;
import com.hongganju.db.entity.center.Dbconfig;
import com.hongganju.db.entity.center.Dbmanager;



public class TaskFactory extends DBFactoryBase{
	public TaskFactory()
	{
		this.entityList = new ArrayList<String>();
		entityList.add("com/hongganju/db/entity/task");
	}
	static TaskFactory _instance = null;
	static TaskFactory getInstance()
	{
		if(_instance == null)
			_instance = new TaskFactory();
		return _instance;
	}
	
	//CutOff用
	public static LocalSessionFactoryBean getSpringSessionFactoryBean(Short dbid)
	{
		return getInstance().getSpringSessionFactoryBeanImpl(dbid);
	}
	
	//默认接口
	public static synchronized SessionFactory getSessionFactory(Short dbid)
	{
		return getInstance().getSessionFactoryImpl(dbid, null, null);
	}
	
	//指定大小接口
	public static synchronized SessionFactory getSessionFactory(Short dbid, Integer max, Integer min)
	{
		return getInstance().getSessionFactoryImpl(dbid, max, min);
	}
	public static void main(String[] args)
	{
		TaskFactory.getSessionFactory((short)41);
		System.out.println("ok");
	}
}
