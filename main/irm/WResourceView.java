package irm;

/**
 * 管理资源总览数据
 * @author	TangDican
 * @date	2013 Aug 31
 * @email	tangdican2008@163.com
 */
import irm.common.DBEntry;
import irm.common.GetInstance;
import irm.dao.WResourceViewDao;
import irm.entity.InstanceEntity;
import irm.entity.WResourceViewEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.hongganju.common.util.NumUtil;
import statistic.DataManager;

public class WResourceView {

	// log记录
	private static final Log log = LogFactory.getLog(WResourceView.class);
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
	HashMap<String, WResourceViewEntity> maps;
	// db返回的值
	ResultSet rs;
	// 实体类
	WResourceViewEntity wrve;
	// 省份id
	String areaid;
	
	public void run(String instanceIds[]) {
		for (int i = 0; i < instanceIds.length; i++) {
			instanceId = Integer.parseInt(instanceIds[i]);
			ie = new GetInstance().getInstance(instanceId);
			instanceId = ie.getTaskinstanceId();
			taskId = ie.getTaskId();
			webName = ie.getWebName();
			dbId = ie.getDbId();
			type = ie.getType();
			System.out.println("--> task:" + taskId + ",instance:" + instanceId
					+ ",begin static:");
			try {
				if (type == 1)// 普通网页
				{
					// 任务ID，实例ID，dbid
					manage1 = dbe.TaskDBByID(dbId);
					maps = new HashMap<String,WResourceViewEntity>();
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
				+ " GROUP BY serverAreaId ");
		try {	
			// 执行指定instanceId的SQL
			rs = manage1.executeQuery(new WResourceViewDao().getSelectSQL(instanceId));
			while (rs.next()) {
				// 获取类的数据
				wrve = new WResourceViewEntity();
				wrve.setAreaid(areaid = rs.getString("areaid"));
				wrve.setSite_cnt(NumUtil.parseInt(rs.getString("site_cnt")));
				wrve.setDomain_cnt(NumUtil.parseInt(rs.getString("domain_cnt")));
				wrve.setUrl_cnt(NumUtil.parseInt(rs.getString("url_cnt")));
				wrve.setIspid(rs.getString("ispid"));
				// 实体类存入maps
				if (!maps.containsKey(areaid)) {
					maps.put(areaid, wrve);
				}
			}
			// db关闭
			rs.close();
		} catch (SQLException e) {
			log.error("select * from woaurl with failed", e);
			e.printStackTrace();
		}
	}
	
	// 保存数据到w_resource_v表
	public void saveDBData()
	{
		// 从map获取数据
		for (Map.Entry<String, WResourceViewEntity> areaidVal : maps.entrySet()) 
		{
			// log记录
			log.debug("insert into w_resource_v with property areaid:"+wrve.getAreaid());
			System.out.printf("insert into w_resource_v with property areaid:", wrve.getAreaid());
			// 获取类值
			wrve = areaidVal.getValue();
			// db执行
			manage1.execute(new WResourceViewDao().getInsertSQL(wrve));
		}
	}	
}
