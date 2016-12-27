package com.netmonitor.cms.entity.base;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.netmonitor.cms.entity.Awardrequest;


/**
 * AbstractAward entity provides the base persistence definition of the Award entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseAward  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer awardId;
     private String name;
     private String image;
     private Timestamp startTime;
     private String status;
     private Integer priceOfAward;
     private Set<Awardrequest> awardrequests = new HashSet<Awardrequest>(0);


    // Constructors

    /** default constructor */
    public BaseAward() {
    }

    
    /** full constructor */
    public BaseAward(String name, String image, Timestamp startTime, String status, Integer priceOfAward, Set<Awardrequest> awardrequests) {
        this.name = name;
        this.image = image;
        this.startTime = startTime;
        this.status = status;
        this.priceOfAward = priceOfAward;
        this.awardrequests = awardrequests;
    }

   
    // Property accessors

    public Integer getAwardId() {
        return this.awardId;
    }
    
    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriceOfAward() {
        return this.priceOfAward;
    }
    
    public void setPriceOfAward(Integer priceOfAward) {
        this.priceOfAward = priceOfAward;
    }

    public Set<Awardrequest> getAwardrequests() {
        return this.awardrequests;
    }
    
    public void setAwardrequests(Set<Awardrequest> awardrequests) {
        this.awardrequests = awardrequests;
    }
   








}