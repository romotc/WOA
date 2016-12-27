package com.hongganju.woa.browser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.hongganju.communication.data.reportData.PageItem;
import com.hongganju.woa.spider.WOASpider;
import com.hongganju.woa.spider.WOASpiderParam;

//所有的过滤都在这里做
public class WOAFilter {
	private static Logger logger = Logger.getLogger(WOAFilter.class);

	// 1.根据配置文件，拿掉不需要的url
	// 2.去掉https
	// 3.过滤重复的
	public static List filterPageitems(List<PageItem> URLs, WOASpider woa) {
		List list2 = filterDomainPageitems(URLs, woa);
		List list3 = filterCrawledPageitems(list2, woa);
		return list3;
	}

	// 3.过滤重复的
	public static List filterCrawledPageitems(List<PageItem> URLs, WOASpider woa) {
		List<PageItem> list = new ArrayList<PageItem>();
		for (int i = 0; i < URLs.size(); i++) {
			PageItem u = URLs.get(i);
			if (u != null && u.getURL() != null) {

				URL url = null;
				try {
					url = new URL(u.getURL());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					continue;
				}
				if (!woa.spider.isCrawled(url)) {
					list.add(u);
				}
			}

		}
		return list;
	}

	// 1.根据配置文件，拿掉不需要的url
	// 3.去掉https
	// a.domain和filterKeys都null，不过滤
	// b.domain必须存在
	// Items是页面元素，不用过滤
	public static List filterDomainPageitems(List<PageItem> URLs, WOASpider woa) {
		return URLs;
		/*
		 * ArrayList<PageItem> retVal = new ArrayList<PageItem>(); String domain
		 * = null; if(woa.param.getDomain() != null &&
		 * !woa.param.getDomain().trim().equals("")) { domain =
		 * woa.param.getDomain(); } List<String> filterKeys = null;
		 * if(woa.spider.filters != null && woa.spider.filters.size() > 0)
		 * filterKeys = woa.spider.filters;
		 * 
		 * if(domain == null && filterKeys == null) return URLs;
		 * 
		 * 
		 * for (Iterator i = URLs.iterator(); i.hasNext();) { // URL u = (URL)
		 * i.next(); PageItem item = (PageItem)i.next(); if(item == null ||
		 * item.getURL() == null) continue; String s = item.getURL(); if(s ==
		 * null) continue; //去掉https if(s.startsWith("https")) continue; URL u;
		 * try { u = new URL(s); } catch (MalformedURLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); continue; } String
		 * host = u.getHost(); if(host.indexOf(domain) != -1) { if(filterKeys !=
		 * null) { for (int j = 0; j < filterKeys.size(); j++) { if
		 * (s.indexOf(filterKeys.get(j)) != -1) { retVal.add(item); continue; }
		 * } }else { retVal.add(item); }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * return retVal;
		 */
	}

	public static List<URL> filterURLs(List<String> URLs, WOASpider woa) {
		logger.info("enter");
		if (URLs == null)
			return new ArrayList<URL>();
		logger.info("filterURLs");
		List list2 = filterDomainURLs(URLs, woa);
		logger.info("filterCrawledURLs");
		List list3 = filterCrawledURLs(list2, woa);
		logger.info("filterTotal");
		return filterTotal(list3, woa);
	}

	// 根据总数过滤
	public static synchronized List<URL> filterTotal(List<URL> URLs,
			WOASpider woa) {
		if (woa.totalUrl <= 0)
			return URLs;
		int left = woa.totalUrl - woa.crawledUrls;
		if (left < 0)
			left = 0;
		if (left == 0) {
			return new ArrayList<URL>();
		} else if (left < URLs.size()) {
			return URLs.subList(0, left);
		} else
			return URLs;

	}

	// 过滤掉曾经爬过的节点
	private static List<URL> filterCrawledURLs(List<String> URLs, WOASpider woa) {
		// TODO Auto-generated method stub
		return woa.spider.filterCrawled(URLs);
	}

	// 1,domain和filterKeys都为空就不过滤
	private static List<String> filterDomainURLs(List<String> URLs,
			WOASpider woa) {
		// TODO Auto-generated method stub
		ArrayList<String> retVal = new ArrayList<String>();
		List<String> domain = null;
		if (woa.param.getDomains() != null && woa.param.getDomains().size() > 0) {
			domain = woa.param.getDomains();
		}
		List<String> filterKeys = null;
		if (woa.getParam().getFilterKeys() != null
				&& woa.getParam().getFilterKeys().size() > 0)
			filterKeys = woa.getParam().getFilterKeys();

		// 不需要过滤
		if (domain == null && filterKeys == null) {
			for (int i = 0; i < URLs.size(); i++) {
				try {
					URL u = new URL(URLs.get(i));
					retVal.add(URLs.get(i));
				} catch (Exception e) {
				}
			}
			return retVal;
		}

		// 过滤
		for (Iterator i = URLs.iterator(); i.hasNext();) {
			String s = (String) i.next();
			if (s == null)
				continue;
			// 去掉https
			if (s.startsWith("https"))
				continue;
			URL u;
			try {
				u = new URL(s);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				continue;
			}
			String host = u.getHost();

			boolean passFilter = false;
			// 判断domain
			if (domain != null) {
				for (int ii = 0; ii < domain.size(); ii++) {
					String d = domain.get(ii);
					if (host.indexOf(d) > -1) {
						passFilter = true;
						break;
					}
				}
			} else
				passFilter = true;

			// 如果domain合法，那么开始判断filterKeys
			if (passFilter == true && filterKeys != null) {
				passFilter = false;
				for (int ii = 0; ii < filterKeys.size(); ii++) {
					String d = filterKeys.get(ii);
					if (s.indexOf(d) > -1) {
						passFilter = true;
						break;
					}
				}
			}

			if (passFilter == true)
				retVal.add(s);
		}

		return retVal;
	}

	public static void main(String[] args) {
		WOASpiderParam param = new WOASpiderParam();
		param.setSeed("http://www.zol.com.cn");
		WOASpider woa = new WOASpider(param);

		// woa.getParam().setDomains("sina, zol");
		// woa.getParam().setFilterKeys("bkb");

		List<String> list = new ArrayList<String>();
		list.add("http://www.sina.com.cn/bkb");
		list.add("http://www.zol.com.cn");
		list.add("http://www.bka.com.cn");
		list.add("http://www.bka.com.cn/bkb.tttt");
		List<String> l = filterDomainURLs(list, woa);
		System.out.println(l.toString());
	}
}
