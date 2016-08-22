package com.answer_record.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Answer_recordVO implements Serializable {
	
	Integer r_id;
	String r_userId;
	Integer r_questionId;
	Integer r_user_login_count;
	Integer r_correct_count;
	Integer r_incorrect_count;
	Timestamp r_user_joindate;
	Timestamp r_user_update_date;
	Integer r_group_id;
	
	
	public Integer getR_id() {
		return r_id;
	}
	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}
	public String getR_userId() {
		return r_userId;
	}
	public void setR_userId(String r_userId) {
		this.r_userId = r_userId;
	}
	public Integer getR_questionId() {
		return r_questionId;
	}
	public void setR_questionId(Integer r_questionId) {
		this.r_questionId = r_questionId;
	}
	public Integer getR_user_login_count() {
		return r_user_login_count;
	}
	public void setR_user_login_count(Integer r_user_login_count) {
		this.r_user_login_count = r_user_login_count;
	}
	public Integer getR_correct_count() {
		return r_correct_count;
	}
	public void setR_correct_count(Integer r_correct_count) {
		this.r_correct_count = r_correct_count;
	}
	public Integer getR_incorrect_count() {
		return r_incorrect_count;
	}
	public void setR_incorrect_count(Integer r_incorrect_count) {
		this.r_incorrect_count = r_incorrect_count;
	}
	public Timestamp getR_user_joindate() {
		return r_user_joindate;
	}
	public void setR_user_joindate(Timestamp r_user_joindate) {
		this.r_user_joindate = r_user_joindate;
	}
	public Timestamp getR_user_update_date() {
		return r_user_update_date;
	}
	public void setR_user_update_date(Timestamp r_user_update_date) {
		this.r_user_update_date = r_user_update_date;
	}
	public Integer getR_group_id() {
		return r_group_id;
	}
	public void setR_group_id(Integer r_group_id) {
		this.r_group_id = r_group_id;
	}
	
	
}
