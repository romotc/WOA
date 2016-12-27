package com.hongganju.woa.browser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.hongganju.common.util.DBUtil;
import com.hongganju.common.util.NumUtil;
import com.hongganju.communication.data.reportData.CDNPageReportData;
import com.hongganju.communication.data.reportData.PageItem;
import com.hongganju.db.entity.center.Ipregion;
import com.hongganju.db.entity.task.Woaurl;
import com.hongganju.node.NodeConfig;
import com.hongganju.woa.domain.DomainStatData;
import com.hongganju.woa.spider.WOASpider;

public class WOASaver {
//	public static void saveError(URL urll, String error, WOASpider woa) {
//		// TODO Auto-generated method stub
//		Woaurl url = new Woaurl();
//		if(error.length() > 1024)
//			url.setErrorInfo(error.substring(0,1024));
//		else
//			url.setErrorInfo(error);
//		url.setTaskid(woa.getParam().taskId);
//		url.setInstanceId(woa.getParam().taskInstanceId);
//		url.setCustomerId(woa.getParam().customerId);
//		url.setClusterid(NodeConfig.getClusterId());
//		url.setDomain(urll.getHost());
//		String u = urll.toString();
//		if(u.length() > 4096)
//			url.setUrl(u);
//		else
//			url.setUrl(u.substring(0,4096));
//		url.setHttpSuccess(0);
//		url.setSuccess(0);
//		url.setNodeAreaId(NodeConfig.getArea());
//		url.setNodeIp(NodeConfig.getIp());
//		url.setNodeIspid(NodeConfig.getIsp());
//		url.setUploadTime(new Date());
//		DBUtil.saveJDBC(url, woa.getParam().dbid);
//		
//		DomainStatData domain = null;
//		synchronized(woa)
//		{
//			domain = woa.domainStats.get(url.getDomain());
//			if(domain == null)
//			{
//				domain = new DomainStatData();
//				domain.domain = url.getDomain();
//				woa.domainStats.put(url.getDomain(), domain);
//			}
//			domain.errorNumber++;
//			domain.totalUrls++;
//		}
//	}

	public static Ipregion save(CDNPageReportData data, String domain, String referUrl, WOASpider woa)
	{
		Woaurl url = new Woaurl();
		url.setTaskid(woa.getParam().taskId);
		url.setInstanceId(woa.getParam().taskInstanceId);
		url.setCustomerId(woa.getParam().customerId);
		url.setClusterid(NodeConfig.getClusterId());
		url.setDomain(domain);
		url.setNodeAreaId(NodeConfig.getArea());
		url.setNodeIp(NodeConfig.getIp());
		url.setNodeIspid(NodeConfig.getIsp());
		url.setMimeType(data.getFileType());
//		try {
//			InetAddress addr = InetAddress.getByName(domain);
//			String ip = addr.getHostAddress();
//			data.setServerIp(ip);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		url.setServerIp(data.getPOPIP());
		Ipregion re = DBUtil.getIpregion(data.getPOPIP());
		if(re != null)
		{
			url.setServerAreaId(re.getArea());
			url.setServerIspid(re.getIsp());
		}
		url.setErrorInfo(data.getErrorCode());
		url.setUploadTime(new Date());
		if(data.getURL() != null && data.getURL().length() > 4096)
			url.setUrl(data.getURL().substring(0,4096));
		else
			url.setUrl(data.getURL());
		if(referUrl != null && referUrl.length() > 1024)
			url.setReferUrl(referUrl.substring(0,1024));
		else
			url.setReferUrl(referUrl);
		url.setHttpCode(data.getHttpCode());
		if(url.getHttpCode() != null && url.getHttpCode().equals("200"))
			url.setHttpSuccess(1);
		else
			url.setHttpSuccess(0);
		
		if((data.getErrorCode() == null || data.getErrorCode().equals("0") )&& url.getHttpSuccess() == 1)
			url.setSuccess(1);
		else
			url.setSuccess(0);
		
		
		if(url.getSuccess() == 1)
		{
			PageItem item = null;
			if(data.getItems() != null && data.getItems().size() > 0)
				item = data.getItems().get(0);
			if(item == null)
			{
				Float total = NumUtil.parseFloat(data.getTotalDownLoadTime()) * 1000;
				if(total != null)
					url.setDownloadTime(total.intValue());
				else
					url.setDownloadTime(0);
				url.setSize(NumUtil.parseInt(data.getFileSize()));
				url.setNetworkBytes(NumUtil.parseInt(data.getNetwordBytes()));
				url.setCname(data.getCname());
				url.setContentBytes(NumUtil.parseInt(data.getContentBytes()));
				if(url.getNetworkBytes() != null && url.getDownloadTime() != null && url.getDownloadTime() != 0)
				{
					Float avg_download = url.getNetworkBytes()/(float)url.getDownloadTime();
					url.setDownloadSpeed(avg_download);
				}
			}
			else
			{
				url.setSize(NumUtil.parseInt(item.getFileSize()));
				Float f = NumUtil.parseFloat(item.getTotalDownLoadTime()) * 1000;
				url.setCname(item.getCname());
				if(f != null)
					url.setDownloadTime(f.intValue());
				else
					url.setDownloadTime(0);
				url.setContentBytes(NumUtil.parseInt(item.getContentBytes()));
				url.setCname(item.getCname());
				url.setNetworkBytes(NumUtil.parseInt(item.getNetwordBytes()));
				if(url.getNetworkBytes() != null && url.getDownloadTime() != null && url.getDownloadTime() != 0)
				{
					Float avg_download = url.getNetworkBytes()/(float)url.getDownloadTime();
					url.setDownloadSpeed(avg_download);
				}
				else
					url.setDownloadSpeed(0.0F);
			}
		}

		DBUtil.saveJDBC(url, woa.getParam().dbid);
		//计算domain
		computeDomain(url, re, woa);
		return re;
	}
	
	//计算domain的统计数据
	public static void computeDomain(Woaurl url, Ipregion re, WOASpider woa)
	{
		DomainStatData domain = null;
		synchronized(woa)
		{
			domain = woa.domainStats.get(url.getDomain());
			if(domain == null)
			{
				domain = new DomainStatData();
				domain.domain = url.getDomain();
				woa.domainStats.put(url.getDomain(), domain);
			}	
		}
		
		synchronized(domain)
		{
			if(url.getSuccess() == 0)
			{
				domain.errorNumber++;
				domain.totalUrls++;
				return;
			}
			
			//网外
			if(re == null || !re.getArea().equals(NodeConfig.getArea()) || !re.getIsp().equals(NodeConfig.getIsp()))
			{
//				domain.foreignDownloadSpeed += url.getSize();
				domain.foreignDownloadSpeed += url.getNetworkBytes();
				domain.foreignDownloadTime += url.getDownloadTime();
				if(url.getDownloadSpeed() != null)
				{
					if(url.getDownloadSpeed() > domain.foreignMaxSpeed)
						domain.foreignMaxSpeed = url.getDownloadSpeed();
					if(url.getDownloadSpeed() < domain.foreignMinSpeed)
						domain.foreignMinSpeed = url.getDownloadSpeed();
				}
				if(url.getDownloadTime() != null)
				{
					if(url.getDownloadTime() > domain.foreignMaxTime)
						domain.foreignMaxTime = url.getDownloadTime();
					if(url.getDownloadTime() < domain.foreignMinTime)
						domain.foreignMinTime = url.getDownloadTime();
				}
				
			}
			//网内
			else
			{
				domain.localDownloadSpeed += url.getNetworkBytes();
				domain.localDownloadTime += url.getDownloadTime();
				if(url.getDownloadSpeed() != null)
				{
					if(url.getDownloadSpeed() > domain.localMaxSpeed)
						domain.localMaxSpeed = url.getDownloadSpeed();
					if(url.getDownloadSpeed() < domain.localMinSpeed)
						domain.localMinSpeed = url.getDownloadSpeed();
				}
				if(url.getDownloadTime() != null)
				{
					if(url.getDownloadTime() > domain.localMaxTime)
						domain.localMaxTime = url.getDownloadTime();
					if(url.getDownloadTime() < domain.localMinTime)
						domain.localMinTime = url.getDownloadTime();
				}
				domain.localurl++;
			}
			
			domain.totalDownloadSpeed += url.getNetworkBytes();
			domain.totalDownloadTime += url.getDownloadTime();
			if(url.getDownloadSpeed() != null)
			{
				if(url.getDownloadSpeed() > domain.totalMaxSpeed)
					domain.totalMaxSpeed = url.getDownloadSpeed();
				if(url.getDownloadSpeed() < domain.totalMinSpeed)
					domain.totalMinSpeed = url.getDownloadSpeed();
			}
			if(url.getDownloadTime() != null)
			{
				if(url.getDownloadTime() > domain.totalMaxTime)
					domain.totalMaxTime = url.getDownloadTime();
				if(url.getDownloadTime() < domain.totalMinTime)
					domain.totalMinTime = url.getDownloadTime();
			}
			
			domain.totalUrls++;
		}
	}

	public static void saveItems(List<PageItem> filtered, WOASpider woa, String referUrl)
	{
		System.out.println("save Items:" + filtered.size() + "个");
		List<Woaurl> list = new ArrayList<Woaurl>();
		for(int i=0; i<filtered.size(); i++)
		{
			Woaurl url = createItem(filtered.get(i), woa, referUrl);
			list.add(url);
		}
		DBUtil.saveJDBCList(list, woa.getParam().dbid);
	}
	
	//当前在用的方法
	public static Woaurl createItem(PageItem item, WOASpider woa, String referUrl) {
		// TODO Auto-generated method stub
		Woaurl url = new Woaurl();
		url.setTaskid(woa.getParam().taskId);
		url.setInstanceId(woa.getParam().taskInstanceId);
		url.setCustomerId(woa.getParam().customerId);
		url.setClusterid(NodeConfig.getClusterId());
		
		try {
			URL u = new URL(item.getURL());
			url.setDomain(u.getHost());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		url.setNodeAreaId(NodeConfig.getArea());
		url.setNodeIp(NodeConfig.getIp());
		url.setNodeIspid(NodeConfig.getIsp());
		url.setMimeType(item.getFileType());
		url.setServerIp(item.getPOPIP());
		Ipregion re = DBUtil.getIpregion(item.getPOPIP());
		if(re != null)
		{
			url.setServerAreaId(re.getArea());
			url.setServerIspid(re.getIsp());
		}
//		url.setErrorInfo(item.getErrorCode());
		url.setErrorInfo(item.getErrorCode());
		url.setUploadTime(new Date());
		if(item.getURL() != null && item.getURL().length() > 4096)
			url.setUrl(item.getURL().substring(0,4096));
		else
			url.setUrl(item.getURL());
		if(referUrl != null && referUrl.length() > 1024)
			url.setReferUrl(referUrl.substring(0,1024));
		else
			url.setReferUrl(referUrl);
		url.setHttpCode(item.getHttpCode());
		if(url.getHttpCode() != null && (url.getHttpCode().equals("200") || url.getHttpCode().startsWith("30")))
			url.setHttpSuccess(1);
		else
			url.setHttpSuccess(0);
		
		if((item.getErrorCode() == null || item.getErrorCode().equals("0") )&& url.getHttpSuccess() == 1)
			url.setSuccess(1);
		else
			url.setSuccess(0);
		
		
		if(url.getSuccess() == 1)
		{
			Float total = NumUtil.parseFloat(item.getTotalDownLoadTime()) * 1000;
			if(total != null)
				url.setDownloadTime(total.intValue());
			else
				url.setDownloadTime(0);
			url.setSize(NumUtil.parseInt(item.getFileSize()));
			url.setNetworkBytes(NumUtil.parseInt(item.getNetwordBytes()));
			url.setCname(item.getCname());
			url.setContentBytes(NumUtil.parseInt(item.getContentBytes()));
			if(url.getNetworkBytes() != null && url.getDownloadTime() != null && url.getDownloadTime() != 0)
			{
				Float avg_download = url.getNetworkBytes()/(float)url.getDownloadTime();
				url.setDownloadSpeed(avg_download);
			}

			url.setSize(NumUtil.parseInt(item.getFileSize()));
			Float f = NumUtil.parseFloat(item.getTotalDownLoadTime()) * 1000;
			url.setCname(item.getCname());
			if(f != null)
				url.setDownloadTime(f.intValue());
			else
				url.setDownloadTime(0);
			url.setContentBytes(NumUtil.parseInt(item.getContentBytes()));
			url.setCname(item.getCname());
			url.setNetworkBytes(NumUtil.parseInt(item.getNetwordBytes()));
			if(url.getNetworkBytes() != null && url.getDownloadTime() != null && url.getDownloadTime() != 0)
			{
				Float avg_download = url.getNetworkBytes()/(float)url.getDownloadTime();
				url.setDownloadSpeed(avg_download);
			}
			else
				url.setDownloadSpeed(0.0F);
			url.setFirstByteTime(NumUtil.parseFloat(item.getFirstByteTime()));
			url.setDnsResolveTotalTime(NumUtil.parseInt(item.getDNSTime()));
			url.setConnectionTime(NumUtil.parseFloat(item.getConnectionTime()));
			url.setRedirectTime(NumUtil.parseFloat(item.getRedirectTime()));
		}

		//DBUtil.saveJDBC(url, woa.getParam().dbid);
		//计算domain
		//computeDomain(url, re, woa);
		return url;
	}
	
}
