/** * 文件名：NavigationAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.navigation;

import net.carshopping.core.BaseAction;
import net.carshopping.services.manage.navigation.NavigationService;
import net.carshopping.services.manage.navigation.bean.Navigation;
/** 项目名称：carShopping 
 * 类名称：NavigationAction 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:02:26 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:02:26 
 * 修改备注：
 * @version 1.0* */

public class NavigationAction extends BaseAction<Navigation> {
	private static final long serialVersionUID = 1L;
	private NavigationService navigationService;

	public NavigationService getNavigationService() {
		return navigationService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("navigation!selectList.action");
	}

	public void setNavigationService(NavigationService navigationService) {
		this.navigationService = navigationService;
	}

	public Navigation getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Navigation();
		}
		super.initPageSelect();
	}

	public void insertAfter(Navigation e) {
		e.clear();
	}
	
	@Override
	public String insert() throws Exception {
		e.setTarget("_blank");
		e.setPosition("bottom");
		return super.insert();
	}
	
	@Override
	public String update() throws Exception {
		e.setTarget("_blank");
		e.setPosition("bottom");
		return super.update();
	}
}
