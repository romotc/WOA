package com.hongganju.common.socket;

//服务器端处理器
public interface MessageDistributer {
	public String process(int type, String body, String ip, int port);
}
