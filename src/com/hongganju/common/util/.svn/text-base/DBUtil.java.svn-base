package com.hongganju.common.util;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.impl.SessionFactoryImpl;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.hongganju.common.util.hibernate.HibernateDBInfo;
import com.hongganju.common.util.hibernate.HibernateDBInfoManager;
import com.hongganju.common.util.hibernate.HibernateDBInfoUtil;
import com.hongganju.common.util.hibernate.HibernateField;
import com.hongganju.db.dao.DBManagerreadDao;
import com.hongganju.db.entity.center.Dbmanager;
import com.hongganju.db.entity.center.Dbmanagerread;
import com.hongganju.db.entity.center.Ipregion;
import com.hongganju.db.sessionFactory.PeerFactory;
import com.hongganju.db.sessionFactory.TaskFactory;
import com.hongganju.db.sessionFactory.TaskSlaveFactory;


public class DBUtil {
	private static Logger logger = Logger.getLogger(DBUtil.class);
	public static void main(String[] args)
	{
		DBUtil.transferIp("202.85.221.34");
		Ipregion re = DBUtil.getIpregion("202.85.221.34");
		System.out.println(re.getIsp());
		//ok
//		dropTaskexecObjectXXX("jdbc:mysql://localhost:3306/taskdb_default", 
//				"root", "root", 1);
		//ok
//		createTaskexecObjectXXX("jdbc:mysql://localhost:3306/taskdb_default", 
//				"root", "root", 100);
	}
	public static void update(Object obj, Short dbid)
	{
		SessionFactory sf = TaskFactory.getSessionFactory(dbid);
		Session session = sf.getCurrentSession();
        Transaction t = (Transaction) session.beginTransaction();
        try {
            session.update(obj);
        } catch (HibernateException e) {
            //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw e;
        }
        t.commit();
	}
	public static void dropTaskexecObjectXXX(String url, String user, String password, int dayOfMonth)
	{
		String str = "DROP TABLE taskexecobject" + dayOfMonth;
		try{
			// 载入驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 建立连接
			Connection con = DriverManager.getConnection(url, user, password);
			// 创建状态
			Statement stmt = con.createStatement();
			// 执行SQL语句，返回结果集
			int res = stmt.executeUpdate(str);
			// 对结果集进行处理
			// 释放资源
			stmt.close();
			con.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//创建TaskexecOjbectXXX
	public static void createTaskexecObjectXXX(String url, String user, String password, int dayOfMonth)
	{
		String str = "CREATE TABLE `taskexecobject" + dayOfMonth + "` (" + 
		  "`TaskExecContentID` bigint(20) NOT NULL auto_increment," + 
		  "`CustomID` smallint(6) NOT NULL COMMENT '客户编号，8位，序列自动生成'," + 
		  "`TaskDefinitionID` int(11) default NULL," + 
		  "`PeerTaskMainID` bigint(20) default NULL," + 
		  "`URL` varchar(4096) collate utf8_bin default NULL," + 
		  "`FileType` varchar(50) collate utf8_bin default NULL," + 
		  "`FileSize` int(11) default NULL," + 
		  "`Starttime` bigint(20) default NULL COMMENT '精度微秒'," + 
		  "`totalDownLoadTime` float default NULL," + 
		  "`ContentBytes` float default NULL," + 
		  "`NetwordBytes` float default NULL," + 
		  "`HttpCode` varchar(50) collate utf8_bin default NULL," + 
		  "`ErrorCode` varchar(12) collate utf8_bin default NULL," + 
		  "`DNSTime` float default NULL," + 
		  "`ConnectionTime` float default NULL," + 
		  "`SSLTime` float default NULL," + 
		  "`RedirectTime` float default NULL," + 
		  "`RedirectNum` float default NULL," + 
		  "`RequestTime` float default NULL," + 
		  "`FirstByteTime` float default NULL," + 
		  "`OtherBytesTime` float default NULL," + 
		  "`CloseConnectionTime` float default NULL," + 
		  "`HttpHeader` varchar(1000) collate utf8_bin default NULL," + 
		  "`AvgDownloadSpeed` int(11) default NULL COMMENT '下载速度，单位B/S,计算方式：ContentBytes/totalDownLoadTime'," + 
		  "`HttpRequestHeader` varchar(1000) collate utf8_bin default NULL," + 
		  "`UploadTaskTime` datetime default NULL," + 
		  "`CNAME` varchar(2000) collate utf8_bin default NULL," + 
		  "`domain` varchar(1024) collate utf8_bin default NULL," + 
		  "`popip` varchar(16) collate utf8_bin default NULL," + 
		  "`taskexecTransactionId` bigint(20) default NULL COMMENT '事务任务ID（外键）'," +
		  "`transactionStep` int(11) default NULL COMMENT '事务任务序号'," + 
		  "PRIMARY KEY  (`TaskExecContentID`)," + 
		  "KEY `I_TaskDefinitionID` (`TaskDefinitionID`)," + 
		  "KEY `I_Starttime` (`Starttime`)," + 
		  "KEY `I_TaskExecMainID` (`PeerTaskMainID`)," + 
		  "KEY `uploadtasktime` (`UploadTaskTime`)" + 
		") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin";
		logger.info(str);
		try{
			// 载入驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 建立连接
			Connection con = DriverManager.getConnection(url, user, password);
	
			// 创建状态
			Statement stmt = con.createStatement();
			// 执行SQL语句，返回结果集
			int res = stmt.executeUpdate(str);
			// 对结果集进行处理
			// 释放资源
			stmt.close();
			con.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	static public List<Dbmanager> getTaskdbs()
	{
		SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from Dbmanager where dbtype=2 group by url";
		Transaction t = session.beginTransaction();
		Query query = session.createQuery(hql);
		List<Dbmanager> list = query.list();
		t.commit();
		return list;
	}
	
	static public List<Dbmanagerread> getReadTaskdbsByTaskDB(List<Dbmanager> dbs)
	{
		List<Dbmanagerread> list = new ArrayList<Dbmanagerread>();
		DBManagerreadDao dao = new DBManagerreadDao();
		for(int i=0; i<dbs.size(); i++)
		{
			Dbmanagerread read = dao.getDbmanagerByID(dbs.get(i).getId());
			list.add(read);
		}
		
		return list;
	}
	
	
	
	static public List<Dbmanagerread> getReadTaskdbs()
	{
		SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from Dbmanagerread where dbtype=2 group by url";
		Transaction t = session.beginTransaction();
		Query query = session.createQuery(hql);
		List<Dbmanagerread> list = query.list();
		t.commit();
		return list;
	}
	static public List<Dbmanager> getPeerdbs()
	{
		SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from Dbmanager where dbtype=3 group by url";
		Transaction t = session.beginTransaction();
		Query query = session.createQuery(hql);
		List<Dbmanager> list = query.list();
		t.commit();
		return list;
	}
	static public Long transferIp(String Ip)
	{
		int p[] = new int[3];
		int count = 0;
		for(int i=0; i<Ip.length(); i++)
		{
			char c = Ip.charAt(i);
			if(c == '.')
			{
				p[count] = i;
				count++;
			}
		}
		Long A = Long.parseLong(Ip.substring(0, p[0]));
		Long B = Long.parseLong(Ip.substring(p[0]+1, p[1]));
		Long C = Long.parseLong(Ip.substring(p[1]+1, p[2]));
		Long D = Long.parseLong(Ip.substring(p[2]+1));
		
		return A*256*256*256 + B*256*256 + C*256 + D;
	}
	
	//域名对应的ip地址有限，对于woa来说可以缓存
	private static HashMap<String, Ipregion> ipCache = new HashMap<String, Ipregion>();
	static public Ipregion getIpregion(String IP)
	{
		if(IP == null || IP.trim().equals(""))
			return null;
		Ipregion region = ipCache.get(IP);
		if(region != null)
			return region;
		
		long Ip_BigInt = transferIp(IP);
		
		SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
        Transaction t = (Transaction) session.beginTransaction();
        
        String hql = "from Ipregion where CIDRStartInt <= :ip and " +
			"CIDREndInt >= :ip " +
			"order by CIDREndInt - CIDRStartInt ASC, ISP DESC";
		if(logger.isDebugEnabled())
		{
			logger.debug(hql);
			logger.debug("ip_bigint=" + Ip_BigInt);
			logger.debug("ip:" + IP);
		}
		Query query = session.createQuery(hql);
		query.setLong("ip", Ip_BigInt);
		
		List<Ipregion> list = query.list();
        
        t.commit();
        if(list.size() == 0)
        	return null;
		region = list.get(0);
		synchronized(ipCache)
		{
			ipCache.put(IP, region);
		}
		return region;
	}
	public static void cutoffSlaveTaskDBObject(Object obj, Short DBID)
	{
		LocalSessionFactoryBean sessionFactory = TaskSlaveFactory.getSpringSessionFactoryBean(DBID);
		if(sessionFactory != null)
			cutoffHibernateObject(obj, sessionFactory);
	}
	
	public static void cutoffTaskDBObject(Object obj, Short DBID)
	{
		logger.debug("cutoff");
		LocalSessionFactoryBean sessionFactory = TaskFactory.getSpringSessionFactoryBean(DBID);
		if(sessionFactory != null)
		{
			cutoffHibernateObject(obj, sessionFactory);
		}
		else
		{
			sessionFactory = TaskSlaveFactory.getSpringSessionFactoryBean(DBID);
			if(sessionFactory != null)
				cutoffHibernateObject(obj, sessionFactory);
			else
				logger.debug("null");
		}
	}
	
	public static void cutoffPeerDBObject(Object obj, Short DBID)
	{
		
	}
	
	public static void cutoffHibernateObject(Object obj, LocalSessionFactoryBean sessionFactory)
	{
//		LocalSessionFactoryBean sessionFactory = (LocalSessionFactoryBean)SpringUtil.getBean("&sessionFactory");
		PersistentClass persistentClass = sessionFactory.getConfiguration().getClassMapping(obj.getClass().getName());
		Method[] methods = obj.getClass().getMethods();
		
		//遍历所有属性
		for(int i=0; i<methods.length; i++)
		{
			String method = methods[i].getName();
			if(method.startsWith("set"))
			{
				String getter = method.replaceFirst("set", "get");
				
				Method getterMethod = null;
				Method setterMethod = null;
				try {
					getterMethod = obj.getClass().getMethod(getter, null);
					setterMethod = obj.getClass().getMethod(method, String.class);
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
				} catch (NoSuchMethodException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
				}
				if(getterMethod == null || setterMethod == null)
					continue;
//				System.out.println("运行" + getter);
				Object value = null;
				try {
					value = getterMethod.invoke(obj, null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
				}
				if(value == null)
					continue;
				if(!(value instanceof String))
				{
					continue;
				}
				
				String value_str = (String) value;
				
				
				String name = method.substring(3);
				String first = name.substring(0,1);
				String other = name.substring(1);
				name = first.toLowerCase() + other;
				Property prop = null;
				try{
					prop = persistentClass.getProperty(name);
				}catch(Exception e)
				{}
				name = first.toUpperCase() + other;
				if(prop == null)
				try{
					prop = persistentClass.getProperty(name);
				}catch(Exception e)
				{}
				
				if(prop == null)
					continue;
				int len = ((Column)prop.getColumnIterator().next()).getLength();
//				System.out.println("修改前：" + value_str);
				if(value_str.length() > len)
				{
					if(logger.isDebugEnabled())
					{
						logger.debug("字段" + name + "被截断到" + len + "位");
						logger.debug("原字段：" + value_str);
					}
					
					value_str = value_str.substring(0, len);
				}
					
//				System.out.println("修改后：" + value_str);
				try {
					setterMethod.invoke(obj, value_str);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
//				System.out.println(prop + ",len=" + len);
			}
		}
	}
	
	//list中只能是一种数据
	//不支持返回自增主键
	public static boolean saveJDBCList(List list, short dbid)
	{
		boolean returnFlag = false;
		SessionFactory sessionFactory = TaskFactory.getSessionFactory(dbid);
		if(sessionFactory == null || list == null || list.size() <= 0)
			return false;
		if(sessionFactory instanceof SessionFactoryImpl)
		{
			SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl)sessionFactory;
			HibernateDBInfo info = HibernateDBInfoManager.getDBInfo(dbid);
			Object object = list.get(0);
			String className = object.getClass().getName();
			
			//以fieldList的顺序创建preparedSQL语句
			String sql = HibernateDBInfoUtil.createPreparedSQL(object, info);
			//System.out.println(sql);
			
			//准备执行主键的回写
			Method keySetter = info.getKeySetterByClassName(object.getClass().getName());
			Connection conn = null;
			PreparedStatement statment = null;
			Boolean connAutoCommit = null;
			List<HibernateField> fieldList = info.getDBInfoByClassName(className);
			try {
				conn = sessionFactoryImpl.getConnectionProvider().getConnection();
				
				connAutoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				statment = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				//保存每一个对象
				for(int i=0; i<list.size(); i++)
				{
					//要保存的对象
					Object saveObj = list.get(i);
					
					for(int j=0; j<fieldList.size(); j++)
					{
						Method getMethod = fieldList.get(j).getter;
						Object o = getMethod.invoke(saveObj, null);
						statment.setObject(j+1, o);
					}
					statment.addBatch();
					//把自生成的主键写回
	                
				}
				
                int[] rowcount = statment.executeBatch();
//                ResultSet rs = statment.getGeneratedKeys();
//                if (rs.next()) { 
//                    //知其仅有一列，故获取第一列 
//                    Object id = rs.getObject(1);
////                    System.out.println("-----预定义SQL模式-----id = " + id);
//                    
//                    if(keySetter != null)
//                    {
////                    	keySetter.invoke(saveObj, id);
//                    }
//                }
                conn.commit();
                returnFlag = true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(statment != null)
				{
					try {
						statment.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						statment = null;
					}
				}
				if(conn != null)
				{
					try{
						if(connAutoCommit != null)
							conn.setAutoCommit(connAutoCommit);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					try {		
						conn.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						conn = null;
					}
				}
			}
		}
		
		return returnFlag;
	}
	
	public static boolean saveJDBC(Object object, Short dbid)
	{
		boolean returnFlag = false;
		SessionFactory sessionFactory = TaskFactory.getSessionFactory(dbid);
		if(sessionFactory == null)
			return false;
		if(sessionFactory instanceof SessionFactoryImpl)
		{
			SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl)sessionFactory;
			HibernateDBInfo info = HibernateDBInfoManager.getDBInfo(dbid);
			String sql = HibernateDBInfoUtil.createSQL(object, info);
			logger.debug(sql);
			if(sql != null)
			{
				Connection conn = null;
				Statement statment = null;
				Boolean connAutoCommit = null;
				try {
					conn = sessionFactoryImpl.getConnectionProvider().getConnection();
					
					connAutoCommit = conn.getAutoCommit();
					conn.setAutoCommit(false);
					statment = conn.createStatement();
	                int rowcount = statment.executeUpdate(sql);
	                conn.commit();
	                
	                //把自生成的主键写回
	                ResultSet rs = statment.getGeneratedKeys();
	                if (rs.next()) { 
	                    //知其仅有一列，故获取第一列 
	                    Object id = rs.getObject(1);
//	                    System.out.println("-----预定义SQL模式-----id = " + id);
	                    Method keySetter = info.getKeySetterByClassName(object.getClass().getName());
	                    if(keySetter != null && id != null)
	                    {
//	                    	keySetter.get
	                    	Class[] types = keySetter.getParameterTypes();
	                    	String objName = id.getClass().getName();
	                    	String paramName = types[0].getName();
	                    	if(!paramName.equals(objName))
	                    	{
	                    		if(paramName.equals("java.lang.Integer") && objName.equals("java.lang.Long"))
	                    		{
	                    			Long v = (Long) id;
	                    			keySetter.invoke(object, v.intValue());
	                    		}else if(paramName.equals("java.lang.Long") && objName.equals("java.lang.Integer"))
	                    		{
	                    			Integer v = (Integer)id;
	                    			keySetter.invoke(object, v.longValue());
	                    		}
	                    	}
	                    	else
	                    	{
	                    		keySetter.invoke(object, id);
	                    	}
//	                    	System.out.println(types[0]);
	                    	
	                    }
	                }
	                returnFlag = true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					if(statment != null)
					{
						try {
							statment.close();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally{
							statment = null;
						}
					}
					if(conn != null)
					{
						try{
							if(connAutoCommit != null)
								conn.setAutoCommit(connAutoCommit);
						}catch(Exception e)
						{
							e.printStackTrace();
						}
						try {		
							conn.close();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally{
							conn = null;
						}
					}
				}
				
			}
		}
		return returnFlag;
	}

	public static void save(Object object, SessionFactory sessionFactory)
	{
		if(object == null || sessionFactory == null)
			return;
		Session session = sessionFactory.openSession();
		Transaction t = null;
    	try{
    		t = (Transaction) session.beginTransaction();
    		session.save(object);
    		t.commit();
    	}catch(HibernateException e)
    	{
    		if(t != null)
        		t.rollback();
    		throw e;
    	}
    	finally{
    		session.close();
    	}
	}
	
	public static void saveList(List list, SessionFactory sessionFactory)
	{
		if(sessionFactory == null || list == null || list.size() == 0)
			return;

		Session session = sessionFactory.openSession();
		Transaction t = null;
    	try{
    		t = (Transaction) session.beginTransaction();
    		for(int i=0; i<list.size(); i++)
    		{
    			session.save(list.get(i));
    		}
    		
    		t.commit();
    	}catch(HibernateException e)
    	{
    		if(t != null)
        		t.rollback();
    		throw e;
    	}
    	finally{
    		session.close();
    	}
	}
}
