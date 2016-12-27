/**
 * 
 */
package irm;

import irm.common.DBEntry;
import irm.common.GetInstance;
import irm.dao.SiteInDao;
import irm.entity.InstanceEntity;
import irm.entity.SiteInEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.hongganju.common.util.NumUtil;
import statistic.DataManager;

/**
 * 网站资源引入分析操作
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class SiteIn {

	    // log记录
		private static final Log log = LogFactory.getLog(SiteIn.class);
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
		HashMap<String, SiteInEntity> maps;
		// db返回的值
		ResultSet rs;
		// 实体类
		SiteInEntity sie;
		// 区域id
		String areaid;
		// 运营商id
		String ispid;
		
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
						maps = new HashMap<String,SiteInEntity>();
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
					+ " GROUP BY serverAreaId");
			try {	
				// 执行指定instanceId的SQL
				rs = manage1.executeQuery(new SiteInDao().getSelectSQL(instanceId));
				while (rs.next()) {
					// 获取类的数据
					sie = new SiteInEntity();
					sie.setAreaid(areaid=rs.getString("areaid"));
					sie.setIspid(ispid=rs.getString("ispid"));
					sie.setDomain_cnt(NumUtil.parseInt(rs.getString("domain_cnt")));
					sie.setSite(webName);
					sie.setUrl_cnt(NumUtil.parseInt(rs.getString("url_cnt")));
					sie.setAva_cache_resource_cnt(NumUtil.parseInt(rs.getString("ava_cache_resource_cnt")));
					sie.setAva_cache_resource_cnt_rate(NumUtil.parseFloat(rs.getString("ava_cache_resource_cnt_rate")));
					sie.setAva_cache_resource_size_rate(NumUtil.parseFloat(rs.getString("ava_cache_resource_size_rate")));
					sie.setUrl_size(NumUtil.parseFloat(rs.getString("url_size")));
					sie.setCache_hit_rate(NumUtil.parseFloat(rs.getString("cache_hit_rate")));
					
					// 实体类存入maps
					if (!maps.containsKey(areaid+ispid)) {
						maps.put(areaid+ispid, sie);
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
			for (Map.Entry<String, SiteInEntity> areaidIspidVal : maps.entrySet()) 
			{
				// log记录
				log.debug("insert into w_site_in with property areaid:"+sie.getAreaid()+",ispid:"+sie.getIspid());
				// 获取类值
				sie = areaidIspidVal.getValue();
				System.out.println("insert into w_site_in with property areaid:"+sie.getAreaid()+",ispid:"+sie.getIspid());
				// db执行
				manage1.execute(new SiteInDao().getInsertSQL(sie));
			}
		}	
}
