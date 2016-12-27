package com.hongganju.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Log4JIniter {
	private static Logger logger = Logger.getLogger(Log4JIniter.class);
	public static void init(String path)
	{
		String filename = path + "//log4j.properties";
		Properties props = new Properties();
		InputStream in = null;
		System.out.println("read from file:" + filename);
		try{
			in = new FileInputStream(filename);
		}catch(Exception e)
		{
			System.out.println("file:" + filename + "不存在");
		}
		
		if(in == null)
		{
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			in = loader.getResourceAsStream("log4j.properties");
			if(in != null)
				System.out.println("read from classpath");
		}
		
		try {
			props.load(in);
			in.close();
			System.out.println("ql");
			System.out.println(props);
			PropertyConfigurator.configure(props);// 装入log4j配置信息
			logger.info(filename);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	public static void init()
	{
		//String filename = "D:\\workspace\\eclipse\\pt\\hongganju\\src//log4j.properties";
		String filename = "src//log4j.properties";
		Properties props = new Properties();
		try {
			FileInputStream istream = new FileInputStream(filename);
			props.load(istream);
			istream.close();
			PropertyConfigurator.configure(props);// 装入log4j配置信息
			logger.info(filename);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
}
