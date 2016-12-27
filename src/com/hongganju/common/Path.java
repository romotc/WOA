package com.hongganju.common;

public class Path {
	static public String path;
	static private String servletPath;
	static private String catalina_home = null;
	public static void setServletPath(String path)
	{
		servletPath = path;
	}

	public static String getCatalinaHome()
	{
		if(catalina_home == null)
			catalina_home = Path.getPath() + "/../";
		return catalina_home;
	}
	public static String getServletPath()
	{
		return servletPath;
	}
	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		Path.path = path;
	}
}
