package net.carshopping.services.manage.order.dao.impl;import java.util.List;import net.carshopping.core.dao.BaseDao;import net.carshopping.core.dao.page.PagerModel;import net.carshopping.services.manage.order.bean.Order;import net.carshopping.services.manage.order.bean.OrdersReport;import net.carshopping.services.manage.order.dao.OrderDao;import net.carshopping.web.action.manage.report.ReportInfo;public class OrderDaoImpl implements OrderDao {	private BaseDao dao;	public void setDao(BaseDao dao) {		this.dao = dao;	}	public PagerModel selectPageList(Order e) {		return dao.selectPageList("manage.order.selectPageList",				"manage.order.selectPageCount", e);	}	public List selectList(Order e) {		return dao.selectList("manage.order.selectList", e);	}	public Order selectOne(Order e) {		return (Order) dao.selectOne("manage.order.selectOne", e);	}	public int delete(Order e) {		return dao.delete("manage.order.delete", e);	}	public int update(Order e) {		return dao.update("manage.order.update", e);	}	public int deletes(String[] ids) {		Order e = new Order();		for (int i = 0; i < ids.length; i++) {			e.setId(ids[i]);			delete(e);		}		return 0;	}	public int insert(Order e) {		return dao.insert("manage.order.insert", e);	}	public int deleteById(int id) {		return dao.delete("manage.order.deleteById", id);	}	public Order selectById(String id) {		return (Order) dao.selectOne("manage.order.selectById",id);	}	@Override	public List<ReportInfo> selectOrderSales(Order order) {		return dao.selectList("manage.order.selectOrderSales", order);	}	@Override	public List<Order> selectCancelList(Order order) {		return dao.selectList("manage.order.selectCancelList",order);	}	@Override	public List<ReportInfo> selectProductSales(Order order) {		return dao.selectList("manage.order.selectProductSales", order);	}	@Override	public void updatePayMonery(Order e) {		dao.update("manage.order.updatePayMonery",e);	}	@Override	public OrdersReport loadOrdersReport() {		return (OrdersReport) dao.selectOne("manage.order.loadOrdersReport");	}}