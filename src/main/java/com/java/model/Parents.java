package com.java.model;

import java.util.List;

public class Parents {

	private List<Parent> parents;
	private String status;
	private Integer code;

	public List<Parent> getParent() {
		return parents;
	}

	public void setParent(List<Parent> parents) {
		this.parents = parents;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}
