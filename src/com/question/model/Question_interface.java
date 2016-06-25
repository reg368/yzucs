package com.question.model;

import java.util.List;

public interface Question_interface {
	public Integer insertGetPrimaryKey(QuestionVO vo);
	public QuestionVO findByQid(Integer qid);
	public List<QuestionVO> findByGroupId(Integer gid);
}
