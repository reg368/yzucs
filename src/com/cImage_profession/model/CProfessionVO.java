package com.cImage_profession.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CProfessionVO implements Serializable {
	private Integer cprofession_id;
	private String  cprofession_title;
	private Timestamp cprofession_joindate;
	public Integer getCprofession_id() {
		return cprofession_id;
	}
	public void setCprofession_id(Integer cprofession_id) {
		this.cprofession_id = cprofession_id;
	}
	public String getCprofession_title() {
		return cprofession_title;
	}
	public void setCprofession_title(String cprofession_title) {
		this.cprofession_title = cprofession_title;
	}
	public Timestamp getCprofession_joindate() {
		return cprofession_joindate;
	}
	public void setCprofession_joindate(Timestamp cprofession_joindate) {
		this.cprofession_joindate = cprofession_joindate;
	}
	
	
}
