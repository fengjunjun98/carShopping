/** * 文件名：FreemarkerAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.front.freemarker;
import java.io.IOException;

import net.carshopping.core.BaseAction;
import net.carshopping.core.freemarker.front.FreemarkerHelper;
import net.jeeshop.services.front.account.bean.Account;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
/** 项目名称：carShopping 
 * 类名称：FreemarkerAction 
 * 网站会员action
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:17:49 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:17:49 
 * 修改备注：
 * @version 1.0* */

public class FreemarkerAction extends BaseAction<Account> {
	private static final Logger logger = Logger.getLogger(FreemarkerAction.class);
	private static final long serialVersionUID = 1L;
	private FreemarkerHelper freemarkerHelper;

	public FreemarkerHelper getFreemarkerHelper() {
		return freemarkerHelper;
	}

	public void setFreemarkerHelper(FreemarkerHelper freemarkerHelper) {
		this.freemarkerHelper = freemarkerHelper;
	}

	private String error;// 错误消息

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public Account getE() {
		return this.e;
	}

	@Override
	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Account();
		}
	}

	public String toIndex() {
		return SUCCESS;
	}

	@Override
	public void insertAfter(Account e) {
	}

	@Override
	protected void selectListAfter() {
	}
	
	/**
	 * 生成门户菜单
	 * 
	 * @return
	 * @throws IOException 
	 */
//	@Deprecated
//	public String indexMenu() {
//		freemarkerHelper.index("indexMenu.ftl", "indexMenu.jsp");
//		return SUCCESS;
//	}
	
	/**
	 * 对商品的详情内容、帮助文章的内容、公告、新闻等的内容进行静态化处理。
	 * 方法是将这些大文本的内容存储未jsp的文件格式，将来访问页面的时候直接把地址链接进去就行了。
	 * @return
	 * @throws Exception
	 */
	public String create() throws Exception{
		String method = getRequest().getParameter("method");
		logger.error("create method = " + method);
		if(StringUtils.isBlank(method)){
			
		}else if(method.equals("helps")){
			freemarkerHelper.helps();//所有帮助文件静态化
		}else if(method.equals("notices")){
			freemarkerHelper.notices();//所有公告通知静态化
		}else if(method.equals("products")){
			String error = freemarkerHelper.products();//所有商品描述静态化
			if(error==null){
				getResponse().getWriter().write("success");
			}else{
				getResponse().getWriter().write("部分商品静态化失败，商品ID："+error);
			}
			return null;
		}else if(method.equals("staticProductByID")){
			String id = getRequest().getParameter("id");
			
			String response = freemarkerHelper.staticProductByID(id);//所有商品描述静态化
			getResponse().setCharacterEncoding("utf-8");
			getResponse().getWriter().write(response);
			return null;
		}else if(method.equals("staticNewsByID")){
			String id = getRequest().getParameter("id");
			
			String response = freemarkerHelper.staticNewsByID(id);//所有商品描述静态化
			getResponse().setCharacterEncoding("utf-8");
			getResponse().getWriter().write(response);
			return null;
		}
		
		getResponse().getWriter().write("success");
		return null;
//		return SUCCESS;
	}
	
//	public String helps(){
//		String method = getRequest().getParameter("method");
//		if(StringUtils.isBlank(method)){
//			
//		}else if(method.equals("staticNews")){
//			//生成所有的文章静态页面
//			freemarkerHelper.staticNews();
//		}else if(method.equals("staticNews")){
//			//对单个文章静态化
//			String id = getRequest().getParameter("id");
//			freemarkerHelper.staticNewsInfo(id);
//		}
//		return SUCCESS;
//	}

	/**
	 * 一键生成
	 * 
	 * @return
	 */
//	@Deprecated
//	public String createAll() {
//		indexMenu();
//		return SUCCESS;
//	}
}

