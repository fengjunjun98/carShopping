/** * 文件名：AreaAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.area;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.carshopping.core.BaseAction;
import net.carshopping.core.front.SystemManager;
import net.carshopping.core.oscache.FrontCache;
import net.carshopping.core.util.CreateAreaUtil;
import net.carshopping.services.manage.area.AreaService;
import net.carshopping.services.manage.area.bean.Area;
import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
/** 项目名称：carShopping 
 * 类名称：AreaAction 
 * 区域
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:36:56 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:36:56 
 * 修改备注：
 * @version 1.0* */

public class AreaAction extends BaseAction<Area> {
	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AreaAction.class);
	private AreaService areaService;
	private FrontCache frontCache;
	
	public FrontCache getFrontCache() {
		return frontCache;
	}

	public void setFrontCache(FrontCache frontCache) {
		this.frontCache = frontCache;
	}

	public AreaService getAreaService() {
		return areaService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("area!selectList.action");
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public Area getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Area();
		}
	}

	public void insertAfter(Area e) {
		e.clear();
	}
	
	/**
	 * 获得省市区的树结构数据
	 * @return
	 * @throws IOException 
	 */
	public String getAreaTree() throws IOException{
//		List<Area> areaList = areaService.getAreaTree("0");
		Collection<net.carshopping.services.front.area.bean.Area> areaList = SystemManager.areaMap.values();
		
		JSONArray json = JSONArray.fromObject(areaList);
		String jsonStr = json.toString();
//		logger.error("jsonStr="+jsonStr);
		super.write(jsonStr);
		return null;
	}
	
	/**
	 * 转到 添加/修改菜单 页面
	 * @return
	 * @throws Exception
	 */
	public String toAddOrUpdate() throws Exception{
		e.clear();
		e.setId(getRequest().getParameter("id"));
		e = areaService.selectOne(e);
		return "addOrUpdate";
	}
	
	/**
	 * 添加主类/修改主类、添加子类
	 * @return
	 * @throws IOException
	 */
	public String doAddOrUpdate() throws IOException{
		String id = getRequest().getParameter("id");
		String code = getRequest().getParameter("code");
		String pcode = getRequest().getParameter("pcode");
		String name = getRequest().getParameter("name");
		
		String children_code = getRequest().getParameter("children_code");
		String children_pcode = getRequest().getParameter("children_pcode");
		String children_name = getRequest().getParameter("children_name");
		String children_type = getRequest().getParameter("children_type");
		
		//debug
		StringBuilder buff = new StringBuilder();
		buff.append("id="+id+";");
		buff.append("code="+code+";");
		buff.append("pcode="+pcode+";");
		buff.append("name="+name+";");
		buff.append("children_code="+children_code+";");
		buff.append("children_pcode="+children_pcode+";");
		buff.append("children_name="+children_name+";");
		buff.append("children_type="+children_type+";");
		logger.error("doAddOrUpdate.buff="+buff.toString());
		
		if (StringUtils.isBlank(id) || StringUtils.isBlank(code)
				|| StringUtils.isBlank(pcode) || StringUtils.isBlank(name)) {
			//非法请求
			getResponse().getWriter().write("-1");
		}else{
			//修改顶级类别
			Area area = new Area();
			area.setId(id);
			area.setCode(code);
			area.setPcode(pcode);
			area.setName(name);
			areaService.update(area);
			
			if (StringUtils.isBlank(children_code)
					|| StringUtils.isBlank(children_pcode)
					|| StringUtils.isBlank(children_name)
					) {
				//忽略
			}else{
				if(children_type.equals("top")){//添加顶级类
					children_code = "0";
				}else{//添加子类
					children_pcode = code;
				}
				
				//添加子类别
				area.clear();
				area.setCode(children_code);
				area.setPcode(children_pcode);
				area.setName(children_name);
				areaService.insert(area);
			}
			
			//成功
			getResponse().getWriter().write("0");
		}
		return null;
	}
	
	/**
	 * 对树的删除操作
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception{
		String ids = getRequest().getParameter("ids");
		if(StringUtils.isBlank(ids)){
			throw new Exception("非法请求！");
		}
		logger.error("delete.ids="+ids+",deleteParent="+getRequest().getParameter("deleteParent"));
		areaService.deletes2(ids,getRequest().getParameter("deleteParent"));
		
		//删除成功返回1
		getResponse().getWriter().println("1");
		return null;
	}
	
	/**
	 * 初始化区域树
	 * @return
	 */
	public String initAreaTree(){
//		cacheManager.initAreaDataToDB();
		
		Map<String, Area> map = CreateAreaUtil.getAreaMap();
		if(map.size()==0){
			throw new NullPointerException("无数据源！");
		}
		
		areaService.initAreaDataToDB(map);
		
		loadArea();
		
		frontCache.readJsonArea();
		return "areaTree";
	}
	
	/**
	 * 加载省市区数据
	 */
	private void loadArea(){
		logger.error("loadArea...");
		Area area = new Area();
		area.setPcode("0");
		List<Area> rootData = areaService.selectList(area);
		if(rootData==null){
			return ;
		}
		
		for(int i=0;i<rootData.size();i++){
			Area item = rootData.get(i);
			getAreaByDigui2(item);
		}
		
		Map<String, Area> map = new TreeMap<String, Area>();
		for(int i=0;i<rootData.size();i++){
			Area item = rootData.get(i);
			map.put(item.getCode(), item);
		}
		
//		SystemManager.areaMap = map;
		
//		logger.error("SystemManager.areaMap=="+SystemManager.areaMap);
		
		String json = JSON.toJSONString(map);
//		logger.error("json="+json);
		try {
			
			String path = FrontCache.class.getResource("/").getPath();
			logger.error("path = " + path);
			File file = new File(path + "__area.json");
			
			//写到文件
//			File file = new File("__area.txt");
//			logger.error("file.exists()="+file.exists());
//			if(!file.exists()){
//				boolean f = file.createNewFile();
//				logger.error("f="+f);
//			}
			logger.error("file.getAbsolutePath()="+file.getAbsolutePath());
			FileUtils.writeStringToFile(file, json, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 递归加载省份下的：城市、区域、以后还会有街道的数据
	 * @param item
	 */
	private void getAreaByDigui2(Area item){
		Area area = new Area();
		area.setPcode(item.getCode());
		List<Area> children = areaService.selectList(area);
		if(children==null){
			return ;
		}
		
		item.setChildren(children);
		
		
		for(int i=0;i<children.size();i++){
			getAreaByDigui2(children.get(i));
		}
	}
}

