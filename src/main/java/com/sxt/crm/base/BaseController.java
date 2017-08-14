package com.sxt.crm.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sxt.crm.base.ResultInfo;
import com.sxt.crm.constant.Constant;
import com.sxt.crm.exception.ParamException;

public class BaseController {  
	protected ResultInfo failure(Integer errorCode,String errorMessage){
		ResultInfo result=new ResultInfo(errorCode,errorMessage,errorMessage);
		return result;
	}
	protected ResultInfo failure(String errorMessage){
		ResultInfo result=failure(Constant.ERROR_CODE,errorMessage);
		return result;
	}
	protected ResultInfo failure(ParamException e){
		ResultInfo result=failure(e.getErrorCode(),e.getMessage());
		return result;
	}
	protected ResultInfo failure(Exception e){
		ResultInfo result=failure(Constant.ERROR_CODE,e.getMessage());
		return result;
	}
	protected ResultInfo success(Object result) {
		ResultInfo resultInfo = new ResultInfo(Constant.SUCCESS_CODE, Constant.SUCCESS_MESSAGE, result);
		return resultInfo;
	}
	//----------------------
	@ModelAttribute
	protected void preMethod(HttpServletRequest req,Model model){
		String ctx=req.getContextPath();
		model.addAttribute("ctx", ctx);
	}
}
