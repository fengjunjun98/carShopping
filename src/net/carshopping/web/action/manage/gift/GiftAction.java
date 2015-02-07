/** * 文件名：GiftAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.gift;
import org.apache.commons.lang.StringUtils;

import net.carshopping.core.BaseAction;
import net.carshopping.core.ManageContainer;
import net.carshopping.core.system.bean.User;
import net.carshopping.services.manage.gift.GiftService;
import net.carshopping.services.manage.gift.bean.Gift;
/** 项目名称：carShopping 
 * 类名称：GiftAction 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:44:56 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:44:56 
 * 修改备注：
 * @version 1.0* */

public class GiftAction extends BaseAction<Gift> {
	private static final long serialVersionUID = 1L;
	private GiftService giftService;

	public GiftService getGiftService() {
		return giftService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("gift!selectList.action");
	}

	public void setGiftService(GiftService giftService) {
		this.giftService = giftService;
	}

	public Gift getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Gift();
		}
	}

	public void insertAfter(Gift e) {
		e.clear();
	}
	
	@Override
	public String insert() throws Exception {
		User user = (User)getSession().getAttribute(ManageContainer.manage_session_user_info);
		e.setCreateAccount(user.getUsername());
		return super.insert();
	}
	
	@Override
	public String update() throws Exception {
		User user = (User)getSession().getAttribute(ManageContainer.manage_session_user_info);
		e.setUpdateAccount(user.getUsername());
		return super.update();
	}
	
	/**
	 * 查看商品赠品的基本信息
	 * @return
	 */
	public String show(){
		if(StringUtils.isBlank(e.getId())){
			throw new NullPointerException("id不能为空！");
		}
		
		e = giftService.selectById(e.getId());
		return super.show;
	}
}
