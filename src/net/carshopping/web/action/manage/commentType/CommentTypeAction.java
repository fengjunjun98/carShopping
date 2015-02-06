/** * 文件名：CommentTypeAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.commentType;
import net.jeeshop.core.BaseAction;
import net.jeeshop.core.KeyValueHelper;
import net.jeeshop.core.ManageContainer;
import net.jeeshop.core.exception.NotThisMethod;
import net.jeeshop.services.manage.comment.bean.Comment;
import net.jeeshop.services.manage.commentType.CommentTypeService;
import net.jeeshop.services.manage.commentType.bean.CommentType;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/** 项目名称：carShopping 
 * 类名称：CommentTypeAction 评论方式
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:41:22 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:41:22 
 * 修改备注：
 * @version 1.0* */

public class CommentTypeAction extends BaseAction<CommentType> {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CommentTypeAction.class);
	private CommentTypeService commentTypeService;

	public CommentTypeService getCommentTypeService() {
		return commentTypeService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("commentType!selectList.action");
	}

	public void setCommentTypeService(CommentTypeService commentTypeService) {
		this.commentTypeService = commentTypeService;
	}

	public CommentType getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new CommentType();
		}
	}

	public void insertAfter(CommentType e) {
		e.clear();
	}
	
	@Override
	public String insert() throws Exception {
		comm();
		return super.insert();
	}
	
	@Override
	public String update() throws Exception {
		comm();
		return super.update();
	}
	
	//根据code获取名称
	private void comm() {
		logger.error("comm..code="+e.getCode());
		String name = KeyValueHelper.get("commentType_code_"+e.getCode());
		if(StringUtils.isBlank(name)){
			throw new NullPointerException("未配置"+e.getCode()+"的评论插件的键值对！");
		}
		
		e.setName(name);
	}
	
	@Override
	public String deletes() throws Exception {
		throw new NotThisMethod(ManageContainer.not_this_method);
	}
	
	/**
	 * 设置指定的评论未系统默认评论插件
	 * @return
	 * @throws Exception 
	 */
	public String updateDefaultCommentType() throws Exception{
		if(StringUtils.isBlank(e.getId())){
			throw new NullPointerException("非法请求！");
		}
		
		CommentType comm = new CommentType();
		comm.setId(e.getId());
		comm.setStatus(Comment.comment_status_y);
		commentTypeService.update(comm);
		
		return selectList();
//		return "updateDefaultCommentType";
	}
}

