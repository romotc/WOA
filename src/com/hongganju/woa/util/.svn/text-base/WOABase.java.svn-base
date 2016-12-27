package com.hongganju.woa.util;

import lotus.alg.compress.Base64;

public class WOABase {
	public static void main(String[] args)
	{
		String s = "http://hz.youku.com/red/click.php?tp=1&cp=4005177&cpp=1000404&url=http://u.youku.com/my_info/type_basic.html";
		System.out.println(encodeBase64UTF8(s));
	}
	public static String encodeBase64UTF8(String msg)
	{
		
		try {
			String encoded = Base64.encode(msg.getBytes("utf8"));
			String res = encoded.replaceAll("=", "-");
			return res;
		}catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return msg;
	}
	
	public static String decodeBase64UTF8(String base64)
	{
		String b = base64.replaceAll("-", "=");
		try{
			byte[] bytes = Base64.decode(b);
			String res = new String(bytes, "utf8");
			return res;
		}catch(Exception e)
		{
			
		}
		
		return base64;
	}
}
