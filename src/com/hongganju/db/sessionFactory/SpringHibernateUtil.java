package com.hongganju.db.sessionFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;


import com.hongganju.common.util.FileUtil;
import com.hongganju.common.util.SpringUtil;
import com.hongganju.db.entity.center.Dbconfig;

public class SpringHibernateUtil {
	private static Logger logger = Logger.getLogger(SpringHibernateUtil.class);
	static private String hibernate_template = "hibernate.properties.template";
	static private String URL_TAG = "#url";
	static private String NAME_TAG = "#name";
	static private String PASSWORD_TAG = "#password";
	static private String ALIAS_TAG = "#alias";
	static private Object synObj = new Object();
	static public void main(String[] args){
		HibernatePair f = createSessionFactroy("jdbc:mysql://124.254.29.5:3306/taskdb","root","hongganju", 50, 10, null);
		HibernatePair f2 = createSessionFactroy("jdbc:mysql://124.254.29.5:3306/centerdb","root","hongganju", 50, 10, null);
		HibernatePair f3 = createSessionFactroy("jdbc:mysql://124.254.29.5:3306/peerdb","root","hongganju", 50, 10, null);
		System.out.println("ok");
	}
	static private int tag = 0;
	
	static public Object getSynObj()
	{
		return synObj;
	}
	
	static public HibernatePair createSessionFactroy(String url, String name, String password, Integer maxConnection, Integer minConnection, List<String> entityList)
	{
		logger.info(url + ", " + name + ", max=" + maxConnection + ", min=" + minConnection);
		int realtag = tag;
		tag++;
		String alias = "DBPool" + tag;
		String filename = "DBPool" + tag + ".xml";
		URL url_path =SpringHibernateUtil.class.getResource("/");
		String temp_dir = url_path.getPath();
		if(maxConnection == null)
			maxConnection = 50;
		if(minConnection == null)
			minConnection = 10;
		logger.info(filename);
		String file = url_path.getPath() + "../../dbtemp/" + filename;
		try {
			String entitys = "";
			for(int i=0; i<entityList.size(); i++)
			{
				entitys += "			<value>classpath:/" + entityList.get(i) + "</value>\r\n";
			}
			FileUtil.write(file, "");
			String hibernate_content = FileUtil.read(temp_dir + "applicationContext.proxool.xml");
			hibernate_content = hibernate_content.replaceFirst(ALIAS_TAG, alias);
			hibernate_content = hibernate_content.replaceFirst(URL_TAG, url);
			hibernate_content = hibernate_content.replaceFirst(NAME_TAG, name);
			hibernate_content = hibernate_content.replaceFirst(PASSWORD_TAG, password);
			hibernate_content = hibernate_content.replaceFirst("#maxConnection", maxConnection.toString());
			hibernate_content = hibernate_content.replaceAll("#minConnection", minConnection.toString());
			hibernate_content = hibernate_content.replaceAll("#entitys", entitys.toString());
			FileUtil.write(file, hibernate_content);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug(file);
		FileSystemXmlApplicationContext context = null;
		try{
			context = 
				new FileSystemXmlApplicationContext(file);
		}catch(Exception e){}
		//linux
		if(context == null)
		{
			file = "/" + file;
			context = 
				new FileSystemXmlApplicationContext(file);
		}
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		LocalSessionFactoryBean sf = (LocalSessionFactoryBean)context.getBean("&sessionFactory");
		HibernatePair pair = new HibernatePair();
		pair.sessionFactory = sessionFactory;
		pair.sessionFactoryBean = sf;
		return pair;
	}
	
	static public HibernatePair getReadSessionFactroy(String url, String name, String password, Integer maxConnection, Integer minConnection,
			List<String> entityList)
	{
		int realtag = tag;
		tag++;
		String alias = "DBPoolRead" + tag;
		String filename = "DBPoolRead" + tag + ".xml";
		URL url_path = SpringHibernateUtil.class.getResource("/");
		String temp_dir = url_path.getPath();
		if(maxConnection == null)
			maxConnection = 50;
		if(minConnection == null)
			minConnection = 10;
		String file = url_path.getPath() + "../../dbtemp/" + filename;
		String entitys = "";
		for(int i=0; i<entityList.size(); i++)
		{
			entitys += "			<value>classpath:" + entityList.get(i) + "</value>\r\n";
		}
		try {
			FileUtil.write(file, "");
			String hibernate_content = FileUtil.read(temp_dir + "applicationContext.proxool.xml");
			hibernate_content = hibernate_content.replaceFirst(ALIAS_TAG, alias);
			hibernate_content = hibernate_content.replaceFirst(URL_TAG, url);
			hibernate_content = hibernate_content.replaceFirst(NAME_TAG, name);
			hibernate_content = hibernate_content.replaceFirst(PASSWORD_TAG, password);
			hibernate_content = hibernate_content.replaceFirst("#maxConnection", maxConnection.toString());
			hibernate_content = hibernate_content.replaceAll("#minConnection", minConnection.toString());
			hibernate_content = hibernate_content.replaceAll("#entitys", entitys.toString());
			FileUtil.write(file, hibernate_content);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug(file);
		FileSystemXmlApplicationContext context = null;
		try{
			context = 
				new FileSystemXmlApplicationContext(file);
		}catch(Exception e){}
		//linux
		if(context == null)
		{
			file = "/" + file;
			context = 
				new FileSystemXmlApplicationContext(file);
		}
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		LocalSessionFactoryBean sf = (LocalSessionFactoryBean)context.getBean("&sessionFactory");
		HibernatePair pair = new HibernatePair();
		pair.sessionFactory = sessionFactory;
		pair.sessionFactoryBean = sf;
		return pair;
	}
	static public HibernatePair getSlaveSessionFactroy(String url, String name, String password, Integer maxConnection, Integer minConnection,
			List<String> entityList)
	{
		int realtag = tag;
		tag++;
		String alias = "DBPoolSlave" + tag;
		String filename = "DBPoolSlave" + tag + ".xml";
		URL url_path = SpringHibernateUtil.class.getResource("/");
		String temp_dir = url_path.getPath();
		if(maxConnection == null)
			maxConnection = 50;
		if(minConnection == null)
			minConnection = 10;
		String file = url_path.getPath() + "../../dbtemp/" + filename;
		try {
			String entitys = "";
			for(int i=0; i<entityList.size(); i++)
			{
				entitys += "			<value>classpath:/" + entityList.get(i) + "</value>\r\n";
			}
			FileUtil.write(file, "");
			String hibernate_content = FileUtil.read(temp_dir + "applicationContext.proxool.xml");
			hibernate_content = hibernate_content.replaceFirst(ALIAS_TAG, alias);
			hibernate_content = hibernate_content.replaceFirst(URL_TAG, url);
			hibernate_content = hibernate_content.replaceFirst(NAME_TAG, name);
			hibernate_content = hibernate_content.replaceFirst(PASSWORD_TAG, password);
			hibernate_content = hibernate_content.replaceFirst("#maxConnection", maxConnection.toString());
			hibernate_content = hibernate_content.replaceAll("#minConnection", minConnection.toString());
			hibernate_content = hibernate_content.replaceAll("#entitys", entitys.toString());
			FileUtil.write(file, hibernate_content);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug(file);
		FileSystemXmlApplicationContext context = null;
		try{
			context = 
				new FileSystemXmlApplicationContext(file);
		}catch(Exception e){}
		//linux
		if(context == null)
		{
			file = "/" + file;
			context = 
				new FileSystemXmlApplicationContext(file);
		}
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		LocalSessionFactoryBean sf = (LocalSessionFactoryBean)context.getBean("&sessionFactory");
		HibernatePair pair = new HibernatePair();
		pair.sessionFactory = sessionFactory;
		pair.sessionFactoryBean = sf;
		return pair;
	}
	public static Dbconfig getDBConfig(String url, String username)
	{

		SessionFactory sessionFactory = (SessionFactory)SpringUtil.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String hql = "from Dbconfig where dburl='" + url + "' and username='" + username  + "'";
		Query q = session.createQuery(hql);
		List<Dbconfig> list = q.list();
		
		t.commit();
		if(list == null || list.size() == 0)
			return null;
		else
			return list.get(0);
	}
//	static public void createProxoolXML(String url, String name, String password, Integer maxConnection, Integer minConnection)
//	{
//		int realtag = tag;
//		tag++;
//		String alias = "DBPool" + tag;
//		URL url_path = SpringHibernateUtil.class.getResource("/");
//		if(maxConnection == null)
//			maxConnection = 50;
//		if(minConnection == null)
//			minConnection = 10;
//		String temp_dir = url_path.getPath();
//		try {
//			//FileUtil.write(temp_dir + hibernate, "");
//			String hibernate_content = FileUtil.read(url_path.getPath() + hibernate_template);
//			hibernate_content = hibernate_content.replaceFirst(ALIAS_TAG, alias);
//			hibernate_content = hibernate_content.replaceFirst(URL_TAG, url);
//			hibernate_content = hibernate_content.replaceFirst(NAME_TAG, name);
//			hibernate_content = hibernate_content.replaceFirst(PASSWORD_TAG, password);
//			hibernate_content = hibernate_content.replaceFirst("#maxConnection", maxConnection.toString());
//			hibernate_content = hibernate_content.replaceAll("#minConnection", minConnection.toString());
//			//FileUtil.write(temp_dir + hibernate, hibernate_content);
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
}
