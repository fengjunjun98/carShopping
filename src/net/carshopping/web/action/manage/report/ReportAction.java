/** * 文件名：ReportAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.report;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.carshopping.core.BaseAction;
import net.carshopping.services.manage.order.OrderService;
import net.carshopping.services.manage.order.bean.Order;
import net.carshopping.services.manage.orderdetail.OrderdetailService;
import net.carshopping.services.manage.orderdetail.bean.Orderdetail;
import net.carshopping.services.manage.product.ProductService;
import net.carshopping.web.action.manage.order.OrderAction;
import net.carshopping.web.action.manage.product.ProductAction;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/** 项目名称：carShopping 
 * 类名称：ReportAction 图标，报表
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:12:46 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:12:46 
 * 修改备注：
 * @version 1.0* */

public class ReportAction extends BaseAction<ReportInfo> {
	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ReportAction.class);
	private ProductService	productService;
	private OrderService orderService;
	private OrderdetailService orderdetailService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setOrderdetailService(OrderdetailService orderdetailService) {
		this.orderdetailService = orderdetailService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("ReportInfo!selectList.action");
	}

	public ReportInfo getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new ReportInfo();
		}

		super.initPageSelect();
	}

	public void insertAfter(ReportInfo e) {
		e.clear();
	}
	
	/**
	 * 产品销售排行榜统计：图表的形式展示指定时间范围内的商品的销售情况，包括数量、金额等。
	 * @return
	 */
	public String productSales(){
//		List<ReportInfo> list = orderdetailService.reportProductSales(new Orderdetail());
//		return null;
		return "productSales";
	}
	
	/**
	 * 销量统计：统计选择的时间范围内的销量情况。
	 * @return
	 */
	public String orderSales(){
//		logger.error("reportOrderSales....");
//		try {
//			Order order = new Order();
//			order.setStartDate("2013-01");
//			order.setEndDate("2014-05");
//			List<ReportInfo> list = orderService.reportOrderSales(order);
//			if(list==null){
//				logger.error("reportOrderSales=0");
//			}else{
//				logger.error("reportOrderSales="+list.size());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return "orderSales";
	}
	
	/**
	 * 查询指定时间范围内的订单的销量情况
	 * @return
	 * @throws IOException 
	 */
	public String selectOrderSales() throws IOException{
		logger.error("selectOrderSales....startDate="+e.getStartDate()+",endDate="+e.getEndDate());
//		try {
//			Thread.sleep(5000L);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
		Order order = new Order();
		order.setStartDate(e.getStartDate());
		order.setEndDate(e.getEndDate());
//			if(StringUtils){
//				order.setStartDate("2013-01");
//				order.setEndDate("2014-05");
//			}
		List<ReportInfo> list = orderService.selectOrderSales(order);
		if(list==null){
			logger.error("reportOrderSales=0");
		}else{
			logger.error("reportOrderSales="+list.size());
		}
		if(list!=null && list.size()>0){
			Map<String, String> map = new LinkedHashMap<String, String>();
			StringBuilder amountBuff = new StringBuilder("[");
			StringBuilder createdateBuff = new StringBuilder("[");
			for(int i=0;i<list.size();i++){
				ReportInfo item = list.get(i);
				logger.error("item="+item.toString());
				amountBuff.append(item.getSumAmount());
				createdateBuff.append(item.getCreatedate());
				if(i!=list.size()-1){
					amountBuff.append(",");
					createdateBuff.append(",");
				}
			}
			amountBuff.append("]");
			createdateBuff.append("]");
			
			map.put("amountArr", amountBuff.toString());
			map.put("orderdateArr", createdateBuff.toString());
			
			logger.error("json="+JSON.toJSONString(map).toString());
			getResponse().getWriter().write(JSON.toJSONString(map).toString());
		}else{
			getResponse().getWriter().write("0");
		}
		return null;
	}
	
	/**
	 * 查询指定时间范围内的产品的销量情况
	 * @return
	 * @throws IOException 
	 */
	public String selectProductSales() throws IOException{
		logger.error("selectProductSales....startDate="+e.getStartDate()+",endDate="+e.getEndDate());
//		try {
//			Thread.sleep(5000L);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
		Order order = new Order();
		order.setStartDate(e.getStartDate());
		order.setEndDate(e.getEndDate());
		List<ReportInfo> list = orderService.selectProductSales(order);
		if(list==null){
			logger.error("reportOrderSales=0");
		}else{
			logger.error("reportOrderSales="+list.size());
		}
		if(list!=null && list.size()>0){
			Map<String, String> map = new LinkedHashMap<String, String>();
			StringBuilder productSellCountBuff = new StringBuilder("[");
			StringBuilder productNameBuff = new StringBuilder("[");
//			for(int i=0;i<list.size();i++){
			for(int i=list.size()-1;i>=0;i--){
				ReportInfo item = list.get(i);
//				logger.error("item="+item.toString());
				productSellCountBuff.append(item.getProductSellCount());
				productNameBuff.append("'"+item.getProductName()+"'");
//				if(i!=list.size()-1){
				if(i!=0){
					productSellCountBuff.append(",");
					productNameBuff.append(",");
				}
			}
			productSellCountBuff.append("]");
			productNameBuff.append("]");
			
			map.put("productSellCountArr", productSellCountBuff.toString());
			map.put("productNameArr", productNameBuff.toString());
			
			logger.error("json="+JSON.toJSONString(map).toString());
			getResponse().getWriter().write(JSON.toJSONString(map).toString());
		}else{
			getResponse().getWriter().write("0");
		}
		return null;
	}
}
