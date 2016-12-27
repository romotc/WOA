package statistic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class SiteDomainLeverService {

	protected PropertyUtil propertyUtil=new PropertyUtil();
	protected String url=propertyUtil.getProperty("db.url");
	protected String username=propertyUtil.getProperty("db.user"); 
	protected String password=propertyUtil.getProperty("db.password"); 
	
	public  void SiteStatic(int taskID,int taskInstanceId ,Short DBID){
		//按网站分 1,2,3,4级域名统计
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

	public void statistic(int taskid,int instanceId ,Short dbid){

		DataManager manage = TaskDBByID(dbid);
		try{
			String nodes = " SELECT NodeIP,NodeAreaId,NodeISPId FROM woadomain WHERE InstanceId='"+instanceId+"' GROUP BY NodeIP ";
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
                
                for(int j = 1 ;j < 5;j++)
                {
                	int domaintype = j;
                	
                	String perfors = " SELECT " +
    				" SUM(LocalUrls) AS count, " +
    				" SUM(LocalRights) AS num, " +
    				" SUM(sumdownloadtime) AS downloadTime, " +
    				" SUM(sumdownspeed) AS downloadSpeed, " +
    				" SUM(sumdnstime) AS dnsResolveTotalTime, " +
    				" SUM(sumconnecttime) AS connecttime, " +
    				" SUM(sumRedirectTime) AS RedirectTime, " +
    				" SUM(sumfirstbytes) AS FirstByteTime, " +
    				" SUM(networkBytes) AS networkBytes " +
    				" FROM woadomain WHERE " +
    				" InstanceId='"+instanceId+"' " +
    				" and domaintype = " +domaintype+
    				" and NodeAreaId ='"+value[0]+"' and NodeISPId = '"+value[1]+"' " +
    				" and serverAreaId like '"+prov+"%' and serverISPId = '"+value[1]+"' " +
    				" GROUP BY serverISPId ";
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
    				" SUM(LocalUrls) AS count, " +
    				" SUM(LocalRights) AS num, " +
    				" SUM(sumdownloadtime) AS downloadTime, " +
    				" SUM(sumdownspeed) AS downloadSpeed, " +
    				" SUM(sumdnstime) AS dnsResolveTotalTime, " +
    				" SUM(sumconnecttime) AS connecttime, " +
    				" SUM(sumRedirectTime) AS RedirectTime, " +
    				" SUM(sumfirstbytes) AS FirstByteTime, " +
    				" SUM(networkBytes) AS networkBytes " +
    				" FROM woadomain WHERE " +
    				" InstanceId='"+instanceId+"' " +
    				" and domaintype = " +domaintype+
    				" and NodeAreaId ='"+value[0]+"' and NodeISPId = '"+value[1]+"' " +
    				" and serverAreaId not like '"+prov+"%' and serverISPId = '"+value[1]+"' " +
    				" GROUP BY serverISPId ";
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
                        
    					obj1.put("count", count);
    					obj1.put("num", num);
    					obj1.put("downloadTime", downloadTime);
    					obj1.put("downloadSpeed", downloadSpeed);
    					obj.put("dnsResolveTotalTime", dnsResolveTotalTime);
    					obj.put("ConnectionTime", ConnectionTime);
    					obj.put("RedirectTime", RedirectTime);
    					obj.put("FirstByteTime", FirstByteTime);
    					obj1.put("networkBytes", networkBytes);
    				}
    				rs.close();
    				perfors = " SELECT " +
    				" SUM(LocalUrls) AS count, " +
    				" SUM(LocalRights) AS num, " +
    				" SUM(sumdownloadtime) AS downloadTime, " +
    				" SUM(sumdownspeed) AS downloadSpeed, " +
    				" SUM(sumdnstime) AS dnsResolveTotalTime, " +
    				" SUM(sumconnecttime) AS connecttime, " +
    				" SUM(sumRedirectTime) AS RedirectTime, " +
    				" SUM(sumfirstbytes) AS FirstByteTime, " +
    				" SUM(networkBytes) AS networkBytes, " +
    				" serverISPId " +
    				" FROM woadomain WHERE " +
    				" InstanceId='"+instanceId+"' " +
    				" and domaintype = " +domaintype+
    				" and NodeAreaId ='"+value[0]+"' and NodeISPId = '"+value[1]+"' " +
    				" and serverISPId != '"+value[1]+"' " +
    				" GROUP BY serverISPId ";
    				rs = manage.executeQuery(perfors);
    				JSONArray array = new JSONArray();
    				while (rs.next()) {
    					String count = rs.getString(1);
    					String num = rs.getString(2);
    					String downloadTime = rs.getString(3);
    					String downloadSpeed = rs.getString(4);	String dnsResolveTotalTime = rs.getString(5);
    					String ConnectionTime = rs.getString(6);
    					String RedirectTime = rs.getString(7);
    					String FirstByteTime = rs.getString(8);
    					String networkBytes = rs.getString(9);
    					String serverISPId = rs.getString(10);

    					JSONObject obj2 = new JSONObject();
    					obj2.put("count", count);
    					obj2.put("num", num);
    					obj2.put("downloadTime", downloadTime);
    					obj2.put("downloadSpeed", downloadSpeed);
    					obj.put("dnsResolveTotalTime", dnsResolveTotalTime);
    					obj.put("ConnectionTime", ConnectionTime);
    					obj.put("RedirectTime", RedirectTime);
    					obj.put("FirstByteTime", FirstByteTime);
    					obj2.put("networkBytes", networkBytes);
    					obj2.put("serverISPId", serverISPId);
    					array.add(obj2);

    				}
    				rs.close();
    				int counts = 0;
    				int num = 0;
    				Float size = 0.00f;
    				if(null != obj.get("num"))
    				{
    					counts += Integer.parseInt(obj.get("count").toString());
    					num += Integer.parseInt(obj.get("num").toString());
    					String networkBytes = obj.get("networkBytes").toString();
    					if(null != networkBytes)
    					size += Float.parseFloat(networkBytes);
    				}
    				if(null != obj1.get("num"))
    				{
    					counts += Integer.parseInt(obj1.get("count").toString());
    					num += Integer.parseInt(obj1.get("num").toString());
    					String networkBytes = obj1.get("networkBytes").toString();
    					if(null != networkBytes)
    					size += Float.parseFloat(networkBytes);
    				}
    				if(array.size() > 0){
    					for(int i = 0;i < array.size();i++){
    						JSONObject obj2 = (JSONObject) array.get(i);
    						if(null != obj2.get("num"))
    						{
    							counts += Integer.parseInt(obj2.get("count").toString());
    							num += Integer.parseInt(obj2.get("num").toString());
    							String networkBytes = obj2.get("networkBytes").toString();
    	    					if(null != networkBytes)
    	    					size += Float.parseFloat(networkBytes);
    						}
    					}
    				}
    				DecimalFormat  df  = new  DecimalFormat("0.000");
    				if(null != obj.get("count"))
    				{
    						String count = obj.get("count").toString();
    						String right  = obj.get("num").toString();
    						String countrate  = df.format(Float.parseFloat(count)/counts);
    						String downloadTime = obj.get("downloadTime").toString();
    						//String downloadTimerate  = df.format(Float.parseFloat(downloadTime)/Integer.parseInt(right));
    						String downloadSpeed = obj.get("downloadSpeed").toString();
    						String downloadSpeedrate  = df.format(Float.parseFloat(downloadSpeed)/Integer.parseInt(right));
    						
    						String dnsResolveTotalTime = "0";
							if(null != obj.get("dnsResolveTotalTime"))
							       dnsResolveTotalTime = obj.get("dnsResolveTotalTime").toString();
							String dnsResolveTotalTimerate  = df.format(Float.parseFloat(dnsResolveTotalTime)/Integer.parseInt(right));
							String ConnectionTime = "0";
							if(null != obj.get("ConnectionTime"))
							       ConnectionTime = obj.get("ConnectionTime").toString();
							String ConnectionTimerate  = df.format(Float.parseFloat(ConnectionTime)/Integer.parseInt(right));
							String RedirectTime = "0";
							if(null != obj.get("RedirectTime"))
								RedirectTime = obj.get("RedirectTime").toString();
							String RedirectTimerate  = df.format(Float.parseFloat(RedirectTime)/Integer.parseInt(right));
							String FirstByteTime = "0";
							if(null != obj.get("FirstByteTime"))
								  FirstByteTime = obj.get("FirstByteTime").toString();
							String FirstByteTimerate  = df.format(Float.parseFloat(FirstByteTime)/Integer.parseInt(right));
    						
    						String networkBytes = obj.get("networkBytes").toString();
    						String networkBytesrate  = df.format(Float.parseFloat(networkBytes)/size);
    						String insertsql =  " insert into woasite  " +
    						" (taskid,NodeIP,NodeAreaId,NodeISPId,InstanceId,domaintype,serverAreaId,serverISPId," +
    						" LocalUrls,LocalRate,downloadtime,downspeed,networkBytes,networkBytesRate," +
    						" dnstime,connecttime,firstbytes,RedirectTime)" +
							" values ('"+taskid+"','"+value[2]+"','"+value[0]+"','"+value[1]+"','"+instanceId+"','"+domaintype+"','"+prov+"00','"+value[1]+"',"+
    						" '"+count+"','"+countrate+"','"+downloadTime+"','"+downloadSpeedrate+"','"+networkBytes+"','"+networkBytesrate+"'," +
    						" '"+dnsResolveTotalTimerate+"','"+ConnectionTimerate+"','"+RedirectTimerate+"','"+FirstByteTimerate+"')" ;
    						manage.execute(insertsql);
    				}
    				if(null != obj1.get("count"))
    				{
    						String count = obj1.get("count").toString();
    						String right  = obj1.get("num").toString();
    						String countrate  = df.format(Float.parseFloat(count)/counts);
    						String downloadTime = obj1.get("downloadTime").toString();
    						//String downloadTimerate  = df.format(Float.parseFloat(downloadTime)/num);
    						String downloadSpeed = obj1.get("downloadSpeed").toString();
    						String downloadSpeedrate  = df.format(Float.parseFloat(downloadSpeed)/Integer.parseInt(right));
    						
    						String dnsResolveTotalTime = "0";
							if(null != obj1.get("dnsResolveTotalTime"))
							       dnsResolveTotalTime = obj1.get("dnsResolveTotalTime").toString();
							String dnsResolveTotalTimerate  = df.format(Float.parseFloat(dnsResolveTotalTime)/Integer.parseInt(right));
							String ConnectionTime = "0";
							if(null != obj1.get("ConnectionTime"))
							       ConnectionTime = obj1.get("ConnectionTime").toString();
							String ConnectionTimerate  = df.format(Float.parseFloat(ConnectionTime)/Integer.parseInt(right));
							String RedirectTime = "0";
							if(null != obj1.get("RedirectTime"))
								RedirectTime = obj1.get("RedirectTime").toString();
							String RedirectTimerate  = df.format(Float.parseFloat(RedirectTime)/Integer.parseInt(right));
							String FirstByteTime = "0";
							if(null != obj1.get("FirstByteTime"))
								  FirstByteTime = obj1.get("FirstByteTime").toString();
							String FirstByteTimerate  = df.format(Float.parseFloat(FirstByteTime)/Integer.parseInt(right));
    						
    						String networkBytes = obj1.get("networkBytes").toString();
    						String networkBytesrate  = df.format(Float.parseFloat(networkBytes)/size);
    						String insertsql =  " insert into woasite  " +
    						" (taskid,NodeIP,NodeAreaId,NodeISPId,InstanceId,domaintype,serverAreaId,serverISPId," +
    						" LocalUrls,LocalRate,downloadtime,downspeed,networkBytes,networkBytesRate," +
    						" dnstime,connecttime,firstbytes,RedirectTime)" +
							" values ('"+taskid+"','"+value[2]+"','"+value[0]+"','"+value[1]+"','"+instanceId+"','"+domaintype+"','CN0000','"+value[1]+"',"+
    						" '"+count+"','"+countrate+"','"+downloadTime+"','"+downloadSpeedrate+"','"+networkBytes+"','"+networkBytesrate+"'," +
    						" '"+dnsResolveTotalTimerate+"','"+ConnectionTimerate+"','"+RedirectTimerate+"','"+FirstByteTimerate+"')" ;

    						manage.execute(insertsql);
    				}
    				if(array.size() > 0){
    					for(int i = 0;i < array.size();i++){
    						JSONObject obj2 = (JSONObject) array.get(i);
    						if(null != obj2.get("count"))
    						{
    							String count = obj2.get("count").toString();
    							String right = obj2.get("num").toString();
    							String countrate  = df.format(Float.parseFloat(count)/counts);
    							String downloadTime = obj2.get("downloadTime").toString();
    							//String downloadTimerate  = df.format(Float.parseFloat(downloadTime)/num);
    							String downloadSpeed = obj2.get("downloadSpeed").toString();
    							String downloadSpeedrate  = df.format(Float.parseFloat(downloadSpeed)/Integer.parseInt(right));
    							
    							String dnsResolveTotalTime = "0";
    							if(null != obj2.get("dnsResolveTotalTime"))
    							       dnsResolveTotalTime = obj2.get("dnsResolveTotalTime").toString();
    							String dnsResolveTotalTimerate  = df.format(Float.parseFloat(dnsResolveTotalTime)/Integer.parseInt(right));
    							String ConnectionTime = "0";
    							if(null != obj2.get("ConnectionTime"))
    							       ConnectionTime = obj2.get("ConnectionTime").toString();
    							String ConnectionTimerate  = df.format(Float.parseFloat(ConnectionTime)/Integer.parseInt(right));
    							String RedirectTime = "0";
    							if(null != obj2.get("RedirectTime"))
    								RedirectTime = obj2.get("RedirectTime").toString();
    							String RedirectTimerate  = df.format(Float.parseFloat(RedirectTime)/Integer.parseInt(right));
    							String FirstByteTime = "0";
    							if(null != obj2.get("FirstByteTime"))
    								  FirstByteTime = obj2.get("FirstByteTime").toString();
    							String FirstByteTimerate  = df.format(Float.parseFloat(FirstByteTime)/Integer.parseInt(right));
    							
    							String networkBytes = obj2.get("networkBytes").toString();
    							String networkBytesrate  = df.format(Float.parseFloat(networkBytes)/size);
    							String serverISPId = obj2.get("serverISPId").toString();
    							String insertsql =  " insert into woasite  " +
    							" (taskid,NodeIP,NodeAreaId,NodeISPId,InstanceId,domaintype,serverAreaId,serverISPId," +
    							" LocalUrls,LocalRate,downloadtime,downspeed,networkBytes,networkBytesRate," +
    							" dnstime,connecttime,firstbytes,RedirectTime)" +
    							" values ('"+taskid+"','"+value[2]+"','"+value[0]+"','"+value[1]+"','"+instanceId+"','"+domaintype+"','CN0000','"+serverISPId+"',"+
    							" '"+count+"','"+countrate+"','"+downloadTime+"','"+downloadSpeedrate+"','"+networkBytes+"','"+networkBytesrate+"'," +
        						" '"+dnsResolveTotalTimerate+"','"+ConnectionTimerate+"','"+RedirectTimerate+"','"+FirstByteTimerate+"')" ;

    							manage.execute(insertsql);
    						}
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
