package com.hongganju.woa.browser.ie;

import com.hongganju.common.socket.DCSocketConfig;
import com.hongganju.common.socket.DCSocketServer;
import com.hongganju.common.socket.DataProvider;

public class WOAIEProvider implements DataProvider{
	public static MultiIEThreadManager threadmanager;
	@Override
	public String retreive(String request, String ip, int port) {
		// TODO Auto-generated method stub
		System.out.println(request.length());
		BrowseResult result = MulitIEUtil.decode(request);
		
		System.out.println("received");
		threadmanager.notify(result);
		System.out.println("notify: " + result.getThreadNo());
		return null;
	}
	public static void main(String[] args)
	{
		DCSocketServer socketserver = new DCSocketServer(DCSocketConfig.innerPort);
		socketserver.start();
		System.out.println("1");
	}
}
