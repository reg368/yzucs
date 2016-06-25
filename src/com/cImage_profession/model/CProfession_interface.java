package com.cImage_profession.model;

import java.util.List;

public interface CProfession_interface {
	public int insert(CProfessionVO cpvo);
	public List<CProfessionVO> getAll();
	public List<CProfessionVO> getAllChar();
	public List<CProfessionVO> getAllPet();
	
}
