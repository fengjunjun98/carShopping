/** * 文件名：AttributeAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.attribute;
import java.io.IOException;
import java.util.List;

import net.jeeshop.core.BaseAction;
import net.jeeshop.services.manage.attribute.AttributeService;
import net.jeeshop.services.manage.attribute.bean.Attribute;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/** 项目名称：carShopping 
 * 类名称：AttributeAction 
 * 属性action
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:37:57 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:37:57 
 * 修改备注：
 * @version 1.0* */

public class AttributeAction extends BaseAction<Attribute> {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AttributeAction.class);
	private AttributeService attributeService;
	private static final Object lock = new Object();//添加参数锁，防止多人操作产生的数据异常。

	public AttributeService getAttributeService() {
		return attributeService;
	}

	protected void selectListAfter() {
		savePid();
		pager.setPagerUrl("attribute!selectList.action");
	}

	public void setAttributeService(AttributeService attributeService) {
		this.attributeService = attributeService;
	}

	public Attribute getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Attribute();
		}
		this.initPageSelect();
	}

	public void insertAfter(Attribute e) {
		savePid();
	}
	
	private void savePid(){
		int pid = e.getPid();//保存目录类别
		int catalogID = e.getCatalogID();//保存查询目录ID
		e.clear();
		e.setPid(pid);
		e.setCatalogID(catalogID);
	}
	
	/**
	 * 保留下pid，因为这个表示属性和参数2个方面
	 */
	public String toAdd() throws Exception {
		savePid();
		
		getSession().setAttribute("insertOrUpdateMsg","");
		return toAdd;
	}
	
	@Override
	public String back() throws Exception {
		savePid();
		selectList();
		return toList;
	}
	
	public String selectList() throws Exception {
		int pid = e.getPid();
		if(StringUtils.isNotBlank(this.getInit()) && this.getInit().equals("y")){
			this.e.clear();
			this.init = null;
		}
		e.setPid(pid);
		
		super.selectList();
		Attribute p = new Attribute();
		StringBuilder itemBuff = new StringBuilder();
		if(pager.getList()!=null && pager.getList().size()>0){
			//根据每一项的ID作为子项的PID去查询子项的数据列表
			for(int i=0;i<pager.getList().size();i++){
				Attribute item = (Attribute) pager.getList().get(i);
				p.setPid(Integer.valueOf(item.getId()));
				List<Attribute> list = attributeService.selectList(p);
				itemBuff.setLength(0);
				for(int j=0;j<list.size();j++){
					itemBuff.append(list.get(j).getName());
					if(j!=list.size()){
						itemBuff.append(",");
					}
				}
				item.setNameBuff(itemBuff.toString());
			}
		}
		
		return toList;
	}
	
	/**
	 * 对属性和参数的新增操作需要注意一下：同一个类别下面可以有多个属性，同一个属性下只能有一个参数。同一个属性下的所有商品将会共享这一二个参数。
	 * 
	 * 这个可以参看淘宝的做法，如果查看笔记本，那么该笔记本类别下可以有多个品牌，一个品牌下有多个属性。但是
	 * 
	 * 淘宝商品结构：
	 * 
	 * 商品服务分类		--顶级目录
	 * 		手机数码		--顶级虚目录
	 * 			笔记本	--二级虚目录
	 * 				联想、三星、惠普、索尼		--商品属性(或者称之为品牌)分类
	 * 					联想属性(品牌)下有：X240/S3/T430/T400		--品牌下的型号分类
	 * 				
	 */
	@Override
	public String insert() throws Exception {
		try {
//			String id = e.getId();
			int pid = e.getPid();
//			if(StringUtils.isBlank(id)){
//				throw new NullPointerException();
//			}
			
			logger.error("Attribute.insert.id = " + e.getId()+",pid="+pid);
			if(pid==-1){
				synchronized (lock) {
					//查询指定的类目下是否有多个参数
					Attribute ee = new Attribute();
					ee.setCatalogID(e.getCatalogID());
					ee.setPid(e.getPid());
					int count = attributeService.selectCount(ee);
					if (count == 0) {
						return insert0();
					}else if(count == 1){
						if(StringUtils.isNotBlank(e.getId())){
							return insert0();
						}
					}

					//已经添加了一个参数，则会抛出RuntimeException异常。
					throw new RuntimeException("一个商品类别下只能添加一个参数！");
				}
			}else if(pid==0){
				//忽略
				return insert0();
			}else{
				throw new NullPointerException("参数异常。pid="+pid+"，pid不正确。只能为0或-1。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private String insert0() throws Exception{
		int id = getServer().insert(getE());
		logger.error("insert0.id="+id);
		getSession().setAttribute("insertOrUpdateMsg", "操作成功！");
		getResponse().sendRedirect(getEditUrl(String.valueOf(id)));
		return null;
	}
	
	private String getEditUrl(String id){
		logger.error("getEditUrl.id=" + id);
		return "attribute!toEdit2.action?e.id="+id;
	}
	
	/**
	 * 列表页面点击 编辑商品
	 */
	public String toEdit() throws Exception {
		getSession().setAttribute("insertOrUpdateMsg", "");
		return toEdit0();
	}
	
	/**
	 * 添加或编辑商品后程序回转编辑页面
	 * @return
	 * @throws Exception
	 */
	public String toEdit2() throws Exception {
//		String id = getRequest().getParameter("id");
//		e.setId(id);
		return toEdit0();
	}
	
	private String toEdit0() throws Exception {
		if(StringUtils.isBlank(e.getId())){
			throw new NullPointerException();
		}
		logger.error("e.getId() = " + e.getId());
		//加载属性/参数对象
		e = attributeService.selectById(e.getId());
		logger.error("e = " + e);
		//加载子属性列表
		Attribute ee = new Attribute();
		ee.setPid(Integer.valueOf(e.getId()));
		e.setAttrList(attributeService.selectList(ee));
		
		return toEdit;
	}
	
//	//列表页面点击 编辑商品
//	public String toEdit() throws Exception {
//		getSession().setAttribute("insertOrUpdateMsg", "");
//		return toEdit0();
//	}
	
//	@Override
//	protected void setParamWhenInitQuery() {
//		super.setParamWhenInitQuery();
//		logger.error("setParamWhenInitQuery catalogID = " + e.getCatalogID());
//	}
	
	/**
	 * 删除商品属性数据，需要同时删除该属性下的子属性 以及 attribute_link表的关联数据
	 */
	public String deletes() throws Exception {
		return super.deletes();
	}
	
//	@Override
//	public String insert() throws Exception {
//		if(StringUtils.isBlank(e.getAttrNames0())){
//			throw new NullPointerException("添加失败！");
//		}
//		e.
//		return super.insert();
//	}
}
