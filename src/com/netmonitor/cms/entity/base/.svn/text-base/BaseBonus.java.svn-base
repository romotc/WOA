package com.netmonitor.cms.entity.base;

import java.sql.Timestamp;

import com.netmonitor.cms.entity.Peer;


/**
 * AbstractBonus entity provides the base persistence definition of the Bonus entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseBonus  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer bonusNum;
     private Peer peer;
     private Timestamp time;
     private Float money;
     private Float balance;
     private String remark;


    // Constructors

    /** default constructor */
    public BaseBonus() {
    }

    
    /** full constructor */
    public BaseBonus(Peer peer, Timestamp time, Float money, Float balance, String remark) {
        this.peer = peer;
        this.time = time;
        this.money = money;
        this.balance = balance;
        this.remark = remark;
    }

   
    // Property accessors

    public Integer getBonusNum() {
        return this.bonusNum;
    }
    
    public void setBonusNum(Integer bonusNum) {
        this.bonusNum = bonusNum;
    }

    public Peer getPeer() {
        return this.peer;
    }
    
    public void setPeer(Peer peer) {
        this.peer = peer;
    }

    public Timestamp getTime() {
        return this.time;
    }
    
    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Float getMoney() {
        return this.money;
    }
    
    public void setMoney(Float money) {
        this.money = money;
    }

    public Float getBalance() {
        return this.balance;
    }
    
    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}