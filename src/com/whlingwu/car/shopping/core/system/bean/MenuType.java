/** * 文件名：MenuType.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.system.bean;

/** 项目名称：carShopping 
 * 类名称：MenuType 
 * 资源类型
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午10:35:25 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午10:35:25 
 * 修改备注：
 * @version 1.0* */

public enum MenuType {
	/**
	 * 模块
	 */
	module, 
	/**
	 * 页面
	 */
	page, 
	
	/**
	 * 按钮、功能
	 */
	button
}
