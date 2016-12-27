package com.hongganju.woa.stat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hongganju.common.util.DBUtil;
import com.hongganju.common.util.SpringUtil;
import com.hongganju.db.entity.center.Woainstance;
import com.hongganju.db.entity.center.Woatask;
import com.hongganju.db.entity.task.Woadomain;
import com.hongganju.db.entity.task.Woaurl;
import com.hongganju.db.sessionFactory.TaskFactory;

public class StatWOADomain {
	@SuppressWarnings("unchecked")
	public void stat(String instanceId, String areaId, String ispId)
	{
		String sb1="SELECT taskid,clusterid,nodeIp,nodeAreaId,nodeIspid,instanceId,domain,COUNT(url)AS totalUrls,CAST(AVG(downloadSpeed) AS DECIMAL(10,3))AS totalDownloadSpeed,CAST(AVG(downloadTime/1000) AS DECIMAL(10,3))AS totalDownloadTime,CAST(MAX(downloadSpeed) AS DECIMAL(10,3))AS totalMaxSpeed,CAST(MIN(downloadSpeed) AS DECIMAL(10,3))AS totalMinSpeed,CAST(MAX(downloadTime/1000) AS DECIMAL(10,3))AS totalMaxTime,CAST(MIN(downloadTime/1000) AS DECIMAL(10,3))AS totalMinTime,customerId,serverISPId " +
				"FROM Woaurl WHERE instanceId=" +
			instanceId+" AND success=1 and serverISPid IS not NULL GROUP BY domain,serverISPId ORDER BY COUNT(*) DESC";
		String sb2="SELECT taskid,clusterid,nodeIp,nodeAreaId,nodeIspid,instanceId,domain,COUNT(*)AS errorNumber,customerId,serverISPId " +
				"FROM Woaurl WHERE success!=1 AND instanceId=" +
			instanceId+" and serverISPid IS not NULL GROUP BY domain,serverISPId ORDER BY COUNT(*) DESC";
		String sb3="SELECT domain,COUNT(url)AS Localurl,CAST(AVG(downloadSpeed) AS DECIMAL(10,3))AS localDownloadSpeed,CAST(AVG(downloadTime/1000) AS DECIMAL(10,3))AS localDownloadTime,CAST(MAX(downloadSpeed) AS DECIMAL(10,3))AS localMaxSpeed,CAST(MIN(downloadSpeed) AS DECIMAL(10,3))AS localMinSpeed,CAST(MAX(downloadTime/1000) AS DECIMAL(10,3))AS localMaxTime,CAST(MIN(downloadTime/1000) AS DECIMAL(10,3))AS localMinTime,serverISPId " +
				"FROM Woaurl WHERE instanceId=" +
			instanceId+" AND success=1 AND serverAreaId='" +
			areaId+"' AND serverISPId='"+ispId+"' and serverISPid IS not NULL GROUP BY domain,serverISPId ORDER BY COUNT(*) DESC";
		String sb4="SELECT domain,CAST(AVG(downloadSpeed) AS DECIMAL(10,3))AS foreignDownloadSpeed,CAST(AVG(downloadTime/1000) AS DECIMAL(10,3))AS foreignDownloadTime,CAST(MAX(downloadSpeed) AS DECIMAL(10,3))AS foreignMaxSpeed,CAST(MIN(downloadSpeed) AS DECIMAL(10,3))AS foreignMinSpeed,CAST(MAX(downloadTime/1000) AS DECIMAL(10,3))AS foreignMaxTime,CAST(MIN(downloadTime/1000) AS DECIMAL(10,3))AS  foreignMinTime,serverISPId " +
				"FROM Woaurl WHERE instanceId=" +
			instanceId+" AND success=1 AND (serverAreaId!='" +
			areaId+"' or serverISPId!='"+ispId+"') and serverISPid IS not NULL GROUP BY domain,serverISPId ORDER BY COUNT(*) DESC";
		String sb5="SELECT domain ,COUNT(domain)as totalISPNumber " +
				"from Woaurl WHERE instanceId=" +
			instanceId+" and serverISPid IS not NULL GROUP BY domain ORDER BY COUNT(*) DESC";
//		System.out.println("---"+sb5);
		
		SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		String hqlTmp="FROM Woainstance WHERE TaskInstanceID ='"+instanceId+"'";//获取woatask任务id
		Transaction t = session.beginTransaction();
		Query queryTmp = session.createQuery(hqlTmp);
		List<Woainstance> listTmp = queryTmp.list();
		String hql = "FROM Woatask WHERE taskid='"+listTmp.get(0).getTaskid()+"'";//获取dbid
		Query query = session.createQuery(hql);
		List<Woatask> list = query.list();
		t.commit();
		Iterator<Woatask> it = list.iterator();
		short dbid =0;
		while(it.hasNext()){
			Woatask wtask = it.next();
			dbid=wtask.getDbid();
		}
//		dbid=41;//测试使用
		/*
		 * XXAll代表=200的全部，XXError代表！=200，
		 * XXEqual代表=200并两个Area和Isp都等的全部
		 * XXUnequal代表=200并两个XXArea和XXIsp至少一个不等的全部
		 * */
		SessionFactory sessionFactory2 = TaskFactory.getSessionFactory(dbid);
		Session session2 = sessionFactory2.getCurrentSession();
		Transaction t2 = session2.beginTransaction();
		Query queryAll = session2.createSQLQuery(sb1)
			.addScalar("taskid",Hibernate.INTEGER)
			.addScalar("clusterid",Hibernate.INTEGER)
			.addScalar("nodeIp",Hibernate.STRING)
			.addScalar("nodeAreaId",Hibernate.STRING)
			.addScalar("nodeIspid",Hibernate.STRING)
			.addScalar("instanceId",Hibernate.INTEGER)
			.addScalar("domain",Hibernate.STRING)
			.addScalar("totalUrls",Hibernate.INTEGER)
			.addScalar("totalDownloadSpeed",Hibernate.FLOAT)
			.addScalar("totalDownloadTime",Hibernate.FLOAT)
			.addScalar("totalMaxSpeed",Hibernate.FLOAT)
			.addScalar("totalMinSpeed",Hibernate.FLOAT)
			.addScalar("totalMaxTime",Hibernate.FLOAT)
			.addScalar("totalMinTime",Hibernate.FLOAT)
			.addScalar("customerId",Hibernate.SHORT)
			.addScalar("serverISPId",Hibernate.STRING);  //16
		List listAll = queryAll.list();
		Query queryError = session2.createSQLQuery(sb2)
			.addScalar("taskid",Hibernate.INTEGER)
			.addScalar("clusterid",Hibernate.INTEGER)
			.addScalar("nodeIp",Hibernate.STRING)
			.addScalar("nodeAreaId",Hibernate.STRING)
			.addScalar("nodeIspid",Hibernate.STRING)
			.addScalar("instanceId",Hibernate.INTEGER)
			.addScalar("domain",Hibernate.STRING)
			.addScalar("errorNumber",Hibernate.INTEGER)
			.addScalar("customerId",Hibernate.SHORT)
			.addScalar("serverISPId",Hibernate.STRING);//10
		List listError = queryError.list();
		Query queryEqual = session2.createSQLQuery(sb3)
			.addScalar("domain",Hibernate.STRING)
			.addScalar("Localurl",Hibernate.INTEGER)
			.addScalar("localDownloadSpeed",Hibernate.FLOAT)
			.addScalar("localDownloadTime",Hibernate.FLOAT)
			.addScalar("localMaxSpeed",Hibernate.FLOAT)
			.addScalar("localMinSpeed",Hibernate.FLOAT)
			.addScalar("localMaxTime",Hibernate.FLOAT)
			.addScalar("localMinTime",Hibernate.FLOAT)
			.addScalar("serverISPId",Hibernate.STRING); //9
		List listEqual = queryEqual.list();
		Query queryUnequal = session2.createSQLQuery(sb4)
			.addScalar("domain",Hibernate.STRING)
			.addScalar("foreignDownloadSpeed",Hibernate.FLOAT)
			.addScalar("foreignDownloadTime",Hibernate.FLOAT)
			.addScalar("foreignMaxSpeed",Hibernate.FLOAT)
			.addScalar("foreignMinSpeed",Hibernate.FLOAT)
			.addScalar("foreignMaxTime",Hibernate.FLOAT)
			.addScalar("foreignMinTime",Hibernate.FLOAT)
			.addScalar("serverISPId",Hibernate.STRING); //8
		List listUnequal = queryUnequal.list();
		Query queryLocalRate = session2.createSQLQuery(sb5)
		.addScalar("domain",Hibernate.STRING)
		.addScalar("totalISPNumber",Hibernate.FLOAT);
		List listLocalRate = queryLocalRate.list();
		t2.commit();
		int lenAll = listAll.size();
		int lenError = listError.size();
		int lenEqual = listEqual.size();
		int lenUnequal = listUnequal.size();
		int lenLocalRate = listLocalRate.size();
		
		for(int i=0;i<lenAll;i++){
			Woadomain woa= new Woadomain();
			Object[]  objectAll =(Object[]) listAll.get(i);
			Object domain=objectAll[6];
			Object localIsp = objectAll[15];
			woa.setTaskid((Integer)objectAll[0]);//Object taskid=objectAll[0];
			woa.setClusterid((Integer)objectAll[1]);//Object clusterid=objectAll[1];
			woa.setNodeIp((String)objectAll[2]);//Object NodeIP=objectAll[2];
			woa.setNodeAreaId((String)objectAll[3]);//Object NodeAreaId=objectAll[3];
			woa.setNodeIspid((String)objectAll[4]);//Object NodeISPId=objectAll[4];
			woa.setInstanceId((Integer)objectAll[5]);//Object InstanceId=objectAll[5];
			woa.setDomain((String)objectAll[6]);//Object domain=objectAll[6];
			woa.setTotalUrls((Integer)objectAll[7]);//Object totalUrls=objectAll[7];
			woa.setTotalDownloadSpeed((Float)objectAll[8]);//Object totalDownloadSpeed=objectAll[8];
			woa.setTotalDownloadTime((Float)objectAll[9]);//Object totalDownloadTime=objectAll[9];
			woa.setTotalMaxSpeed((Float)objectAll[10]);//Object totalMaxSpeed=objectAll[10];
			woa.setTotalMinSpeed((Float)objectAll[11]);//Object totalMinSpeed=objectAll[11];
			woa.setTotalMaxTime((Float)objectAll[12]);//Object totalMaxTime=objectAll[12];
			woa.setTotalMinTime((Float)objectAll[13]);//Object totalMinTime=objectAll[13];
			woa.setCustomerId((Short)objectAll[14]);//Object customerId=objectAll[14];
			woa.setLocalIsp((String)objectAll[15]);//Object customerId=objectAll[15];
			
			woa.setErrorNumber(0);//默认值
			int errorNumber =0;
			for(int j=0;j<lenError;j++){
				Object[]  objectError =(Object[]) listError.get(j);
				Object localIsp9 = objectError[9];
				if(localIsp9!=null){
					if(!objectError[6].equals(domain)||!localIsp9.equals(localIsp)){//Object domain1=objectAll[0];
						continue;
					}
				}else if(objectError[6]!=null){
					if(!objectError[6].equals(domain)
							||localIsp9!=null){//Object domain1=objectAll[0];
						continue;
					}
				}
				errorNumber=(Integer)objectError[7];
				woa.setErrorNumber(errorNumber);//Object errorNumber=objectAll[1];
				
			}
			for(int m=0;m<lenLocalRate;m++){//在网率,下面的在网率取得时百分制，故乘以100
				Object[]  objectLocalRate =(Object[]) listLocalRate.get(m);
				if(objectLocalRate[0]!=null){
					if(objectLocalRate[0].equals(domain)){
						woa.setLocalRate(round((float)((100*((Integer)objectAll[7]+errorNumber))/(Float)objectLocalRate[1]),3,BigDecimal.ROUND_HALF_UP));//在网率
					}
				}
			}
			woa.setLocalurl(0);//默认值
			woa.setLocalDownloadSpeed(0f);//默认值
			woa.setLocalDownloadTime(0f);//默认值
			woa.setLocalMaxSpeed(0f);//默认值
			woa.setLocalMinSpeed(0f);//默认值
			woa.setLocalMaxTime(0f);//默认值
			woa.setLocalMinTime(0f);//默认值
			//woa.setLocalRate(0f);//默认值
			for(int k=0;k<lenEqual;k++){
				Object[]  objectEqual =(Object[]) listEqual.get(k);
				Object localIsp8 = objectEqual[8];
				if(localIsp8!=null){
					if(!objectEqual[0].equals(domain)||!localIsp8.equals(localIsp)){//Object domain1=objectAll[0];
						continue;
					}
				}else{
					if(!objectEqual[0].equals(domain)||localIsp8!=null){//Object domain1=objectAll[0];
						continue;
					}
				}
				woa.setLocalurl((Integer)objectEqual[1]);//Object Localurl=objectAll[1];
				woa.setLocalDownloadSpeed((Float)objectEqual[2]);//Object localDownloadSpeed=objectAll[2];
				woa.setLocalDownloadTime((Float)objectEqual[3]);//Object localDownloadTime=objectAll[3];
				woa.setLocalMaxSpeed((Float)objectEqual[4]);//Object localMaxSpeed=objectAll[4];
				woa.setLocalMinSpeed((Float)objectEqual[5]);//Object localMinSpeed=objectAll[5];
				woa.setLocalMaxTime((Float)objectEqual[6]);//Object localMaxTime=objectAll[6];
				woa.setLocalMinTime((Float)objectEqual[7]);//Object localMinTime=objectAll[7];
			}
			woa.setForeignDownloadSpeed(0f);//默认值
			woa.setForeignDownloadTime(0f);//默认值
			woa.setForeignMaxSpeed(0f);//默认值
			woa.setForeignMinSpeed(0f);//默认值
			woa.setForeignMaxTime(0f);//默认值
			woa.setForeignMinTime(0f);//默认值
			for(int l=0;l<lenUnequal;l++){
				Object[]  objectUnequal =(Object[]) listUnequal.get(l);
				Object localIsp7 = objectUnequal[7];
				if(localIsp7!=null){
					if(!objectUnequal[0].equals(domain)||!localIsp7.equals(localIsp)){
						continue;
					}
				}else{
					if(!objectUnequal[0].equals(domain)||localIsp7!=null){
						continue;
					}
				}
				woa.setForeignDownloadSpeed((Float)objectUnequal[1]);//Object foreignDownloadSpeed=objectAll[1];
				woa.setForeignDownloadTime((Float)objectUnequal[2]);//Object foreignDownloadTime=objectAll[2];
				woa.setForeignMaxSpeed((Float)objectUnequal[3]);//Object foreignMaxSpeed=objectAll[3];
				woa.setForeignMinSpeed((Float)objectUnequal[4]);//Object foreignMinSpeed=objectAll[4];
				woa.setForeignMaxTime((Float)objectUnequal[5]);//Object foreignMaxTime=objectAll[5];
				woa.setForeignMinTime((Float)objectUnequal[6]);//Object foreignMinTime=objectAll[6];
			}
			Timestamp   s = new java.sql.Timestamp(new Date().getTime());
			woa.setUploadTime(s);
			DBUtil.saveJDBC(woa, dbid);
		}
		for(int i=0;i<lenError;i++){//查询httpcode全部不等于200的情况
			Object[]  objectError =(Object[]) listError.get(i);
			Object domain =objectError[6];
			Object localIsp =objectError[9];
			for(int j=0;j<lenAll;j++){
				Object[]  objectAll =(Object[]) listAll.get(j);
				Object localIsp15=objectAll[15];
				if(localIsp15!=null){
					if(objectAll[6].equals(domain)&&localIsp15.equals(localIsp)){
						break;
					}
				}else{
					if(objectAll[6].equals(domain)&&localIsp==null){
						break;
					}
				}
				if(j==(lenAll-1)){
					Woadomain woad =new Woadomain();
					woad.setTaskid((Integer)objectError[0]);//Object taskid=objectAll[0];
					woad.setClusterid((Integer)objectError[1]);//Object clusterid=objectAll[1];
					woad.setNodeIp((String)objectError[2]);//Object NodeIP=objectAll[2];
					woad.setNodeAreaId((String)objectError[3]);//Object NodeAreaId=objectAll[3];
					woad.setNodeIspid((String)objectError[4]);//Object NodeISPId=objectAll[4];
					woad.setInstanceId((Integer)objectError[5]);//Object InstanceId=objectAll[5];
					woad.setDomain((String)objectError[6]);//Object domain=objectAll[6];
					woad.setErrorNumber((Integer)objectError[7]);//Object errorNumber=objectAll[1];
					woad.setCustomerId((Short)objectError[8]);//Object customerId=objectAll[14];
					woad.setLocalIsp((String)objectError[9]);//Object localIsp=objectError[9];
					for(int m=0;m<lenLocalRate;m++){//在网率,下面的在网率取得时百分制，故乘以100
						Object[]  objectLocalRate =(Object[]) listLocalRate.get(m);
						if(objectLocalRate[0]!=null){
							if(objectLocalRate[0].equals(domain)){
								woad.setLocalRate(round((float)((100*((Integer)objectError[7]))/(Float)objectLocalRate[1]),3,BigDecimal.ROUND_HALF_UP));//在网率
							}
						}
					}
					
					woad.setTotalUrls(0);//默认值
					woad.setTotalDownloadSpeed(0f);//默认值
					woad.setTotalDownloadTime(0f);//默认值
					woad.setTotalMaxSpeed(0f);//默认值
					woad.setTotalMinSpeed(0f);//默认值
					woad.setTotalMaxTime(0f);//默认值
					woad.setTotalMinTime(0f);//默认值
					
					woad.setLocalurl(0);//默认值
					woad.setLocalDownloadSpeed(0f);//默认值
					woad.setLocalDownloadTime(0f);//默认值
					woad.setLocalMaxSpeed(0f);//默认值
					woad.setLocalMinSpeed(0f);//默认值
					woad.setLocalMaxTime(0f);//默认值
					woad.setLocalMinTime(0f);//默认值
					
					
					woad.setForeignDownloadSpeed(0f);//默认值
					woad.setForeignDownloadTime(0f);//默认值
					woad.setForeignMaxSpeed(0f);//默认值
					woad.setForeignMinSpeed(0f);//默认值
					woad.setForeignMaxTime(0f);//默认值
					woad.setForeignMinTime(0f);//默认值
					Timestamp   s = new java.sql.Timestamp(new Date().getTime());
					woad.setUploadTime(s);
					DBUtil.saveJDBC(woad, dbid);
				}
			}
		}
	}
	public static  float round(float value, int scale, int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        float d = bd.floatValue();
        bd = null;
        return d;
    }

	
	public static void main(String[] args) {
		StatWOADomain statwoa = new StatWOADomain();
		System.out.println(args.length);
		if(args.length < 3)
		{
			System.out.println("请输入instanceId, AreaId, ISPID");
			return;
		}
//		statwoa.stat("799", "CN1100", "CN0002");
		statwoa.stat(args[0], args[1], args[2]);

		System.out.println("---------------------------------");
	}
	
}
