package com.sxt.crm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.crm.base.BaseController;
import com.sxt.crm.base.ResultInfo;
import com.sxt.crm.service.UserService;
import com.sxt.crm.vo.UserLoginIdentity;


@Controller
@RequestMapping("user")
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	
	@RequestMapping("login")
	@ResponseBody
	public ResultInfo login(String userName,String password){
		
		UserLoginIdentity userLoginIdentity=userService.login(userName, password);
		ResultInfo result=success(userLoginIdentity);
		return result;
	}
	
}
