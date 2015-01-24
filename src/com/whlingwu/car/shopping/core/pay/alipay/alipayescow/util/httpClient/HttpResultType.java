/** * 文件名：HttpResultType.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.pay.alipay.alipayescow.util.httpClient;

/** 项目名称：carShopping 
 * 类名称：HttpResultType 
 *功能：表示Http返回的结果字符方式
 *详细：表示Http返回的结果字符方式
 *版本：3.3
 *日期：2012-08-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午10:02:29 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午10:02:29 
 * 修改备注：
 * @version 1.0* */

public enum HttpResultType {
	/**
     * 字符串方式
     */
    STRING,

    /**
     * 字节数组方式
     */
    BYTES

}
