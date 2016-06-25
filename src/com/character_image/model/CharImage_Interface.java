package com.character_image.model;

import java.util.List;

public interface CharImage_Interface {
	public int insert(CharImageVO cImagevo);
	public List<CharImageVO> findImageByGenderAndProfession(String gender,int profession);
}
