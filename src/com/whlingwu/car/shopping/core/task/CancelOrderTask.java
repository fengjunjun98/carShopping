/** * 文件名：CancelOrderTask.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.task;

/** 项目名称：carShopping 
 * 类名称：CancelOrderTask 
 *  一周内部支付的订单，系统自动帮其取消
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午11:00:30 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午11:00:30 
 * 修改备注：
 * @version 1.0* */

public class CancelOrderTask implements Runnable{
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CancelOrderTask.class);
	private OrderService orderService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public void run() {
		while(true){
			try {
//				TimeUnit.DAYS.sleep(1);
				TimeUnit.SECONDS.sleep(Long.valueOf(SystemManager.getInstance().get("task_SystemAutoNotifyTask_time")));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			logger.error("OrderCancelTask.run...");
			Order order = new Order();
			order.setStartDate(sdf.format(DateUtils.addDays(new Date(), -7)));
			List<Order> list = orderService.selectCancelList(order);
			if(list!=null){
				logger.error("list="+list.size());
				for(int i=0;i<list.size();i++){
					Order orderInfo = list.get(i);
					
					orderService.cancelOrderByID(orderInfo.getId());
				}
			}
		}
	}
	
//	public static void main(String[] args) {
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.addDays(new Date(), -7)));
//	}

}
