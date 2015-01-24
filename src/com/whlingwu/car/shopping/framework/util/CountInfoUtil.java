/** * 文件名：CountInfoUtil.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.framework.util;

import java.io.Serializable;
import java.util.Date;

/** 项目名称：carShopping 
 * 类名称：CountInfoUtil 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午11:26:12 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午11:26:12 
 * 修改备注：
 * @version 1.0* */

@Deprecated
public class CountInfoUtil implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 总访问量合计
	protected int totalCount = 0;
	// 日访问量
	protected int dayCount = 0;
	// 周访问量
	protected int weekCount = 0;
	// 月访问量
	protected int monthCount = 0;
	// 年访问量
	protected int yearCount = 0;

	// 临时访问量
	protected int tempCount = 0;

	protected Date date = new Date();

	/**
	 * @return
	 */
	public int getDayCount() {
		return dayCount;
	}

	/**
	 * @return
	 */
	public int getMonthCount() {
		return monthCount;
	}

	/**
	 * @return
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @return
	 */
	public int getWeekCount() {
		return weekCount;
	}

	/**
	 * @return
	 */
	public int getYearCount() {
		return yearCount;
	}

	/**
	 * @param i
	 */
	public void setDayCount(int i) {
		dayCount = i;
	}

	/**
	 * @param i
	 */
	public void setMonthCount(int i) {
		monthCount = i;
	}

	/**
	 * @param i
	 */
	public void setTotalCount(int i) {
		totalCount = i;
	}

	/**
	 * @param i
	 */
	public void setWeekCount(int i) {
		weekCount = i;
	}

	/**
	 * @param i
	 */
	public void setYearCount(int i) {
		yearCount = i;
	}

	/**
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return
	 */
	public int getTempCount() {
		return tempCount;
	}

	/**
	 * @param i
	 */
	public void setTempCount(int i) {
		tempCount = i;
	}
}

