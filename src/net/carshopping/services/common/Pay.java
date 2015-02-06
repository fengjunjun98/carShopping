/** * 文件名：Pay.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;
import java.io.Serializable;

import net.carshopping.core.dao.page.PagerModel;


/** 项目名称：carShopping 
 * 类名称：Pay 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:42:45 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:42:45 
 * 修改备注：
 * @version 1.0* */

public class Pay extends PagerModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String code;
	private String seller;
	private int order1;
	private String status;
	private String partner;
	private String key1;
	
	public static final String pay_code_alipayescow = "alipayescow";//支付宝

	public void clear() {
		super.clear();
		id = null;
		name = null;
		code = null;
		seller = null;
		order1 = 0;
		status = null;
		partner = null;
		key1 = null;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public int getOrder1() {
		return order1;
	}

	public void setOrder1(int order1) {
		this.order1 = order1;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}


}
