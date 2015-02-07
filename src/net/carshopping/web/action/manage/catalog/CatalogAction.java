/** * 文件名：CatalogAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.catalog;
import java.io.IOException;
import java.util.List;

import net.carshopping.core.BaseAction;
import net.carshopping.core.front.SystemManager;
import net.carshopping.core.oscache.FrontCache;
import net.carshopping.core.util.PinYinUtil;
import net.carshopping.services.manage.catalog.CatalogService;
import net.carshopping.services.manage.catalog.bean.Catalog;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/** 项目名称：carShopping 
 * 类名称：CatalogAction 
 * 商品分类,可以无限极分类
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:39:50 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:39:50 
 * 修改备注：
 * @version 1.0* */

public class CatalogAction extends BaseAction<Catalog> {
	private static final Logger logger = LoggerFactory
			.getLogger(CatalogAction.class);
	private static final long serialVersionUID = 1L;
	private CatalogService catalogService;
	private FrontCache frontCache;

	public FrontCache getFrontCache() {
		return frontCache;
	}

	public void setFrontCache(FrontCache frontCache) {
		this.frontCache = frontCache;
	}

	public CatalogService getCatalogService() {
		return catalogService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("catalog!selectList.action");
	}

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	public Catalog getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Catalog();
		}
	}

	public void insertAfter(Catalog e) {
	}

	@Override
	public String selectList() throws Exception {
		String type = e.getType();
		super.initPageSelect();
		e.setType(type);
		logger.error("CatalogAction.selectList.e.type="+e.getType());
		return toList;
	}

	/**
	 * 递归查询数据库获取商品目录
	 * 返回tree的数据结构 从PID=0开始加载菜单资源 获取指定节点的全部子菜单（包括当前菜单节点）
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getRoot() throws Exception {
		List<Catalog> root = catalogService.loadRoot(getE());
		JSONArray json = JSONArray.fromObject(root);
		System.out.println(json.toString());
		String jsonStr = json.toString();
		try {
			getResponse().getWriter().write(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 数据来自缓存
	 * 返回适合easyui.treegrid的JSON的数据结构 从PID=0开始加载菜单资源 获取指定节点的全部子菜单（包括当前菜单节点）
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getRootWithTreegrid() throws Exception {
		logger.error(">>>selectList type = "+e.getType());
		
		List<net.carshopping.services.front.catalog.bean.Catalog> root = null;
		if(e.getType().equals("p")){
			//直接使用缓存数据
			if(SystemManager.productCatalogJsonStr!=null){
				super.write(SystemManager.productCatalogJsonStr);
				return null;
			}
			
			root = SystemManager.catalogs;
			
			JSONArray json = JSONArray.fromObject(root);
			SystemManager.productCatalogJsonStr = json.toString();
			super.write(SystemManager.productCatalogJsonStr);
			
		}else if(e.getType().equals("a")){
			//直接使用缓存数据
			if(SystemManager.articleCatalogJsonStr!=null){
				super.write(SystemManager.articleCatalogJsonStr);
				return null;
			}
			
			root = SystemManager.catalogsArticle;
			
			JSONArray json = JSONArray.fromObject(root);
			SystemManager.articleCatalogJsonStr = json.toString();
			super.write(SystemManager.articleCatalogJsonStr);
		}else{
			throw new IllegalAccessError("参数异常。");
		}
		
		return null;
	}

	/**
	 * 根据ID删除指定的目录,如果该类目下面有子类目,则会一并删除;如果该类目下面有商品,则会一并删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteByID() throws Exception {
		String id = getRequest().getParameter("id");
		if (StringUtils.isBlank(id)) {
			throw new NullPointerException("参数不正确！");
		}
		
		boolean isSuccess = catalogService.deleteByID(id);
		logger.error("isSuccess=" + isSuccess);
		reset();
		super.write(String.valueOf(isSuccess));
		return null;
	}
	
	/**
	 * 添加/修改/删除 某一个类别后，需要重新加载缓存数据。并且清除JSON字符串缓存，以便重新生成新的。
	 * @throws Exception
	 */
	private void reset() throws Exception{
		SystemManager.productCatalogJsonStr = null;
		SystemManager.articleCatalogJsonStr = null;
		frontCache.loadCatalogs(true);//同步更新缓存
	}

	/**
	 * 不支持批量删除
	 */
	public String deletes() throws Exception {
		throw new NullPointerException();
	}

	@Override
	public String toAdd() throws Exception {
		String type = getE().getType();
		logger.error("CatalogAction.toAdd.type="+e.getType());
		getE().clear();
		getE().setType(type);
		return toAdd;
	}

	@Override
	public String toEdit() throws Exception {
		if(StringUtils.isBlank(e.getId())){
			throw new NullPointerException("非法请求！");
		}
		String _id = e.getId();
		e.clear();
		e.setId(_id);
		e = getServer().selectOne(e);
		
		if(e==null){
			throw new NullPointerException("非法请求！");
		}
		return toEdit;
	}

	/**
	 * 返回到查询页面
	 */
	public String back() throws Exception {
		return selectList();
	}
	
	public String insert() throws Exception {
		if(StringUtils.isBlank(e.getPid())){
			e.setPid("0");
		}
		String type = e.getType();
		logger.error("type = "+type);
		try {
			getServer().insert(getE());
			e.clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		reset();
		
		getE().setType(type);
		return super.selectAllList;
	}
	
	public String update() throws Exception {
		String type = e.getType();
		logger.error("type = "+type);
		try {
			getServer().update(getE());
			e.clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		reset();
		getE().setType(type);
		return super.selectAllList;
	}
	
	/**
	 * 唯一性检查
	 * @return
	 * @throws IOException 
	 */
	public String unique() throws IOException{
		logger.error("unique code = " + e.getCode());
		synchronized (this) {
			if(StringUtils.isNotBlank(e.getCode())){
				Catalog catalog = new Catalog();
				catalog.setCode(e.getCode());
				catalog = catalogService.selectOne(catalog);
				
				if(catalog==null){
					//数据库中部存在此编码
					getResponse().getWriter().write("{\"ok\":\"编码可以使用!\"}");
				}else{
					if(StringUtils.isBlank(e.getId())){
						//当前为insert操作，但是编码已经存在，则只可能是别的记录的编码
						getResponse().getWriter().write("{\"error\":\"编码已经存在!\"}");
					}else{
						//update操作，又是根据自己的编码来查询的，所以当然可以使用啦
						getResponse().getWriter().write("{\"ok\":\"编码可以使用!\"}");
					}
				}
			}else{
				getResponse().getWriter().write("{\"error\":\"编码不能为空!\"}");
			}
		}
		return null;
	}
	
	/**
	 * 根据类别名称自动获取拼音-ajax
	 * @return
	 * @throws IOException 
	 */
	public String autoCode() throws IOException{
		if(StringUtils.isBlank(e.getName())){
			return null;
		}
		
		String pinyin = PinYinUtil.getPingYin(e.getName());
		logger.error("pinyin="+pinyin);
		while(true){
			Catalog c = new Catalog();
			c.setCode(pinyin);
			c = catalogService.selectOne(c);
			if(c==null){
				super.write(pinyin);
				break;
			}else{
				pinyin = pinyin + "1";
			}
		}
		return null;
	}
}

