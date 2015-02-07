/** * 文件名：NewsAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.front.news;
import net.carshopping.core.BaseAction;
import net.carshopping.core.FrontContainer;
import net.carshopping.services.front.news.NewsService;
import net.carshopping.services.front.news.bean.News;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/** 项目名称：carShopping 
 * 类名称：NewsAction 
 * 文章管理
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:18:48 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:18:48 
 * 修改备注：
 * @version 1.0* */

public class NewsAction extends BaseAction<News> {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(NewsAction.class);
	private NewsService newsService;
	private String helpCode;
	private News news;
	
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

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	@Override
	public News getE() {
		return this.e;
	}

	@Override
	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new News();
		}
	}

	@Override
	public void insertAfter(News e) {
	}

	@Override
	protected void selectListAfter() {
		pager.setPagerUrl("news!selectList.action");
	}

	/**
	 * 获取新闻详情
	 * @return
	 */
	public String newsInfo() throws Exception{
		
		super.setSelectMenu(FrontContainer.not_select_menu);//设置主菜单为不选中
		
		String id = getRequest().getParameter("id");
		logger.error("NewsAction.newsInfo=== id="+id);
		if(StringUtils.isBlank(id)){
			throw new NullPointerException("id is null");
		}
		
//		e = newsService.selectById(id);
		
		News newsParam = new News();
		newsParam.setId(id);
		e =newsService.selectSimpleOne(newsParam);
		if(e==null){
			throw new NullPointerException();
		}
		
		String url = "/jsp/notices/"+e.getId()+".jsp";
		logger.error("url = " + url);
		getRequest().setAttribute("newsInfoUrl",url);
		
		return "newsInfo";
	}
	
	/**
	 * 帮助中心
	 * @return
	 */
	public String help() throws Exception {
		
		super.setSelectMenu(FrontContainer.not_select_menu);//设置主菜单为不选中
		
		logger.error("this.helpCode="+this.helpCode);
		if(StringUtils.isBlank(this.helpCode)){
			throw new NullPointerException("helpCode参数不能为空");
		}else if(this.helpCode.equals("index")){
			return "help";
		}else{
			News newsParam = new News();
			newsParam.setCode(helpCode);
			news = newsService.selectSimpleOne(newsParam);
			if(news==null){
				throw new NullPointerException("根据code查询不到文章！");
			}
			
			String url = "/jsp/helps/"+news.getId()+".jsp";
			logger.error("url = " + url);
			getRequest().setAttribute("newsInfoUrl",url);
			
			return "help";
		}
	}
}
