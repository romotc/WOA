package irm.dao;

import irm.entity.MonitorEntity;

/**
 * 获取SQL
 * @author	TangDican
 * @date	2013 Aug 31, 2013
 * @email	tangdican2008@163.com
 */
public class MonitorDao {

	    // 从woaul获取数据
		public String getSelectSQL(int instanceId)
		{
			String getSQL = 
					  "SELECT " 
					+ "a.areaid AS areaid," 
					+ "a.ispid AS ispid," 
					+ "e.domain_sum AS domain_cnt," 
					+ "CAST(e.province_in_cnt/e.domain_sum*100 AS DECIMAL(6,3)) AS province_in_rate," 
					+ "CAST((e.province_in_size/e.contentBytes)*100 AS DECIMAL(6,3)) AS province_resource_service_rate " 
					+ "FROM "
					+ "(" 
					+ "SELECT " 
					+ "LEFT(serverAreaId,4) AS areaid," 
					+ "serverISPId AS ispid " 
					+ "FROM woaurl " 
					+ "WHERE InstanceId='" + instanceId + "' " 
					+ "AND domain != '' " 
					+ "AND domain IS NOT NULL " 
					+ "AND serverAreaid !='' " 
					+ "AND serverAreaid IS NOT NULL " 
					+ "AND serverISPId !='' " 
					+ "AND serverISPId IS NOT NULL "
					+ "GROUP BY serverAreaId,serverISPId"
					+ ") a," 
					+ "(" 
					+ "SELECT " 
					+ "b.domain_sum AS domain_sum," 
					+ "c.contentBytes AS contentBytes," 
					+ "d.province_in_cnt AS province_in_cnt," 
					+ "d.province_in_size AS province_in_size," 
					+ "d.areaid " 
					+ "FROM " 
					+ "(" 
					+ "SELECT COUNT(DISTINCT domain) AS domain_sum " 
					+ "FROM woaurl " 
					+ "WHERE " 
					+ "InstanceId='" + instanceId + "' " 
					+ "AND domain != '' " 
					+ "AND domain IS NOT NULL " 
					+ "AND serverAreaid !='' " 
					+ "AND serverAreaid IS NOT NULL " 
					+ "AND serverISPId !='' " 
					+ "AND serverISPId IS NOT NULL "
					+ ") b," 
					+ "(" 
					+ "SELECT " 
					+ "SUM(ALL contentBytes) AS contentBytes " 
					+ "FROM woaurl " 
					+ "WHERE " 
					+ "InstanceId='" + instanceId + "' " 
					+ "AND domain != '' "
					+ "AND domain IS NOT NULL " 
					+ "AND serverAreaid !=''  " 
					+ "AND serverAreaid IS NOT NULL "
					+ "AND serverISPId !='' " 
					+ "AND serverISPId IS NOT NULL "
					+ ") c," 
					+ "(" 
					+ "SELECT " 
					+ "LEFT(serverAreaId,4) AS areaid," 
					+ "COUNT(DISTINCT domain) AS province_in_cnt," 
					+ "SUM(contentBytes) AS province_in_size " 
					+ "FROM woaurl "
					+ "WHERE InstanceId='" + instanceId + "' " 
					+ "AND domain != '' "
					+ "AND domain IS NOT NULL " 
					+ "AND serverAreaid !=''  " 
					+ "AND serverAreaid IS NOT NULL "
					+ "AND serverISPId !='' " 
					+ "AND serverISPId IS NOT NULL "
					+ "GROUP BY LEFT(serverAreaId,4) "
					+ ") d " 
					+ ") e " 
					+ "WHERE a.areaid=e.areaid ";
			return getSQL;
		}
		
		// 保存数据到w_resource_v
		public String getInsertSQL(MonitorEntity me)
		{
			String insertSQL = "insert into w_monitor " + "( " 
			        + "areaid,"
					+ "site," 
			        + "domain_cnt,"
					+ "province_in_rate," 
					+ "province_resource_service_rate,"
			        + "ispid ) "
					+ "value " + "('"
					+ me.getAreaid()
					+ "','"
					+ me.getSite()
					+ "','"
					+ me.getDomain_cnt()
					+ "','"
					+ me.getProvince_in_rate()
					+ "','"
					+ me.getProvince_resource_service_rate()
					+ "','"
					+ me.getIspid()
					+ "')";
			return insertSQL;
		}
}
