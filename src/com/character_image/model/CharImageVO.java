package com.character_image.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CharImageVO implements Serializable {
  private Integer cImage_id;
  private String cImage_path;
  private Integer cImage_mood;
  private String cImage_gender;
  private Integer cImage_profession;
  private Timestamp cImage_joindate;
  private Integer image_level;
  
public Integer getcImage_id() {
	return cImage_id;
}
public void setcImage_id(Integer cImage_id) {
	this.cImage_id = cImage_id;
}
public String getcImage_path() {
	return cImage_path;
}
public void setcImage_path(String cImage_path) {
	this.cImage_path = cImage_path;
}
public Integer getcImage_mood() {
	return cImage_mood;
}
public void setcImage_mood(Integer cImage_mood) {
	this.cImage_mood = cImage_mood;
}
public String getcImage_gender() {
	return cImage_gender;
}
public void setcImage_gender(String cImage_gender) {
	this.cImage_gender = cImage_gender;
}
public Integer getcImage_profession() {
	return cImage_profession;
}
public void setcImage_profession(Integer cImage_profession) {
	this.cImage_profession = cImage_profession;
}

public Integer getImage_level() {
	return image_level;
}
public void setImage_level(Integer image_level) {
	this.image_level = image_level;
}
public Timestamp getcImage_joindate() {
	return cImage_joindate;
}
public void setcImage_joindate(Timestamp cImage_joindate) {
	this.cImage_joindate = cImage_joindate;
}


  
}
