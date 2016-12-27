package com.netmonitor.cms.entity;

import java.util.Date;

import com.netmonitor.cms.entity.Peer;
import com.netmonitor.cms.entity.base.BasePeerprizestat;


/**
 * Peerprizestat entity. @author MyEclipse Persistence Tools
 */
public class Peerprizestat extends BasePeerprizestat implements java.io.Serializable {

    // Constructors

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** default constructor */
    public Peerprizestat() {
    }

	/** minimal constructor */
    public Peerprizestat(Peer peer) {
        super(peer);        
    }
    
    /** full constructor */
    public Peerprizestat(Peer peer, Date monitorDate, Integer totalPrize, Integer totalFinishTaskNum, Integer tt1, Integer tt2, Integer tt3, Integer tt4, Integer tt5, Integer tt6, Integer tt7, Integer tt8, Integer tt9, Integer tt10, Integer tt11, Integer tt12) {
        super(peer, monitorDate, totalPrize, totalFinishTaskNum, tt1, tt2, tt3, tt4, tt5, tt6, tt7, tt8, tt9, tt10, tt11, tt12);        
    }
   
}
