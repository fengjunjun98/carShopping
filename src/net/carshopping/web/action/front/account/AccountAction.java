/** * 文件名：AccountAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.front.account;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.carshopping.core.BaseAction;
import net.carshopping.core.FrontContainer;
import net.carshopping.core.dao.page.PagerModel;
import net.carshopping.core.front.SystemManager;
import net.carshopping.core.pay.alipay.alipayescow.config.AlipayConfig;
import net.carshopping.core.pay.alipay.alipayescow.util.AlipaySubmit;
import net.carshopping.core.util.AddressUtils;
import net.carshopping.core.util.DateTimeUtil;
import net.carshopping.core.util.MD5;
import net.carshopping.core.util.TokenUtil;
import net.carshopping.services.front.account.AccountService;
import net.carshopping.services.front.account.bean.Account;
import net.carshopping.services.front.account.bean.LoginTypeEnum;
import net.carshopping.services.front.address.AddressService;
import net.carshopping.services.front.address.bean.Address;
import net.carshopping.services.front.area.bean.Area;
import net.carshopping.services.front.email.EmailService;
import net.carshopping.services.front.email.bean.Email;
import net.carshopping.services.front.favorite.FavoriteService;
import net.carshopping.services.front.favorite.bean.Favorite;
import net.carshopping.services.front.news.NewsService;
import net.carshopping.services.front.news.bean.News;
import net.carshopping.services.front.notifyTemplate.bean.NotifyTemplate;
import net.carshopping.services.front.order.OrderService;
import net.carshopping.services.front.order.bean.Order;
import net.carshopping.services.front.order.bean.OrderSimpleReport;
import net.carshopping.services.front.product.ProductService;
import net.carshopping.services.front.product.bean.Product;
import net.carshopping.web.action.front.orders.CartInfo;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import weibo4j.model.WeiboException;

import com.alibaba.fastjson.JSON;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
/** 项目名称：carShopping 
 * 类名称：AccountAction 
 *  门户会员服务类
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:15:33 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:15:33 
 * 修改备注：
 * @version 1.0* */

public class AccountAction extends BaseAction<Account> {
	private static final Logger logger = Logger.getLogger(AccountAction.class);
	private static final long serialVersionUID = 1L;
	private AccountService accountService;
	private OrderService orderService;
	private NewsService newsService;
	private AddressService addressService;//配送地址service
	private List<Address> addressList;//配送地址列表
	private ProductService productService;
	private FavoriteService favoriteService;//商品收藏夹
	private String selectLeftMenu;//选中的个人中心的菜单项
	private EmailService emailService;
	
	private String helpCode;//帮助code
	private News news;//文章
	private Address address;//配送地址
	private OrderSimpleReport orderSimpleReport;//简单报表
	
	private static final Object qq_login_lock = new Object();//qq登陆，本地锁
	private static final Object sinawb_login_lock = new Object();//新浪微博登陆，本地锁
	
	// 登陆错误信息
	private String errorMsg;
	
	private static final String toLogin = "toLogin";//转到登陆界面,forword方式 地址不变
	private static final String toLoginRedirect = "toLoginRedirect";//转到登陆界面,getResponse().sendRedirect(arg0)方式 地址变化
	private static final String toIndex = "toIndex";//转到门户首页

	public OrderSimpleReport getOrderSimpleReport() {
		return orderSimpleReport;
	}

	public void setOrderSimpleReport(OrderSimpleReport orderSimpleReport) {
		this.orderSimpleReport = orderSimpleReport;
	}

	public FavoriteService getFavoriteService() {
		return favoriteService;
	}

	public void setFavoriteService(FavoriteService favoriteService) {
		this.favoriteService = favoriteService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public String getHelpCode() {
		return helpCode;
	}

	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public String getSelectLeftMenu() {
		return selectLeftMenu;
	}

	public void setSelectLeftMenu(String selectLeftMenu) {
		this.selectLeftMenu = selectLeftMenu;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public AccountService getAccountService() {
		return accountService;
	}

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
		logger.error("AccountAction.prepare...");
		if (this.e == null) {
			this.e = new Account();
		}else{
			e.clear();
		}
		
		if(address==null){
			this.address = new Address();
		}else{
			address.clear();
		}
		
		errorMsg = null;
		
		if(orderSimpleReport!=null){
			orderSimpleReport.clear();
			orderSimpleReport = null;
		}
		
		/**
		 * 清除地址列表数据
		 */
		if(addressList!=null && addressList.size()>0){
			for(int i=0;i<addressList.size();i++){
				addressList.get(i).clear();
			}
			addressList.clear();
			addressList = null;
		}
		
		super.setSelectMenu(FrontContainer.not_select_menu);//设置主菜单为不选中
	}

	public void insertAfter(Account e) {
		e.clear();
	}

	/**
	 * 用户注册
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String doRegister() throws IOException {
		if(StringUtils.isBlank(e.getEmail())){
			throw new NullPointerException("邮箱不能为空！");
		}
		
		e.setPassword(MD5.md5(e.getPassword()));
		if (StringUtils.isBlank(e.getId())) {
			// 用户注册
			getServer().insert(e);
			
			//发送邮件到用户的邮箱
//			MailUtil mail = new MailUtil(e.getEmail());//new MailUtil("543089122@qq.com",SystemManager.getInstance().get("from_email_account"),SystemManager.getInstance().get("from_email_password"), SystemManager.getInstance().get("from_eamil_smtpServer"), "myshop注册验证邮件");//用户注册成功发送邮件
//			boolean result = mail.startSend("注册邮箱验证","恭喜，您在myshop站点的账号注册成功！点击下面的链接进行验证。有效时间为2小时。http://myshop.itelse.com");
//			logger.error("email resule = " + result);
			
			accountService.sendEmail(e, NotifyTemplate.email_reg);
		} else {
			// 修改密码
//			getServer().update(e);
			throw new NullPointerException("不支持！");
		}
		
//		getSession().setAttribute("checkEmail","checkEmail");
		getSession().setAttribute("uid", e.getId());
		getResponse().sendRedirect(SystemManager.systemSetting.getWww()+"/user/checkEmail.html");
		return null;
	}
	
	/**
	 * 用户注册--》再次发送邮件
	 * @return
	 * @throws IOException 
	 */
	public String sendEmailAgain() throws IOException{
		String uid = getRequest().getParameter("uid");
		if(StringUtils.isBlank(uid)){
			throw new NullPointerException("参数不正确！");
		}
		
		Account acc = accountService.selectById(uid);
		if(acc==null){
			throw new NullPointerException("根据用户ID查询不到用户信息！");
		}
		
		accountService.sendEmail(acc, NotifyTemplate.email_reg);
		getSession().setAttribute("uid", acc.getId());
		getResponse().sendRedirect(SystemManager.systemSetting.getWww()+"/user/checkEmail.html");
		return null;
	}
	/**
	 * 转到邮箱验证提示页面
	 * @return
	 */
	public String checkEmail(){
		logger.error("checkEmail");
		
//		Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);
//		if (acc == null || StringUtils.isBlank(acc.getAccount())) {
//			return toLogin;
//		}
		return "checkEmail";
	}
	
	/**
	 * 转到忘记密码页面
	 * @return
	 */
	public String forget(){
		return "forget";
	}
	
	/**
	 * 找回密码
	 * @return
	 * @throws Exception 
	 */
	public String doForget() throws Exception{
//		synchronized (this) {
//			String token = getRequest().getParameter("token");
//			logger.error("doForget...token="+token);
//			boolean _isTokenValid = TokenUtil.getInstance().isTokenValid(getRequest());
//			logger.error("_isTokenValid = " + _isTokenValid);
			if(!TokenUtil.getInstance().isTokenValid(getRequest())){
				throw new Exception("表单重复提交了！");
			}
//		}
		String account = getRequest().getParameter("account");
		if(StringUtils.isNotBlank(account)){
			//如果此值不为空，则说明是 重新发送按钮 请求的此方法。重新发送按钮来重发邮件
			e.setAccount(account);
		}
		
		accountService.doForget(e);
		//等待用户检查短信或邮件
		return "toWaitUserCheck";
	}
	
	public String waitUserCheck(){
		return "waitUserCheck";
	}
	
	/**
	 * ajax检查用户名称是否存在
	 * @return
	 * @throws IOException 
	 */
	public String checkAccountExist() throws IOException{
		super.utf8JSON();
		if(StringUtils.isBlank(e.getAccount())){
			getResponse().getWriter().write("{\"error\":\"用户名不能为空!\"}");
		}else{
			Account acc = new Account();
			acc.setAccount(e.getAccount());
			if(accountService.selectCount(acc)==0){
				getResponse().getWriter().write("{\"error\":\"用户名不存在!\"}");
			}else{
				getResponse().getWriter().write("{\"ok\":\"用户名输入正确!\"}");
			}
		}
		return null;
	}

	/**
	 * ajax检查密码是否正确
	 * @return
	 * @throws IOException 
	 */
	public String checkPassword() throws IOException{
		super.utf8JSON();
		if(StringUtils.isBlank(e.getPassword())){
			getResponse().getWriter().write("{\"error\":\"密码不能为空!\"}");
		}else{
			Account acc = new Account();
			acc.setPassword(MD5.md5(e.getPassword()));
			if(accountService.selectCount(acc)==0){
				getResponse().getWriter().write("{\"error\":\"输入的密码不正确!\"}");
			}else{
				getResponse().getWriter().write("{\"ok\":\"密码正确!\"}");
			}
		}
		return null;
	}
	
	/**
	 * ajax检查新邮箱不能和原邮箱一致
	 * @return
	 * @throws IOException 
	 */
	public String changeEmailCheck() throws IOException{
		super.utf8JSON();
		if(StringUtils.isBlank(e.getNewEmail())){
			getResponse().getWriter().write("{\"error\":\"新邮箱不能为空!\"}");
		}else{
			Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);
			if (acc == null || StringUtils.isBlank(acc.getAccount())) {
				return toLogin;
			}
			
			if(acc.getEmail().equals(e.getNewEmail())){
				getResponse().getWriter().write("{\"error\":\"新邮箱不能和原邮箱一致!\"}");
			}else{
				getResponse().getWriter().write("{\"ok\":\"系统认为此邮箱可用!\"}");
			}
		}
		return null;
	}
	
	/**
	 * 使用QQ账号登陆。JAVA版
	 * @return
	 * @throws IOException
	 */
	public String qqLogin() throws IOException{
		getResponse().setContentType("text/html;charset=utf-8");
        try {
        	getResponse().sendRedirect(new Oauth().getAuthorizeURL(getRequest()));
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        
        return null;
	}
	
	/**
	 * qq登陆回调方法。java版
	 * @return
	 * @throws IOException
	 */
	public String qqCallbackNotifySession2() throws IOException{
		logger.error("==qqCallbackNotifySession2执行QQ登陆回调方法==");
		getResponse().setContentType("text/html; charset=utf-8");
//        PrintWriter out = getResponse().getWriter();
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(getRequest());
            String accessToken   = null,openID = null,nickname = null;
            long tokenExpireIn = 0L;
            if (accessTokenObj.getAccessToken().equals("")) {
//                我们的网站被CSRF攻击了或者用户取消了授权
//                做一些数据统计工作
                logger.error("没有获取到响应参数");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();

                getRequest().getSession().setAttribute("demo_access_token", accessToken);
                getRequest().getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj =  new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();

                logger.error("欢迎你，代号为 " + openID + " 的用户!");
//                getRequest().getSession().setAttribute("demo_openid", openID);
                logger.error("<a href=" + "/shuoshuoDemo.html" +  " target=\"_blank\">去看看发表说说的demo吧</a>");
                // 利用获取到的accessToken 去获取当前用户的openid --------- end

                logger.error("<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- start </p>");
                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
//                out.println("<br/>");
                if (userInfoBean.getRet() == 0) {
                	nickname = userInfoBean.getNickname();
                    logger.error(userInfoBean.getNickname() + "<br/>");
                    logger.error(userInfoBean.getGender() + "<br/>");
                    logger.error("黄钻等级： " + userInfoBean.getLevel() + "<br/>");
                    logger.error("会员 : " + userInfoBean.isVip() + "<br/>");
                    logger.error("黄钻会员： " + userInfoBean.isYellowYearVip() + "<br/>");
                    logger.error("<image src=" + userInfoBean.getAvatar().getAvatarURL30() + "/><br/>");
                    logger.error("<image src=" + userInfoBean.getAvatar().getAvatarURL50() + "/><br/>");
                    logger.error("<image src=" + userInfoBean.getAvatar().getAvatarURL100() + "/><br/>");
                } else {
                    logger.error("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
                }
                logger.error("<p> end -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- end </p>");
            }
            
            
            synchronized (qq_login_lock) {
            	//查询本地数据库，如果没有此用户则创建一个
    			Account acc = new Account();
    			acc.setOpenId(openID);
    			acc = accountService.selectOne(acc);
    			if(acc==null){
    				logger.error("查询不到此qq用户。准备创建一个。");
    				acc = new Account();
    				acc.setOpenId(openID);
    				acc.setAccessToken(accessToken);
    				acc.setLoginType(LoginTypeEnum.qq);//设置为QQ登陆
    				acc.setAccountType(acc.getLoginType().toString());
    				accountService.insertOutAccount(acc);
    				logger.error("创建成功。");
    			}
    			
    			acc.setLoginType(LoginTypeEnum.qq);//设置为QQ登陆
    			acc.setNickname(nickname);
    			getSession().setAttribute(FrontContainer.USER_INFO,acc);
    			logger.error("注册完毕.");
			}
            
            logger.error("QQ登陆回调方法执行完毕，准备跳转到门户首页。。。");
            getResponse().sendRedirect(SystemManager.systemSetting.getWww());
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return null;
	}
	
	/**
	 * 新浪微博登陆java版
	 * @return
	 * @throws IOException 
	 * @throws WeiboException 
	 */
	public String sinawb() throws IOException, WeiboException{
		logger.error("sinawb...");
//		BareBonesBrowserLaunch.openURL(new weibo4j.Oauth().authorize("code","",""));
		String url = new weibo4j.Oauth().authorize("code","","");
		logger.error("url = " + url);
		getResponse().sendRedirect(url);
		return null;
	}
	
	/**
	 * 新浪微博回调方法java版
	 * @return
	 */
	public String sinawbCallbackNotifySession2() throws Exception{
		String code = getRequest().getParameter("code");
		logger.error("sinawbCallbackNotifySession2 ..");
		if(StringUtils.isBlank(code)){
			throw new NullPointerException("非法请求！");
		}
		
		weibo4j.Oauth oauth = new weibo4j.Oauth();
		weibo4j.http.AccessToken accessToken = oauth.getAccessTokenByCode(code);
		logger.error("accessToken = " + accessToken.getAccessToken() + ",uid = " + accessToken.getUid());
		logger.error("AccessToken = " + accessToken);
		
		weibo4j.Users um = new weibo4j.Users();
		um.client.setToken(accessToken.getAccessToken());
		weibo4j.model.User user = um.showUserById(accessToken.getUid());
		logger.error("user = " + user);
		
		//查询本地数据库，如果没有此用户则创建一个
		synchronized (sinawb_login_lock) {
			Account acc = new Account();
			acc.setSinaWeiboID(accessToken.getUid());
			acc = accountService.selectOne(acc);
			if(acc==null){
				logger.error("查询不到此新浪微博用户。准备创建一个。");
				acc = new Account();
				acc.setSinaWeiboID(accessToken.getUid());
				acc.setLoginType(LoginTypeEnum.sinawb);//设置为新浪微博登陆
				acc.setAccountType(acc.getLoginType().toString());
				accountService.insertOutAccount(acc);
				logger.error("创建成功。");
			}
			acc.setLoginType(LoginTypeEnum.sinawb);//设置为新浪微博登陆
			acc.setNickname(user.getScreenName());
			getSession().setAttribute(FrontContainer.USER_INFO,acc);
			logger.error("注册完毕.");
		}
		
		
		logger.error("新浪微博回调方法执行完毕，准备跳转到门户首页。。。");
        getResponse().sendRedirect(SystemManager.systemSetting.getWww());
		return null;
	}
	
	/**
	 * QQ回调通知系统session的业务逻辑处理。ajax回调版
	 * @return
	 * @throws IOException 
	 */
	@Deprecated
	public String qqCallbackNotifySession() throws IOException{
		String status = getRequest().getParameter("status");
		String openId = getRequest().getParameter("openId");
		String accessToken = getRequest().getParameter("accessToken");
		String nickname = getRequest().getParameter("nickname");
		logger.error("qqCallbackNotifySession >> status="+status+",openId="+openId+",accessToken="+accessToken+",nickname="+nickname);
		
		if(StringUtils.isBlank(status) || StringUtils.isBlank(openId) || StringUtils.isBlank(accessToken) || StringUtils.isBlank(nickname)){
			logger.error("qqCallbackNotifySession 非法请求。");
			getResponse().getWriter().write("-1");
			return null;
		}

		if(status.equals("login")){
			logger.error("qq用户登陆，在本地系统内注册到session中.");
			
			//查询本地数据库，如果没有此用户则创建一个
			Account acc = new Account();
			acc.setOpenId(openId);
			acc = accountService.selectOne(acc);
			if(acc==null){
				logger.error("查询不到此新浪微博用户。准备创建一个。");
				acc = new Account();
				acc.setOpenId(openId);
				acc.setAccessToken(accessToken);
				acc.setLoginType(LoginTypeEnum.qq);//设置为QQ登陆
				acc.setAccountType(acc.getLoginType().toString());
				accountService.insertOutAccount(acc);
				logger.error("创建成功。");
			}
			
			acc.setLoginType(LoginTypeEnum.qq);//设置为QQ登陆
			acc.setNickname(nickname);
			getSession().setAttribute(FrontContainer.USER_INFO,acc);
			logger.error("注册完毕.");
		}else if(status.equals("loginOut")){
			logger.error("qq登陆用户退出。");
			loginout();
		}
		getResponse().getWriter().write("0");
		return null;
	}
	
	/**
	 * 新浪微博登陆成功后通知本地系统
	 * @return
	 * @throws IOException 
	 */
	public String sinaWeiboLoginNotifySession() throws IOException{
		String id = getRequest().getParameter("id");//新浪微博登陆返回的ID
		String status = getRequest().getParameter("status");//login/loginout
		String nickname = getRequest().getParameter("nickname");//昵称
		logger.error("sinaWeiboLoginNotifySession...id="+id+",status="+status+",nickname="+nickname);
		if(StringUtils.isBlank(id) || StringUtils.isBlank(status) || StringUtils.isBlank(nickname)){
			getResponse().getWriter().write("-1");//非法请求
			return null;
		}
		
		if(status.equals("login")){//微博登陆成功
			logger.error("sinaWeiboLoginNotifySession用户登陆，在本地系统内注册到session中.");
			
			//查询本地数据库，如果没有此用户则创建一个
			Account acc = new Account();
			acc.setSinaWeiboID(id);
			acc = accountService.selectOne(acc);
			if(acc==null){
				logger.error("查询不到此新浪微博用户。准备创建一个。");
				acc = new Account();
				acc.setSinaWeiboID(id);
				acc.setLoginType(LoginTypeEnum.sinawb);//设置为新浪微博登陆
				acc.setAccountType(acc.getLoginType().toString());
				accountService.insertOutAccount(acc);
				logger.error("创建成功。");
			}
			acc.setLoginType(LoginTypeEnum.sinawb);//设置为新浪微博登陆
			acc.setNickname(nickname);
			getSession().setAttribute(FrontContainer.USER_INFO,acc);
			logger.error("注册完毕.");
		}else{
			//微博退出成功
			loginout();
		}
		getResponse().getWriter().write("0");
		return null;
	}
	
	/**
	 * 转到登陆页面
	 * @return
	 */
	public String login() {
		this.errorMsg = null;
		getSession().setAttribute(FrontContainer.login_errorMsg, errorMsg);
		logger.error("toLogin...");
		getSession().setAttribute("errorMsg", null);
		if (getSession().getAttribute(FrontContainer.USER_INFO) != null) {
			return toIndex;
		}
		return toLogin;
	}
	
	/**
	 * 转到注册页面
	 * @return
	 */
	public String register() {
		logger.error("register...");
		if (getSession().getAttribute(FrontContainer.USER_INFO) != null) {
			return toIndex;
		}
		return "register";
	}
	
	/**
	 * 用户登陆
	 * 
	 * @return
	 */
	public String doLogin() {
		logger.error("doLogin()...");
		if (getSession().getAttribute(FrontContainer.USER_INFO) != null) {
			return toIndex;
		}

		errorMsg = "<font color='red'>帐号或密码错误!</font>";
		if (e.getAccount() == null || e.getAccount().trim().equals("")
				|| e.getPassword() == null || e.getPassword().trim().equals("")){
			getSession().setAttribute("errorMsg", errorMsg);
			logger.error("doLogin.errorMsg="+errorMsg);
			return toLogin;
		}

		//用户验证
		e.setPassword(MD5.md5(e.getPassword()));
		String account = e.getAccount();
		String password = e.getPassword();
		e.clear();
		e.setAccount(account);
		e.setPassword(password);
		Account acc = accountService.selectOne(e);
		if (acc == null) {
			getSession().setAttribute(FrontContainer.login_errorMsg, errorMsg);
			return toLogin;
		}else if(acc.getFreeze().equals(Account.account_freeze_y)){
			if(StringUtils.isBlank(acc.getFreezeStartdate()) && StringUtils.isBlank(acc.getFreezeEnddate())){
				getSession().setAttribute(FrontContainer.login_errorMsg, "<font color='red'>此账号已永久冻结!有疑问请联系站点管理员!</font>");
			}else{
				getSession().setAttribute(FrontContainer.login_errorMsg, "<font color='red'>此账号已暂时冻结!有疑问请联系站点管理员!</font>");
			}
			return toLogin;
		}else if(acc.getEmailIsActive().equals(Account.account_emailIsActive_n)){
			//邮箱未激活
			errorMsg = "<font color='red'>此账号的邮箱尚未激活，请立即去激活邮箱！</font>";
			getSession().setAttribute(FrontContainer.login_errorMsg, errorMsg);
			return toLogin;
		}
		errorMsg = null;
		acc.setLoginType(LoginTypeEnum.system);//登陆方式
		getSession().setAttribute(FrontContainer.USER_INFO, acc);
		
		//更新用户最后登录时间
		e.clear();
		e.setId(acc.getId());
		e.setLastLoginTime("yes");
		e.setLastLoginIp(AddressUtils.getIp(getRequest()));
		String address = null;
		try {
			address = AddressUtils.getAddresses("ip=" + e.getLastLoginIp(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		e.setLastLoginArea(address);
		accountService.update(e);
		e.clear();
		return toIndex;
	}

	/**
	 * 获得客户端真实IP地址
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {  
	    String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	}
	
	public String exit() {
		loginout();
		return "toLoginRedirect";
	}
	/**
	 * 用户注销
	 * 
	 * @return
	 */
	public String loginout() {
		//清除用户session
		Account account = (Account) getSession().getAttribute(
				FrontContainer.USER_INFO);
		if (account != null) {
			account.clear();
		}
		getSession().setAttribute(FrontContainer.USER_INFO, null);
		
		//清除用户购物车缓存
		CartInfo cartInfo = (CartInfo) getSession().getAttribute(FrontContainer.myCart);
		if(cartInfo!=null){
			cartInfo.clear();
		}
		getSession().setAttribute(FrontContainer.myCart, null);
		getSession().setAttribute(FrontContainer.login_errorMsg, null);
		
		
		//清除历史浏览记录
		LinkedHashMap<String, Product> history_product_map = (LinkedHashMap<String, Product>) getSession().getAttribute(FrontContainer.history_product_map);
//		List<String> history_product_map = (List<String>) getSession().getAttribute(FrontContainer.history_product_map);
		if(history_product_map!=null){
			history_product_map.clear();
		}
		getSession().setAttribute(FrontContainer.history_product_map,null);
		return toLogin;
	}
	
	/**
	 * 分页查询商品收藏夹
	 * @return
	 * @throws Exception 
	 */
	public String favorite() throws Exception{
		Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);
		if (acc == null || StringUtils.isBlank(acc.getAccount())) {
			return toLogin;
		}
		
		Favorite favorite = new Favorite();
		favorite.setAccount(acc.getAccount());
		PagerModel commentPager = super.selectPagerModelByServices(favoriteService, favorite);
		super.pager = commentPager;//公用分页控件需要这么写。
		
		if(super.pager!=null && super.pager.getList()!=null && super.pager.getList().size()>0){
			List<String> productIds = new LinkedList<String>();
			for(int i=0;i<super.pager.getList().size();i++){
				Favorite ff = (Favorite)super.pager.getList().get(i);
				productIds.add(ff.getProductID());
			}
			
			//根君商品ID集合加载商品信息：名称、价格、销量、是否上下架等
			Product p = new Product();
			p.setProductIds(productIds);
			List<Product> productList = productService.selectProductListByIds(p);
			
			//将查询出来的每一个商品对象挂到收藏夹对象上去
			if(productList!=null && productList.size()>0){
				for(int i=0;i<super.pager.getList().size();i++){
					Favorite ff = (Favorite)super.pager.getList().get(i);
					for(int j=0;j<productList.size();j++){
						Product product = productList.get(j);
						if(ff.getProductID().equals(product.getId())){
							ff.setProduct(product);
							break;
						}
					}
				}
			}
		}
		
		selectLeftMenu = FrontContainer.user_leftMenu_favorite;
		return "favorite";
	}
	
	/**
	 * ajax验证输入的字符的唯一性
	 * @return
	 * @throws IOException
	 */
	public String unique() throws IOException{
		logger.error("验证输入的字符的唯一性"+e);
		logger.error(e.getNickname());
		if(StringUtils.isNotBlank(e.getNickname())){//验证昵称是否被占用
			logger.error("验证昵称是否被占用");
			String nickname = e.getNickname();
			e.clear();
			e.setNickname(nickname);
			getResponse().setCharacterEncoding("utf-8");
			if(accountService.selectCount(e)>0){
				getResponse().getWriter().write("{\"error\":\"昵称已经被占用!\"}");
			}else{
				getResponse().getWriter().write("{\"ok\":\"昵称可以使用!\"}");
			}
		}else if(StringUtils.isNotBlank(e.getAccount())){//验证用户名是否被占用
			logger.error("验证用户名是否被占用");
			String account = e.getAccount();
			e.clear();
			e.setAccount(account);
			getResponse().setCharacterEncoding("utf-8");
			if(accountService.selectCount(e)>0){
				getResponse().getWriter().write("{\"error\":\"用户名已经被占用!\"}");
			}else{
				getResponse().getWriter().write("{\"ok\":\"用户名可以使用!\"}");
			}
		}else if(StringUtils.isNotBlank(e.getEmail())){//验证邮箱是否被占用
			logger.error("验证邮箱是否被占用="+e.getEmail());
			String email = e.getEmail();
			e.clear();
			e.setEmail(email);
			getResponse().setCharacterEncoding("utf-8");
			if(accountService.selectCount(e) > 0){
				getResponse().getWriter().write("{\"error\":\"邮箱已经被占用!\"}");
			}else{
				getResponse().getWriter().write("{\"ok\":\"邮箱可以使用!\"}");
			}
		}else if(StringUtils.isNotBlank(e.getVcode())){//验证验证码输入的是否正确
			logger.error("检查验证码输入的是否正确"+e.getVcode());
			String validateCode = getSession().getAttribute(FrontContainer.validateCode).toString();
			logger.error("validateCode="+validateCode);
			getResponse().setCharacterEncoding("utf-8");
			if(validateCode.equalsIgnoreCase(e.getVcode())){
				getResponse().getWriter().write("{\"ok\":\"验证码输入正确!\"}");
			}else{
				getResponse().getWriter().write("{\"error\":\"验证码输入有误!\"}");
			}
//			vcode = null;
		}else if(StringUtils.isNotBlank(e.getPassword())){//验证原始密码输入是否正确
			logger.error("验证原始密码输入是否正确"+e.getPassword());
			Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);
			getResponse().setCharacterEncoding("utf-8");
			if(StringUtils.isNotBlank(e.getPassword()) && MD5.md5(e.getPassword()).equals(acc.getPassword())){
				getResponse().getWriter().write("{\"ok\":\"原密码输入正确!\"}");
			}else{
				getResponse().getWriter().write("{\"error\":\"原密码输入有误!\"}");
			}
		}
		
		if(e!=null){
			e.clear();
		}
		return null;
	}
	
	/**
	 * 查看个人信息
	 * @return
	 */
	public String user(){
		Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);
		if (acc == null || StringUtils.isBlank(acc.getAccount())) {
			return toLogin;
		}
		
		selectLeftMenu = "user";
		e = accountService.selectById(acc.getId());
		setSelectMenu(FrontContainer.not_select_menu);//设置主菜单为不选中
		
//		getSession().setAttribute(FrontContainer.WEB_USER_INFO,e);
		
		//查询未读信件的数量
//		Letters letter = new Letters();
//		letter.setAccount(acc.getAccount());
//		int notReadLetters = lettersService.getCount(letter);
//		logger.error("notReadLetters="+notReadLetters);
//		acc.setNotReadLetters(notReadLetters);
		
		return "user";
	}
	
	/**
	 * 修改个人信息
	 * @return
	 */
	public String saveSetting(){
		logger.error("saveSetting.....");
//		logger.error("sex="+sex);
		logger.error(e);
		accountService.update(e);
//		Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);
//		acc.setSign(e.getSign());
//		acc.setMyself(e.getMyself());
//		acc.setSex(e.getSex());
		
		e.clear();
//		return "saveSetting";
		return user();
	}
	
	private boolean requireLogin() throws NullPointerException{
		Account account = (Account) getSession().getAttribute(FrontContainer.USER_INFO);
		if (account == null || StringUtils.isBlank(account.getAccount())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 配送地址管理
	 * @return
	 */
	public String address(){
		Account account = (Account) getSession().getAttribute(FrontContainer.USER_INFO);
		if (account == null || StringUtils.isBlank(account.getAccount())) {
			return toLogin;
		}
		selectLeftMenu = "address";
		address.setAccount(account.getAccount());
		addressList = addressService.selectList(address);
		return "address";
	}
	
	/**
	 * 增加配送地址
	 * @return
	 */
	public String saveAddress(){
		Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);
		if (acc == null || StringUtils.isBlank(acc.getAccount())) {
			return toLogin;
		}
		selectLeftMenu = "address";
		
		//需要将省市区的代号换成中文，插入到pcadetail字段里面去，显示的时候方便。
		StringBuilder pcadetail = new StringBuilder();
		Area sheng = SystemManager.areaMap.get(address.getProvince());//省
		pcadetail.append(sheng.getName());
		
		for(int i=0;i<sheng.getChildren().size();i++){
			Area shi = sheng.getChildren().get(i);//市
			if(shi.getCode().equals(address.getCity())){
				
				pcadetail.append(" ").append(shi.getName());
				
				for(int j = 0;j<shi.getChildren().size();j++){
					Area qu = shi.getChildren().get(j);//区
					if(qu.getCode().equals(address.getArea())){
						pcadetail.append(" ").append(qu.getName());
						break;
					}
				}
				
				break;
			}
		}
		
		address.setPcadetail(pcadetail.toString());
		
		address.setAccount(acc.getAccount());
		if(StringUtils.isBlank(address.getId())){
			addressService.insert(address);
		}else{
			addressService.update(address);
		}
		address.clear();
		return address();
	}
	
	/**
	 * 删除指定的配送地址
	 * @return
	 */
	public String deleteAddress(){
		Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);
		if (acc == null || StringUtils.isBlank(acc.getAccount())) {
			return toLogin;
		}
		selectLeftMenu = "address";
		String id = getRequest().getParameter("id");
		if(StringUtils.isBlank(id)){
			throw new NullPointerException("id is null!");
		}
		Address add = new Address();
		add.setId(id);
		addressService.delete(add);
		
		return address();
	}

	/**
	 * 编辑指定的配送地址
	 * @return
	 */
	public String editAddress(){
		Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);
		if (acc == null || StringUtils.isBlank(acc.getAccount())) {
			return toLogin;
		}
		selectLeftMenu = "address";
		String id = getRequest().getParameter("id");
		if(StringUtils.isBlank(id)){
			throw new NullPointerException("id is null!");
		}
		address = addressService.selectById(id);
		
		//获取区域列表
		if(StringUtils.isNotBlank(address.getArea())){
//			address.getArea()
			Area area = SystemManager.areaMap.get(address.getProvince());
			if(area!=null && area.getChildren()!=null && area.getChildren().size()>0){
				for(int i=0;i<area.getChildren().size();i++){
					Area city = area.getChildren().get(i);
					if(city.getCode().equals(address.getCity())){
						
						logger.error("address.getCity()="+address.getCity());
						logger.error(city.toString());
						address.setAreaList(city.getChildren());
						break;
					}
				}
			}
		}
		
		return address();
	}
	
	/**
	 * 我的订单列表
	 * @return
	 * @throws Exception 
	 */
	public String orders() throws Exception{
		Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);
		if (acc == null || StringUtils.isBlank(acc.getAccount())) {
			return toLogin;
		}
//		getSession().setAttribute(FrontContainer.selectMenu,FrontContainer.not_select_menu);
		selectLeftMenu = "orders";

		getMyOrders(acc.getAccount());
		
		//查询汇总
		orderSimpleReport = orderService.selectOrdersSimpleReport(acc.getAccount());
		logger.error("orderSimpleReport="+orderSimpleReport);
		return "orders";
	}
	
	/**
	 * 分页查询订单集合
	 * @return
	 * @throws Exception
	 */
	private void selectMyOrders(String account) throws Exception {
		int offset = 0;
		if (getRequest().getParameter("pager.offset") != null) {
			offset = Integer
					.parseInt(getRequest().getParameter("pager.offset"));
		}
		if (offset < 0)
			offset = 0;
		
//		PagerModel pm = new PagerModel();
		Order order = new Order();
		order.setAccount(account);
		((PagerModel)order).setOffset(offset);
		pager = orderService.selectPageList(order);
		if(pager==null)pager = new PagerModel();
		// 计算总页数
		pager.setPagerSize((pager.getTotal() + pager.getPageSize() - 1)
				/ pager.getPageSize());
		
//		selectListAfter();
		pager.setPagerUrl("orders.html");
	}
	/**
	 * 分页获取我的订单列表，首页分页查询订单集合，然后把查询到的ID集合仍到一个多表联合的查询里面，查询出更多的信息。分页显示用户的订单只用一个SQL貌似不好搞的。想到好办法再去优化。
	 * @throws Exception
	 */
	private void getMyOrders(String account) throws Exception {
		//分页查询订单ID集合
//		super.selectList();
		//1、分页查询订单集合
		selectMyOrders(account);
		//根据上面查询出来的ID集合，多表联合查询出订单和订单明细数据
		List<Order> ordersTemp = getPager().getList();
		List<String> ids = new LinkedList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i=0;i<ordersTemp.size();i++){
			Order orderItem = ordersTemp.get(i);
			//时间转换成可以阅读的格式
			orderItem.setCreatedate(DateTimeUtil.getDateTimeString(sdf.parse(orderItem.getCreatedate())));
			ids.add(orderItem.getId());
		}
		
		Order order = new Order();
		order.clear();
		order.setAccount(account);
		order.setQueryOrderIDs(ids);
		//2、查询指定订单集合的所有订单项集合，然后内存中对订单项进行分组
		List<Order> myOrders = orderService.selectList(order);
		if(myOrders!=null && myOrders.size()>0){
			for(int i=0;i<ordersTemp.size();i++){
				Order orderItem = ordersTemp.get(i);
				for(Iterator<Order> it = myOrders.iterator();it.hasNext();){
					Order orderdetail = it.next();
//					logger.error("orderdetail.getId()="+orderdetail.getId());
//					logger.error("orderItem.getId()="+orderItem.getId());
					if(orderdetail.getId().equals(orderItem.getId())){
						orderItem.getOrders().add(orderdetail);
						it.remove();
					}
				}
			}
		}
		
//		Map<String, Order> orderMap = new HashMap<String, Order>();
		//处理成页面显示的数据格式
//		if(myOrders!=null && myOrders.size()>0){
//			orderMap.clear();
//			for(int i=0;i<myOrders.size();i++){
//				order = myOrders.get(i);
//				Order entry = orderMap.get(order.getId());
//				if(entry==null){
//					//添加订单
//					orderMap.put(order.getId(), order);
//					//添加订单项
//					order.getOrders().add(order);
//					continue;
//				}
//				
//				//否则添加订单到此MAP订单的orders集合中，此集合存储的是订单明细信息
//				entry.getOrders().add(order);
//			}
//			myOrders.clear();
//			myOrders.addAll(orderMap.values());
//			orderMap.clear();
//
//			//根据订单ID排序
//			Collections.sort(myOrders, new Comparator<Order>() {
//				@Override
//				public int compare(Order o1, Order o2) {
//					int id1 = Integer.valueOf(o1.getId());
//					int id2 = Integer.valueOf(o2.getId());
//					if (id1 > id2) {
//						return 1;
//					} else if (id1 < id2) {
//						return 2;
//					}
//					return 0;
//				}
//			});
//			getPager().setList(myOrders);
//		}
//		getSession().setAttribute(FrontContainer.selectMenu, "user_centers");
	}
	
	/**
	 * 转到修改密码
	 * @return
	 */
	public String topwd(){
		if (getSession().getAttribute(FrontContainer.USER_INFO) == null) {
			return toIndex;
		}
		this.errorMsg = null;
		selectLeftMenu = "topwd";
		e.clear();
		return "topwd";
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	public String changePwd(){
		Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);
		if (acc == null || StringUtils.isBlank(acc.getAccount())) {
			return toLogin;
		}

		if (StringUtils.isBlank(e.getNewPassword())
				|| StringUtils.isBlank(e.getNewPassword2())
				|| StringUtils.isBlank(e.getPassword()) 
				|| !e.getNewPassword2().equals(e.getNewPassword())) {
			throw new NullPointerException();
		}
		getSession().setAttribute(FrontContainer.selectMenu,FrontContainer.not_select_menu);
//		selectLeftMenu = "changePwd";
		selectLeftMenu = "topwd";
//		logger.error(">>e.getNewPassword() = "+e.getNewPassword());
		e.setPassword(MD5.md5(e.getNewPassword()));
		e.setId(acc.getId());
//		logger.error(">>e.getPassword() = "+e.getPassword());
		accountService.update(e);
		this.errorMsg = "修改密码成功！";
		//重新缓存密码数据
		acc.setPassword(e.getPassword());
		
		e.clear();
		return "changePwdSuccess";
	}
	
//	private void setSelectMenu(String selectID){
//		getSession().setAttribute(FrontContainer.selectMenu, selectID);
//	}
	
	
//	/**
//	 * 帮助中心
//	 * @return
//	 */
//	public String help() throws Exception {
//		logger.error("this.helpCode="+this.helpCode);
//		if(StringUtils.isBlank(this.helpCode)){
//			throw new NullPointerException("helpCode参数不能为空");
//		}else if(this.helpCode.equals("index")){
//			return "help";
//		}else{
//			News newsParam = new News();
//			newsParam.setCode(helpCode);
//			news = newsService.selectSimpleOne(newsParam);
//			if(news==null){
//				throw new NullPointerException("根据code查询不到文章！");
//			}
//			
//			String url = "/jsp/helps/"+news.getId()+".jsp";
//			logger.error("url = " + url);
//			getRequest().setAttribute("newsInfoUrl",url);
//			
//			return "help";
//			
////			logger.error("SystemManager.newsMap="+SystemManager.newsMap);
////			news = SystemManager.newsMap.get(this.helpCode);//newsService.selectById(String.valueOf(helpID));
////			if(news==null){
////				throw new NullPointerException("根据code查询不到文章！");
////			}
//		}
////		return "help";
//	}
	
	/**
	 * 设置选中的
	 * @return
	 */
	public String setAddressDefault(){
		address.clear();
		String id = getRequest().getParameter("id");
		if(StringUtils.isBlank(id)){
			throw new NullPointerException("默认地址ID不能为空！");
		}
		
		Account account = (Account)getRequest().getSession().getAttribute(FrontContainer.USER_INFO);
		if(account==null || StringUtils.isBlank(account.getAccount())){
			throw new NullPointerException("账号不能为空！");
		}
		
		address.setId(id);
		address.setIsdefault("y");
		address.setAccount(account.getAccount());
		addressService.setAddressDefault(address);
		return null;
	}
	
	/**
	 * 根据省份编码获取城市列表
	 * @return
	 * @throws IOException 
	 */
	public String selectCitysByProvinceCode() throws IOException{
		logger.error("selectCitysByProvinceCode...");
		String provinceCode = getRequest().getParameter("provinceCode");
		logger.error("selectCitysByProvinceCode...provinceCode="+provinceCode);
		if(StringUtils.isBlank(provinceCode)){
			throw new NullPointerException("provinceCode is null");
		}
		
//		Area area = new Area();
//		area.setCode(provinceCode);
		if(SystemManager.areaMap!=null && SystemManager.areaMap.size()>0){
			Area areaInfo = SystemManager.areaMap.get(provinceCode);
			
			logger.error("areaInfo = " + areaInfo);
			
			if(areaInfo!=null && areaInfo.getChildren()!=null && areaInfo.getChildren().size()>0){
				String jsonStr = JSON.toJSONString(areaInfo.getChildren());
				logger.error("jsonStr="+jsonStr);
				super.utf8JSON();
				getResponse().getWriter().write(jsonStr);
				return null;
			}
		}
		
		getResponse().getWriter().write("{}");
		return null;
	}

	/**
	 * 根据城市编码获取区域列表
	 * @return
	 * @throws IOException 
	 */
	public String selectAreaListByCityCode() throws IOException{
		logger.error("selectAreaListByCityCode...");
		String provinceCode = getRequest().getParameter("provinceCode");
		String cityCode = getRequest().getParameter("cityCode");
		logger.error("selectAreaListByCityCode...provinceCode="+provinceCode+",cityCode="+cityCode);
		if(StringUtils.isBlank(provinceCode) || StringUtils.isBlank(cityCode)){
			throw new NullPointerException("provinceCode or cityCode is null");
		}
		
		if(SystemManager.areaMap!=null && SystemManager.areaMap.size()>0){
			Area city = SystemManager.areaMap.get(provinceCode);
			
			logger.error("areaInfo = " + city);
			
			if(city!=null && city.getChildren()!=null && city.getChildren().size()>0){
				for(int i=0;i<city.getChildren().size();i++){
					Area item = city.getChildren().get(i);
					if(item.getCode().equals(cityCode)){
						if(item.getChildren()!=null && item.getChildren().size()>0){
							String jsonStr = JSON.toJSONString(item.getChildren());
							logger.error("jsonStr="+jsonStr);
							super.utf8JSON();
							getResponse().getWriter().write(jsonStr);
							return null;
						}
					}
				}
			}
		}
		
		getResponse().getWriter().write("{}");
		return null;
	}
	
	public String alipayFastLogin() throws Exception{
		if(true){
			return "alipayFastLogin";
		}
		////////////////////////////////////请求参数//////////////////////////////////////
		
		//目标服务地址
		String target_service = "user.auth.quick.login";
		//必填
		//必填，页面跳转同步通知页面路径
		String return_url = SystemManager.systemSetting.getWww()+"/alipayapi_fastLogin_return_url.jsp";
		//需http://格式的完整路径，不允许加?id=123这类自定义参数
		
		//防钓鱼时间戳
		String anti_phishing_key = "";
		//若要使用请调用类文件submit中的query_timestamp函数
		
		//客户端的IP地址
		String exter_invoke_ip = "";
		//非局域网的外网IP地址，如：221.0.0.1
		
		
		//////////////////////////////////////////////////////////////////////////////////
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "alipay.auth.authorize");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("target_service", target_service);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
//		getResponse().getOutputStream().println(sHtmlText);
//		getResponse().getWriter().println(sHtmlText);
		System.out.println("sHtmlText="+sHtmlText);
//		return "alipayFastLogin";
		return null;
	}

	/**
	 * 用户使用邮件重置密码
	 * @return
	 */
	public String reset(){
		checkSendEmail();
		return "reset";
	}
	
	/**
	 * 系统发出邮件后，用户访问邮件中的URL地址，此方法检查该地址的有效性和时间的有效性
	 */
	private Email checkSendEmail(){
		String sign = getRequest().getParameter("sign");
		if(StringUtils.isBlank(sign)){
			throw new NullPointerException("参数非法!");
		}
		
		//查询邮件是否是本系统所发出的
		Email email = new Email();
		email.setSign(sign);
		email = emailService.selectOne(email);
		if(email==null){
			throw new NullPointerException("非法请求！");
		}
		
		if(email.getStatus().equals(email.email_status_y)){
			getRequest().setAttribute(FrontContainer.reset_password_email_timeout, "当前连接已失效！");
			return null;
		}
		
//		String email_id = email.getId();
		
		e.setAccount(email.getAccount());
		//检查此邮件是否过期
		long time1 = Long.valueOf(email.getStarttime());
		long time2 = new Date().getTime();
		long time3 = Long.valueOf(email.getEndtime());
		if (time2 > time1 && time2 < time3) {
			//更新邮件状态为已失效
			Email email2 = new Email();
			email2.setStatus(email.email_status_y);
			email2.setId(email.getId());
			emailService.update(email2);
			
			//允许修改密码
			return email;
		}else{
			logger.error("邮件已过期！");
			getRequest().setAttribute(FrontContainer.reset_password_email_timeout, "当前连接已失效！");
		}
		return null;
	}
	
	/**
	 * 通过邮件重置密码
	 * @return
	 * @throws IOException 
	 */
	public String doReset() throws IOException{
		logger.error("doReset...");
		if(StringUtils.isBlank(e.getAccount()) || StringUtils.isBlank(e.getPassword()) || StringUtils.isBlank(e.getPassword2())){
			throw new NullPointerException("请求非法！");
		}
		
		if(!e.getPassword().equals(e.getPassword2())){
//			getRequest().setAttribute(FrontContainer.show_user_option_error, "两次输入的密码不一致！");
			throw new RuntimeException("两次输入的密码不一致！");
		}
		logger.error("doReset...e.getPassword() = "+e.getPassword());
		Account acc = new Account();
		acc.setAccount(e.getAccount());
		acc.setPassword(MD5.md5(e.getPassword()));
		accountService.updatePasswordByAccount(acc);
		getResponse().sendRedirect(SystemManager.systemSetting.getWww()+"/user/resetSuccess.html");
		return null;
//		return "resetSuccess";
	}
	
	public String resetSuccess(){
		
		return "resetSuccess";
	}
	
	/**
	 * 转到修改邮箱页面
	 * @return
	 */
	public String changeEmail(){
		Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);
		if (acc == null || StringUtils.isBlank(acc.getAccount())) {
			return toLoginRedirect;
		}
		return "toChangeEmail";
	}
	
	/**
	 * 修改邮箱
	 * @return
	 * @throws Exception 
	 */
	public String doChangeEmail() throws Exception{
		logger.error("e.getNewEmail() = "+e.getNewEmail());
		Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);
		if (acc == null || StringUtils.isBlank(acc.getAccount())) {
			return toLogin;
		}
		
		logger.error("doChangeEmail..");
		if(!TokenUtil.getInstance().isTokenValid(getRequest())){
			throw new Exception("表单重复提交了！");
		}
		
		if(StringUtils.isBlank(e.getPassword()) || StringUtils.isBlank(e.getNewEmail())){
			throw new NullPointerException("非法请求！");
		}
		
//		Account acc = (Account)getSession().getAttribute(FrontContainer.USER_INFO);
		if(!MD5.md5(e.getPassword()).equals(acc.getPassword())){
			//前台AJAX检查密码出问题了，后台来处理前端的不足
			throw new RuntimeException("出现错误，请联系系统管理员！");
		}
		
		//发送邮件到指定邮箱。
		acc.setNewEmail(e.getNewEmail());
		accountService.sendEmail(acc,NotifyTemplate.email_change_email);
		acc.setNewEmail(null);
		return "changeEamilWait";
	}
	
	public String changeEamilWait(){
		logger.error("changeEamilWait..");
		return "toChangeEamilWait";
	}
	
	/**
	 * 修改邮箱--->用户登陆邮箱后点击邮件---->激活邮箱---->调用此方法
	 * @return
	 */
	public String active(){
		logger.error("active...");
		selectLeftMenu = "user";
		
		String sign = getRequest().getParameter("sign"); 
//		String type = getRequest().getParameter("type"); 
		if(StringUtils.isBlank(sign)){
			throw new NullPointerException("非法请求！");
		}
		Email email = checkSendEmail();
		if(email!=null){
			Account acc = new Account();
			acc.setEmail(email.getNewEmail());
			acc.setAccount(email.getAccount());
			accountService.updateEmailByAccount(acc);
			
			//修改邮箱成功后，更新session缓存中数据
			acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);
			if (acc != null && StringUtils.isNotBlank(acc.getAccount())) {
				acc.setEmail(email.getNewEmail());
			}
			
			email = new Email();
			email.setStatus(email.email_status_n);
			email.setPageMsg("恭喜：新邮箱已激活！");
			getRequest().setAttribute(FrontContainer.reset_password_email_timeout, email);
		}else{
			email = new Email();
			email.setStatus(email.email_status_y);
			email.setPageMsg("当前连接已失效！");
			getRequest().setAttribute(FrontContainer.reset_password_email_timeout, email);
		}
		return "active";
	}

	/**
	 * 激活账号的邮件的回调
	 * @return
	 */
	public String activeAccount(){
		logger.error("active...");
		String sign = getRequest().getParameter("sign");
		if(StringUtils.isBlank(sign)){
			throw new NullPointerException("非法请求！");
		}
		
		//查询邮件是否是本系统所发出的
		Email email = new Email();
		email.setSign(sign);
		email = emailService.selectOne(email);
		if(email==null){
			throw new NullPointerException("非法请求！");
		}
		
		if(email.getStatus().equals(Email.email_status_y)){
			getRequest().setAttribute("LinkInvalid", "链接已失效！");
			return "activeAccount";
		}
		
		Account acc = new Account();
		acc.setAccount(email.getAccount());
		acc = accountService.selectOne(acc);
		if(acc==null){
			throw new NullPointerException("非法请求！"); 
		}
		
		Account acc2 = new Account();
		acc2.setId(acc.getId());
		acc2.setEmailIsActive(Account.account_emailIsActive_y);
		accountService.updateDataWhenActiveAccount(acc2,acc.getAccount());
		
		return "activeAccount";
	}
	
	
}

