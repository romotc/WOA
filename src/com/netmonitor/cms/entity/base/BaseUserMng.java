package com.netmonitor.cms.entity.base;

import java.io.Serializable;
import java.util.Date;

/**
 * AbstractUser entity provides the base persistence definition of the User
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseUserMng implements Serializable {

	public static String REF = "UserMng";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_REMARK = "remark";
	public static String PROP_STATUS = "status";
	public static String PROP_INDEXPAGE = "indexPage";
	public static String PROP_EMAIL = "email";
	public static String PROP_MOBILE = "mobile";
	public static String PROP_PHONE = "phone";
	public static String PROP_PASSWORD = "password";
	public static String PROP_REALNAME = "realName";
	public static String PROP_USERNAME = "userName";
	public static String PROP_CUSTOMID = "customId";
	public static String PROP_USERTYPE = "userType";
	public static String PROP_ID = "id";
	
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.Long id;
	//
	private String userType;
	private Short customId;
	private String userName;
	private String realName;
	private String password;
	private String phone;
	private String mobile;
	private String email;
	private String indexPage;
	private String status;
	private String remark;
	private Date createTime;

	// Constructors

	private int hashCode = Integer.MIN_VALUE;
	//
	/** default constructor */
	public BaseUserMng() {
		initialize();
	}

	protected void initialize () {}
	
	/** minimal constructor */
	public BaseUserMng(java.lang.Long userId) {
		this.id = userId;
	}

	/** full constructor */
	public BaseUserMng(String userType, Short customId, String userName,
			String realName, String password, String phone, String mobile,
			String email, String indexPage, String status, String remark,
			Date createTime) {
		this.userType = userType;
		this.customId = customId;
		this.userName = userName;
		this.realName = realName;
		this.password = password;
		this.phone = phone;
		this.mobile = mobile;
		this.email = email;
		this.indexPage = indexPage;
		this.status = status;
		this.remark = remark;
		this.createTime = createTime;
	}

	// Property accessors
	

	public String getUserType() {
		return this.userType;
	}

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Short getCustomId() {
		return this.customId;
	}

	public void setCustomId(Short customId) {
		this.customId = customId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndexPage() {
		return this.indexPage;
	}

	public void setIndexPage(String indexPage) {
		this.indexPage = indexPage;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.netmonitor.cms.entity.UserMng)) return false;
		else {
			com.netmonitor.cms.entity.UserMng user = (com.netmonitor.cms.entity.UserMng) obj;
			if (null == this.getId() || null == user.getId()) return false;
			else return (this.getId().equals(user.getId()));
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