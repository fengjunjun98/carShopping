/** * 文件名：TokenUtil.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.framework.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

/** 项目名称：carShopping 
 * 类名称：TokenUtil 
 * 防止表单重复提交工具类
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午11:06:35 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午11:06:35 
 * 修改备注：
 * @version 1.0* */

public class TokenUtil {
	private static final Logger logger = Logger.getLogger(TokenUtil.class);
	private static final TokenUtil instance = new TokenUtil();
	private Object checkTokenLock = new Object();
	private BASE64Encoder encoder = new BASE64Encoder();// base64编码
	private TokenUtil() {
	}

	public static TokenUtil getInstance() {
		return instance;
	}

	/**
	 * 生成token
	 * @return
	 */
	public String generateToken(HttpSession session) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] md5 = md.digest(UUID.randomUUID().toString().getBytes());
			String token = encoder.encode(md5);
//			logger.error("tokenStr=" + token);
			session.setAttribute("token", token);
			return token;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 验证请求中的token和session中的是否一致，如果不一致说明是非法的或重复请求。
	 * 
	 * @param request
	 * @return true:合法请求，false:重复请求
	 */
	public boolean isTokenValid(HttpServletRequest request) {
		synchronized (checkTokenLock) {
			String client_token = request.getParameter("token");
			if (client_token == null) {
				return false;
			}
			String server_token = (String) request.getSession().getAttribute("token");
			request.getSession().removeAttribute("token");
			logger.error("server_token = " + server_token);
			if (server_token == null) {
				return false;
			}
			if (!client_token.equals(server_token)) {
				return false;
			}
			return true;
		}
	}
}

