package com.netmonitor.cms.entity;

import java.sql.Timestamp;

import com.netmonitor.cms.entity.Peer;
import com.netmonitor.cms.entity.base.BaseBonus;


/**
 * Bonus entity. @author MyEclipse Persistence Tools
 */
public class Bonus extends BaseBonus implements java.io.Serializable {

    // Constructors

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/** default constructor */
    public Bonus() {
    }

    
    /** full constructor */
    public Bonus(Peer peer, Timestamp time, Float money, Float balance, String remark) {
        super(peer, time, money, balance, remark);        
    }
   
}
