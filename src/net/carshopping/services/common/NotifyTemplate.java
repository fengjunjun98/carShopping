/** * 文件名：NotifyTemplate.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;
import java.io.Serializable;

import net.carshopping.core.dao.page.PagerModel;
/** 项目名称：carShopping 
 * 类名称：NotifyTemplate 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:45:08 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:45:08 
 * 修改备注：
 * @version 1.0* */

public class NotifyTemplate extends PagerModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String type;
	private String code;
	private String name;
	private String template;
	private String remark;
	
	/**
	 * 模板代号
	 */
	public static final String email_reg = "email_reg";
	public static final String email_forget_password = "email_forget_password";
	public static final String email_change_email = "email_change_email";
	public static final String email_notify_product = "email_notify_product";
	public static final String sms_pay_success = "sms_pay_success";

	public void clear() {
		super.clear();
		id = null;
		type = null;
		code = null;
		name = null;
		template = null;
		remark = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
