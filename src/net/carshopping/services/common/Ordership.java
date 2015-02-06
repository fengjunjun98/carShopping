/** * 文件名：Ordership.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;
import java.io.Serializable;

import net.carshopping.core.dao.page.PagerModel;
/** 项目名称：carShopping 
 * 类名称：Ordership 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:43:28 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:43:28 
 * 修改备注：
 * @version 1.0* */

public class Ordership extends PagerModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String orderid;
	private String shipname;
	private String shipaddress;
	private String province;
	private String provinceCode;
	private String city;
	private String cityCode;
	private String area;
	private String areaCode;
	private String phone;
	private String tel;
	private String zip;
	private String sex;
	private String remark;

	public Ordership() {
		super();
	}

	public Ordership(String orderid) {
		this.orderid = orderid;
	}

	public void clear() {
		super.clear();
		id = null;
		orderid = null;
		shipname = null;
		shipaddress = null;
		province = null;
		provinceCode = null;
		city = null;
		cityCode = null;
		area = null;
		areaCode = null;
		phone = null;
		tel = null;
		zip = null;
		sex = null;
		remark = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getShipname() {
		return shipname;
	}

	public void setShipname(String shipname) {
		this.shipname = shipname;
	}

	public String getShipaddress() {
		return shipaddress;
	}

	public void setShipaddress(String shipaddress) {
		this.shipaddress = shipaddress;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Override
	public String toString() {
		return "Ordership [id=" + id + ", orderid=" + orderid + ", shipname="
				+ shipname + ", shipaddress=" + shipaddress + ", province="
				+ province + ", provinceCode=" + provinceCode + ", city="
				+ city + ", cityCode=" + cityCode + ", area=" + area
				+ ", areaCode=" + areaCode + ", phone=" + phone + ", tel="
				+ tel + ", zip=" + zip + ", sex=" + sex + ", remark=" + remark
				+ "]";
	}

}