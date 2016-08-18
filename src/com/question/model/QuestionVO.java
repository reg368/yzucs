package com.question.model;

import java.io.Serializable;

public class QuestionVO implements Serializable {
	Integer q_id;
	Integer q_groupid;
	String q_text;
	String q_joindate;
	String q_point;
	String q_tip;
	byte[] q_pic;
	Integer q_level_id;
	
	public Integer getQ_id() {
		return q_id;
	}
	public void setQ_id(Integer q_id) {
		this.q_id = q_id;
	}
	public Integer getQ_groupid() {
		return q_groupid;
	}
	public void setQ_groupid(Integer q_groupid) {
		this.q_groupid = q_groupid;
	}
	public String getQ_text() {
		return q_text;
	}
	public void setQ_text(String q_text) {
		this.q_text = q_text;
	}
	public String getQ_joindate() {
		return q_joindate;
	}
	public void setQ_joindate(String q_joindate) {
		this.q_joindate = q_joindate;
	}
	public String getQ_point() {
		return q_point;
	}
	public void setQ_point(String q_point) {
		this.q_point = q_point;
	}
	public String getQ_tip() {
		return q_tip;
	}
	public void setQ_tip(String q_tip) {
		this.q_tip = q_tip;
	}
	public byte[] getQ_pic() {
		return q_pic;
	}
	public void setQ_pic(byte[] q_pic) {
		this.q_pic = q_pic;
	}
	public Integer getQ_level_id() {
		return q_level_id;
	}
	public void setQ_level_id(Integer q_level_id) {
		this.q_level_id = q_level_id;
	}
	
}
