package com.hongganju.common.socket;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DCSocketServerThread extends Thread{
	Socket socket;
	BufferedOutputStream out;
	BufferedInputStream in;
	public DCSocketServerThread(Socket socket, BufferedOutputStream out, 
			BufferedInputStream in)
	{
		this.socket = socket;
		this.out = out;
		this.in = in;
		//this.processor = processor;
	}
	
	public void run()
	{
		//BroadReceiverInterface processor = (BroadReceiverInterface) receiver.newInstance();
		
		DCSocketParser parser = new DCSocketParser();
		boolean p = parser.parse(in);
		if(p == true)
		{
			MessageDistributer processor = new MessageDistributerImpl();
			String responseBody = processor.process(parser.getType(), parser.getBody(), 
					socket.getInetAddress().getHostAddress(), socket.getPort());
			if(responseBody != null)
			{
				StringBuffer buf = new StringBuffer();
				buf.append(parser.getType());
				buf.append(",");
				buf.append(responseBody);
				int len = buf.length();
				buf.insert(0, ":");
				buf.insert(0, len);
				String response = buf.toString();
				try{
					this.out.write(response.getBytes());
					this.out.flush();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}else
			{
				try{
					this.out.write("end".getBytes());
					this.out.flush();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		try {
			this.out.close();
			this.in.close();
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

