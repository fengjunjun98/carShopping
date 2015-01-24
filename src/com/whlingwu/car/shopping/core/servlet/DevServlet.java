/** * 文件名：DevServlet.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 项目名称：carShopping 
 * 类名称：DevServlet 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午10:30:51 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午10:30:51 
 * 修改备注：
 * @version 1.0* */

@Deprecated
public class DevServlet extends HttpServlet {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)throws ServletException, IOException {
		arg0.setCharacterEncoding("utf-8");
		arg1.setCharacterEncoding("utf-8");
		String devPath = DevServlet.class.getResource("/").getFile()
				+ "/开发日志.txt";
		
		FileReader r = new FileReader(devPath);
//		BufferedReader reader = new BufferedReader(new InputStreamReader(
//				new FileInputStream(devPath)));
		
		BufferedReader reader = new BufferedReader(r);
		
//		StringBuilder buff = new StringBuilder();
		String s = "";
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}
//			buff.append(line);
			s+=line;
		}
		System.out.println(s);
		System.out.println(new String(s.getBytes("utf-8")));
//		buff.
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		ByteArrayInputStream in = new ByteArrayInputStream(buff.toString().getBytes());

		arg1.getWriter().write(s);
		reader.close();
	}
}
