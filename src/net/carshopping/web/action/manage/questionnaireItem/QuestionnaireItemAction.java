/** * 文件名：QuestionnaireItemAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.questionnaireItem;
import net.carshopping.core.BaseAction;
import net.carshopping.services.manage.questionnaireItem.QuestionnaireItemService;
import net.carshopping.services.manage.questionnaireItem.bean.QuestionnaireItem;
/** 项目名称：carShopping 
 * 类名称：QuestionnaireItemAction 问卷调查 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:10:55 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:10:55 
 * 修改备注：
 * @version 1.0* */

@Deprecated
public class QuestionnaireItemAction extends BaseAction<QuestionnaireItem> {
	private static final long serialVersionUID = 1L;
	private QuestionnaireItemService questionnaireItemService;

	public QuestionnaireItemService getQuestionnaireItemService() {
		return questionnaireItemService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("questionnaireItem!selectList.action");
	}

	public void setQuestionnaireItemService(
			QuestionnaireItemService questionnaireItemService) {
		this.questionnaireItemService = questionnaireItemService;
	}

	public QuestionnaireItem getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new QuestionnaireItem();
		}
	}

	public void insertAfter(QuestionnaireItem e) {
		e.clear();
	}
}

