package irm.entity;
/**
 * 网站库概览实体
 * @author	TangDican
 * @date	2013 Sep 1, 2013
 * @email	tangdican2008@163.com
 */
public class SiteViewEntity {

	// 区域id
	private String areaid;
	// 运营商id
	private String ispid;
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
}
