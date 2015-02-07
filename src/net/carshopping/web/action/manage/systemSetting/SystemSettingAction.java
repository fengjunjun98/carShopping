/** * 文件名：SystemSettingAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.systemSetting;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import net.carshopping.core.BaseAction;
import net.carshopping.core.ManageContainer;
import net.carshopping.core.oscache.FrontCache;
import net.carshopping.services.manage.systemSetting.SystemSettingService;
import net.carshopping.services.manage.systemSetting.bean.BelieveLoginInfo;
import net.carshopping.services.manage.systemSetting.bean.SystemSetting;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
/** 项目名称：carShopping 
 * 类名称：SystemSettingAction 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:18:25 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:18:25 
 * 修改备注：
 * @version 1.0* */

public class SystemSettingAction extends BaseAction<SystemSetting> {
	private static final Logger logger = LoggerFactory.getLogger(SystemSetting.class);
	private static final long serialVersionUID = 1L;
	private SystemSettingService systemSettingService;
	private FrontCache frontCache;
	private String sync;//是否立即同步的标志
	private String[] imagePaths;//商品图片的路径集合
	
	public String[] getImagePaths() {
		return imagePaths;
	}

	public void setImagePaths(String[] imagePaths) {
		this.imagePaths = imagePaths;
	}

	public FrontCache getFrontCache() {
		return frontCache;
	}

	public void setFrontCache(FrontCache frontCache) {
		this.frontCache = frontCache;
	}

	public String getSync() {
		return sync;
	}

	public void setSync(String sync) {
		this.sync = sync;
	}

	public SystemSettingService getSystemSettingService() {
		return systemSettingService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("systemSetting!selectList.action");
	}

	public void setSystemSettingService(
			SystemSettingService systemSettingService) {
		this.systemSettingService = systemSettingService;
	}

	public SystemSetting getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new SystemSetting();
		}
	}

	public void insertAfter(SystemSetting e) {
		e.clear();
	}
	
	/**
	 * 保存系统设置信息，始终应该只有一条数据才对
	 */
	public String insertOrupdate() throws Exception {
		synchronized (this) {
//			e.setBelieveLoginConfig(e.getBelieveLoginInfo().getJsonStr());
			
			if(StringUtils.isEmpty(e.getId())){
				e.setImages(getImagesPath(null));
				//新增
				insert();
			}else{
				SystemSetting ss = systemSettingService.selectById(e.getId());
				if(ss==null){
					throw new NullPointerException("根据ID"+e.getId()+"，查询不到系统环境！");
				}
				
				e.setImages(getImagesPath(ss.getImages()));
				//修改
				super.update();
			}
//			logger.error("sync="+sync);
			//同步到内存
			frontCache.loadAllCache();
			
//			if(StringUtils.isNotBlank(sync) && Boolean.valueOf(sync)){
//				cacheManager.loadAllCache();
//			}
		}
		return toEdit();
	}
	
	@Override
	public String toEdit() throws Exception {
		super.toEdit();
		
		//分解图集，以便页面显示
		if(StringUtils.isNotBlank(e.getImages())){
			if(e.getImagesList()==null){
				e.setImagesList(new LinkedList<String>());
			}else{
				e.getImagesList().clear();
			}
			
			String[] imageArr = e.getImages().split(ManageContainer.product_images_spider);
			for(int i=0;i<imageArr.length;i++){
				e.getImagesList().add(imageArr[i]);
			}
		}
		
//		logger.error(">>e.getBelieveLoginConfig() = " + e.getBelieveLoginConfig());
		//分解信任登陆配置信息
//		if(StringUtils.isNotBlank(e.getBelieveLoginConfig())){
//			e.setBelieveLoginInfo(JSON.parseObject(e.getBelieveLoginConfig(), BelieveLoginInfo.class));
//			logger.error(">>believeLoginInfo = " + e.getBelieveLoginInfo().toString());
//		}
		return toEdit;
	}
	
	/**
	 * 例如：http://127.0.0.1:8082/myshop/upload/1.jpg;http://127.0.0.1:8082/myshop/upload/2.jpg;
	 * 获取产品图片路径，注意，这个应该都是相对路径，因为图片有可能会放到专门的图片服务器上。
	 * @return
	 */
	private String getImagesPath(String appendImgs){
		logger.error("e.images = "+e.getImages());
		Set<String> imagesSet = new HashSet<String>();
		
		//添加库里面查询出的图片
		if(StringUtils.isNotBlank(appendImgs)){
			String[] images2 = appendImgs.split(ManageContainer.product_images_spider);
			for(int i=0;i<images2.length;i++){
				if(StringUtils.isNotBlank(images2[i])){
					imagesSet.add(images2[i].trim());
				}
			}
		}
		
		//添加页面上传的图片
		String[] images = e.getImages().split(ManageContainer.product_images_spider);
		for(int i=0;i<images.length;i++){
			if(StringUtils.isNotBlank(images[i])){
				imagesSet.add(images[i].trim());
			}
		}
		
		//图片转为逗号分割形式
		StringBuilder buff = new StringBuilder();
		for(Iterator<String> it = imagesSet.iterator();it.hasNext();){
			buff.append(it.next()+",");
		}
		String rr = buff.toString();
		if(rr.length()>0 && rr.endsWith(ManageContainer.product_images_spider)){
			rr = rr.substring(0, rr.length()-1);
		}
		return rr;
	}
	
	/**
	 * 根据选择的商品图片名称来删除商品图片
	 * @return
	 * @throws Exception 
	 */
	public String deleteImageByImgPaths() throws Exception{
		String id = e.getId();
		if(imagePaths!=null & imagePaths.length>0){
			SystemSetting ee = systemSettingService.selectById(id);
			if(StringUtils.isNotBlank(ee.getImages())){
				String[] images = ee.getImages().split(ManageContainer.product_images_spider);
				//和该商品的图片集合比对，找出不删除的图片然后保存到库
				for(int i=0;i<imagePaths.length;i++){
					for(int j=0;j<images.length;j++){
						if(imagePaths[i].equals(images[j])){
							images[j] = null;
							break;
						}
					}
					imagePaths[i] = null;
				}
				StringBuilder buff = new StringBuilder();
				for(int j=0;j<images.length;j++){
					if(images[j]!=null){
						buff.append(images[j]+",");
					}
				}
				ee.clear();
				ee.setId(id);
				ee.setImages(buff.toString());
				if(ee.getImages().equals("")){
					ee.setImages(ManageContainer.product_images_spider);//全部删除了
				}
				systemSettingService.update(ee);
			}
			imagePaths = null;
		}
//		getResponse().sendRedirect(getEditUrl(id));
//		return null;
		return toEdit();
	}
	
	private String getEditUrl(String id){
		return "systemSetting!toEdit.action?e.id="+id;
	}
	
	public static void main(String[] args) {
		Map<String, Map<String, String>> map = new HashMap<String, Map<String,String>>();
		
		Map<String, String> qqMap = new HashMap<String, String>();
		qqMap.put("app_ID", "101020775");
		qqMap.put("app_KEY", "dca110b976f2a72a7d3fd283fe35d6ec");
		qqMap.put("redirect_URI", "http://myshop.itelse.com/");
		
		Map<String, String> sinawbMap = new HashMap<String, String>();
		sinawbMap.put("app_ID", "101020775");
		sinawbMap.put("app_KEY", "dca110b976f2a72a7d3fd283fe35d6ec");
		sinawbMap.put("redirect_URI", "http://myshop.itelse.com/");
		
		map.put("qq", qqMap);
		map.put("sinawb", sinawbMap);
		
		System.out.println(JSON.toJSONString(map));
	}
}

