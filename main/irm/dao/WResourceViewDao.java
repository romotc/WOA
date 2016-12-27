package irm.dao;

import irm.entity.WResourceViewEntity;

/**
 * 资源总览SQL
 * @author	TangDican
 * @email	tangdican2008@163.com
 * @date	2013 Aug 28
 */
public class WResourceViewDao {
	
	// 从woaul获取数据
	public String getSelectSQL(int instanceId)
	{
		String getSQL = "SELECT " 
				+ "a.areaid AS areaid," 
				+ "a.ispid AS ispid," 
				+ "COUNT(DISTINCT a.taskid) AS site_cnt," 
				+ "COUNT(DISTINCT a.domain) AS domain_cnt," 
				+ "COUNT(DISTINCT a.url) AS url_cnt "
				+ "FROM " 
				+ "(" 
				+ "SELECT LEFT(serverAreaId,4) AS areaid,serverISPId AS ispid,domain,taskid,url " 
				+ "FROM woaurl WHERE InstanceId='" + instanceId + "' AND url != '' AND url IS NOT NULL " 
				+ "AND domain != '' AND domain IS NOT NULL AND serverAreaid !=''AND serverAreaid IS NOT NULL "
				+ ") a "
				+ "GROUP BY a.areaid";
		return getSQL;
	}
	
	// 保存数据到w_resource_v
	public String getInsertSQL(WResourceViewEntity wrve)
	{
		String insertSQL = "insert into w_resource_v " + "( " 
		        + "areaid,"
				+ "site_cnt," 
		        + "domain_cnt,"
				+ "url_cnt," 
		        + "ispid ) "
				+ "value " + "('"
				+ wrve.getAreaid()
				+ "','"
				+ wrve.getSite_cnt()
				+ "','"
				+ wrve.getDomain_cnt()
				+ "','"
				+ wrve.getUrl_cnt()
				+ "','"
				+ wrve.getIspid()
				+ "')";
		return insertSQL;
	}
}
