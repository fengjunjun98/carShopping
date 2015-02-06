/** * 文件名：Catalog.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;

import java.io.Serializable;

import net.carshopping.core.dao.QueryModel;

/** 项目名称：carShopping 
 * 类名称：Catalog 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:34:46 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:34:46 
 * 修改备注：
 * @version 1.0* */

public class Catalog extends QueryModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String pid;
	private String name;
	private String code;
	private int order1;
	private String type;
	private String showInNav;
	
	
	private String showInNavStr;//是否在导航栏显示的中文化
	
	/**
	 * 是否显示在首页的导航条上
	 */
	public static final String catalog_showInNav_y = "y";
	public static final String catalog_showInNav_n = "n";
	
	/**
	 * type类型
	 */
	public static final String catalog_type_p = "p";//商品
	public static final String catalog_type_a = "a";//文章

	public Catalog() {
		super();
	}

	public Catalog(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public void clear() {
		super.clear();
		id = null;
		name = null;
		code = null;
		pid = null;
		order1 = 0;
		type = null;
		showInNav = null;
		showInNavStr = null;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrder1() {
		return order1;
	}

	public void setOrder1(int order1) {
		this.order1 = order1;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShowInNav() {
		return showInNav;
	}

	public void setShowInNav(String showInNav) {
		this.showInNav = showInNav;
	}

	public String getShowInNavStr() {
		return showInNavStr;
	}

	public void setShowInNavStr(String showInNavStr) {
		this.showInNavStr = showInNavStr;
	}

}
