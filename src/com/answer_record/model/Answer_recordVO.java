package com.answer_record.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Answer_recordVO implements Serializable {
	
	private Integer ar_id;
	private Integer ar_lr_id;
	private Integer ar_q_id;
	private String ar_user_id;
	private Integer ar_a_id1;
	private Integer ar_a_id2;
	private Integer ar_a_id3;
	private Integer ar_a_id4;
	private Integer ar_correct_a_id1;
	private Integer ar_correct_a_id2;
	private Integer ar_correct_a_id3;
	private Integer ar_correct_a_id4;
	private Timestamp ar_joindate;
	private Integer ar_user_login_count;
	private Integer ar_isCorrect;
	private Integer ar_isMulti;
	public Integer getAr_id() {
		return ar_id;
	}
	public void setAr_id(Integer ar_id) {
		this.ar_id = ar_id;
	}
	public Integer getAr_lr_id() {
		return ar_lr_id;
	}
	public void setAr_lr_id(Integer ar_lr_id) {
		this.ar_lr_id = ar_lr_id;
	}
	public Integer getAr_q_id() {
		return ar_q_id;
	}
	public void setAr_q_id(Integer ar_q_id) {
		this.ar_q_id = ar_q_id;
	}
	public String getAr_user_id() {
		return ar_user_id;
	}
	public void setAr_user_id(String ar_user_id) {
		this.ar_user_id = ar_user_id;
	}
	public Integer getAr_a_id1() {
		return ar_a_id1;
	}
	public void setAr_a_id1(Integer ar_a_id1) {
		this.ar_a_id1 = ar_a_id1;
	}
	public Integer getAr_a_id2() {
		return ar_a_id2;
	}
	public void setAr_a_id2(Integer ar_a_id2) {
		this.ar_a_id2 = ar_a_id2;
	}
	public Integer getAr_a_id3() {
		return ar_a_id3;
	}
	public void setAr_a_id3(Integer ar_a_id3) {
		this.ar_a_id3 = ar_a_id3;
	}
	public Integer getAr_a_id4() {
		return ar_a_id4;
	}
	public void setAr_a_id4(Integer ar_a_id4) {
		this.ar_a_id4 = ar_a_id4;
	}
	public Integer getAr_correct_a_id1() {
		return ar_correct_a_id1;
	}
	public void setAr_correct_a_id1(Integer ar_correct_a_id1) {
		this.ar_correct_a_id1 = ar_correct_a_id1;
	}
	public Integer getAr_correct_a_id2() {
		return ar_correct_a_id2;
	}
	public void setAr_correct_a_id2(Integer ar_correct_a_id2) {
		this.ar_correct_a_id2 = ar_correct_a_id2;
	}
	public Integer getAr_correct_a_id3() {
		return ar_correct_a_id3;
	}
	public void setAr_correct_a_id3(Integer ar_correct_a_id3) {
		this.ar_correct_a_id3 = ar_correct_a_id3;
	}
	public Integer getAr_correct_a_id4() {
		return ar_correct_a_id4;
	}
	public void setAr_correct_a_id4(Integer ar_correct_a_id4) {
		this.ar_correct_a_id4 = ar_correct_a_id4;
	}
	public Timestamp getAr_joindate() {
		return ar_joindate;
	}
	public void setAr_joindate(Timestamp ar_joindate) {
		this.ar_joindate = ar_joindate;
	}
	public Integer getAr_user_login_count() {
		return ar_user_login_count;
	}
	public void setAr_user_login_count(Integer ar_user_login_count) {
		this.ar_user_login_count = ar_user_login_count;
	}
	public Integer getAr_isCorrect() {
		return ar_isCorrect;
	}
	public void setAr_isCorrect(Integer ar_isCorrect) {
		this.ar_isCorrect = ar_isCorrect;
	}
	public Integer getAr_isMulti() {
		return ar_isMulti;
	}
	public void setAr_isMulti(Integer ar_isMulti) {
		this.ar_isMulti = ar_isMulti;
	}
	
	
	
}
