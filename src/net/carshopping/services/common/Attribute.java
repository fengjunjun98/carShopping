/** * 文件名：Attribute.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;

import java.io.Serializable;

import net.carshopping.core.dao.QueryModel;

/** 项目名称：carShopping 
 * 类名称：Attribute 
 * * 商品属性、参数类
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:35:44 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:35:44 
 * 修改备注：
 * @version 1.0* */

public class Attribute extends QueryModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private int catalogID;
	private int pid;
	private int order1;

	public void clear() {
		super.clear();
		id = null;
		name = null;
		catalogID = 0;
		pid = 0;
		order1 = 0;
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

	public int getCatalogID() {
		return catalogID;
	}

	public void setCatalogID(int catalogID) {
		this.catalogID = catalogID;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getOrder1() {
		return order1;
	}

	public void setOrder1(int order1) {
		this.order1 = order1;
	}

	@Override
	public String toString() {
		return "Attribute [id=" + id + ", name=" + name + ", catalogID="
				+ catalogID + ", pid=" + pid + ", order1=" + order1 + "]";
	}

}
