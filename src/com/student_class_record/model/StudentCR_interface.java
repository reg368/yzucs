package com.student_class_record.model;

public interface StudentCR_interface {
	public void insert(StudentCRVO vo);
	public StudentCRVO findByStudentIdAndClassId(String sid,int c_id);
}
