/** * 文件名：EmailNotifyProductAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.emailNotifyProduct;
import java.io.IOException;

import org.slf4j.LoggerFactory;

import net.jeeshop.core.BaseAction;
import net.jeeshop.services.manage.emailNotifyProduct.EmailNotifyProductService;
import net.jeeshop.services.manage.emailNotifyProduct.bean.EmailNotifyProduct;
import net.jeeshop.services.manage.emailNotifyProduct.impl.EmailNotifyProductServiceImpl;
/** 项目名称：carShopping 
 * 类名称：EmailNotifyProductAction 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:43:05 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:43:05 
 * 修改备注：
 * @version 1.0* */

public class EmailNotifyProductAction extends BaseAction<EmailNotifyProduct> {
	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EmailNotifyProductAction.class);
	private EmailNotifyProductService emailNotifyProductService;

	public EmailNotifyProductService getEmailNotifyProductService() {
		return emailNotifyProductService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("emailNotifyProduct!selectList.action");
	}

	public void setEmailNotifyProductService(
			EmailNotifyProductService emailNotifyProductService) {
		this.emailNotifyProductService = emailNotifyProductService;
	}

	public EmailNotifyProduct getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new EmailNotifyProduct();
		}
	}

	public void insertAfter(EmailNotifyProduct e) {
		e.clear();
	}
	
	public String autoNotify() throws IOException{
		logger.error("autoNotify...");
		emailNotifyProductService.autoNotify();
		super.write("success");
		return null;
	}
}

