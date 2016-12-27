package com.hongganju.woa.spider;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import weblech.spider.Spider;

import com.hongganju.common.util.DBUtil;
import com.hongganju.common.util.Log4JIniter;
import com.hongganju.db.entity.task.Woadomain;
import com.hongganju.node.NodeConfig;
import com.hongganju.woa.domain.DomainStatData;

//还要再加上排除的域名，防止指向高级的域名
//Webblech的Wrapper
//每个任务开一个Spider
public class WOASpider {
	static{
		Log4JIniter.init("config");
	}
	public static final Logger logger = Logger.getLogger(WOASpider.class);
	public WOASpiderParam param = null;
	public Spider spider;
	//url总数
	public int totalUrl = -1;
	public int shceduledUlrs = 0;
	//已经采集到的url数量(在WebBrowserBase里计数)
	public int crawledUrls = 0;
	//下一个url，当前爬到的位置(用于断点续传)(在WebBrowserBase里计数)
	public int currentUrl = 0;
	//写crawledUrl的计数器(在写db时计数，DBSaver)
	public int crawledUrlsCounter = 0;
	
	//运行浏览器的时间，由于ie吃cpu太高，要尽量保持不要并行
	public long lastBrowseTime = 0;
	
	//已经爬到的objects(目前还没有使用)
	public int crawledObjects = 0;

	public HashMap<String, DomainStatData> domainStats = new HashMap<String, DomainStatData>();
	public WOASpider(WOASpiderParam param)
	{
		this.param = param;
		spider = new Spider(WOAConfig.config, param, this);
		this.totalUrl = param.getTotalUrls();
	}
	public WOASpiderParam getParam()
	{
		return this.param;
	}
	
	//启动，注意是每个任务开一个Spider
	public void start()
	{
		logger.info("Start new Task: taskId=" + this.param.taskId + " instanceId=" + this.param.taskInstanceId + 
				" seed=" + this.param.getSeed() + " totalUrls=" + this.param.totalUrls);
		spider.start();
	}
	
	//统计所有
	public void statAll()
	{
		System.out.println("开始统计");

		Iterator iter = domainStats.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String domain = (String) entry.getKey();
			DomainStatData stat = (DomainStatData) entry.getValue();
			statDomain(stat, domain);
		}
		
		System.out.println("统计结束");
	}
	private void statDomain(DomainStatData stat, String domain) {
		// TODO Auto-generated method stub
		Woadomain woa = new Woadomain();
		woa.setClusterid(NodeConfig.getClusterId());
		woa.setCustomerId(this.param.customerId);
		woa.setDomain(domain);
		woa.setErrorNumber(stat.errorNumber);
		woa.setInstanceId(this.param.taskInstanceId);
		woa.setNodeAreaId(NodeConfig.getArea());
		woa.setNodeIp(NodeConfig.getIp());
		woa.setNodeIspid(NodeConfig.getIsp());
		woa.setTaskid(this.param.taskId);
		woa.setUploadTime(new Date());
		
		float forgein = stat.totalUrls - stat.errorNumber - stat.localurl;
		float local = stat.localurl;
		float total = forgein + local;
		woa.setTotalUrls((int)total);
		woa.setLocalurl((int)local);
		if(total != 0)
			woa.setLocalRate(local/total);
		else
			woa.setLocalRate((float)0.0);
		
		
		if(stat.foreignDownloadTime != 0)
			woa.setForeignDownloadSpeed(stat.foreignDownloadSpeed/(float)stat.foreignDownloadTime);
		else
			woa.setForeignDownloadSpeed((float)0.0);
		if(forgein != 0)
		{
			woa.setForeignDownloadTime(stat.foreignDownloadTime/forgein);
		}
		else
			woa.setForeignDownloadTime((float)0.0);
		
		if(forgein == 0.0)
		{
			woa.setForeignMaxSpeed((float)0.0);
			woa.setForeignMinSpeed((float)0.0);
			woa.setForeignMaxTime((float)0.0);
			woa.setForeignMinTime((float)0.0);
		}
		else
		{
			woa.setForeignMaxSpeed(stat.foreignMaxSpeed);
			woa.setForeignMinSpeed(stat.foreignMinSpeed);
			woa.setForeignMaxTime((float) stat.foreignMaxTime);
			woa.setForeignMinTime((float) stat.foreignMinTime);
		}
		
		
		
		if(stat.localDownloadTime != 0)
			woa.setLocalDownloadSpeed(stat.localDownloadSpeed/(float)stat.localDownloadTime);
		else
			woa.setLocalDownloadSpeed((float)0.0);
		if(local != 0)
		{
			woa.setLocalDownloadTime(stat.localDownloadTime/local);
		}else
			woa.setLocalDownloadTime((float)0.0);
		
		if(local <= 0.0)
		{
			woa.setLocalMaxSpeed((float)0.0);
			woa.setLocalMinSpeed((float)0.0);
			woa.setLocalMaxTime((float)0.0);
			woa.setLocalMinTime((float)0.0);
		}
		else
		{
			woa.setLocalMaxSpeed(stat.localMaxSpeed);
			woa.setLocalMinSpeed(stat.localMinSpeed);
			woa.setLocalMaxTime((float) stat.localMaxTime);
			woa.setLocalMinTime((float) stat.localMinTime);
		}
		
		
		
		if(stat.totalDownloadTime != 0)
			woa.setTotalDownloadSpeed(stat.totalDownloadSpeed/(float)stat.totalDownloadTime);
		else
			woa.setTotalDownloadSpeed((float)0.0);
		
		if(total != 0)
		{
			woa.setTotalDownloadTime(stat.totalDownloadTime/total);
		}else
			woa.setTotalDownloadTime((float)0.0);
		
		if(total <= 0.0)
		{
			woa.setTotalMaxSpeed((float)0.0);
			woa.setTotalMinSpeed((float)0.0);
			woa.setTotalMaxTime((float)0.0);
			woa.setTotalMinTime((float)0.0);
		}
		else
		{
			woa.setTotalMaxSpeed(stat.totalMaxSpeed);
			woa.setTotalMinSpeed(stat.totalMinSpeed);
			woa.setTotalMaxTime((float) stat.totalMaxTime);
			woa.setTotalMinTime((float) stat.totalMinTime);
		}
		
		
		DBUtil.saveJDBC(woa, this.getParam().dbid);
	}

}
