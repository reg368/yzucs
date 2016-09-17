package com.level_record.model;

import com.user.model.UserVO;


public interface Level_record_interface  {
	public int insertOrUpdateGerPrimaryKey(Level_recordVO vo);
	public Level_recordVO findByUserVOAndLevelId(UserVO vo , int level_id);
}
