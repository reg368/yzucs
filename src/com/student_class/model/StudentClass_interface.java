package com.student_class.model;

import java.util.List;

public interface StudentClass_interface {
	public Integer insertGetPrimaryKey(StudentClassVO vo);
	public Integer saveOrUpdateGerPrimaryKey(StudentClassVO vo);
	public StudentClassVO findStudentClassByClassId(int c_id);
	public List<StudentClassVO> findStudentClassByTeacherId(String teacherId);
	public List<StudentClassVO> findStudentClassByQuestionGroupId(int g_id);
	public List<StudentClassVO> findStudentClassByTeacherIdAndNotInGroupId(String teacherId,int g_id);
}
