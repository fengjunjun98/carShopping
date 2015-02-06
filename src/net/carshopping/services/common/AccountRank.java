/** * 文件名：AccountRank.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;
import java.io.Serializable;

import net.carshopping.core.dao.page.PagerModel;
/** 项目名称：carShopping 
 * 类名称：AccountRank 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:37:56 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:37:56 
 * 修改备注：
 * @version 1.0* */

public class AccountRank extends PagerModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private int minScore;
	private int maxScore;
	private String remark;

	public void clear() {
		super.clear();
		id = null;
		code = null;
		name = null;
		minScore = 0;
		maxScore = 0;
		remark = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinScore() {
		return minScore;
	}

	public void setMinScore(int minScore) {
		this.minScore = minScore;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
