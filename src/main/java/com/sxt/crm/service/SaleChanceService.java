package com.sxt.crm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.sxt.crm.dao.SaleChanceDao;
import com.sxt.crm.dto.SaleChanceDto;
import com.sxt.crm.dto.SaleChanceQuery;
import com.sxt.crm.model.SaleChance;
import com.sxt.crm.util.AssertUtil;
@Service
public class SaleChanceService {

	@Autowired
	private SaleChanceDao saleChanceDao;
	
	private static Logger logger = LoggerFactory.getLogger(SaleChanceService.class);
	
	public  Map<String, Object> selectForPage(SaleChanceQuery query) {
		
		PageBounds pageBounds = new PageBounds();
		
		List<SaleChance> saleChances=saleChanceDao.selectForPage(query,pageBounds);
		PageList<SaleChance> result = (PageList<SaleChance>) saleChances;
		
		Paginator paginator=result.getPaginator();
		Map<String, Object> map = new HashMap<>();
		map.put("paginator", paginator);
		map.put("rows", result);
		map.put("total", paginator.getTotalCount());
		return map;
	}


	public void add(SaleChanceDto saleChanceDto, String loginUserName) {
		checkParams(saleChanceDto.getCustomerId(), saleChanceDto.getCustomerName(), saleChanceDto.getCgjl());
		
		String assignMan = saleChanceDto.getAssignMan();
		int state=0;
		Date assignTime=null;
		
		SaleChance saleChance=new SaleChance();
		BeanUtils.copyProperties(saleChanceDto, saleChance);
		saleChance.setAssignTime(assignTime);
		saleChance.setState(state);
		saleChance.setCreateMan(loginUserName);
	
		int count=saleChanceDao.insert(saleChance);
		logger.debug("插入的记录数为：{}, 主键为：", count, saleChance.getId()); 
		logger.info("插入的记录数为：{}, 主键为：", count, saleChance.getId()); 
	}
	


	public void update(SaleChance saleChance) {
		Integer id = saleChance.getId();
		AssertUtil.intIsNotEmpty(id, "请选择记录进行更新");
		checkParams(saleChance.getCustomerId(),saleChance.getCustomerName(), saleChance.getCgjl());
		
		checkState(saleChance);
		saleChance.setUpdateDate(new Date());
		
		saleChanceDao.update(saleChance);
	}


	public void delete(String ids) {
		// TODO Auto-generated method stub
		
	}
	
	private void checkParams(Integer customerId,String cutomerName,Integer cgjl){
		AssertUtil.intIsNotEmpty(customerId, "请选择客户");
		AssertUtil.isNotEmpty(cutomerName, "请选择客户");
		AssertUtil.intIsNotEmpty(cgjl, "请输入成交几率");
	}
	
	private void checkState(SaleChance saleChance) {
		SaleChance saleChanceFromDB = saleChanceDao.findById(saleChance.getId());
		AssertUtil.notNull(saleChanceFromDB, "该记录不存在，请重新选择");
		
		int state = saleChanceFromDB.getState();
		Date assignTime = null;
		String assignMan = saleChanceFromDB.getAssignMan();
		if (saleChanceFromDB.getState() == 0) { // 未分配
			if (StringUtils.isNoneBlank(saleChance.getAssignMan())) {
				state = 1; // 已分配
				assignTime = new Date();
			}
			
		} else { // 已分配
			if (!saleChanceFromDB.getAssignMan().equals(saleChance.getAssignMan())) { // 页面传入的指派人和数据库中的指派人不相等
				if (StringUtils.isNoneBlank(saleChance.getAssignMan())) { // 客户端没有传值
					state = 0; // 处于未分配的状态
					assignTime = null;
				} else { // 客户端传值
					assignMan = saleChance.getAssignMan();
					assignTime = new Date();
				}
			}
		}
		saleChance.setAssignMan(assignMan);
		saleChance.setAssignTime(assignTime);
		saleChance.setState(state);
	}
	public void detele(String ids){
		AssertUtil.isNotEmpty(ids, "请选择要删除的记录");
		saleChanceDao.delete(ids);
	}

}
