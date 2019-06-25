package com.kensure.mdt.entity;

import java.io.Serializable;

/**
 * 角色表对象类
 * @author fankd created on 2019-6-10
 * @since
 */
public class SysRole implements Serializable{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/**角色表*/		
	private Long id; 

	/**角色名称*/		
	private String name; 


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
