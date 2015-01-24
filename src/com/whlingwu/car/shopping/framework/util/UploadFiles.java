/** * 文件名：UploadFiles.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.framework.util;

/** 项目名称：carShopping 
 * 类名称：UploadFiles 
 * 文件类，需要的时候，可以和数据库进行关联
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午11:02:28 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午11:02:28 
 * 修改备注：
 * @version 1.0* */

public class UploadFiles {
	private String uploadFileName;// 上传的文件名称
	private String uploadContentType;// 类型
	private String uploadRealName;// 保存的文件真实名称，UUID

	// 如果使用数据库的话，建议这三个字段都进行保存
	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadRealName() {
		return uploadRealName;
	}

	public void setUploadRealName(String uploadRealName) {
		this.uploadRealName = uploadRealName;
	}

}
