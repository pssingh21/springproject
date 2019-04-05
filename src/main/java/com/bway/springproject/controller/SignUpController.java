package com.bway.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bway.springproject.dao.UserDao;
import com.bway.springproject.dao.UserDaoImpl;
import com.bway.springproject.model.User;

@Controller
public class SignUpController {
	@Autowired
	private UserDao udao;
	
	@RequestMapping(value="/signUp", method=RequestMethod.GET)
	public String getSignUpForm(){
		return "signUp";
	}
	
	@RequestMapping(value="/signUp", method=RequestMethod.POST)
	public String signUp(@ModelAttribute User u, Model model){
		
		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		
		udao.userSignUp(u);
		
		return "login";
	}
}
