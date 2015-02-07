/** * 文件名：HotqueryAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.hotquery;
import net.carshopping.core.BaseAction;
import net.carshopping.services.manage.hotquery.HotqueryService;
import net.carshopping.services.manage.hotquery.bean.Hotquery;

/** 项目名称：carShopping 
 * 类名称：HotqueryAction 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:59:20 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:59:20 
 * 修改备注：
 * @version 1.0* */

public class HotqueryAction extends BaseAction<Hotquery> {
	private static final long serialVersionUID = 1L;
	private HotqueryService hotqueryService;

	public HotqueryService getHotqueryService() {
		return hotqueryService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("hotquery!selectList.action");
	}

	public void setHotqueryService(HotqueryService hotqueryService) {
		this.hotqueryService = hotqueryService;
	}

	public Hotquery getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Hotquery();
		}
	}

	public void insertAfter(Hotquery e) {
		e.clear();
	}
}

