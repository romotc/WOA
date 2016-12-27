/**
 * 
 */
package irm.dao;

import irm.entity.ProvinceSiteViewEntity;

/**
 * 分区域网站概览SQL
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class ProvinceSiteViewDao {

	 // 从woaul获取数据
	public String getSelectSQL(int instanceId)
	{
		String getSQL = 
				"SELECT " +
				"a.areaid AS areaid," +
				"a.domain_in_cnt AS domain_in_cnt," +
				"a.url_in_cnt AS url_in_cnt," +
				"a.ispid AS ispid," +
				"CAST(a.domain_net_in_size/b.domain_net_in_size_sum * 100 AS DECIMAL(6,3)) AS domain_net_in_depth," +
				"CAST(a.domain_in_cnt/b.domain_sum * 100 AS DECIMAL(6,3)) AS domain_net_in_rate " +
				"FROM " +
				"(" +
				"SELECT " +
				"LEFT(serverAreaId,4) AS areaid," +
				"COUNT(DISTINCT domain) AS domain_in_cnt," +
				"COUNT(DISTINCT url) AS url_in_cnt," +
				"serverISPId AS ispid," +
				"SUM(contentBytes) AS domain_net_in_size " +
				"FROM woaurl " +
				"WHERE InstanceId='"+ instanceId +"' " +
				"AND serverAreaId IS NOT NULL " +
				"AND serverAreaId !='' " +
				"AND domain != '' " +
				"AND domain IS NOT NULL " +
				"AND serverISPId != '' "+ 
				"AND serverISPId IS NOT NULL "+ 
				"GROUP BY LEFT(serverAreaId,4),serverISPId ) a," +
				"(" +
				"SELECT " +
				"COUNT(DISTINCT domain) AS domain_sum," +
				"SUM(contentBytes) AS domain_net_in_size_sum " +
				"FROM woaurl " +
				"WHERE InstanceId='"+ instanceId +"' " +
				"AND serverAreaId IS NOT NULL " +
				"AND serverAreaId !='' " +
				"AND domain != '' " +
				"AND domain IS NOT NULL " +
				"AND serverISPId IS NOT NULL " +
				"AND serverISPId != '') b" ;
		return getSQL;
	}
	
	        // 保存数据到w_province_site_v
			public String getInsertSQL(ProvinceSiteViewEntity psve)
			{
				String insertSQL = "insert into w_province_site_v " + "( " 
				        + "areaid,"
						+ "site," 
				        + "domain_in_cnt,"
						+ "url_in_cnt," 
						+ "domain_net_in_rate,"
						+ "domain_net_in_depth,"
				        + "ispid ) "
						+ "value " + "('"
						+ psve.getAreaid()
						+ "','"
						+ psve.getSite()
						+ "','"
						+ psve.getDomain_in_cnt()
						+ "','"
						+ psve.getUrl_in_cnt()
						+ "','"
						+ psve.getDomain_net_in_rate()
						+ "','"
						+ psve.getDomain_net_in_depth()
						+ "','"
						+ psve.getIspid()
						+ "')";
				return insertSQL;
			}
}
