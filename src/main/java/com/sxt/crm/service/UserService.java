package com.sxt.crm.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxt.crm.dao.UserDao;
import com.sxt.crm.exception.ParamException;
import com.sxt.crm.model.User;
import com.sxt.crm.util.MD5Util;
import com.sxt.crm.util.UserIDBase64;
import com.sxt.crm.vo.UserLoginIdentity;
@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	public UserLoginIdentity login(String userName, String password) {
		
		if(StringUtils.isBlank(userName)){
			throw new ParamException("用户空");
		}
		if(StringUtils.isBlank(password)){
			throw new ParamException("密码空");
		}
		
		User user=userDao.queryByUserName(userName.trim());
		if(null==user){
			throw new ParamException("不存在");
		}
		
		String md5pwd=MD5Util.md5Method(password);
		if(!md5pwd.equals(user.getPassword())){
			throw new ParamException("密码错!");
		}
		UserLoginIdentity userLoginIdentity=new UserLoginIdentity();
		userLoginIdentity.setUserName(userName);
		userLoginIdentity.setRealName(user.getTrueName());
		userLoginIdentity.setUserIdString(UserIDBase64.encoderUserID(user.getId()));
		return userLoginIdentity;
	}

}
