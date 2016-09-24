package com.question_level.model;

import java.util.List;



public interface Question_level_interface {
	public Integer insertGetPrimaryKey(Question_levelVO vo);
	public Integer saveOrUpdateGerPrimaryKey(Question_levelVO vo);
	public Question_levelVO findByL_id(int l_id);
	public List<Question_levelVO> findQustionLevelsByGid(Integer gid);
	public List<Question_levelVO> findQustionLevelsByGidAndIsVisible(Integer gid ,boolean isVisible);
}
