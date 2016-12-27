/**
 * 
 */
package irm.entity;

/**
 * 网站引入概览实体
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class SiteInViewEntity {

	// 区域id
	private String areaid;
	// 网站名称
	private String site;
	// 域名数量
	private Integer domain_cnt;
	// 域名引入数量
	private Integer domain_in_cnt;
	// 本省域名引入数量
	private Integer province_domain_in_cnt;
	// 本省域名引入率
	private float province_domain_in_rate;
	// 移动域名引入数量
	private Integer yd_domain_in_cnt;
	// 移动域名引入率
	private float yd_domain_in_rate;
	// 电信域名引入数量
	private Integer dx_domain_in_cnt;
	// 电信域名引入率
	private float dx_domain_in_rate;
	// 联通域名引入数量
	private Integer lt_domain_in_cnt;
	// 联通域名引入率
	private float lt_domain_in_rate;
	// 其他域名引入数量
	private Integer other_domain_in_cnt;
	// 其他域名引入率
	private float other_domain_in_rate;
	// 运营商id
	private String ispid;
	// 域名大小
	private Integer domain_size;
	// 域名引入大小
	private Integer domain_in_size;
	// 本省域名引入大小
	private Integer province_domain_in_size;
	// 本省域名引入深度
	private float province_domain_in_depth;
	// 移动域名引入大小
	private Integer yd_domain_in_size;
	// 移动域名引入深度
	private float yd_domain_in_depth;
	// 电信域名引入大小
	private Integer dx_domain_in_size;
	// 电信域名引入深度
	private float dx_domain_in_depth;
	// 联通域名引入大小
	private Integer lt_domain_in_size;
	// 联通域名引入深度
	private float lt_domain_in_depth;
	// 其他域名引入大小
	private Integer other_domain_in_size;
	// 其他域名引入深度
	private float other_domain_in_depth;
	
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
	 * @return the province_domain_in_cnt
	 */
	public Integer getProvince_domain_in_cnt() {
		return province_domain_in_cnt;
	}
	/**
	 * @param province_domain_in_cnt the province_domain_in_cnt to set
	 */
	public void setProvince_domain_in_cnt(Integer province_domain_in_cnt) {
		this.province_domain_in_cnt = province_domain_in_cnt;
	}
	/**
	 * @return the province_domain_in_rate
	 */
	public float getProvince_domain_in_rate() {
		return province_domain_in_rate;
	}
	/**
	 * @param province_domain_in_rate the province_domain_in_rate to set
	 */
	public void setProvince_domain_in_rate(float province_domain_in_rate) {
		this.province_domain_in_rate = province_domain_in_rate;
	}
	/**
	 * @return the yd_domain_in_cnt
	 */
	public Integer getYd_domain_in_cnt() {
		return yd_domain_in_cnt;
	}
	/**
	 * @param yd_domain_in_cnt the yd_domain_in_cnt to set
	 */
	public void setYd_domain_in_cnt(Integer yd_domain_in_cnt) {
		this.yd_domain_in_cnt = yd_domain_in_cnt;
	}
	/**
	 * @return the yd_domain_in_rate
	 */
	public float getYd_domain_in_rate() {
		return yd_domain_in_rate;
	}
	/**
	 * @param yd_domain_in_rate the yd_domain_in_rate to set
	 */
	public void setYd_domain_in_rate(float yd_domain_in_rate) {
		this.yd_domain_in_rate = yd_domain_in_rate;
	}
	/**
	 * @return the dx_domain_in_cnt
	 */
	public Integer getDx_domain_in_cnt() {
		return dx_domain_in_cnt;
	}
	/**
	 * @param dx_domain_in_cnt the dx_domain_in_cnt to set
	 */
	public void setDx_domain_in_cnt(Integer dx_domain_in_cnt) {
		this.dx_domain_in_cnt = dx_domain_in_cnt;
	}
	/**
	 * @return the dx_domain_in_rate
	 */
	public float getDx_domain_in_rate() {
		return dx_domain_in_rate;
	}
	/**
	 * @param dx_domain_in_rate the dx_domain_in_rate to set
	 */
	public void setDx_domain_in_rate(float dx_domain_in_rate) {
		this.dx_domain_in_rate = dx_domain_in_rate;
	}
	/**
	 * @return the lt_domain_in_cnt
	 */
	public Integer getLt_domain_in_cnt() {
		return lt_domain_in_cnt;
	}
	/**
	 * @param lt_domain_in_cnt the lt_domain_in_cnt to set
	 */
	public void setLt_domain_in_cnt(Integer lt_domain_in_cnt) {
		this.lt_domain_in_cnt = lt_domain_in_cnt;
	}
	/**
	 * @return the lt_domain_in_rate
	 */
	public float getLt_domain_in_rate() {
		return lt_domain_in_rate;
	}
	/**
	 * @param lt_domain_in_rate the lt_domain_in_rate to set
	 */
	public void setLt_domain_in_rate(float lt_domain_in_rate) {
		this.lt_domain_in_rate = lt_domain_in_rate;
	}
	/**
	 * @return the other_domain_in_cnt
	 */
	public Integer getOther_domain_in_cnt() {
		return other_domain_in_cnt;
	}
	/**
	 * @param other_domain_in_cnt the other_domain_in_cnt to set
	 */
	public void setOther_domain_in_cnt(Integer other_domain_in_cnt) {
		this.other_domain_in_cnt = other_domain_in_cnt;
	}
	/**
	 * @return the other_domain_in_rate
	 */
	public float getOther_domain_in_rate() {
		return other_domain_in_rate;
	}
	/**
	 * @param other_domain_in_rate the other_domain_in_rate to set
	 */
	public void setOther_domain_in_rate(float other_domain_in_rate) {
		this.other_domain_in_rate = other_domain_in_rate;
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
	 * @return the domain_size
	 */
	public Integer getDomain_size() {
		return domain_size;
	}
	/**
	 * @param domain_size the domain_size to set
	 */
	public void setDomain_size(Integer domain_size) {
		this.domain_size = domain_size;
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
	 * @return the province_domain_in_size
	 */
	public Integer getProvince_domain_in_size() {
		return province_domain_in_size;
	}
	/**
	 * @param province_domain_in_size the province_domain_in_size to set
	 */
	public void setProvince_domain_in_size(Integer province_domain_in_size) {
		this.province_domain_in_size = province_domain_in_size;
	}
	/**
	 * @return the province_domain_in_depth
	 */
	public float getProvince_domain_in_depth() {
		return province_domain_in_depth;
	}
	/**
	 * @param province_domain_in_depth the province_domain_in_depth to set
	 */
	public void setProvince_domain_in_depth(float province_domain_in_depth) {
		this.province_domain_in_depth = province_domain_in_depth;
	}
	/**
	 * @return the yd_domain_in_size
	 */
	public Integer getYd_domain_in_size() {
		return yd_domain_in_size;
	}
	/**
	 * @param yd_domain_in_size the yd_domain_in_size to set
	 */
	public void setYd_domain_in_size(Integer yd_domain_in_size) {
		this.yd_domain_in_size = yd_domain_in_size;
	}
	/**
	 * @return the yd_domain_in_depth
	 */
	public float getYd_domain_in_depth() {
		return yd_domain_in_depth;
	}
	/**
	 * @param yd_domain_in_depth the yd_domain_in_depth to set
	 */
	public void setYd_domain_in_depth(float yd_domain_in_depth) {
		this.yd_domain_in_depth = yd_domain_in_depth;
	}
	/**
	 * @return the dx_domain_in_size
	 */
	public Integer getDx_domain_in_size() {
		return dx_domain_in_size;
	}
	/**
	 * @param dx_domain_in_size the dx_domain_in_size to set
	 */
	public void setDx_domain_in_size(Integer dx_domain_in_size) {
		this.dx_domain_in_size = dx_domain_in_size;
	}
	/**
	 * @return the dx_domain_in_depth
	 */
	public float getDx_domain_in_depth() {
		return dx_domain_in_depth;
	}
	/**
	 * @param dx_domain_in_depth the dx_domain_in_depth to set
	 */
	public void setDx_domain_in_depth(float dx_domain_in_depth) {
		this.dx_domain_in_depth = dx_domain_in_depth;
	}
	/**
	 * @return the lt_domain_in_size
	 */
	public Integer getLt_domain_in_size() {
		return lt_domain_in_size;
	}
	/**
	 * @param lt_domain_in_size the lt_domain_in_size to set
	 */
	public void setLt_domain_in_size(Integer lt_domain_in_size) {
		this.lt_domain_in_size = lt_domain_in_size;
	}
	/**
	 * @return the lt_domain_in_depth
	 */
	public float getLt_domain_in_depth() {
		return lt_domain_in_depth;
	}
	/**
	 * @param lt_domain_in_depth the lt_domain_in_depth to set
	 */
	public void setLt_domain_in_depth(float lt_domain_in_depth) {
		this.lt_domain_in_depth = lt_domain_in_depth;
	}
	/**
	 * @return the other_domain_in_size
	 */
	public Integer getOther_domain_in_size() {
		return other_domain_in_size;
	}
	/**
	 * @param other_domain_in_size the other_domain_in_size to set
	 */
	public void setOther_domain_in_size(Integer other_domain_in_size) {
		this.other_domain_in_size = other_domain_in_size;
	}
	/**
	 * @return the other_domain_in_depth
	 */
	public float getOther_domain_in_depth() {
		return other_domain_in_depth;
	}
	/**
	 * @param other_domain_in_depth the other_domain_in_depth to set
	 */
	public void setOther_domain_in_depth(float other_domain_in_depth) {
		this.other_domain_in_depth = other_domain_in_depth;
	}
}