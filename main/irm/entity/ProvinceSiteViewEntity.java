/**
 * 
 */
package irm.entity;

/**
 * 分区域网站概览实体
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class ProvinceSiteViewEntity {

	    // 区域id
		private String areaid;
		// 网站名称
		private String site;
		// 运营商id
		private String ispid;
		// 域名引入数量
		private Integer domain_in_cnt;
		// url数量
		private Integer url_in_cnt;
		// 域名本网引入率
		private float domain_net_in_rate;
		// 域名本网引入深度
		private float domain_net_in_depth;
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
		 * @return the domain_net_in_depth
		 */
		public float getDomain_net_in_depth() {
			return domain_net_in_depth;
		}
		/**
		 * @param domain_net_in_depth the domain_net_in_depth to set
		 */
		public void setDomain_net_in_depth(float domain_net_in_depth) {
			this.domain_net_in_depth = domain_net_in_depth;
		}
}
