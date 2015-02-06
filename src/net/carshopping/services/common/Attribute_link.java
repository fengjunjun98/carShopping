/** * 文件名：Attribute_link.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;

import java.io.Serializable;

import net.carshopping.core.dao.QueryModel;

/** 项目名称：carShopping 
 * 类名称：Attribute_link  属性产品中间类
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:35:08 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:35:08 
 * 修改备注：
 * @version 1.0* */

public class Attribute_link extends QueryModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private int attrID;
	private int productID;
	private String value;//此值用于该属性为参数的情况

	public void clear() {
		super.clear();
		id = null;
		attrID = 0;
		productID = 0;
		value = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAttrID() {
		return attrID;
	}

	public void setAttrID(int attrID) {
		this.attrID = attrID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
