/**
 * 
 */
package irm.dao;

import irm.entity.ResourceDuplicateDetailEntity;

/**
 * 全网重复资源明细表SQL
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class ResourceDuplicateDetailDao {

	 // 从数据库获取数据的SQL
	public String getSelectSQL(int instanceId)
	{
		String getSQL = "SELECT "
				+ "domain,"
				+ "url "
				+ "FROM woaurl " 
				+ "WHERE InstanceId='"
				+ instanceId + "' " 
				+ "AND domain != '' "
				+ "AND domain IS NOT NULL "
				+ "AND url != '' "
				+ "AND url IS NOT NULL "
				+ "GROUP BY url ";
		return getSQL;
	}
	// 插入数据到指定表的SQL
	public String getInsertSQL(ResourceDuplicateDetailEntity rdde)
	{
		String insertSQL = "insert into w_resource_duplicate_detail " + "( " 
				+ "site,"
		        + "domain,"
		        + "url ) "
				+ "value " + "('"
				+ rdde.getSite()
				+ "','"
				+ rdde.getDomain()
				+ "','"
				+ rdde.getUrl()
				+ "')";
		return insertSQL;
	}
}
