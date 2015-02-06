/** * 文件名：OrderlogAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.orderlog;
import net.carshopping.core.BaseAction;
import net.jeeshop.services.manage.orderlog.OrderlogService;
import net.jeeshop.services.manage.orderlog.bean.Orderlog;
/** 项目名称：carShopping 
 * 类名称：OrderlogAction 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:06:41 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:06:41 
 * 修改备注：
 * @version 1.0* */

public class OrderlogAction extends BaseAction<Orderlog> {
	private static final long serialVersionUID = 1L;
	private OrderlogService orderlogService;

	public OrderlogService getOrderlogService() {
		return orderlogService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("orderlog!selectList.action");
	}

	public void setOrderlogService(OrderlogService orderlogService) {
		this.orderlogService = orderlogService;
	}

	public Orderlog getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Orderlog();
		}
	}

	public void insertAfter(Orderlog e) {
		e.clear();
	}
}

