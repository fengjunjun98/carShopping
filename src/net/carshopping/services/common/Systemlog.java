/** * 文件名：Systemlog.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;

import java.io.Serializable;

import net.carshopping.core.dao.page.PagerModel;

/** 项目名称：carShopping 
 * 类名称：Systemlog 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:39:43 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:39:43 
 * 修改备注：
 * @version 1.0* */

public class Systemlog extends PagerModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String content;
	private String account;
	private int type;
	private String loginIP;
	private String logintime;
	private String loginArea;
	private String diffAreaLogin;
	
	/**
	 * 是否是异地登陆
	 */
	public static final String systemlog_diffAreaLogin_y = "y";//是
	public static final String systemlog_diffAreaLogin_n = "n";//否

	public void clear() {
		super.clear();
		id = null;
		title = null;
		content = null;
		account = null;
		type = 0;
		loginIP = null;
		logintime = null;
		loginArea = null;
		diffAreaLogin = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	public String getLoginArea() {
		return loginArea;
	}

	public void setLoginArea(String loginArea) {
		this.loginArea = loginArea;
	}

	public String getDiffAreaLogin() {
		return diffAreaLogin;
	}

	public void setDiffAreaLogin(String diffAreaLogin) {
		this.diffAreaLogin = diffAreaLogin;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
