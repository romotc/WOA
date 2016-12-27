/**
 * 
 */
package irm;

import irm.common.DBEntry;
import irm.common.GetInstance;
import irm.dao.DomainDao;
import irm.entity.InstanceEntity;
import irm.entity.DomainEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import statistic.DataManager;

/**
 * 域名明细操作
 * @author	TangDican
 * @date	2013 Sep 4, 2013
 * @email	tangdican2008@163.com
 */
public class Domain {
	 // log记录
	private static final Log log = LogFactory.getLog(Domain.class);
	// 实例化
	DBEntry dbe = new DBEntry();
	// 实例id
	int instanceId;
	// 实例实体
	InstanceEntity ie;
	// 任务id
	Integer taskId;
	// 网站名称
	String webName;
	// dbmanager id
	short dbId;
	// 网页形式
	Integer type;
	// db操作
	DataManager manage1;
	HashMap<String, DomainEntity> maps;
	// db返回的值
	ResultSet rs;
	// 实体类
	DomainEntity de;
	// domain
	String domain;
	
	// 运行实例
	public void run(String instanceIds[]) {
		// 实例循环
		for (int i = 0; i < instanceIds.length; i++) {
			// 获取实例id
			instanceId = Integer.parseInt(instanceIds[i]);
			ie = new GetInstance().getInstance(instanceId);
			instanceId = ie.getTaskinstanceId();
			// 获取任务id
			taskId = ie.getTaskId();
			// 获取网站名称
			webName = ie.getWebName();
			// 获取数据库id
			dbId = ie.getDbId();
			// 获取网站类型
			type = ie.getType();			
			// 实例开始
			System.out.println("--> task:" + taskId + ",instance:" + instanceId
					+ ",begin static:");
			try {
				if (type == 1)// 普通网页
				{
					// 任务ID，实例ID，dbid
					manage1 = dbe.TaskDBByID(dbId);
					maps = new HashMap<String,DomainEntity>();
					// 获取数据
					getDBData();
					// 保存数据
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
	
	// 从woaurl表获取数据
	public void getDBData()
	{
		log.debug("SELECT * FROM woaurl "
	            + "WHERE woaurl"
				+ " GROUP BY domain");
		try {	
			// 执行指定instanceId的SQL
			rs = manage1.executeQuery(new DomainDao().getSelectSQL(instanceId));
			while (rs.next()) {
				// 获取类的数据
				de = new DomainEntity();
				de.setDomain(domain = rs.getString("domain"));
				de.setIp(rs.getString("ip"));
				// 实体类存入maps
				if (!maps.containsKey(domain)) {
					maps.put(domain, de);
				}
			}
			// db关闭
			rs.close();
		} catch (SQLException e) {
			log.error("select * from woaurl with failed", e);
			e.printStackTrace();
		}
	}
	
	// 保存数据到w_domain表
	public void saveDBData()
	{
		// 从map获取数据
		for (Map.Entry<String, DomainEntity> domainVal : maps.entrySet()) 
		{
			// log记录
			log.debug("insert into w_domain with property domain:"+de.getDomain());
			// 获取类值
			de = domainVal.getValue();
			System.out.println("insert into w_domain with property domain:"+ de.getDomain());
			// db执行
			manage1.execute(new DomainDao().getInsertSQL(de));
		}
	}	
}
