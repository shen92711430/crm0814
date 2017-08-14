package com.sxt.crm.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.crm.base.BaseController;
import com.sxt.crm.base.ResultInfo;
import com.sxt.crm.dao.SaleChanceDao;
import com.sxt.crm.dto.SaleChanceDto;
import com.sxt.crm.dto.SaleChanceQuery;
import com.sxt.crm.model.SaleChance;
import com.sxt.crm.service.SaleChanceService;
import com.sxt.crm.util.CookieUtil;

@Controller
@RequestMapping("sale_chance")
public class SaleChanceController extends BaseController {
	@Autowired
	private SaleChanceService saleChanceService;
	
	@RequestMapping("index")
	public String index(){
		
		return "sale_chance";
	}
	@RequestMapping("list")
	@ResponseBody
	public Map<String ,Object> list(SaleChanceQuery query){
		Map<String ,Object> result=saleChanceService.selectForPage(query);
		return result;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ResultInfo add(SaleChanceDto saleChanceDto,HttpServletRequest req){
		String userName=CookieUtil.getCookieValue(req, "userName");
		
		saleChanceService.add(saleChanceDto,userName);
		return success("添加成功");
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ResultInfo update(SaleChance saleChance){
		saleChanceService.update(saleChance);
		return success("修改成功");
 	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ResultInfo delete(String ids) {
		saleChanceService.delete(ids);
		return success("删除成功");
	}
	
}
