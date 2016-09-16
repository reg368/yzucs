package com.answer_record.model;

import java.util.List;

import com.user.model.UserVO;

public interface Answer_record_interface {
	public int insert(Answer_recordVO vo);
	public int insertOrUpdateGetPirmary(Answer_recordVO vo);
	public Answer_recordVO findByUserVOAndQuestionid(UserVO uservo , int questionId);
	public Answer_recordVO findByUserVOAndQuestionidAndLevelId(UserVO uservo , int questionId,int levelId);
	public List<Answer_recordVO> findByUserVOAndQuestionidResult(UserVO uservo ,int questionId);
	public List<Answer_recordVO> findAnswerVOByUserVO(UserVO vo);
	public List<Answer_recordVO> findAnswerVOSumResultByUserVO(UserVO vo);
}
