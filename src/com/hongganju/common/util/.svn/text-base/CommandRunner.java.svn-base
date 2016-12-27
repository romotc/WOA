package com.hongganju.common.util;

import java.io.IOException;

public class CommandRunner {
	public static void runLinux(String cmd)
	{
		try {  
            Runtime rt = Runtime.getRuntime();
              String str3[] = new String[]{"sh",   "-c", cmd};
               Process   proc   =  rt.exec(str3);
               System.out.println("restart success!");
       } catch (Exception e) {  
               e.printStackTrace();
               System.out.println("something wrong!");               
   
       }
	}
}
