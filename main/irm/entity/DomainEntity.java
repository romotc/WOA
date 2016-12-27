/**
 * 
 */
package irm.entity;

/**
 * 域名库明细
 * @author	TangDican
 * @date	2013 Sep 4, 2013
 * @email	tangdican2008@163.com
 */
public class DomainEntity {

	// 域名
	private String domain;
	// ip
	private String ip;
	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}
	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
