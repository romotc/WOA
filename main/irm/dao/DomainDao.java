/**
 * 
 */
package irm.dao;

import irm.entity.DomainEntity;

/**
 * 域名明细SQL
 * @author	TangDican
 * @date	2013 Sep 4, 2013
 * @email	tangdican2008@163.com
 */
public class DomainDao {

	// 从数据库获取数据的SQL
			public String getSelectSQL(int instanceId)
			{
				String getSQL = "SELECT "
						+ "domain,"
						+ "serverIp as ip "
						+ "FROM woaurl " 
						+ "WHERE InstanceId='"
						+ instanceId + "' " 
						+ "AND serverAreaId != '' " +
						"AND serverAreaId IS NOT NULL " +
						"AND domain != '' " +
						"AND domain IS NOT NULL " +
						"AND serverISPId != '' " +
						"AND serverISPId IS NOT NULL " +
						"GROUP BY domain";
				return getSQL;
			}
			// 插入数据到指定表的SQL
			public String getInsertSQL(DomainEntity de)
			{
				String insertSQL = "insert into w_domain " 
						+ "( " 
				        + "domain,"
				        + "ip ) "
						+ "value " + "('"
						+ de.getDomain()
						+ "','"
						+ de.getIp()
						+ "')";
				return insertSQL;
			}
}
