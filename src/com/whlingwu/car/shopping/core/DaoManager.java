/** * 文件名：DaoManager.java 
 * * 版本信息： 
 * 日期：2015-1-21 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core;

import java.util.List;

import com.whlingwu.car.shopping.core.dao.page.PagerModel;

/** 项目名称：carShopping 
 * 类名称：DaoManager 
 * 该接口提供业务逻辑最基本的服务，所有的业逻辑类都必须实现此接口，这样该业务逻辑类对应
 * 的action就免去了写基本selectList、insert、update、toEdit、deletes
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-21 下午07:11:35 
 * 修改人：凤军军 
 * 修改时间：2015-1-21 下午07:11:35 
 * 修改备注：
 * @version 1.0* */

public interface DaoManager<E extends PagerModel> {
	/**
	 * insert(新建) 
	 * @param e
	 * @return 
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public int insert(E e);
	/**
	 * delete(删除) 
	 * @param e
	 * @return 
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public int delete(E e);
	/**
	 * update(修改) 
	 * @param e
	 * @return 
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public int update(E e);
	/**
	 * selectOne(查询一条记录) 
	 * @param e
	 * @return 
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public E selectOne(E e);
	/**
	 * selectPageList(分页查询) 
	 * @param e
	 * @return 
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public PagerModel selectPageList(E e);
	/**
	 * selectList(根据条件查询所有) 
	 * @param e
	 * @return 
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<E> selectList(E e);
	/**
	 * deleteById(根据ID来删除一条记录) 
	 * @param id
	 * @return 
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public int deleteById(int id);
	/**
	 * selectById(根据ID查询一条记录) 
	 * @param id
	 * @return 
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public E selectById(String id);

}
