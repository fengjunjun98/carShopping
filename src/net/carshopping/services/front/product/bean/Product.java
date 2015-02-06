package net.carshopping.services.front.product.bean;import java.io.Serializable;import java.text.DecimalFormat;import java.util.HashSet;import java.util.LinkedList;import java.util.List;import java.util.Set;import net.carshopping.core.front.SystemManager;import net.carshopping.services.front.news.bean.News;import net.carshopping.services.manage.activity.bean.Activity;import net.carshopping.services.manage.gift.bean.Gift;import net.carshopping.services.manage.spec.bean.Spec;import org.apache.commons.lang.StringUtils;/** * 商品 *  * @author huangf *  */public class Product extends net.carshopping.services.common.Product implements Serializable {	private static final long serialVersionUID = 1L;	private boolean hot;// 是否热门	private int orderBy;//排序规则。1：热门，2：价格，3：最新		private int top;// 促销前10个	private String endtime;// 查询录入的结束时间	private int buyCount = 1;// 购买该类产品的总数	private List<Integer> queryCatalogIDs;//查询目录集合，SQL语句会根据这个集合组装in语句查询	private String key;// 搜索关键字	private int attrID;	private int addSellcount;//销量增加数		/**	 * 活动的属性，页面显示用的	 */	private String finalPrice;//根据规则，计算出来的最终给活动价格	private String activityEndDateTime;//此商品的活动结束时间	private boolean expire;// 活动是否已经到期true:已到期；false:未到期	private String activityType;	private String discountType;//r:减免；d:折扣；s:双倍积分；m：秒杀	private String discountFormat;//折扣格式化显示	private int maxSellCount;//活动的最多购买数量	private String accountRange;//允许参与活动的会员范围	private int exchangeScore;//积分商城中商品的兑换积分	private int minGroupCount;//最低团购人数	private String tuanPrice;//团购价	private int hasBuyGroupPerson;//已参与的团购人数		@Deprecated	private List<String> imageList;//商品详情页面显示的商品图片列表	private List<ProductImageInfo> productImageList;//商品详情页面显示的商品图片列表	private List<Product> parameterList;//商品参数列表	private String value;//参数值		private List<Product> leftProducts;//商品详情页面的左侧商品列表	private String mainCatalogName;//主类别名称	private String childrenCatalogName;//子类别名称	private String childrenCatalogCode;//子类别code		public String product_sorry_str;	private String special;//促销商品标识。newest：新品；sale：特价	private List<String> productIds;//商品ID集合		private News news;	private boolean showEmailNotifyProductInput;//true:显示商品到货通知输入框	private String total0;//小计	private int totalExchangeScore;//所需总积分	private Spec buySpecInfo;//购买的此商品的规格ID	private Gift gift;//商品赠品信息		/**	 * 规格相关	 */	private String specJsonString;//规格JSON字符串	private Set<String> specColor;//颜色 	private Set<String> specSize;//尺寸		public void clear() {		super.clear();		hot = false;		orderBy = 0;		endtime = null;		buyCount = 1;		key = null;		if (queryCatalogIDs != null) {			queryCatalogIDs.clear();			queryCatalogIDs = null;		}		attrID = 0;		addSellcount = 0;		finalPrice = null;		activityEndDateTime = null;		expire = false;		activityType = null;		discountType = null;		discountFormat = null;		maxSellCount = 0;		accountRange = null;		exchangeScore = 0;		minGroupCount = 0;		tuanPrice = null;		hasBuyGroupPerson = 0;				if(imageList!=null){			imageList.clear();			imageList = null;		}		if(productImageList!=null){			for(int i=0;i<productImageList.size();i++){				productImageList.get(i).clear();			}			productImageList.clear();			productImageList = null;		}				if(parameterList!=null && parameterList.size()>0){			for(int i=0;i<parameterList.size();i++){				Product p = parameterList.get(i);				if(p!=null){					p.clear();					p = null;				}			}			parameterList = null;		}		value = null;				if(leftProducts!=null){			for(int i=0;i<leftProducts.size();i++){				Product p = leftProducts.get(i);				p.clear();				p = null;			}			leftProducts.clear();			leftProducts = null;		}				mainCatalogName = null;		childrenCatalogName = null;		childrenCatalogCode = null;		product_sorry_str = null;		special = null;				if(news!=null){			news.clear();			news = null;		}				super.clearList(productIds);		showEmailNotifyProductInput = false;		total0 = null;		totalExchangeScore = 0;		if(buySpecInfo!=null){			buySpecInfo.clear();			buySpecInfo = null;		}				if(gift!=null){			gift.clear();			gift = null;		}				specJsonString = null;		super.clearSet(specColor);		super.clearSet(specSize);	}		DecimalFormat df = new DecimalFormat("0.00");		/**	 * 计算活动商品的最终结算价。如果此商品不是活动商品，则直接返回此商品的现价	 * @return	 */	public String caclFinalPrice(){		ProductStockInfo momeryProduct = SystemManager.productStockMap.get(getId());		if(StringUtils.isBlank(momeryProduct.getActivityID())){//			throw new RuntimeException("此商品不属于活动商品！");			return super.getNowPrice();		}				double finalPrice = 0d;		Activity activity = SystemManager.activityMap.get(momeryProduct.getActivityID());		if(activity.getActivityType().equals(Activity.activity_activityType_c)){						String discountType = activity.getDiscountType();			if(discountType.equals(Activity.activity_discountType_r)){				finalPrice = Double.valueOf(super.getNowPrice()) - Double.valueOf(activity.getDiscount());							}else if(discountType.equals(Activity.activity_discountType_d)){				finalPrice = Double.valueOf(super.getNowPrice()) * Double.valueOf(activity.getDiscount()) / 100d;							}else if(discountType.equals(Activity.activity_discountType_s)){				//双倍积分的商品价格上不做优惠				finalPrice = Double.valueOf(super.getNowPrice());							}			return df.format(finalPrice);		}		return super.getNowPrice();	}	public List<Integer> getQueryCatalogIDs() {		if(queryCatalogIDs==null){			this.queryCatalogIDs = new LinkedList<Integer>();		}		return queryCatalogIDs;	}	public void setQueryCatalogIDs(List<Integer> queryCatalogIDs) {		this.queryCatalogIDs = queryCatalogIDs;	}	public String getKey() {		return key;	}	public void setKey(String key) {		this.key = key;	}	public int getBuyCount() {		return buyCount;	}	public void setBuyCount(int buyCount) {		this.buyCount = buyCount;	}	public String getEndtime() {		return endtime;	}	public void setEndtime(String endtime) {		this.endtime = endtime;	}	public int getTop() {		return top;	}	public void setTop(int top) {		this.top = top;	}	public int getOrderBy() {		return orderBy;	}	public void setOrderBy(int orderBy) {		this.orderBy = orderBy;	}	public int getAttrID() {		return attrID;	}	public void setAttrID(int attrID) {		this.attrID = attrID;	}	public List<String> getImageList() {		return imageList;	}	public void setImageList(List<String> imageList) {		this.imageList = imageList;	}	public boolean isHot() {		return hot;	}	public void setHot(boolean hot) {		this.hot = hot;	}	public List<Product> getParameterList() {		return parameterList;	}	public void setParameterList(List<Product> parameterList) {		this.parameterList = parameterList;	}	public String getValue() {		return value;	}	public void setValue(String value) {		this.value = value;	}	public List<Product> getLeftProducts() {		return leftProducts;	}	public void setLeftProducts(List<Product> leftProducts) {		this.leftProducts = leftProducts;	}	public List<ProductImageInfo> getProductImageList() {		return productImageList;	}	public void setProductImageList(List<ProductImageInfo> productImageList) {		this.productImageList = productImageList;	}	public String getMainCatalogName() {		return mainCatalogName;	}	public void setMainCatalogName(String mainCatalogName) {		this.mainCatalogName = mainCatalogName;	}	public String getChildrenCatalogName() {		return childrenCatalogName;	}	public void setChildrenCatalogName(String childrenCatalogName) {		this.childrenCatalogName = childrenCatalogName;	}	public String getSpecial() {		return special;	}	public void setSpecial(String special) {		this.special = special;	}	public News getNews() {		return news;	}	public void setNews(News news) {		this.news = news;	}	public List<String> getProductIds() {		return productIds;	}	public void setProductIds(List<String> productIds) {		this.productIds = productIds;	}	public boolean isShowEmailNotifyProductInput() {		return showEmailNotifyProductInput;	}	public void setShowEmailNotifyProductInput(boolean showEmailNotifyProductInput) {		this.showEmailNotifyProductInput = showEmailNotifyProductInput;	}	public int getAddSellcount() {		return addSellcount;	}	public void setAddSellcount(int addSellcount) {		this.addSellcount = addSellcount;	}	public String getChildrenCatalogCode() {		return childrenCatalogCode;	}	public void setChildrenCatalogCode(String childrenCatalogCode) {		this.childrenCatalogCode = childrenCatalogCode;	}	public String getTotal0() {		return total0;	}	public void setTotal0(String total0) {		this.total0 = total0;	}	public String getFinalPrice() {		return finalPrice;	}	public void setFinalPrice(String finalPrice) {		this.finalPrice = finalPrice;	}	public String getActivityEndDateTime() {		return activityEndDateTime;	}	public void setActivityEndDateTime(String activityEndDateTime) {		this.activityEndDateTime = activityEndDateTime;	}	public boolean isExpire() {		return expire;	}	public void setExpire(boolean expire) {		this.expire = expire;	}	public String getDiscountType() {		return discountType;	}	public void setDiscountType(String discountType) {		this.discountType = discountType;	}	public String getDiscountFormat() {		return discountFormat;	}	public void setDiscountFormat(String discountFormat) {		this.discountFormat = discountFormat;	}	public int getMaxSellCount() {		return maxSellCount;	}	public void setMaxSellCount(int maxSellCount) {		this.maxSellCount = maxSellCount;	}	public String getAccountRange() {		return accountRange;	}	public void setAccountRange(String accountRange) {		this.accountRange = accountRange;	}	public String getSpecJsonString() {		return specJsonString;	}	public void setSpecJsonString(String specJsonString) {		this.specJsonString = specJsonString;	}	public Set<String> getSpecColor() {		return specColor;	}	public void setSpecColor(Set<String> specColor) {		this.specColor = specColor;	}	public Set<String> getSpecSize() {		return specSize;	}	public void setSpecSize(Set<String> specSize) {		this.specSize = specSize;	}	public Spec getBuySpecInfo() {		return buySpecInfo;	}	public void setBuySpecInfo(Spec buySpecInfo) {		this.buySpecInfo = buySpecInfo;	}	public int getExchangeScore() {		return exchangeScore;	}	public void setExchangeScore(int exchangeScore) {		this.exchangeScore = exchangeScore;	}	public String getActivityType() {		return activityType;	}	public void setActivityType(String activityType) {		this.activityType = activityType;	}	public int getTotalExchangeScore() {		return totalExchangeScore;	}	public void setTotalExchangeScore(int totalExchangeScore) {		this.totalExchangeScore = totalExchangeScore;	}	public int getMinGroupCount() {		return minGroupCount;	}	public void setMinGroupCount(int minGroupCount) {		this.minGroupCount = minGroupCount;	}	public String getTuanPrice() {		return tuanPrice;	}	public void setTuanPrice(String tuanPrice) {		this.tuanPrice = tuanPrice;	}	public int getHasBuyGroupPerson() {		return hasBuyGroupPerson;	}	public void setHasBuyGroupPerson(int hasBuyGroupPerson) {		this.hasBuyGroupPerson = hasBuyGroupPerson;	}	public Gift getGift() {		return gift;	}	public void setGift(Gift gift) {		this.gift = gift;	}}