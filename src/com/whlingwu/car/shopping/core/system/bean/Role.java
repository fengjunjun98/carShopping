/** * 文件名：Role.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.system.bean;

import java.io.Serializable;

import com.whlingwu.car.shopping.core.dao.page.PagerModel;

/** 项目名称：carShopping 
 * 类名称：Role 
 * 角色
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午10:36:32 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午10:36:32 
 * 修改备注：
 * @version 1.0* */

public class Role extends PagerModel implements Serializable {
	private String id;
	private String role_name;
	private String role_desc;
	private String role_dbPrivilege;
	private String status;
	
	public static final String role_status_y = "y";//启用
	public static final String role_status_n = "n";//禁用
	
	private String privileges;
	private String insertOrUpdate = "1";// 1:新增,2:修改
	
	public void clear() {
		id = null;
		role_name = null;
		role_desc = null;
		role_dbPrivilege = null;
		status = null;
		
		privileges = null;
		insertOrUpdate = null;
	}

	public String getRole_dbPrivilege() {
		return role_dbPrivilege;
	}

	public void setRole_dbPrivilege(String role_dbPrivilege) {
		this.role_dbPrivilege = role_dbPrivilege;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_desc() {
		return role_desc;
	}

	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}

	public String getPrivileges() {
		return privileges;
	}

	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}

	public String getInsertOrUpdate() {
		return insertOrUpdate;
	}

	public void setInsertOrUpdate(String insertOrUpdate) {
		this.insertOrUpdate = insertOrUpdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
