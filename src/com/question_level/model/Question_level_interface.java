package com.question_level.model;

import java.util.List;



public interface Question_level_interface {
	public Integer insertGetPrimaryKey(Question_levelVO vo);
	public List<Question_levelVO> findQustionLevelsByGid(Integer gid);
}
