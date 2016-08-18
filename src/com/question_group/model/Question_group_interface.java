package com.question_group.model;

import java.util.List;


public interface Question_group_interface {
	public void insert(Question_groupVO vo);
	public Question_groupVO findByGid(Integer gid);
	public List<Question_groupVO> findQuestion_groupsByUserId(String userId);
}
