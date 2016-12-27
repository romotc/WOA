package com.hongganju.db.entity.base;


import java.util.Date;

/**
 * AbstractWoaurl entity provides the base persistence definition of the Woaurl
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractWoaflvurl implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer taskid;
	private Integer clusterid;
	private String nodeIp;
	private String nodeAreaId;
	private String nodeIspid;
	private Integer instanceId;
	private String domain;
	private Date uploadTime;
	private String url;
	private String flvurl;
	private String mimeType;
	private Integer size;
	private Integer downloadTime;
	private Float downloadSpeed;
	private String serverIp;
	private String serverAreaId;
	private String serverIspid;
	private Integer success;
	private Integer httpSuccess;
	private String httpCode;
	private String errorInfo;
	private Integer isCached;
	private Integer networkBytes;
	private Short customerId;
	private Integer contentBytes;
	private String cname;
	private String referUrl;
	private Integer dnsResolveTotalTime;
	private Float ConnectionTime;
	private Float RedirectTime;
	private Float FirstByteTime;
	private String flvTitle;
	
	// Constructors

	public String getFlvTitle() {
		return flvTitle;
	}

	public void setFlvTitle(String flvTitle) {
		this.flvTitle = flvTitle;
	}

	public Integer getDnsResolveTotalTime() {
		return dnsResolveTotalTime;
	}

	public String getFlvurl() {
		return flvurl;
	}

	public void setFlvurl(String flvurl) {
		this.flvurl = flvurl;
	}

	public void setDnsResolveTotalTime(Integer dnsResolveTotalTime) {
		this.dnsResolveTotalTime = dnsResolveTotalTime;
	}

	public Float getConnectionTime() {
		return ConnectionTime;
	}

	public void setConnectionTime(Float connectionTime) {
		ConnectionTime = connectionTime;
	}

	public Float getRedirectTime() {
		return RedirectTime;
	}

	public void setRedirectTime(Float redirectTime) {
		RedirectTime = redirectTime;
	}

	public Float getFirstByteTime() {
		return FirstByteTime;
	}

	public void setFirstByteTime(Float firstByteTime) {
		FirstByteTime = firstByteTime;
	}

	public String getReferUrl() {
		return referUrl;
	}

	public void setReferUrl(String referUrl) {
		this.referUrl = referUrl;
	}

	public Integer getContentBytes() {
		return contentBytes;
	}

	public void setContentBytes(Integer contentBytes) {
		this.contentBytes = contentBytes;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Short getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Short customerId) {
		this.customerId = customerId;
	}

	public Integer getNetworkBytes() {
		return networkBytes;
	}

	public void setNetworkBytes(Integer networkBytes) {
		this.networkBytes = networkBytes;
	}

	/** default constructor */
	public AbstractWoaflvurl() {
	}


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTaskid() {
		return this.taskid;
	}

	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}

	public Integer getClusterid() {
		return this.clusterid;
	}

	public void setClusterid(Integer clusterid) {
		this.clusterid = clusterid;
	}

	public String getNodeIp() {
		return this.nodeIp;
	}

	public void setNodeIp(String nodeIp) {
		this.nodeIp = nodeIp;
	}

	public String getNodeAreaId() {
		return this.nodeAreaId;
	}

	public void setNodeAreaId(String nodeAreaId) {
		this.nodeAreaId = nodeAreaId;
	}

	public String getNodeIspid() {
		return this.nodeIspid;
	}

	public void setNodeIspid(String nodeIspid) {
		this.nodeIspid = nodeIspid;
	}

	public Integer getInstanceId() {
		return this.instanceId;
	}

	public void setInstanceId(Integer instanceId) {
		this.instanceId = instanceId;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Date getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMimeType() {
		return this.mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Integer getSize() {
		return this.size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getDownloadTime() {
		return this.downloadTime;
	}

	public void setDownloadTime(Integer downloadTime) {
		this.downloadTime = downloadTime;
	}

	public Float getDownloadSpeed() {
		return this.downloadSpeed;
	}

	public void setDownloadSpeed(Float downloadSpeed) {
		this.downloadSpeed = downloadSpeed;
	}

	public String getServerIp() {
		return this.serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerAreaId() {
		return this.serverAreaId;
	}

	public void setServerAreaId(String serverAreaId) {
		this.serverAreaId = serverAreaId;
	}

	public String getServerIspid() {
		return this.serverIspid;
	}

	public void setServerIspid(String serverIspid) {
		this.serverIspid = serverIspid;
	}

	public Integer getSuccess() {
		return this.success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public Integer getHttpSuccess() {
		return this.httpSuccess;
	}

	public void setHttpSuccess(Integer httpSuccess) {
		this.httpSuccess = httpSuccess;
	}

	public String getHttpCode() {
		return this.httpCode;
	}

	public void setHttpCode(String httpCode) {
		this.httpCode = httpCode;
	}

	public String getErrorInfo() {
		return this.errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public Integer getIsCached() {
		return this.isCached;
	}

	public void setIsCached(Integer isCached) {
		this.isCached = isCached;
	}

}