package com.netmonitor.cms.entity.base;

import java.sql.Timestamp;

import com.netmonitor.cms.entity.Customer;


/**
 * AbstractTestmode entity provides the base persistence definition of the Testmode entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseTestmode  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer testModeId;
     private Customer customer;
     private String testModeName;
     private String testModeType;
     private String payMode;
     private String taskType;
     private String taskControlLevel;
     private String connMode;
     private Short maxTaskNum;
     private Boolean dataAnalyse;
     private Short minTestFrequency;
     private Short subNodeGroupPeerNum;
     private Short cityNum;
     private Integer totalPeerNum;
     private Timestamp disabledDate;
     private Boolean pingOption;
     private Boolean nslookupOption;
     private Boolean tracerouteOption;
     private Boolean cutScreenOption;
     private Integer contractTotalTestNum;
     private String peerScope;


    // Constructors

    /** default constructor */
    public BaseTestmode() {
    }

    
    /** full constructor */
    public BaseTestmode(Customer customer, String testModeName, String testModeType, String payMode, String taskType, String taskControlLevel, String connMode, Short maxTaskNum, Boolean dataAnalyse, Short minTestFrequency, Short subNodeGroupPeerNum, Short cityNum, Integer totalPeerNum, Timestamp disabledDate, Boolean pingOption, Boolean nslookupOption, Boolean tracerouteOption, Boolean cutScreenOption, Integer contractTotalTestNum, String peerScope) {
        this.customer = customer;
        this.testModeName = testModeName;
        this.testModeType = testModeType;
        this.payMode = payMode;
        this.taskType = taskType;
        this.taskControlLevel = taskControlLevel;
        this.connMode = connMode;
        this.maxTaskNum = maxTaskNum;
        this.dataAnalyse = dataAnalyse;
        this.minTestFrequency = minTestFrequency;
        this.subNodeGroupPeerNum = subNodeGroupPeerNum;
        this.cityNum = cityNum;
        this.totalPeerNum = totalPeerNum;
        this.disabledDate = disabledDate;
        this.pingOption = pingOption;
        this.nslookupOption = nslookupOption;
        this.tracerouteOption = tracerouteOption;
        this.cutScreenOption = cutScreenOption;
        this.contractTotalTestNum = contractTotalTestNum;
        this.peerScope = peerScope;
    }

   
    // Property accessors

    public Integer getTestModeId() {
        return this.testModeId;
    }
    
    public void setTestModeId(Integer testModeId) {
        this.testModeId = testModeId;
    }

    public Customer getCustomer() {
        return this.customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getTestModeName() {
        return this.testModeName;
    }
    
    public void setTestModeName(String testModeName) {
        this.testModeName = testModeName;
    }

    public String getTestModeType() {
        return this.testModeType;
    }
    
    public void setTestModeType(String testModeType) {
        this.testModeType = testModeType;
    }

    public String getPayMode() {
        return this.payMode;
    }
    
    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getTaskType() {
        return this.taskType;
    }
    
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskControlLevel() {
        return this.taskControlLevel;
    }
    
    public void setTaskControlLevel(String taskControlLevel) {
        this.taskControlLevel = taskControlLevel;
    }

    public String getConnMode() {
        return this.connMode;
    }
    
    public void setConnMode(String connMode) {
        this.connMode = connMode;
    }

    public Short getMaxTaskNum() {
        return this.maxTaskNum;
    }
    
    public void setMaxTaskNum(Short maxTaskNum) {
        this.maxTaskNum = maxTaskNum;
    }

    public Boolean getDataAnalyse() {
        return this.dataAnalyse;
    }
    
    public void setDataAnalyse(Boolean dataAnalyse) {
        this.dataAnalyse = dataAnalyse;
    }

    public Short getMinTestFrequency() {
        return this.minTestFrequency;
    }
    
    public void setMinTestFrequency(Short minTestFrequency) {
        this.minTestFrequency = minTestFrequency;
    }

    public Short getSubNodeGroupPeerNum() {
        return this.subNodeGroupPeerNum;
    }
    
    public void setSubNodeGroupPeerNum(Short subNodeGroupPeerNum) {
        this.subNodeGroupPeerNum = subNodeGroupPeerNum;
    }

    public Short getCityNum() {
        return this.cityNum;
    }
    
    public void setCityNum(Short cityNum) {
        this.cityNum = cityNum;
    }

    public Integer getTotalPeerNum() {
        return this.totalPeerNum;
    }
    
    public void setTotalPeerNum(Integer totalPeerNum) {
        this.totalPeerNum = totalPeerNum;
    }

    public Timestamp getDisabledDate() {
        return this.disabledDate;
    }
    
    public void setDisabledDate(Timestamp disabledDate) {
        this.disabledDate = disabledDate;
    }

    public Boolean getPingOption() {
        return this.pingOption;
    }
    
    public void setPingOption(Boolean pingOption) {
        this.pingOption = pingOption;
    }

    public Boolean getNslookupOption() {
        return this.nslookupOption;
    }
    
    public void setNslookupOption(Boolean nslookupOption) {
        this.nslookupOption = nslookupOption;
    }

    public Boolean getTracerouteOption() {
        return this.tracerouteOption;
    }
    
    public void setTracerouteOption(Boolean tracerouteOption) {
        this.tracerouteOption = tracerouteOption;
    }

    public Boolean getCutScreenOption() {
        return this.cutScreenOption;
    }
    
    public void setCutScreenOption(Boolean cutScreenOption) {
        this.cutScreenOption = cutScreenOption;
    }

    public Integer getContractTotalTestNum() {
        return this.contractTotalTestNum;
    }
    
    public void setContractTotalTestNum(Integer contractTotalTestNum) {
        this.contractTotalTestNum = contractTotalTestNum;
    }

    public String getPeerScope() {
        return this.peerScope;
    }
    
    public void setPeerScope(String peerScope) {
        this.peerScope = peerScope;
    }
   








}