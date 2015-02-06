/** * 文件名：Advert.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;

import java.io.Serializable;

import net.carshopping.core.dao.QueryModel;

/** 项目名称：carShopping 
 * 类名称：Advert 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:36:47 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:36:47 
 * 修改备注：
 * @version 1.0* */

public class Advert extends QueryModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String code;
	private String remark;
	private String html;
	private String startdate;
	private String enddate;
	private String status;//y:启用,n:禁用 ；默认启用
	private String useImagesRandom;//使用随机选择图集
	
	/**
	 * 广告启用、不启用状态
	 */
	public static final String advert_status_y = "y";//启用
	public static final String advert_status_n = "n";//不启用
	
	/**
	 * 广告是否使用随即图集
	 */
	public static final String advert_useImagesRandom_y = "y";//使用
	public static final String advert_useImagesRandom_n = "n";//不使用

	public void clear() {
		super.clear();
		id = null;
		title = null;
		code = null;
		remark = null;
		html = null;
		startdate = null;
		enddate = null;
		status = null;
		useImagesRandom = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUseImagesRandom() {
		return useImagesRandom;
	}

	public void setUseImagesRandom(String useImagesRandom) {
		this.useImagesRandom = useImagesRandom;
	}

}
