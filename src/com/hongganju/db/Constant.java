package com.hongganju.db;

public class Constant {
	   //查询操作
    public static final String QUERY_LIKE = "like";//相似查询
    public static final String QUERY_LESS_THAN = "lt";//小于
    public static final String QUERY_LESS_OR_EQUAL = "le";//小于等于
    public static final String QUERY_GREATER_OR_EQUAL = "ge";//大于等于
    public static final String QUERY_GREATER_THAN = "gt";//大于
    public static final String QUERY_GREATER_BETWEEN = "between";//两个值的区间之内
    //数据库查询操作

    //查询排序方式
    public static final String ORDER_ASC = "asc";//升序排序
    public static final String ORDER_DESC = "desc";//降序排序
    //查询排序方式
    
    //导入的覆盖方式
    public static final int ALLUPDATE = 1;//全覆盖
    public static final int GROUPUPDATE = 2;//组内覆盖
    public static final int NOUPDATE = 3;//不覆盖
}
