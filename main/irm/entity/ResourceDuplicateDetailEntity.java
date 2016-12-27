/**
 * 
 */
package irm.entity;

/**
 * 全网重复资源明细表实体
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class ResourceDuplicateDetailEntity {

		// 网站名称
		private String site;
		// 域名
		private String domain;
		// url地址
		private String url;
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
		 * @return the url
		 */
		public String getUrl() {
			return url;
		}
		/**
		 * @param url the url to set
		 */
		public void setUrl(String url) {
			this.url = url;
		}
}
