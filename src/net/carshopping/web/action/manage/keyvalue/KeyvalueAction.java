/** * 文件名：KeyvalueAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.keyvalue;

import net.jeeshop.core.BaseAction;
import net.jeeshop.core.KeyValueHelper;
import net.jeeshop.core.oscache.FrontCache;
import net.jeeshop.services.manage.keyvalue.KeyvalueService;
import net.jeeshop.services.manage.keyvalue.bean.Keyvalue;
/** 项目名称：carShopping 
 * 类名称：KeyvalueAction 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:01:43 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:01:43 
 * 修改备注：
 * @version 1.0* */

public class KeyvalueAction extends BaseAction<Keyvalue> {
	private static final long serialVersionUID = 1L;
	private KeyvalueService keyvalueService;
	private FrontCache frontCache;

	public FrontCache getFrontCache() {
		return frontCache;
	}

	public void setFrontCache(FrontCache frontCache) {
		this.frontCache = frontCache;
	}

	public KeyvalueService getKeyvalueService() {
		return keyvalueService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("keyvalue!selectList.action");
	}

	public void setKeyvalueService(KeyvalueService keyvalueService) {
		this.keyvalueService = keyvalueService;
	}

	public Keyvalue getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Keyvalue();
		}
		super.initPageSelect();
	}

	public void insertAfter(Keyvalue e) {
		e.clear();
	}

	@Override
	public String insert() throws Exception {
		super.insert();

//		KeyValueHelper.load(getPager().getList());
		frontCache.loadKeyValue();
//		return selectList();
		return super.selectAllList;
	}

	@Override
	public String update() throws Exception {
		super.update();
//		KeyValueHelper.load(getPager().getList());
		frontCache.loadKeyValue();
		return selectList();
	}
	
}
