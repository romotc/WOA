package com.hongganju.woa.domain;

public class DomainStat {
	String domain;
	
	public DomainStat(String domain)
	{
		System.out.println("开始统计:" + domain);
		this.domain = domain;
	}
	
	public void processURL(String url)
	{
		System.out.println(url);
	}
}
