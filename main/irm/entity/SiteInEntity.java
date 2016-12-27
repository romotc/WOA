/**
 * 
 */
package irm.entity;

/**
 * 网站资源引入分析实体
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class SiteInEntity {

	// 网站名称
	private String site;
	// 域名id
	private String areaid;
	// 域名数量
	private Integer domain_cnt;
	// url数量
	private Integer url_cnt;
	// url大小
	private float url_size;
	// 可缓存资源数量
	private Integer ava_cache_resource_cnt;
	// 可缓存资源数量占比
	private float ava_cache_resource_cnt_rate;
	// 可缓存资源大小占比
	private float ava_cache_resource_size_rate;
	// 缓存命中率
	private float cache_hit_rate;
	// 运营商id
	private String ispid;
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
	 * @return the domain_cnt
	 */
	public Integer getDomain_cnt() {
		return domain_cnt;
	}
	/**
	 * @param domain_cnt the domain_cnt to set
	 */
	public void setDomain_cnt(Integer domain_cnt) {
		this.domain_cnt = domain_cnt;
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
	 * @return the ava_cache_resource_cnt
	 */
	public Integer getAva_cache_resource_cnt() {
		return ava_cache_resource_cnt;
	}
	/**
	 * @param ava_cache_resource_cnt the ava_cache_resource_cnt to set
	 */
	public void setAva_cache_resource_cnt(Integer ava_cache_resource_cnt) {
		this.ava_cache_resource_cnt = ava_cache_resource_cnt;
	}
	/**
	 * @return the ava_cache_resource_cnt_rate
	 */
	public float getAva_cache_resource_cnt_rate() {
		return ava_cache_resource_cnt_rate;
	}
	/**
	 * @param ava_cache_resource_cnt_rate the ava_cache_resource_cnt_rate to set
	 */
	public void setAva_cache_resource_cnt_rate(float ava_cache_resource_cnt_rate) {
		this.ava_cache_resource_cnt_rate = ava_cache_resource_cnt_rate;
	}
	/**
	 * @return the ava_cache_resource_size_rate
	 */
	public float getAva_cache_resource_size_rate() {
		return ava_cache_resource_size_rate;
	}
	/**
	 * @param ava_cache_resource_size_rate the ava_cache_resource_size_rate to set
	 */
	public void setAva_cache_resource_size_rate(float ava_cache_resource_size_rate) {
		this.ava_cache_resource_size_rate = ava_cache_resource_size_rate;
	}
	/**
	 * @return the cache_hit_rate
	 */
	public float getCache_hit_rate() {
		return cache_hit_rate;
	}
	/**
	 * @param cache_hit_rate the cache_hit_rate to set
	 */
	public void setCache_hit_rate(float cache_hit_rate) {
		this.cache_hit_rate = cache_hit_rate;
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
}
