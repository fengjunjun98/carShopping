/** * 文件名：SystemListener.java 
 * * 版本信息： 
 * 日期：2015-1-22 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.whlingwu.car.shopping.core.TaskManager;
import com.whlingwu.car.shopping.core.front.SystemManager;

/** 项目名称：carShopping 
 * 类名称：SystemListener
 * 系统配置加载监听器 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-22 上午09:19:05 
 * 修改人：凤军军 
 * 修改时间：2015-1-22 上午09:19:05 
 * 修改备注：
 * @version 1.0* */

public class SystemListener implements ServletContextListener {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SystemListener.class);
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			SystemManager.getInstance();

			WebApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
			FrontCache frontCache = (FrontCache) app.getBean("frontCache");
			ManageCache manageCache = (ManageCache) app.getBean("manageCache");
			frontCache.loadAllCache();
			manageCache.loadAllCache();
			
			TaskManager taskManager = (TaskManager) app.getBean("taskManager");
			taskManager.start();
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("System load faild!"+e.getMessage());
			try {
				throw new Exception("系统初始化失败！");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}

