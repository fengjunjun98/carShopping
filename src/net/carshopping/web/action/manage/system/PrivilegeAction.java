/** * 文件名：PrivilegeAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.system;
import java.util.List;

import net.carshopping.core.BaseAction;
import net.carshopping.core.Services;
import net.carshopping.core.system.bean.Privilege;
import net.carshopping.services.manage.system.impl.PrivilegeService;
/** 项目名称：carShopping 
 * 类名称：PrivilegeAction 权限管理
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:16:04 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:16:04 
 * 修改备注：
 * @version 1.0* */

public class PrivilegeAction extends BaseAction<Privilege> {//implements ModelDriven<Role>{
	private static final long serialVersionUID = 1L;
	private PrivilegeService privilegeService;
	private List privilegeList;

	
	private Privilege privilege = new Privilege();

	public String delete() throws Exception {
		this.privilegeService.delete(privilege);
		return SUCCESS;
	}

	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}

	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}

	public List getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List privilegeList) {
		this.privilegeList = privilegeList;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	@Override
	public Privilege getE() {
		// TODO Auto-generated method stub
		return this.privilege;
	}

	@Override
	public Services<Privilege> getServer() {
		// TODO Auto-generated method stub
		return this.privilegeService;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertAfter(Privilege e) {
		// TODO Auto-generated method stub
		e.clear();
	}
	@Override
	protected void selectListAfter() {
		pager.setPagerUrl("privilege!selectList.action");
	}
}
