package net.carshopping.services.front.navigation.impl;import net.carshopping.core.ServersManager;import net.carshopping.services.front.navigation.NavigationService;import net.carshopping.services.front.navigation.bean.Navigation;import net.carshopping.services.front.navigation.dao.NavigationDao;public class NavigationServiceImpl extends ServersManager<Navigation> implements		NavigationService {	private NavigationDao navigationDao;	public void setNavigationDao(NavigationDao navigationDao) {		this.navigationDao = navigationDao;	}}