/** * 文件名：Attribute_linkAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.attribute_link;
import net.carshopping.core.BaseAction;
import net.carshopping.services.common.Attribute_link;
import net.carshopping.services.manage.attribute_link.Attribute_linkService;
/** 项目名称：carShopping 
 * 类名称：Attribute_linkAction 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:38:50 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:38:50 
 * 修改备注：
 * @version 1.0* */

public class Attribute_linkAction extends BaseAction<Attribute_link> {
	private static final long serialVersionUID = 1L;
	private Attribute_linkService attribute_linkService;

	public Attribute_linkService getAttribute_linkService() {
		return attribute_linkService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("attribute_link!selectList.action");
	}

	public void setAttribute_linkService(
			Attribute_linkService attribute_linkService) {
		this.attribute_linkService = attribute_linkService;
	}

	public Attribute_link getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Attribute_link();
		}
	}

	public void insertAfter(Attribute_link e) {
		e.clear();
	}
}
