/** * 文件名：kuaidi100.java 
 * * 版本信息： 
 * 日期：2015-1-21 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/** 项目名称：carShopping 
 * 类名称：kuaidi100 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-21 下午07:19:37 
 * 修改人：凤军军 
 * 修改时间：2015-1-21 下午07:19:37 
 * 修改备注：
 * @version 1.0* */

public class kuaidi100 {
	private static final Logger logger = LoggerFactory.getLogger(kuaidi100.class);

	public static void main(String[] agrs) throws Exception {

		if (1 == 1) {
			String response = HttpUtil.get(
					"http://127.0.0.1:8080/myshopFront/kuaidi100JSON.jsp",null, "UTF-8");
			logger.error("response = " + response);
			// Map map = JSON.toJavaObject(JSON.parseObject(response),
			// Map.class);
			Map<String, Object> map = (Map<String, Object>) JSON.parse(response);
			logger.error("map = " + map.get("data"));
			return;
		}
		try {
			URL url = new URL("http://api.kuaidi100.com/api?id=XXXX&com=tiantian&nu=11111&show=2&muti=1&order=desc");
			URLConnection con = url.openConnection();
			con.setAllowUserInteraction(false);
			InputStream urlStream = url.openStream();
			String type = con.guessContentTypeFromStream(urlStream);
			String charSet = null;
			if (type == null)
				type = con.getContentType();

			if (type == null || type.trim().length() == 0
					|| type.trim().indexOf("text/html") < 0)
				return;

			if (type.indexOf("charset=") > 0)
				charSet = type.substring(type.indexOf("charset=") + 8);

			byte b[] = new byte[10000];
			int numRead = urlStream.read(b);
			String content = new String(b, 0, numRead);
			while (numRead != -1) {
				numRead = urlStream.read(b);
				if (numRead != -1) {
					// String newContent = new String(b, 0, numRead);
					String newContent = new String(b, 0, numRead, charSet);
					content += newContent;
				}
			}
			// System.out.println("content:" + content);
			urlStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
