package com.cImage_mood.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CMoodVO implements Serializable {
	private Integer cMood_id;
	private String cMood_title;
	private Timestamp cMood_joindate;
	
	public Integer getcMood_id() {
		return cMood_id;
	}
	public void setcMood_id(Integer cMood_id) {
		this.cMood_id = cMood_id;
	}
	public String getcMood_title() {
		return cMood_title;
	}
	public void setcMood_title(String cMood_title) {
		this.cMood_title = cMood_title;
	}
	public Timestamp getcMood_joindate() {
		return cMood_joindate;
	}
	public void setcMood_joindate(Timestamp cMood_joindate) {
		this.cMood_joindate = cMood_joindate;
	}
	
	
	
	
}
