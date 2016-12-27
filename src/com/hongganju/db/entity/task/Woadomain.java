package com.hongganju.db.entity.task;

import java.util.Date;

import com.hongganju.db.entity.base.AbstractWoadomain;

/**
 * Woadomain entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Woadomain extends AbstractWoadomain implements
		java.io.Serializable {

	// Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** default constructor */
	public Woadomain() {
	}

	/** minimal constructor */
	public Woadomain(Integer id) {
		super(id);
	}

	/** full constructor */
	public Woadomain(Integer id, Integer taskid, Integer clusterid,
			String nodeIp, String nodeAreaId, String nodeIspid,
			Integer instanceId, String domain, String localIsp,Date uploadTime,
			Integer errorNumber, Integer totalUrls, Integer localurl,
			Float localRate, Float totalDownloadSpeed, Float totalDownloadTime,
			Float totalMaxSpeed, Float totalMinSpeed, Float totalMaxTime,
			Float totalMinTime, Float localDownloadSpeed,
			Float localDownloadTime, Float localMaxSpeed, Float localMinSpeed,
			Float localMaxTime, Float localMinTime, Float foreignDownloadSpeed,
			Float foreignDownloadTime, Float foreignMaxSpeed,
			Float foreignMinSpeed, Float foreignMaxTime, Float foreignMinTime) {
		super(id, taskid, clusterid, nodeIp, nodeAreaId, nodeIspid, instanceId,
				domain,localIsp, uploadTime, errorNumber, totalUrls, localurl,
				localRate, totalDownloadSpeed, totalDownloadTime,
				totalMaxSpeed, totalMinSpeed, totalMaxTime, totalMinTime,
				localDownloadSpeed, localDownloadTime, localMaxSpeed,
				localMinSpeed, localMaxTime, localMinTime,
				foreignDownloadSpeed, foreignDownloadTime, foreignMaxSpeed,
				foreignMinSpeed, foreignMaxTime, foreignMinTime);
	}

}
