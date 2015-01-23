/** * 文件名：Services.java 
 * * 版本信息： 
 * 日期：2015-1-21 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core;

import java.util.List;

import com.whlingwu.car.shopping.core.dao.page.PagerModel;

/** 项目名称：carShopping 
 * 类名称：Services 
 * 该接口提供业务逻辑最基本的服务，所有的业逻辑类都必须实现此接口，这样该业务逻辑类对应
 * 的action就免去了写基本selectList、insert、update、toEdit、deletes麻烦
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-21 下午07:26:43 
 * 修改人：凤军军 
 * 修改时间：2015-1-21 下午07:26:43 
 * 修改备注：
 * @version 1.0* */

public interface Services<E extends PagerModel> {
	/**
	 * 添加
	 * 
	 * @param e
	 * @return
	 */
	public int insert(E e);

	/**
	 * 删除
	 * 
	 * @param e
	 * @return
	 */
	public int delete(E e);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	public int deletes(String[] ids);

	/**
	 * 修改
	 * 
	 * @param e
	 * @return
	 */
	public int update(E e);

	/**
	 * 查询一条记录
	 * 
	 * @param e
	 * @return
	 */
	public E selectOne(E e);
	
	/**
	 * 根据ID查询一条记录
	 * 
	 * @param e
	 * @return
	 */
	public E selectById(String id);

	/**
	 * 分页查询
	 * 
	 * @param e
	 * @return
	 */
	public PagerModel selectPageList(E e);
	
	/**
	 * 根据条件查询所有
	 * @return
	 */
	public List<E> selectList(E e);
	
}
