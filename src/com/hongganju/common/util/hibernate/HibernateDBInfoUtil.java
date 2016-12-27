package com.hongganju.common.util.hibernate;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HibernateDBInfoUtil {
	static public SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static public String createPreparedSQL(Object obj, HibernateDBInfo info)
	{
		if(obj == null)
			return null;
		
		String className = obj.getClass().getName();
		List<HibernateField> fieldList = info.getDBInfoByClassName(className);
		String tableName = info.getTableNameByClassName(className);
		if(fieldList == null)
			return null;
		try {
			return createPreparedInsert(obj, fieldList, tableName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	static public String createSQL(Object obj, HibernateDBInfo info)
	{
		if(obj == null)
			return null;
		
		String className = obj.getClass().getName();
		List<HibernateField> fieldList = info.getDBInfoByClassName(className);
		String tableName = info.getTableNameByClassName(className);
		if(fieldList == null)
			return null;
		try {
			return refectionInsert(obj, fieldList, tableName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static String createPreparedInsert(Object obj, List<HibernateField> fieldList, String tableName) throws SecurityException, Exception{
		if(obj == null || fieldList == null || tableName == null || tableName.trim().equals(""))
			return null;
		
		//针对一个Object内容
		Method getMethod = null;
		
		StringBuffer sql = new StringBuffer();
		StringBuffer value = new StringBuffer();
		sql.append("insert into ").append(tableName).append(" (");
		value.append(" values (");
		
		for(int i = 0; i < fieldList.size(); i++){
			//System.out.println(o.getClass());
			if(i == 0){
				sql.append(fieldList.get(i).dbFieldName);
				value.append("?");
			}else{
				sql.append(",").append(fieldList.get(i).dbFieldName);
				value.append(",?");
			}		
		}
		
		sql.append(")").append(value).append(")");
		//System.out.println(sql);
		return sql.toString();	
	}
	
	public static String refectionInsert(Object obj, List<HibernateField> fieldList, String tableName) throws SecurityException, Exception{

		if(obj == null || fieldList == null || tableName == null || tableName.trim().equals(""))
			return null;
		
		//针对一个Object内容
		Method getMethod = null;
		
		StringBuffer sql = new StringBuffer();
		StringBuffer value = new StringBuffer();
		sql.append("insert into ").append(tableName).append(" (");
		value.append(" values (");
		
		for(int i = 0; i < fieldList.size(); i++){
			getMethod = fieldList.get(i).getter;
			Object o = getMethod.invoke(obj, null);
			//System.out.println(o.getClass());
			if(i == 0){
				sql.append(fieldList.get(i).dbFieldName);
				value.append(getVlaueByReturnObj(o));
			}else{
				
				sql.append(",").append(fieldList.get(i).dbFieldName);
				value.append(",").append(getVlaueByReturnObj(o));
			}		
		}
		
		sql.append(")").append(value).append(")");
		//System.out.println(sql);
		return sql.toString();				
	}
/**
 * 根据对象的数据类型，得到相应的字符串值
 * @param returnObj
 * @return
 * @throws Exception
 */
static public String getVlaueByReturnObj(Object returnObj)
		throws Exception {

	if (returnObj == null) {
		return "null";
	} else if (returnObj.getClass().getName().equals("java.lang.String")) {
		String str = (String)returnObj;
		String str1 = "";
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) == '\''){
				str1 += "''";
			}else if(str.charAt(i) == '\r'){
				str1 += "&rtn;";
			}else if(str.charAt(i) == '\n'){
				str1 += "&nwl;";
			}else{
				str1 += str.charAt(i);
			}					
		}		
		return "'" + str1 + "'";
	} else if (returnObj.getClass().getName().equals("java.util.Date") || returnObj.getClass().getName().equals("java.sql.Date")) {
		Date date = (Date) returnObj;
		String content = dateformat.format(date);
		return "'" + content +"'";		
	} else if (returnObj.getClass().getName().equals("java.sql.Timestamp")) {
		String content = ((Timestamp) returnObj).toString();
		
		return "'" + content +"'";
	}else if(returnObj.getClass().getName().equals("java.math.BigDecimal")){
		return  ((BigDecimal)returnObj).toString();
	}else if(returnObj.getClass().getName().equals("java.lang.Short")){
		return  ((Short)returnObj).toString();
	}else if(returnObj.getClass().getName().equals("java.lang.Boolean")){
		boolean temp = (Boolean)returnObj;
		if(temp){
			return "'1'";
		}
		else{
			return "'0'";
		}
	}else if(returnObj.getClass().getName().equals("java.lang.Integer")){
		return  ((Integer)returnObj).toString();
	}else if(returnObj.getClass().getName().equals("java.lang.Float")){
		return  ((Float)returnObj).toString();
	}
	else if(returnObj.getClass().getName().equals("java.lang.Double")){
		return  ((Double)returnObj).toString();
	}else if(returnObj.getClass().getName().equals("java.lang.Long")){
		return  ((Long)returnObj).toString();
	}else if(returnObj.getClass().getName().equals("java.sql.Blob") || returnObj.getClass().getName().equals("org.hibernate.lob.SerializableBlob")){
		return  "empty_blob()";
	}else if (returnObj.getClass().getName().equals("[B")){
		return  "empty_blob()";
	}
	return null;
}
	public static void main(String[] args) {

	}
}
