/** * 文件名：CountListener.java 
 * * 版本信息： 
 * 日期：2015-1-22 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.listener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/** 项目名称：carShopping 
 * 类名称：CountListener 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-22 上午09:16:16 
 * 修改人：凤军军 
 * 修改时间：2015-1-22 上午09:16:16 
 * 修改备注：
 * @version 1.0* */

/*@Deprecated
public class CountListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
		WebApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(arg0.getSession().getServletContext());
		SessionCountService sessionCountService = (SessionCountService) app.getBean("sessionCountService");
		
		System.out.println("session创建："+arg0.getSession().isNew());
		CountUtil xmlcount = CountUtil.getInstance();
		if (arg0.getSession().isNew()) {
			SessionCount sessionCount = new SessionCount();
			sessionCount.setIp(getIp((HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST)));
		sessionCountService.insert(sessionCount );
		
			(HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			System.out.println("是新创建的session");
			xmlcount.addcount(new Date());// 增加访问量
			int n = xmlcount.getTotalCount();// 取总访问量
			String count = Integer.toString(n);
		arg0.getSession().putValue("count", count);
	}
	}

	@Override
public void sessionDestroyed(HttpSessionEvent arg0) {
	// TODO Auto-generated method stub
		System.out.println("session销毁");
}
*//** 获取IP *//*
private String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("Proxy-Client-IP");
		}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("WL-Proxy-Client-IP");
}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getRemoteAddr();
	}

		return ip;
	}
}

*/