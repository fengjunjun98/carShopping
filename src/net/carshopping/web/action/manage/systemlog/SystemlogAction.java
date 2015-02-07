/** * 文件名：SystemlogAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.systemlog;
import net.carshopping.core.BaseAction;
import net.carshopping.services.manage.systemlog.SystemlogService;
import net.carshopping.services.manage.systemlog.bean.Systemlog;

import org.apache.commons.lang.StringUtils;
/** 项目名称：carShopping 
 * 类名称：SystemlogAction  系统日志
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:17:37 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:17:37 
 * 修改备注：
 * @version 1.0* */

public class SystemlogAction extends BaseAction<Systemlog> {
	private static final long serialVersionUID = 1L;
	private SystemlogService systemlogService;

	public SystemlogService getSystemlogService() {
		return systemlogService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("systemlog!selectList.action");
	}

	public void setSystemlogService(SystemlogService systemlogService) {
		this.systemlogService = systemlogService;
	}

	public Systemlog getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Systemlog();
		}
		super.initPageSelect();
	}

	public void insertAfter(Systemlog e) {
		e.clear();
	}
	
	public String systemlogListAndDetail() throws Exception{
		String type = getRequest().getParameter("type");
		if(StringUtils.isNotBlank(type)){
			e.setType(Integer.valueOf(type));
		}
		selectList();
		return "systemlogListAndDetail";
	}
}
