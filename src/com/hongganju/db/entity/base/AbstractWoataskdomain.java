package com.hongganju.db.entity.base;

/**
 * AbstractWoataskdomain entity provides the base persistence definition of the
 * Woataskdomain entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractWoataskdomain implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer taskid;
	private String domainUrls;

	// Constructors

	/** default constructor */
	public AbstractWoataskdomain() {
	}

	/** full constructor */
	public AbstractWoataskdomain(Integer id, Integer taskid, String domainUrls) {
		this.id = id;
		this.taskid = taskid;
		this.domainUrls = domainUrls;
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

	public String getDomainUrls() {
		return this.domainUrls;
	}

	public void setDomainUrls(String domainUrls) {
		this.domainUrls = domainUrls;
	}

}