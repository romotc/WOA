package com.hongganju.db.entity.center;

import com.hongganju.db.entity.base.AbstractIpregion;

/**
 * Ipregion entity. @author MyEclipse Persistence Tools
 */
public class Ipregion extends AbstractIpregion implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Ipregion() {
	}

	/** minimal constructor */
	public Ipregion(String ipregionId) {
		super(ipregionId);
	}

	/** full constructor */
	public Ipregion(String ipregionId, String ipcidr15, String ipcidrmask15,
			Long cidrstartInt, Long cidrendInt, String cidrname, String area,
			String isp) {
		super(ipregionId, ipcidr15, ipcidrmask15, cidrstartInt, cidrendInt,
				cidrname, area, isp);
	}

}
