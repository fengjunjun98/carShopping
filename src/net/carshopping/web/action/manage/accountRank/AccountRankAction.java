/** * 文件名：AccountRankAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.accountRank;
import net.jeeshop.core.BaseAction;
import net.jeeshop.core.ManageContainer;
import net.jeeshop.core.exception.NotThisMethod;
import net.jeeshop.services.manage.accountRank.AccountRankService;
import net.jeeshop.services.manage.accountRank.bean.AccountRank;
/** 项目名称：carShopping 
 * 类名称：AccountRankAction 
 * 会员等级
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:33:27 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:33:27 
 * 修改备注：
 * @version 1.0* */

public class AccountRankAction extends BaseAction<AccountRank> {
	private static final long serialVersionUID = 1L;
	private AccountRankService accountRankService;

	public AccountRankService getAccountRankService() {
		return accountRankService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("accountRank!selectList.action");
	}

	public void setAccountRankService(AccountRankService accountRankService) {
		this.accountRankService = accountRankService;
	}

	public AccountRank getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new AccountRank();
		}
	}

	public void insertAfter(AccountRank e) {
		e.clear();
	}

	/**
	 * 不支持此方法
	 */
	@Override
	public String insert() throws Exception {
		throw new NotThisMethod(ManageContainer.not_this_method);
	}

	/**
	 * 不支持此方法
	 */
	@Override
	public String deletes() throws Exception {
		throw new NotThisMethod(ManageContainer.not_this_method);
	}
}

