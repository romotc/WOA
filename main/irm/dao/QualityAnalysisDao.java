package irm.dao;
/**
 * 获取SQL
 * @author	TangDican
 * @date	2013 Aug 31, 2013
 * @email	tangdican2008@163.com
 */
import irm.entity.QualityAnalysisEntity;

import com.hongganju.common.util.NumUtil;

public class QualityAnalysisDao {

	// 从数据库获取数据的SQL
	public String getSelectSQL(int instanceId)
	{
		String getSQL = "SELECT "
				+ "Id,"
				+ "taskid,"
				+ "clusterid,"
				+ "NodeIP,"
				+ "LEFT(NodeAreaId,4) AS NodeAreaId,"
				+ "NodeISPId,"
				+ "InstanceId,"
				+ "domain,"
				+ "uploadTime,"
				+ "url,"
				+ "MimeType,"
				+ "Size,"
				+ "downloadTime,"
				+ "downloadSpeed,"
				+ "serverIp,"
				+ "LEFT(serverAreaId,4) AS serverAreaId,"
				+ "serverISPId,"
				+ "success,"
				+ "COUNT(IF((httpSuccess = 1),1,0)) AS successNum,"
				+ "httpCode," 
				+ "errorInfo," 
				+ "isCached,"
				+ "networkBytes," 
				+ "customerId,"
				+ "contentBytes," 
				+ "cname," 
				+ "referurl,"
				+ "dnsResolveTotalTime," 
				+ "ConnectionTime,"
				+ "RedirectTime," 
				+ "FirstByteTime "
				+ "FROM woaurl " 
				+ "WHERE InstanceId='"
				+ instanceId + "' " 
				+ "AND domain != '' "
				+ "AND domain IS NOT NULL "
				+ "GROUP BY domain ";
		return getSQL;
	}
	// 插入数据到指定表的SQL
	public String getInsertSQL(QualityAnalysisEntity aqa)
	{
		String insertSQL = "insert into w_quality " + "( " 
		        + "domain,"
				+ "domain_level," 
		        + "serverip,"
				+ "server_areaid," 
		        + "server_ispid,"
				+ "dns_time," 
		        + "connection_time,"
				+ "firstbyte_time," 
		        + "download_time,"
				+ "downloat_speed," 
		        + "success_num ) "
				+ "value " + "('"
				+ aqa.getDomain()
				+ "','"
				+ aqa.getDomainLevel()
				+ "','"
				+ aqa.getServerIp()
				+ "','"
				+ aqa.getServerAreaId()
				+ "','"
				+ aqa.getServerISPId()
				+ "','"
				+ NumUtil.floatToString(aqa.getDnsResolveTotalTime())
				+ "','"
				+ NumUtil.floatToString(aqa.getConnectionTime())
				+ "','"
				+ NumUtil.floatToString(aqa.getFirstByteTime())
				+ "','"
				+ NumUtil.floatToString(aqa.getDownloadTime())
				+ "','"
				+ NumUtil.floatToString(aqa.getDownloadSpeed())
				+ "','"
				+ NumUtil.intToString(aqa.getSuccessNum())
				+ "')";
		return insertSQL;
	}
}
