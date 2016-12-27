package com.hongganju.common.socket;

import java.io.BufferedInputStream;
import java.io.IOException;


public class DCSocketParser {
	private int type;
	private String body;
	public int getType()
	{
		return type;
	}
	public String getBody()
	{
		return body;
	}
	
	public boolean parse(BufferedInputStream in)
	{
//		boolean result = false;
		
		byte[] buf = new byte[10000];
		

		int readlen = 0;
		int lenSplit = -1;
		int typeSplit = -1;
		do{
			//每次读到的实际字节数
			int len;
			//先读20个
			try {
				len = in.read(buf, readlen, 20-readlen);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			//出错
			if(len == 0)
				return false;
			
			int start = readlen;
			readlen += len;
			
			//找到实际长度和类型
			for(int i=start; i<readlen; i++)
			{
				if(buf[i] == ':')
				{
					lenSplit = i;
				}
				else if(buf[i] == ',')
				{
					typeSplit = i;
					break;
				}
			}
		}while(readlen > 0 && readlen<=40 && typeSplit < 0);
		
		if(lenSplit < 0 || typeSplit < 0)
			return false;
		
		
		String lenStr = new String(buf, 0, lenSplit);
		//实际长度
		int allLen = 0;
		try{
			allLen = Integer.parseInt(lenStr);
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		//类型
		String typeStr = new String(buf, lenSplit+1, typeSplit - (lenSplit + 1));
		try{
			this.type = Integer.parseInt(typeStr);
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		int bodyLen = allLen - (typeSplit - lenSplit );
		//大buffer
		byte[] bodyBytes = new byte[bodyLen];
		
		int bodyReaded = readlen - (typeSplit + 1);

		if(bodyReaded > 0)
		{
			System.arraycopy(buf, typeSplit+1, bodyBytes, 0, bodyReaded);
		}
		
		//一次性读到所有的
		while(bodyReaded < bodyLen)
		{
			int len = 0;
			try {
				len = in.read(bodyBytes, bodyReaded, bodyLen - bodyReaded);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			if(len <= 0)
				return false;
			
			bodyReaded += len;
		}
		
		this.body = new String(bodyBytes);
		
		
		return true;
	}
}
