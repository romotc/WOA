/**
 * 
 */
package irm.dao;

import irm.entity.SiteViewEntity;

/**
 * 网站库概览SQL
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class SiteViewDao {

	// 从数据库获取数据的SQL
			public String getSelectSQL(int instanceId)
			{
				String getSQL = "SELECT "
						+ "serverISPId AS ispid,"
						+ "left(serverAreaId,4) AS areaid "
						+ "FROM woaurl " 
						+ "WHERE InstanceId='"
						+ instanceId + "' " 
						+ "AND serverAreaId != '' "
						+ "AND serverAreaId IS NOT NULL "
						+ "AND serverISPId != '' "
						+ "AND serverISPId IS NOT NULL "
						+ "GROUP BY left(serverAreaId,4),serverISPId ";
				return getSQL;
			}
			// 插入数据到指定表的SQL
			public String getInsertSQL(SiteViewEntity sve)
			{
				String insertSQL = "insert into w_site_v " + "( " 
						+ "areaid,"
				        + "ispid ) "
						+ "value " + "('"
						+ sve.getAreaid()
						+ "','"
						+ sve.getIspid()
						+ "')";
				return insertSQL;
			}
}
