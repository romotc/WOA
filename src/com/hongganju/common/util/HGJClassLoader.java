package com.hongganju.common.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;


//我的ClassLoader
public class HGJClassLoader extends ClassLoader {

    private static String MyClasspath = new String("");
    
    private static Hashtable<String, Class> loadClassHashTable = new Hashtable<String, Class>();

    /** *//**
     * 供JasonDataAutoGenerator调用，返回已经load过的class
     */
    static public Class getClassByName(String name)
    {
    	return loadClassHashTable.get(name);
    }
    

    /** *//**
     * 构造自定义的加载器 参数1：路径名
     */
    public HGJClassLoader(String MyClasspath) {
        if (!MyClasspath.endsWith("\\")) {
            MyClasspath = MyClasspath + "\\";
        }
        this.MyClasspath = MyClasspath;
    }

    public Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class klass = null;
        try {
            klass = findLoadedClass(name); //检查该类是否已经被装载。
            if (klass != null) {
                return klass;  
            }
           
            byte[] bs = getClassBytes(name);//从一个特定的信息源寻找并读取该类的字节。
            if (bs != null && bs.length > 0) {
                klass = defineClass(name, bs, 0, bs.length);  
            }
            if (klass == null) { //如果读取字节失败，则试图从JDK的系统API中寻找该类。
                klass = findSystemClass(name);
            }
            if (resolve && klass != null) {
                resolveClass(klass);  
            }
        } catch (IOException e) {
            throw new ClassNotFoundException(e.toString());
        }  
        System.out.println("klass == " + klass);
        return klass;
    }  
    private byte[] getClassBytes(String className) throws IOException {
    	FileInputStream fis = null;
        try {
            fis = new FileInputStream(MyClasspath + File.separatorChar + className + ".class");
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return null;   //如果查找失败，则放弃查找。捕捉这个异常主要是为了过滤JDK的系统API。
        }
        byte[] bs = new byte[fis.available()];
        fis.read(bs);
        return bs;
    }

    public static void main(String[] args) {
    	HGJClassLoader cl = new HGJClassLoader("E:\\testClass");
        try {
            // 从tools.jar包中加载ContentSigner类
        	Class c = cl.loadClass("TestTime", false);
        	System.out.println(c.getPackage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
