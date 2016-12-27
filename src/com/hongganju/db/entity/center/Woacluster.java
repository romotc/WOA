package com.hongganju.db.entity.center;

import com.hongganju.db.entity.base.AbstractWoacluster;

/**
 * Woacluster entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Woacluster extends AbstractWoacluster implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Woacluster() {
	}

	/** minimal constructor */
	public Woacluster(Integer clusterId) {
		super(clusterId);
	}

	/** full constructor */
	public Woacluster(Integer clusterId, String areaId, String ispid) {
		super(clusterId, areaId, ispid);
	}

}
