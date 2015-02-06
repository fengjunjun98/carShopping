/** * 文件名：Questionnaire.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.carshopping.core.dao.page.PagerModel;

/** 项目名称：carShopping 
 * 类名称：Questionnaire 问卷对象
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:41:54 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:41:54 
 * 修改备注：
 * @version 1.0* */

public class Questionnaire extends PagerModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String title2;
	private String createdate;
	private String updatedate;
	private String createAccount;
	private String updateAccount;
	private String status;
	private String showStartDate;
	private String showEndDate;
	
	private QuestionnaireItem questionnaireItem = new QuestionnaireItem();//问卷题目编辑对象
	
	private Map<String,QuestionnaireItem> questionnaireItemMap;//问卷题目列表
	
	public void clear() {
		super.clear();
		id = null;
		title = null;
		title2 = null;
		createdate = null;
		updatedate = null;
		createAccount = null;
		updateAccount = null;
		status = null;
		showStartDate = null;
		showEndDate = null;
		questionnaireItem.clear();
		
		if(questionnaireItemMap!=null && questionnaireItemMap.size()>0){
			for(Iterator<Entry<String, QuestionnaireItem>> it = questionnaireItemMap.entrySet().iterator();it.hasNext();){
				it.next().getValue().clear();
			}
			questionnaireItemMap.clear();
			questionnaireItemMap = null;
		}
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

	public String getTitle2() {
		return title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getCreateAccount() {
		return createAccount;
	}

	public void setCreateAccount(String createAccount) {
		this.createAccount = createAccount;
	}

	public String getUpdateAccount() {
		return updateAccount;
	}

	public void setUpdateAccount(String updateAccount) {
		this.updateAccount = updateAccount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShowStartDate() {
		return showStartDate;
	}

	public void setShowStartDate(String showStartDate) {
		this.showStartDate = showStartDate;
	}

	public String getShowEndDate() {
		return showEndDate;
	}

	public void setShowEndDate(String showEndDate) {
		this.showEndDate = showEndDate;
	}

	public QuestionnaireItem getQuestionnaireItem() {
		return questionnaireItem;
	}

	public void setQuestionnaireItem(QuestionnaireItem questionnaireItem) {
		this.questionnaireItem = questionnaireItem;
	}

	public Map<String, QuestionnaireItem> getQuestionnaireItemMap() {
		return questionnaireItemMap;
	}

	public void setQuestionnaireItemMap(
			Map<String, QuestionnaireItem> questionnaireItemMap) {
		this.questionnaireItemMap = questionnaireItemMap;
	}

}
