package com.cImage_mood.model;

import java.util.List;

public interface CMood_interface {
	public int insert(CMoodVO moodVO);
	public List<CMoodVO> getAllMood();
}
