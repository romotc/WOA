package com.hongganju.common.util.hibernate;

import java.util.HashMap;


//按照
public class HibernateDBInfoManager {
	static HashMap<Short, HibernateDBInfo> alldbInfos = new HashMap<Short, HibernateDBInfo>();
	
	//按照dbid区分，有问题
	public static void addDBInfo(Short dbid, HibernateDBInfo dbInfo)
	{
		alldbInfos.put(dbid, dbInfo);
	}

	public static HibernateDBInfo getDBInfo(Short dbid)
	{
		return alldbInfos.get(dbid);
	}
}
