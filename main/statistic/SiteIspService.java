package statistic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class SiteIspService {

	protected PropertyUtil propertyUtil=new PropertyUtil();
	protected String url=propertyUtil.getProperty("db.url");
	protected String username=propertyUtil.getProperty("db.user"); 
	protected String password=propertyUtil.getProperty("db.password"); 
	
	public  void siteAll(int taskID,int taskInstanceId ,Short DBID){
		//按网站分运营商统计
		statistic(taskID,taskInstanceId,DBID);// 任务ID，实例ID，dbid
	}
	
	public DataManager TaskDBByID(Short id)
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
				String prov = value[0].substring(0, 4);

				String perfors = " SELECT " +
				" COUNT(Id) AS count," +
				" COUNT(IF((httpCode = 200),1 , 0)) AS num," +
				" SUM(CAST(networkBytes / 1024 / 1024  AS DECIMAL(10,3))) AS networkBytes " +
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

				perfors = " SELECT " +
				" COUNT(uploadTime) AS count, " +
				" COUNT(IF((httpCode = 200),1 , 0)) AS num," +
				" SUM(CAST(downloadTime / 1000 AS DECIMAL(10,3))) AS downloadTime, " +
				" SUM(CAST(downloadSpeed  AS DECIMAL(10,3))) AS downloadSpeed, " +
				" SUM(CAST(dnsResolveTotalTime / 1000 AS DECIMAL(10,3))) AS dnsResolveTotalTime, " +
				" SUM(CAST(ConnectionTime / 1000 AS DECIMAL(10,3))) AS ConnectionTime, " +
				" SUM(CAST(RedirectTime / 1000 AS DECIMAL(10,3))) AS RedirectTime, " +
				" SUM(CAST(FirstByteTime / 1000 AS DECIMAL(10,3))) AS FirstByteTime, " +
				" SUM(CAST(networkBytes / 1024 / 1024 AS DECIMAL(10,3))) AS networkBytes " +
				" FROM woaurl WHERE " +
				" InstanceId='"+instanceId+"' " +
				" and NodeAreaId ='"+value[0]+"' and NodeISPId = '"+value[1]+"' " +
				" and serverAreaId like '"+prov+"%' and serverISPId = '"+value[1]+"' " +
				" GROUP BY InstanceId ";
				rs = manage.executeQuery(perfors);
				JSONObject obj = new JSONObject();
				while (rs.next()) {
					String count = rs.getString(1);
					String num = rs.getString(2);
					String downloadTime = rs.getString(3);
					String downloadSpeed = rs.getString(4);
					String dnsResolveTotalTime = rs.getString(5);
					String ConnectionTime = rs.getString(6);
					String RedirectTime = rs.getString(7);
					String FirstByteTime = rs.getString(8);
					String networkBytes = rs.getString(9);

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

					obj.put("count", count);
					obj.put("num", num);
					obj.put("downloadTime", downloadTime);
					obj.put("downloadSpeed", downloadSpeed);
					obj.put("dnsResolveTotalTime", dnsResolveTotalTime);
					obj.put("ConnectionTime", ConnectionTime);
					obj.put("RedirectTime", RedirectTime);
					obj.put("FirstByteTime", FirstByteTime);
					obj.put("networkBytes", networkBytes);
				}
				rs.close();
				perfors = " SELECT " +
				" COUNT(uploadTime) AS count, " +
				" COUNT(IF((httpCode = 200),1 , 0)) AS num," +
				" SUM(CAST(downloadTime / 1000 AS DECIMAL(10,3))) AS downloadTime, " +
				" SUM(CAST(downloadSpeed  AS DECIMAL(10,3))) AS downloadSpeed, " +
				" SUM(CAST(dnsResolveTotalTime / 1000 AS DECIMAL(10,3))) AS dnsResolveTotalTime, " +
				" SUM(CAST(ConnectionTime / 1000 AS DECIMAL(10,3))) AS ConnectionTime, " +
				" SUM(CAST(RedirectTime / 1000 AS DECIMAL(10,3))) AS RedirectTime, " +
				" SUM(CAST(FirstByteTime / 1000 AS DECIMAL(10,3))) AS FirstByteTime, " +
				" SUM(CAST(networkBytes / 1024 / 1024 AS DECIMAL(10,3))) AS networkBytes " +
				" FROM woaurl WHERE " +
				" InstanceId='"+instanceId+"' " +
				" and NodeAreaId ='"+value[0]+"' and NodeISPId = '"+value[1]+"' " +
				" and serverAreaId not like '"+prov+"%' and serverISPId = '"+value[1]+"' " +
				" GROUP BY InstanceId ";
				rs = manage.executeQuery(perfors);
				JSONObject obj1 = new JSONObject();
				while (rs.next()) {
					String count = rs.getString(1);
					String num = rs.getString(2);
					String downloadTime = rs.getString(3);
					String downloadSpeed = rs.getString(4);
					String dnsResolveTotalTime = rs.getString(5);
					String ConnectionTime = rs.getString(6);
					String RedirectTime = rs.getString(7);
					String FirstByteTime = rs.getString(8);
					String networkBytes = rs.getString(9);

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


					obj1.put("count", count);
					obj1.put("num", num);
					obj1.put("downloadTime", downloadTime);
					obj1.put("downloadSpeed", downloadSpeed);
					obj1.put("dnsResolveTotalTime", dnsResolveTotalTime);
					obj1.put("ConnectionTime", ConnectionTime);
					obj1.put("RedirectTime", RedirectTime);
					obj1.put("FirstByteTime", FirstByteTime);
					obj1.put("networkBytes", networkBytes);

				}
				rs.close();
				perfors = " SELECT " +
				" COUNT(uploadTime) AS count, " +
				" COUNT(IF((httpCode = 200),1 , 0)) AS num," +
				" SUM(CAST(downloadTime / 1000 AS DECIMAL(10,3))) AS downloadTime, " +
				" SUM(CAST(downloadSpeed  AS DECIMAL(10,3))) AS downloadSpeed, " +
				" SUM(CAST(dnsResolveTotalTime / 1000 AS DECIMAL(10,3))) AS dnsResolveTotalTime, " +
				" SUM(CAST(ConnectionTime / 1000 AS DECIMAL(10,3))) AS ConnectionTime, " +
				" SUM(CAST(RedirectTime / 1000 AS DECIMAL(10,3))) AS RedirectTime, " +
				" SUM(CAST(FirstByteTime / 1000 AS DECIMAL(10,3))) AS FirstByteTime, " +
				" SUM(CAST(networkBytes / 1024 / 1024 AS DECIMAL(10,3))) AS networkBytes, " +
				" serverISPId " +
				" FROM woaurl WHERE " +
				" InstanceId='"+instanceId+"' " +
				" and NodeAreaId ='"+value[0]+"' and NodeISPId = '"+value[1]+"' " +
				" and serverISPId != '"+value[1]+"' " +
				" GROUP BY InstanceId,serverISPId ";
				rs = manage.executeQuery(perfors);

				JSONArray array = new JSONArray();
				while (rs.next()) {
					String count = rs.getString(1);
					String num = rs.getString(2);
					String downloadTime = rs.getString(3);
					String downloadSpeed = rs.getString(4);
					String dnsResolveTotalTime = rs.getString(5);
					String ConnectionTime = rs.getString(6);
					String RedirectTime = rs.getString(7);
					String FirstByteTime = rs.getString(8);
					String networkBytes = rs.getString(9);
					String serverISPId = rs.getString(10);

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

					JSONObject obj3 = new JSONObject();
					obj3.put("count", count);
					obj3.put("num", num);
					obj3.put("downloadTime", downloadTime);
					obj3.put("downloadSpeed", downloadSpeed);
					obj3.put("dnsResolveTotalTime", dnsResolveTotalTime);
					obj3.put("ConnectionTime", ConnectionTime);
					obj3.put("RedirectTime", RedirectTime);
					obj3.put("FirstByteTime", FirstByteTime);
					obj3.put("networkBytes", networkBytes);
					obj3.put("serverISPId", serverISPId);
					array.add(obj3);

				}
				rs.close();
				DecimalFormat  df  = new  DecimalFormat("0.000");

				int counts = Integer.parseInt(nums[0]);//全部
				//int num = Integer.parseInt(nums[1]);//正确
				String bytes = nums[2];
				if(null != obj.get("count"))
				{
					String count = obj.get("count").toString();
					String right = obj.get("num").toString();
					String countrate  = df.format(Float.parseFloat(count)/counts);
					String downloadTime = obj.get("downloadTime").toString();
					//String downloadTimerate  = df.format(Float.parseFloat(downloadTime)/Integer.parseInt(right));
					String downloadSpeed = obj.get("downloadSpeed").toString();
					String downloadSpeedrate  = df.format(Float.parseFloat(downloadSpeed)/Integer.parseInt(right));
					
					String dnsResolveTotalTime = obj.get("dnsResolveTotalTime").toString();
					String dnsResolveTotalTimerate  = df.format(Float.parseFloat(dnsResolveTotalTime)/Integer.parseInt(right));
					String ConnectionTime = obj.get("ConnectionTime").toString();
					String ConnectionTimerate  = df.format(Float.parseFloat(ConnectionTime)/Integer.parseInt(right));
					String RedirectTime = obj.get("RedirectTime").toString();
					String RedirectTimerate  = df.format(Float.parseFloat(RedirectTime)/Integer.parseInt(right));
					String FirstByteTime = obj.get("FirstByteTime").toString();
					String FirstByteTimerate  = df.format(Float.parseFloat(FirstByteTime)/Integer.parseInt(right));
					
					String networkBytes = obj.get("networkBytes").toString();

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
					" values ('"+taskid+"','"+value[2]+"','"+value[0]+"','"+value[1]+"','"+instanceId+"',0,'"+prov+"00','"+value[1]+"',"+
					" '"+count+"','"+countrate+"','"+downloadTime+"','"+downloadSpeedrate+"','"+networkBytes+"','"+networkBytesrate+"'," +
					" '"+dnsResolveTotalTimerate+"','"+ConnectionTimerate+"','"+RedirectTimerate+"','"+FirstByteTimerate+"')" ;
					manage.execute(insertsql);
				}


				if(null != obj1.get("count"))
				{
					String count = obj1.get("count").toString();
					String right = obj1.get("num").toString();
					String countrate  = df.format(Float.parseFloat(count)/counts);
					String downloadTime = obj1.get("downloadTime").toString();
					//String downloadTimerate  = df.format(Float.parseFloat(downloadTime)/Integer.parseInt(right));
					String downloadSpeed = obj1.get("downloadSpeed").toString();
					

					String dnsResolveTotalTime = obj1.get("dnsResolveTotalTime").toString();
					String dnsResolveTotalTimerate  = df.format(Float.parseFloat(dnsResolveTotalTime)/Integer.parseInt(right));
					String ConnectionTime = obj1.get("ConnectionTime").toString();
					String ConnectionTimerate  = df.format(Float.parseFloat(ConnectionTime)/Integer.parseInt(right));
					String RedirectTime = obj1.get("RedirectTime").toString();
					String RedirectTimerate  = df.format(Float.parseFloat(RedirectTime)/Integer.parseInt(right));
					String FirstByteTime = obj1.get("FirstByteTime").toString();
					String FirstByteTimerate  = df.format(Float.parseFloat(FirstByteTime)/Integer.parseInt(right));
					
					
					String downloadSpeedrate  = df.format(Float.parseFloat(downloadSpeed)/Integer.parseInt(right));
					String networkBytes = obj1.get("networkBytes").toString();

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
					" values ('"+taskid+"','"+value[2]+"','"+value[0]+"','"+value[1]+"','"+instanceId+"',0,'CN0000','"+value[1]+"',"+
					" '"+count+"','"+countrate+"','"+downloadTime+"','"+downloadSpeedrate+"','"+networkBytes+"','"+networkBytesrate+"'," +
					" '"+dnsResolveTotalTimerate+"','"+ConnectionTimerate+"','"+RedirectTimerate+"','"+FirstByteTimerate+"')" ;

					manage.execute(insertsql);

				}

				if(array.size() > 0){
					for(int i = 0 ; i < array.size();i++ )
					{
						JSONObject obj2 = (JSONObject) array.get(i);
						if(null != obj2.get("count"))
						{
							String count = obj2.get("count").toString();
							String right = obj2.get("num").toString();
							String countrate  = df.format(Float.parseFloat(count)/counts);
							String downloadTime = obj2.get("downloadTime").toString();
							//String downloadTimerate  = df.format(Float.parseFloat(downloadTime)/Integer.parseInt(right));
							String downloadSpeed = obj2.get("downloadSpeed").toString();
							String downloadSpeedrate  = df.format(Float.parseFloat(downloadSpeed)/Integer.parseInt(right));
							
							String dnsResolveTotalTime = obj2.get("dnsResolveTotalTime").toString();
							String dnsResolveTotalTimerate  = df.format(Float.parseFloat(dnsResolveTotalTime)/Integer.parseInt(right));
							String ConnectionTime = obj2.get("ConnectionTime").toString();
							String ConnectionTimerate  = df.format(Float.parseFloat(ConnectionTime)/Integer.parseInt(right));
							String RedirectTime = obj2.get("RedirectTime").toString();
							String RedirectTimerate  = df.format(Float.parseFloat(RedirectTime)/Integer.parseInt(right));
							String FirstByteTime = obj2.get("FirstByteTime").toString();
							String FirstByteTimerate  = df.format(Float.parseFloat(FirstByteTime)/Integer.parseInt(right));
							
							String networkBytes = obj2.get("networkBytes").toString();
							//String isp = obj2.get("serverISPId").toString();
							String networkBytesrate  = "1";
							if(null != bytes)
							{
								Float size = Float.parseFloat(bytes);
								networkBytesrate = df.format(Float.parseFloat(networkBytes)/size);
							}
							String serverISPId = obj2.get("serverISPId").toString();
							
							String insertsql =  " insert into woasite  " +
							" (taskid,NodeIP,NodeAreaId,NodeISPId,InstanceId,domaintype,serverAreaId,serverISPId," +
							" LocalUrls,LocalRate,downloadtime,downspeed,networkBytes,networkBytesRate," +
							" dnstime,connecttime,firstbytes,RedirectTime)" +
							" values ('"+taskid+"','"+value[2]+"','"+value[0]+"','"+value[1]+"','"+instanceId+"',0,'CN0000','"+serverISPId+"',"+
							" '"+count+"','"+countrate+"','"+downloadTime+"','"+downloadSpeedrate+"','"+networkBytes+"','"+networkBytesrate+"'," +
    						" '"+dnsResolveTotalTimerate+"','"+ConnectionTimerate+"','"+RedirectTimerate+"','"+FirstByteTimerate+"')" ;

							manage.execute(insertsql);
						}
					}
				}



			}

			manage.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
