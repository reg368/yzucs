package com.yzu_concept.model;

import java.util.List;

public interface Concept_interface {
	public Integer insertGetPrimaryKey(ConceptVO vo);
	public List<ConceptVO> findConceptByUserId(String userId);
	public List<ConceptVO> findConceptByUserIdAndGroupId(String userId,Integer gid);
	public List<ConceptVO> findConceptNotInGroupByUserIdAndGroupId(String userId,Integer gid);
}
