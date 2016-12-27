/**
 * 
 */
package irm.dao;

import irm.entity.SiteDetailEntity;

/**
 * 网站库明细SQL
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class SiteDetailDao {
	     	// 从数据库获取数据的SQL
			public String getSelectSQL(int instanceId)
			{
				String getSQL = 
						"SELECT " +
						"a.areaid AS areaid," +
						"a.ispid AS ispid," +
						"a.domain_netin_cnt AS domain_netin_cnt," +
						"a.url_netin_cnt AS url_netin_cnt," +
						"a.netin_size AS domain_netin_size," +
						"a.netin_size AS url_netin_size," +
						"b.domain_cnt AS domain_cnt," +
						"b.url_cnt AS url_cnt," +
						"b.size AS domain_size," +
						"b.size AS url_size," +
						"CAST(a.domain_netin_cnt/b.domain_cnt * 100 AS DECIMAL(6,3)) AS domain_netin_rate," +
						"CAST(a.url_netin_cnt/b.url_cnt * 100 AS DECIMAL(6,3)) AS url_netin_rate ," +
						"CAST(a.netin_size/b.size * 100 AS DECIMAL(6,3)) AS domain_netin_size_rate," +
						"CAST(a.netin_size/b.size * 100 AS DECIMAL(6,3)) AS url_netin_size_rate " +
						"FROM " +
						"(" +
						"SELECT " +
						"LEFT(serverAreaId,4) AS areaid," +
						"serverISPId AS ispid," +
						"COUNT(DISTINCT domain) AS domain_netin_cnt," +
						"COUNT(DISTINCT url) AS url_netin_cnt," +
						"CAST(SUM(contentBytes)/1024 AS DECIMAL(10,0)) AS netin_size " +
						"FROM woaurl " +
						"WHERE " +
						"InstanceId = '" + instanceId +"' " +
						"AND serverAreaId != '' " +
						"AND serverAreaId IS NOT NULL " +
						"AND domain != '' " +
						"AND domain IS NOT NULL " +
						"AND serverISPId != '' " +
						"AND serverISPId IS NOT NULL " +
						"GROUP BY LEFT(serverAreaId,4) ,serverISPId" +
						") a," +
						"(" +
						"SELECT " +
						"COUNT(DISTINCT domain) AS domain_cnt," +
						"COUNT(DISTINCT url) AS url_cnt," +
						"CAST(SUM(contentBytes)/1024 AS DECIMAL(10,0)) AS size " +
						"FROM woaurl " +
						"WHERE " +
						"InstanceId = '" + instanceId +"' " +
						"AND serverAreaId != '' " +
						"AND serverAreaId IS NOT NULL " +
						"AND domain != '' " +
						"AND domain IS NOT NULL " +
						"AND serverISPId != '' " +
						"AND serverISPId IS NOT NULL " +
						") b ";
				return getSQL;
			}
			// 插入数据到指定表的SQL
			public String getInsertSQL(SiteDetailEntity sde)
			{
				String insertSQL = "insert into w_site_detail " + "( " 
						+ "areaid,"
				        + "ispid,"
				        + "domain_level,"
				        + "domain_cnt,"
				        + "domain_netin_cnt,"
				        + "domain_netin_rate,"
				        + "url_cnt,"
				        + "url_netin_cnt,"
				        + "url_netin_rate,"
				        + "domain_size,"
				        + "domain_netin_size,"
				        + "domain_netin_size_rate,"
				        + "url_size,"
				        + "url_netin_size,"
				        + "url_netin_size_rate,"
				        + "site ) "
						+ "value " + "('"
						+ sde.getAreaid()
						+ "','"
						+ sde.getIspid()
						+ "','"
						+ sde.getDomain_level()
						+ "','"
						+ sde.getDomain_cnt()
						+ "','"
						+ sde.getDomain_netin_cnt()
						+ "','"
						+ sde.getDomain_netin_rate()
						+ "','"
						+ sde.getUrl_cnt()
						+ "','"
						+ sde.getUrl_netin_cnt()
						+ "','"
						+ sde.getUrl_netin_rate()
						+ "','"
						+ sde.getDomain_size()
						+ "','"
						+ sde.getDomain_netin_size()
						+ "','"
						+ sde.getDomain_netin_size_rate()
						+ "','"
						+ sde.getUrl_size()
						+ "','"
						+ sde.getUrl_netin_size()
						+ "','"
						+ sde.getUrl_netin_size_rate()
						+ "','"
						+ sde.getSite()
						+ "')";
				return insertSQL;
			}
}

