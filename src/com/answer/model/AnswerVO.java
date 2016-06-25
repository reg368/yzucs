package com.answer.model;

import java.io.Serializable;

public class AnswerVO implements Serializable {
	Integer a_id;
	Integer a_qid;
	String a_text;
	Integer a_is_correct;
	String a_joindate;
	byte[] a_pic;
	
	public Integer getA_id() {
		return a_id;
	}
	public void setA_id(Integer a_id) {
		this.a_id = a_id;
	}
	public Integer getA_qid() {
		return a_qid;
	}
	public void setA_qid(Integer a_qid) {
		this.a_qid = a_qid;
	}
	public String getA_text() {
		return a_text;
	}
	public void setA_text(String a_text) {
		this.a_text = a_text;
	}
	public Integer getA_is_correct() {
		return a_is_correct;
	}
	public void setA_is_correct(Integer a_is_correct) {
		this.a_is_correct = a_is_correct;
	}
	public String getA_joindate() {
		return a_joindate;
	}
	public void setA_joindate(String a_joindate) {
		this.a_joindate = a_joindate;
	}
	public byte[] getA_pic() {
		return a_pic;
	}
	public void setA_pic(byte[] a_pic) {
		this.a_pic = a_pic;
	}
	
	
	
}
