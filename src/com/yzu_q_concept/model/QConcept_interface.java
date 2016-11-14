package com.yzu_q_concept.model;

import java.util.Map;

public interface QConcept_interface {
	public Integer insertGetPermaryKey(QConceptVO vo);
	//取得產生圖表 conceptChart.jsp 所需要的資料
	//Map key : {q_id(題目id 圖表y軸) + c_id (概念id 圖表x軸) (字串)} , value 物件 取得分數比重
	public Map<String,QConceptVO> findByUserIdAndGroupId(String userId ,Integer g_id);
}
