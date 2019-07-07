package com.kensure.mdt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * MDT随访反馈表对象类
 */
public class MdtApplyFeedback implements Serializable{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/**MDT随访反馈表*/		
	private Long id; 

	/**MDT申请id*/		
	private Long applyId; 

	/**MDT前诊断*/		
	private String mdtBedoreDiagnosis; 

	/**MDT前诊断*/		
	private String mdtAfterDiagnosis; 

	/**转归*/		
	private String outcome; 

	/**MDT前后治疗变化*/		
	private String treatmentChange; 

	/**对MDT中心的工作有何建议和意见*/		
	private String opinion; 

	/**随访姓名*/		
	private String visitName; 

	/**随访人电话*/		
	private String visitPhone; 

	/**随访日期*/
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", timezone="GMT+8")
	private Date visitTime; 

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
	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}
	public String getMdtBedoreDiagnosis() {
		return mdtBedoreDiagnosis;
	}

	public void setMdtBedoreDiagnosis(String mdtBedoreDiagnosis) {
		this.mdtBedoreDiagnosis = mdtBedoreDiagnosis;
	}
	public String getMdtAfterDiagnosis() {
		return mdtAfterDiagnosis;
	}

	public void setMdtAfterDiagnosis(String mdtAfterDiagnosis) {
		this.mdtAfterDiagnosis = mdtAfterDiagnosis;
	}
	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	public String getTreatmentChange() {
		return treatmentChange;
	}

	public void setTreatmentChange(String treatmentChange) {
		this.treatmentChange = treatmentChange;
	}
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getVisitName() {
		return visitName;
	}

	public void setVisitName(String visitName) {
		this.visitName = visitName;
	}
	public String getVisitPhone() {
		return visitPhone;
	}

	public void setVisitPhone(String visitPhone) {
		this.visitPhone = visitPhone;
	}
	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
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
