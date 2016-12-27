package statistic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
	private Connection conn = null;

	public DataManager() {
	}

	public DataManager(String url, String username, String password) {
		initConn(url, username, password);
	}
	
	protected void initConn(String url, String username, String password) {
		try {
			conn = getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("initConn:" + url);
	}

	private static Connection getConnection(String url, String username,
			String password) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 用JDBC查询sql语句
	 * 
	 * @param sql
	 * @return
	 */
	public ResultSet executeQuery(String sql) {
		ResultSet resultSet = null;
		try {
			Statement statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;

	}

	public boolean execute(String sql) {
		boolean result = true;
		try {
			Statement statement = conn.createStatement();
			result = statement.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * 用JDBC插入数据
	 * 
	 * @param sql
	 * @return
	 */
	public void insert(String sql) {
		try {
			Statement statement = conn.createStatement();
			statement.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 用JDBC创建表
	 * 
	 * @param sql
	 * @return
	 */
	public void createdb(String sql) {
		try {
			Statement statement = conn.createStatement();
			statement.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 用JDBC查询sql语句
	 * 
	 * @param sql
	 * @return
	 */
	public List<ResultSet> executeQuery(String[] sqls) {
		List<ResultSet> sets = new ArrayList<ResultSet>();
		try {
			for (String sql : sqls) {
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);
				sets.add(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sets;
	}

	/**
	 * 用JDBC查询sql语句
	 * 
	 * @param sql
	 * @return
	 */
	public List<ResultSet> executeQuery(List<String> sqls) {
		return executeQuery(sqls.toArray(new String[0]));
	}

	/**
	 * 用JDBC更新sql语句
	 * 
	 * @param sql
	 * @return
	 */
	public int executeUpdate(String sql) {
		int result = 0;
		try {
			Statement statement = conn.createStatement();
			result = statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 关闭连接
	 */
	public void closeConn() {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
