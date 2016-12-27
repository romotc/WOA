package com.hongganju.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;


public class RandomUtil {
	private static Logger logger = Logger.getLogger(RandomUtil.class); 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		long t1 = System.currentTimeMillis();
//		for(int i=0; i<9999;i++)
//		{
//			List<Integer> list = getRandom(200, 100);
//		}
//		long t2 = System.currentTimeMillis();
//		System.err.println((t2-t1) + "ms");
		
		List<Integer> list = getRandom(20, 3);
		
		
		HashMap<Integer, String> filter = new HashMap<Integer, String>();
		String str = "";
		for(int i=0; i< list.size(); i++)
		{
			str = str + list.get(i) + ", ";
//			System.out.println(list.get(i));
			if(filter.containsKey(list.get(i)))
			{
				System.err.println("重复：" + list.get(i));
			}else
				filter.put(list.get(i), null);
		}
		System.out.println(str);
	}
	static public List<Integer> getRandom(int scope, int number)
	{
		return getRandom(0, scope, number);
	}
	static public List<Integer> getRandom(int start, int end, int number)
	{
		/*
		LinkedList<Integer> list = new LinkedList<Integer>();
		List<Integer> l = new ArrayList<Integer>(); 
		
		int all = end - start;
		//init
		for(int i=start; i< end; i++)
		{
			list.add(i);
		}
		//generate
		for(int i=0; i<number; i++)
		{
			int randomNum = all - number;
			Integer random = new Random().nextInt(randomNum);
			Integer v = list.get(random);
			l.add(v);
			list.remove(v);
		}
		return l;*/
		
		//拿连续数，因为全随机巨吃cpu
		List<Integer> l = new ArrayList<Integer>();
		//产生随机数
		int all = end - start;
		Random random = new Random();
		int index = random.nextInt(all);
		logger.debug("随机数：" + index);
		index = (index + start)%all;
		//数量
		for(int i=0; i<number; i++)
		{
			logger.debug("随机数：" + i);
			l.add(index);
			index++;
			if(index == end)
				index = start;
		}
//		System.out.println("start=" + start + ", end=" + end + ", number=" + number);
//		System.out.println(l.size());
		return l;
	}
}
