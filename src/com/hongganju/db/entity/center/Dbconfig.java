package com.hongganju.db.entity.center;

import com.hongganju.db.entity.base.AbstractDbconfig;

/**
 * Dbconfig entity. @author MyEclipse Persistence Tools
 */
public class Dbconfig extends AbstractDbconfig implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Dbconfig() {
	}

	/** full constructor */
	public Dbconfig(String dburl, String username, Integer maxConnection,
			Integer minConnection) {
		super(dburl, username, maxConnection, minConnection);
	}

}
