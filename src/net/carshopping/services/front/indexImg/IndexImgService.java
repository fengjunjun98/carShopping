/**
 * 2012-7-8
 * jqsl2012@163.com
 */
package net.carshopping.services.front.indexImg;

import java.util.List;

import net.carshopping.core.Services;
import net.carshopping.services.front.indexImg.bean.IndexImg;
import net.carshopping.services.front.product.bean.Product;


/**
 * @author huangf
 */
public interface IndexImgService extends Services<IndexImg> {

	/**
	 * 加载图片显示到门户
	 * @param i
	 */
	List<IndexImg> getImgsShowToIndex(int i);

}
