/** * 文件名：OrderAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.order;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import net.carshopping.core.BaseAction;
import net.carshopping.core.ManageContainer;
import net.carshopping.core.KeyValueHelper;
import net.carshopping.core.exception.UpdateOrderStatusException;
import net.carshopping.core.front.SystemManager;
import net.carshopping.core.system.bean.User;
import net.carshopping.services.front.area.bean.Area;
import net.carshopping.services.manage.order.OrderService;
import net.carshopping.services.manage.order.bean.Order;
import net.carshopping.services.manage.orderdetail.OrderdetailService;
import net.carshopping.services.manage.orderdetail.bean.Orderdetail;
import net.carshopping.services.manage.orderlog.OrderlogService;
import net.carshopping.services.manage.orderlog.bean.Orderlog;
import net.carshopping.services.manage.orderpay.OrderpayService;
import net.carshopping.services.manage.orderpay.bean.Orderpay;
import net.carshopping.services.manage.ordership.OrdershipService;
import net.carshopping.services.manage.ordership.bean.Ordership;
import net.carshopping.services.manage.product.ProductService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
/** 项目名称：carShopping 
 * 类名称：OrderAction  订单管理
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:04:49 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:04:49 
 * 修改备注：
 * @version 1.0* */

	public class OrderAction extends BaseAction<Order> {
		private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OrderAction.class);
		private static final long serialVersionUID = 1L;
		private OrderService orderService;
		private OrderpayService orderpayService;
		private OrdershipService ordershipService;
		private OrderdetailService orderdetailService;
		private ProductService productService;
		private OrderlogService orderlogService;
		
//		private Order order;
		private List<Orderdetail> orderdetailList;//订单项列表
		private String optionMsg;//操作消息提示
		private List<net.carshopping.services.front.area.bean.Area> areaList;//区域列表
		
		public List<Area> getAreaList() {
			return areaList;
		}

		public void setAreaList(List<Area> areaList) {
			this.areaList = areaList;
		}

		public OrderlogService getOrderlogService() {
			return orderlogService;
		}

		public void setOrderlogService(OrderlogService orderlogService) {
			this.orderlogService = orderlogService;
		}

		public String getOptionMsg() {
			return optionMsg;
		}

		public void setOptionMsg(String optionMsg) {
			this.optionMsg = optionMsg;
		}

		public OrdershipService getOrdershipService() {
			return ordershipService;
		}

		public void setOrdershipService(OrdershipService ordershipService) {
			this.ordershipService = ordershipService;
		}

		public OrderpayService getOrderpayService() {
			return orderpayService;
		}

		public void setOrderpayService(OrderpayService orderpayService) {
			this.orderpayService = orderpayService;
		}

		public ProductService getProductService() {
			return productService;
		}

		public void setProductService(ProductService productService) {
			this.productService = productService;
		}

		public OrderdetailService getOrderdetailService() {
			return orderdetailService;
		}

		public void setOrderdetailService(OrderdetailService orderdetailService) {
			this.orderdetailService = orderdetailService;
		}

		public List<Orderdetail> getOrderdetailList() {
			return orderdetailList;
		}

		public void setOrderdetailList(List<Orderdetail> orderdetailList) {
			this.orderdetailList = orderdetailList;
		}
		public OrderService getOrderService() {
			return orderService;
		}

		protected void selectListAfter() {
			pager.setPagerUrl("order!selectList.action");
		}

		public void setOrderService(OrderService orderService) {
			this.orderService = orderService;
		}

		public Order getE() {
			return this.e;
		}

		public void prepare() throws Exception {
			if (this.e == null) {
				this.e = new Order();
			}
			super.initPageSelect();
			
//			if(areaList!=null){
//				for(int i=0;i<areaList.size();i++){
//					areaList.get(i).clear();
//				}
//				areaList.clear();
//				areaList = null;
//			}
		}

		public void insertAfter(Order e) {
			e.clear();
		}
		
		/**
		 * 加载订单列表
		 */
		public String selectList() throws Exception {
			logger.error("selectList()...");
			super.selectList();
			
			if(pager.getList()!=null){
				//订单状态中文化显示。
				for(int i=0;i<pager.getList().size();i++){
					Order item = (Order) pager.getList().get(i);
					item.setStatusStr(KeyValueHelper.get("order_status_"+item.getStatus()));
					item.setPaystatusStr(KeyValueHelper.get("order_paystatus_"+item.getPaystatus()));
				}
			}
			
			return toList;
		}
		
		/**
		 * 退款管理、退货管理 页面必须直接显示与退款、退款状态相一致的数据
		 */
		@Override
		protected void setParamWhenInitQuery() {
			String refundStatus = getRequest().getParameter("refundStatus");
			String status = getRequest().getParameter("status");
			String paystatus = getRequest().getParameter("paystatus");
//			String notCancel = getRequest().getParameter("notCancel");
			logger.error("refundStatus="+refundStatus+",status="+status+",paystatus="+paystatus);
			
			if(StringUtils.isNotBlank(refundStatus)){
				e.setRefundStatus(refundStatus);
			}
			if(StringUtils.isNotBlank(status)){
				e.setStatus(status);
			}
			if(StringUtils.isNotBlank(paystatus)){
				e.setPaystatus(paystatus);
			}
		}
		
		/**
		 * 订单打印功能
		 * @return
		 * @throws Exception
		 */
		public String toPrint() throws Exception {
			if(StringUtils.isBlank(e.getId())){
				throw new NullPointerException("订单ID不能为空！");
			}
			
			//加载指定的订单信息
			e = orderService.selectById(e.getId());
			
			//加载收货人地址信息
			Ordership ordership = new Ordership();
			ordership.setOrderid(e.getId());
			ordership = ordershipService.selectOne(ordership);
			if(ordership==null){
				throw new NullPointerException("系统查询不到收货人地址信息！");
			}
			e.setOrdership(ordership);
			
			//加载订单项列表 以及 产品信息
			Orderdetail orderdetail = new Orderdetail();
			orderdetail.setOrderID(Integer.valueOf(e.getId()));
			orderdetailList = orderdetailService.selectList(orderdetail);
			if(orderdetailList==null){
				throw new NullPointerException("查询不到订单明细信息！");
			}
			e.setOrderdetail(orderdetailList);
			return "toPrint";
		}
		
		/**
		 * 查看订单详细信息
		 */
		public String toEdit() throws Exception {
			if(StringUtils.isBlank(e.getId())){
				throw new NullPointerException("订单ID不能为空！");
			}
			
			//加载指定的订单信息
			e = orderService.selectOne(getE());
			if(e==null){
				throw new NullPointerException("根据订单ID查询不到订单！");
			}
			
			//订单各种状态 中文化。这样做是为了考虑到以后国际化的需要
			if(StringUtils.isNotBlank(e.getStatus())){
				e.setStatusStr(KeyValueHelper.get("order_status_"+e.getStatus()));
			}
			if(StringUtils.isNotBlank(e.getRefundStatus())){
				e.setRefundStatusStr(KeyValueHelper.get("order_refundStatus_"+e.getRefundStatus()));
			}
			if(StringUtils.isNotBlank(e.getPaystatus())){
				e.setPaystatusStr(KeyValueHelper.get("order_paystatus_"+e.getPaystatus()));
			}
			
			//加载支付记录
			Orderpay orderpay = new Orderpay();
			orderpay.setOrderid(e.getId());
			e.setOrderpayList(orderpayService.selectList(orderpay));
			if(e.getOrderpayList()!=null){
				for(int i=0;i<e.getOrderpayList().size();i++){
					Orderpay orderpayInfo = e.getOrderpayList().get(i);
					String paymethod = KeyValueHelper.get("orderpay_paymethod_"+orderpayInfo.getPaymethod());
					orderpayInfo.setPaymethod(paymethod);
				}
			}
			
			//加载订单配送记录
			e.setOrdership(ordershipService.selectOne(new Ordership(e.getId())));
			
			//加载订单项列表 以及 产品信息
			Orderdetail orderdetail = new Orderdetail();
			orderdetail.setOrderID(Integer.valueOf(getE().getId()));
			orderdetailList = orderdetailService.selectList(orderdetail);
			if(orderdetailList==null || orderdetailList.size()==0){
				throw new NullPointerException("订单数据异常，订单未包含任何订单项数据！");
			}
			e.setOrderdetail(orderdetailList);
			
			//检查此订单是否含赠品
			for(int i=0;i<e.getOrderdetail().size();i++){
				Orderdetail item = e.getOrderdetail().get(i);
				if(StringUtils.isNotBlank(item.getGiftID())){
					e.setHasGift(true);
					break;
				}
			}
			
			//加载订单支付日志记录
			if(StringUtils.isNotBlank(e.getId())){
				e.setOrderlogs(orderlogService.selectList(new Orderlog(e.getId())));
				if(e.getOrderlogs()==null){
					e.setOrderlogs(Collections.EMPTY_LIST);
				}
				logger.error(">>>orderlogs.size="+e.getOrderlogs().size());
			}
			
			return toEdit;
		}
		
		/**
		 * 后台添加订单支付记录
		 * @return
		 * @throws Exception 
		 */
		public String insertOrderpay() throws Exception {
			logger.error(">>>addOrderpay...orderid="+e.getId());
			if(StringUtils.isBlank(e.getId())){
				throw new NullPointerException(ManageContainer.OrderAction_param_null);
			}
			
			checkStatus1();
			
			e.getOrderpay().setOrderid(e.getId());//订单ID
			e.getOrderpay().setTradeNo("tradeNoTest");
			e.getOrderpay().setPaystatus(Orderpay.orderpay_paystatus_y);//假设支付成功
			orderpayService.insert(e.getOrderpay());
			getRequest().getSession().setAttribute("optionMsg", "添加支付记录成功！");
			
			insertOrderlog(e.getId(),"【增加支付记录】增"+e.getOrderpay().getPayamount()+"￥;");
			
			Order oInfo = new Order();
			oInfo.setId(e.getId());
			oInfo.setPaystatus(Order.order_paystatus_y);//全额支付
			orderService.update(oInfo);
			
			toEdit2();
			return null;
		}
		
		/**
		 * 刷新指定订单的信息
		 * @throws IOException
		 */
		private void toEdit2() throws IOException {
			getResponse().sendRedirect("order!toEdit.action?e.id="+e.getId());
		}
		
		/**
		 * 设置订单为审核通过
		 * @return
		 * @throws IOException 
		 */
		public String updateOrderStatus() throws IOException{
			logger.error("updateOrderStatus id = "+e.getId()+",status="+e.getStatus());
			if(StringUtils.isBlank(e.getId()) || StringUtils.isBlank(e.getStatus())){
				throw new NullPointerException(ManageContainer.OrderAction_param_null);
			}
			
			Order orderInfo = orderService.selectById(e.getId());
			if(orderInfo==null){
				throw new UpdateOrderStatusException(ManageContainer.OrderAction_selectById_null);
			}
			
			/**
			 * 订单流程控制
			 */
			if(e.getStatus().equals(Order.order_status_cancel)){
				if(!(orderInfo.getStatus().equals(Order.order_status_init) 
						|| orderInfo.getStatus().equals(Order.order_status_pass))){
					throw new NullPointerException(ManageContainer.OrderAction_updatePayMonery_cancel);
				}
			}
			
			if(orderInfo.getStatus().equals(Order.order_status_cancel)){//已取消的订单不能再进行任何操作了
				throw new NullPointerException(ManageContainer.OrderAction_updateOrderStatus_alreadyCancel);
			}else{
				/*
				 * 未被取消的订单的状态只能往前推进，不可后撤。
				 */
				if(orderInfo.getStatus().equals(Order.order_status_init)){
					if(!e.getStatus().equals(Order.order_status_pass)){
						throw new RuntimeException(ManageContainer.OrderAction_updateOrderStatus_statusException);
					}
				}else if(orderInfo.getStatus().equals(Order.order_status_pass)){
					if(!e.getStatus().equals(Order.order_status_send)){
						throw new RuntimeException(ManageContainer.OrderAction_updateOrderStatus_statusException);
					}
				}else if(orderInfo.getStatus().equals(Order.order_status_send)){
					if(!e.getStatus().equals(Order.order_status_sign)){
						throw new RuntimeException(ManageContainer.OrderAction_updateOrderStatus_statusException);
					}
				}else if(orderInfo.getStatus().equals(Order.order_status_sign)){
					if(!e.getStatus().equals(Order.order_status_file)){
						throw new RuntimeException(ManageContainer.OrderAction_updateOrderStatus_statusException);
					}
				}
			}
			
			if(e.getStatus().equals(Order.order_status_send)){
//				if(orderInfo.getStatus().equals(Order.order_status_pass)){
//					//非法请求
//					throw new NullPointerException(ManageContainer.RoleAction_update_error);
//				}
				//检查此订单是否【已发货】。如果已经发货，则直接跳转到订单明细接口。
				if(StringUtils.isNotBlank(orderInfo.getExpressNo())){
					toEdit2();
					return null;
				}
				
				/**
				 * 转到发货页面
				 */
				Orderpay orderpay = new Orderpay();
				orderpay.setOrderid(e.getId());
				orderpay.setPaystatus(Orderpay.orderpay_paystatus_y);
				orderpay = orderpayService.selectOne(orderpay);
				//检查订单是否已经支付成功
				if(orderpay==null || StringUtils.isBlank(orderpay.getTradeNo())){
					//非法请求
					throw new NullPointerException(ManageContainer.RoleAction_update_error);
				}
				e.setTradeNo(orderpay.getTradeNo());
				
				/*
				 * 转到发货页面==》请求支付宝发货接口，如果成功支付宝会将此订单的状态设置为【已发货】
				 * ***注意：如果是财付通或其他的支付接口，则需要调对应的发货接口才行。
				 */
				return "toSendProduct";
			}
			
			//修改订单状态
			Order order = new Order();
			order.setStatus(e.getStatus());
			order.setId(e.getId());
			orderService.update(order);
			
			//记录日志
			if(e.getStatus().equals(Order.order_status_pass)){
				insertOrderlog(e.getId(),"【审核通过】");
			}else if(e.getStatus().equals(Order.order_status_send)){
				insertOrderlog(e.getId(),"【已发货】");
			}else if(e.getStatus().equals(Order.order_status_sign)){
				insertOrderlog(e.getId(),"【已签收】");
			}else if(e.getStatus().equals(Order.order_status_file)){
				insertOrderlog(e.getId(),"【已归档】");
			}else if(e.getStatus().equals(Order.order_status_cancel)){
				insertOrderlog(e.getId(),"【取消订单】");
			}
			
			toEdit2();
			return null;
		}

		/**
		 * 插入订单操作日志
		 * @param orderid	订单ID
		 * @param content	日志内容
		 */
		private void insertOrderlog(String orderid,String content) {
			User user = (User)getRequest().getSession().getAttribute(ManageContainer.manage_session_user_info);
			Orderlog orderlog = new Orderlog();
			orderlog.setOrderid(orderid);//订单ID
			orderlog.setAccount(user.getUsername());//操作人账号
			orderlog.setContent(content);//日志内容
			orderlog.setAccountType(Orderlog.orderlog_accountType_m);
			orderlogService.insert(orderlog);
		}

		/**
		 * 设置订单为取消
		 * @return
		 * @throws IOException 
		 */
//		public String cancel() throws IOException{
//			if(StringUtils.isBlank(e.getId())){
//				throw new NullPointerException();
//			}
//			
//			Order order = new Order();
//			order.setStatus(Order.order_status_cancel);
//			order.setId(e.getId());
//			orderService.update(order);
//			
//			insertOrderlog(e.getId(),"【取消订单】");
//			
//			toEdit2();
//			return null;
//		}
		
		/**
		 * 设置订单为已发货
		 * @return
		 * @throws IOException 
		 */
//		public String setSend() throws IOException{
//			if(StringUtils.isBlank(e.getId())){
//				throw new NullPointerException();
//			}
//			
//			Order order = new Order();
//			order.setStatus(Order.order_status_send);
//			order.setId(e.getId());
//			orderService.update(order);
//			
//			insertOrderlog(e.getId(),"【已发货】");
//			
//			toEdit2();
//			return null;
//		}
		
		/**
		 * 设置订单为已签收
		 * @return
		 * @throws IOException 
		 */
//		public String setSign() throws IOException{
//			if(StringUtils.isBlank(e.getId())){
//				throw new NullPointerException();
//			}
//			
//			Order order = new Order();
//			order.setStatus(Order.order_status_sign);
//			order.setId(e.getId());
//			orderService.update(order);
//			
//			insertOrderlog(e.getId(),"【已签收】");
//			
//			toEdit2();
//			return null;
//		}
		
		/**
		 * 设置订单为已归档
		 * @return
		 * @throws IOException 
		 */
//		public String setFile() throws IOException{
//			if(StringUtils.isBlank(e.getId())){
//				throw new NullPointerException();
//			}
//			
//			Order order = new Order();
//			order.setStatus(Order.order_status_file);
//			order.setId(e.getId());
//			orderService.update(order);
//			
//			insertOrderlog(e.getId(),"【已归档】");
//			
//			toEdit2();
//			return null;
//		}
		
		/**
		 * 修改订单的各种状态
		 * @return
		 * @throws Exception
		 */
//		@Deprecated
//		public String changeOrderStatus() throws Exception {
//			logger.error(">>>changeOrderStatus...");
////			String aaa = getRequest().getParameter("aaa");
////			log.error(">>>changeOrderStatus...aaa="+this.aaa);
//			return null;
//		}

		/**
		 * 后台修改订单总金额
		 * @return
		 * @throws Exception
		 */
		public String updatePayMonery() throws Exception {
			checkStatus1();
			
			logger.error("updatePayMonery = id = " + e.getId() + ",amount = " + e.getAmount());
			User user = (User)getRequest().getSession().getAttribute(ManageContainer.manage_session_user_info);
			orderService.updatePayMonery(e,user.getUsername());
			
			toEdit2();
			return null;
		}

		/**
		 * 后台编辑订单页面，添加支付记录、修改订单总金额 操作之前需要进行如下的判断，这2个按钮的操作必须是订单为未审核 且 订单未支付 才可以，否则抛出异常。
		 */
		private void checkStatus1() {
			Order orderInfo = orderService.selectById(e.getId());
			if(orderInfo==null){
				throw new NullPointerException(ManageContainer.OrderAction_selectById_null);
			}
			
			/**
			 * 订单流程控制
			 */
			if(!orderInfo.getStatus().equals(Order.order_status_init)){
				throw new UpdateOrderStatusException(ManageContainer.OrderAction_updatePayMonery_update);
			}
			
			if(!orderInfo.getPaystatus().equals(Order.order_paystatus_n)){
				throw new UpdateOrderStatusException("未支付的订单才支持此操作！");
			}
		}
		
		/**
		 * 查询订单的配送地址信息-->然后后台工作人员可以进行修改
		 * @return
		 */
		public String selectOrdership(){
			String orderid = getRequest().getParameter("orderid");
			if(StringUtils.isBlank(orderid)){
				throw new NullPointerException("非法请求！");
			}
			
			Ordership ordership = new Ordership();
			ordership.setOrderid(orderid);
			ordership = ordershipService.selectOne(ordership);
			if(ordership==null){
				throw new NullPointerException("根据订单ID查询不到该订单的配送信息！");
			}
			
			e.setOrdership(ordership);
			
//			areaList
			//获取区域列表
			if(StringUtils.isNotBlank(ordership.getArea())){
//						address.getArea()
				net.carshopping.services.front.area.bean.Area area = SystemManager.areaMap.get(ordership.getProvinceCode());
				if(area!=null && area.getChildren()!=null && area.getChildren().size()>0){
					for(int i=0;i<area.getChildren().size();i++){
						net.carshopping.services.front.area.bean.Area city = area.getChildren().get(i);
						if(city.getCode().equals(ordership.getCityCode())){
							
//							logger.error("address.getCity()="+address.getCity());
//							logger.error(city.toString());
//							address.setAreaList(city.getChildren());
							areaList = city.getChildren();
							break;
						}
					}
				}
			}
			
			return "selectOrdership";
		}
		
		/**
		 * 修改订单配送地址信息
		 * @return
		 * @throws IOException 
		 */
		public String updateOrdership() throws IOException{
			logger.error("updateOrdership...");
			if(StringUtils.isBlank(e.getOrdership().getShipname())){
				throw new NullPointerException("收货人不能为空！");
			}else if(StringUtils.isBlank(e.getOrdership().getShipaddress())){
				throw new NullPointerException("收货人街道地址不能为空！");
			}else if(StringUtils.isBlank(e.getOrdership().getTel())){
				throw new NullPointerException("收货人手机号码！");
			}else if(StringUtils.isBlank(e.getOrdership().getProvince())){
				throw new NullPointerException("省份人不能为空！");
			}else if(StringUtils.isBlank(e.getOrdership().getCity())){
				throw new NullPointerException("城市人不能为空！");
			}
			
			if(StringUtils.isBlank(e.getId())){
				throw new NullPointerException(ManageContainer.OrderAction_param_null);
			}
			
			Order order = orderService.selectById(e.getId());
			if(order==null){
				throw new NullPointerException("查询不到订单信息!");
			}
			
			if(!order.getStatus().equals(Order.order_status_init)){
				throw new RuntimeException("只有【未审核】的订单才能修改收货人配送地址信息!");
			}
			
			
			ordershipService.update(e.getOrdership());
			
			toEdit2();
			return null;
		}

		/**
		 * 根据省份编码获取城市列表
		 * @return
		 * @throws IOException 
		 */
		public String selectCitysByProvinceCode() throws IOException{
			logger.error("selectCitysByProvinceCode...");
			String provinceCode = getRequest().getParameter("provinceCode");
			logger.error("selectCitysByProvinceCode...provinceCode="+provinceCode);
			if(StringUtils.isBlank(provinceCode)){
				throw new NullPointerException("provinceCode is null");
			}
			
//			Area area = new Area();
//			area.setCode(provinceCode);
			if(SystemManager.areaMap!=null && SystemManager.areaMap.size()>0){
				net.carshopping.services.front.area.bean.Area areaInfo = SystemManager.areaMap.get(provinceCode);
				
				logger.error("areaInfo = " + areaInfo);
				
				if(areaInfo!=null && areaInfo.getChildren()!=null && areaInfo.getChildren().size()>0){
					String jsonStr = JSON.toJSONString(areaInfo.getChildren());
					logger.error("jsonStr="+jsonStr);
					super.utf8JSON();
					getResponse().getWriter().write(jsonStr);
					return null;
				}
			}
			
			getResponse().getWriter().write("{}");
			return null;
		}

		/**
		 * 根据城市编码获取区域列表
		 * @return
		 * @throws IOException 
		 */
		public String selectAreaListByCityCode() throws IOException{
			logger.error("selectAreaListByCityCode...");
			String provinceCode = getRequest().getParameter("provinceCode");
			String cityCode = getRequest().getParameter("cityCode");
			logger.error("selectAreaListByCityCode...provinceCode="+provinceCode+",cityCode="+cityCode);
			if(StringUtils.isBlank(provinceCode) || StringUtils.isBlank(cityCode)){
				throw new NullPointerException("provinceCode or cityCode is null");
			}
			
			if(SystemManager.areaMap!=null && SystemManager.areaMap.size()>0){
				net.carshopping.services.front.area.bean.Area city = SystemManager.areaMap.get(provinceCode);
				
				logger.error("areaInfo = " + city);
				
				if(city!=null && city.getChildren()!=null && city.getChildren().size()>0){
					for(int i=0;i<city.getChildren().size();i++){
						net.carshopping.services.front.area.bean.Area item = city.getChildren().get(i);
						if(item.getCode().equals(cityCode)){
							if(item.getChildren()!=null && item.getChildren().size()>0){
								String jsonStr = JSON.toJSONString(item.getChildren());
								logger.error("jsonStr="+jsonStr);
								super.utf8JSON();
								getResponse().getWriter().write(jsonStr);
								return null;
							}
						}
					}
				}
			}
			
			getResponse().getWriter().write("{}");
			return null;
		}
	}

