package com.hongganju.woa.util;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

//本抽取带有重复抽取功能
public class FlvUrlExtractor {
	public static final Logger logger = Logger.getLogger(FlvUrlExtractor.class);
	public interface Analyser extends Library{
    	Analyser INSTANCE = (Analyser)Native.loadLibrary("lib/Analyzer", Analyser.class);
    	
    	void InitAnalyzer();
    	boolean GetMultiVersionTrueUrlWithReferer(String pInitialUrl, PointerByReference ppTrueUrl, 
    			PointerByReference ppTitle, PointerByReference ppReferer, 
    			PointerByReference ppAgent);
    }
	static{
		Analyser.INSTANCE.InitAnalyzer();
	}
	private String trueUrl;
	private String title;
	//尝试次数
	public void setRetryTime(int retry)
	{
		this.retryTime = retry;
	}
	private int retryTime = 3;
	public String getTrueUrl()
	{
		return trueUrl;
	}
	public String getTitle()
	{
		return title;
	}
	
	public void analysis(String url)
	{
		this.title = null;
		this.trueUrl = null;
		int retry = 1;
		if(retryTime > 0)
			retry = retryTime;
		for(int i=0; i<retry; i++)
		{
			if(analysisUrl(url) == true)
				return;
		}
	}
	
	private boolean analysisUrl(String url)
	{
		logger.debug("分析url: " + url);
		if(url == null || url.trim().equals(""))
			return true;
    	PointerByReference turl = new PointerByReference();
    	PointerByReference title = new PointerByReference();
    	PointerByReference ref = new PointerByReference();
    	PointerByReference agent = new PointerByReference();
    	
    	//String url ="http://tv.sohu.com/20121214/n360404534.shtml";
    	//url = "http://v.youku.com/v_show/id_XNDg3NzEzNjQ0.html";
    	
    	Analyser.INSTANCE.GetMultiVersionTrueUrlWithReferer(url, turl, title, ref, agent);
    	Pointer s = turl.getValue();
    	if(s == null)
    		return false;
    	this.trueUrl = turl.getValue().getString(0);
    	
    	
    	Pointer s2 = title.getValue();
    	if(s2 == null)
    		return true;
    	
    	String strTitle = title.getValue().getString(0);
    	try {
    		byte[] bbb;
    		bbb = title.getValue().getByteArray(0, strTitle.length());
    		this.title = new String(bbb, "GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return true;
//    	String strTitle = title.getValue().getString(0);
//    	String strRef = ref.getValue().getString(0);
//    	String strAgent = agent.getValue().getString(0);
//    	
//    	System.out.println(strUrl);
//    	System.out.println(strTitle);
//    	System.out.println(strRef);
//    	System.out.println(strAgent);
	}
    public static void main(String[] args) {
    	String url = "http://tv.sohu.com/20121214/n360404534.shtml";
    	url = "http://v.youku.com/v_show/id_XNDg3NzEzNjQ0.html";
    	FlvUrlExtractor e = new FlvUrlExtractor();
    	e.analysis(url);
    	System.out.println(e.getTitle());
    	System.out.println(e.getTrueUrl());
    	/*
    	Analyser.INSTANCE.InitAnalyzer();
    	
    	PointerByReference turl = new PointerByReference();
    	PointerByReference title = new PointerByReference();
    	PointerByReference ref = new PointerByReference();
    	PointerByReference agent = new PointerByReference();
    	
    	String url ="http://tv.sohu.com/20121214/n360404534.shtml";
    	//url = "http://v.youku.com/v_show/id_XNDg3NzEzNjQ0.html";
    	
    	Analyser.INSTANCE.GetTrueUrlCo(url, turl, title, ref, agent);
    	Pointer s = turl.getValue();
    	String strUrl = turl.getValue().getString(0);
    	String strTitle = title.getValue().getString(0);
    	String strRef = ref.getValue().getString(0);
    	String strAgent = agent.getValue().getString(0);
    	
    	System.out.println(strUrl);
    	System.out.println(strTitle);
    	System.out.println(strRef);
    	System.out.println(strAgent);
    	*/
    }

}
