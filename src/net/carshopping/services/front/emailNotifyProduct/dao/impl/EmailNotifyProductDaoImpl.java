package net.carshopping.services.front.emailNotifyProduct.dao.impl;import java.util.List;import net.carshopping.core.dao.BaseDao;import net.carshopping.core.dao.page.PagerModel;import net.carshopping.services.front.emailNotifyProduct.bean.EmailNotifyProduct;import net.carshopping.services.front.emailNotifyProduct.dao.EmailNotifyProductDao;public class EmailNotifyProductDaoImpl implements EmailNotifyProductDao {	private BaseDao dao;	public void setDao(BaseDao dao) {		this.dao = dao;	}	public PagerModel selectPageList(EmailNotifyProduct e) {		return dao.selectPageList("front.emailNotifyProduct.selectPageList",				"front.emailNotifyProduct.selectPageCount", e);	}	public List selectList(EmailNotifyProduct e) {		return dao.selectList("front.emailNotifyProduct.selectList", e);	}	public EmailNotifyProduct selectOne(EmailNotifyProduct e) {		return (EmailNotifyProduct) dao.selectOne(				"front.emailNotifyProduct.selectOne", e);	}	public int delete(EmailNotifyProduct e) {		return dao.delete("front.emailNotifyProduct.delete", e);	}	public int update(EmailNotifyProduct e) {		return dao.update("front.emailNotifyProduct.update", e);	}	public int deletes(String[] ids) {		EmailNotifyProduct e = new EmailNotifyProduct();		for (int i = 0; i < ids.length; i++) {			e.setId(ids[i]);			delete(e);		}		return 0;	}	public int insert(EmailNotifyProduct e) {		return dao.insert("front.emailNotifyProduct.insert", e);	}	public int deleteById(int id) {		return dao.delete("front.emailNotifyProduct.deleteById", id);	}	@Override	public EmailNotifyProduct selectById(String id) {		return (EmailNotifyProduct) dao.selectOne(				"front.emailNotifyProduct.selectById", id);	}	@Override	public int selectCount(EmailNotifyProduct ep) {		Object obj = dao.selectOne("front.emailNotifyProduct.selectCount", ep);		if(obj==null)return 0;		return Integer.valueOf(obj.toString());	}}