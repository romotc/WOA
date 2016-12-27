package com.hongganju.woa.browser.ie;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import lotus.alg.lang.Pair;
import lotus.alg.util.LogUtil;

import com.hongganju.common.socket.DCSocketClient;
import com.hongganju.common.socket.DCSocketConfig;
import com.hongganju.common.util.FileUtil;
import com.hongganju.common.util.Log4JIniter;
import com.hongganju.communication.data.reportData.CDNPageReportData;
import com.hongganju.woa.util.WOABase;
import com.pt.client.crawl.Crawler;

public class IEBrowserMain {
	private static final int timeout = 280000;
	private static String url = null;
	public static void main(String[] args) {
		Log4JIniter.init("src");
		// TODO Auto-generated method stub
		System.out.println(args.length);
		if(args.length < 2)
		{
			System.out.println("线程号和请输入url");
			return;
		}
		int threadno = Integer.parseInt(args[0]);
		url = args[1];
		url = WOABase.decodeBase64UTF8(url);
		System.out.println(url);
		if(args.length > 2)
		{
			String port_str = args[2];
			try{
			Integer port = Integer.parseInt(port_str);
			DCSocketConfig.innerPort = port;
			}catch(Exception e){}
		}
		//等待同步
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		int threadno = 1;
//		String url = "http://www.sina.com.cn";
		
		
		
		BrowseResult result = new BrowseResult();
		result.setThreadNo(threadno);
		Crawler c  = new Crawler();
		Pair<CDNPageReportData, List<String>> data;
		long t1 = System.currentTimeMillis();
		new Thread(){
			public void run(){
				try {
					Thread.sleep(timeout);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					FileUtil.append("timeoutUrl.txt", new Date() + " " + url);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
			}
		}.start();
		try {
			data = c.download(url);
			result.setData(data.first);
			result.setUrls(data.second);
			result.setSuccess(true);
			System.out.println(LogUtil.toString(data));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setSuccess(false);
			result.setExceptionMsg(e.getMessage());
		}
		
		//超时不再上报，以免服务器进程控制混乱
		long t2 = System.currentTimeMillis();
		if(t2 - t1 > timeout)
		{
			System.out.println("超时，不再上报");
			return;
		}
		IEBrowserMain m = new IEBrowserMain();
		m.reportResult(result);
		System.exit(0);
	}
	public void reportResult(BrowseResult result)
	{
		String encoded = MulitIEUtil.encode(result);
		System.out.println(encoded.length());
		String json_return = DCSocketClient.sendMessage(WOAMultiIEConfig.innerMsg, encoded, "localhost");
		return;
	}
}
