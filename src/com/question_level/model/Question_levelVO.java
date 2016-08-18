package com.question_level.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Question_levelVO implements Serializable {
	Integer l_id;
	String l_level;
	Timestamp l_joindate;
	Integer l_group_id;
	public Integer getL_id() {
		return l_id;
	}
	public void setL_id(Integer l_id) {
		this.l_id = l_id;
	}
	public String getL_level() {
		return l_level;
	}
	public void setL_level(String l_level) {
		this.l_level = l_level;
	}
	public Timestamp getL_joindate() {
		return l_joindate;
	}
	public void setL_joindate(Timestamp l_joindate) {
		this.l_joindate = l_joindate;
	}
	public Integer getL_group_id() {
		return l_group_id;
	}
	public void setL_group_id(Integer l_group_id) {
		this.l_group_id = l_group_id;
	}
	
	
}
