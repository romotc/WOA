package com.hongganju.woa.spider;

import java.util.List;
import java.net.URL;

//用于处理浏览器访问的结果，过滤，统计等等
public abstract class WOAPostProcessURLBase {
	protected WOASpider woa = null;
	public abstract List<URL> process(List<URL> list);
	public void setWOA(WOASpider woa){
		this.woa = woa;
	}
}
