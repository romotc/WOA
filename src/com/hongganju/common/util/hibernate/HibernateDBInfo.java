package com.hongganju.common.util.hibernate;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import org.hibernate.mapping.Column;
import org.hibernate.mapping.PrimaryKey;
import org.hibernate.mapping.Property;
import org.hibernate.mapping.RootClass;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
//保存一个DB中的所有java对象，db字段对
public class HibernateDBInfo {
	private HashMap<String, List<HibernateField>> dbInfo = new HashMap<String, List<HibernateField>>();
	private HashMap<String, String> classNameToTableName = new HashMap<String, String>();
	//用于sql写完后直接给key赋值, key是类名
	private HashMap<String, Method> keySetter = new HashMap<String, Method>();
	
//	public void init(HashMap<String, List<HibernateField>> dbInfo)
//	{
//		this.dbInfo = dbInfo;
//	}
	
	//需要全部名字
	public List<HibernateField> getDBInfoByClassName(String className)
	{
		return dbInfo.get(className);
	}
	public String getTableNameByClassName(String className)
	{
		return classNameToTableName.get(className);
	}
	
	public Method getKeySetterByClassName(String className)
	{
		return keySetter.get(className);
	}
	//根据LocalSessionFactoryBean
	//1,找到所有的java对象
	//2,找到有所的db字段和java对象的get方法
	public static HibernateDBInfo createHibernateDBInfo(LocalSessionFactoryBean sessionFactory)
	{
		HibernateDBInfo info = new HibernateDBInfo();
		
		Iterator iterator = sessionFactory.getConfiguration()
				.getClassMappings();
		//处理每一个hibernate对象
		while (iterator.hasNext()) {
			//写入类名---属性列
			RootClass obj = (RootClass) iterator.next();
			String className = obj.getClassName();
			List<HibernateField> fieldList = new ArrayList<HibernateField>();
			info.dbInfo.put(className, fieldList);
			
			String tableName = obj.getTemporaryIdTableName().substring(3);
			info.classNameToTableName.put(className, tableName);
			
			//主键，目前只支持单主键，不支持复合主键
			genKeySetter(obj, info);
//			PrimaryKey primaryKey = obj.getTable().getPrimaryKey();
//			String dbKeyName = primaryKey.getName();

			
			//处理所有的字段
			processRootClass(obj, fieldList);
		}
		return info;
	}
	
	static private void genKeySetter(RootClass root, HibernateDBInfo info)
	{
		//构建Key的setter方法
		String keyName = root.getIdentifierProperty().getName();
		String className = root.getClassName();
		String setterName = "set"
				+ keyName.substring(0, 1).toUpperCase()
				+ keyName.substring(1);

		try {
			Class c = Class.forName(className);
			Method m = c.getMethod(setterName, root.getIdentifierProperty().getType().getReturnedClass());
			info.keySetter.put(className, m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	static private void processRootClass(RootClass obj, List<HibernateField> fieldList)
	{
		String className = obj.getClassName();
		Iterator propertyIterator = obj.getPropertyIterator();
		while (propertyIterator.hasNext()) {
			//列名---get方法
			HibernateField field = new HibernateField();
			
			Property property = (Property) propertyIterator.next();
			//类中的名字
			String name = property.getName();
			
			String dbName = null;
			
			
			Method m2 = null;
			//set-get方法
//			field.dbFieldName = property.get
			if (!name.contains(".")) {
				
				String getterName = "get"
						+ name.substring(0, 1).toUpperCase()
						+ name.substring(1);
				String setterName = "set"
						+ name.substring(0, 1).toUpperCase()
						+ name.substring(1);
				try {
					Class c = Class.forName(className);
					Class[] params = new Class[0];
					Method m1 = c.getMethod(getterName, params);
					if (m1 != null) {
						field.getter = m1;
					}
					m2 = c.getMethod(setterName, m1.getReturnType());
					if (m2 != null) {
						field.setter = m2;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			}
			
			
			Iterator columnIterator = property.getColumnIterator();
			//目前的系统只有一个
			//字段名
			if(columnIterator != null && columnIterator.hasNext())
			{
				Column column = (Column)columnIterator.next();
				field.dbFieldName = column.getName();
			}
			
			//放入list
			if(field.dbFieldName != null && field.getter != null)
				fieldList.add(field);
		}
	}
}
