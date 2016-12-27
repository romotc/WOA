/**
 * 
 */
package irm.entity;

/**
 * 域名库概览实体
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class DomainViewEntity {

	// 区域id
	private String areaid;
	// 域名
	private String domain;
	// 网站名称
	private String site;
	// url数量
	private Integer url_cnt;
	// url大小
	private float url_size;
	// 网内url数量
	private Integer netin_url_cnt;
	// 网内url资源占比
	private float netin_url_resource_rate;
	// 运营商id
	private String ispid;
	// 网内url大小
	private Integer netin_url_size;
	// 网内url资源深度
	private float netin_url_resource_size_rate;
	/**
	 * @return the areaid
	 */
	public String getAreaid() {
		return areaid;
	}
	/**
	 * @param areaid the areaid to set
	 */
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
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
	 * @return the site
	 */
	public String getSite() {
		return site;
	}
	/**
	 * @param site the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}
	/**
	 * @return the url_cnt
	 */
	public Integer getUrl_cnt() {
		return url_cnt;
	}
	/**
	 * @param url_cnt the url_cnt to set
	 */
	public void setUrl_cnt(Integer url_cnt) {
		this.url_cnt = url_cnt;
	}
	/**
	 * @return the url_size
	 */
	public float getUrl_size() {
		return url_size;
	}
	/**
	 * @param url_size the url_size to set
	 */
	public void setUrl_size(float url_size) {
		this.url_size = url_size;
	}
	/**
	 * @return the netin_url_cnt
	 */
	public Integer getNetin_url_cnt() {
		return netin_url_cnt;
	}
	/**
	 * @param netin_url_cnt the netin_url_cnt to set
	 */
	public void setNetin_url_cnt(Integer netin_url_cnt) {
		this.netin_url_cnt = netin_url_cnt;
	}
	/**
	 * @return the netin_url_resource_rate
	 */
	public float getNetin_url_resource_rate() {
		return netin_url_resource_rate;
	}
	/**
	 * @param netin_url_resource_rate the netin_url_resource_rate to set
	 */
	public void setNetin_url_resource_rate(float netin_url_resource_rate) {
		this.netin_url_resource_rate = netin_url_resource_rate;
	}
	/**
	 * @return the ispid
	 */
	public String getIspid() {
		return ispid;
	}
	/**
	 * @param ispid the ispid to set
	 */
	public void setIspid(String ispid) {
		this.ispid = ispid;
	}
	/**
	 * @return the netin_url_size
	 */
	public Integer getNetin_url_size() {
		return netin_url_size;
	}
	/**
	 * @param netin_url_size the netin_url_size to set
	 */
	public void setNetin_url_size(Integer netin_url_size) {
		this.netin_url_size = netin_url_size;
	}
	/**
	 * @return the netin_url_resource_size_rate
	 */
	public float getNetin_url_resource_size_rate() {
		return netin_url_resource_size_rate;
	}
	/**
	 * @param netin_url_resource_size_rate the netin_url_resource_size_rate to set
	 */
	public void setNetin_url_resource_size_rate(float netin_url_resource_size_rate) {
		this.netin_url_resource_size_rate = netin_url_resource_size_rate;
	}
}
