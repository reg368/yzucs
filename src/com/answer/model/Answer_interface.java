package com.answer.model;

import java.util.List;

public interface Answer_interface {
	public Integer insertGetPrimary(AnswerVO vo);
	public AnswerVO findByAid(Integer aid);
	public List<AnswerVO> findAnswersByQid(Integer qid);
	public List<AnswerVO> findCorrectAnswerVoByQid(int qid);
}
