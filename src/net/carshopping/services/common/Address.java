/** * 文件名：Address.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;

import java.io.Serializable;
import java.util.List;

import net.carshopping.core.dao.QueryModel;

/** 项目名称：carShopping 
 * 类名称：Address 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:37:17 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:37:17 
 * 修改备注：
 * @version 1.0* */

public class Address extends QueryModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String account;
	private String name;
	private String province;
	private String city;
	private String area;
	private String pcadetail;
	private String address;
	private String zip;
	private String phone;
	private String mobile;
	private String isdefault;
	
	private List<Area> areaList;//区域列表

	public void clear() {
		super.clear();
		id = null;
		account = null;
		name = null;
		province = null;
		city = null;
		area = null;
		pcadetail = null;
		address = null;
		zip = null;
		phone = null;
		mobile = null;
		isdefault = null;
		
//		if(areaList!=null){
//			for(int i=0;i<areaList.size();i++){
//				areaList.get(i).clear();
//			}
//			areaList.clear();
//			areaList = null;
//		}
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
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

	public List<Area> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}

	public String getPcadetail() {
		return pcadetail;
	}

	public void setPcadetail(String pcadetail) {
		this.pcadetail = pcadetail;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", account=" + account + ", name=" + name
				+ ", province=" + province + ", city=" + city + ", area="
				+ area + ", address=" + address + ", zip=" + zip + ", phone="
				+ phone + ", mobile=" + mobile + ", isdefault=" + isdefault
				+ "]";
	}

}

