/** * 文件名：EmailNotifyProduct.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;

import java.io.Serializable;

import net.carshopping.core.dao.page.PagerModel;

/** 项目名称：carShopping 
 * 类名称：EmailNotifyProduct 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:48:00 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:48:00 
 * 修改备注：
 * @version 1.0* */

public class EmailNotifyProduct extends PagerModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String account;
	private String receiveEmail;
	private String productID;
	private String productName;
	private String createdate;
	private String notifydate;
	private String status;
	private int sendFailureCount;

	/**
	 * 是否已发送通知
	 */
	public static final String emailNotifyProduct_status_y = "y";// 已发送
	public static final String emailNotifyProduct_status_n = "n";// 未发送

	public void clear() {
		super.clear();
		id = null;
		account = null;
		receiveEmail = null;
		productID = null;
		productName = null;
		createdate = null;
		notifydate = null;
		status = null;
		sendFailureCount = 0;
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

	public String getReceiveEmail() {
		return receiveEmail;
	}

	public void setReceiveEmail(String receiveEmail) {
		this.receiveEmail = receiveEmail;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getNotifydate() {
		return notifydate;
	}

	public void setNotifydate(String notifydate) {
		this.notifydate = notifydate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSendFailureCount() {
		return sendFailureCount;
	}

	public void setSendFailureCount(int sendFailureCount) {
		this.sendFailureCount = sendFailureCount;
	}
}
