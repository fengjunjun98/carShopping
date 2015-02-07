/**
 * 2012-7-8
 * jqsl2012@163.com
 */
package net.carshopping.services.manage.indexImg.dao;

import java.util.List;

import net.carshopping.core.DaoManager;
import net.carshopping.services.manage.indexImg.bean.IndexImg;


public interface IndexImgDao extends DaoManager<IndexImg> {

	/**
	 * @param i
	 * @return
	 */
	List<IndexImg> getImgsShowToIndex(int i);

}
