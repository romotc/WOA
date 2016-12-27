/**
 * 
 */
package irm;

import irm.common.DBEntry;
import irm.common.GetInstance;
import irm.dao.SiteInViewDao;
import irm.entity.InstanceEntity;
import irm.entity.SiteInViewEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import statistic.DataManager;
import com.hongganju.common.util.NumUtil;

/**
 * 网站引入概览操作
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class SiteInView {
	 // log记录
	private static final Log log = LogFactory.getLog(SiteInView.class);
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
	HashMap<String, SiteInViewEntity> maps;
	// db返回的值
	ResultSet rs;
	// 实体类
	SiteInViewEntity sive;
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
					maps = new HashMap<String,SiteInViewEntity>();
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
				+ " GROUP BY serverAreaId,serverISPId");
		try {	
			// 执行指定instanceId的SQL
			rs = manage1.executeQuery(new SiteInViewDao().getSelectSQL(instanceId));
			while (rs.next()) {
				// 获取类的数据
				sive = new SiteInViewEntity();
				sive.setAreaid(areaid = rs.getString("areaid"));
				sive.setIspid(ispid = rs.getString("ispid"));
				sive.setSite(webName);
				
				sive.setDomain_cnt(NumUtil.parseInt(rs.getString("domain_cnt")));
				sive.setDomain_in_cnt(NumUtil.parseInt(rs.getString("domain_in_cnt")));
				sive.setProvince_domain_in_cnt(NumUtil.parseInt(rs.getString("province_domain_in_cnt")));
				sive.setDx_domain_in_cnt(NumUtil.parseInt(rs.getString("dx_domain_in_cnt")));
				sive.setLt_domain_in_cnt(NumUtil.parseInt(rs.getString("lt_domain_in_cnt")));
				sive.setYd_domain_in_cnt(NumUtil.parseInt(rs.getString("yd_domain_in_cnt")));
				sive.setOther_domain_in_cnt(NumUtil.parseInt(rs.getString("other_domain_in_cnt")));
				
				sive.setProvince_domain_in_rate(NumUtil.parseFloat(rs.getString("province_domain_in_rate")));
				sive.setDx_domain_in_rate(NumUtil.parseFloat(rs.getString("dx_domain_in_rate")));
				sive.setLt_domain_in_rate(NumUtil.parseFloat(rs.getString("lt_domain_in_rate")));
				sive.setYd_domain_in_rate(NumUtil.parseFloat(rs.getString("yd_domain_in_rate")));
				sive.setOther_domain_in_rate(NumUtil.parseFloat(rs.getString("other_domain_in_rate")));
				
				sive.setDomain_size(NumUtil.parseInt(rs.getString("domain_size")));
				sive.setDomain_in_size(NumUtil.parseInt(rs.getString("domain_in_size")));
				sive.setProvince_domain_in_size(NumUtil.parseInt(rs.getString("province_domain_in_size")));
				sive.setDx_domain_in_size(NumUtil.parseInt(rs.getString("dx_domain_in_size")));
				sive.setLt_domain_in_size(NumUtil.parseInt(rs.getString("lt_domain_in_size")));
				sive.setYd_domain_in_size(NumUtil.parseInt(rs.getString("yd_domain_in_size")));
				sive.setOther_domain_in_size(NumUtil.parseInt(rs.getString("other_domain_in_size")));
				
				sive.setProvince_domain_in_depth(NumUtil.parseFloat(rs.getString("province_domain_in_depth")));
				sive.setDx_domain_in_depth(NumUtil.parseFloat(rs.getString("dx_domain_in_depth")));
				sive.setLt_domain_in_depth(NumUtil.parseFloat(rs.getString("lt_domain_in_depth")));
				sive.setYd_domain_in_depth(NumUtil.parseFloat(rs.getString("yd_domain_in_depth")));
				sive.setOther_domain_in_depth(NumUtil.parseFloat(rs.getString("other_domain_in_depth")));
				
				// 实体类存入maps
				if (!maps.containsKey(areaid+ispid)) {
					maps.put(areaid+ispid, sive);
				}
			}
			// db关闭
			rs.close();
		} catch (SQLException e) {
			log.error("select * from woaurl with failed", e);
			e.printStackTrace();
		}
	}
	
	// 保存数据到w_site_in_v表
	public void saveDBData()
	{
		// 从map获取数据
		for (Map.Entry<String, SiteInViewEntity> areaidIspidVal : maps.entrySet()) 
		{
			// log记录
			log.debug("insert into w_site_in_v with property areaid:"
					+sive.getAreaid()+",ispid:"+sive.getIspid());
			// 获取类值
			sive = areaidIspidVal.getValue();
			System.out.println("insert into w_site_in_v with property areaid:"
					+sive.getAreaid()+",ispid:"+sive.getIspid());
			// db执行
			manage1.execute(new SiteInViewDao().getInsertSQL(sive));
		}
	}	
}
