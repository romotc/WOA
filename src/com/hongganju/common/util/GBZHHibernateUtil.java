package com.hongganju.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.PrimaryKey;
import org.hibernate.mapping.Property;
import org.hibernate.mapping.RootClass;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

//部队项目，仅供参考，不能调用
public class GBZHHibernateUtil {
	static HashMap<String, String> classToTable = new HashMap<String, String>();
	static HashMap<String, String> tableToClass = new HashMap<String, String>();
 
	public static String getClassToTable() {
		return classToTable.values().toString();
	}

	static HashMap<String, List<String>> classKey = new HashMap<String, List<String>>();
	static HashMap<String, List<String>> tableKey = new HashMap<String, List<String>>();
	static HashMap<String, String> classSingleKey = new HashMap<String, String>();
	static HashMap<String, String> tableSingleKey = new HashMap<String, String>();
	static HashMap<String, List<String>> tableColumns = new HashMap<String, List<String>>();
	static HashMap<String, Map<String, Integer>> tableColumnsLength = new HashMap<String, Map<String, Integer>>();
	static HashMap<String, String> classKeyString = new HashMap<String, String>();
	static HashMap<String, String> DBKeyString = new HashMap<String, String>();
	static HashMap<String, HashMap> classPropertyType = new HashMap<String, HashMap>();

	static HashMap<String, List<Method>> fieldGetMethods = new HashMap<String, List<Method>>();
	static HashMap<String, List<Method>> fieldSetMethods = new HashMap<String, List<Method>>();

	static {
		// SpringUtil.init();
		LocalSessionFactoryBean sessionFactory = (LocalSessionFactoryBean) SpringUtil
				.getBean("&sessionFactory");
		//
		Iterator iterator = sessionFactory.getConfiguration()
				.getClassMappings();
		initGetMethods();
		while (iterator.hasNext()) {
			RootClass obj = (RootClass) iterator.next();
			String className = obj.getClassName();
			int last = className.lastIndexOf(".");
			String classShortName = className.substring(last + 1);
			String tableName = obj.getTemporaryIdTableName().substring(3);

			classToTable.put(classShortName, tableName);
			tableToClass.put(tableName, classShortName);
			HashMap<String, String> propertyMap = new HashMap<String, String>();
			classPropertyType.put(classShortName, propertyMap);

			// 获得简单主键
			PersistentClass persistentClass = sessionFactory.getConfiguration()
					.getClassMapping(className);
			PrimaryKey primaryKey = persistentClass.getTable().getPrimaryKey();
			List<Column> list = primaryKey.getTable().getPrimaryKey()
					.getColumns();
			for (int i = 0; i < list.size(); i++) {
				Column key = list.get(i);
				propertyMap.put(key.getName(), key.getValue().getType()
						.getReturnedClass().getName());
			}
			if (list.size() > 0) {
				classSingleKey.put(classShortName, list.get(0).getName());
				tableSingleKey.put(tableName, list.get(0).getName());
			}

			if (list.size() == 1) {
				Column column = list.get(0);

				if (column.getValue().getType().getName().equals("string")) {
					classKeyString.put(classShortName + "-" + column.getName(),
							"yes");
				}
			}
			if (list.size() == 1) {
				Column column = list.get(0);
				if (column.getValue().getType().getName().equals("string")) {
					DBKeyString.put(tableName + "-" + column.getName(), "yes");
				}
			}
			List<String> columnNameList = new ArrayList<String>();
			Map<String, Integer> columnsLength = new HashMap<String, Integer>();
			List<String> keyList = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				keyList.add(list.get(i).getName());
				if (list.size() > 1) {
					columnNameList.add("id."
							+ list.get(i).getName().toLowerCase());
					columnsLength.put("id."
							+ list.get(i).getName().toLowerCase(), list.get(i)
							.getLength());
				} else {
					columnNameList.add(list.get(i).getName().toLowerCase());
					columnsLength.put(list.get(i).getName().toLowerCase(), list
							.get(i).getLength());
				}
			}
			classKey.put(className, keyList);
			tableKey.put(tableName, keyList);
			Iterator columnIterator = persistentClass
					.getPropertyClosureIterator();
			// System.out.println("\n" + className);
			while (columnIterator.hasNext()) {
				Property property = (Property) columnIterator.next();
				Type type = property.getType();
				// System.out.println(property.getName() + ", " +
				// type.getReturnedClass().getName());
				propertyMap.put(property.getName(), type.getReturnedClass()
						.getName());
				if (type.getReturnedClass().getName().equals("java.util.Set")) {
					continue;
				}
				Column column = (Column) property.getColumnIterator().next();
				columnNameList.add(property.getName());
				columnsLength.put(property.getName(), column.getLength());
			}
			tableColumnsLength.put(tableName, columnsLength);
			tableColumns.put(tableName, columnNameList);
			if (list.size() > 0) {
				// classSingleKey.put(classShortName, value);
				tableSingleKey.put(tableName, list.get(0).getName());
			}
		}
	}

	static private void initGetMethods() {
		// SpringUtil.init();
		LocalSessionFactoryBean sessionFactory = (LocalSessionFactoryBean) SpringUtil
				.getBean("&sessionFactory");
		Iterator iterator = sessionFactory.getConfiguration()
				.getClassMappings();
		while (iterator.hasNext()) {
			RootClass obj = (RootClass) iterator.next();
			String className = obj.getClassName();
			Iterator columnIterator = obj.getPropertyIterator();

			List<Method> getterList = new ArrayList<Method>();
			List<Method> setterList = new ArrayList<Method>();
			fieldGetMethods.put(className, getterList);
			fieldSetMethods.put(className, setterList);
			while (columnIterator.hasNext()) {
				Property property = (Property) columnIterator.next();
				String name = property.getName();
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
						Method m = c.getMethod(getterName, params);
						if (m != null) {
							getterList.add(m);
						}
						m = c.getMethod(setterName, m.getReturnType());
						if (m != null) {
							setterList.add(m);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
	}

	static public boolean isDBKeyString(String tablename) {
		String keyname = GBZHHibernateUtil.getTableKeynameByTableName(tablename)
				.get(0);
		return DBKeyString.containsKey(tablename + "-" + keyname);
	}

	static public List<String> getAllTableName() {
		List<String> list = new ArrayList<String>(tableToClass.keySet());
		return list;
	}

	static public boolean isClassKeyString(String classname) {
		String keyname = GBZHHibernateUtil.getClassKeynameByClassName(classname)
				.get(0);
		return classKeyString.containsKey(classname + "-" + keyname);
	}

	static public List<String> getTableColumnsByDBTableName(String tablename) {
		return tableColumns.get(tablename);
	}

	static public Integer getTableColumnWidthByDBTableNameAndColumnName(
			String tablename, String columnname) {
		return tableColumnsLength.get(tablename).get(columnname);
	}

	static public List<String> getTableColumnsByClass(Class class1) {
		// String className = class1.getName();
		// int last = className.lastIndexOf(".");
		// String classShortName = className.substring(last+1);
		return tableColumns.get(classToTable.get(class1.getSimpleName()));
	}

	static public List<String> getTableColumnsByClassName(String className) {
		return tableColumns.get(classToTable.get(className));
	}

	static public String getDBTableNameByClass(Class clasz) {
		return classToTable.get(clasz.getSimpleName());
	}

	static public String getClassNameByDBTableName(String tablename) {
		return tableToClass.get(tablename);
	}

	static public String getDBTableNameByClassName(String className) {
		return classToTable.get(className);
	}

	static public List<String> getTableKeynameByTableName(String tablename) {
		return tableKey.get(tablename);
	}

	static public List<String> getClassKeynameByClassName(String classname) {
		return classKey.get(classname);
	}

	static public String getTableKeynameByClassName(String classname) {
		String tablename = classToTable.get(classname);
		if (tablename == null)
			return null;
		return tableSingleKey.get(tablename);
	}

	static public String getClassKeynameByTableName(String tablename) {
		String classname = tableToClass.get(tablename);
		if (classname == null)
			return null;
		return classSingleKey.get(classname);
	}

	static public List<String> getTableKeynamesByClassName(String classname) {
		String tablename = classToTable.get(classname);
		if (tablename == null)
			return null;
		return tableKey.get(tablename);
	}

	static public List<String> getClassKeynamesByTableName(String tablename) {
		String classname = classToTable.get(tablename);
		if (classname == null)
			return null;
		return classKey.get(classname);
	}

	/**
	 *  得到类属性的数据类型，
	 * @param classname
	 * @param property
	 * @return
	 *例如：getClassPropertyType("org.gbzh.server.db.entity.A编制", "机构名称码")
	 *返回Java.lang.String 
	 * */
	static public String getClassPropertyType(String classname, String property) {
		HashMap map = classPropertyType.get(classname);
		if (property.contains(".")) {
			property = property.substring(property.lastIndexOf(".") + 1);
		}
		String result = (String) map.get(property);
		if (result == null) {
			result = (String) map.get(property.toUpperCase());
		}
		return result;
	}

	/**
	 *  得到数据库表的列数据类型
	 * @param tableName
	 * @return
	 */
	static public HashMap<String, String> getTableColumnDataTypeFromTableName(
			String tableName) {
		// SpringUtil.init();
		String className = GBZHHibernateUtil.getClassNameByDBTableName(tableName);// //得到类名
		List<String> list = new ArrayList<String>();
		list = GBZHHibernateUtil.tableColumns.get(tableName);// //由表名得到列名
		HashMap<String, String> map = new HashMap<String, String>();
		map = GBZHHibernateUtil.classPropertyType.get(className);
		/*
		 * for(int i=0; i < list.size(); i ++){ String column_name =
		 * list.get(i); String data_type = HibernateUtil.getClassPropertyType(
		 * "org.gbzh.server.db.entity."+ className, column_name);
		 * map.put(column_name, data_type); }
		 */
		return map;

	}

	static public List<Method> getGMethodsByClassName(String className) {
		return fieldGetMethods.get(className);
	}

	static public List<Method> getSMethodByClassName(String className) {
		return fieldSetMethods.get(className);
	}

	static public boolean isEntityEqual(Object obj1, Object obj2) {
		if (obj1.getClass() != obj2.getClass())
			return false;

		List<Method> getterList = fieldGetMethods
				.get(obj1.getClass().getName());
		List<Method> setterList = fieldSetMethods
				.get(obj1.getClass().getName());
		int size = getterList.size();
		if (setterList.size() != size) {
			return false;
		}

		for (int i = 0; i < size; i++) {
			Method getter = getterList.get(i);
			Method setter = setterList.get(i);
			System.out.println(getter + "\t" + setter);
		}
		return true;
	}

	/**
	 * 获得对象entity的主键值，T为entity类型，PK为主键类型 返回值为数据库中主键的列与值的对应即：columnName->value
	 * 注意的问题：hibernate Mapping表中Class的属性名与数据库中的column名相同（大小写无关）。 yxl
	 */
	public static <T, PK> Map<String, Object> getPrimaryKeyValueMap(T entity) {
		Map<String, Object> primaryKeyValueMap = new HashMap<String, Object>();

		LocalSessionFactoryBean sessionFactory = (LocalSessionFactoryBean) SpringUtil
				.getBean("&sessionFactory");
		PersistentClass persistentClass = sessionFactory.getConfiguration()
				.getClassMapping(entity.getClass().getName());

		PrimaryKey primaryKey = persistentClass.getTable().getPrimaryKey();
		List<Column> columnList = primaryKey.getColumns();

		Method[] methods = entity.getClass().getMethods();
		PK value = null;
		if (columnList.size() == 1) {// 单一主键
			for (Method method : methods) {
				if (method.getName().startsWith("get")
						&& columnList
								.get(0)
								.getName()
								.equalsIgnoreCase(method.getName().substring(3))) {
					try {
						value = (PK) method.invoke(entity, new Object[0]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if (value != null) {
						primaryKeyValueMap.put(columnList.get(0).getName(),
								value);
						break;
					}
				}
			}
		} else if (columnList.size() > 1) {// 复合主键
			for (Method method : methods) {// 获得复合主键值
				if (method.getName().equals("getId")) {
					try {
						value = (PK) method.invoke(entity, new Object[0]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					break;
				}
			}

			if (value != null) {// 获得复合主键中各列的值
				Method[] pkmethods = value.getClass().getMethods();

				for (Column column : columnList) {
					String columnName = column.getName();
					for (Method method : pkmethods) {
						String methodName = method.getName();
						if (methodName.startsWith("get")
								&& methodName.substring(3).equalsIgnoreCase(
										columnName)) {
							Object key = null;
							try {
								key = method.invoke(value, new Object[0]);
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}

							if (key != null) {
								primaryKeyValueMap.put(columnName, key);
							}
						}
					}
				}
			}
		}

		return primaryKeyValueMap;
	}
	/**
	 * 查找 C_表关键字 表，得到一个表的关键字
	 * input：tableName
	 * output：List<String> keys
	 */
	public static List<String> getKeysByTableName(String tableName){
		SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		String hql = "select 关键字 from C_表关键字 where 表名 = '" + tableName +"'" ;				
		Session session = sessionFactory.openSession();
		Query query = session.createSQLQuery(hql);
		List list = query.list();
		//System.out.println((String)list.get(0));
		session.close();
		List<String> keys = new ArrayList<String>();
		String[] str = ((String)list.get(0)).split(",");
		for(int i = 0; i < str.length; i++){
			keys.add(str[i]);
		}
		return keys;
	
	}

	public static void main(String[] args) {
		// F受训情况 f1 = new F受训情况();
		// F受训情况 f2 = new F受训情况();
		// f1.set受训等级("1");
		// f2.set受训等级("1");
		// System.out.println(isEntityEqual(f1, f2));

		List list = GBZHHibernateUtil.getKeysByTableName("OF_直系亲属");
		System.out.println(list.toString());


		// HashMap map =
		// HibernateUtil.getTableColumnDataTypeFromTableName("A_基本情况");
		// Map<String, Object> map1 =
		// HibernateUtil.getPrimaryKeyValueMap("A_基本情况");
		for (int i = 0; i < 1; i++)
			;

	}
}