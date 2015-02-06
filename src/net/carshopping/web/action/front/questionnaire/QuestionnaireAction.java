/** * 文件名：QuestionnaireAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.front.questionnaire;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import net.jeeshop.core.BaseAction;
import net.jeeshop.core.FrontContainer;
import net.jeeshop.services.front.questionnaire.QuestionnaireService;
import net.jeeshop.services.front.questionnaire.bean.Questionnaire;
import net.jeeshop.services.front.questionnaireItem.QuestionnaireItemService;
import net.jeeshop.services.front.questionnaireItem.bean.QuestionnaireItem;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
/** 项目名称：carShopping 
 * 类名称：QuestionnaireAction
 * 问卷调查 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:23:30 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:23:30 
 * 修改备注：
 * @version 1.0* */

public class QuestionnaireAction extends BaseAction<Questionnaire> {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(QuestionnaireAction.class);
	private static final long serialVersionUID = 1L;
	private QuestionnaireService questionnaireService;
	private QuestionnaireItemService questionnaireItemService;

	public void setQuestionnaireItemService(
			QuestionnaireItemService questionnaireItemService) {
		this.questionnaireItemService = questionnaireItemService;
	}
	
	public void setQuestionnaireService(
			QuestionnaireService questionnaireService) {
		this.questionnaireService = questionnaireService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("questionnaire!selectList.action");
	}

	public Questionnaire getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Questionnaire();
		}
		
		setSelectMenu(FrontContainer.not_select_menu);//设置主菜单为不选中
	}

	public void insertAfter(Questionnaire e) {
		e.clear();
	}
	
	private String getEditUrl(String id){
		return "questionnaire!toEdit2.action?e.id="+id;
	}
	
	/**
	 * 查看问卷
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
		String id = getRequest().getParameter("id");
		
		logger.error("QuestionnaireAction show id = " + id);
		if(StringUtils.isBlank(id)){
			throw new NullPointerException("问卷ID不能为空！");
		}
		
		e = questionnaireService.selectById(id);
		if(e==null){
			throw new NullPointerException("根据问卷ID查询不到问卷信息！");
		}
		
		//加载问卷题目列表
		QuestionnaireItem qItem = new QuestionnaireItem();
		qItem.setQid(e.getId());
		
		List<QuestionnaireItem> list = questionnaireItemService.selectList(qItem);
		if(list!=null && list.size()>0){
			if(e.getQuestionnaireItemMap()==null){
				e.setQuestionnaireItemMap(new LinkedHashMap<String, QuestionnaireItem>());
			}
			
			for(int i=0;i<list.size();i++){
				QuestionnaireItem item = list.get(i);
				
				QuestionnaireItem mapItem = e.getQuestionnaireItemMap().get(item.getSubject());
				if(mapItem==null){
					if(item.getOptionList()==null){
						item.setOptionList(new LinkedList<String>());
					}
					item.getOptionList().add(item.getOption1());
					e.getQuestionnaireItemMap().put(item.getSubject(),item);
				}else{
					mapItem.getOptionList().add(item.getOption1());//为指定的题目添加选项
				}
			}
		}
		
		logger.error("e.getQuestionnaireItemMap() = " + e.getQuestionnaireItemMap());
		return "show";
	}
	
	/**
	 * 用户提交问卷
	 * @return
	 * @throws IOException 
	 */
	public String submitQuestion() throws IOException{
		logger.error("submitQuestion...");
		
//		getResponse().sendRedirect("success.html");
//		return null;
		return SUCCESS;
	}
		
}

