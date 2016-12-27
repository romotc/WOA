package com.hongganju.common.socket;

import com.hongganju.woa.browser.ie.WOAIEProvider;
import com.hongganju.woa.browser.ie.WOAMultiIEConfig;



public class MessageDistributerImpl implements MessageDistributer{

	@Override
	public String process(int type, String body, String ip, int port) {
		// TODO Auto-generated method stub
		switch(type)
		{
			case WOAMultiIEConfig.innerMsg:
//				System.out.println("receive:" + body);
				DataProvider provider = new WOAIEProvider();
				return provider.retreive(body, ip, port);
			default :
				System.out.println("default:" + body);
				System.out.println("报文长度:" + body.length());
				return null;
		}
	}

}
