package com.answer_submit.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Answer_submitVO implements Serializable {
	private Integer s_Id;
	private Integer r_id;
	private Integer a_id;
	private Timestamp s_joindate;
	public Integer getS_Id() {
		return s_Id;
	}
	public void setS_Id(Integer s_Id) {
		this.s_Id = s_Id;
	}
	public Integer getR_id() {
		return r_id;
	}
	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}
	public Integer getA_id() {
		return a_id;
	}
	public void setA_id(Integer a_id) {
		this.a_id = a_id;
	}
	public Timestamp getS_joindate() {
		return s_joindate;
	}
	public void setS_joindate(Timestamp s_joindate) {
		this.s_joindate = s_joindate;
	}
	
	
}
