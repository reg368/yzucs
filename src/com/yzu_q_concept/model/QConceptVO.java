package com.yzu_q_concept.model;

import java.io.Serializable;

public class QConceptVO implements Serializable {
	private Integer qc_id;
	private Integer c_id;
	private Integer percentage;
	private Integer q_id;
	public Integer getQc_id() {
		return qc_id;
	}
	public void setQc_id(Integer qc_id) {
		this.qc_id = qc_id;
	}
	public Integer getPercentage() {
		return percentage;
	}
	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}
	public Integer getQ_id() {
		return q_id;
	}
	public void setQ_id(Integer q_id) {
		this.q_id = q_id;
	}
	public Integer getC_id() {
		return c_id;
	}
	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}
	
	
}
