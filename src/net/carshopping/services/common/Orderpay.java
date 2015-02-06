/** * 文件名：Orderpay.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;

import java.io.Serializable;

import net.carshopping.core.dao.page.PagerModel;

/** 项目名称：carShopping 
 * 类名称：Orderpay 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:43:48 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:43:48 
 * 修改备注：
 * @version 1.0* */

public class Orderpay extends PagerModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String orderid;
	private String paystatus;
	private double payamount;
	private String createtime;
	private String paymethod;
	private String confirmdate;
	private String confirmuser;
	private String remark;
	private String tradeNo;
	
	public static final String orderpay_paystatus_y = "y";//支付成功
	public static final String orderpay_paystatus_n = "n";//未支付成功
	
	public static final String orderpay_paymethod_alipayescow = "alipayescow";//支付宝纯担保交易接口

	public void clear() {
		super.clear();
		id = null;
		orderid = null;
		paystatus = null;
		payamount = 0;
		createtime = null;
		paymethod = null;
		confirmdate = null;
		confirmuser = null;
		remark = null;
		tradeNo = null;
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

	public String getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
	}

	public double getPayamount() {
		return payamount;
	}

	public void setPayamount(double payamount) {
		this.payamount = payamount;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public String getConfirmdate() {
		return confirmdate;
	}

	public void setConfirmdate(String confirmdate) {
		this.confirmdate = confirmdate;
	}

	public String getConfirmuser() {
		return confirmuser;
	}

	public void setConfirmuser(String confirmuser) {
		this.confirmuser = confirmuser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

}
