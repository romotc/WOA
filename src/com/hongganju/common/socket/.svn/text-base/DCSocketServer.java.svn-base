package com.hongganju.common.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class DCSocketServer {
//	private static Logger logger = Logger.getLogger(Server.class);
	private boolean isRunning = false;;
	
	private int port = DCSocketConfig.innerPort;
	//protected Processor new_processor_processor;
	//BufferedInputStream in = null;
	//BufferedOutputStream out = null;
	private Thread thread;
	private ServerSocket socket;
	public DCSocketServer()
	{
//		this.port = DCSocketConfig.innerPort;
		this.port = -1;
	}
	public DCSocketServer(int port)
	{
		this.port = port;
	}
	public void stop()
	{
		isRunning = false;
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void start()
	{
		isRunning = true;
		thread = new Thread(){
			public void run(){
//				logger.debug("open port=" + port);
				try {
					if(port == -1)
					{
						socket = new ServerSocket(getRandomPort());
					}
					else
						socket = new ServerSocket(port);
					DCSocketConfig.innerPort = socket.getLocalPort();
					System.out.println("DCSocketPort=" + DCSocketConfig.innerPort);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
//				logger.debug("Server ok");
				while(isRunning)
				{
					try {
						Socket client;
						client = socket.accept();
//						if(logger.isDebugEnabled())
//						{
//							logger.debug("accept");
//						}
						BufferedOutputStream out = 
							new BufferedOutputStream(client.getOutputStream());
						BufferedInputStream in = 
							new BufferedInputStream(client.getInputStream());
						
						//ProcessorInterface new_processor = processor.getClass().newInstance();
						DCSocketServerThread thread = new DCSocketServerThread(
									client, out, in);
						thread.start();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						if(isRunning == true)
							e.printStackTrace();
					}
					
				}
			}
		};
		thread.start();
		
		
	}
	private static int getRandomPort()
	{
		ServerSocket   tmp; 
		int base = DCSocketConfig.innerPort;
		for(int i=0; i<100; i++)
		{
			int port = base + i;
			try{
				tmp = new ServerSocket(port);
				tmp.close();
				tmp=null;
				return port;
			}catch(Exception e){}
		}
		return -1;
	}
	public static void main(String[] args)
	{
		DCSocketServer socketserver = new DCSocketServer(DCSocketConfig.innerPort);
		socketserver.start();
		
		try {
			Thread.sleep(300000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
