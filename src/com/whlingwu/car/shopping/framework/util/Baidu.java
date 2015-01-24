/** * 文件名：Baidu.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/** 项目名称：carShopping 
 * 类名称：Baidu 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午11:27:20 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午11:27:20 
 * 修改备注：
 * @version 1.0* */

public class Baidu {
	public static void main(String[] args) throws Exception {
//		String address = "http://api.map.baidu.com/geocoder/v2/?address=华夏东路&output=json&ak=E4805d16520de693a3fe707cdc962045";
//		getLatlon(address);
		
		List<String> addressList = new LinkedList<String>();
//		addressList.add("人民广场23号");
//		addressList.add("长寿路站33号");
//		addressList.add("徐汇区");
		addressList.add("上海市浦东新区华夏一路34");
		for(int i=0;i<addressList.size();i++){
			String address = "http://api.map.baidu.com/geocoder/v2/?address="+addressList.get(i)+"&output=json&ak=E4805d16520de693a3fe707cdc962045";
			try {
				getLatlon(address);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param address
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private static void getLatlon(String address) throws MalformedURLException,
			IOException {
		URL t = new URL(address);
		URLConnection conn = t.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = reader.readLine();
//		System.out.println(line);
		
		BaiduMapInfo bInfo = JSON.parseObject(line, BaiduMapInfo.class);
//		System.out.println(bInfo);
		System.out.println(bInfo.getResult().getLocation());
	}
}

