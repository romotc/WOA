package com.hongganju.woa.browser.ie;

import java.util.List;

import com.hongganju.communication.data.reportData.CDNPageReportData;

//结果
public class BrowseResult {
	public CDNPageReportData data;
	public List<String> urls;
	Integer threadNo;
	Boolean success;
	String exceptionMsg;
	public CDNPageReportData getData() {
		return data;
	}
	public void setData(CDNPageReportData data) {
		this.data = data;
	}
	public Integer getThreadNo() {
		return threadNo;
	}
	public void setThreadNo(Integer threadNo) {
		this.threadNo = threadNo;
	}
	public List<String> getUrls() {
		return urls;
	}
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getExceptionMsg() {
		return exceptionMsg;
	}
	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	
}
