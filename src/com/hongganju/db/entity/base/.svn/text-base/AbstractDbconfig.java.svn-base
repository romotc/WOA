package com.hongganju.db.entity.base;

/**
 * AbstractDbconfig entity provides the base persistence definition of the
 * Dbconfig entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDbconfig implements java.io.Serializable {

	// Fields

	private Integer id;
	private String dburl;
	private String username;
	private Integer maxConnection;
	private Integer minConnection;

	// Constructors

	/** default constructor */
	public AbstractDbconfig() {
	}

	/** full constructor */
	public AbstractDbconfig(String dburl, String username,
			Integer maxConnection, Integer minConnection) {
		this.dburl = dburl;
		this.username = username;
		this.maxConnection = maxConnection;
		this.minConnection = minConnection;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDburl() {
		return this.dburl;
	}

	public void setDburl(String dburl) {
		this.dburl = dburl;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getMaxConnection() {
		return this.maxConnection;
	}

	public void setMaxConnection(Integer maxConnection) {
		this.maxConnection = maxConnection;
	}

	public Integer getMinConnection() {
		return this.minConnection;
	}

	public void setMinConnection(Integer minConnection) {
		this.minConnection = minConnection;
	}

}