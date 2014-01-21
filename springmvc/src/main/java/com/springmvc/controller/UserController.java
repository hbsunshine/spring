package com.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.persistence.UserMapper;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserMapper userMapper;
	@RequestMapping("/test")
	public String test(Model model){
		model.addAttribute("hello", "freemaker");
		return "/index";
	}

}
