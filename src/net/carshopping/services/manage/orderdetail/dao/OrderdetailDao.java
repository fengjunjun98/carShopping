package net.carshopping.services.manage.orderdetail.dao;import java.util.List;import net.carshopping.core.DaoManager;import net.carshopping.services.manage.orderdetail.bean.Orderdetail;import net.carshopping.web.action.manage.report.ReportInfo;public interface OrderdetailDao extends DaoManager<Orderdetail> {	List<ReportInfo> reportProductSales(Orderdetail orderdetail);}