/** * 文件名：FrontInterceptor.java 
 * * 版本信息： 
 * 日期：2015-1-22 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.front.interceptor;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.whlingwu.car.shopping.core.FrontContainer;
import com.whlingwu.car.shopping.core.front.SystemManager;


/** 项目名称：carShopping 
 * 类名称：FrontInterceptor 
 * 前台拦截器，负责对前台404,、500、action访问产生的异常进行拦截处理
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-22 上午09:10:37 
 * 修改人：凤军军 
 * 修改时间：2015-1-22 上午09:10:37 
 * 修改备注：
 * @version 1.0* */

public class FrontInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ManageInterceptor.class);
	//private static final Logger logger = LoggerFactory.getLogger(ManageInterceptor.class);
	public static final String error = "error";//访问action异常

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		logger.error("CommonInterceptor.intercept...");
		try {
			return actionInvocation.invoke();
//			return intercept0(actionInvocation);
		} catch (Throwable e) {
			e.printStackTrace();
			String msg = e.getMessage();
			logger.error("msg="+msg);
			if(StringUtils.isNotBlank(msg)){
				ServletActionContext.getRequest().getSession().setAttribute(FrontContainer.action_exception_error, msg);
			}else{
				ServletActionContext.getRequest().getSession().setAttribute(FrontContainer.action_exception_error, "未知！");
			}
			
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			ServletActionContext.getRequest().getSession().setAttribute(FrontContainer.action_exception_stack_error, sw.getBuffer().toString());
		}
		return error;
	}
	
	@Deprecated
	private String intercept0(ActionInvocation actionInvocation) throws Exception {
		String actionName = actionInvocation.getProxy().getActionName();
		Object action = actionInvocation.getProxy().getAction();
		String method = actionInvocation.getProxy().getMethod();
		String namespace = actionInvocation.getProxy().getNamespace();
		Object action2 = actionInvocation.getAction();
		
		logger.error("========CommonInterceptor interceptor! actionName="+actionName+";action="+action+";method="+method+";namespace="+namespace+";action2="+action2);
		return actionInvocation.invoke();
		
	}
}

