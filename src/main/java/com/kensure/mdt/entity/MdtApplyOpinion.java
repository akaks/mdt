package com.kensure.mdt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * MDT专家意见表对象类
 */
public class MdtApplyOpinion implements Serializable{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/**MDT专家意见表*/		
	private Long id; 

	/**MDT申请id*/		
	private Long applyId; 

	/**用户id*/		
	private Long userId; 

	/**内容*/		
	private String content; 

	/**创建时间*/		
	private Date createTime; 

	/**更新时间*/		
	private Date updateTime;

	/***/
	private String username;

	/***/
	private String department;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
