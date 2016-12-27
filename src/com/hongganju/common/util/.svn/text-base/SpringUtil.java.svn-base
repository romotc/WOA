package com.hongganju.common.util;

import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	private static ClassPathXmlApplicationContext context = null;
	static{
		context = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("init1");
	}
	public static void init()
	{

	}
	public static void rebuild()
	{
		ClassPathXmlApplicationContext back = context;
		
		
		ClassPathXmlApplicationContext new_context = context = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
		context = new_context;
//		back.close();
		
		
		SessionFactory sessionFactory = (SessionFactory) back.getBean("sessionFactory");
		sessionFactory.close();
		back.stop();
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public static Object getBean(String str)
	{
		return context.getBean(str);
	}
	
	public static void main(String args[])
	{
		Log4JIniter.init();
		SpringUtil.init();
		
		ClassPathXmlApplicationContext context2 = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("init2");
		
		context.close();
		context = context2;
	}
	
}
