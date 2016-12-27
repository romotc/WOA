package statistic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
public class AreaIspService {
	
	protected PropertyUtil propertyUtil=new PropertyUtil();
	protected String url=propertyUtil.getProperty("db.url");
	protected String username=propertyUtil.getProperty("db.user"); 
	protected String password=propertyUtil.getProperty("db.password"); 
	
	public  void sitePop(int taskID,int taskInstanceId ,Short DBID){
		//按地区分运营商统计
		statistic(taskID,taskInstanceId,DBID);// 任务ID，实例ID，dbid
	}
	
	public  DataManager TaskDBByID(Short id)
	{
		String sql = "select url,username,password from dbmanagerread where id = '"+id+"'";
		DataManager manage= new DataManager(url,username,password);
		DataManager manages = null;
		try {

			ResultSet rs = manage.executeQuery(sql);
			while(rs.next()){
				String urlmore = rs.getString(1);
				String usernamemore  = rs.getString(2);
				String passwordmore  = rs.getString(3);

				manages = new DataManager(urlmore ,usernamemore ,passwordmore);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(manage != null)
				manage.closeConn();
		}

		return manages;
	}

	public  void statistic(int taskid,int instanceId,Short dbid){

		DataManager manage = TaskDBByID(dbid);
		try{
			String nodes = " SELECT NodeIP,NodeAreaId,NodeISPId FROM woaurl WHERE InstanceId='"+instanceId+"' GROUP BY NodeIP ";
			ResultSet rs = manage.executeQuery(nodes);
			String[] areaisp = new String[3];
			HashMap<String, String[]> nodemaps = new HashMap<String, String[]>();

			while (rs.next()) {
				String IP = rs.getString(1);
				String area = rs.getString(2);
				String isp = rs.getString(3);
				areaisp[0] = area;
				areaisp[1] = isp;
				areaisp[2] = IP;
				if(!nodemaps.containsKey(IP))
				{
					nodemaps.put(IP, areaisp);
				}
			}
			rs.close();
			for(Map.Entry<String, String[]> val : nodemaps.entrySet())
			{
				String [] value = val.getValue();
				
				String perfors = " SELECT " +
				" COUNT(Id) AS count," +
				" COUNT(IF((httpCode = 200),1 , 0)) AS num," +
				" SUM(CAST(networkBytes / 1024 / 1024 AS DECIMAL(10,3))) AS networkBytes " +
				" FROM woaurl WHERE " +
				" InstanceId='"+instanceId+"' " +
				" and domain !='' and domain !='null' and domain !='*' and domain !='0.0.0.0' and domain is not null " +
				" and NodeAreaId ='"+value[0]+"' and NodeISPId = '"+value[1]+"' " +
				" GROUP BY InstanceId ";
				rs = manage.executeQuery(perfors);
				String[] nums = new String[3];
				while (rs.next()) {
					String count = rs.getString(1);
					String countright = rs.getString(2);
					String networkBytes = rs.getString(3);
					nums[0] = count;
					nums[1] = countright;
					nums[2] = networkBytes;
				}
				rs.close();
				DecimalFormat  df  = new  DecimalFormat("0.000");
				int counts = Integer.parseInt(nums[0]);//全部
			//  int num = Integer.parseInt(nums[1]);//正确
				String bytes = nums[2];
				
				perfors = " SELECT " +
				" COUNT(uploadTime) AS count, " +
				" COUNT(IF((httpCode = 200),1 , 0)) AS num," +
				" CAST(SUM(downloadTime) / 1000 AS DECIMAL(10,3)) AS downloadTime, " +
				" CAST(SUM(downloadSpeed)  AS DECIMAL(10,3)) AS downloadSpeed, " +
				" CAST(SUM(dnsResolveTotalTime) / 1000 AS DECIMAL(10,3)) AS dnsResolveTotalTime, " +
				" CAST(SUM(ConnectionTime) / 1000 AS DECIMAL(10,3)) AS ConnectionTime, " +
				" CAST(SUM(RedirectTime) / 1000 AS DECIMAL(10,3)) AS RedirectTime, " +
				" CAST(SUM(FirstByteTime) / 1000 AS DECIMAL(10,3)) AS FirstByteTime, " +
				" CAST(SUM(networkBytes) / 1024 / 1024 AS DECIMAL(10,3)) AS networkBytes," +
				" serverAreaId,serverISPId " +
				" FROM woaurl WHERE " +
				" InstanceId='"+instanceId+"' " +
				" and NodeAreaId ='"+value[0]+"' and NodeISPId = '"+value[1]+"' " +
				" and serverAreaId is not null " +
				" GROUP BY SUBSTR(serverAreaId,1,4),serverISPId ";
				rs = manage.executeQuery(perfors);
				while (rs.next()) {
					String count = rs.getString(1);
					String right = rs.getString(2);
					String downloadTime = rs.getString(3);
					String downloadSpeed = rs.getString(4);
					String dnsResolveTotalTime = rs.getString(5);
					String ConnectionTime = rs.getString(6);
					String RedirectTime = rs.getString(7);
					String FirstByteTime = rs.getString(8);
					String networkBytes = rs.getString(9);
					String area = rs.getString(10).toString().substring(0, 4)+"00";
					String isp = rs.getString(11);
					
					if(null == downloadTime)
						downloadTime = "0";
					if(null == downloadSpeed)
						downloadSpeed = "0";
					if(null == networkBytes)
						networkBytes = "0";
					if(null == dnsResolveTotalTime)
						dnsResolveTotalTime = "0";
					if(null == ConnectionTime)
						ConnectionTime = "0";
					if(null == RedirectTime)
						RedirectTime = "0";
					if(null == FirstByteTime)
						FirstByteTime = "0";
					
					String countrate  = df.format(Float.parseFloat(count)/counts);
					//String downloadTimerate  = df.format(Float.parseFloat(downloadTime)/Integer.parseInt(right));
					String downloadSpeedrate  = df.format(Float.parseFloat(downloadSpeed)/Integer.parseInt(right));
					
					String dnsResolveTotalTimerate  = df.format(Float.parseFloat(dnsResolveTotalTime)/Integer.parseInt(right));
					String ConnectionTimerate  = df.format(Float.parseFloat(ConnectionTime)/Integer.parseInt(right));
					String RedirectTimerate  = df.format(Float.parseFloat(RedirectTime)/Integer.parseInt(right));
					String FirstByteTimerate  = df.format(Float.parseFloat(FirstByteTime)/Integer.parseInt(right));
					
					String networkBytesrate  = "1";
					if(null != bytes)
					{
						Float size = Float.parseFloat(bytes);
						networkBytesrate = df.format(Float.parseFloat(networkBytes)/size);
					}
					
					String insertsql =  " insert into woasite  " +
					" (taskid,NodeIP,NodeAreaId,NodeISPId,InstanceId,domaintype,serverAreaId,serverISPId," +
					" LocalUrls,LocalRate,downloadtime,downspeed,networkBytes,networkBytesRate," +
					" dnstime,connecttime,firstbytes,RedirectTime)" +
					" values ('"+taskid+"','"+value[2]+"','"+value[0]+"','"+value[1]+"','"+instanceId+"',5,'"+area+"','"+isp+"',"+
					" '"+count+"','"+countrate+"','"+downloadTime+"','"+downloadSpeedrate+"','"+networkBytes+"','"+networkBytesrate+"'," +
					" '"+dnsResolveTotalTimerate+"','"+ConnectionTimerate+"','"+RedirectTimerate+"','"+FirstByteTimerate+"')" ;
					manage.execute(insertsql);
				}
				rs.close();
			}

			manage.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
