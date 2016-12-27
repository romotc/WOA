package irm.dao;

import irm.entity.SiteInViewEntity;

/**
 * 网站引入概览SQL
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class SiteInViewDao {
	    // 从woaul获取数据
		public String getSelectSQL(int instanceId)
		{
			String getSQL = 
					"SELECT " +
					"aa.areaid AS areaid," +
					"aa.ispid AS ispid," +
					"aa.domain_cnt AS domain_cnt," +
					"aa.domain_in_cnt AS domain_in_cnt," +
					"bb.province_domain_in_cnt AS province_domain_in_cnt," +
					"CAST(bb.province_domain_in_cnt/aa.domain_cnt * 100 AS DECIMAL(6,3)) AS province_domain_in_rate," +
					"aa.domain_size AS domain_size," +
					"aa.domain_in_size AS domain_in_size," +
					"bb.province_domain_in_size AS province_domain_in_size," +
					"CAST(bb.province_domain_in_size/aa.domain_size * 100 AS DECIMAL(6,3)) AS province_domain_in_depth," +
					"aa.dx_domain_in_cnt AS dx_domain_in_cnt," +
					"CAST(aa.dx_domain_in_cnt/aa.domain_cnt * 100 AS DECIMAL(6,3)) AS dx_domain_in_rate," +
					"aa.lt_domain_in_cnt AS lt_domain_in_cnt," +
					"CAST(aa.lt_domain_in_cnt/aa.domain_cnt * 100 AS DECIMAL(6,3)) AS lt_domain_in_rate," +
					"aa.yd_domain_in_cnt AS yd_domain_in_cnt," +
					"CAST(aa.yd_domain_in_cnt/aa.domain_cnt * 100 AS DECIMAL(6,3)) AS yd_domain_in_rate," +
					"aa.other_domain_in_cnt AS other_domain_in_cnt," +
					"CAST(aa.other_domain_in_cnt/aa.domain_cnt * 100 AS DECIMAL(6,3)) AS other_domain_in_rate," +
					"aa.dx_domain_in_size AS dx_domain_in_size," +
					"CAST(aa.dx_domain_in_size/aa.domain_size * 100 AS DECIMAL(6,3)) AS dx_domain_in_depth," +
					"aa.lt_domain_in_size AS lt_domain_in_size," +
					"CAST(aa.lt_domain_in_size/aa.domain_size * 100 AS DECIMAL(6,3)) AS lt_domain_in_depth," +
					"aa.yd_domain_in_size AS yd_domain_in_size," +
					"CAST(aa.yd_domain_in_size/aa.domain_size * 100 AS DECIMAL(6,3)) AS yd_domain_in_depth," +
					"aa.other_domain_in_size AS other_domain_in_size," +
					"CAST(aa.other_domain_in_size/aa.domain_size * 100 AS DECIMAL(6,3)) AS other_domain_in_depth " +
					"FROM " +
					"(" +
					"SELECT " +
					"a.areaid AS areaid," +
					"a.ispid AS ispid," +
					"a.domain_in_cnt AS domain_in_cnt," +
					"a.domain_in_size AS domain_in_size," +
					"a.dx_domain_in_cnt AS dx_domain_in_cnt," +
					"a.lt_domain_in_cnt AS lt_domain_in_cnt," +
					"a.yd_domain_in_cnt AS yd_domain_in_cnt," +
					"a.other_domain_in_cnt AS other_domain_in_cnt," +
					"a.dx_domain_in_size AS dx_domain_in_size," +
					"a.lt_domain_in_size AS lt_domain_in_size," +
					"a.yd_domain_in_size AS yd_domain_in_size," +
					"a.other_domain_in_size AS other_domain_in_size," +
					"b.domain_cnt AS domain_cnt," +
					"b.domain_size AS domain_size " +
					"FROM " +
					"(" +
					"SELECT " +
					"LEFT(serverAreaId,4) AS areaid," +
					"COUNT(DISTINCT domain) AS domain_in_cnt," +
					"serverISPId AS ispid," +
					"SUM(contentBytes) AS domain_in_size," +
					"COUNT( DISTINCT IF(serverISPId='CN0001',domain,NULL))  AS dx_domain_in_cnt," +
					"COUNT(DISTINCT IF(serverISPId='CN0002',domain,NULL)) AS lt_domain_in_cnt," +
					"COUNT(DISTINCT IF(serverISPId='CN0003',domain,NULL)) AS yd_domain_in_cnt," +
					"COUNT(DISTINCT IF(serverISPId !='CN0001' AND serverISPId !='CN0002' " +
					         "AND serverISPId !='CN0003',domain,NULL)) AS other_domain_in_cnt," +
					"SUM(IF(serverISPId='CN0001',contentBytes,0))  AS dx_domain_in_size," +
					"SUM(IF(serverISPId='CN0002',contentBytes,0)) AS lt_domain_in_size," +
					"SUM(IF(serverISPId='CN0003',contentBytes,0)) AS yd_domain_in_size," +
					"SUM(IF(serverISPId !='CN0001' AND serverISPId !='CN0002' " +
					         "AND serverISPId !='CN0003',contentBytes,0)) AS other_domain_in_size " +
					"FROM woaurl " +
					"WHERE " +
					"InstanceId='"+ instanceId +"' " +
					"AND serverAreaId != '' " +
					"AND serverAreaId IS NOT NULL " +
					"AND domain != '' " +
					"AND domain IS NOT NULL " +
					"AND serverISPId != '' " +
					"AND serverISPId IS NOT NULL " +
					"GROUP BY LEFT(serverAreaId,4),serverISPId" +
					") a," +
					"(" +
					"SELECT " +
					"COUNT(DISTINCT domain) AS domain_cnt," +
					"SUM(contentBytes) AS domain_size " +
					"FROM woaurl " +
					"WHERE " +
					"InstanceId='"+ instanceId +"' " +
					"AND serverAreaId !='' " +
					"AND serverAreaId IS NOT NULL " +
					"AND domain != '' " +
					"AND domain IS NOT NULL " +
					") b" +
					") aa," +
					"(" +
					"SELECT " +
					"LEFT(serverAreaId,4) AS areaid," +
					"COUNT(DISTINCT domain) AS province_domain_in_cnt," +
					"SUM(contentBytes) AS province_domain_in_size " +
					"FROM woaurl " +
					"WHERE " +
					"InstanceId='"+ instanceId +"' " +
					"AND serverAreaId != '' " +
					"AND serverAreaId IS NOT NULL " +
					"AND domain != '' " +
					"AND domain IS NOT NULL " +
					"AND serverISPId != '' " +
					"AND serverISPId IS NOT NULL " +
					"GROUP BY LEFT(serverAreaId,4) " +
					") bb " +
					"WHERE aa.areaid = bb.areaid ";
			return getSQL;
		}
		
		// 保存数据到w_site_in
		public String getInsertSQL(SiteInViewEntity sie)
		{
			String insertSQL = "insert into w_site_in_v " + "( " 
			        + "areaid,"
					+ "site," 
			        + "domain_cnt,"
					+ "domain_in_cnt," 
					+ "province_domain_in_cnt,"
					+ "province_domain_in_rate,"
					+ "yd_domain_in_cnt,"
					+ "yd_domain_in_rate,"
					+ "dx_domain_in_cnt,"
					+ "dx_domain_in_rate,"
					+ "lt_domain_in_cnt,"
					+ "lt_domain_in_rate,"
					+ "other_domain_in_cnt,"
					+ "other_domain_in_rate,"
					+ "domain_size,"
					+ "domain_in_size,"
					+ "province_domain_in_size,"
					+ "province_domain_in_depth,"
					+ "yd_domain_in_size,"
					+ "yd_domain_in_depth,"
					+ "dx_domain_in_size,"
					+ "dx_domain_in_depth,"
					+ "lt_domain_in_size,"
					+ "lt_domain_in_depth,"
					+ "other_domain_in_size,"
					+ "other_domain_in_depth,"
			        + "ispid ) "
					+ "value " + "('"
					+ sie.getAreaid()
					+ "','"
					+ sie.getSite()
					+ "','"
					+ sie.getDomain_cnt()
					+ "','"
					+ sie.getDomain_in_cnt()
					+ "','"
					+ sie.getProvince_domain_in_cnt()
					+ "','"
					+ sie.getProvince_domain_in_rate()
					+ "','"
					+ sie.getYd_domain_in_cnt()
					+ "','"
					+ sie.getYd_domain_in_rate()
					+ "','"
					+ sie.getDx_domain_in_cnt()
					+ "','"
					+ sie.getDx_domain_in_rate()
					+ "','"
					+ sie.getLt_domain_in_cnt()
					+ "','"
					+ sie.getLt_domain_in_rate()
					+ "','"
					+ sie.getOther_domain_in_cnt()
					+ "','"
					+ sie.getOther_domain_in_rate()
					+ "','"
					+ sie.getDomain_size()
					+ "','"
					+ sie.getDomain_in_size()
					+ "','"
					+ sie.getProvince_domain_in_size()
					+ "','"
					+ sie.getProvince_domain_in_depth()
					+ "','"
					+ sie.getYd_domain_in_size()
					+ "','"
					+ sie.getYd_domain_in_depth()
					+ "','"
					+ sie.getDx_domain_in_size()
					+ "','"
					+ sie.getDx_domain_in_depth()
					+ "','"
					+ sie.getLt_domain_in_size()
					+ "','"
					+ sie.getLt_domain_in_depth()
					+ "','"
					+ sie.getOther_domain_in_size()
					+ "','"
					+ sie.getOther_domain_in_depth()
					+ "','"
					+ sie.getIspid()
					+ "')";
			return insertSQL;
		}
}
