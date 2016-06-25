package com.question_group.model;

import java.io.Serializable;

public class Question_groupVO implements Serializable {
	Integer g_id;
	String g_name;
	String g_joindate;
	
	public Integer getG_id() {
		return g_id;
	}
	public void setG_id(Integer g_id) {
		this.g_id = g_id;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getG_joindate() {
		return g_joindate;
	}
	public void setG_joindate(String g_joindate) {
		this.g_joindate = g_joindate;
	}
	
	
}
