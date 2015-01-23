/** * 文件名：PagerModel.java 
 * * 版本信息： 
 * 日期：2015-1-21 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.dao.page;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.whlingwu.car.shopping.core.ManageContainer;

/** 项目名称：carShopping 
 * 类名称：PagerModel 
 * 分页模型，也是所有实体类的基类
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-21 下午07:34:22 
 * 修改人：凤军军 
 * 修改时间：2015-1-21 下午07:34:22 
 * 修改备注：
 * @version 1.0* */

public class PagerModel implements ClearBean {
	private int total; // 总数
	private List list; // 分页集合列表
	private int pageSize = ManageContainer.PAGE_SIZE;// 每页显示记录数
	private int offset; // 偏移量
	private int pagerSize;// 总页数
	protected String pagerUrl;//分页标签需要访问的ACTION地址
	private String id;

	public String getPagerUrl() {
		return pagerUrl;
	}

	public void setPagerUrl(String pagerUrl) {
		this.pagerUrl = pagerUrl;
	}

	public int getPagerSize() {
		return pagerSize;
	}

	public void setPagerSize(int pagerSize) {
		this.pagerSize = pagerSize;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getList() {
		return list == null ? new LinkedList() : list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.jeeshop.common.page.ClearBean#clear()
	 */
	public void clear() {
		total = 0; // 总数
		list = null; // 分页集合列表
		offset = 0; // 偏移量
		pagerSize = 0;// 总页数
//		pagerUrl = null;//分页标签需要访问的ACTION地址
		
		id = null;
	}
	
	public String trim(String str){
		if(str==null){
			return null;
		}
		return str.trim();
	}
	
	public void clearList(List<String> list){
		if(list==null || list.size()==0){
			return;
		}
		list.clear();
		list = null;
	}

	public void clearSet(Set<String> set){
		if(set==null || set.size()==0){
			return;
		}
		set.clear();
		set = null;
	}
	
	public void clearListBean(List<PagerModel> list){
		if(list==null || list.size()==0){
			return;
		}
		for(int i=0;i<list.size();i++){
			ClearBean item = list.get(i);
			item.clear();
			item = null;
		}
		list.clear();
		list = null;
	}
	
	public void clearArray(String[] arr){
		if(arr==null || arr.length==0){
			return;
		}
		for(int i=0;i<arr.length;i++){
			arr[i] = null;
		}
		arr = null;
	}
	
	
	
	@Override
	public String toString() {
		return "total:"+total+",list:"+list+",offset:"+offset;
	}
}

