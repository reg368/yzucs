package com.question_group.model;

import java.util.List;


public interface Question_group_interface {
	public Integer insertGetPrimaryKey(Question_groupVO vo);
	public Integer saveOrUpdateGetPrimaryKey(Question_groupVO vo);
	public Question_groupVO findByGid(Integer gid);
	public List<Question_groupVO> findQuestion_groupsByUserId(String userId);
	public List<Question_groupVO> findQuestion_groupsByStudentUserId(String userId);
}
