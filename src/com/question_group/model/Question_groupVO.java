package com.question_group.model;

import java.io.Serializable;

public class Question_groupVO implements Serializable {
	private Integer g_id;
	private String g_name; //課程名稱
	private String g_joindate;
	private String g_user_id;
	private String g_number; // 課號
	private String g_semester; //學期
	
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
	public String getG_user_id() {
		return g_user_id;
	}
	public void setG_user_id(String g_user_id) {
		this.g_user_id = g_user_id;
	}
	public String getG_number() {
		return g_number;
	}
	public void setG_number(String g_number) {
		this.g_number = g_number;
	}
	public String getG_semester() {
		return g_semester;
	}
	public void setG_semester(String g_semester) {
		this.g_semester = g_semester;
	}
	
	
}
