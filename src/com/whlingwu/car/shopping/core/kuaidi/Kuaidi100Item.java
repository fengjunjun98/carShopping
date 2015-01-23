/** * 文件名：Kuaidi100Item.java 
 * * 版本信息： 
 * 日期：2015-1-22 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.kuaidi;

/** 项目名称：carShopping 
 * 类名称：Kuaidi100Item 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-22 上午09:14:52 
 * 修改人：凤军军 
 * 修改时间：2015-1-22 上午09:14:52 
 * 修改备注：
 * @version 1.0* */

public class Kuaidi100Item {
	private String time;// 每条数据的时间
	private String context;// 每条数据的状态

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public String toString() {
		return "Kuaidi100Item [time=" + time + ", context=" + context + "]";
	}

}

