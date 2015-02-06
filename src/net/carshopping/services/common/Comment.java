/** * 文件名：Comment.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;

import java.io.Serializable;

import net.carshopping.core.dao.page.PagerModel;

/** 项目名称：carShopping 
 * 类名称：Comment 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:34:07 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:34:07 
 * 修改备注：
 * @version 1.0* */

public class Comment extends PagerModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String productID;
	private String account;
	private String nickname;
	private String orderID;
	private String orderdetailID;
	private String content;
	private String createdate;
	private int star;
	private String status;
	private String reply;

	public static final String comment_status_y = "y";// 显示
	public static final String comment_status_n = "n";// 不显示

	public void clear() {
		super.clear();
		id = null;
		productID = null;
		account = null;
		nickname = null;
		orderID = null;
		orderdetailID = null;
		content = null;
		createdate = null;
		star = 0;
		status = null;
		reply = null;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getOrderdetailID() {
		return orderdetailID;
	}

	public void setOrderdetailID(String orderdetailID) {
		this.orderdetailID = orderdetailID;
	}

}

