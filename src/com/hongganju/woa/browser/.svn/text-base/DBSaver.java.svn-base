package com.hongganju.woa.browser;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import weblech.spider.URLToDownload;

import com.hongganju.common.util.DBUtil;
import com.hongganju.communication.data.reportData.CDNPageReportData;
import com.hongganju.communication.data.reportData.PageItem;
import com.hongganju.db.dao.WoainstanceDao;
import com.hongganju.db.entity.center.Woainstance;
import com.hongganju.db.entity.task.Woacrawledurl;
import com.hongganju.db.entity.task.Woaurlerror;
import com.hongganju.woa.spider.WOASpider;
import com.hongganju.woa.spider.WOASpiderParam;

public class DBSaver {

	// 只根据http code来
	static public boolean isError(CDNPageReportData data) {
		if (data == null)
			return false;
		if (data.getHttpCode() != null) {
			if (data.getHttpCode().startsWith("4")
					|| data.getHttpCode().startsWith("5"))
				return true;
			else if (data.getHttpCode().startsWith("1")
					|| data.getHttpCode().startsWith("2")
					|| data.getHttpCode().startsWith("3"))
				return false;
		}

		// if(data.getErrorCode() == null ||
		// data.getErrorCode().trim().equals("0"))
		// return false;
		// else
		// return true;
		return false;
	}

	// 只根据http code来
	static public boolean isError(PageItem data) {
		if (data == null)
			return false;
		if (data.getHttpCode() != null) {
			if (data.getHttpCode().startsWith("4")
					|| data.getHttpCode().startsWith("5"))
				return true;
			else if (data.getHttpCode().startsWith("1")
					|| data.getHttpCode().startsWith("2")
					|| data.getHttpCode().startsWith("3"))
				return false;
		}

		// if(data.getErrorCode() == null ||
		// data.getErrorCode().trim().equals("0"))
		// return false;
		// else
		// return true;
		return false;
	}

	static public void saveError(CDNPageReportData data, URL refer, String url, WOASpiderParam param) {
		String ref = null;
		if (refer != null) {
			ref = refer.toExternalForm();
			if (ref.length() > 1024)
				ref = ref.substring(0, 1024);
		}

		if (data == null)
			return;
		List list = new ArrayList();
		if (isError(data)) {
			if (data.getURL() != null && !data.getURL().trim().equals("")) {
				Woaurlerror error = new Woaurlerror();
				error.setErrordescription(data.getErrorCode() + ": "
						+ data.getHttpCode() + data.getErrorDescription());
				error.setHttpCode(data.getHttpCode());
				error.setErrorCode(data.getErrorCode());
				error.setStatTime(new Timestamp(System.currentTimeMillis()));
				error.setUrl(data.getURL());
				error.setUrlStatId(2);
				error.setRefer(ref);
				error.setPageUrl(url);
				error.setUrltaskid(param.taskId);
				error.setInstanceId(param.taskInstanceId);
				list.add(error);
			}

		}

		// 处理item
		if (data != null && data.getItems() != null) {
			for (int i = 0; i < data.getItems().size(); i++) {
				PageItem item = data.getItems().get(i);
				if (item.getURL() == null || item.getURL().trim().equals(""))
					continue;
				// if(!item.getURL().contains("www.sx.10086.cn"))
				// continue;

				if (isError(item)){
					Woaurlerror error = new Woaurlerror();
					error.setRefer(ref);
					error.setErrordescription(item.getErrorCode() + ", "
							+ item.getHttpCode() + ": item");
					error.setHttpCode(data.getHttpCode());
					error.setErrorCode(data.getErrorCode());
					error.setStatTime(new Timestamp(System.currentTimeMillis()));
					error.setUrl(item.getURL());
					error.setUrlStatId(2);
					error.setPageUrl(url);
					error.setUrltaskid(param.taskId);
					error.setInstanceId(param.taskInstanceId);
					list.add(error);
				}

			}
		}
		try {
			DBUtil.saveJDBCList(list, param.dbid);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public static void saveCrawledUrlInDB(List<URLToDownload> list, WOASpider woa) {
		List<Woacrawledurl> l = new ArrayList<Woacrawledurl>();
		for (int i = 0; i < list.size(); i++) {
			URLToDownload todownload = list.get(i);
			Woacrawledurl url = createCrawledurl(todownload, woa);
			if (url != null)
				l.add(url);

		}
		DBUtil.saveJDBCList(l, woa.getParam().dbid);
	}

	public static Woacrawledurl createCrawledurl(URLToDownload todownload,
			WOASpider woa) {
		// TODO Auto-generated method stub
		if (todownload == null)
			return null;
		Woacrawledurl queue = new Woacrawledurl();
		queue.setInstanceId(woa.getParam().instance.getTaskinstanceId());
		queue.setTaskid(woa.getParam().instance.getTaskid());
		URL url = todownload.getURL();
		if (url == null)
			return null;
		String u = url.toExternalForm();
		if (u != null && u.length() > 4096)
			queue.setUrl(u.substring(0, 4096));
		else
			queue.setUrl(u);
		if (todownload.getReferer() != null) {
			String ref = todownload.getReferer().toExternalForm();
			if (ref != null && ref.length() > 1024)
				queue.setRefer(ref.substring(0, 1024));
			else
				queue.setRefer(ref);
		}
		queue.setDepth(todownload.getDepth());
		synchronized (woa) {
			woa.crawledUrlsCounter++;
			queue.setUrlNo(woa.crawledUrlsCounter);
		}
		return queue;
	}

	public static void saveInDB(URLToDownload todownload, WOASpider woa) {

		Woacrawledurl url = createCrawledurl(todownload, woa);
		if (url != null)
			DBUtil.saveJDBC(url, (short) 41);
	}

	public static void updateWOAInstance(WOASpider woa) {
		// TODO Auto-generated method stub
		Woainstance instance = woa.param.instance;
		synchronized(instance)
		{
			instance.setCurrentCrawledUrl(woa.currentUrl);
			WoainstanceDao dao = new WoainstanceDao();
			dao.update(instance);
		}
		
	}
	public static void updateFlvInstance(WOASpiderParam param)
	{
		Woainstance instance = param.instance;
		synchronized(instance){
			instance.setCurrentFlvUrl(param.currentFlvUrl);
			WoainstanceDao dao = new WoainstanceDao();
			dao.update(instance);
		}
	}
	
}
