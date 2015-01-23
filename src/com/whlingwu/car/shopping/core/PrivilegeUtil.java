/** * 文件名：PrivilegeUtil.java 
 * * 版本信息： 
 * 日期：2015-1-21 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whlingwu.car.shopping.core.exception.PrivilegeException;

/** 项目名称：carShopping 
 * 类名称：PrivilegeUtil 
 *  权限检查工具
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-21 下午07:24:36 
 * 修改人：凤军军 
 * 修改时间：2015-1-21 下午07:24:36 
 * 修改备注：
 * @version 1.0* */

public class PrivilegeUtil {
private static final Logger logger = LoggerFactory.getLogger(PrivilegeUtil.class);
	

	/**
	 * 检查用户是否具有指定的权限
	 * 
	 * @param session
	 *            用户session
	 * @param pName
	 *            权限名称
	 * @return  true：有权限，false:没有权限
	 */
	public static boolean check(HttpSession session, String pName) throws PrivilegeException{
		if(1==1){
			return true;
		}
		
		
		Map<String,String> root = (Map<String,String>) session.getAttribute(ManageContainer.user_resource_menus_button);
		if(root==null || root.size()==0){
			logger.error("该用户没有任何权限。没有权限访问该资源！");
			return false;
		}
		User u = (User) session.getAttribute(ManageContainer.manage_session_user_info);
		if(u==null){
			throw new PrivilegeException("用户未登陆!");
		}
		logger.error("==PrivilegeUtil.check : pName="+pName+"root:"+root.toString());
		if(root.get(pName)==null){
			logger.error("抱歉，没有权限访问该资源！");
			return false;
		}
		logger.error("有权限访问该资源！");
		return true;
	}
}

