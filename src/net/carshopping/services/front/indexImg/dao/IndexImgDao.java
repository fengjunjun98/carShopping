/**
 * 2012-7-8
 * jqsl2012@163.com
 */
package net.carshopping.services.front.indexImg.dao;

import java.util.List;

import net.carshopping.core.DaoManager;
import net.carshopping.services.front.indexImg.bean.IndexImg;


public interface IndexImgDao extends DaoManager<IndexImg> {

	/**
	 * @param i
	 * @return
	 */
	List<IndexImg> getImgsShowToIndex(int i);

}
