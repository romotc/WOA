package com.hongganju.woa.spider;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;


//用于处理单domain爬行限制
//一般来说这个不用再扩展
//预先写入3倍
public class SimplePostProcessURL extends WOAPostProcessURLBase {

	@Override
	public List<URL> process(List<URL> list) {
		// TODO Auto-generated method stub
		if(this.woa == null || this.woa.totalUrl < 0)
			return list;
		List<URL> real = list;
		synchronized(this.woa)
		{
			
			int size = list.size();
			//预先写入3倍
			int left = this.woa.totalUrl - this.woa.shceduledUlrs;
			//结束，不再执行新的请求
			if(left <= 0)
			{
				real = new ArrayList<URL>();
			}
			//删除多的一部分
			else
			{
				if(left < size)
				{
//					real = list.subList(0, size - left);
					real = list.subList(0, left);
					this.woa.shceduledUlrs += left;
				}
				else
					this.woa.shceduledUlrs += size;
			}
		}
		
		return real;
	}
}
