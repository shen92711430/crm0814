package com.sxt.crm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.crm.model.User;
import com.sxt.crm.service.TestService;

@RestController
@RequestMapping("hello")
public class TestController {
	@Autowired
	private TestService testService;
	
	@GetMapping("get/{id}")
	public Map<String,Object> get(@PathVariable Integer id){
		User user=testService.findById(id);
		Map<String,Object> result=new HashMap<String, Object>();
		result.put("test1", 1);
		result.put("test2", "欧尼酱");
		result.put("test3",49+90/3);
		result.put("result", user);
		return result;
	}
}
