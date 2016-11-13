package com.yzu_q_concept.model;

import java.io.Serializable;

public class QConceptVO implements Serializable {
	private Integer qc_id;
	private Integer gc_id;
	private Integer percentage;
	private Integer q_id;
	public Integer getQc_id() {
		return qc_id;
	}
	public void setQc_id(Integer qc_id) {
		this.qc_id = qc_id;
	}
	public Integer getGc_id() {
		return gc_id;
	}
	public void setGc_id(Integer gc_id) {
		this.gc_id = gc_id;
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
	
	
}
