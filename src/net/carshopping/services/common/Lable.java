/** * 文件名：Lable.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;

import java.io.Serializable;

import net.carshopping.core.dao.page.PagerModel;

/** 项目名称：carShopping 
 * 类名称：Lable 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:46:25 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:46:25 
 * 修改备注：
 * @version 1.0* */

public class Lable extends PagerModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String desc1;
	private int type;
	private int lableIDSum;
	private int typeSum;
	private int order1;

	@Override
	public void clear() {
		super.clear();

		id = null;
		name = null;
		desc1 = null;
		type = 0;
		lableIDSum = 0;
		typeSum = 0;
		order1 = 0;
	}

	public int getOrder1() {
		return order1;
	}

	public void setOrder1(int order1) {
		this.order1 = order1;
	}

	public int getLableIDSum() {
		return lableIDSum;
	}

	public void setLableIDSum(int lableIDSum) {
		this.lableIDSum = lableIDSum;
	}

	public int getTypeSum() {
		return typeSum;
	}

	public void setTypeSum(int typeSum) {
		this.typeSum = typeSum;
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

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
