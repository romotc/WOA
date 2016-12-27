package com.hongganju.woa.browser.hbrowserNoJS.core;

import lotus.http.download.HttpDownloadUrl;


public interface SimpleCrawlerListener {
	public void start(HttpDownloadUrl url);

	public void complete(DownloadData data);

	public void error(HttpDownloadUrl url, String errMsg);
}