/** * 文件名：AccountAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.account;
import java.io.IOException;

import net.jeeshop.core.BaseAction;
import net.jeeshop.core.KeyValueHelper;
import net.jeeshop.core.oscache.FrontCache;
import net.jeeshop.core.util.MD5;
import net.jeeshop.services.manage.account.AccountService;
import net.jeeshop.services.manage.account.bean.Account;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/** 项目名称：carShopping 
 * 类名称：AccountAction 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:32:22 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:32:22 
 * 修改备注：
 * @version 1.0* */

public class AccountAction extends BaseAction<Account> {
	private static final Logger logger = LoggerFactory.getLogger(AccountAction.class);
	private static final long serialVersionUID = 1L;
	private AccountService accountService;
//	private AreaService areaService;

//	public void setAreaService(AreaService areaService) {
//		this.areaService = areaService;
//	}

	protected void selectListAfter() {
		pager.setPagerUrl("account!selectList.action");
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public Account getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Account();
		}else{
			e.clear();
		}
		
		super.initPageSelect();
	}
	
	@Override
	public String toEdit() throws Exception {
		super.toEdit();
		
		getKeyValue();
		return toEdit;
	}

	/**
	 * 根据键获取值
	 */
	private void getKeyValue() {
		if(StringUtils.isNotBlank(e.getAccountType())){
			e.setAccountTypeName(KeyValueHelper.get("account_accountType_"+e.getAccountType()));
		}else{
			e.setAccountTypeName("系统会员");
		}
		if(StringUtils.isNotBlank(e.getRank())){
			e.setRankName(KeyValueHelper.get("account_rank_"+e.getRank()));
		}
	}
	
	/**
	 * 根据会员账号查询会员信息
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
		String account = getRequest().getParameter("account");
		if(StringUtils.isBlank(account)){
			throw new NullPointerException("账号不能为空！");
		}
		e.clear();  
		e.setAccount(account);
		e = getServer().selectOne(getE());
		if(e==null){
			throw new NullPointerException("系统查询不到账号为"+account+"的用户信息！");
		}
		getKeyValue();
		
		return super.show;
	}
	
	
	public void insertAfter(Account e) {
		e.clear();
	}
	
	/**
	 * 重置会员密码，发送重置好的密码到会员邮箱
	 * @return
	 * @throws Exception
	 */
	public String czmm() throws Exception{
		Account acc = new Account();
		acc.setId(e.getId());
		acc.setPassword(MD5.md5("111111"));
		accountService.update(acc);
		return selectList();
	}
	
	/**
	 * 转到冻结页面
	 * @return
	 */
	public String toFreeze() throws Exception {
		logger.error("AccountAction.toFreeze id = " + e.getId());
		if(StringUtils.isBlank(e.getId())){
			throw new NullPointerException("参数不能为空");
		}
		
		e = accountService.selectById(e.getId());
		if(e==null){
			throw new NullPointerException("根据用户ID查询不到用户信息");
		}
		return "freeze";
	}
	
	/**
	 * 冻结用户
	 * @return
	 * @throws Exception 
	 */
	public String updateFreeze() throws Exception {
		logger.error("AccountAction.freeze id = " + e.getId());
		if(StringUtils.isBlank(e.getId())){
			throw new NullPointerException("参数不能为空");
		}
		
		accountService.update(e);
		
		e.clear();
		return super.selectList();
	}
	
	public String testUpdateScoure(){
		logger.error("testUpdateScoure...");
		WebApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(getRequest().getSession().getServletContext());
		net.jeeshop.services.front.account.AccountService accountServiceFront = (net.jeeshop.services.front.account.AccountService) app.getBean("accountServiceFront");
		net.jeeshop.services.front.account.bean.Account acc = new net.jeeshop.services.front.account.bean.Account();
		acc.setAccount("test1");
		acc.setAddScore(1200);
		accountServiceFront.updateScore(acc);
		return null;
	}
	
	/**
	 * 分页查询会员信息
	 */
//	@Override
//	public String selectList() throws Exception {
//		super.selectList();
//		if(pager.getList()!=null && pager.getList().size()>0){
//			for(int i=0;i<pager.getList().size();i++){
//				Account acc = (Account)pager.getList().get(i);
//				if(StringUtils.isNotBlank(acc.getOpenId())){//腾讯QQ
//					
//				}
//			}
//		}
//		return toList;
//	}
}

