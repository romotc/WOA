package com.hongganju.db.entity.center;

import com.hongganju.db.entity.base.AbstractWoataskdomain;

/**
 * Woataskdomain entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Woataskdomain extends AbstractWoataskdomain implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Woataskdomain() {
	}

	/** full constructor */
	public Woataskdomain(Integer id, Integer taskid, String domainUrls) {
		super(id, taskid, domainUrls);
	}

}
