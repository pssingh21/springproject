package com.bway.springproject.dao;

import com.bway.springproject.model.User;

public interface UserDao {
	void userSignUp(User u);
	boolean userLogin(String un, String psw);
}
