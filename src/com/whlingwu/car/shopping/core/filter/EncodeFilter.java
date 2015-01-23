/** * 文件名：EncodeFilter.java 
 * * 版本信息： 
 * 日期：2015-1-21 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/** 项目名称：carShopping 
 * 类名称：EncodeFilter 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-21 下午07:42:49 
 * 修改人：凤军军 
 * 修改时间：2015-1-21 下午07:42:49 
 * 修改备注：
 * @version 1.0* */

public class EncodeFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
