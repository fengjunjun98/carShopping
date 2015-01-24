/** * 文件名：SMSWebChinese.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.sms;

/** 项目名称：carShopping 
 * 类名称：SMSWebChinese 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * http://www.webchinese.com.cn   此公司的SMS短信平台
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午10:33:07 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午10:33:07 
 * 修改备注：
 * @version 1.0* */

public class SMSWebChinese {
	public static void main(String[] args) throws HttpException, IOException {
		sendSMS(null);
	}

	public static void sendSMS(Sms sms) throws IOException, HttpException,
			UnsupportedEncodingException {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn");
		post.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", "jqsl2012"),
				new NameValuePair("Key", "8376220944aae870d31a"),
				new NameValuePair("smsMob", sms.getPhone()),
				new NameValuePair("smsText", sms.getContent()) };
		post.setRequestBody(data);

		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println("h.toString()="+h.toString());
		}
		String returnCode = new String(post.getResponseBodyAsString().getBytes(
				"gbk"));
		System.out.println("result="+returnCode);

		post.releaseConnection();
		
		sms.setReturnCode(returnCode);
	}
}
