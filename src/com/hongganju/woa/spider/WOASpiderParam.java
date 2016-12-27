package com.hongganju.woa.spider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import com.hongganju.db.entity.center.Woainstance;
import com.hongganju.db.entity.task.Woacrawledflvurl;

public class WOASpiderParam {
	private static Logger logger = Logger.getLogger(WOASpiderParam.class); 
	public Object flvCountSynObj = new Object();
	public Object flvNumSynObj = new Object();
	public int flvCount = 0;
	public Integer taskId;
	public Integer taskInstanceId;
	public Short customerId;
	public Woainstance instance;
	public int maxDownloadFlvUrl = 0;
	//种子地址
	private String seed;
	
	//域名关键字,list的在WOASpider中
	private String domains;
	private List<String> domainList;
	//过滤的关键字,list的在WOASpider中
	private String filterKeys;
	private List<String> filterKeyList;
	//爬行深度, -1不限制
	private int depth = -1;
	//爬行总数, -1不限制
	public int totalUrls = -1;
	public short dbid;
	
	public boolean isDownloadObject;
	List<String> flvPrefix = new ArrayList<String>();
	public float maxDownloadBytes;
	public int taskType;
	//下一个url，当前爬到的位置(用于断点续传)(在WebBrowserBase里计数)
	public int currentFlvUrl = 0;
	//已经采集到的url数量(在WebBrowserBase里计数)
	public int crawledFlvUrls = 0;
	//写crawledUrl的计数器(在写db时计数，DBSaver)，因为队列的存在这个值始终>=currentFlvUrl
	private int crawledFlvUrlsCounter = 0;
	public synchronized void initCrawledFlvUrlId(int id)
	{
		this.crawledFlvUrlsCounter = id;
	}
	public synchronized int getCrawledFlvUrlId()
	{
		crawledFlvUrlsCounter++;
		return crawledFlvUrlsCounter;
	}
	public String getSeed() {
		return seed;
	}
	public void setSeed(String seed) {
		this.seed = seed;
	}
	public List<String> getDomains() {
		
		return this.domainList;
	}
	public void setDomains(String domain) {
		this.domains = domain;
		domainList = new ArrayList<String>();
		String f = this.domains;
		if (f != null && !f.trim().equals("")) {
			String[] keys = f.split(",");
			for (int j = 0; j < keys.length; j++) {
				String key = keys[j].trim();
				if (!key.equals(""))
					domainList.add(key);
			}
		}
	}
	public List<String> getFilterKeys() {
		return this.filterKeyList;
	}
	public void setFilterKeys(String filterKeys) {
		this.filterKeys = filterKeys;
		filterKeyList = new ArrayList<String>();
		String f = this.filterKeys;
		if (f != null && !f.trim().equals("")) {
			String[] keys = f.split(",");
			for (int j = 0; j < keys.length; j++) {
				String key = keys[j].trim();
				if (!key.equals(""))
					filterKeyList.add(key);
			}
		}
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getTotalUrls() {
		return this.totalUrls;
	}
	public void setTotalUrls(int urlNumbers) {
		this.totalUrls = urlNumbers;
	}
	
	private HashMap<String, String> flvFilter = new HashMap<String, String>();
	//初始化的时候调用
	public void addToFilter(List<Woacrawledflvurl> list)
	{
		logger.debug("初始化过滤器，新增url：" +  list.size() + "个");
		if(list == null || list.size() == 0)
			return;
		for(int i=0; i<list.size(); i++)
		{
			if(list.get(i) != null)
				flvFilter.put(list.get(i).getUrl(), null);
		}
	}
	public boolean filterCrawledFlvUlr(String flvUrl)
	{
		if (flvFilter.containsKey(flvUrl))
			return false;
		flvFilter.put(flvUrl, null);
		return true;
	}
	public boolean isFlvPrefixOK(String url)
	{
		for (int i = 0; i < flvPrefix.size(); i++) {
//			System.out.println(flvPrefix.get(i) + " flv 处理：" + url);
			if (url.startsWith(flvPrefix.get(i))) {
				return true;
			}
		}
		return false;
	}
	public void setFlvPrefix(String flvPrefixStr) {
		if (flvPrefixStr == null)
			return;
		String[] prefix = flvPrefixStr.split(",");
		for (int i = 0; i < prefix.length; i++) {
			if (prefix[i] != null && !prefix[i].trim().equals(""))
				flvPrefix.add(prefix[i].trim());
		}
	}
	//过滤超过指定数量的url
	//抽取指定前缀的url
	//过滤掉曾经爬过的url
	public List<String> extractNewFlvUrls(List<String> urls) {
		// TODO Auto-generated method stub
		List<String> newUrl = new ArrayList<String>();
		synchronized(this)
		{
			if(this.maxDownloadFlvUrl > 0 && this.crawledFlvUrls > this.maxDownloadFlvUrl)
				return new ArrayList<String>();
			//先过滤，然后截取一下
			for(int i=0; i<urls.size(); i++)
			{
				String u = urls.get(i);
				if(this.isFlvPrefixOK(u))
				{
					if(this.filterCrawledFlvUlr(u))
					{
						newUrl.add(u);
					}
				}
			}
			if(this.maxDownloadFlvUrl > 0 )
			{
				int now = this.crawledFlvUrls + newUrl.size();
				//截短一下
				if(now > this.maxDownloadFlvUrl)
				{
					int left = this.maxDownloadFlvUrl - this.crawledFlvUrls;
					newUrl = newUrl.subList(0, left);
				}
			}
		}		
		return newUrl;
	}
}
