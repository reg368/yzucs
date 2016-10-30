package com.QL.model;

import java.io.Serializable;

public class QLVO implements Serializable {
	private Integer ql_id;
	private Integer l_id;
	private Integer q_id;
	private Integer g_id;
	
	public Integer getQl_id() {
		return ql_id;
	}
	public void setQl_id(Integer ql_id) {
		this.ql_id = ql_id;
	}
	public Integer getL_id() {
		return l_id;
	}
	public void setL_id(Integer l_id) {
		this.l_id = l_id;
	}
	public Integer getQ_id() {
		return q_id;
	}
	public void setQ_id(Integer q_id) {
		this.q_id = q_id;
	}
	public Integer getG_id() {
		return g_id;
	}
	public void setG_id(Integer g_id) {
		this.g_id = g_id;
	}
	
}
