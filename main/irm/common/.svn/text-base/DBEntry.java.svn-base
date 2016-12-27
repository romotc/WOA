package irm.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import statistic.DataManager;
import statistic.PropertyUtil;

/**
 * @author	TangDican
 * @email	tangdican2008@163.com
 * @date	2013 Aug 28
 */
public class DBEntry {
	// 工具类实例化
	static PropertyUtil propertyUtil=new PropertyUtil();
	// 获取数据库名称
	static String url=propertyUtil.getProperty("db.url");
	// 获取数据库用户
	static String username=propertyUtil.getProperty("db.user"); 
	// 获取数据库密码
	static String password=propertyUtil.getProperty("db.password");
	
	// 数据库登录
	public DataManager DBlogin()
	{
		DataManager dm = new DataManager(url,username,password);
		return dm;
	}

	// 指定dbid的数据库登录
	public DataManager TaskDBByID(short id)
	{
		// 从表中获取帐号信息
		String sql = "select url,username,password from dbmanagerread where id = '"+id+"'";
		DataManager manage= new DataManager(url,username,password);
		DataManager manages = null;
		try {
			// 执行SQL
			ResultSet rs = manage.executeQuery(sql);
			// 获取指定数据库的帐号信息
			while(rs.next()){
				String urlmore = rs.getString(1);
				String usernamemore  = rs.getString(2);
				String passwordmore  = rs.getString(3);
				// 登录数据库
				manages = new DataManager(urlmore ,usernamemore ,passwordmore);
			}
			// 关闭
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(manage != null)
				manage.closeConn();
		}
		return manages;
	}
}
