/**
 * 
 */
package irm.dao;

import irm.entity.DomainViewEntity;

/**
 * 域名库概览SQL
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class DomainViewDao {
	            // 从数据库获取数据的SQL
				public String getSelectSQL(int instanceId)
				{
					String getSQL = 
							"SELECT " +
							"a.areaid AS areaid," +
							"a.ispid AS ispid," +
							"a.domain AS domain," +
							"b.url_cnt AS url_cnt," +
							"b.url_size AS url_size," +
							"a.netin_url_cnt AS netin_url_cnt," +
							"a.netin_url_size AS netin_url_size," +
							"CAST(a.netin_url_cnt/b.url_cnt * 100 AS DECIMAL(6,3)) AS netin_url_resource_rate," +
							"CAST(a.netin_url_size/b.url_size * 100 AS DECIMAL(6,3)) AS netin_url_resource_size_rate " +
							"FROM " +
							"(" +
							"SELECT " +
							"LEFT(serverAreaId,4) AS areaid," +
							"serverISPId AS ispid," +
							"domain," +
							"COUNT(DISTINCT url) AS netin_url_cnt," +
							"CAST(SUM(contentBytes)/1024 AS DECIMAL(10,0)) AS netin_url_size " +
							"FROM woaurl " +
							"WHERE " +
							"InstanceId = '"+ instanceId +"' " +
							"AND serverAreaId != '' " +
							"AND serverAreaId IS NOT NULL " +
							"AND domain != '' " +
							"AND domain IS NOT NULL " +
							"AND serverISPId != '' " +
							"AND serverISPId IS NOT NULL " +
							"GROUP BY domain" +
							") a," +
							"(" +
							"SELECT " +
							"COUNT(DISTINCT url) AS url_cnt," +
							"CAST(SUM(contentBytes)/1024 AS DECIMAL(10,0)) AS url_size " +
							"FROM woaurl " +
							"WHERE " +
							"InstanceId = '"+ instanceId +"' " +
							"AND serverAreaId != '' " +
							"AND serverAreaId IS NOT NULL " +
							"AND domain != '' " +
							"AND domain IS NOT NULL " +
							"AND serverISPId != '' " +
							"AND serverISPId IS NOT NULL " +
							") b  ";
					return getSQL;
				}
				// 插入数据到指定表的SQL
				public String getInsertSQL(DomainViewEntity dve)
				{
					String insertSQL = "insert into w_domain_v " + "( " 
							+ "areaid,"
					        + "ispid,"
					        + "domain,"
					        + "url_cnt,"
					        + "netin_url_cnt,"
					        + "netin_url_resource_rate,"
					        + "url_size,"
					        + "netin_url_size,"
					        + "netin_url_resource_size_rate,"
					        + "site ) "
							+ "value " + "('"
							+ dve.getAreaid()
							+ "','"
							+ dve.getIspid()
							+ "','"
							+ dve.getDomain()
							+ "','"
							+ dve.getUrl_cnt()
							+ "','"
							+ dve.getNetin_url_cnt()
							+ "','"
							+ dve.getNetin_url_resource_rate()
							+ "','"
							+ dve.getUrl_size()
							+ "','"
							+ dve.getNetin_url_cnt()
							+ "','"
							+ dve.getNetin_url_resource_size_rate()
							+ "','"
							+ dve.getSite()
							+ "')";
					return insertSQL;
				}
}
