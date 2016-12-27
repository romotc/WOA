package statistic;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaticWork {

//	public void workRun(Woatask task,Woainstance instance)
//	{
//		try 
//		{
//			System.out.println("task:"+task.getTaskid()+",instance:"+instance.getTaskinstanceId()+",begin static:");
//			if(task.getTaskType() == 1)//普通网页
//			{
//				DomainService domain = new DomainService();
//				domain.DomainStatic(task.getTaskid(),instance.getTaskinstanceId(),task.getDbid());
//
//				AreaIspService sitepop = new AreaIspService();
//				sitepop.sitePop(task.getTaskid(),instance.getTaskinstanceId(),task.getDbid());
//
//				SiteDomainLeverService siteStatic = new SiteDomainLeverService();
//				siteStatic.SiteStatic(task.getTaskid(),instance.getTaskinstanceId(),task.getDbid());
//
//				SiteIspService siteall = new SiteIspService();
//				siteall.siteAll(task.getTaskid(),instance.getTaskinstanceId(),task.getDbid());
//			}
//			else if(task.getTaskType() == 2)//流媒体任务
//			{
//				FlvSiteService flv = new FlvSiteService();
//				flv.FlvStatic(task.getTaskid(),instance.getTaskinstanceId(),task.getDbid());
//			}
//		}catch(Exception e)
//		{
//			System.out.println("task:"+task.getTaskid()+",instance:"+instance.getTaskinstanceId()+",error");
//		}
//		finally {
//
//			System.out.println("task:"+task.getTaskid()+",instance:"+instance.getTaskinstanceId()+",end static");
//		}
//	}




	public static void main(String[] args)
	{
		PropertyUtil propertyUtil=new PropertyUtil();
		String url=propertyUtil.getProperty("db.url");
		String username=propertyUtil.getProperty("db.user"); 
		String password=propertyUtil.getProperty("db.password"); 

		DataManager manage= new DataManager(url,username,password);

		String[] instanceIds = "111".split(",");
		for(int i = 0 ;i< instanceIds.length;i++)
		{
			int instanceId = Integer.parseInt(instanceIds[i]);
			int taskId = 0;
			int type = 0;
			Short dbId = 0;

			String sql = "select b.taskid,b.taskType,b.DBID from woainstance a,woatask b where a.TaskinstanceId = '"+instanceId+"' and a.taskid=b.taskid";
			try {
				ResultSet rs = manage.executeQuery(sql);
				while(rs.next()){
					taskId = Integer.parseInt(rs.getString(1));
					type  = Integer.parseInt(rs.getString(2));
					dbId  = Short.parseShort(rs.getString(3));
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(manage != null)
					manage.closeConn();
			}


			System.out.println("task:"+taskId+",instance:"+instanceId+",begin static:");
			try 
			{
				if(type == 1)//普通网页
				{
					DomainService domain = new DomainService();
					domain.DomainStatic(taskId,instanceId,dbId);

					AreaIspService sitepop = new AreaIspService();
					sitepop.sitePop(taskId,instanceId,dbId);

					SiteDomainLeverService siteStatic = new SiteDomainLeverService();
					siteStatic.SiteStatic(taskId,instanceId,dbId);

					SiteIspService siteall = new SiteIspService();
					siteall.siteAll(taskId,instanceId,dbId);
				}
				else if(type == 2)//流媒体任务
				{
					FlvSiteService flv = new FlvSiteService();
					flv.FlvStatic(taskId,instanceId,dbId);
				}
			}catch(Exception e)
			{
			    e.printStackTrace();
				System.out.println("task:"+taskId+",instance:"+instanceId+",error");
			}
			finally {

				System.out.println("task:"+taskId+",instance:"+instanceId+",end static");
			}
		}
	}
}
