/** * 文件名：SystemAutoNotifyTask.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.task;

/** 项目名称：carShopping 
 * 类名称：SystemAutoNotifyTask 
 *  系统自动到货通知定时器
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午10:58:31 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午10:58:31 
 * 修改备注：
 * @version 1.0* */

public class SystemAutoNotifyTask implements Runnable {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SystemAutoNotifyTask.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private EmailNotifyProductService emailNotifyProductService;

	public void setEmailNotifyProductService(
			EmailNotifyProductService emailNotifyProductService) {
		this.emailNotifyProductService = emailNotifyProductService;
	}

	/**
	 * 1、查询出没有发送通知的系统到货通知列表
	 * 2、从第一步查询出的列表中去分析这些数据对应的商品是否缺货，如果不缺货，则按照指定的邮件模板发送邮件到指定邮箱，通知商品已到货。
	 */
	@Override
	public void run() {
//		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
//		WebApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
//		EmailNotifyProductService emailNotifyProductService = (EmailNotifyProductService) app.getBean("emailNotifyProductServiceManage");
		logger.error("emailNotifyProductService="+emailNotifyProductService);
		while (true) {
			try {
//				TimeUnit.DAYS.sleep(1);
				TimeUnit.SECONDS.sleep(Long.valueOf(SystemManager.getInstance().get("task_SystemAutoNotifyTask_time")));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.error("OrderCancelTask.run...");
			emailNotifyProductService.autoNotify();
		}
	}

}

