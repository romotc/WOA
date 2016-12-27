package com.hongganju.db.entity.base;

import java.util.Date;

/**
 * AbstractWoaunit entity provides the base persistence definition of the
 * Woaunit entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractWoaunit implements java.io.Serializable {

	// Fields

	private Integer unitId;
	private Integer taskid;
	private Integer clusterid;
	private String runningNodeIp;
	private Integer runningstatus;
	private Date aliveTime;

	// Constructors

	/** default constructor */
	public AbstractWoaunit() {
	}

	/** minimal constructor */
	public AbstractWoaunit(Integer unitId) {
		this.unitId = unitId;
	}

	/** full constructor */
	public AbstractWoaunit(Integer unitId, Integer taskid, Integer clusterid,
			String runningNodeIp, Integer runningstatus, Date aliveTime) {
		this.unitId = unitId;
		this.taskid = taskid;
		this.clusterid = clusterid;
		this.runningNodeIp = runningNodeIp;
		this.runningstatus = runningstatus;
		this.aliveTime = aliveTime;
	}

	// Property accessors

	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
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

}