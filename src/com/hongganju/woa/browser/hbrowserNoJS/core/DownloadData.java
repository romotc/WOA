package com.hongganju.woa.browser.hbrowserNoJS.core;

import lotus.http.download.HttpDownloadUrl;

public class DownloadData {
	public HttpDownloadUrl Url; // 原始url
	public String Type; // 类型，来自http 返回头的content-type
	public int ContentLenFromHead; // http头返回的content-len
	public int DownloadTime; // 下载时间，单位ms
	// public int Speed; NetWorkBytes/DownloadTime
	public String Ip; // 远端ip
	public int HttpCode; // http code
	public int NetWorkBytes; // 网络字节数，包括发送和获取
	public int ContentRealBytes; // 实际内容长度
	public String CName; // cname
	
	public long dnsResolveTotalTime;
	public float ConnectionTime;
	public float RedirectTime;
	public float FirstByteTime;
	
	public boolean isOK;
	/**
	 * html/css/js文本
	 */
	public String Html, Css, Js;
	/**
	 * http返回头
	 */
	public String HttpRetHead;
}