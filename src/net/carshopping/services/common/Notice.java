/** * 文件名：Notice.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;
import java.io.Serializable;

import net.carshopping.core.dao.page.PagerModel;
/** 项目名称：carShopping 
 * 类名称：Notice 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:45:26 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:45:26 
 * 修改备注：
 * @version 1.0* */

@Deprecated
public class Notice extends PagerModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String content;
	private String createtime;

	private int top;

	public Notice() {
	}

	public Notice(int top) {
		this.top = top;
	}

	public void clear() {
		super.clear();
		id = null;
		title = null;
		content = null;
		createtime = null;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

}
