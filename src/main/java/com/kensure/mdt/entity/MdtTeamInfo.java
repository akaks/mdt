package com.kensure.mdt.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * MDT团队基本信息表对象类
 */
public class MdtTeamInfo implements Serializable{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/**MDT团队基本信息表（多人明细）*/		
	private Long id; 

	/**团队id*/		
	private Long teamId; 

	/**专家姓名*/		
	private String name; 

	/**科室*/		
	private String department; 

	/**职称*/		
	private String title; 

	/**联系电话*/		
	private String phone; 

	/**专家类型*/		
	private String specialistType; 

	/**创建时间*/		
	private Date createTime; 


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSpecialistType() {
		return specialistType;
	}

	public void setSpecialistType(String specialistType) {
		this.specialistType = specialistType;
	}
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
