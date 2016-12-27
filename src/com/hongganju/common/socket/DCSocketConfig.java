package com.hongganju.common.socket;

public class DCSocketConfig {
	//发方缓存
	static final public int SEND_BUFFER_SIZE = 100*1024;
	//收方缓存
	static final public int RECV_BUFFER_SIZE = 2*1024*1024;
	
	public static int innerPort = 13423;
}
