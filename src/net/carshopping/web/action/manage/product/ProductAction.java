/** * 文件名：ProductAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.product;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.carshopping.core.BaseAction;
import net.carshopping.core.ManageContainer;
import net.carshopping.core.front.SystemManager;
import net.carshopping.core.oscache.ManageCache;
import net.carshopping.core.system.bean.User;
import net.jeeshop.services.front.product.bean.ProductStockInfo;
import net.jeeshop.services.manage.attribute.AttributeService;
import net.jeeshop.services.manage.attribute.bean.Attribute;
import net.jeeshop.services.manage.attribute_link.Attribute_linkService;
import net.jeeshop.services.manage.attribute_link.bean.Attribute_link;
import net.jeeshop.services.manage.gift.GiftService;
import net.jeeshop.services.manage.gift.bean.Gift;
import net.jeeshop.services.manage.product.ProductService;
import net.jeeshop.services.manage.product.bean.Product;
import net.jeeshop.services.manage.spec.SpecService;
import net.jeeshop.services.manage.spec.bean.Spec;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
/** 项目名称：carShopping 
 * 类名称：ProductAction 商品信息管理
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:08:57 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:08:57 
 * 修改备注：
 * @version 1.0* */

public class ProductAction extends BaseAction<Product> {
	private static final Logger logger = LoggerFactory.getLogger(ProductAction.class);
	private static final long serialVersionUID = 1L;
	private ProductService productService;
	private AttributeService attributeService;
	private Attribute_linkService attribute_linkService;
	private ManageCache manageCache;
	private SpecService specService;
	private GiftService giftService;
	
	private String updateMsg;//更新数据成功或失败的消息
	private String[] imagePaths;//商品图片的路径集合
	private List<Gift> giftList;
	
	public List<Gift> getGiftList() {
		return giftList;
	}

	public void setGiftList(List<Gift> giftList) {
		this.giftList = giftList;
	}

	public GiftService getGiftService() {
		return giftService;
	}

	public void setGiftService(GiftService giftService) {
		this.giftService = giftService;
	}

	public SpecService getSpecService() {
		return specService;
	}

	public void setSpecService(SpecService specService) {
		this.specService = specService;
	}

	public AttributeService getAttributeService() {
		return attributeService;
	}

	public ManageCache getManageCache() {
		return manageCache;
	}

	public void setManageCache(ManageCache manageCache) {
		this.manageCache = manageCache;
	}

	public String[] getImagePaths() {
		return imagePaths;
	}

	public void setImagePaths(String[] imagePaths) {
		this.imagePaths = imagePaths;
	}

	public void setAttributeService(AttributeService attributeService) {
		this.attributeService = attributeService;
	}

	public Attribute_linkService getAttribute_linkService() {
		return attribute_linkService;
	}

	public void setAttribute_linkService(Attribute_linkService attribute_linkService) {
		this.attribute_linkService = attribute_linkService;
	}

	public String getUpdateMsg() {
		return updateMsg;
	}

	public void setUpdateMsg(String updateMsg) {
		this.updateMsg = updateMsg;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("product!selectList.action");
	}

	public Product getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Product();
		}
		
		this.updateMsg = "";
		
		super.initPageSelect();
		
		if(StringUtils.isNotBlank(e.getId())){
			//查询的时候去掉两端空格
			e.setId(e.getId().trim());
			if(StringUtils.isNotBlank(e.getName())){
				e.setName(e.getName().trim());
			}
		}
	}
	
	/**
	 * 添加商品
	 */
	public String toAdd() throws Exception {
		getSession().setAttribute("insertOrUpdateMsg", "");
		
		String chanageCatalog = getRequest().getParameter("chanageCatalog");
		if(StringUtils.isNotBlank(chanageCatalog)){
			if(Boolean.valueOf(chanageCatalog)){
				String catalog = getRequest().getParameter("catalog");//新目录
				logger.error("添加商品-修改目录 。catalog = " + catalog + ",chanageCatalog = " + chanageCatalog);
//				e.clear();
				e.setCatalogID(catalog);
				
				//加载指定类别下商品属性和参数
				changeCatalog(true);
				return toAdd;
			}else{
				throw new NullPointerException("请求非法！");
			}
		}
		
		loadGiftList();
		
		return super.toAdd();
	}
	
	/**
	 * 加载商品赠品列表
	 */
	private void loadGiftList(){
		Gift gift = new Gift();
		gift.setStatus(Gift.gift_status_up);
		giftList = giftService.selectList(gift);
	}
	
	//列表页面点击 编辑商品
	public String toEdit() throws Exception {
		getSession().setAttribute("insertOrUpdateMsg", "");
		return toEdit0();
	}

	/**
	 * 修改商品的类别，会联动清除商品已有的属性和参数
	 * @return
	 * @throws Exception
	 */
	public String updateProductCatalog() throws Exception {
		getSession().setAttribute("insertOrUpdateMsg", "");
		return toEdit0();
	}
	
	/**
	 * 添加或编辑商品后程序回转编辑
	 * @return
	 * @throws Exception
	 */
	public String toEdit2() throws Exception {
		return toEdit0();
	}

	/**
	 * 根据商品ID，加载商品全部信息
	 */
	private String toEdit0() throws Exception {
		if(StringUtils.isBlank(e.getId())){
			throw new NullPointerException("商品ID不能为空！");
		}
		
		e = getServer().selectById(e.getId());
		if(e==null || StringUtils.isBlank(e.getId())){
			throw new NullPointerException("根据商品ID查询不到指定的商品！");
		}
		
		//加载商品图片列表
		if(StringUtils.isNotBlank(e.getImages())){
			if(e.getImagesList()==null){
				e.setImagesList(new LinkedList<String>());
			}else{
				e.getImagesList().clear();
			}
			String[] _images = e.getImages().split(ManageContainer.product_images_spider);
			for(int i=0;i<_images.length;i++){
				if(StringUtils.isNotBlank(_images[i])){
					e.getImagesList().add(_images[i]);
				}
			}
		}else{
			if(e.getImagesList()==null){
				e.setImagesList(Collections.EMPTY_LIST);
			}else{
				e.getImagesList().clear();
			}
		}
		//如果未切换商品目录，则加载商品目录
		if(!changeCatalog(false)){
			if(StringUtils.isNotBlank(e.getCatalogID())){
				int catalogID = Integer.valueOf(e.getCatalogID());
				loadAttribute(catalogID);
				loadParameter(catalogID);
				loadSpec(e);
			}
		}
		
		loadGiftList();
		
		return toEdit;
	}
	
	/**
	 * 加载商品规格
	 */
	private void loadSpec(Product p){
		if(StringUtils.isBlank(p.getId())){
			logger.error("loadSpec id = " + p.getId());
			return;
		}
		
		Spec specInfo = new Spec();
		specInfo.setProductID(p.getId());
		p.setSpecList(specService.selectList(specInfo));
		
		if(p.getSpecList()!=null){
			logger.error("loadSpec = p.getSpecList() = " + p.getSpecList().size());
		}else{
			logger.error("loadSpec = p.getSpecList() is null");
		}
		
		if(p.getSpecList()!=null && p.getSpecList().size() > 0){
			
			//如果有规格，则添加3个到集合的最后，以方便添加数据
			for(int i=0;i<3;i++){
				p.getSpecList().add(new Spec());
			}
			
		}else{
			
			//如果没有规格，则默认添加10个空的，以方便添加数据
			if(p.getSpecList()==null){
				p.setSpecList(new ArrayList<Spec>(10));
			}
			for(int i=0;i<10;i++){
				p.getSpecList().add(new Spec());
			}
		}
	}
	
	/**
	 * 如果添加或编辑商品的时候切换了商品目录，则该商品的属性和参数得重新加载。
	 * @return true：重新加载商品的属性和参数。
	 */
	private boolean changeCatalog(boolean toAdd){
		String chanageCatalog = getRequest().getParameter("chanageCatalog");
		if(toAdd){
			chanageCatalog = "true";
		}
		
		if(StringUtils.isNotBlank(chanageCatalog)){
			if(Boolean.valueOf(chanageCatalog)){
				getSession().setAttribute("insertOrUpdateMsg", "改变商品目录，已重新加载了商品的属性和参数。");
				int catalog = Integer.valueOf(getRequest().getParameter("catalog"));
				logger.error("catalogID====="+catalog);
				//删除该商品之前的目录对应的属性和参数
				if(StringUtils.isNotBlank(e.getId())){
					Attribute_link attrLink = new Attribute_link();
					attrLink.setProductID(Integer.valueOf(e.getId()));
					attribute_linkService.deleteByCondition(attrLink);
				}
				
				e.setCatalogID(String.valueOf(catalog));
				//切换商品目录，则自动切换商品属性和参数
				loadAttribute(catalog);
				loadParameter(catalog);
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据商品分类加载商品属性列表
	 * @catalogID 商品类别ID
	 */
	private void loadParameter(int catalogID) {
		Attribute attr = new Attribute();
		attr.setCatalogID(catalogID);
		attr.setPid(-1);
		attr = this.attributeService.selectOne(attr);//加载参数主属性，一个参数下包含多个子参数
		if(attr!=null){
			//加载每个属性下的子属性列表
			int id = Integer.valueOf(attr.getId());
			attr.clear();
			attr.setPid(id);
//			attr.setPid(0);
			attr.setCatalogID(0);
			//@@@
			e.setParameterList(this.attributeService.selectList(attr));
		}
		
		//如果商品ID不存在，则不加载商品选中的参数列表
		if(StringUtils.isBlank(e.getId())){
			return;
		}
		
		//加载商品参数
		if(e.getParameterList()!=null && e.getParameterList().size()>0){
			Attribute_link attrLink = new Attribute_link();
			attrLink.setProductID(Integer.valueOf(e.getId()));
			//查询参数列表
			List<Attribute_link> attrLinkList = attribute_linkService.selectList(attrLink);
			if(attrLinkList!=null && attrLinkList.size()>0){
				
				for(int i=0;i<e.getParameterList().size();i++){//循环主属性
					Attribute itemInfo = e.getParameterList().get(i);
					int _attrID = Integer.valueOf(itemInfo.getId());
					for(int k=0;k<attrLinkList.size();k++){//循环用户选择的属性
						Attribute_link al = attrLinkList.get(k);
						if(al.getAttrID()==_attrID){
							itemInfo.setParameterValue(al.getValue());
							break;
						}
					}
				}
				
			}
		}
	}

	/**
	 * 根据商品分类加载商品属性列表
	 * @catalogID 商品类别ID
	 */
	private void loadAttribute(int catalogID) {
		Attribute attr = new Attribute();
		attr.setCatalogID(catalogID);
		List<Attribute> attrList = this.attributeService.selectList(attr);
		//加载每个属性下的子属性列表
		if(attrList!=null && attrList.size()>0){
			attr.setCatalogID(0);
			attr.setPid(0);//属性的
			for(int i=0;i<attrList.size();i++){
				Attribute item = attrList.get(i);
				attr.setPid(Integer.valueOf(item.getId()));
				//###
				item.setAttrList(this.attributeService.selectList(attr));
			}
		}
		e.setAttrList(attrList);
		
		//如果商品ID不存在，则不加载商品选中的属性列表
		if(StringUtils.isBlank(e.getId())){
			return;
		}
		
		//加载商品所选中的属性列表
		Attribute_link attrLink = new Attribute_link();
		attrLink.setProductID(Integer.valueOf(e.getId()));
		List<Attribute_link> attrLinkList = attribute_linkService.selectList(attrLink);
		if(attrLinkList!=null && attrLinkList.size()>0){
			if(e.getAttrList()!=null && e.getAttrList().size()>0){
//				for(int i=0;i<attrLinkList.size();i++){
//					Attribute_link al = attrLinkList.get(i);
//					loop:for(int j=0;j<e.getAttrList().size();j++){
//						List<Attribute> attrItemList = e.getAttrList().get(j).getAttrList();
//						for(int k=0;k<attrItemList.size();k++){
//							Attribute attrInfo = attrItemList.get(k);
//							int _selected = Integer.valueOf(attrInfo.getId());
//							//选中用户设置的属性
//							if(al.getAttrID()==_selected){
//								attrInfo.setSelectedID(_selected);
//								break loop;
//							}
//						}
//					}
//				}
				
				
				for(int i=0;i<e.getAttrList().size();i++){//循环主属性
					Attribute mainAttr = e.getAttrList().get(i);
					List<Attribute> itemList = mainAttr.getAttrList();
					loop:for(int j=0;j<itemList.size();j++){//循环子属性列表
						Attribute itemInfo = itemList.get(j);
						int _attrID = Integer.valueOf(itemInfo.getId());
						for(int k=0;k<attrLinkList.size();k++){//循环用户选择的属性
							Attribute_link al = attrLinkList.get(k);
							if(al.getAttrID()==_attrID){
								mainAttr.setSelectedID(_attrID);
								break loop;
							}
						}
					}
				}
				
			}
		}
	}

	//分页查询商品
	public String selectList() throws Exception {
		try {
			e.setQueryCatalogIDs(SystemManager.getInstance().getCatalogsById(e.getCatalogID()));
			super.selectList();
			return toList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	protected void setParamWhenInitQuery() {
		super.setParamWhenInitQuery();
		String selectOutOfStockProduct = getRequest().getParameter("selectOutOfStockProduct");
		if(StringUtils.isNotBlank(selectOutOfStockProduct)){
			//后台--首页 需要查询缺货商品
			e.setSelectOutOfStockProduct(Boolean.valueOf(selectOutOfStockProduct));
		}
	}

	public void insertAfter(Product e) {
		e.clear();
	}

	/**
	 * ajax查询指定商品的图片集合
	 * @return
	 */
	@Deprecated
	public String ajaxLoadImgList(){
		String id = getRequest().getParameter("id");
		String path = ProductAction.class.getResource("/").getFile();
		System.out.println("path=" + path);
		path = path.substring(0, path.indexOf("WEB-INF"));
		System.out.println("path=" + path);
		path = path+"//upload//"+id+"//";
		System.out.println("path=" + path);
		
		File dir = new File(path);
		File[] fiels = dir.listFiles();
		List<String> fileList = new LinkedList<String>();
		if(fiels!=null && fiels.length>0){
			String www_address = SystemManager.getInstance().get("www_address");
			for(int i=0;i<fiels.length;i++){
				fileList.add(www_address + "/upload/"+id+"/"+fiels[i].getName());
			}
		}
		String json = JSON.toJSONString(fileList);
		System.out.println(json);
		try {
			getResponse().getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *  添加产品
	 */
	public String insert() throws Exception {
		logger.error(">>>insert product...");
		//设置产品的图片路径
		getE().setImages(getImagesPath(null));
		getE().setStatus(1);
		getE().setCreateAccount(getAccount());
		
//		if(e.getPicture().indexOf(""))
		int productID = getServer().insert(getE());
		e.clear();
		e.setId(String.valueOf(productID));
		insertOrUpdateCommon();
//		clearCache();
		getSession().setAttribute("insertOrUpdateMsg", "添加产品成功！");
		getResponse().sendRedirect(getEditUrl(String.valueOf(productID)));
		return null;
	}
	
	//获取后台管理人员的账号
	private String getAccount(){
		User user = (User) getRequest().getSession().getAttribute(ManageContainer.manage_session_user_info);
		if(user==null){
			throw new NullPointerException("登陆超时！");
		}
		return user.getUsername();
	}
	
	/**
	 * 更新产品
	 */
	public String update() throws Exception {
		logger.error(">>>update product..."+e.getCatalogID());
		
		String id = getE().getId();
		Product ee = productService.selectById(id);
		
		boolean loadReport = false;
		//如果库存原来是0,现在变成大于0的了,那么需要重新加载商品库存数据
		if (ee.getStock() <= 0 && e.getStock() > 0) {
			loadReport = true;
		}
		
		
		//设置产品的图片路径
		getE().setImages(getImagesPath(ee.getImages()));
		getE().setUpdateAccount(getAccount());
		getServer().update(getE());
		getE().clear();
		getE().setId(id);
//		getE().setStatus(1);
//		getE().setName(null);
		
		insertOrUpdateCommon();
		
		if(loadReport){
			manageCache.loadOrdersReport();
		}
//		clearCache();
//		updateMsg = "更新产品成功！";
		getSession().setAttribute("insertOrUpdateMsg", "更新产品成功！");
		getResponse().sendRedirect(getEditUrl(id));
		return null;
	}
	
	private String getEditUrl(String id){
		return "product!toEdit2.action?e.id="+id;
	}

	/**
	 * 添加或更新商品的公共功能
	 * @throws IOException
	 */
	private void insertOrUpdateCommon() throws IOException {
		logger.error("=insertOrUpdateCommon=");
		
		/**
		 * 同步内存商品库存数据
		 */
		ProductStockInfo momeryProduct = SystemManager.productStockMap.get(e.getId());
		List<String> productIDs = new LinkedList<String>();
		productIDs.add(e.getId());
		Product proObject = productService.selectStockByIDs(productIDs).get(0);
		
		if(momeryProduct==null){
			ProductStockInfo p = new ProductStockInfo();
			p.setId(proObject.getId());
			p.setStock(proObject.getStock());
			p.setScore(proObject.getScore());
			SystemManager.productStockMap.put(proObject.getId(), p);
		}else{
			momeryProduct.setStock(proObject.getStock());
			momeryProduct.setScore(proObject.getScore());
		}
		
		//上传图片
//		uploadImages();
		
		//删除产品旧的属性列表
		Attribute_link oldAttr = new Attribute_link();
		oldAttr.setProductID(Integer.valueOf(e.getId()));
		attribute_linkService.deleteByCondition(oldAttr);
		
		//保存商品属性
		e.setAttrSelectIds(getRequest().getParameterValues("e.attrSelectIds"));
		logger.error("attrSelectIds="+e.getAttrSelectIds());
		if(e.getAttrSelectIds()!=null && e.getAttrSelectIds().length>0){
			for(int i=0;i<e.getAttrSelectIds().length;i++){
				String attrID = e.getAttrSelectIds()[i];
				if(StringUtils.isBlank(attrID)){
					continue;
				}
				//插入数据到属性中间表
				Attribute_link attrLink = new Attribute_link();
				attrLink.setAttrID(Integer.valueOf(attrID));
				attrLink.setProductID(Integer.valueOf(e.getId()));
				attribute_linkService.insert(attrLink);
			}
		}
		
		//保存商品参数
		e.setParameterIds(getRequest().getParameterValues("id"));
		e.setParameterNames(getRequest().getParameterValues("parameterValue"));
		if(e.getParameterNames()!=null && e.getParameterNames().length>0){
			for(int i=0;i<e.getParameterNames().length;i++){
				String pName = e.getParameterNames()[i];
				if(StringUtils.isBlank(pName)){
					continue;
				}
				//插入数据到属性中间表
				Attribute_link attrLink = new Attribute_link();
				attrLink.setAttrID(Integer.valueOf(e.getParameterIds()[i]));
				attrLink.setValue(pName);
				attrLink.setProductID(Integer.valueOf(e.getId()));
				attribute_linkService.insert(attrLink);
			}
		}
	}
	
	/**
	 * 例如：http://127.0.0.1:8082/myshop/upload/1.jpg;http://127.0.0.1:8082/myshop/upload/2.jpg;
	 * 获取产品图片路径，注意，这个应该都是相对路径，因为图片有可能会放到专门的图片服务器上。
	 * @return
	 */
	private String getImagesPath(String appendImgs){
		logger.error("e.images = "+e.getImages());
//		if(StringUtils.isBlank(e.getImages())){
//			return null;
//		}
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
	 * 上架指定商品
	 * @return
	 * @throws Exception 
	 */
	public String updateUpProduct() throws Exception{
		if(StringUtils.isBlank(e.getId())){
			throw new NullPointerException();
		}
		
		User user = (User)getSession().getAttribute(ManageContainer.manage_session_user_info);
		productService.updateProductStatus(new String[]{e.getId()},Product.Product_status_y,user.getUsername());
		getSession().setAttribute("insertOrUpdateMsg", "上架成功！");
		getResponse().sendRedirect(getEditUrl(e.getId()));
		return null;
	}
	
	/**
	 * 下架指定商品
	 * @return
	 * @throws Exception 
	 */
	public String updateDownProduct() throws Exception{
		if(StringUtils.isBlank(e.getId())){
			throw new NullPointerException();
		}
		
		User user = (User)getSession().getAttribute(ManageContainer.manage_session_user_info);
		productService.updateProductStatus(new String[]{e.getId()},Product.Product_status_n,user.getUsername());
		getSession().setAttribute("insertOrUpdateMsg", "下架成功！");
		getResponse().sendRedirect(getEditUrl(e.getId()));
		return null;
	}
	
	/**
	 * 商品上架
	 * @return
	 * @throws Exception 
	 */
	public String updateUp() throws Exception{
		updateStatus(Product.Product_status_y);
		return selectList();
	}
	
	/**
	 * 商品下架
	 * @return
	 * @throws Exception 
	 */
	public String updateDown() throws Exception{
		updateStatus(Product.Product_status_n);
		return selectList();
	}
	
	private void updateStatus(int status){
		User user = (User)getSession().getAttribute(ManageContainer.manage_session_user_info);
		productService.updateProductStatus(getIds(),status,user.getUsername());
	}
	
	/**
	 * 根据选择的商品图片名称来删除商品图片
	 * @return
	 * @throws IOException 
	 */
	public String deleteImageByImgPaths() throws IOException{
		String id = getE().getId();
		if(imagePaths!=null & imagePaths.length>0){
			Product ee = productService.selectById(id);
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
				productService.update(ee);
			}
			imagePaths = null;
		}
		getResponse().sendRedirect(getEditUrl(id));
		return null;
	}
	
	/**
	 * 设置指定的图片为产品的默认图片
	 * @return
	 * @throws Exception 
	 */
//	@Deprecated
//	public String setProductImageToDefault() throws Exception{
////		productService.downGoods(getIds());
////		Product goods = new Product();
////		String imageUrl = getRequest().getParameter("imageUrl");
////		imageUrl = "upload/"+getRequest().getParameter("id")+"/"+imageUrl.substring(imageUrl.lastIndexOf("/"));//取出相对路径
////		goods.setId(getRequest().getParameter("id"));
////		goods.setPicture(imageUrl);
////		productService.update(goods);
////		getResponse().getWriter().write("0");
//		return null;
//	}

	/**
	 * 删除指定的图片
	 * @return
	 * @throws Exception 
	 */
	@Deprecated
//	public String deleteImageByProductID() throws Exception{
//		//项目的物理地址
//		String filePath = SystemManager.getInstance().get("file_path");
////		goodsService.downGoods(getIds());
//		Product goods = new Product();
//		String imageUrl = getRequest().getParameter("imageUrl");
//		String imageName = imageUrl.substring(imageUrl.lastIndexOf("/")+1);
//		imageUrl = "upload/"+imageName;
//		filePath += "\\upload\\"+getRequest().getParameter("id")+"\\"+imageName;//取出相对路径
//		
//		//删除图片文件
//		System.out.println("filePath=="+filePath);
//		File file = new File(filePath);
//		if(file.exists()){
//			file.delete();
//		}
////		FileUtils.deleteDirectory(new File(filePath));
//		
//		goods.setId(getRequest().getParameter("id"));
////		goods.setPicture(imageUrl);
//		goods = productService.selectOne(goods);
//		if(goods!=null && goods.getPicture().equals(imageUrl)){
//			//如果图片被设置为了封面图片，则删除
//		}
//		getResponse().getWriter().write("0");
//		return null;
//	}
	
	/**
	 * 批量生成测试用的商品
	 * @return
	 * @throws Exception
	 * http://127.0.0.1:8080/myshop/manage/product!createTestProducts.action?_refProductID=10013&_refCatalogID=58&_refNum=33
	 * http://127.0.0.1:8080/myshop/manage/product!createTestProducts.action?_refProductID=10009&_refCatalogID=28&_refNum=33
	 */
	public String createTestProducts() throws Exception {
		String _refProductID = getRequest().getParameter("_refProductID");//参考商品ID
		String _refCatalogID = getRequest().getParameter("_refCatalogID");//参考类别ID
		int _refNum = Integer.valueOf(getRequest().getParameter("_refNum"));//生成数量
		if(StringUtils.isBlank(_refProductID) || StringUtils.isBlank(_refCatalogID) || _refNum<=0){
			throw new NullPointerException();
		}
		
		Product refp = productService.selectById(_refProductID);
		if(refp==null || StringUtils.isBlank(refp.getId())){
			throw new NullPointerException();
		}
		
		for(int i=0;i<_refNum;i++){
			Product product0 = new Product();
			product0.setName(refp.getName()+"_"+(i+1));
			product0.setCatalogID(refp.getCatalogID());
			product0.setPicture(refp.getPicture());
			product0.setPrice(refp.getPrice());
			product0.setNowPrice(refp.getNowPrice());
			product0.setSellcount(refp.getSellcount());
			product0.setStock(refp.getStock());
			product0.setIsnew(refp.getIsnew());
			product0.setSale(refp.getSale());
			product0.setTitle(refp.getTitle());
			product0.setDescription(refp.getDescription());
			product0.setKeywords(refp.getKeywords());
			product0.setIntroduce(refp.getIntroduce());
			product0.setImages(refp.getImages());
			product0.setProductHTML(refp.getProductHTML());
			product0.setStatus(refp.getStatus());
			
			productService.insert(product0);
		}
		return selectList();
	}
	
	
//	public String test2() throws IOException{
//		List<Product> list = productService.selectList(new Product());
//		for(int i=0;i<list.size();i++){
//			Product pp = list.get(i);
//			if(StringUtils.isNotBlank(pp.getProductHTML())){
//				
//				Product ppp = new Product();
//				ppp.setId(pp.getId());
//				ppp.setProductHTML(pp.getProductHTML().replace("http://jeeshopxx.oss.aliyuncs.com/", "http://myshopxx.oss.aliyuncs.com/"));
//				
//				logger.error(">>>test2>>"+ppp.getProductHTML());
//				productService.update(ppp);
//			}
//		}
//		
//		getResponse().getWriter().write("success");
//		return null;
//	}
	
	/**
	 * 把所有商品的大图更新为小图
	 * @return
	 */
	public String test10() {
		logger.error("test10...");
		List<Product> list = productService.selectList(new Product());
		for(int i=0;i<list.size();i++){
			Product pp = list.get(i);
			String img = pp.getPicture();
			if(StringUtils.isBlank(img)){
				continue;
			}
			String[] arr = img.split("_");
			if(arr.length==2){
				String fx = img.substring(img.lastIndexOf("."));
				Product p = new Product();
				p.setId(pp.getId());
				p.setPicture(arr[0]+"_1"+fx);
				
				if(pp.getIsnew().toString().equals("0")){
					p.setIsnew(Product.Product_isnew_n);
				}else{
					p.setIsnew(Product.Product_isnew_y);
				}
				
				if(pp.getSale().toString().equals("0")){
					p.setSale(Product.Product_sale_n);
				}else{
					p.setSale(Product.Product_sale_y);
				}
				
				logger.error("p.getPicture = " + p.getPicture());
				productService.updateImg(p);
				
//				throw new NullPointerException();
			}
		}
		
		return null;
	}
}

