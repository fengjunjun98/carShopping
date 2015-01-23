/** * 文件名：KeyValueHelper.java 
 * * 版本信息： 
 * 日期：2015-1-21 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

/** 项目名称：carShopping 
 * 类名称：KeyValueHelper 
 * key-value工具类，页面获取中文文字描述都从此获取
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-21 下午07:17:04 
 * 修改人：凤军军 
 * 修改时间：2015-1-21 下午07:17:04 
 * 修改备注：
 * @version 1.0* */

public class KeyValueHelper {

	private static Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());

	public static void load(List<Keyvalue> list) {
		if (list == null || list.size() == 0) {
			map.clear();
			return;
		}

		for (int i = 0; i < list.size(); i++) {
			Keyvalue keyvalue = list.get(i);
			map.put(keyvalue.getKey1(), keyvalue.getValue());
		}
	}

	/**
	 * 根据指定的key获取指定的value
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		if (StringUtils.isEmpty(key)) {
			throw new NullPointerException("key is not null.");
		}

		return map.get(key);
	}

	/**
	 * key的规则是：table_column_key
	 * 
	 * @param key
	 *            参数为table_column_
	 * @return
	 */
	public static Map<String, String> getMap(String key) {
		if (StringUtils.isEmpty(key)) {
			throw new NullPointerException("key is not null.");
		}
		Map<String, String> mapItem = new HashMap<String, String>();
		for (Iterator<Entry<String, String>> it = map.entrySet().iterator(); it
				.hasNext();) {
			Entry<String, String> entry = it.next();
			if (entry.getKey().indexOf(key) >= 0) {
				mapItem.put(entry.getKey(), entry.getValue());
			}
		}
		return mapItem;
	}
}
