/** * 文件名：Task.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;

import java.io.Serializable;

import net.carshopping.core.dao.page.PagerModel;

/** 项目名称：carShopping 
 * 类名称：Task 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:39:00 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:39:00 
 * 修改备注：
 * @version 1.0* */

public class Task extends PagerModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private String sleep;
	private String unit;
	private String nextWorkTime;
	private String currentStatus;
	private String clz;

	public static final String task_code_ManageIndexReportTask = "ManageIndexReportTask";

	public static final String task_currentStatus_wait = "wait";
	public static final String task_currentStatus_run = "run";
	public static final String task_currentStatus_stop = "stop";

	public void clear() {
		super.clear();
		id = null;
		code = null;
		name = null;
		sleep = null;
		unit = null;
		nextWorkTime = null;
		currentStatus = null;
		clz = null;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSleep() {
		return sleep;
	}

	public void setSleep(String sleep) {
		this.sleep = sleep;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getNextWorkTime() {
		return nextWorkTime;
	}

	public void setNextWorkTime(String nextWorkTime) {
		this.nextWorkTime = nextWorkTime;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getClz() {
		return clz;
	}

	public void setClz(String clz) {
		this.clz = clz;
	}
}

