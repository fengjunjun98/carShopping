/** * 文件名：AdvertAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.advert;
import net.carshopping.core.BaseAction;
import net.carshopping.services.manage.advert.AdvertService;
import net.carshopping.services.manage.advert.bean.Advert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 项目名称：carShopping 
 * 类名称：AdvertAction 
 * 广告管理
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:35:43 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:35:43 
 * 修改备注：
 * @version 1.0* */

public class AdvertAction extends BaseAction<Advert> {
	private static final Logger logger = LoggerFactory.getLogger(AdvertAction.class);
	private static final long serialVersionUID = 1L;
	private AdvertService advertService;

	public AdvertService getAdvertService() {
		return advertService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("advert!selectList.action");
	}

	public void setAdvertService(AdvertService advertService) {
		this.advertService = advertService;
	}

	public Advert getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Advert();
		}
		super.initPageSelect();
	}

	public void insertAfter(Advert e) {
		e.clear();
	}
	
	@Override
	public String insert() throws Exception {
		logger.error(">>>AdvertAction.insert");
		getSession().setAttribute("insertOrUpdateMsg", "添加广告成功！");
		getServer().insert(getE());
		String id = e.getId();
		logger.error(">>>AdvertAction.insert ,id = "+id);
		e.clear();
		getResponse().sendRedirect("advert!toEdit2.action?e.id="+id);
		return null;
	}
	
	@Override
	public String update() throws Exception {
		getSession().setAttribute("insertOrUpdateMsg", "更新广告成功！");
		String id = e.getId();
		super.update();
		getResponse().sendRedirect("advert!toEdit2.action?e.id="+id);
		return null;
	}
	
	/**
	 * 编辑
	 */
	public String toEdit() throws Exception {
		getSession().setAttribute("insertOrUpdateMsg", "");//列表页面进行编辑文章的时候,需要清空信息
		return toEdit0();
	}
	
	/**
	 * 编辑
	 */
	public String toEdit2() throws Exception {
		return toEdit0();
	}
	
	private String toEdit0() throws Exception {
		super.toEdit();
		return toEdit;
	}
	
}
