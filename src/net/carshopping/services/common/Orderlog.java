/** * 文件名：Orderlog.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;

import java.io.Serializable;

import net.carshopping.core.dao.page.PagerModel;

/** 项目名称：carShopping 
 * 类名称：Orderlog 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:44:04 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:44:04 
 * 修改备注：
 * @version 1.0* */

public class Orderlog extends PagerModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String orderid;
	private String account;
	private String createdate;
	private String content;
	private String accountType;//w:会员;m:后台管理人员;
	
	public static final String orderlog_accountType_w = "w";//日志类型：前台
	public static final String orderlog_accountType_m = "m";//日志类型：后台
	public static final String orderlog_accountType_p = "p";//日志类型：支付系统回调

	public Orderlog() {
		super();
	}

	public Orderlog(String orderid) {
		super();
		this.orderid = orderid;
	}

	public void clear() {
		super.clear();
		id = null;
		orderid = null;
		account = null;
		createdate = null;
		content = null;
		accountType = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
}
