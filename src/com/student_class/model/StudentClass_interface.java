package com.student_class.model;

import java.util.List;

public interface StudentClass_interface {
	public Integer insertGetPrimaryKey(StudentClassVO vo);
	public List<StudentClassVO> findStudentClassByTeacherId(String teacherId);
	public List<StudentClassVO> findStudentClassByQuestionGroupId(int g_id);
}
