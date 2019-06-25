package com.kensure.mdt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * MDT团队表对象类
 */
public class MdtTeam implements Serializable{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/**MDT团队表*/		
	private Long id; 

	/**申请人*/		
	private String proposer; 

	/**MDT名称*/		
	private String name; 

	/**申请日期*/
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date date; 

	/**MDT病种纳入标准和诊疗规范（指南）*/		
	private String standard; 

	/**审核状态 (0:未审核 1:科主任审核 2:医务部主任审核 3:分管院长审核)*/		
	private String auditStatus;

	/***/		
	private Date createTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
