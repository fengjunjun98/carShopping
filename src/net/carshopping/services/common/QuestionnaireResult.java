/** * 文件名：QuestionnaireResult.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;

import java.io.Serializable;

import net.carshopping.core.dao.page.PagerModel;

/** 项目名称：carShopping 
 * 类名称：QuestionnaireResult 问卷调整结果
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:40:33 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:40:33 
 * 修改备注：
 * @version 1.0* */

public class QuestionnaireResult extends PagerModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String qid;
	private String account;
	private String qItemID;
	private String text;

	public void clear() {
		super.clear();
		id = null;
		qid = null;
		account = null;
		qItemID = null;
		text = null;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getqItemID() {
		return qItemID;
	}

	public void setqItemID(String qItemID) {
		this.qItemID = qItemID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
