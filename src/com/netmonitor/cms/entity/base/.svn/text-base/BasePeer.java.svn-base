package com.netmonitor.cms.entity.base;

import java.util.HashSet;
import java.util.Set;

import com.netmonitor.cms.entity.Commercialagent;
import com.netmonitor.cms.entity.Awardrequest;
import com.netmonitor.cms.entity.Bonus;
import com.netmonitor.cms.entity.Peerprizestat;

/**
 * AbstractPeer entity provides the base persistence definition of the Peer
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class BasePeer implements java.io.Serializable {

	// Fields
	private String peerIP;
	private String peerId;
	private String memberId;
	private String surfMode;
	private String surfLocation;
	private String area;
	private String isp;
	private String connMode;
	private String dns;
	private Integer sumOnlineTime;
	private Integer sumAward;
	private Integer sumRecTasks;
	private Integer sumFinTasks;
	private Short dbid;
	private String taskControlLevel;
	private String os;
	private String browserVersion;
	private String flashVersion;
	private Integer peerOnlineId;
	private Integer black;
	private Boolean validity;
	private String memberType;
	private Integer flashVersionInt;
	private Integer ieVersion;
	private Integer ffVersion;
	private Integer hbrowserVersion;
	private Integer peerRank;
	// Constructors
	private String peerVersion;
	public String getPeerVersion() {
		return peerVersion;
	}

	public Integer getFlashVersionInt() {
		return flashVersionInt;
	}

	public void setFlashVersionInt(Integer flashVersionInt) {
		this.flashVersionInt = flashVersionInt;
	}

	public Integer getIeVersion() {
		return ieVersion;
	}

	public void setIeVersion(Integer ieVersion) {
		this.ieVersion = ieVersion;
	}

	public Integer getFfVersion() {
		return ffVersion;
	}

	public void setFfVersion(Integer ffVersion) {
		this.ffVersion = ffVersion;
	}

	public Integer getHbrowserVersion() {
		return hbrowserVersion;
	}

	public void setHbrowserVersion(Integer hbrowserVersion) {
		this.hbrowserVersion = hbrowserVersion;
	}

	public void setPeerVersion(String peerVersion) {
		this.peerVersion = peerVersion;
	}

	/** default constructor */
	public BasePeer() {
	}

	/** full constructor */
	public BasePeer(String memberId, String surfMode, String surfLocation,
			String area, String isp, String connMode, String dns,
			String zfbaccountsName, String zfbaccounts, String manualIsp,
			String manualCity, Integer sumOnlineTime, Integer sumAward,
			Integer sumRecTasks, Integer sumFinTasks, Short dbid,
			String taskControlLevel, String os, String browserVersion,
			String flashVersion, Integer peerOnlineId, Integer black,
			Boolean validity) {
		this.memberId = memberId;
		this.surfMode = surfMode;
		this.surfLocation = surfLocation;
		this.area = area;
		this.isp = isp;
		this.connMode = connMode;
		this.dns = dns;

		this.sumOnlineTime = sumOnlineTime;
		this.sumAward = sumAward;
		this.sumRecTasks = sumRecTasks;
		this.sumFinTasks = sumFinTasks;
		this.dbid = dbid;
		this.taskControlLevel = taskControlLevel;
		this.os = os;
		this.browserVersion = browserVersion;
		this.flashVersion = flashVersion;
		this.peerOnlineId = peerOnlineId;
		this.black = black;
		this.validity = validity;
	}

	// Property accessors
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getMemberType() {
		return this.memberType;
	}

	public String getPeerId() {
		return this.peerId;
	}

	public void setPeerId(String peerId) {
		this.peerId = peerId;
	}

	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getSurfMode() {
		return this.surfMode;
	}

	public void setSurfMode(String surfMode) {
		this.surfMode = surfMode;
	}

	public String getSurfLocation() {
		return this.surfLocation;
	}

	public void setSurfLocation(String surfLocation) {
		this.surfLocation = surfLocation;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getIsp() {
		return this.isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getConnMode() {
		return this.connMode;
	}

	public void setConnMode(String connMode) {
		this.connMode = connMode;
	}

	public String getDns() {
		return this.dns;
	}

	public void setDns(String dns) {
		this.dns = dns;
	}

	public Integer getSumOnlineTime() {
		return this.sumOnlineTime;
	}

	public void setSumOnlineTime(Integer sumOnlineTime) {
		this.sumOnlineTime = sumOnlineTime;
	}

	public Integer getSumAward() {
		return this.sumAward;
	}

	public void setSumAward(Integer sumAward) {
		this.sumAward = sumAward;
	}

	public Integer getSumRecTasks() {
		return this.sumRecTasks;
	}

	public void setSumRecTasks(Integer sumRecTasks) {
		this.sumRecTasks = sumRecTasks;
	}

	public Integer getSumFinTasks() {
		return this.sumFinTasks;
	}

	public void setSumFinTasks(Integer sumFinTasks) {
		this.sumFinTasks = sumFinTasks;
	}

	public Short getDbid() {
		return this.dbid;
	}

	public void setDbid(Short dbid) {
		this.dbid = dbid;
	}

	public String getTaskControlLevel() {
		return this.taskControlLevel;
	}

	public void setTaskControlLevel(String taskControlLevel) {
		this.taskControlLevel = taskControlLevel;
	}

	public String getOs() {
		return this.os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getBrowserVersion() {
		return this.browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	public String getFlashVersion() {
		return this.flashVersion;
	}

	public void setFlashVersion(String flashVersion) {
		this.flashVersion = flashVersion;
	}

	public Integer getPeerOnlineId() {
		return this.peerOnlineId;
	}

	public void setPeerOnlineId(Integer peerOnlineId) {
		this.peerOnlineId = peerOnlineId;
	}

	public Integer getBlack() {
		return this.black;
	}

	public void setBlack(Integer black) {
		this.black = black;
	}

	public Boolean getValidity() {
		return this.validity;
	}

	public void setValidity(Boolean validity) {
		this.validity = validity;
	}

	public String getPeerIP() {
		return peerIP;
	}

	public void setPeerIP(String peerIP) {
		this.peerIP = peerIP;
	}

	public Integer getPeerRank() {
		return peerRank;
	}

	public void setPeerRank(Integer peerRank) {
		this.peerRank = peerRank;
	}

}