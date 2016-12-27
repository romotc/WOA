package com.hongganju.woa.browser.ie;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import com.hongganju.communication.data.reportData.CDNPageReportData;
import com.hongganju.communication.data.reportData.PageItem;

//消息编解码
public class MulitIEUtil {
	public static void main(String[] args)
	{
		BrowseResult result = new BrowseResult();
		List<String> urls = new ArrayList<String>();
		urls.add("123");
		urls.add("asfasd");
		result.setData(getdata(5));
		result.setUrls(urls);
		String encoded = encode(result);
		System.out.println(encoded);
		BrowseResult r2 = decode(encoded);
		System.out.println("ok");
	}
	public static String encode(BrowseResult result)
//	public static String encode(CDNPageReportData result)
	{
		
		JSONObject json = JSONObject.fromObject(result);
		return json.toString();
	}
	public static BrowseResult decode(String text)
	{
		JSONObject j = JSONObject.fromObject(text);
		BrowseResult req = (BrowseResult) JSONObject.toBean(j,
				BrowseResult.class);
		String str = j.getString("data");
		if(str != null && !str.trim().equals("null"))
		{
			JSONObject data = JSONObject.fromObject(str);
			String items_str = data.getString("items");
			if(items_str != null && !items_str.trim().equals("null"))
			{
				JSONArray array = JSONArray.fromObject(items_str);
				Object[] items = array.toArray();
				List<PageItem> pageItems = new ArrayList<PageItem>();
				for (int i = 0; i < items.length; i++) {
					PageItem temp = (PageItem) JSONObject.toBean(
							(JSONObject) items[i], PageItem.class);
					pageItems.add(temp);
				}
				req.getData().setItems(pageItems);
			}
		}
		return req;
	}

	//test
	public static CDNPageReportData getdata(int n)
	{
		CDNPageReportData data = new CDNPageReportData();
		data.setTaskType("1");
		data.setCustomId("10001");
		//data.setIsInstant(false);
		data.setTaskInstanceID("3");
		data.setPeerExecuteTaskID("4");
		data.setURL("5");
		data.setErrorCode("6");
		data.setProtocolType("7");
		data.setPOPIP("8");
		data.setDNS_IP("9");
		data.setStartTime("10");
		data.setOS("11");
		data.setBrowserVersion("12");
		data.setFlashVersion("13");
		data.setPeerIP("14");
		data.setFileType("15");
		data.setHttpCode("16");
		data.setTotalDownLoadTime("17");
		data.setMainURLTime("18");
		data.setContextDownLoadTime("19");
		data.setAdditionalDataTime("20");
		data.setPageObjectNum("21");
		data.setContentBytes("22");
		data.setNetwordBytes("23");
		data.setDNSTime("24");
		data.setConnectionTime("25");
		data.setSSLTime("26");
		data.setRedirectTime("27");
		data.setRedirectNum("28");
		data.setRequestTime("29");
		data.setFirstByteTime("30");
		data.setCloseConnectionTime("31");
		data.setOtherBytesTime("32");
		data.setHttpHeader("33");
		data.setPing("34");
		data.setNslookup("35");
		data.setTraceroute("36");
		
		data.setPeerExecuteTaskID("34207");
		data.setPeerId("00-04-23-b8-16-2c");
		data.setTaskInstanceID("5366");
		data.setCustomId("10001");
		data.setErrorCode(null);
		data.setHttpCode("200");
		String t = Long.toString(System.currentTimeMillis());
		System.out.println(t);
		data.setTaskRequestTime(t);
		
		List<PageItem> list = new ArrayList<PageItem>();
		data.setItems(list);
		for(int i=0; i<n; i++)
		{
			PageItem item = new PageItem();
			
			item.setStarttime("0");
			item.setURL("1");
			item.setFileType("2");
			item.setErrorCode("3");
			item.setHttpCode("4");
			item.setTotalDownLoadTime("5");
			item.setContentBytes("6");
			item.setNetwordBytes("7");
			item.setDNSTime("8");
			item.setConnectionTime("9");
			item.setSSLTime("10");
			item.setRedirectTime("11");
			item.setRequestTime("12");
			item.setFirstByteTime("13");
			item.setCloseConnectionTime("14");
			item.setRedirectNum("15");
			item.setOtherBytesTime("16");
			item.setHttpHeader("17");
			
			list.add(item);
		}
		return data;
	}
}