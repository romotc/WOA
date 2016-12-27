package com.hongganju.common.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;




public class DCSocketClient {


	public static String sendMessage(int type, String body, String ip)
	{
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		Socket socket = null;
		

		try {
			socket = new Socket(ip, DCSocketConfig.innerPort);
			in = new BufferedInputStream(socket.getInputStream());
			out = new BufferedOutputStream(socket.getOutputStream());
			socket.setReceiveBufferSize(DCSocketConfig.RECV_BUFFER_SIZE);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		
		//必须先转换成bytes，以便传递中文
		byte[] bodyBytes = body.getBytes();
		StringBuffer buf = new StringBuffer();
		buf.append(type);
		buf.append(",");
//		buf.append(body);
		int len = bodyBytes.length + buf.length();
		buf.insert(0, ":");
		buf.insert(0, len);
		String request = buf.toString();
		try{
			out.write(request.getBytes());
			out.write(bodyBytes);
			out.flush();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		DCSocketParser parser = new DCSocketParser();
		boolean p = parser.parse(in);
		String result = null;
		if(p == true)
		{
			result = parser.getBody();
		}


		try {
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
		
	}
	public static void main(String[] args)
	{
		System.out.println(DCSocketClient.sendMessage(1, "1234566123123123", "localhost"));
	}
}
