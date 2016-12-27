package com.hongganju.db.entity.base;

import java.util.Date;

/**
 * AbstractWoatask entity provides the base persistence definition of the
 * Woatask entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractWoatask implements java.io.Serializable {

	// Fields

	private Integer taskid;
	private String webName;
	private String seedUrl;
	private String domainkey;
	private Date createTime;
	private Date disableTime;
	private Integer executeFrequency;
	private Integer status;
	private Short customerId;
	private String description;
	private Integer totalUrls;
	private Integer totalObjects;
	private Integer depth;
	private Integer domainUrls;
	private Short dbid;
	private Float maxResourceSize;
	private Integer maxThreads;
	private Integer maxDownloadItems;
	private Float singleObjectTimeout;
	private Boolean isDownloadObject;
	private String extractUrl;
	private Integer taskType;
	private Float maxDownloadKB;
	private Integer maxDownloadFlvUrl;
	// Constructors

	public Float getMaxDownloadKB() {
		return maxDownloadKB;
	}

	public Integer getMaxDownloadFlvUrl() {
		return maxDownloadFlvUrl;
	}

	public void setMaxDownloadFlvUrl(Integer maxDownloadFlvUrl) {
		this.maxDownloadFlvUrl = maxDownloadFlvUrl;
	}

	public void setMaxDownloadKB(Float maxDownloadKB) {
		this.maxDownloadKB = maxDownloadKB;
	}

	public Float getMaxResourceSize() {
		return maxResourceSize;
	}

	public void setMaxResourceSize(Float maxResourceSize) {
		this.maxResourceSize = maxResourceSize;
	}

	public Integer getMaxThreads() {
		return maxThreads;
	}

	public void setMaxThreads(Integer maxThreads) {
		this.maxThreads = maxThreads;
	}

	public Integer getMaxDownloadItems() {
		return maxDownloadItems;
	}

	public void setMaxDownloadItems(Integer maxDownloadItems) {
		this.maxDownloadItems = maxDownloadItems;
	}

	public Float getSingleObjectTimeout() {
		return singleObjectTimeout;
	}

	public void setSingleObjectTimeout(Float singleObjectTimeout) {
		this.singleObjectTimeout = singleObjectTimeout;
	}

	public Boolean getIsDownloadObject() {
		return isDownloadObject;
	}

	public void setIsDownloadObject(Boolean isDownloadObject) {
		this.isDownloadObject = isDownloadObject;
	}

	public String getExtractUrl() {
		return extractUrl;
	}

	public void setExtractUrl(String extractUrl) {
		this.extractUrl = extractUrl;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	/** default constructor */
	public AbstractWoatask() {
	}

	/** minimal constructor */
	public AbstractWoatask(Integer taskid) {
		this.taskid = taskid;
	}

	/** full constructor */
	public AbstractWoatask(Integer taskid, String webName, String seedUrl,
			String domainkey, Date createTime, Date disableTime,
			Integer executeFrequency, Integer status, Short customerId,
			String description, Integer totalUrls, Integer totalObjects,
			Integer depth, Integer domainUrls, Short dbid) {
		this.taskid = taskid;
		this.webName = webName;
		this.seedUrl = seedUrl;
		this.domainkey = domainkey;
		this.createTime = createTime;
		this.disableTime = disableTime;
		this.executeFrequency = executeFrequency;
		this.status = status;
		this.customerId = customerId;
		this.description = description;
		this.totalUrls = totalUrls;
		this.totalObjects = totalObjects;
		this.depth = depth;
		this.domainUrls = domainUrls;
		this.dbid = dbid;
	}

	// Property accessors

	public Integer getTaskid() {
		return this.taskid;
	}

	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}

	public String getWebName() {
		return this.webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public String getSeedUrl() {
		return this.seedUrl;
	}

	public void setSeedUrl(String seedUrl) {
		this.seedUrl = seedUrl;
	}

	public String getDomainkey() {
		return this.domainkey;
	}

	public void setDomainkey(String domainkey) {
		this.domainkey = domainkey;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getDisableTime() {
		return this.disableTime;
	}

	public void setDisableTime(Date disableTime) {
		this.disableTime = disableTime;
	}

	public Integer getExecuteFrequency() {
		return this.executeFrequency;
	}

	public void setExecuteFrequency(Integer executeFrequency) {
		this.executeFrequency = executeFrequency;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Short getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Short customerId) {
		this.customerId = customerId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTotalUrls() {
		return this.totalUrls;
	}

	public void setTotalUrls(Integer totalUrls) {
		this.totalUrls = totalUrls;
	}

	public Integer getTotalObjects() {
		return this.totalObjects;
	}

	public void setTotalObjects(Integer totalObjects) {
		this.totalObjects = totalObjects;
	}

	public Integer getDepth() {
		return this.depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public Integer getDomainUrls() {
		return this.domainUrls;
	}

	public void setDomainUrls(Integer domainUrls) {
		this.domainUrls = domainUrls;
	}

	public Short getDbid() {
		return this.dbid;
	}

	public void setDbid(Short dbid) {
		this.dbid = dbid;
	}

}