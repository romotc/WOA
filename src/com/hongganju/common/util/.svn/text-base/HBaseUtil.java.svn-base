package com.hongganju.common.util;

public class HBaseUtil {
	private static Long taskexecobjectKey = (long)0;
	private static int key_count = 0;
	public static String getTaskexecbjectKey(Long uploadtime)
	{
		if(uploadtime == null)
			return null;
		if(uploadtime > taskexecobjectKey)
		{
			key_count = 0;
			taskexecobjectKey = uploadtime;
		}
		else
		{
			key_count++;
		}
		String key = taskexecobjectKey + NumberFormat.format4(key_count);
		return key;
	}
}
