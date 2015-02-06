/** * 文件名：TaskAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.task;

import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import net.carshopping.core.BaseAction;
import net.carshopping.core.TaskManager;
import net.jeeshop.services.manage.task.TaskService;
import net.jeeshop.services.manage.task.bean.Task;
/** 项目名称：carShopping 
 * 类名称：TaskAction 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:19:27 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:19:27 
 * 修改备注：
 * @version 1.0* */

public class TaskAction extends BaseAction<Task> {
	private static final long serialVersionUID = 1L;
	private TaskService taskService;
	private TaskManager taskManager;

	public void setTaskManager(TaskManager taskManager) {
		this.taskManager = taskManager;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("task!selectList.action");
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public Task getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Task();
		}
	}

	public void insertAfter(Task e) {
		e.clear();
	}
	
	@Override
	public String selectList() throws Exception {
		super.selectList();
		
		//获取每个任务的内存数据
		if(pager.getList()!=null){
			for(int i=0;i<pager.getList().size();i++){
				Task item = (Task) pager.getList().get(i);
				
				Task task = TaskManager.getTask(item.getCode());
				if(task!=null){
					item.setNextWorkTime(task.getNextWorkTime());
					
					if(task.getThread()!=null){
						item.setCurrentStatus(task.getThread().getState().name());
					}else{
						item.setCurrentStatus("已停止");
					}
				}
			}
		}
		
		return toList;
	}
	
	/**
	 * 立即执行一个任务
	 * @return
	 * @throws Exception 
	 */
	public String startTask() throws Exception{
		if(StringUtils.isBlank(e.getCode())){
			throw new NullPointerException("任务代号不能为空！");
		}
		
//		Task task = taskManager.getTask(e.getCode());
//		if(task==null){
//			throw new NullPointerException("系统中不存在此任务！");
//		}
		
//		task.getThread().
		return selectList();
	}

	/**
	 * 立即终止一个任务
	 * @return
	 * @throws Exception 
	 */
	public String stopTask() throws Exception{
		if(StringUtils.isBlank(e.getCode())){
			throw new NullPointerException("任务代号不能为空！");
		}
		
		return selectList();
	}
}
