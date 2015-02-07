/** * 文件名：SmsAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.sms;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import net.carshopping.core.BaseAction;
import net.carshopping.core.ManageContainer;
import net.carshopping.core.sms.SMSWebChinese;
import net.carshopping.services.manage.sms.SmsService;
import net.carshopping.services.manage.sms.bean.Sms;
/** 项目名称：carShopping 
 * 类名称：SmsAction 短信管理
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:13:33 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:13:33 
 * 修改备注：
 * @version 1.0* */

public class SmsAction extends BaseAction<Sms> {
	private static final long serialVersionUID = 1L;
	private SmsService smsService;

	public SmsService getSmsService() {
		return smsService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("sms!selectList.action");
	}

	public void setSmsService(SmsService smsService) {
		this.smsService = smsService;
	}

	public Sms getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Sms();
		}
	}

	public void insertAfter(Sms e) {
		e.clear();
	}
	
	/**
	 * 重发短信
	 * @return
	 * @throws Exception
	 */
	public String updateSendSMS() throws Exception{
		if(StringUtils.isBlank(e.getId())){
			throw new NullPointerException(ManageContainer.RoleAction_update_error);
		}
		
		e = smsService.selectById(e.getId());
		if(e==null){
			throw new NullPointerException("系统查询不到此短信！");
		}
		
		SMSWebChinese.sendSMS(e);
		
		Sms sms = new Sms();
		sms.setId(e.getId());
		sms.setReturnCode(e.getReturnCode());
		smsService.update(e);
		
		return selectList();
	}
}
