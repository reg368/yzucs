package com.answer_submit.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Answer_submitVO implements Serializable {
	private Integer s_id;
	private Integer r_id;
	private String a_ids;
	private Timestamp s_joindate;
	
	
	public Integer getS_id() {
		return s_id;
	}
	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}
	public String getA_ids() {
		return a_ids;
	}
	public void setA_ids(String a_ids) {
		this.a_ids = a_ids;
	}
	public Integer getR_id() {
		return r_id;
	}
	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}
	public Timestamp getS_joindate() {
		return s_joindate;
	}
	public void setS_joindate(Timestamp s_joindate) {
		this.s_joindate = s_joindate;
	}
	
	
}
