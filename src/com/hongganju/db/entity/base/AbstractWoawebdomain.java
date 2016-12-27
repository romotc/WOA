package com.hongganju.db.entity.base;

import java.util.Date;

/**
 * AbstractWoawebdomain entity provides the base persistence definition of the
 * Woawebdomain entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractWoawebdomain implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer unitId;
	private String runningNodeIp;
	private Integer runningstatus;
	private Date aliveTime;
	private String domainUrls;

	// Constructors

	/** default constructor */
	public AbstractWoawebdomain() {
	}

	/** minimal constructor */
	public AbstractWoawebdomain(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractWoawebdomain(Integer id, Integer unitId,
			String runningNodeIp, Integer runningstatus, Date aliveTime,
			String domainUrls) {
		this.id = id;
		this.unitId = unitId;
		this.runningNodeIp = runningNodeIp;
		this.runningstatus = runningstatus;
		this.aliveTime = aliveTime;
		this.domainUrls = domainUrls;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getRunningNodeIp() {
		return this.runningNodeIp;
	}

	public void setRunningNodeIp(String runningNodeIp) {
		this.runningNodeIp = runningNodeIp;
	}

	public Integer getRunningstatus() {
		return this.runningstatus;
	}

	public void setRunningstatus(Integer runningstatus) {
		this.runningstatus = runningstatus;
	}

	public Date getAliveTime() {
		return this.aliveTime;
	}

	public void setAliveTime(Date aliveTime) {
		this.aliveTime = aliveTime;
	}

	public String getDomainUrls() {
		return this.domainUrls;
	}

	public void setDomainUrls(String domainUrls) {
		this.domainUrls = domainUrls;
	}

}