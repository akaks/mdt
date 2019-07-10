package com.kensure.mdt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * MDT申请表对象类
 */
public class MdtApply implements Serializable{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/**MDT申请表*/		
	private Long id; 

	/**患者类型 1住院 2门诊*/		
	private String patientType; 

	/**姓名*/		
	private String name; 

	/**性别*/		
	private String gender; 

	/**出生日期*/
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date birthday; 

	/**联系电话*/		
	private String phone; 

	/**入院/首诊时间*/
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date diagnoseDate; 

	/**住院/门诊号*/		
	private String number; 

	/**嘉和电子病历用户截图*/		
	private String picture; 

	/**病情概述（含主诉、病史、诊断、诊治过程等）*/		
	private String overview; 

	/**检验报告*/		
	private String surveyReport; 

	/**检查报告*/		
	private String inspectionReport; 

	/**MDT时间*/
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone="GMT+8")
	private Date mdtDate; 

	/**MDT地点*/		
	private String mdtLocation; 

	/**病种名称*/		
	private String diseaseName; 

	/**病种名称其它*/		
	private String otherDiseaseName; 

	/**MDT目的*/		
	private String mdtPurpose; 

	/**MDT目的其它*/		
	private String otherMdtPurpose; 

	/**诊治难点*/		
	private String difficulty; 

	/**是否收费 (1:是 0:否)*/		
	private String isCharge; 

	/**申请人*/		
	private String applyPerson; 

	/**申请递交时间*/
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone="GMT+8")
	private Date applyCreatetime; 

	/**申请人电话*/		
	private String applyPhone; 

	/**申请状态*/		
	private String applyStatus; 

	/**审核结果(科主任审核)*/		
	private String auditResult1; 

	/**审核意见(科主任审核)*/		
	private String auditOpinion1; 

	/**科主任名称(科主任审核)*/		
	private String auditPerson1; 

	/**审核时间(科主任审核)*/
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone="GMT+8")
	private Date auditTime1; 

	/**审核结果(医务部主任审核)*/		
	private String auditResult2; 

	/**审核意见(医务部主任审核)*/		
	private String auditOpinion2; 

	/**医务部主任名称(医务部主任审核)*/		
	private String auditPerson2; 

	/**审核时间(医务部主任审核)*/
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone="GMT+8")
	private Date auditTime2; 

	/**创建人*/		
	private Long createUserid;

	/**创建人科室*/		
	private String createDept;

	/***/
	private String share;

	/***/
	private String isDelete;

	/**创建时间*/
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone="GMT+8")
	private Date createTime; 

	/**更新时间*/
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone="GMT+8")
	private Date updateTime; 


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDiagnoseDate() {
		return diagnoseDate;
	}

	public void setDiagnoseDate(Date diagnoseDate) {
		this.diagnoseDate = diagnoseDate;
	}
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getSurveyReport() {
		return surveyReport;
	}

	public void setSurveyReport(String surveyReport) {
		this.surveyReport = surveyReport;
	}
	public String getInspectionReport() {
		return inspectionReport;
	}

	public void setInspectionReport(String inspectionReport) {
		this.inspectionReport = inspectionReport;
	}
	public Date getMdtDate() {
		return mdtDate;
	}

	public void setMdtDate(Date mdtDate) {
		this.mdtDate = mdtDate;
	}
	public String getMdtLocation() {
		return mdtLocation;
	}

	public void setMdtLocation(String mdtLocation) {
		this.mdtLocation = mdtLocation;
	}
	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String getOtherDiseaseName() {
		return otherDiseaseName;
	}

	public void setOtherDiseaseName(String otherDiseaseName) {
		this.otherDiseaseName = otherDiseaseName;
	}
	public String getMdtPurpose() {
		return mdtPurpose;
	}

	public void setMdtPurpose(String mdtPurpose) {
		this.mdtPurpose = mdtPurpose;
	}
	public String getOtherMdtPurpose() {
		return otherMdtPurpose;
	}

	public void setOtherMdtPurpose(String otherMdtPurpose) {
		this.otherMdtPurpose = otherMdtPurpose;
	}
	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getIsCharge() {
		return isCharge;
	}

	public void setIsCharge(String isCharge) {
		this.isCharge = isCharge;
	}
	public String getApplyPerson() {
		return applyPerson;
	}

	public void setApplyPerson(String applyPerson) {
		this.applyPerson = applyPerson;
	}
	public Date getApplyCreatetime() {
		return applyCreatetime;
	}

	public void setApplyCreatetime(Date applyCreatetime) {
		this.applyCreatetime = applyCreatetime;
	}
	public String getApplyPhone() {
		return applyPhone;
	}

	public void setApplyPhone(String applyPhone) {
		this.applyPhone = applyPhone;
	}
	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
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
	public String getAuditPerson1() {
		return auditPerson1;
	}

	public void setAuditPerson1(String auditPerson1) {
		this.auditPerson1 = auditPerson1;
	}
	public Date getAuditTime1() {
		return auditTime1;
	}

	public void setAuditTime1(Date auditTime1) {
		this.auditTime1 = auditTime1;
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
	public String getAuditPerson2() {
		return auditPerson2;
	}

	public void setAuditPerson2(String auditPerson2) {
		this.auditPerson2 = auditPerson2;
	}
	public Date getAuditTime2() {
		return auditTime2;
	}

	public void setAuditTime2(Date auditTime2) {
		this.auditTime2 = auditTime2;
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

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
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
