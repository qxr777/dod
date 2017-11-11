package edu.whut.cs.oo.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Document implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 679617740760345448L;
	private long id;
	private String sn;   //档案编码，唯一编码
	private String name;  //档案名称
	private User user;
	private Timestamp timestamp;
	private String description;
	private String absolutePath;  //附件在后台的绝对路径
	private String sourcePath; //前台的绝对路径，不持久化
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbsolutePath() {
		return absolutePath;
	}
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	@Override
	public String toString() {
		return "Document [id=" + id + ", sn=" + sn + ", name=" + name + ", user=" + user + ", timestamp=" + timestamp
				+ ", description=" + description + ", absolutePath=" + absolutePath + "]";
	}
	public String getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	
}
