package com.kensure.mdt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * MDT团队课题表对象类
 */
public class MdtTeamIssue implements Serializable{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/**MDT团队课题表*/		
	private Long id; 

	/**团队id*/		
	private Long teamId; 

	/**项目名称*/		
	private String name; 

	/**项目研究时间*/
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date researchDate; 

	/**项目经费*/		
	private String projectFund; 

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
	public Date getResearchDate() {
		return researchDate;
	}

	public void setResearchDate(Date researchDate) {
		this.researchDate = researchDate;
	}
	public String getProjectFund() {
		return projectFund;
	}

	public void setProjectFund(String projectFund) {
		this.projectFund = projectFund;
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
