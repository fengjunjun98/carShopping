/** * 文件名：QuestionnaireItem.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.services.common;

import java.io.Serializable;
import java.util.List;

import net.carshopping.core.dao.page.PagerModel;

/** 项目名称：carShopping 
 * 类名称：QuestionnaireItem 问卷调查题目
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:41:29 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:41:29 
 * 修改备注：
 * @version 1.0* */

public class QuestionnaireItem extends PagerModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String qid;//问卷ID和题目subject双主键唯一确定一道题目。
	private String subject;
	private String option1;
	private String type;
	private int order1;
	private String display;//inline：一行显示全部选项;lines:一个选项一行
	
	private String[] optionArr;//多个选项
	private List<String> optionList;//页面显示的选项列表

	public void clear() {
		super.clear();
		id = null;
		qid = null;
		subject = null;
		option1 = null;
		type = null;
		order1 = 0;
		display = null;

		optionArr = null;
		clearList(optionList);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getOptionArr() {
		return optionArr;
	}

	public void setOptionArr(String[] optionArr) {
		this.optionArr = optionArr;
	}
	
	public List<String> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<String> optionList) {
		this.optionList = optionList;
	}

	public int getOrder1() {
		return order1;
	}

	public void setOrder1(int order1) {
		this.order1 = order1;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	@Override
	public String toString() {
		StringBuilder buff = new StringBuilder();
		buff.append("subject="+subject+";");
		buff.append("type="+type+";");
		if(optionArr!=null && optionArr.length>0){
			for(int i=0;i<optionArr.length;i++){
				buff.append("optionArr="+optionArr[i]+";");
			}
		}else{
			buff.append("optionArr=null;");
		}
		return buff.toString();
	}
	
}
