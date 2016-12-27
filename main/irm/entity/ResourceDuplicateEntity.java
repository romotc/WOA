/**
 * 
 */
package irm.entity;

/**
 * 全网重复资源实体
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class ResourceDuplicateEntity {

	// 网站名称
	private String site;
	// 域名
	private String domain;
	// 缓存数量
	private Integer cache_num;
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
	 * @return the cache_num
	 */
	public Integer getCache_num() {
		return cache_num;
	}
	/**
	 * @param cache_num the cache_num to set
	 */
	public void setCache_num(Integer cache_num) {
		this.cache_num = cache_num;
	}
}
