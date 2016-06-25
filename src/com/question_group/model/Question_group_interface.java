package com.question_group.model;


public interface Question_group_interface {
	public void insert(Question_groupVO vo);
	public Question_groupVO findByGid(Integer gid);
}
