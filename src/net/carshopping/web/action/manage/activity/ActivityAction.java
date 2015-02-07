/** * 文件名：ActivityAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.activity;
import net.carshopping.core.BaseAction;
import net.carshopping.core.util.DateTimeUtil;
import net.carshopping.services.manage.activity.ActivityService;
import net.carshopping.services.manage.activity.bean.Activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/** 项目名称：carShopping 
 * 类名称：ActivityAction 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:34:59 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:34:59 
 * 修改备注：
 * @version 1.0* */

public class ActivityAction extends BaseAction<Activity> {
	private static final long serialVersionUID = 1L;
	private ActivityService activityService;
	private String[] selectAccountRange;
	private static final Logger logger = LoggerFactory.getLogger(ActivityAction.class);

	public String[] getSelectAccountRange() {
		return selectAccountRange;
	}

	public void setSelectAccountRange(String[] selectAccountRange) {
		this.selectAccountRange = selectAccountRange;
	}

	public ActivityService getActivityService() {
		return activityService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("activity!selectList.action");
	}

	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	public Activity getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Activity();
		}
	}

	public void insertAfter(Activity e) {
		e.clear();
	}
	
	@Override
	public String toAdd() throws Exception {
		
		if(selectAccountRange!=null){
			for(int i=0;i<selectAccountRange.length;i++){
				selectAccountRange[i] = null;
			}
			selectAccountRange = null;
		}
		return super.toAdd();
	}
	
	@Override
	public String toEdit() throws Exception {
		e = getServer().selectOne(getE());
		selectAccountRange = e.getAccountRange().split(",");
		for(int i=0;i<selectAccountRange.length;i++){
			//去除因struts2控件提交导致的空格
			selectAccountRange[i]  = selectAccountRange[i].trim();
			logger.error("selectAccountRange[i]="+selectAccountRange[i]);
		}
		return toEdit;
	}
	
	@Override
	public String selectList() throws Exception {
		super.selectList();
		
		if(getPager()!=null && getPager().getList()!=null && getPager().getList().size()>0){
			for(int i=0;i<getPager().getList().size();i++){
				Activity activity = (Activity) getPager().getList().get(i);
				
				activity.setExpire(activity.checkActivity());
				if(!activity.isExpire()){
					//计算活动多久结束，是否已结束
					activity.setActivityEndDateTime(DateTimeUtil.getActivityEndDateTimeString(activity.getEndDate()));					
				}
			}
		}
		
		return toList;
	}
}

