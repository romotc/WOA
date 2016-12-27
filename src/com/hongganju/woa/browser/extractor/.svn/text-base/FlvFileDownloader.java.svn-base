package com.hongganju.woa.browser.extractor;

import java.net.URL;
import java.util.Date;

import org.apache.log4j.Logger;

import com.hongganju.common.util.DBUtil;
import com.hongganju.db.entity.center.Ipregion;
import com.hongganju.db.entity.task.Woaflvurl;
import com.hongganju.node.NodeConfig;
import com.hongganju.woa.browser.hbrowserNoJS.HUrlDownloader;
import com.hongganju.woa.browser.hbrowserNoJS.core.DownloadData;
import com.hongganju.woa.spider.WOASpiderParam;

//视频文件下载类，下载并保存
public class FlvFileDownloader {
	private static Logger logger = Logger.getLogger(FlvFileDownloader.class);
	public static void download(String url, String flvUrl, String refer, String title, WOASpiderParam param)
	{
		if (flvUrl == null) {
			logger.info("url: " + url + "抽取失败");
			saveFLVError(url, refer, param);
		} else {
			HUrlDownloader downloader = new HUrlDownloader();
			downloader.setMaxDownloadBytes((long) param.maxDownloadBytes);
			DownloadData download = downloader.download(flvUrl);
			saveFLV(download, downloader.getErrormsg(), flvUrl, url, refer, title, param);
		}
	}
	private static void saveFLVError(String url, String refer, WOASpiderParam param) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Woaflvurl obj = new Woaflvurl();

		if (url != null && url.length() > 4096)
			obj.setUrl(url.substring(0, 4096));
		else
			obj.setUrl(url);


		obj.setTaskid(param.taskId);
		obj.setInstanceId(param.taskInstanceId);
		obj.setCustomerId(param.customerId);
		obj.setClusterid(NodeConfig.getClusterId());

		obj.setNodeAreaId(NodeConfig.getArea());
		obj.setNodeIp(NodeConfig.getIp());
		obj.setNodeIspid(NodeConfig.getIsp());

		// try {
		// InetAddress addr = InetAddress.getByName(domain);
		// String ip = addr.getHostAddress();
		// data.setServerIp(ip);
		// } catch (UnknownHostException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		
		obj.setErrorInfo("can't get the flv url");
		obj.setUploadTime(new Date());

		if (refer != null && refer.length() > 1024)
			obj.setReferUrl(refer.substring(0, 1024));
		else
			obj.setReferUrl(refer);
		obj.setHttpSuccess(0);

		obj.setSuccess(0);

		try {
			DBUtil.saveJDBC(obj, param.dbid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	// 保存，处理各种错误
	private static void saveFLV(DownloadData download, String errormsg, String flvUrl,
			String url, String refer, String title, WOASpiderParam param) {
		// TODO Auto-generated method stub
		Woaflvurl obj = new Woaflvurl();

		if (url != null && url.length() > 4096)
			obj.setUrl(url.substring(0, 4096));
		else
			obj.setUrl(url);
		if (flvUrl != null && flvUrl.length() > 4096)
			obj.setFlvurl(flvUrl.substring(0, 4096));
		else
			obj.setFlvurl(flvUrl);

		obj.setTaskid(param.taskId);
		obj.setInstanceId(param.taskInstanceId);
		obj.setCustomerId(param.customerId);
		obj.setClusterid(NodeConfig.getClusterId());
		try {
			URL u = new URL(flvUrl);
			obj.setDomain(u.getHost());
		} catch (Exception e) {

		}
		obj.setNodeAreaId(NodeConfig.getArea());
		obj.setNodeIp(NodeConfig.getIp());
		obj.setNodeIspid(NodeConfig.getIsp());
		obj.setMimeType(download.Type);
		// try {
		// InetAddress addr = InetAddress.getByName(domain);
		// String ip = addr.getHostAddress();
		// data.setServerIp(ip);
		// } catch (UnknownHostException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		obj.setServerIp(download.Ip);
		Ipregion re = DBUtil.getIpregion(obj.getServerIp());
		if (re != null) {
			obj.setServerAreaId(re.getArea());
			obj.setServerIspid(re.getIsp());
		}
		obj.setErrorInfo(errormsg);
		obj.setUploadTime(new Date());

		if (refer != null && refer.length() > 1024)
			obj.setReferUrl(refer.substring(0, 1024));
		else
			obj.setReferUrl(refer);
		obj.setHttpCode(Integer.toString(download.HttpCode));
		if (obj.getHttpCode() != null && obj.getHttpCode().equals("200"))
			obj.setHttpSuccess(1);
		else
			obj.setHttpSuccess(0);

		// if((download.getErrorCode() == null ||
		// data.getErrorCode().equals("0") )&& url.getHttpSuccess() == 1)
		if (download.isOK == true && download.HttpCode > 199
				&& download.HttpCode < 300)
			obj.setSuccess(1);
		else
			obj.setSuccess(0);

		int totalDownloadTime = download.DownloadTime;
		System.out.println("totalDownloadTime=" + totalDownloadTime);
		obj.setDownloadTime(totalDownloadTime);
		obj.setSize(download.ContentLenFromHead);
		obj.setNetworkBytes(download.NetWorkBytes);
		obj.setCname(download.CName);
		obj.setContentBytes(download.ContentRealBytes);
		if (obj.getNetworkBytes() != null && obj.getDownloadTime() != null
				&& obj.getDownloadTime() != 0) {
			Float avg_download = obj.getNetworkBytes()
					/ (float) obj.getDownloadTime();
			obj.setDownloadSpeed(avg_download);
		}
		obj.setDnsResolveTotalTime((int)download.dnsResolveTotalTime);
		obj.setRedirectTime(download.RedirectTime);
		obj.setReferUrl(refer);
		obj.setConnectionTime(download.ConnectionTime);
		obj.setFirstByteTime(download.FirstByteTime);
		obj.setFlvTitle(title);
		try {
			DBUtil.saveJDBC(obj, param.dbid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
}
