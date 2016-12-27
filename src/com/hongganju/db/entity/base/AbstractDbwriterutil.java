package com.hongganju.db.entity.base;

import java.sql.Timestamp;

/**
 * AbstractDbwriterutil entity provides the base persistence definition of the
 * Dbwriterutil entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDbwriterutil implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Timestamp writeTime;
	private Integer type;
	private Short dbid;

	// Constructors

	/** default constructor */
	public AbstractDbwriterutil() {
	}

	/** full constructor */
	public AbstractDbwriterutil(String name, Timestamp writeTime, Integer type,
			Short dbid) {
		this.name = name;
		this.writeTime = writeTime;
		this.type = type;
		this.dbid = dbid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getWriteTime() {
		return this.writeTime;
	}

	public void setWriteTime(Timestamp writeTime) {
		this.writeTime = writeTime;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Short getDbid() {
		return this.dbid;
	}

	public void setDbid(Short dbid) {
		this.dbid = dbid;
	}

}