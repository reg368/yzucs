package com.user.model;


public interface User_interface {
	public int insert(UserVO uservo);
	public String insertGetPrimaryKey(UserVO uservo);
	public int update(UserVO uservo);
	public UserVO findByUser_id(String user_id);
	public UserVO findByUser_login_id(String user_login_id);
	
}
