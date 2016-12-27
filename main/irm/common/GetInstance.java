package irm.common;
/**
 * 获取指定instanceid实例的数据
 * @author	TangDican
 * @email	tangdican2008@163.com
 * @date	2013 Aug 28
 */
import java.sql.ResultSet;
import java.sql.SQLException;

import statistic.DataManager;

import irm.entity.InstanceEntity;

public class GetInstance {
	// 实例化类
	InstanceEntity ie = new InstanceEntity();
	DBEntry dbe = new DBEntry();
	DataManager manage= dbe.DBlogin();
	// 从数据库表中获取数据
	public InstanceEntity getInstance(int instanceId)
	{
		String sql = 
				"SELECT " +
				"cc.taskid AS taskid," +
				"cc.WebName AS webName," +
				"cc.taskType AS TYPE," +
				"cc.DBID AS dbId ," +
				"bb.TaskinstanceId AS TaskinstanceId " +
				"FROM " +
				"(" +
				"SELECT " +
				"MAX(a.TaskinstanceId) AS TaskinstanceId " +
				"FROM " +
				"woainstance a ," +
				"(" +
				"SELECT " +
				"taskid " +
				"FROM woainstance  " +
				"WHERE TaskinstanceId = '"+ instanceId + "'" +
				") b " +
				"WHERE a.taskid=b.taskid " +
				") aa," +
				"woainstance bb," +
				"woatask cc " +
				"WHERE " +
				"bb.TaskinstanceId = aa.TaskinstanceId " +
				"AND bb.taskid=cc.taskid ";
		try {
			// 执行SQL
			ResultSet rs = manage.executeQuery(sql);
			if (rs.next()) {
				// 获取值
				ie.setTaskId(Integer.parseInt(rs.getString("taskid")));
				ie.setWebName(rs.getString("webName"));
				ie.setDbId(Short.parseShort(rs.getString("dbId")));
				ie.setType(Integer.parseInt(rs.getString("type")));
				ie.setTaskinstanceId(Integer.parseInt(rs.getString("TaskinstanceId")));
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (manage != null)
				manage.closeConn();
		}
		return ie;
	}
}
