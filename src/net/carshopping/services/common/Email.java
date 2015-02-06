/** * 文件名：Email.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;

import java.io.Serializable;

import net.carshopping.core.dao.page.PagerModel;

/** 项目名称：carShopping 
 * 类名称：Email 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:33:00 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:33:00 
 * 修改备注：
 * @version 1.0* */

public class Email extends PagerModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String sign;
	private String account;
	private String type;
	private String url;
	private String createdate;
	private String starttime;
	private String endtime;
	private String newEmail;
	private String status;
	private String sendStatus;
	
//	public static final String email_type_forget = "forget";//忘记密码的邮件
//	public static final String email_type_product = "product";//商品邮件。比如：商品的促销，新上架等
//	public static final String email_type_reg = "reg";//注册新账号后,激活新注册的账号的邮件
//	public static final String email_type_changeEmail = "changeEmail";//修改邮箱的邮件
	
	/**
	 * 邮件内部链接状态
	 */
	public static final String email_status_y = "y";
	public static final String email_status_n = "n";
	
	/**
	 * 邮件发送状态
	 */
	public static final String email_sendStatus_y = "y";
	public static final String email_sendStatus_n = "n";
	
	private String pageMsg;//页面显示的消息

	public void clear() {
		super.clear();
		id = null;
		sign = null;
		account = null;
		type = null;
		url = null;
		createdate = null;
		starttime = null;
		endtime = null;
		newEmail = null;
		status = null;
		sendStatus = null;
		pageMsg = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPageMsg() {
		return pageMsg;
	}

	public void setPageMsg(String pageMsg) {
		this.pageMsg = pageMsg;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

}

