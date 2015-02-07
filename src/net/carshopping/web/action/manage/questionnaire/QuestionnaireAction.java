/** * 文件名：QuestionnaireAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.questionnaire;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import net.carshopping.core.BaseAction;
import net.carshopping.core.ManageContainer;
import net.carshopping.core.system.bean.User;
import net.carshopping.services.manage.questionnaire.QuestionnaireService;
import net.carshopping.services.manage.questionnaire.bean.Questionnaire;
import net.carshopping.services.manage.questionnaireItem.QuestionnaireItemService;
import net.carshopping.services.manage.questionnaireItem.bean.QuestionnaireItem;
import net.carshopping.web.action.manage.order.OrderAction;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

/** 项目名称：carShopping 
 * 类名称：QuestionnaireAction  问卷调查
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:10:04 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:10:04 
 * 修改备注：
 * @version 1.0* */

public class QuestionnaireAction extends BaseAction<Questionnaire> {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OrderAction.class);
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
		super.initPageSelect();
	}

	public void insertAfter(Questionnaire e) {
		e.clear();
	}
	
	@Override
	public String selectList() throws Exception {
		try {
			super.selectList();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return super.selectList();
	}
	
	/**
	 * 新增问卷
	 */
	@Override
	public String insert() throws Exception {
//		super.insertCheck();
//		try {
			User user = (User) getSession().getAttribute(ManageContainer.manage_session_user_info);
			if(user==null){
				throw new NullPointerException();
			}
			e.setCreateAccount(user.getUsername());
			questionnaireService.insert(e);
			getResponse().sendRedirect(getEditUrl(e.getId()));
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
		return null;
	}
	
	/**
	 * 修改问卷
	 */
	@Override
	public String update() throws Exception {
		User user = (User) getSession().getAttribute(ManageContainer.manage_session_user_info);
		if(user==null){
			throw new NullPointerException();
		}
		e.setUpdateAccount(user.getUsername());
		questionnaireService.update(e);
		getResponse().sendRedirect(getEditUrl(e.getId()));
		return null;
	}
	
	/**
	 * 保存题目
	 * @return
	 * @throws IOException 
	 */
	public String insertQuestionItem() throws Exception{
		logger.error("insertQuestionItem...id = " + e.getId());
		logger.error("insertQuestionItem = "+e.getQuestionnaireItem());
		logger.error("e.getQuestionnaireItem()="+e.getQuestionnaireItem().getType());
		
		e.getQuestionnaireItem().setQid(e.getId());
		questionnaireItemService.insertList(e.getQuestionnaireItem(),e.getQuestionnaireItem().getItemList(e.getId()));
		
		String id = e.getId();
		e.clear();
		e.setId(id);
		getResponse().sendRedirect(getEditUrl(e.getId()));
		return null;
	}
	
	/**
	 * 删除选择的题目
	 * @return
	 * @throws IOException 
	 */
	public String deleteItem() throws Exception{
		try {
			questionnaireItemService.deletes(ids);
			getResponse().sendRedirect(getEditUrl(e.getId()));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}
	
	private String getEditUrl(String id){
		if(StringUtils.isBlank(id)){
			throw new NullPointerException("id is null!");
		}
		return "questionnaire!toEdit2.action?e.id="+id;
	}
	
	/**
	 * 添加或编辑后程序回转编辑
	 * @return
	 * @throws Exception
	 */
	public String toEdit2() throws Exception {
		logger.error("toEdit2...");
		try {
			toEdit0();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return toEdit;
	}
	
	//列表页面点击 进入编辑页面
	public String toEdit() throws Exception {
		logger.error("toEdit...");
		getSession().setAttribute("insertOrUpdateMsg", "");
		try {
			toEdit0();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return toEdit;
	}
	
	/**
	 * 根据问卷ID等信息加载指定的问卷信息
	 * @return
	 * @throws Exception
	 */
	private String toEdit0() throws Exception {
		if(StringUtils.isBlank(e.getId())){
			throw new NullPointerException("问卷ID不能为空！");
		}
		
		boolean editQuestionnaireItem = e.isEditQuestionnaireItem();//是否是编辑题目模式
		boolean addQuestionnaireItem = e.isAddQuestionnaireItem();//是否是添加题目模式
		String qItemID = e.getQuestionnaireItem().getId();//题目ID
		logger.error("qItemID=" + qItemID +",editQuestionnaireItem="+editQuestionnaireItem);
		
		e = questionnaireService.selectById(e.getId());
		if(e==null){
			throw new NullPointerException("根据问卷ID查询不到问卷信息！");
		}
		
		//加载问卷题目列表
		QuestionnaireItem qItem = new QuestionnaireItem();
		qItem.setQid(e.getId());
		
		List<QuestionnaireItem> list = questionnaireItemService.selectList(qItem);
		if(list!=null && list.size()>0){
			if(e.getQuestionnaireItemMap()==null){
				e.setQuestionnaireItemMap(new HashMap<String, QuestionnaireItem>());
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
		
//		logger.error("e.getQuestionnaireItemMap() = " + e.getQuestionnaireItemMap());
		
		//如果是编辑题目模式，则加载指定的题目
		if(editQuestionnaireItem && StringUtils.isNotBlank(qItemID)){
			QuestionnaireItem qItemObj = questionnaireItemService.selectById(qItemID);
			
			qItem.clear();
			qItem.setQid(qItemObj.getQid());
			qItem.setSubject(qItemObj.getSubject());
			list = questionnaireItemService.selectList(qItem);
			//对查询出的选项数据进行处理，以便页面能正确显示
			if(list!=null && list.size()>0){
				e.setQuestionnaireItem(list.get(0));
				if(e.getQuestionnaireItem().getOptionList()==null){
					e.getQuestionnaireItem().setOptionList(new LinkedList<String>());
				}else{
					e.getQuestionnaireItem().getOptionList().clear();
				}
				for(int i=0;i<list.size();i++){
					e.getQuestionnaireItem().getOptionList().add(list.get(i).getOption1());
				}
			}else{
				throw new NullPointerException("数据异常！");
			}
			
			e.setEditQuestionnaireItem(editQuestionnaireItem);//最后设置下当前为题目编辑模式
		}
		
		e.setAddQuestionnaireItem(addQuestionnaireItem);

		return toEdit;
	}
	
	/**
	 * 编辑题目
	 * @return
	 * @throws IOException 
	 */
	@Deprecated
	public String toEditItem() throws Exception{
		logger.error("toEditItem...");
		if(StringUtils.isBlank(e.getId()) || StringUtils.isBlank(e.getQuestionnaireItem().getId())){
			throw new NullPointerException("缺少相关参数");
		}
		
		e.setEditQuestionnaireItem(true);//设置为题目编辑模式
		QuestionnaireItem qItem = questionnaireItemService.selectById(e.getQuestionnaireItem().getId());
		if(qItem==null){
			throw new NullPointerException("根据题目ID查询不到题目！");
		}
		
		e.setQuestionnaireItem(qItem);
		
		getResponse().sendRedirect(getEditUrl(e.getId()));
		return null;
	}
	
	/**
	 * 同步缓存内的新闻
	 * 审核通过，记录将会出现在门户上
	 * @return
	 * @throws Exception
	 */
	public String setStatusY() throws Exception {
		questionnaireService.changeStatus(getIds(),Questionnaire.questionnaire_status_y);
		return super.selectList();
	}
	
	/**
	 * 审核未通过,记录将不会出现在门户上
	 * @return
	 * @throws Exception
	 */
	public String setStatusN() throws Exception {
		questionnaireService.changeStatus(getIds(),Questionnaire.questionnaire_status_n);
		return super.selectList();
	}
}

