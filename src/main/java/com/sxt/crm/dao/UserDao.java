package com.sxt.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sxt.crm.model.User;

public interface UserDao {
	@Select("select id,user_name as userName, password,true_name as trueName, email, "
			+ " phone, is_valid as isValid, create_date as createDate, update_date as updateDate from t_user "
			+ " where id = #{id}")
	User findById(Integer id);
	
	
	@Select("select user_name ,password,true_name from t_user where user_name=#{userName}")
	User queryByUserName(@Param(value="userName")String userName);


	
}
