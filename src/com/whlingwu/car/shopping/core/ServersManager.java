/** * 文件名：ServersManager.java 
 * * 版本信息： 
 * 日期：2015-1-21 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.whlingwu.car.shopping.core.dao.page.PagerModel;

/** 项目名称：carShopping 
 * 类名称：ServersManager 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-21 下午07:25:44 
 * 修改人：凤军军 
 * 修改时间：2015-1-21 下午07:25:44 
 * 修改备注：
 * @version 1.0* */

public class ServersManager<E extends PagerModel> implements Services<E>{

	private DaoManager<E> dao;

	public DaoManager<E> getDao() {
		return dao;
	}

	public void setDao(DaoManager<E> dao) {
		this.dao = dao;
	}

	/**
	 * 添加
	 * 
	 * @param e
	 * @return
	 */
	public int insert(E e) {
		if(e==null){
			throw new NullPointerException();
		}
		return dao.insert(e);
	}

	/**
	 * 批量添加
	 * 
	 * @param e
	 * @return
	 */
//	public void insertList(List<E> list) {
//		if(list==null)
//			throw new NullPointerException();
//		
//		for(int i=0;i<list.size();i++){
//			dao.insert(list.get(i));
//		}
//	}

	/**
	 * 删除
	 * 
	 * @param e
	 * @return
	 */
	public int delete(E e) {
		if(e==null){
			throw new NullPointerException();
		}
		return dao.delete(e);
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	public int deletes(String[] ids) {
		if (ids == null || ids.length == 0) {
			throw new NullPointerException("id不能全为空！");
		}
		
		for (int i = 0; i < ids.length; i++) {
			if(StringUtils.isBlank(ids[i])){
				throw new NullPointerException("id不能为空！");
			}
			dao.deleteById(Integer.parseInt(ids[i]));
		}
		return 0;
	}

	/**
	 * 修改
	 * 
	 * @param e
	 * @return
	 */
	public int update(E e) {
		if(e==null){
			throw new NullPointerException();
		}
		return dao.update(e);
	}

	/**
	 * 查询一条记录
	 * 
	 * @param e
	 * @return
	 */
	public E selectOne(E e) {
		return dao.selectOne(e);
	}

	/**
	 * 分页查询
	 * 
	 * @param e
	 * @return
	 */
	public PagerModel selectPageList(E e) {
		return dao.selectPageList(e);
	}
	
	public List<E> selectList(E e) {
		return dao.selectList(e);
	}

	@Override
	public E selectById(String id) {
		return dao.selectById(id);
	}
}
