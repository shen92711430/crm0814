package com.sxt.crm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sxt.crm.base.BaseController;
import com.sxt.crm.util.CookieUtil;

@Controller
public class IndexController extends BaseController{
	@RequestMapping("index")
	public String index(HttpServletRequest req,Model model){
		return "index";
	}
	
	@RequestMapping("main")
	public String main(HttpServletRequest req,Model model){
		String userName = CookieUtil.getCookieValue(req, "userName");
		model.addAttribute("userName", userName);
		String realName = CookieUtil.getCookieValue(req, "realName");
		model.addAttribute("realName", realName);
		return "main";
	}
	
}
