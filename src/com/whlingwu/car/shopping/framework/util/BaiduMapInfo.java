/** * 文件名：BaiduMapInfo.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.framework.util;

import java.io.Serializable;

/** 项目名称：carShopping 
 * 类名称：BaiduMapInfo 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午11:26:57 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午11:26:57 
 * 修改备注：
 * @version 1.0* */

//http://developer.baidu.com/map/webservice-geocoding.htm
public class BaiduMapInfo implements Serializable {
	private int status;
	private Result result;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
