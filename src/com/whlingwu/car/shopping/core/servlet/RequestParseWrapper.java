/** * 文件名：RequestParseWrapper.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.dispatcher.multipart.JakartaMultiPartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 项目名称：carShopping 
 * 类名称：RequestParseWrapper 
 *  文件上传工具类；此类和struts2有冲突，下面的代码已给出解决冲突的办法了。
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午10:31:12 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午10:31:12 
 * 修改备注：
 * @version 1.0* */

public class RequestParseWrapper extends JakartaMultiPartRequest{
	private static final Logger log = LoggerFactory.getLogger(RequestParseWrapper.class);
	public void parse(HttpServletRequest request, String saveDir)
			throws IOException {
		/**
		 * 判断传入的参数是否是图片，如果是图片则屏蔽struts2的request功能，这个是为了解决jquery上传图片的插件以及kindedit和struts2的request上传文件时候的冲突。
		 */
		String image = request.getParameter("dir");
		log.error(request+","+saveDir+",image="+image);
		
		if(StringUtils.isBlank(image) || !image.equals("image")){
			super.parse(request, saveDir);
		}
	}
}
