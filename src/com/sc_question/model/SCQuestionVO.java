package com.sc_question.model;

import java.io.Serializable;

public class SCQuestionVO implements Serializable {
	private Integer qc_id;
	private Integer group_id;
	private Integer class_id;
	public Integer getQc_id() {
		return qc_id;
	}
	public void setQc_id(Integer qc_id) {
		this.qc_id = qc_id;
	}
	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}
	public Integer getClass_id() {
		return class_id;
	}
	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}
	
	
}
