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

	/**科主任审核结果 0:未通过 1:通过*/		
	private String auditResult1; 

	/**科主任审核意见*/		
	private String auditOpinion1; 

	/**医务部主任审核结果 0:未通过 1:通过*/		
	private String auditResult2; 

	/**医务部主任审核意见*/		
	private String auditOpinion2; 

	/**分管院长审核结果 0:未通过 1:通过*/		
	private String auditResult3; 

	/**分管院长审核意见*/		
	private String auditOpinion3;

	/***/
	private Long createUserid;

	/***/
	private String createDept;

	/**是否删除 1是 0否*/
	private String isDelete;

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
	public String getAuditResult1() {
		return auditResult1;
	}

	public void setAuditResult1(String auditResult1) {
		this.auditResult1 = auditResult1;
	}
	public String getAuditOpinion1() {
		return auditOpinion1;
	}

	public void setAuditOpinion1(String auditOpinion1) {
		this.auditOpinion1 = auditOpinion1;
	}
	public String getAuditResult2() {
		return auditResult2;
	}

	public void setAuditResult2(String auditResult2) {
		this.auditResult2 = auditResult2;
	}
	public String getAuditOpinion2() {
		return auditOpinion2;
	}

	public void setAuditOpinion2(String auditOpinion2) {
		this.auditOpinion2 = auditOpinion2;
	}
	public String getAuditResult3() {
		return auditResult3;
	}

	public void setAuditResult3(String auditResult3) {
		this.auditResult3 = auditResult3;
	}
	public String getAuditOpinion3() {
		return auditOpinion3;
	}

	public void setAuditOpinion3(String auditOpinion3) {
		this.auditOpinion3 = auditOpinion3;
	}
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
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
