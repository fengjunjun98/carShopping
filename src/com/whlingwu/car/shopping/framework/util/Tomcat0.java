/** * 文件名：Tomcat0.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/** 项目名称：carShopping 
 * 类名称：Tomcat0 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午11:05:43 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午11:05:43 
 * 修改备注：
 * @version 1.0* */

public class Tomcat0 {
	public static void main(String[] args) throws IOException {
//		Process p = Runtime.getRuntime().exec("cmd /c start E:\\apache-tomcat-6.0.35\\bin\\startup.bat");
//		System.out.println(p);
//		InputStream in = p.getInputStream();
//		while(in.read()!=-1){
//			System.out.println(in.read());
//		}
		
		Process p;    
		try      
        {      
           //执行命令      
            p = Runtime.getRuntime().exec("E:\\apache-tomcat-6.0.35\\bin\\startup.bat");      
           //取得命令结果的输出流      
            InputStream fis=p.getInputStream();      
           //用一个读输出流类去读      
            InputStreamReader isr=new InputStreamReader(fis);      
           //用缓冲器读行      
            BufferedReader br=new BufferedReader(isr);      
            String line=null;      
           //直到读完为止      
           while((line=br.readLine())!=null)      
            {      
                System.out.println(line);      
            }      
        }      
       catch (IOException e)      
        {      
            e.printStackTrace();      
        }      
	}
}
