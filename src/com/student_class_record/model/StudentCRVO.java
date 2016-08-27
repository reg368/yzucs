package com.student_class_record.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class StudentCRVO implements Serializable {
	Integer cr_id;
	Integer cr_class_id;
	String cr_student_id;
	Timestamp cr_joindate;
	public Integer getCr_id() {
		return cr_id;
	}
	public void setCr_id(Integer cr_id) {
		this.cr_id = cr_id;
	}
	public Integer getCr_class_id() {
		return cr_class_id;
	}
	public void setCr_class_id(Integer cr_class_id) {
		this.cr_class_id = cr_class_id;
	}
	public String getCr_student_id() {
		return cr_student_id;
	}
	public void setCr_student_id(String cr_student_id) {
		this.cr_student_id = cr_student_id;
	}
	public Timestamp getCr_joindate() {
		return cr_joindate;
	}
	public void setCr_joindate(Timestamp cr_joindate) {
		this.cr_joindate = cr_joindate;
	}
	
	
}
