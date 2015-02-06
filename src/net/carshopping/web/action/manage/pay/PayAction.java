/** * 文件名：PayAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.pay;
import net.carshopping.core.BaseAction;
import net.carshopping.core.KeyValueHelper;
import net.carshopping.core.ManageContainer;
import net.carshopping.core.system.bean.User;
import net.jeeshop.services.manage.pay.PayService;
import net.jeeshop.services.manage.pay.bean.Pay;
import net.carshopping.web.action.manage.product.ProductAction;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 项目名称：carShopping 
 * 类名称：PayAction  支付方式
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:08:05 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:08:05 
 * 修改备注：
 * @version 1.0* */

public class PayAction extends BaseAction<Pay> {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PayAction.class);
	private PayService payService;

	public PayService getPayService() {
		return payService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("pay!selectList.action");
	}

	public void setPayService(PayService payService) {
		this.payService = payService;
	}

	public Pay getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Pay();
		}else{
			e.clear();
		}
	}

	public void insertAfter(Pay e) {
		e.clear();
	}
	
	@Override
	public String insert() throws Exception {
		if(true){
			throw new RuntimeException("非法请求！");
		}
		comm();
		return super.insert();
	}
	
	//根据code获取名称
	private void comm() {
		logger.error("comm..code="+e.getCode());
		String name = KeyValueHelper.get("pay_code_"+e.getCode());
		if(StringUtils.isBlank(name)){
			throw new NullPointerException("未配置"+e.getCode()+"的支付方式的键值对！");
		}
		
		e.setName(name);
	}
	
	@Override
	public String update() throws Exception {
		comm();
		return super.update();
	}
	
	@Override
	public String deletes() throws Exception {
		throw new RuntimeException("非法请求！");
	}
}

