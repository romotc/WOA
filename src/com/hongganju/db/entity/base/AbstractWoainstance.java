package com.hongganju.db.entity.base;

import java.util.Date;

/**
 * AbstractWoainstance entity provides the base persistence definition of the
 * Woainstance entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractWoainstance implements java.io.Serializable {

	// Fields

	private Integer taskinstanceId;
	private Integer taskid;
	private Integer unitId;
	private Date startTime;
	private Date endTime;
	private Integer status;
	private Integer currentCrawledUrl;
	private Integer currentFlvUrl;
	private Integer batTaskId;
	// Constructors

	public Integer getCurrentFlvUrl() {
		return currentFlvUrl;
	}

	public Integer getBatTaskId() {
		return batTaskId;
	}

	public void setBatTaskId(Integer batTaskId) {
		this.batTaskId = batTaskId;
	}

	public void setCurrentFlvUrl(Integer currentFlvUrl) {
		this.currentFlvUrl = currentFlvUrl;
	}

	/** default constructor */
	public AbstractWoainstance() {
	}

	/** minimal constructor */
	public AbstractWoainstance(Integer taskinstanceId) {
		this.taskinstanceId = taskinstanceId;
	}

	/** full constructor */
	public AbstractWoainstance(Integer taskinstanceId, Integer taskid,
			Integer unitId, Date startTime, Date endTime, Integer status) {
		this.taskinstanceId = taskinstanceId;
		this.taskid = taskid;
		this.unitId = unitId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}

	// Property accessors

	public Integer getTaskinstanceId() {
		return this.taskinstanceId;
	}

	public void setTaskinstanceId(Integer taskinstanceId) {
		this.taskinstanceId = taskinstanceId;
	}

	public Integer getTaskid() {
		return this.taskid;
	}

	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}

	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCurrentCrawledUrl() {
		return currentCrawledUrl;
	}

	public void setCurrentCrawledUrl(Integer currentCrawledUrl) {
		this.currentCrawledUrl = currentCrawledUrl;
	}

}