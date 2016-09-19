package com.answer_record.model;

import java.util.List;

public interface Answer_record_interface {
	public int insertOrUpdateGerPrimaryKey(Answer_recordVO vo);
	public List<Answer_recordVO> findByAr_lr_id(int ar_lr_id);
}
