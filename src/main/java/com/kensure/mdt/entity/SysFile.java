package com.kensure.mdt.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件表对象类
 */
public class SysFile implements Serializable{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/**文件表*/		
	private Long id; 

	/**内容*/		
	private String content; 

	/**创建人id*/		
	private Long createUserid; 

	/**创建人科室*/		
	private String createDept; 

	/**创建时间*/		
	private Date createTime; 

	/**更新时间*/		
	private Date updateTime; 


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public Long getCreateUserid() {
		return createUserid;
	}

	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}
	public String getCreateDept() {
		return createDept;
	}

	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
