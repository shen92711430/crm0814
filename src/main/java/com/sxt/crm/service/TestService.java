package com.sxt.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxt.crm.dao.UserDao;
import com.sxt.crm.model.User;

@Service
public class TestService {
	@Autowired
	private UserDao userDao;

	public User findById(Integer id) {
		User user=userDao.findById(id);
		return user;
	}
}
