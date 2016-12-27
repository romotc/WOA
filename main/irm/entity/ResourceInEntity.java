/**
 * 
 */
package irm.entity;

/**
 * 各省本网资源引入分析
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class ResourceInEntity {
	// 区域id
	private String areaid;
	// 运营商id
	private String ispid;
	// 网站数量
	private Integer site_in_cnt;
	// 域名数量
	private Integer domain_in_cnt;
	// url数量
	private Integer url_in_cnt;
	// 网站引入大小
	private Integer site_in_size;
	// 域名引入大小
	private Integer domain_in_size;
	// url引入大小
	private Integer url_in_size;
	// 域名本网引入率
	private float domain_net_in_rate;
	// 域名本网引入深度
	private float domain_net_in_size_rate;
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
	 * @return the site_in_cnt
	 */
	public Integer getSite_in_cnt() {
		return site_in_cnt;
	}
	/**
	 * @param site_in_cnt the site_in_cnt to set
	 */
	public void setSite_in_cnt(Integer site_in_cnt) {
		this.site_in_cnt = site_in_cnt;
	}
	/**
	 * @return the domain_in_cnt
	 */
	public Integer getDomain_in_cnt() {
		return domain_in_cnt;
	}
	/**
	 * @param domain_in_cnt the domain_in_cnt to set
	 */
	public void setDomain_in_cnt(Integer domain_in_cnt) {
		this.domain_in_cnt = domain_in_cnt;
	}
	/**
	 * @return the url_in_cnt
	 */
	public Integer getUrl_in_cnt() {
		return url_in_cnt;
	}
	/**
	 * @param url_in_cnt the url_in_cnt to set
	 */
	public void setUrl_in_cnt(Integer url_in_cnt) {
		this.url_in_cnt = url_in_cnt;
	}
	/**
	 * @return the site_in_size
	 */
	public Integer getSite_in_size() {
		return site_in_size;
	}
	/**
	 * @param site_in_size the site_in_size to set
	 */
	public void setSite_in_size(Integer site_in_size) {
		this.site_in_size = site_in_size;
	}
	/**
	 * @return the domain_in_size
	 */
	public Integer getDomain_in_size() {
		return domain_in_size;
	}
	/**
	 * @param domain_in_size the domain_in_size to set
	 */
	public void setDomain_in_size(Integer domain_in_size) {
		this.domain_in_size = domain_in_size;
	}
	/**
	 * @return the url_in_size
	 */
	public Integer getUrl_in_size() {
		return url_in_size;
	}
	/**
	 * @param url_in_size the url_in_size to set
	 */
	public void setUrl_in_size(Integer url_in_size) {
		this.url_in_size = url_in_size;
	}
	/**
	 * @return the domain_net_in_rate
	 */
	public float getDomain_net_in_rate() {
		return domain_net_in_rate;
	}
	/**
	 * @param domain_net_in_rate the domain_net_in_rate to set
	 */
	public void setDomain_net_in_rate(float domain_net_in_rate) {
		this.domain_net_in_rate = domain_net_in_rate;
	}
	/**
	 * @return the domain_net_in_size_rate
	 */
	public float getDomain_net_in_size_rate() {
		return domain_net_in_size_rate;
	}
	/**
	 * @param domain_net_in_size_rate the domain_net_in_size_rate to set
	 */
	public void setDomain_net_in_size_rate(float domain_net_in_size_rate) {
		this.domain_net_in_size_rate = domain_net_in_size_rate;
	}
}
