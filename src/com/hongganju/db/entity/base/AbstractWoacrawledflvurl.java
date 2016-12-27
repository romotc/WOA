package com.hongganju.db.entity.base;

import java.util.Date;

public class AbstractWoacrawledflvurl   implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer taskid;
	private Integer urlNo;
	private String url;
	private Integer instanceId;
	private String refer;
	private Integer depth;
	private Date uploadTime;
	private String errorDescription;
	private Integer status;
	private String flvTitle;
	private String flvurl;
	
	// Constructors

	// Property accessors

	public String getFlvTitle() {
		return flvTitle;
	}

	public void setFlvTitle(String flvTitle) {
		this.flvTitle = flvTitle;
	}

	public String getFlvurl() {
		return flvurl;
	}

	public void setFlvurl(String flvurl) {
		this.flvurl = flvurl;
	}

	public Integer getId() {
		return this.id;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getUrlNo() {
		return this.urlNo;
	}

	public void setUrlNo(Integer urlNo) {
		this.urlNo = urlNo;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getInstanceId() {
		return this.instanceId;
	}

	public void setInstanceId(Integer instanceId) {
		this.instanceId = instanceId;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

}