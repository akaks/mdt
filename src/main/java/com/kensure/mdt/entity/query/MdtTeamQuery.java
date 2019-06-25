package com.kensure.mdt.entity.query;

import java.io.Serializable;

public class MdtTeamQuery implements Serializable{

	private static final long serialVersionUID = 3545276994084105527L;

	/**申请人*/		
	private String proposer; 

	/**MDT名称*/		
	private String nameLike;

	/**审核状态 (0:未审核 1:科主任审核 2:医务部主任审核 3:分管院长审核)*/		
	private Integer auditStatus;


	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
}
