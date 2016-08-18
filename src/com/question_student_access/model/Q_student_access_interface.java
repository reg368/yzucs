package com.question_student_access.model;



public interface Q_student_access_interface {
	public Integer insertGetPrimaryKey(Q_student_accessVO vo);
	public Q_student_accessVO findByAccessId(Integer accessId);
}
