package com.kensure.mdt.entity.resp;

import java.io.Serializable;

/**
 */
public class ToSthResp implements Serializable{


	private Long id;

	private String type;

	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
