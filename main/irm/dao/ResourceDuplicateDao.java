package irm.dao;

import irm.entity.ResourceDuplicateEntity;
import com.hongganju.common.util.NumUtil;

/**
 * 全网重复资源SQL
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class ResourceDuplicateDao {

	    // 从数据库获取数据的SQL
		public String getSelectSQL(int instanceId)
		{
			String getSQL = "SELECT "
					+ "domain,"
					+ "COUNT(IF((isCached = 1),1,0)) AS cache_num "
					+ "FROM woaurl " 
					+ "WHERE InstanceId='"
					+ instanceId + "' " 
					+ "AND domain != '' "
					+ "AND domain IS NOT NULL "
					+ "GROUP BY domain ";
			return getSQL;
		}
		// 插入数据到指定表的SQL
		public String getInsertSQL(ResourceDuplicateEntity rde)
		{
			String insertSQL = "insert into w_resource_duplicate " + "( " 
					+ "site,"
			        + "domain,"
			        + "cache_num ) "
					+ "value " + "('"
					+ rde.getSite()
					+ "','"
					+ rde.getDomain()
					+ "','"
					+ NumUtil.intToString(rde.getCache_num())
					+ "')";
			return insertSQL;
		}
}
