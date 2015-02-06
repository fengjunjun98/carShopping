/** * 文件名：Order.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;
import java.io.Serializable;

import net.carshopping.core.dao.QueryModel;

/** 项目名称：carShopping 
 * 类名称：Order 订单对象
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:44:41 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:44:41 
 * 修改备注：
 * @version 1.0* */

public class Order extends QueryModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String account;
	private double rebate;//折扣
	private String createdate;//创建日期
	private String status;// 订单状态
	private String refundStatus;
	private String paystatus;// 订单支付状态
	private String lowStocks;//n:库存不足；y:库存充足。默认n
	private String remark;//订单支付时候显示的文字
	private String amount;// 订单总金额
	private int amountExchangeScore;//订单总兑换积分
	private String fee;// 总配送费
	private String ptotal;//商品总金额
	private int quantity;//商品总数量
//	private String picture;// 商品图片
	
	private String expressCode;//提交订单时候选中的配送方式
	private String expressName;//配送方式名称
	private String expressNo;//物流单号
	private String expressCompanyName;//物流公司名称
	private String confirmSendProductRemark;
	private String otherRequirement;//客户附加要求
	private String closedComment;//此订单的所有订单项对应的商品都进行了评论，则此值为y，表示此订单的评论功能已经关闭，默认为n，在订单状态为已签收后，订单可以进行评价。
	private int score;//订单能获得的积分
	
	/**
	 * 订单状态
	 */
	public static final String order_status_init = "init";//已成功下单
	public static final String order_status_pass = "pass";//已审核
	public static final String order_status_send = "send";//已发货
	public static final String order_status_sign = "sign";//已签收
	public static final String order_status_cancel = "cancel";//已取消
	public static final String order_status_file = "file";//已归档
	
	public static final String order_status_init_chinese = "已下单";
	public static final String order_status_pass_chinese = "已审核";
	public static final String order_status_send_chinese = "已发货";
	public static final String order_status_sign_chinese = "已签收";
	public static final String order_status_cancel_chinese = "已取消";
	public static final String order_status_file_chinese = "已归档";

	/**
	 * 订单支付状态
	 */
	public static final String order_paystatus_n = "n";// 未支付
	public static final String order_paystatus_p = "p";// 部分支付
	public static final String order_paystatus_y = "y";// 全部支付
	
	/**
	 * 订单是否缺货状态
	 */
	public static final String order_lowStocks_y = "y";//订单中存在商品缺货
	public static final String order_lowStocks_n = "n";//不缺货
	
	/**
	 * 订单评论状态是否关闭
	 */
	public static String order_closedComment_y = "y";//已关闭
	public static String order_closedComment_n = "n";//未关闭

	public void clear() {
		super.clear();
		id = null;
		account = null;
		rebate = 0d;
		remark = null;
		status = null;
		refundStatus = null;
		paystatus = null;
		lowStocks = null;
		amount = null;
		amountExchangeScore = 0;
		fee = null;
		createdate = null;
		ptotal = null;
		quantity = 0;
//		picture = null;
		expressCode = null;
		expressName = null;
		expressNo = null;//物流单号
		expressCompanyName = null;//物流公司名称
		confirmSendProductRemark = null;
		otherRequirement = null;
		closedComment = null;
		score = 0;
//		productID = 0;
//		productNumber = 0;
//		price = 0;
//		productName = null;
//		isComment = null;
//		orderdetailID = null;
//
//		if (orderdetail != null) {
//			orderdetail.clear();
//		}
//		orderdetail = null;
//
//		if (queryOrderIDs != null) {
//			queryOrderIDs.clear();
//		}
//		queryOrderIDs = null;
//		wIDsubject = null;
		
//		if(ordership!=null){
//			ordership.clear();
//			ordership = null;
//		}

//		if(kuaid100Info!=null){
//			kuaid100Info.clear();
//			kuaid100Info = null;
//		}
//		if(orderpay!=null){
//			orderpay.clear();
//			orderpay = null;
//		}
//		orderpayID = null;
//		tradeNo = null;
//		selectAddressID = null;
//		
//		if(rateOrderdetailList!=null){
//			rateOrderdetailList.clear();
//			rateOrderdetailList = null;
//		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
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

	public double getRebate() {
		return rebate;
	}

	public void setRebate(double rebate) {
		this.rebate = rebate;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getPtotal() {
		return ptotal;
	}

	public void setPtotal(String ptotal) {
		this.ptotal = ptotal;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getExpressCode() {
		return expressCode;
	}

	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getOtherRequirement() {
		return otherRequirement;
	}

	public void setOtherRequirement(String otherRequirement) {
		this.otherRequirement = otherRequirement;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getExpressCompanyName() {
		return expressCompanyName;
	}

	public void setExpressCompanyName(String expressCompanyName) {
		this.expressCompanyName = expressCompanyName;
	}

	public String getLowStocks() {
		return lowStocks;
	}

	public void setLowStocks(String lowStocks) {
		this.lowStocks = lowStocks;
	}

	public String getConfirmSendProductRemark() {
		return confirmSendProductRemark;
	}

	public void setConfirmSendProductRemark(String confirmSendProductRemark) {
		this.confirmSendProductRemark = confirmSendProductRemark;
	}

	public String getClosedComment() {
		return closedComment;
	}

	public void setClosedComment(String closedComment) {
		this.closedComment = closedComment;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getAmountExchangeScore() {
		return amountExchangeScore;
	}

	public void setAmountExchangeScore(int amountExchangeScore) {
		this.amountExchangeScore = amountExchangeScore;
	}

}
