package irm;
/**
 * 质量分析数据操作
 * @author	TangDican
 * @email	tangdican2008@163.com
 * @date	2013 Aug 31
 */
import irm.common.DBEntry;
import irm.common.GetInstance;
import irm.dao.QualityAnalysisDao;
import irm.entity.InstanceEntity;
import irm.entity.QualityAnalysisEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.hongganju.common.util.NumUtil;
import statistic.DataManager;

public class QualityAnalysis {
	// 域名后缀的索引
	int n;
	// 实例id
	int instanceId ;
	// 任务id
	int taskId = 0;
	// 网页类型
	int type = 0;
	// 数据库的id
	short dbId = 0;
	// 数据库管理类
	DataManager manage1;
	// 定义hashMap
	HashMap<String, QualityAnalysisEntity> maps;
	// 从数据库得到的数据
	ResultSet rs;
	// 域名
	String domain;
	// 类调用
	QualityAnalysisEntity aqa;
	// 实例类
	InstanceEntity ie;
	// 定义日志
	private static final Log log = LogFactory.getLog(QualityAnalysis.class);
	// 数据库登录类
	DBEntry dbe = new DBEntry();
	// 依次运行实例id
	public void run(String instanceIds[]) {
		for (int i = 0; i < instanceIds.length; i++) {
			// 依次获取实例id
			instanceId = Integer.parseInt(instanceIds[i]);
			ie = new GetInstance().getInstance(instanceId);
			instanceId = ie.getTaskinstanceId();
			// 获取运行实例的初始信息
			taskId = ie.getTaskId();
			dbId = ie.getDbId();
			type = ie.getType();
			// 实例开始
			System.out.println("--> task:" + taskId + ",instance:" + instanceId
					+ ",begin static:");
			try {
				if (type == 1)// 普通网页
				{
					// 任务ID，实例ID，dbid
					manage1 = dbe.TaskDBByID(dbId);
					maps = new HashMap<String,QualityAnalysisEntity>();
					// 从db获取数据
					getDBData();
					// 保存数据到数据库指定的表
					saveDBData();
				   }
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("** task:" + taskId + ",instance:" + instanceId
						+ ",error");
			} finally {
				// 实例结束
				System.out.println("--> task:" + taskId + ",instance:" + instanceId
						+ ",end static");
			}
		}
	}

	// 获取数据
	public void getDBData()
	{
		log.debug("SELECT * FROM woaurl "
	            + "WHERE InstanceId " 
				+ "= " + instanceId  
				+ " AND domain != '' "
				+ " AND domain IS NOT NULL "
				+ " GROUP BY domain ");
		try {	
			// 执行SQL
			rs = manage1.executeQuery(new QualityAnalysisDao().getSelectSQL(instanceId));
			while (rs.next()) {
				// 循环获取的类数据放入maps中
				aqa = new QualityAnalysisEntity();
				aqa.setDomain(domain = rs.getString("domain"));
				aqa.setServerIp(rs.getString("serverIp"));
				aqa.setServerAreaId(rs.getString("serverAreaId"));
				aqa.setServerISPId(rs.getString("serverISPId"));
				aqa.setDnsResolveTotalTime(NumUtil.parseFloat(rs.getString("dnsResolveTotalTime")));
				aqa.setConnectionTime(NumUtil.parseFloat(rs.getString("ConnectionTime")));
				aqa.setFirstByteTime(NumUtil.parseFloat(rs.getString("FirstByteTime")));
				aqa.setDownloadTime(NumUtil.parseFloat(rs.getString("downloadTime")));
				aqa.setDownloadSpeed(NumUtil.parseFloat(rs.getString("downloadSpeed")));
				aqa.setSuccessNum(NumUtil.parseInt(rs.getString("successNum")));
				// 获取域名等级
				String domainSuffix[] = { ".cn", ".com", ".net", "am" };
				for (n = 0; n < domainSuffix.length; n++)
					if (domain.endsWith(domainSuffix[n])) {
						aqa.setDomainLevel(NumUtil.intToString(domain.split("\\.").length - 1));
					}
				
				if (!maps.containsKey(domain)) {
					maps.put(domain, aqa);
				}
			}
			// 关闭
			rs.close();
		} catch (SQLException e) {
			log.error("select * from woaurl with failed", e);
			e.printStackTrace();
		}
	}
	// 保存数据到指定的表
	public void saveDBData()
	{
		// 依次保存数据
		for (Map.Entry<String, QualityAnalysisEntity> domainval : maps.entrySet()) 
		{
			log.debug("insert into w_quality with property domain:"+aqa.getDomain());
			aqa = domainval.getValue();
			System.out.println("insert into w_quality with property domain:"+aqa.getDomain());
			manage1.execute(new QualityAnalysisDao().getInsertSQL(aqa));
		}
	}	
}
