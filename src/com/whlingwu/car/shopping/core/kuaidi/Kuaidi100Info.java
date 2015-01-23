/** * 文件名：Kuaidi100Info.java 
 * * 版本信息： 
 * 日期：2015-1-22 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.kuaidi;

import java.util.List;

/** 项目名称：carShopping 
 * 类名称：Kuaidi100Info 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-22 上午09:14:18 
 * 修改人：凤军军 
 * 修改时间：2015-1-22 上午09:14:18 
 * 修改备注：
 * @version 1.0* */

public class Kuaidi100Info {
	private String message;//消息体
	private String status;//查询的结果状态。0：运单暂无结果，1：查询成功，2：接口出现异常，408：验证码出错（仅适用于APICode url，可忽略) 。遇到其他情况，请按获得身份授权key的邮件中的方法获得技术支持。
	private String state;//快递单当前的状态 。0：在途中,1：已发货，2：疑难件，3： 已签收 ，4：已退货。该状态还在不断完善中，有更多的参数需求，欢迎发邮件至 kuaidi@kingdee.com 提出
	private List<Kuaidi100Item> data;//数据集合

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Kuaidi100Item> getData() {
		return data;
	}

	public void setData(List<Kuaidi100Item> data) {
		this.data = data;
	}

	public void clear() {
		this.message = null;
		this.status = null;
		this.state = null;
		
		if(this.data!=null){
			this.data.clear();
			this.data = null;
		}
	}

	@Override
	public String toString() {
		return "Kuaidi100Info [message=" + message + ", status=" + status
				+ ", state=" + state + ", data=" + data + "]";
	}

}

