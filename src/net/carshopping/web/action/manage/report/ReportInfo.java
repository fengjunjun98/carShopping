/** * 文件名：ReportInfo.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.report;
import java.io.Serializable;

import net.carshopping.core.dao.QueryModel;
import net.carshopping.core.dao.page.PagerModel;
/** 项目名称：carShopping 
 * 类名称：ReportInfo 报表数据对象
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:12:10 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:12:10 
 * 修改备注：
 * @version 1.0* */

public class ReportInfo extends QueryModel implements Serializable {
	private String productID;
	private String productName;//商品名称
	private int productSellCount;// 报表.商品销售总数
	private String createdate;// 订单创建日期
	private double sumAmount;// 订单支付汇总金额
	
	private String amountArr;
	private String orderdateArr;

	public void clear() {
		productID = null;
		productName = null;
		productSellCount = 0;
		createdate = null;
		sumAmount = 0;
		
		amountArr = null;
		orderdateArr = null;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public int getProductSellCount() {
		return productSellCount;
	}

	public void setProductSellCount(int productSellCount) {
		this.productSellCount = productSellCount;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public double getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(double sumAmount) {
		this.sumAmount = sumAmount;
	}

	public String getAmountArr() {
		return amountArr;
	}

	public void setAmountArr(String amountArr) {
		this.amountArr = amountArr;
	}

	public String getOrderdateArr() {
		return orderdateArr;
	}

	public void setOrderdateArr(String orderdateArr) {
		this.orderdateArr = orderdateArr;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "sumAmount="+sumAmount+",createdate="+createdate;
	}
}

