/**
 * 
 */
package irm.entity;

/**
 * 定义实例类
 * @author	TangDican
 * @email	tangdican2008@163.com
 * @date	2013 Aug 29
 */
@SuppressWarnings("serial")
public class InstanceEntity implements java.io.Serializable{
	
	// 任务id
	private Integer taskId;
	// 网站名称
	private String webName;
	// 数据库id
	private short dbId;
	// 网站名称
	private Integer type;
	// 实例id
	private Integer TaskinstanceId;
	/**
	 * @return the taskId
	 */
	public Integer getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return the webName
	 */
	public String getWebName() {
		return webName;
	}
	/**
	 * @param webName the webName to set
	 */
	public void setWebName(String webName) {
		this.webName = webName;
	}
	/**
	 * @return the dbId
	 */
	public short getDbId() {
		return dbId;
	}
	/**
	 * @param dbId the dbId to set
	 */
	public void setDbId(short dbId) {
		this.dbId = dbId;
	}
	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * @return the taskinstanceId
	 */
	public Integer getTaskinstanceId() {
		return TaskinstanceId;
	}
	/**
	 * @param taskinstanceId the taskinstanceId to set
	 */
	public void setTaskinstanceId(Integer taskinstanceId) {
		TaskinstanceId = taskinstanceId;
	}
}
