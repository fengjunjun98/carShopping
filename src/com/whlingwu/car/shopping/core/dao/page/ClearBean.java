/** * 文件名：ClearBean.java 
 * * 版本信息： 
 * 日期：2015-1-21 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.dao.page;

/** 项目名称：carShopping 
 * 类名称：ClearBean 
 *  系统中所有实体类必须继承的接口，此为顶级接口，负责清理实体类中的字段数据
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-21 下午07:33:21 
 * 修改人：凤军军 
 * 修改时间：2015-1-21 下午07:33:21 
 * 修改备注：
 * @version 1.0* */

public interface ClearBean {

	/**
	 * clear(清空实体类的属性的值) 
	 *  
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	void clear();
}
