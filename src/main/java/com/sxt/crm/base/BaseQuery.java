package com.sxt.crm.base;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

@SuppressWarnings("serial")
public class BaseQuery implements Serializable{
	private Integer rows; // 多少条
	private Integer page; // 当前页
	private String sort;
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	
	public PageBounds buidPageBouds(){
		if(null==page){
			page=1;
		}
		if(null==rows){
			rows=10;
		}
		if(StringUtils.isBlank(sort)){
			sort="id.desc";
		}
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sort));
		return pageBounds;
	}
}
