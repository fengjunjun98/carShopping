/** * 文件名：CommentAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.comment;
import net.jeeshop.core.BaseAction;
import net.jeeshop.core.ManageContainer;
import net.jeeshop.services.manage.comment.CommentService;
import net.jeeshop.services.manage.comment.bean.Comment;

import org.apache.commons.lang.StringUtils;

/** 项目名称：carShopping 
 * 类名称：CommentAction 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:40:44 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:40:44 
 * 修改备注：
 * @version 1.0* */

public class CommentAction extends BaseAction<Comment> {
	private static final long serialVersionUID = 1L;
	private CommentService commentService;

	public CommentService getCommentService() {
		return commentService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("comment!selectList.action");
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public Comment getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Comment();
		}
		super.initPageSelect();
	}

	public void insertAfter(Comment e) {
		e.clear();
	}
	
	/**
	 * 设置选中的评论显示
	 * @return
	 * @throws Exception
	 */
	public String updateStatusY() throws Exception{
		if(getIds()!=null && getIds().length>0){
			commentService.updateStatus(getIds(),Comment.comment_status_y);
		}
		return selectList();
	}

	/**
	 * 设置选中的评论不显示
	 * @return
	 * @throws Exception
	 */
	public String updateStatusN() throws Exception{
		if(getIds()!=null && getIds().length>0){
			commentService.updateStatus(getIds(),Comment.comment_status_n);
		}
		return selectList();
	}
	
	/**
	 * 回复用户评论
	 * @return
	 * @throws Exception
	 */
	public String updateReply() throws Exception{
		if(StringUtils.isBlank(e.getReply()) || StringUtils.isBlank(e.getId())){
			throw new NullPointerException(ManageContainer.OrderAction_param_null);
		}
//		String update = getRequest().getParameter("update");//y:修改回复
//		if(update.equals("y")){
//			Comment ee = commentService.selectById(e.getId());
//			if(ee==null){
//				throw new NullPointerException("查询不到评论信息!");
//			}
//			
//			ee.setReply(e.getReply());
//		}
		
		Comment c = new Comment();
		c.setId(e.getId());
		c.setReply(e.getReply());
		commentService.update(c);
		
//		return selectList();
		return super.selectAllList;
	}
	
	@Override
	public String selectList() throws Exception {
		return super.selectList();
	}
	
	@Override
	protected void setParamWhenInitQuery() {
		super.setParamWhenInitQuery();
		String selectCommentFromIndex = getRequest().getParameter("selectCommentFromIndex");
		if(StringUtils.isNotBlank(selectCommentFromIndex)){
			e.setSelectCommentFromIndex(Boolean.valueOf(selectCommentFromIndex));
		}
	}
}
