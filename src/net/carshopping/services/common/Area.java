/** * 文件名：Area.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;

import java.io.Serializable;

import net.carshopping.core.dao.QueryModel;

/** 项目名称：carShopping 
 * 类名称：Area 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:36:19 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:36:19 
 * 修改备注：
 * @version 1.0* */


public class Area extends QueryModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private String pcode;
	private String desc1;

	public Area() {
	}

	public Area(String code, String name, String pcode) {
		this.code = code;
		this.name = name;
		this.pcode = pcode;
	}

	public void clear() {
		super.clear();
		id = null;
		code = null;
		name = null;
		pcode = null;
		desc1 = null;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	@Override
	public String toString() {
		return "Area [id=" + id + ", code=" + code + ", name=" + name
				+ ", pcode=" + pcode + ", desc1=" + desc1 + "]";
	}

}
