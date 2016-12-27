package com.netmonitor.cms.entity.base;

import java.util.Date;

import com.netmonitor.cms.entity.Peer;


/**
 * AbstractPeerprizestat entity provides the base persistence definition of the Peerprizestat entity. @author MyEclipse Persistence Tools
 */

public abstract class BasePeerprizestat  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
     private Peer peer;
     private Date monitorDate;
     private Integer totalPrize;
     private Integer totalFinishTaskNum;
     private Integer tt1;
     private Integer tt2;
     private Integer tt3;
     private Integer tt4;
     private Integer tt5;
     private Integer tt6;
     private Integer tt7;
     private Integer tt8;
     private Integer tt9;
     private Integer tt10;
     private Integer tt11;
     private Integer tt12;


    // Constructors

    /** default constructor */
    public BasePeerprizestat() {
    }

	/** minimal constructor */
    public BasePeerprizestat(Peer peer) {
        this.peer = peer;
    }
    
    /** full constructor */
    public BasePeerprizestat(Peer peer, Date monitorDate, Integer totalPrize, Integer totalFinishTaskNum, Integer tt1, Integer tt2, Integer tt3, Integer tt4, Integer tt5, Integer tt6, Integer tt7, Integer tt8, Integer tt9, Integer tt10, Integer tt11, Integer tt12) {
        this.peer = peer;
        this.monitorDate = monitorDate;
        this.totalPrize = totalPrize;
        this.totalFinishTaskNum = totalFinishTaskNum;
        this.tt1 = tt1;
        this.tt2 = tt2;
        this.tt3 = tt3;
        this.tt4 = tt4;
        this.tt5 = tt5;
        this.tt6 = tt6;
        this.tt7 = tt7;
        this.tt8 = tt8;
        this.tt9 = tt9;
        this.tt10 = tt10;
        this.tt11 = tt11;
        this.tt12 = tt12;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Peer getPeer() {
        return this.peer;
    }
    
    public void setPeer(Peer peer) {
        this.peer = peer;
    }

    public Date getMonitorDate() {
        return this.monitorDate;
    }
    
    public void setMonitorDate(Date monitorDate) {
        this.monitorDate = monitorDate;
    }

    public Integer getTotalPrize() {
        return this.totalPrize;
    }
    
    public void setTotalPrize(Integer totalPrize) {
        this.totalPrize = totalPrize;
    }

    public Integer getTotalFinishTaskNum() {
        return this.totalFinishTaskNum;
    }
    
    public void setTotalFinishTaskNum(Integer totalFinishTaskNum) {
        this.totalFinishTaskNum = totalFinishTaskNum;
    }

    public Integer getTt1() {
        return this.tt1;
    }
    
    public void setTt1(Integer tt1) {
        this.tt1 = tt1;
    }

    public Integer getTt2() {
        return this.tt2;
    }
    
    public void setTt2(Integer tt2) {
        this.tt2 = tt2;
    }

    public Integer getTt3() {
        return this.tt3;
    }
    
    public void setTt3(Integer tt3) {
        this.tt3 = tt3;
    }

    public Integer getTt4() {
        return this.tt4;
    }
    
    public void setTt4(Integer tt4) {
        this.tt4 = tt4;
    }

    public Integer getTt5() {
        return this.tt5;
    }
    
    public void setTt5(Integer tt5) {
        this.tt5 = tt5;
    }

    public Integer getTt6() {
        return this.tt6;
    }
    
    public void setTt6(Integer tt6) {
        this.tt6 = tt6;
    }

    public Integer getTt7() {
        return this.tt7;
    }
    
    public void setTt7(Integer tt7) {
        this.tt7 = tt7;
    }

    public Integer getTt8() {
        return this.tt8;
    }
    
    public void setTt8(Integer tt8) {
        this.tt8 = tt8;
    }

    public Integer getTt9() {
        return this.tt9;
    }
    
    public void setTt9(Integer tt9) {
        this.tt9 = tt9;
    }

    public Integer getTt10() {
        return this.tt10;
    }
    
    public void setTt10(Integer tt10) {
        this.tt10 = tt10;
    }

    public Integer getTt11() {
        return this.tt11;
    }
    
    public void setTt11(Integer tt11) {
        this.tt11 = tt11;
    }

    public Integer getTt12() {
        return this.tt12;
    }
    
    public void setTt12(Integer tt12) {
        this.tt12 = tt12;
    }
   








}