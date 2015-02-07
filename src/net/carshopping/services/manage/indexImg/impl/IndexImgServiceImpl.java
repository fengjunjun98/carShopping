/**
 * 2012-7-8
 * jqsl2012@163.com
 */
package net.carshopping.services.manage.indexImg.impl;

import java.util.List;

import net.carshopping.core.ServersManager;
import net.carshopping.services.manage.indexImg.IndexImgService;
import net.carshopping.services.manage.indexImg.bean.IndexImg;
import net.carshopping.services.manage.indexImg.dao.IndexImgDao;


/**
 * @author huangf
 */
public class IndexImgServiceImpl extends ServersManager<IndexImg> implements
		IndexImgService {

	private IndexImgDao indexImgDao;

	public IndexImgDao getIndexImgDao() {
		return indexImgDao;
	}

	public void setIndexImgDao(IndexImgDao indexImgDao) {
		this.indexImgDao = indexImgDao;
	}

	@Override
	public List<IndexImg> getImgsShowToIndex(int i) {
		return indexImgDao.getImgsShowToIndex(i);
	}

}
