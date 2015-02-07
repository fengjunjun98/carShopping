package net.carshopping.services.front.systemlog.dao.impl;import java.util.List;import net.carshopping.core.dao.BaseDao;import net.carshopping.core.dao.page.PagerModel;import net.carshopping.services.front.systemlog.bean.Systemlog;import net.carshopping.services.front.systemlog.dao.SystemlogDao;public class SystemlogDaoImpl implements SystemlogDao {	private BaseDao dao;	public void setDao(BaseDao dao) {		this.dao = dao;	}	public PagerModel selectPageList(Systemlog e) {		return dao.selectPageList("systemlog.selectPageList",				"systemlog.selectPageCount", e);	}	public List selectList(Systemlog e) {		return dao.selectList("systemlog.selectList", e);	}	public Systemlog selectOne(Systemlog e) {		return (Systemlog) dao.selectOne("systemlog.selectOne", e);	}	public int delete(Systemlog e) {		return dao.delete("systemlog.delete", e);	}	public int update(Systemlog e) {		return dao.update("systemlog.update", e);	}	public int deletes(String[] ids) {		Systemlog e = new Systemlog();		for (int i = 0; i < ids.length; i++) {			e.setId(ids[i]);			delete(e);		}		return 0;	}	public int insert(Systemlog e) {		return dao.insert("systemlog.insert", e);	}	public int deleteById(int id) {		return dao.delete("systemlog.deleteById", id);	}	@Override	public Systemlog selectById(String id) {		// TODO Auto-generated method stub		return null;	}}