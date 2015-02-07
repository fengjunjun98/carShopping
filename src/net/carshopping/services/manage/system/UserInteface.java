package net.carshopping.services.manage.system;

import net.carshopping.core.Services;
import net.carshopping.core.dao.page.PagerModel;
import net.carshopping.core.system.bean.User;

public interface UserInteface extends Services<User> {
	/**
	 * @param e
	 * @return
	 */
	public User login(User e);
}
