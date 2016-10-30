package com.question.model;

import java.util.List;

public interface Question_interface {
	public Integer insertGetPrimaryKey(QuestionVO vo);
	public void insertOrUpdate(QuestionVO vo);
	public QuestionVO findByQid(Integer qid);
	public List<QuestionVO> findByGroupId(Integer gid);
	public List<QuestionVO> findByLevelId(Integer lid);
	public List<QuestionVO> findByLevelIdAndGroupId(Integer lid,Integer groupId); //找出課程題目已被關卡勾選
	public List<QuestionVO> findNotInLevelIdAndGroupId(Integer lid,Integer groupId); //找出課程的題目但還沒被關卡選上的
}
