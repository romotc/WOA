/**
 * 
 */
package irm;

import irm.common.DBEntry;
import irm.common.GetInstance;
import irm.dao.ProvinceSiteViewDao;
import irm.entity.InstanceEntity;
import irm.entity.ProvinceSiteViewEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import statistic.DataManager;
import com.hongganju.common.util.NumUtil;

/**
 * 分区域网站概览操作
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class ProvinceSiteView {

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
		private HashMap<String, ProvinceSiteViewEntity> maps;
		// 定义日志
		private static final Log log = LogFactory.getLog(ProvinceSiteView.class);
		// 数据库登录类
		DBEntry dbe = new DBEntry();
		// 从数据库得到的数据
		private ResultSet rs;
		// 定义区域id
		private String areaid;
		// 定义运营商id
		private String ispid;
		// 定义实体类
		private ProvinceSiteViewEntity psve;
		// 网站名称
		private String webName;

			// 依次运行实例id
			public void run(String instanceIds[]) {
				for (int i = 0; i < instanceIds.length; i++) {
					// 依次获取实例id
					instanceId = Integer.parseInt(instanceIds[i]);
					ie = new GetInstance().getInstance(instanceId);
					instanceId = ie.getTaskinstanceId();
					// 获取运行实例的初始信息
					taskId = ie.getTaskId();
					webName = ie.getWebName();
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
							maps = new HashMap<String,ProvinceSiteViewEntity>();
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
					rs = manage1.executeQuery(new ProvinceSiteViewDao().getSelectSQL(instanceId));
					while (rs.next()) {
						// 循环获取的类数据放入maps中
						psve = new ProvinceSiteViewEntity();
						psve.setAreaid(areaid = rs.getString("areaid"));
						psve.setIspid(ispid=rs.getString("ispid"));
						psve.setDomain_in_cnt(NumUtil.parseInt(rs.getString("domain_in_cnt")));
						psve.setUrl_in_cnt(NumUtil.parseInt(rs.getString("url_in_cnt")));
						psve.setDomain_net_in_rate(NumUtil.parseFloat(rs.getString("domain_net_in_rate")));
						psve.setDomain_net_in_depth(NumUtil.parseFloat(rs.getString("domain_net_in_depth")));
						psve.setSite(webName);
						
						if (!maps.containsKey(areaid+ispid)) {
							maps.put(areaid+ispid, psve);
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
				for (Map.Entry<String,ProvinceSiteViewEntity> areaidIspidVal : maps.entrySet()) 
				{
					log.debug("insert into w_province_site_v with property areaid:"+psve.getAreaid() + ",ispid:"+psve.getIspid());
					psve = areaidIspidVal.getValue();
					System.out.println("insert into w_province_site_v with property areaid:"+psve.getAreaid() + ",ispid:"+psve.getIspid());
					manage1.execute(new ProvinceSiteViewDao().getInsertSQL(psve));
				}
			}	
}
