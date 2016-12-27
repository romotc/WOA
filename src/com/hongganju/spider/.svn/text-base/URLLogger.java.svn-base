package com.hongganju.spider;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class URLLogger {
	static Object synObj = new Object();
	static FileOutputStream fos;
	
	static{
		try {
			fos = new FileOutputStream("url.log", true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static public void setLogger(String file)
	{
		if(fos != null)
		{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fos = new FileOutputStream(file, true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static public void recordURL(String url, String response, String type)
	{
		synchronized(synObj)
		{
			String str = url + " " + response + " " + type + "\r\n";
			try {
				fos.write(str.getBytes());
				fos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	static public void stop()
	{
		try {
			fos.close();
			fos = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
