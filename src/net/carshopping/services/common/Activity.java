/** * 文件名：Activity.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;
import java.io.Serializable;

import net.carshopping.core.dao.page.PagerModel;
/** 项目名称：carShopping 
 * 类名称：Activity 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:37:38 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:37:38 
 * 修改备注：
 * @version 1.0* */


/**
 * ALTER TABLE `carshopping`.`t_activity` ADD COLUMN `activityType` CHAR(1) NULL  AFTER `productID` ;
 * 
 * ALTER TABLE `carshopping`.`t_activity` ADD COLUMN `score` INT NULL  AFTER `activityType` , ADD COLUMN `minGroupCount` INT NULL  AFTER `score` ;
 * @author Administrator
 *
 */
public class Activity extends PagerModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String accountRange;
	private String startDate;
	private String endDate;
	private String content;
	private String status;
	private String catalogs;
	private String activityType;
	private String discountType;
	private String discount;
	private String minprice;
	private String maxprice;
	private int maxSellCount;
	private String productID;// 参与活动的商品ID
	private int exchangeScore;
	private int minGroupCount;
	private String tuanPrice;
	private int hasBuyGroupPerson;
	
	/**
	 * 享受的优惠方式
	 */
	public static final String activity_discountType_r = "r";//减免
	public static final String activity_discountType_d = "d";//折扣
	public static final String activity_discountType_s = "s";//双倍积分
	
	/**
	 * 活动类型
	 */
	public static final String activity_activityType_c = "c";//促销活动
	public static final String activity_activityType_j = "j";//积分兑换
	public static final String activity_activityType_t = "t";//团购活动
	
	public static final String activity_status_y = "y";//团购活动
	public static final String activity_status_n = "n";//团购活动
	
	public void clear() {
		super.clear();
		id = null;
		name = null;
		accountRange = null;
		startDate = null;
		endDate = null;
		content = null;
		status = null;
		catalogs = null;
		activityType = null;
		discountType = null;
		discount = null;
		minprice = null;
		maxprice = null;
		maxSellCount = 0;
		productID = null;
		exchangeScore = 0;
		minGroupCount = 0;
		tuanPrice = null;
		hasBuyGroupPerson = 0;
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

	public String getAccountRange() {
		return accountRange;
	}

	public void setAccountRange(String accountRange) {
		this.accountRange = accountRange;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCatalogs() {
		return catalogs;
	}

	public void setCatalogs(String catalogs) {
		this.catalogs = catalogs;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getMinprice() {
		return minprice;
	}

	public void setMinprice(String minprice) {
		this.minprice = minprice;
	}

	public String getMaxprice() {
		return maxprice;
	}

	public void setMaxprice(String maxprice) {
		this.maxprice = maxprice;
	}

	public int getMaxSellCount() {
		return maxSellCount;
	}

	public void setMaxSellCount(int maxSellCount) {
		this.maxSellCount = maxSellCount;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public int getExchangeScore() {
		return exchangeScore;
	}

	public void setExchangeScore(int exchangeScore) {
		this.exchangeScore = exchangeScore;
	}

	public int getMinGroupCount() {
		return minGroupCount;
	}

	public void setMinGroupCount(int minGroupCount) {
		this.minGroupCount = minGroupCount;
	}

	public String getTuanPrice() {
		return tuanPrice;
	}

	public void setTuanPrice(String tuanPrice) {
		this.tuanPrice = tuanPrice;
	}

	public int getHasBuyGroupPerson() {
		return hasBuyGroupPerson;
	}

	public void setHasBuyGroupPerson(int hasBuyGroupPerson) {
		this.hasBuyGroupPerson = hasBuyGroupPerson;
	}

}
