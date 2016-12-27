/**
 * 
 */
package irm;

import irm.common.DBEntry;
import irm.common.GetInstance;
import irm.dao.ResourceInDao;
import irm.entity.InstanceEntity;
import irm.entity.ResourceInEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.hongganju.common.util.NumUtil;
import statistic.DataManager;

/**
 * 各省本网资源引入分析操作
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class ResourceIn {
	// 实例id
	private int instanceId;
	// 实例类
	private InstanceEntity ie;
	// 任务id
	private Integer taskId;
	// 数据库的id
	private short dbId;
	// 网页类型
	private Integer type;
	// 数据库管理类
	private DataManager manage1;
	// 定义hashMap
	private HashMap<String, ResourceInEntity> maps;
	// 定义日志
	private static final Log log = LogFactory.getLog(ResourceIn.class);
	// 数据库登录类
	DBEntry dbe = new DBEntry();
	// 从数据库得到的数据
	private ResultSet rs;
	// 定义区域id
	private String areaid;
	// 定义运营商id
	private String ispid;
	// 定义实体类
	private ResourceInEntity rie;

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
						maps = new HashMap<String,ResourceInEntity>();
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
					+ "= " + instanceId  );
			try {	
				// 执行SQL
				rs = manage1.executeQuery(new ResourceInDao().getSelectSQL(instanceId));
				while (rs.next()) {
					// 循环获取的类数据放入maps中
					rie = new ResourceInEntity();
					rie.setAreaid(areaid = rs.getString("areaid"));
					rie.setIspid(ispid=rs.getString("ispid"));
					rie.setDomain_in_cnt(NumUtil.parseInt(rs.getString("domain_in_cnt")));
					rie.setSite_in_cnt(NumUtil.parseInt(rs.getString("site_in_cnt")));
					rie.setUrl_in_cnt(NumUtil.parseInt(rs.getString("url_in_cnt")));
					rie.setDomain_net_in_rate(NumUtil.parseFloat(rs.getString("domain_net_in_rate")));
					rie.setDomain_net_in_size_rate(NumUtil.parseFloat(rs.getString("domain_net_in_size_rate")));
					rie.setDomain_in_size(NumUtil.parseInt(rs.getString("domain_in_size")));
					rie.setSite_in_size(NumUtil.parseInt(rs.getString("site_in_size")));
					rie.setUrl_in_size(NumUtil.parseInt(rs.getString("url_in_size")));
					
					if (!maps.containsKey(areaid+ispid)) {
						maps.put(areaid+ispid, rie);
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
			for (Map.Entry<String,ResourceInEntity> areaidIspidVal : maps.entrySet()) 
			{
				log.debug("insert into w_resource_in with property areaid:"+rie.getAreaid()+",ispid:"+rie.getIspid());
				rie = areaidIspidVal.getValue();
				System.out.println("insert into w_quality with property areaid:"+rie.getAreaid()+",ispid:"+rie.getIspid());
				manage1.execute(new ResourceInDao().getInsertSQL(rie));
			}
		}	
}
