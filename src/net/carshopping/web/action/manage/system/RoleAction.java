/** * 文件名：RoleAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.system;
import net.carshopping.core.BaseAction;
import net.carshopping.core.ManageContainer;
import net.carshopping.core.Services;
import net.carshopping.core.exception.NotThisMethod;
import net.carshopping.core.system.bean.Role;
import net.carshopping.core.system.bean.User;
import net.carshopping.services.manage.system.impl.MenuService;
import net.carshopping.services.manage.system.impl.RoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.common.comm.ServiceClient.Request;
/** 项目名称：carShopping 
 * 类名称：RoleAction 角色
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:15:29 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:15:29 
 * 修改备注：
 * @version 1.0* */

public class RoleAction extends BaseAction<Role> {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RoleAction.class);
	private RoleService roleService;
	private MenuService menuService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	private Role role = new Role();

	/**
	 * 添加角色
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		Role r = new Role();
		r.setRole_name(getRequest().getParameter("roleName"));
		r.setId(getRequest().getParameter("id"));
		r.setRole_desc(getRequest().getParameter("roleDesc"));
		r.setRole_dbPrivilege(getRequest().getParameter("role_dbPrivilege"));
		r.setPrivileges(getRequest().getParameter("privileges"));
		r.setStatus(getRequest().getParameter("status"));
		if(r.getRole_name()==null || r.getRole_name().trim().equals("")){
			getResponse().getWriter().print("0");
			return null;
		}else{
			roleService.editRole(r,getRequest().getParameter("insertOrUpdate"));
		}
		
		getResponse().getWriter().write("1");
		return null;
	}
	
	@Override
	public String deletes() throws Exception {
		throw new NotThisMethod(ManageContainer.not_this_method);
	}

	/**
	 * 批量删除角色和角色下的所有权限
	 * @return
	 * @throws Exception
	 */
//	public String delet() throws Exception {
//		logger.error("role.delete...");
//		throw new NotThisMethod(ManageContainer.not_this_method);
////		if(getIds()!=null && getIds().length>0){
////			for(int i=0;i<getIds().length;i++){
////				if(getIds()[i].equals("1")){
////					throw new Exception("超级管理员不可删除！");
////				}
////			}
////			roleService.deletes(getIds());
////		}
////		return selectList();
//	}

	// getter setter
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public Role getE() {
		// TODO Auto-generated method stub
		return this.role;
	}

	@Override
	public Services<Role> getServer() {
		// TODO Auto-generated method stub
		return this.roleService;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		String id = getRequest().getParameter("id");
		if (id==null || id.trim().equals("")){
			role.clear();
			role.setInsertOrUpdate("1");
		}
		else{
			role.clear();
			role.setId(id);
			role = roleService.selectOne(role);
			if (role == null) {
				role = new Role();
			}
			role.setInsertOrUpdate("2");
		}
		
		if(e==null){
			e = new Role();
		}
	}

	@Override
	public void insertAfter(Role e) {
		e.clear();
	}
	@Override
	protected void selectListAfter() {
		pager.setPagerUrl("role!selectList.action");
	}
	
	/**
	 * 只能是admin才具有编辑其他用户权限的功能
	 */
	@Override
	public String update() throws Exception {
		User user = (User)getRequest().getSession().getAttribute(ManageContainer.manage_session_user_info);
		if(!user.getUsername().equals("admin")){
			throw new NullPointerException(ManageContainer.RoleAction_update_error);
		}
		return super.update();
	}
}
