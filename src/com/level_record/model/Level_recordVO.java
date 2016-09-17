package com.level_record.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Level_recordVO implements Serializable {
	
	private Integer lr_id;
	private String lr_user_id;
	private Integer lr_level_id;
	private Integer lr_group_id;
	private Integer lr_user_login_count;
	private Integer lr_correct_count;
	private Integer lr_incorrect_count;
	private Timestamp lr_joindate;
	private Integer lr_qSize;
	
	public Integer getLr_id() {
		return lr_id;
	}
	public void setLr_id(Integer lr_id) {
		this.lr_id = lr_id;
	}
	public String getLr_user_id() {
		return lr_user_id;
	}
	public void setLr_user_id(String lr_user_id) {
		this.lr_user_id = lr_user_id;
	}
	public Integer getLr_level_id() {
		return lr_level_id;
	}
	public void setLr_level_id(Integer lr_level_id) {
		this.lr_level_id = lr_level_id;
	}
	public Integer getLr_group_id() {
		return lr_group_id;
	}
	public void setLr_group_id(Integer lr_group_id) {
		this.lr_group_id = lr_group_id;
	}
	public Integer getLr_user_login_count() {
		return lr_user_login_count;
	}
	public void setLr_user_login_count(Integer lr_user_login_count) {
		this.lr_user_login_count = lr_user_login_count;
	}
	public Integer getLr_correct_count() {
		return lr_correct_count;
	}
	public void setLr_correct_count(Integer lr_correct_count) {
		this.lr_correct_count = lr_correct_count;
	}
	public Integer getLr_incorrect_count() {
		return lr_incorrect_count;
	}
	public void setLr_incorrect_count(Integer lr_incorrect_count) {
		this.lr_incorrect_count = lr_incorrect_count;
	}
	public Timestamp getLr_joindate() {
		return lr_joindate;
	}
	public void setLr_joindate(Timestamp lr_joindate) {
		this.lr_joindate = lr_joindate;
	}
	public Integer getLr_qSize() {
		return lr_qSize;
	}
	public void setLr_qSize(Integer lr_qSize) {
		this.lr_qSize = lr_qSize;
	}
	
	
	
	
}
