/** * 文件名：ProductAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.front.product;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.jeeshop.core.BaseAction;
import net.jeeshop.core.FrontContainer;
import net.jeeshop.core.KeyValueHelper;
import net.jeeshop.core.dao.page.PagerModel;
import net.jeeshop.core.front.SystemManager;
import net.jeeshop.core.util.LRULinkedHashMap;
import net.jeeshop.services.front.account.bean.Account;
import net.jeeshop.services.front.address.AddressService;
import net.jeeshop.services.front.attribute.bean.Attribute;
import net.jeeshop.services.front.attribute_link.Attribute_linkService;
import net.jeeshop.services.front.catalog.bean.Catalog;
import net.jeeshop.services.front.comment.CommentService;
import net.jeeshop.services.front.comment.bean.Comment;
import net.jeeshop.services.front.emailNotifyProduct.EmailNotifyProductService;
import net.jeeshop.services.front.emailNotifyProduct.bean.EmailNotifyProduct;
import net.jeeshop.services.front.favorite.FavoriteService;
import net.jeeshop.services.front.favorite.bean.Favorite;
import net.jeeshop.services.front.news.NewsService;
import net.jeeshop.services.front.news.bean.News;
import net.jeeshop.services.front.product.ProductService;
import net.jeeshop.services.front.product.bean.Product;
import net.jeeshop.services.front.product.bean.ProductImageInfo;
import net.jeeshop.services.front.systemSetting.bean.SystemSetting;
import net.jeeshop.services.manage.activity.bean.Activity;
import net.jeeshop.services.manage.gift.GiftService;
import net.jeeshop.services.manage.gift.bean.Gift;
import net.jeeshop.services.manage.spec.SpecService;
import net.jeeshop.services.manage.spec.bean.Spec;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
/** 项目名称：carShopping 
 * 类名称：ProductAction 
 * 商品信息管理
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:22:31 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:22:31 
 * 修改备注：
 * @version 1.0* */

public class ProductAction extends BaseAction<Product> {
	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProductAction.class);
	private ProductService productService;//商品服务
	private CommentService commentService;//评论服务
	private AddressService addressService;//收货人地址服务
	private Attribute_linkService attribute_linkService;//商品属性链接表服务
	private NewsService newsService;//文章服务
	private FavoriteService favoriteService;//商品收藏夹服务
	private EmailNotifyProductService emailNotifyProductService;//商品到货通知
	private SpecService specService;
	private GiftService giftService;
	
//	private int catalogID;//选择的产品目录ID
	private String catalogCode;//选择的目录code
	private int attributeID;//产品属性ID
	private String special;//促销活动
	private Map<String, String> orderMap;//排序map
	private List<Product> productList;//商品列表
	private int orderBy;//排序规则

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

	public void setEmailNotifyProductService(
			EmailNotifyProductService emailNotifyProductService) {
		this.emailNotifyProductService = emailNotifyProductService;
	}

	public void setFavoriteService(FavoriteService favoriteService) {
		this.favoriteService = favoriteService;
	}

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public void setAttribute_linkService(Attribute_linkService attribute_linkService) {
		this.attribute_linkService = attribute_linkService;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public int getAttributeID() {
		return attributeID;
	}

	public void setAttributeID(int attributeID) {
		this.attributeID = attributeID;
	}

	public Map<String, String> getOrderMap() {
		return orderMap;
	}

	public void setOrderMap(Map<String, String> orderMap) {
		this.orderMap = orderMap;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	protected void selectListAfter() {
		pager.setPagerUrl(this.catalogCode+".html");
	}

	public Product getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Product();
		}
		
		this.orderBy = 0;
		this.attributeID = 0;
		this.special = null;
		this.catalogCode = null;
		e.clear();
		
		if(productList!=null && productList.size()>0){
			for(int i=0;i<this.productList.size();i++){
				Product p = this.productList.get(i);
				p.clear();
				p = null;
			}
			this.productList.clear();
		}
		this.productList = null;
		
		super.setSelectMenu(FrontContainer.not_select_menu);//设置主菜单为不选中
	}
	
	public void insertAfter(Product e) {
		e.clear();
	}
	
	/**
	 * 根据商品关键字搜索商品列表
	 * @return
	 * @throws Exception
	 */
	public String search() throws Exception{
//		getSession().setAttribute("selectMenu", "");
		
		String key = getRequest().getParameter("key");//搜索关键字
		if(key==null || StringUtils.isBlank(key)){
			return "productList";
		}
		
//		logger.error("search?key="+key);
		getRequest().setAttribute("key", key);
		getE().clear();
		getE().setName(key);
		
		productList = selectProductList0();
		getE().clear();
		
		if(productList==null){
			logger.error("productList=0");
		}else{
			logger.error("productList="+productList.size());
		}
		return "productList";
	}
	
	/**
	 * 根据商品属性加载商品列表
	 * @return
	 * @throws Exception 
	 */
	public String productListByAttrID() throws Exception{
		try {
//			logger.error("attributeID="+attributeID);
//			getE().setAttrID(this.attributeID);
//			Attribute attr = SystemManager.attrsMap.get(String.valueOf(this.attributeID));
//			this.catalogID = attr.getCatalogID();
//			logger.error("productListByAttrID  catalogID = "+catalogID);
//			productList = selectProductList0();
			productList();
		} catch (Exception e) {
			logger.error("productListByAttrID()异常："+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return "productList";
	}
	
	/**
	 * 根据商品目录、子目录、属性、排序等规则分页加载商品列表信息。此方法为商品加载的通用方法。
	 * @return
	 * @throws Exception
	 */
	public String productList() throws Exception{
		try {
			if(this.attributeID>0){
				getE().setAttrID(this.attributeID);
				Attribute attr = SystemManager.attrsMap.get(String.valueOf(this.attributeID));
				this.catalogCode = SystemManager.catalogsMap.get(String.valueOf(attr.getCatalogID())).getCode();
			}
			
			logger.error("special="+special + ",orderBy="+orderBy + ",catalogCode="+catalogCode);
			
			Catalog item = SystemManager.catalogsCodeMap.get(this.catalogCode);
			if(item==null){
				throw new NullPointerException("目录为空！");
			}
			logger.error("item.getId()="+item.getId());
			
			getSession().setAttribute("selectMenu", item.getId());//设置选择的商品目录
			
			logger.error("item.getId()="+item.getId());
			//添加可能是父类的类别ID到查询类别集合
			getE().getQueryCatalogIDs().add(Integer.valueOf(item.getId()));
			logger.error("getE().getQueryCatalogIDs()="+getE().getQueryCatalogIDs());
			
			if(item.getPid().equals("0")){//主类别
				e.setMainCatalogName(item.getName());
				//大类ID
				getE().setQueryCatalogIDs(new LinkedList<Integer>());
				for(int j=0;j<item.getChildren().size();j++){
					//如果存在多级目录的话，则此处可以把所有的目录ID全部循环出来，反正SQL语句使用in查询就可以了
					getE().getQueryCatalogIDs().add(Integer.valueOf(item.getChildren().get(j).getId()));
				}
			}else{//子类别
				e.setChildrenCatalogName(item.getName());
				item = SystemManager.catalogsMap.get(item.getPid());
				getSession().setAttribute("selectMenu", item.getId());
				e.setMainCatalogName(item.getName());
			}
			
			//加载商品
			productList = selectProductList0();
			
			orderMap = KeyValueHelper.getMap("product_orderBy_");
		} catch (Exception e) {
			logger.error("productList()异常："+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return "productList";
	}
	
	/**
	 * 加载热门、特价、最新的商品列表信息
	 * @return
	 * @throws Exception
	 */
	public String specialProductList() throws Exception{
		logger.error("special="+special);
//		getSession().setAttribute("selectMenu", -1);//不选择任何的主菜单
		e.setSpecial(special);
		//加载商品
		productList = selectProductList0();
		
		orderMap = KeyValueHelper.getMap("product_orderBy_");
		
		//指定分页请求的地址
		pager.setPagerUrl(special+".html");
		return "specialProductList";
	}
	
	/**
	 * 处理图片，后台上传的图片地址是这样的/myshop/attached/image/20130928/20130928233856_374.jpg
	 * 系统设置的图片服务器的地址是http://127.0.0.1:8082/myshop ，需要合并成正确的可以显示图片的地址
	 */
	@Deprecated
	private void doImage(List<Product> productList){
		SystemSetting ssInfo = SystemManager.systemSetting;//(SystemSetting) CacheSingle.getInstance().get(FrontContainer.SystemSetting);
		if(productList==null || productList.size()==0){
			return ;
		}
		
		for(int i=0;i<productList.size();i++){
			Product p = productList.get(i);
			if(StringUtils.isNotEmpty(p.getPicture())){
				String picture = p.getPicture();
				picture = picture.substring(1);
				int firstChar = picture.indexOf("/");
				picture = picture.substring(firstChar);
				p.setPicture(ssInfo.getImageRootPath() + picture);
			}
		}
	}
	
	/**
	 * 查询指定的产品明细
	 * @return
	 * @throws Exception
	 */
	public String product() throws Exception{
		Catalog item = checkProduct();//检查商品信息
		
		saveHistoryProductToSession();
		
		//加载商品规格，以JSON字符串的形式隐藏在页面上，然后页面将其转换成对象集合，通过脚本控制规格的颜色和尺寸的双向关系。
		Spec spec = new Spec();
		spec.setProductID(e.getId());
		List<Spec> specList = specService.selectList(spec);
		if(specList!=null && specList.size()>0){
			e.setSpecJsonString(JSON.toJSONString(specList));
			logger.error("e.setSpecJsonString = " + e.getSpecJsonString());
			
//			Set<String> specColor = new HashSet<String>(); 
//			Set<String> specSize = new HashSet<String>();
			
			if(e.getSpecColor()==null){
				e.setSpecColor(new HashSet<String>());
			}
			if(e.getSpecSize()==null){
				e.setSpecSize(new HashSet<String>());
			}
			
			//分离商品的尺寸和颜色
			for(int i=0;i<specList.size();i++){
				Spec specItem = specList.get(i);
				if(StringUtils.isNotBlank(specItem.getSpecColor())){
					e.getSpecColor().add(specItem.getSpecColor());
				}
				if(StringUtils.isNotBlank(specItem.getSpecSize())){
					e.getSpecSize().add(specItem.getSpecSize());
				}
			}
		}
		
		//取key-value
		String unitValue = KeyValueHelper.get("product_unit_"+e.getUnit());
		e.setUnit(unitValue);
		
		if(item.getPid().equals("0")){//主类别
			e.setMainCatalogName(item.getName());
		}else{//子类别
			e.setChildrenCatalogName(item.getName());
			e.setChildrenCatalogCode(item.getCode());
			item = SystemManager.catalogsMap.get(item.getPid());
			getSession().setAttribute("selectMenu", item.getId());//商品属于的大类别就是菜单
			e.setMainCatalogName(item.getName());
			
			getRequest().setAttribute("childrenCatalogCode", item.getCode());
		}
		
		//如果商品已上架并且商品的库存数小于等于0，则更新商品为已下架
		if(e.getStatus()==Product.Product_status_y){
			if(e.getStock()<=0){
				e.product_sorry_str = "抱歉，商品已售卖完了。";
				
				//更新商品为已下架
//				Product p = new Product();
//				p.setId(e.getId());
//				p.setStatus(Product.Product_status_n);
//				productService.update(p);
			}
		}
		
		StringBuilder imagesBuff = new StringBuilder(e.getPicture() + FrontContainer.product_images_spider);
		//组装商品详情页的图片地址
		if(StringUtils.isNotBlank(e.getImages())){
			imagesBuff.append(e.getImages());
		}
		productImagesBiz(imagesBuff.toString());
		
		//加载商品参数
		e.setParameterList(productService.selectParameterList(e.getId()));
		
		//更新商品浏览次数
		Product p = new Product();
		p.setId(e.getId());
//		p.setHit(e.getHit()+1);//浏览次数++
		productService.updateHit(p);
		
		//加载指定商品的评论
		Comment comment = new Comment();
		comment.setProductID(e.getId());
		PagerModel commentPager = super.selectPagerModelByServices(commentService, comment);
		getRequest().setAttribute("commentPager", commentPager);
		super.pager = commentPager;//公用分页控件需要这么写。
		
		/*
		 * 加载和这个商品有关联的畅销商品和特价商品，显示到商品明细页面的左侧。
		 * 有关联的商品的选择方法是：加载该商品所在的子目录下的特定商品。考虑到性能问题，
		 * 这个必须借助缓存，事先我们将一些子目录下的畅销商品、特价商品 的前10来个加载到内存，然后用户访问这个页面的时候直接取内存即可。
		 */
//		e.setLeftProducts(loadProducts(1));
		
		
		String url = "/jsp/product/"+e.getId()+".jsp";
		logger.error("url = " + url);
		getRequest().setAttribute("productHTMLUrl",url);
		
		/**
		 * 是否需要显示到货通知的按钮
		 */
		if(e.getStock()<=0){
			Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);
			if(acc!=null){
				//如果用户之前没有填写过到货通知的申请，则可以提示用户填写。
				EmailNotifyProduct ep = new EmailNotifyProduct();
				ep.setAccount(acc.getAccount());
				ep.setProductID(e.getId());
				if(emailNotifyProductService.selectCount(ep)<=0){
					e.setShowEmailNotifyProductInput(true);
				}
			}
		}
		
		
		
		/*
		 * 检查，如果此商品是活动商品，则加载相应的活动信息。
		 */
		logger.error("e.getActivityID() = "+e.getActivityID());
		if(StringUtils.isNotBlank(e.getActivityID())){
			logger.error(">>>计算或拷贝此商品关联的活动的信息到此商品对象上。展示页面用==");
			Activity activity = SystemManager.activityMap.get(e.getActivityID());
			
			/**
			 * 计算或拷贝此商品关联的活动的信息到此商品对象上。展示页面用
			 */
			e.setFinalPrice(String.valueOf(e.caclFinalPrice()));
			e.setExpire(activity.isExpire());
			e.setActivityEndDateTime(activity.getActivityEndDateTime());
			e.setActivityType(activity.getActivityType());
			e.setDiscountType(activity.getDiscountType());
			e.setDiscountFormat(activity.getDiscountFormat());
			e.setActivityEndDateTime(activity.getEndDate());
			e.setMaxSellCount(activity.getMaxSellCount());
			e.setAccountRange(activity.getAccountRange());
			e.setExchangeScore(activity.getExchangeScore());
			e.setTuanPrice(activity.getTuanPrice());
			
			logger.error("finalPrice = " + e.getFinalPrice()+",expire = " + e.isExpire()+",activityEndDateTime="+e.getActivityEndDateTime()+",score="+e.getScore());
		
			/*
			 * 如果商品是活动商品，则查看商品明细页的时候自动选择导航菜单li
			 */
			if(activity.getActivityType().equals(Activity.activity_activityType_c)){
				getSession().setAttribute("selectMenu","activity");
			}else if(activity.getActivityType().equals(Activity.activity_activityType_j)){
				getSession().setAttribute("selectMenu","score");
			}else if(activity.getActivityType().equals(Activity.activity_activityType_t)){
				getSession().setAttribute("selectMenu","tuan");
			}
		}
		
		return "product";
	}
	
	/**
	 * 用户浏览的商品信息存储在session中
	 * 由于存储的数量有限，每一个sessin中只存储最近的10个商品，并且只存储一些基本的信息,如：商品ID、商品名称、现价、原价。
	 * 这里需要用到数量固定的缓存策略，最后浏览的商品在第一个位置
	 */
	private void saveHistoryProductToSession() {
//		List<Product> history_product_list = (List<Product>) getSession().getAttribute(FrontContainer.history_product_list);
		LinkedHashMap<String, Product> history_product_map = (LinkedHashMap<String, Product>) getSession().getAttribute(FrontContainer.history_product_map);
//		LinkedHashMap<String, String> map = new LRULinkedHashMap<String, String>(16, 0.75f, true);
		if(history_product_map==null){
			history_product_map = new LRULinkedHashMap<String, Product>(16, 0.75f, true);
			getSession().setAttribute(FrontContainer.history_product_map,history_product_map);
		}
		
		//添加浏览过的商品信息到集合
		Product pro = new Product();
		pro.setId(e.getId());
		pro.setName(e.getName());
		pro.setPrice(e.getPrice());
		pro.setNowPrice(e.getNowPrice());
		pro.setPicture(e.getPicture());
		history_product_map.put(e.getId(),pro);
		
		//分离数据，方便页面显示
//		Collection<Product> historyList = history_product_map.values();
//		for(int i=historyList.size()-1;i>=0;i--){
//			historyList.
//		}
	}

	/**
	 * 根据商品ID检查商品信息
	 * @return
	 */
	private Catalog checkProduct() {
		if(StringUtils.isBlank(e.getId())){
			throw new NullPointerException("商品ID不能为空！");
		}
		
		e = productService.selectById(getE().getId());
		if(e==null){
			throw new NullPointerException("根据商品ID查询不到指定的商品信息！");
		}
		if(StringUtils.isBlank(e.getCatalogID())){
			throw new NullPointerException("商品无类别！");
		}
		
		/**
		 * 如果商品绑定了赠品，则读取绑定的赠品信息
		 */
		if(StringUtils.isNotBlank(e.getGiftID())){
			Gift gift = giftService.selectById(e.getGiftID());
			e.setGift(gift);
		}
		
		/**
		 * 根据商品信息去查询它的分类
		 */
		Catalog item = SystemManager.catalogsMap.get(e.getCatalogID());
		if(item==null){
			throw new NullPointerException("商品数据异常！");
		}
		return item;
	}

	/**
	 * 商品详情页面，图片列表的处理
	 */
	private void productImagesBiz(String imagesStr) {
		if(StringUtils.isBlank(imagesStr)){
			return;
		}
		
		String[] images = imagesStr.split(FrontContainer.product_images_spider);
		logger.error("e.getImages()="+e.getImages());
		if(e.getProductImageList()==null){
			e.setProductImageList(new LinkedList<ProductImageInfo>());
		}else{
			e.getProductImageList().clear();
		}
		for(int i=0;i<images.length;i++){
			String img = images[i].trim();
			int lastIndex = img.lastIndexOf("_");
			String left = img.substring(0, lastIndex+1);
			String right = img.substring(lastIndex+2);
			logger.error("left = "+left+",right="+right);
			
			e.getProductImageList().add(new ProductImageInfo(left+"1"+right,left+"2"+right,left+"3"+right));
		}
	}
	
	/**
	 * 加载畅销、特价商品。后期此方法会改造成加载缓存内的商品信息
	 * @param type
	 * @return
	 */
//	@Deprecated
//	private List<Product> loadProducts(int type) {
//		Product p = new Product();
//		p.setTop(8);
//		if (type == 1) {
//			p.setIsnew(1);
//		} else if (type == 2) {
//			p.setSale(1);
//		} else if (type == 3) {
//			p.setHot(true);
//		}
//		return productService.selectList(p);
//	}
	
	/**
	 * 分页加载产品列表，每4个一行的显示
	 * @param p
	 * @return
	 * @throws Exception
	 */
	private List<Product> selectProductList0() throws Exception {
//		getE().setStatus(2);//加载已经上架的商品
		getE().setOrderBy(orderBy);//设置排序规则
		super.selectList();//分页搜索数据库中的商品
		List<Product> result = pager.getList();
		
//		doImage(result);
		return result;
//		return convert4(result);
	}
	
	/**
	 * 产品集合转换为页面可以显示的数据结构，4个产品显示一行
	 * @param result
	 * @return
	 */
//	private List<List<Product>> convert4(List<Product> result){
//		if(result==null || result.size()==0){
//			return null;
//		}
//		List<List<Product>> list = new LinkedList<List<Product>>();
//		int nn = 0;
//		int maxLen = 4;
//		List<Product> row = new LinkedList<Product>();
//		for(int i=0;i<result.size();i++){
//			Product ee = result.get(i);
//			row.add(ee);
//			nn++;
//			if(nn==maxLen){
//				list.add(row);
//				nn=0;
//				row = new LinkedList<Product>();
//			}
//			
//			if(result.size()==i+1){
//				if(row.size()==0){
//					row.add(ee);
//				}
//				list.add(row);
//			}
//			
//		}
//		return list;
//	}
	
	public boolean isEmpty(String value){
		if(value==null || value.trim().length()==0){
			return true;
		}
		return false;
	}
	
	/**
	 * 立即购买
	 * @return
	 */
//	public String buyNow(){
//		logger.error("buyNow...");
//		return "confirmOrder";
//	}
	
	
	
	/**
	 * 直接购买，添加到购物车
	 * @return
	 * @deprecated
	 */
//	public String buy(){
//		String id = getRequest().getParameter("#request.goodsDetail.id");
//		System.out.println("id="+id);
//		getE().clear();
//		getE().setId(id);
//		Product goods = productService.selectOne(getE());
//		
//		List<Product> goodsList = (List<Product>) getSession().getAttribute(WebGlobal.myCart);
//		if(goodsList==null){
//			goodsList = new LinkedList<Product>();
//		}
//		goodsList.add(goods);
//		getSession().setAttribute(WebGlobal.myCart, goodsList);
//		return "toCart";
//	}
	
	/**
	 * 门户加载指定页面的产品、以及公共的产品目录
	 * @return
	 */
//	public String list(){
//		getSession().setAttribute("selectMenu", menuID);
//		
//		//加载指定菜单关联的目录下的产品列表
//		IndexMenu indexMenu = new IndexMenu();
//		indexMenu.setId(menuID);
//		IndexMenu menu = indexmenuService.selectOne(indexMenu);
//		e.setSuperTypeID(menu.getCatalogID());
//		productService.selectList(e);
//		return "list";
//	}
	
	/**
	 * 获取新闻列表
	 * @return
	 * @throws Exception 
	 */
	public String newsList() throws Exception{
		News newsInfo = new News();
		newsInfo.setType(News.news_type_notice);
		
		setPager(super.selectPagerModelByServices(newsService, newsInfo));
		return "newsList";
	}
	
//	/**
//	 * 获取新闻详情
//	 * @return
//	 */
//	public String newsInfo() throws Exception{
//		String id = getRequest().getParameter("id");
//		String code = getRequest().getParameter("code");
//		logger.error("ProductAction.newsInfo=== id="+id+",code="+code);
//		
//		if(StringUtils.isNotBlank(id)){
//			
//			logger.error("newsInfo id = "+id);
//			if(StringUtils.isBlank(id)){
//				throw new NullPointerException("id is null");
//			}
//			
//			e.setNews(newsService.selectById(id));
//			
//			String url = "/jsp/notices/"+e.getId()+".jsp";
//			logger.error("url = " + url);
//			getRequest().setAttribute("newsInfoUrl",url);
//			
//			return "newsInfo";
//			
////			if(e.getNews().getType().equals(News.news_type_help)){
////				return "help";
////			}else if(e.getNews().getType().equals(News.news_type_notice)){
////				return "newsInfo";
////			}
//		}else if(StringUtils.isNotBlank(code)){
//			
//			logger.error("newsInfo code = "+code);
//			if(StringUtils.isBlank(code)){
//				throw new NullPointerException("code is null");
//			}
//			News news = new News();
//			news.setCode(code);
//			e.setNews(newsService.selectSimpleOne(news));
//			
//			String url = "/jsp/helps/"+e.getNews().getId()+".jsp";
//			logger.error("url = " + url);
//			getRequest().setAttribute("newsInfoUrl",url);
//			
//			return "help";
//			
////			if(e.getNews().getType().equals(News.news_type_help)){
////				return "help";
////			}else if(e.getNews().getType().equals(News.news_type_notice)){
////				return "newsInfo";
////			}
//		}
//		
//		return "newsInfo";
//	}
	
	/**
	 * 添加商品到收藏夹
	 * @return
	 * @throws IOException 
	 */
	public String addToFavorite() throws IOException{
		String productID = getRequest().getParameter("productID");
		if(StringUtils.isBlank(productID)){
			throw new NullPointerException(FrontContainer.request_illegal_error);
		}
		Account acc = (Account)getSession().getAttribute(FrontContainer.USER_INFO);
		if(acc==null || StringUtils.isBlank(acc.getAccount())){
			getResponse().getWriter().write("-1");
			return null;
		}
		Favorite favorite = new Favorite();
		favorite.setAccount(acc.getAccount());
		favorite.setProductID(productID);
		
		String result = null;
		synchronized (FrontContainer.insert_favorite_lock) {
			if(favoriteService.selectCount(favorite) == 0){
				favoriteService.insert(favorite);
				result = "0";//添加成功
			}else{
				result = "1";//已经添加过了
			}
		}
		getResponse().getWriter().write(result);
		return null;
	}
	
	/**
	 * 商品到货通知-ajax
	 * @return
	 * @throws IOException 
	 */
	public String insertEmailNotifyProductService() throws IOException{
		String productID = getRequest().getParameter("productID");
		String receiveEmail = getRequest().getParameter("receiveEmail");
		String productName = getRequest().getParameter("productName");
		if(StringUtils.isBlank(productID) || StringUtils.isBlank(receiveEmail)){
			throw new NullPointerException(FrontContainer.request_illegal_error);
		}
		
		Account acc = (Account)getSession().getAttribute(FrontContainer.USER_INFO);
		if(acc==null){
			getResponse().getWriter().write("-1");//用户需要登录
			return null;
		}
		
		EmailNotifyProduct info = new EmailNotifyProduct();
		info.setAccount(acc.getAccount());
		info.setReceiveEmail(receiveEmail);
		info.setProductID(productID);
		info.setProductName(productName);
		info.setStatus(EmailNotifyProduct.emailNotifyProduct_status_n);
		emailNotifyProductService.insert(info);
		
		getResponse().getWriter().write("0");//成功
		return null;
	}
	
	/**
	 * 加载促销活动的商品列表
	 * @return
	 * @throws Exception 
	 */
//	public String activityProductList() throws Exception{
//		logger.error("activityProductList...");
//		
//		//加载商品
////		productList = selectProductList0();
//		
//		//指定分页请求的地址
////		pager.setPagerUrl(special+".html");
//		return "activityProductList";
//	}
	
	/**
	 * test
	 * 内存库存查询
	 * @return
	 */
	public String selectMemoryStock(){
		
		return "selectMemoryStock";
	}
}

