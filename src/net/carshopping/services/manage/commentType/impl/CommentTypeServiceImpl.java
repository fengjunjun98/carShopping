package net.carshopping.services.manage.commentType.impl;import net.carshopping.core.ServersManager;import net.carshopping.services.manage.commentType.CommentTypeService;import net.carshopping.services.manage.commentType.bean.CommentType;import net.carshopping.services.manage.commentType.dao.CommentTypeDao;public class CommentTypeServiceImpl extends ServersManager<CommentType>		implements CommentTypeService {	private CommentTypeDao commentTypeDao;	public void setCommentTypeDao(CommentTypeDao commentTypeDao) {		this.commentTypeDao = commentTypeDao;	}		@Override	public int update(CommentType e) {		commentTypeDao.updateAllN();		super.update(e);		return 1;	}}