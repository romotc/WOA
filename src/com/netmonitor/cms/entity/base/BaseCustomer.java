package com.netmonitor.cms.entity.base;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import com.netmonitor.cms.entity.Commercialagent;


/**
 * AbstractCustomer entity provides the base persistence definition of the Customer entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseCustomer  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private java.lang.Long id;
     private Commercialagent commercialagent;
     private String name;
     private String state;
     private String description;
     private Date createDate;
     private String loginName;
     private String custAddress;
     private String linkMan;
     private String phone;
     private String mobile;
     private String fax;
     private String email;
     private String webSiteName;
     private String webSiteType;
     private String deployType;
     public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



	private Boolean exclusive;
     private String password;


    // Constructors

    /** default constructor */
    public BaseCustomer() {
    	initialize ();
    }



    protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;
   
    // Property accessors

    public Commercialagent getCommercialagent() {
        return this.commercialagent;
    }
    
	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public void setCommercialagent(Commercialagent commercialagent) {
        this.commercialagent = commercialagent;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getLoginName() {
        return this.loginName;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getCustAddress() {
        return this.custAddress;
    }
    
    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getLinkMan() {
        return this.linkMan;
    }
    
    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebSiteName() {
        return this.webSiteName;
    }
    
    public void setWebSiteName(String webSiteName) {
        this.webSiteName = webSiteName;
    }

    public String getWebSiteType() {
        return this.webSiteType;
    }
    
    public void setWebSiteType(String webSiteType) {
        this.webSiteType = webSiteType;
    }

    public String getDeployType() {
        return this.deployType;
    }
    
    public void setDeployType(String deployType) {
        this.deployType = deployType;
    }

    public Boolean getExclusive() {
        return this.exclusive;
    }
    
    public void setExclusive(Boolean exclusive) {
        this.exclusive = exclusive;
    }

    public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.netmonitor.cms.entity.Customer)) return false;
		else {
			com.netmonitor.cms.entity.Customer voteTopic = (com.netmonitor.cms.entity.Customer) obj;
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