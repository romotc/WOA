package com.netmonitor.cms.entity.base;

import java.sql.Timestamp;

import com.netmonitor.cms.entity.Peer;
import com.netmonitor.cms.entity.Award;


/**
 * AbstractAwardrequest entity provides the base persistence definition of the Awardrequest entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseAwardrequest  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer requestId;
     private Award award;
     private Peer peer;
     private Timestamp requestDate;
     private String requestStatus;
     private String remark;


    // Constructors

    /** default constructor */
    public BaseAwardrequest() {
    }

    
    /** full constructor */
    public BaseAwardrequest(Award award, Peer peer, Timestamp requestDate, String requestStatus, String remark) {
        this.award = award;
        this.peer = peer;
        this.requestDate = requestDate;
        this.requestStatus = requestStatus;
        this.remark = remark;
    }

   
    // Property accessors

    public Integer getRequestId() {
        return this.requestId;
    }
    
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Award getAward() {
        return this.award;
    }
    
    public void setAward(Award award) {
        this.award = award;
    }

    public Peer getPeer() {
        return this.peer;
    }
    
    public void setPeer(Peer peer) {
        this.peer = peer;
    }

    public Timestamp getRequestDate() {
        return this.requestDate;
    }
    
    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestStatus() {
        return this.requestStatus;
    }
    
    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}