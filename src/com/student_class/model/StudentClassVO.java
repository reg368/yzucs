package com.student_class.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class StudentClassVO implements Serializable {
	
	Integer c_id;
	String c_name;
	String c_teacher_id;
	Timestamp c_joindate;
	public Integer getC_id() {
		return c_id;
	}
	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_teacher_id() {
		return c_teacher_id;
	}
	public void setC_teacher_id(String c_teacher_id) {
		this.c_teacher_id = c_teacher_id;
	}
	public Timestamp getC_joindate() {
		return c_joindate;
	}
	public void setC_joindate(Timestamp c_joindate) {
		this.c_joindate = c_joindate;
	}
	
	

}
