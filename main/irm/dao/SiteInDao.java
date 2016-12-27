package irm.dao;

import irm.entity.SiteInEntity;

/**
 * 网站资源引入分析SQL
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class SiteInDao {

	 // 从woaul获取数据
	public String getSelectSQL(int instanceId)
	{
		String getSQL = 
				"SELECT " +
				"a.serverAreaId AS areaid," +
				"a.serverISPId AS ispid," +
				"a.domain_cnt AS domain_cnt," +
				"a.url_cnt AS url_cnt," +
				"a.url_size AS url_size," +
				"a.ava_cache_resource_cnt AS ava_cache_resource_cnt," +
				"CAST(a.ava_cache_resource_cnt / b.isCached_sum * 100 AS DECIMAL(6,3)) AS ava_cache_resource_cnt_rate," +
				"CAST(a.ava_cache_resource_size/b.contentBytes_sum *100 AS DECIMAL(6,3)) AS ava_cache_resource_size_rate," +
				"CAST(a.ava_cache_resource_cnt/a.isCached_part_sum * 100 AS DECIMAL(6,3)) AS cache_hit_rate " +
				"FROM " +
				"(" +
				"SELECT " +
				"LEFT(serverAreaId,4) AS serverAreaId," +
				"serverISPId," +
				"COUNT(DISTINCT domain) AS domain_cnt," +
				"COUNT(DISTINCT url) AS url_cnt," +
				"SUM(IF((isCached=1),1,0)) AS ava_cache_resource_cnt," +
				"SUM(IF((isCached IS NULL OR isCached IS NOT NULL),1,0)) AS isCached_part_sum," +
				"SUM(IF((isCached=1),contentBytes,0)) AS ava_cache_resource_size ," +
				"CAST(SUM(contentBytes)/1024 AS DECIMAL(10,0)) AS url_size " +
				"FROM woaurl " +
				"WHERE InstanceId='"+ instanceId + "' " +
				"AND domain !='' " +
				"AND domain IS NOT NULL " +
				"AND serverAreaId IS NOT NULL " +
				"AND serverAreaId !='' " +
				"AND serverISPId !='' " +
				"AND serverISPId IS NOT NULL " +
				"GROUP BY LEFT(serverAreaId,4),serverISPId " +
				") a," +
				"(" +
				"SELECT " +
				"SUM(contentBytes) AS contentBytes_sum ," +
				"COUNT(isCached) AS isCached_sum " +
				"FROM woaurl " +
				"WHERE InstanceId='"+ instanceId + "' " +
				"AND domain !='' " +
				"AND domain IS NOT NULL " +
				"AND serverAreaId !='' " +
				"AND serverAreaId IS NOT NULL " +
				"AND serverISPId !='' " +
				"AND serverISPId IS NOT NULL " +
				") b ";
						    	
		return getSQL;
	}
	
	// 保存数据到w_site_in
	public String getInsertSQL(SiteInEntity sie)
	{
		String insertSQL = "insert into w_site_in " + "( " 
		        + "areaid,"
				+ "site," 
		        + "domain_cnt,"
				+ "url_cnt," 
				+ "url_size,"
				+ "ava_cache_resource_cnt,"
				+ "ava_cache_resource_cnt_rate,"
				+ "ava_cache_resource_size_rate,"
				+ "cache_hit_rate,"
		        + "ispid ) "
				+ "value " + "('"
				+ sie.getAreaid()
				+ "','"
				+ sie.getSite()
				+ "','"
				+ sie.getDomain_cnt()
				+ "','"
				+ sie.getUrl_cnt()
				+ "','"
				+ sie.getUrl_size()
				+ "','"
				+ sie.getAva_cache_resource_cnt()
				+ "','"
				+ sie.getAva_cache_resource_cnt_rate()
				+ "','"
				+ sie.getAva_cache_resource_size_rate()
				+ "','"
				+ sie.getCache_hit_rate()
				+ "','"
				+ sie.getIspid()
				+ "')";
		return insertSQL;
	}
}
