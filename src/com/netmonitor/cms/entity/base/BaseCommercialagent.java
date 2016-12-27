package com.netmonitor.cms.entity.base;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import com.netmonitor.cms.entity.Customer;
import com.netmonitor.cms.entity.Peer;


/**
 * AbstractCommercialagent entity provides the base persistence definition of the Commercialagent entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseCommercialagent  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private java.lang.Long id;
     private String commercialAgentName;
     private String commercialAgentType;
     private Date createDate;
     private String email;
     private String fax;
     private String mobile;
     private String phone;
     private String linkMan;
     private String custAddress;
     private String webSiteName;
     private Set<Peer> peers;
     private Set<Customer> customers;


    // Constructors

    /** default constructor */
    public BaseCommercialagent() {
    	initialize ();
    }

    
    /** full constructor */
    public BaseCommercialagent(String commercialAgentName, String commercialAgentType, Timestamp createDate, String email, String fax, String mobile, String phone, String linkMan, String custAddress, String webSiteName, Set<Peer> peers, Set<Customer> customers) {
        this.commercialAgentName = commercialAgentName;
        this.commercialAgentType = commercialAgentType;
        this.createDate = createDate;
        this.email = email;
        this.fax = fax;
        this.mobile = mobile;
        this.phone = phone;
        this.linkMan = linkMan;
        this.custAddress = custAddress;
        this.webSiteName = webSiteName;
        this.peers = peers;
        this.customers = customers;
        initialize ();
    }
    protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

   
    // Property accessors
    

    public String getCommercialAgentName() {
        return this.commercialAgentName;
    }
    
    public java.lang.Long getId() {
		return id;
	}


	public void setId(java.lang.Long id) {
		this.id = id;
	}


	public void setCommercialAgentName(String commercialAgentName) {
        this.commercialAgentName = commercialAgentName;
    }

    public String getCommercialAgentType() {
        return this.commercialAgentType;
    }
    
    public void setCommercialAgentType(String commercialAgentType) {
        this.commercialAgentType = commercialAgentType;
    }

    public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLinkMan() {
        return this.linkMan;
    }
    
    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getCustAddress() {
        return this.custAddress;
    }
    
    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getWebSiteName() {
        return this.webSiteName;
    }
    
    public void setWebSiteName(String webSiteName) {
        this.webSiteName = webSiteName;
    }

    public Set<Peer> getPeers() {
        return this.peers;
    }
    
    public void setPeers(Set<Peer> peers) {
        this.peers = peers;
    }

    public Set<Customer> getCustomers() {
        return this.customers;
    }
    
    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
    public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.netmonitor.cms.entity.Commercialagent)) return false;
		else {
			com.netmonitor.cms.entity.Commercialagent voteTopic = (com.netmonitor.cms.entity.Commercialagent) obj;
			if (null == this.getId() || null == voteTopic.getId()) return false;
			else return (this.getId().equals(voteTopic.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public String toString () {
		return super.toString();
	}
}