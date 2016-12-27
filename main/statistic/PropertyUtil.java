package statistic;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 用来读取属性文件的工具类
 * @author bitjjj@gmail.com
 *
 */
public class PropertyUtil{
    private final static String DEFAULTFILE="jdbc.properties";
    private final Properties properties=new Properties();
    
    public PropertyUtil(String file){
    	InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		if(in == null)
		{
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			in = loader.getResourceAsStream(file);
			if(in != null)
			{
				//System.out.println("read from classpath");
			}
		}
		if(in == null)
		{
	    	try{
	            in = PropertyUtil.class.getClassLoader()
	                    .getResourceAsStream(file);
	            
	                
	        }
	    	 catch (Exception ex1){
	             ex1.printStackTrace();
	        }
		}
		
		if(in != null)
		{
			try {
				properties.load(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
    public PropertyUtil(){
    	this(DEFAULTFILE);
    }
    
    public synchronized String getProperty(String key){
    	return getProperty(key,null);
    }

    public synchronized String getProperty(String key, String defaultValue){
        return properties.getProperty(key, defaultValue);
    }
    
    public synchronized String getProperty(String key, String defaultValue,boolean isnull){
        String value = null;
        value = getProperty(key,defaultValue);
        if(isnull && (value == null || "".equals(value.trim()) )  )
            value = defaultValue;
        return value;
    }
    
    public static void main(String[] args)
    {
//        PropertyUtil preader = new PropertyUtil("log4j.properties");
//        String rootLogger = preader.getProperty("aaa", "defaul");
//        System.out.println(rootLogger);
    	
    	//System.out.println(PropertyUtil.getProperty("sqlite.password"));
    }
}
