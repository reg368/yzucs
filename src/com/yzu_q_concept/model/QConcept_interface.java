package com.yzu_q_concept.model;

import java.util.Map;

public interface QConcept_interface {
	public Integer insertGetPermaryKey(QConceptVO vo);
	//���o���͹Ϫ� conceptChart.jsp �һݭn�����
	//Map key : {q_id(�D��id �Ϫ�y�b) + c_id (����id �Ϫ�x�b) (�r��)} , value ���� ���o���Ƥ�
	public Map<String,QConceptVO> findByUserIdAndGroupId(String userId ,Integer g_id);
}
