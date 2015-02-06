/** * 文件名：ExpressAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.express;

/** 项目名称：carShopping 
 * 类名称：ExpressAction 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:43:47 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:43:47 
 * 修改备注：
 * @version 1.0* */

public class ExpressAction extends BaseAction<Express> {
	private static final long serialVersionUID = 1L;
	private ExpressService expressService;

	public ExpressService getExpressService() {
		return expressService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("express!selectList.action");
	}

	public void setExpressService(ExpressService expressService) {
		this.expressService = expressService;
	}

	public Express getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Express();
		}
	}

	public void insertAfter(Express e) {
		e.clear();
	}
}
