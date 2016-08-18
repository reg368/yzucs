package com.user.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserVO implements Serializable{
	
	private String user_id;
	private String user_name;
	private String user_gender;
	private String user_age;
	private String user_grade;
	private String user_character_image;
	private String user_pet_image;
	private String user_pet_name; 
	private String user_login_id;
	private String user_password;
	private Timestamp user_joindate;
	private String user_cImage_type;
	private String user_pImage_type;
	private Integer user_login_count;
	private Integer user_group_id;
	private String  user_teacher_id;
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	public String getUser_age() {
		return user_age;
	}
	public void setUser_age(String user_age) {
		this.user_age = user_age;
	}
	public String getUser_grade() {
		return user_grade;
	}
	public void setUser_grade(String user_grade) {
		this.user_grade = user_grade;
	}
	public String getUser_character_image() {
		return user_character_image;
	}
	public void setUser_character_image(String user_character_image) {
		this.user_character_image = user_character_image;
	}
	public String getUser_pet_image() {
		return user_pet_image;
	}
	public void setUser_pet_image(String user_pet_image) {
		this.user_pet_image = user_pet_image;
	}
	public String getUser_pet_name() {
		return user_pet_name;
	}
	public void setUser_pet_name(String user_pet_name) {
		this.user_pet_name = user_pet_name;
	}
	public String getUser_login_id() {
		return user_login_id;
	}
	public void setUser_login_id(String user_login_id) {
		this.user_login_id = user_login_id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_cImage_type() {
		return user_cImage_type;
	}
	public void setUser_cImage_type(String user_cImage_type) {
		this.user_cImage_type = user_cImage_type;
	}
	public String getUser_pImage_type() {
		return user_pImage_type;
	}
	public void setUser_pImage_type(String user_pImage_type) {
		this.user_pImage_type = user_pImage_type;
	}
	public Integer getUser_login_count() {
		return user_login_count;
	}
	public void setUser_login_count(Integer user_login_count) {
		this.user_login_count = user_login_count;
	}
	public Timestamp getUser_joindate() {
		return user_joindate;
	}
	public void setUser_joindate(Timestamp user_joindate) {
		this.user_joindate = user_joindate;
	}
	public Integer getUser_group_id() {
		return user_group_id;
	}
	public void setUser_group_id(Integer user_group_id) {
		this.user_group_id = user_group_id;
	}
	public String getUser_teacher_id() {
		return user_teacher_id;
	}
	public void setUser_teacher_id(String user_teacher_id) {
		this.user_teacher_id = user_teacher_id;
	}
	
	
}
