/** * 文件名：FreemarkerTemplateUtil.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.framework.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/** 项目名称：carShopping 
 * 类名称：FreemarkerTemplateUtil 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午11:21:16 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午11:21:16 
 * 修改备注：
 * @version 1.0* */

public class FreemarkerTemplateUtil {
	public static String freemarkerProcess(Map input, String templateStr) {  
	    try {
	    	StringTemplateLoader stringLoader = new StringTemplateLoader();  
		    String template = "content";  
		    stringLoader.putTemplate(template, templateStr);  
		    Configuration cfg = new Configuration();  
		    cfg.setTemplateLoader(stringLoader);  

		    Template templateCon = cfg.getTemplate(template);  
		    StringWriter writer = new StringWriter();  
		    templateCon.process(input, writer);  
		    return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	}  
	  
	// test  
	public static void main(String[] args) throws IOException, TemplateException {  
//		String template = "你好${姓名!'未知'}，今天是${date?string('yyyy-MM-dd')}"; //变量参考freemarker语法  
	    String template = "你好${username}，今天是${date}"; //变量参考freemarker语法  
	    Map m = new HashMap();  
	    m.put("username", "管理员");
	    m.put("date", "2012-10-12");
//	    m.put("date", new Date());  
	    System.out.println(freemarkerProcess(m, template)); //"你好管理员，今天是2013-09-11"  
	}

}
