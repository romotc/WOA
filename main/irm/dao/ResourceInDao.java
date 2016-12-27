/**
 * 
 */
package irm.dao;

import irm.entity.ResourceInEntity;

/**
 * 各省本网资源引入分析SQL
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class ResourceInDao {

	    // 从woaul获取数据
		public String getSelectSQL(int instanceId)
		{
			String getSQL = "SELECT " 														// 第一层
								+ "da.areaid AS areaid,"
								+ "da.ispid,"
								+ "da.domain_in_cnt,"
								+ "da.site_in_cnt,"
								+ "da.url_in_cnt,"
								+ "da.domain_net_in_rate,"
								+ "CAST(da.domain_in_size/1024 AS DECIMAL(10,0)) as domain_in_size,"
								+ "da.domain_net_in_size_rate,"
								+ "CAST(db.site_in_size/1024 AS DECIMAL(10,0)) AS site_in_size,"
								+ "CAST(dc.url_in_size/1024 AS DECIMAL(10,0)) AS url_in_size "
						+ "FROM "
							    + "(SELECT " 												// 第二层
							    	+ "ca.areaid AS areaid,"
							    	+ "ca.ispid,"
							    	+ "ca.domain_in_cnt,"
							    	+ "ca.site_in_cnt,"
							    	+ "ca.url_in_cnt,"
							    	+ "ca.domain_net_in_rate,"
							    	+ "cb.domain_in_size,"
							    	+ "cb.domain_net_in_size_rate " 
							    + "FROM "
							    	+ "(SELECT " 											// 第三层
							    		+ "ba.areaid AS areaid,"
							    		+ "ba.ispid AS ispid,"
							    		+ "ba.domain_in_cnt AS domain_in_cnt,"
							    		+ "ba.site_in_cnt AS site_in_cnt,"
							    		+ "ba.url_in_cnt AS url_in_cnt,"
							    		+ "CAST("
							    		+ "ba.domain_in_cnt / bb.domain_sum * 100 AS DECIMAL (6, 3)"
							    			+") AS domain_net_in_rate "
							    	+ "FROM "
							    		+ "(SELECT " 										// 第四层
							    			+ "LEFT(a.serverAreaId, 4) AS areaid,"
							    			+ "a.serverISPId AS ispid,"
							    			+ "COUNT(DISTINCT a.domain) AS domain_in_cnt,"
							    			+ "COUNT(DISTINCT a.taskid) AS site_in_cnt,"
							    			+ "COUNT(DISTINCT a.url) AS url_in_cnt " 
							    		+ "FROM "
							    			+ "woaurl a " 
							    		+ "WHERE a.InstanceId = '"+ instanceId +"' " 
							    			+ "AND a.domain != '' " 
							    			+ "AND a.domain IS NOT NULL " 
							    			+ "AND a.url != '' "
							    			+ "AND a.url IS NOT NULL "
							    			+ "AND a.serverISPId != '' "
								    		+ "AND a.serverISPId IS NOT NULL " 
							    			+ "AND a.serverAreaId != '' " 
							    			+ "AND a.serverAreaId IS NOT NULL " 
							    		+ "GROUP BY LEFT(a.serverAreaId, 4),a.serverISPId) ba,"
							    		+ "(SELECT " 										// 并列第四层
							    			+ "COUNT(DISTINCT domain) AS domain_sum " 
							    		+ "FROM "
							    			+ "woaurl " 
							    		+ "WHERE InstanceId = '"+ instanceId +"' " 
							    			+ "AND url != '' " 
							    			+ "AND url IS NOT NULL " 
							    			+ "AND domain != '' " 
							    			+ "AND domain IS NOT NULL " 
							    			+ "AND serverISPId != '' "
								    		+ "AND serverISPId IS NOT NULL " 
							    			+ "AND serverAreaId != '' "
							    			+ "AND serverAreaId IS NOT NULL) bb) ca,"
							    	+ "(SELECT " 											// 并列第三层
							    		+ "bca.areaid AS areaid,"
							    		+ "bca.ispid AS ispid,"
							    		+ "bca.domain_in_size AS domain_in_size,"
							    			+ "CAST( "
							    					+ "("
							    						+ "bca.domain_in_size / bcb.contentBytes"
							    					+ ") * 100 AS DECIMAL (6, 3)"
							    				+ ") AS domain_net_in_size_rate " 
							    	+ "FROM "
							    		+ "(SELECT "										// 并列第四层 
							    			+ "bc.areaid AS areaid,"
							    			+ "bc.ispid AS ispid,"
							    			+ "SUM(bc.contentBytes) AS domain_in_size " 
							    		+ "FROM "
							    			+ "(SELECT "									// 第五层
							    				+ "SUM(contentBytes) AS contentBytes,"
							    				+ "LEFT(serverAreaId, 4) AS areaid, "
							    				+ "serverISPId AS ispid "
							    			+ "FROM "
							    				+ "woaurl " 
							    			+ "WHERE InstanceId = '"+ instanceId +"' " 
							    				+ "AND url != '' " 
							    				+ "AND url IS NOT NULL " 
							    				+ "AND domain != '' " 
							    				+ "AND domain IS NOT NULL " 
							    				+ "AND serverISPId != '' "
									    		+ "AND serverISPId IS NOT NULL " 
							    				+ "AND serverAreaId != '' "
							    				+ "AND serverAreaId IS NOT NULL " 
							    			+ "GROUP BY LEFT(serverAreaId, 4),serverISPId,"
							    				+ "domain) bc " 
							    		+ "GROUP BY bc.areaid,bc.ispid) bca,"
							    		+ "(SELECT "										// 并列第四层
							    			+ "SUM(ALL contentBytes) AS contentBytes "
							    		+ "FROM "
							    			+ "woaurl " 
							    		+ "WHERE InstanceId = '"+ instanceId +"' " 
							    			+ "AND url != '' " 
							    			+ "AND url IS NOT NULL " 
							    			+ "AND domain != '' " 
							    			+ "AND domain IS NOT NULL " 
							    			+ "AND serverISPId != '' "
								    		+ "AND serverISPId IS NOT NULL " 
							    			+ "AND serverAreaId != '' " 
							    			+ "AND serverAreaId IS NOT NULL) bcb) cb " 
							    + "WHERE ca.areaid = cb.areaid) da, "							
							    + "(SELECT " 												// 并列第二层
							    	+ "cc.areaid AS areaid,"
							    	+ "cc.ispid AS ispid,"
							    	+ "SUM(cc.contentBytes) AS site_in_size " 
							    + "FROM "
							    	+ "(SELECT " 											// 并列第三层
							    		+ "SUM(contentBytes) AS contentBytes,"
							    		+ "LEFT(serverAreaId, 4) AS areaid, "
							    		+ "serverISPId AS ispid "
							    	+ "FROM "
							    		+ "woaurl " 
							    	+ "WHERE InstanceId = '"+ instanceId +"' " 
							    		+ "AND url != '' " 
							    		+ "AND url IS NOT NULL " 
							    		+ "AND domain != '' " 
							    		+ "AND domain IS NOT NULL " 
							    		+ "AND serverISPId != '' "
							    		+ "AND serverISPId IS NOT NULL " 
							    		+ "AND serverAreaId != '' " 
							    		+ "AND serverAreaId IS NOT NULL " 
							    	+ "GROUP BY LEFT(serverAreaId, 4),serverISPId,"
							    		+ "taskid) cc " 
							    + "GROUP BY cc.areaid,cc.ispid) db,"
							    + "(SELECT " 												// 并列第二层
							    	+ "cd.areaid AS areaid,"
							    	+ "cd.ispid AS ispid,"
							    	+ "SUM(cd.contentBytes) AS url_in_size " 
							    + "FROM "
							    	+ "(SELECT "											// 并列第三层
							    		+ "SUM(contentBytes) AS contentBytes,"
							    		+ "LEFT(serverAreaId, 4) AS areaid,"
							    		+ "serverISPId AS ispid "
							    	+ "FROM "
							    		+ "woaurl " 
							    	+ "WHERE InstanceId = '"+ instanceId +"' "
							    		+ "AND url != '' "
							    		+ "AND url IS NOT NULL "
							    		+ "AND domain != '' "
							    		+ "AND domain IS NOT NULL " 
							    		+ "AND serverISPId != '' "
							    		+ "AND serverISPId IS NOT NULL " 
							    		+ "AND serverAreaId != '' "
							    		+ "AND serverAreaId IS NOT NULL " 
							    	+ "GROUP BY LEFT(serverAreaId, 4),serverISPId,"
							    		+ "url) cd "
							    + "GROUP BY cd.areaid,cd.ispid) dc "
							 + "WHERE da.areaid = db.areaid AND da.areaid = dc.areaid AND da.ispid = db.ispid AND da.areaid = dc.areaid";
							    	
			return getSQL;
		}
		
		// 保存数据到w_resource_in
		public String getInsertSQL(ResourceInEntity rie)
		{
			String insertSQL = "insert into w_resource_in " + "( " 
			        + "areaid,"
					+ "site_in_cnt," 
			        + "domain_in_cnt,"
					+ "url_in_cnt," 
					+ "site_in_size,"
					+ "domain_in_size,"
					+ "url_in_size,"
					+ "domain_net_in_rate,"
					+ "domain_net_in_size_rate,"
			        + "ispid ) "
					+ "value " + "('"
					+ rie.getAreaid()
					+ "','"
					+ rie.getSite_in_cnt()
					+ "','"
					+ rie.getDomain_in_cnt()
					+ "','"
					+ rie.getUrl_in_cnt()
					+ "','"
					+ rie.getSite_in_size()
					+ "','"
					+ rie.getDomain_in_size()
					+ "','"
					+ rie.getUrl_in_size()
					+ "','"
					+ rie.getDomain_net_in_rate()
					+ "','"
					+ rie.getDomain_net_in_size_rate()
					+ "','"
					+ rie.getIspid()
					+ "')";
			return insertSQL;
		}
}
