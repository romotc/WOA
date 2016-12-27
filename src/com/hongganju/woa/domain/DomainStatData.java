package com.hongganju.woa.domain;

import java.util.Date;

public class DomainStatData {

	public String domain;
	public Date uploadTime;
	public int errorNumber = 0;
	
	public int totalUrls = 0;
	public int localurl = 0;
	public long localRate = 0;
	
	public long totalDownloadSpeed = 0;
	public long totalDownloadTime = 0;
	public float totalMaxSpeed = 0;
	public float totalMinSpeed = Integer.MAX_VALUE;
	public int totalMaxTime = 0;
	public int totalMinTime = Integer.MAX_VALUE;
	
	public long localDownloadSpeed = 0;
	public long localDownloadTime = 0;
	public float localMaxSpeed = 0;
	public float localMinSpeed = Integer.MAX_VALUE;
	public int localMaxTime = 0;
	public int localMinTime = Integer.MAX_VALUE;
	
	public long foreignDownloadSpeed = 0;
	public long foreignDownloadTime = 0;
	public float foreignMaxSpeed = 0;
	public float foreignMinSpeed = Integer.MAX_VALUE;
	public int foreignMaxTime = 0;
	public int foreignMinTime = Integer.MAX_VALUE;
}
