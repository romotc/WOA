package com.hongganju.woa.spider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.hongganju.common.Path;

import weblech.spider.SpiderConfig;

//基本的配置类，沿用的WebBlech
public class WOAConfig {
	static public Properties props = null;
	static final String propsFile = "config/Spider.properties";
	static public SpiderConfig config;
	static {
		String fullPropsFile = propsFile;
		try {
			if (Path.getPath() != null) {
				fullPropsFile = Path.getPath() + "/" + fullPropsFile;
			}
			System.out.println("propFile:" + fullPropsFile);
			FileInputStream propsIn = new FileInputStream(fullPropsFile);
			props = new Properties();
			props.load(propsIn);
			propsIn.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found: " + fullPropsFile);
			System.exit(1);
		} catch (IOException ioe) {
			System.out.println("IO Exception caught reading config file: "
					+ ioe.getMessage());
			System.exit(1);
		}
		config = new SpiderConfig(props);
	}
}
