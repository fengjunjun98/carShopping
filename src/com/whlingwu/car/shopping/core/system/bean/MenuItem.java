/** * 文件名：MenuItem.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.system.bean;

/** 项目名称：carShopping 
 * 类名称：MenuItem 
 * 页面上显示的菜单项，每一个MenuItem对应一个节点
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午10:34:46 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午10:34:46 
 * 修改备注：
 * @version 1.0* */

public class MenuItem {
	private String id;// 0：根节点，否则是子节点
	private String pid;// 菜单项的父亲节点
	private String url;// 菜单的URL地址
	private String target = "rightFrame";// 打开的目标
	private String name;// 菜单名称
	private boolean open = false;// 是否展开
	private boolean checked;// true:勾选
	private List<MenuItem> children;// 子节点
	private MenuType type = MenuType.module;// module：模块 ,page：页面 ,button：功能
	private String icon;// 节点图标
	
	/**
	 *	指定菜单的功能类型
	 * @param menu
	 * @param item
	 */
	public void setMenuType(Menu menu) {
		if(menu!=null && StringUtils.isNotEmpty(menu.getType())){
			if(menu.getType().equals("module")){
				this.setType(MenuType.module);
			}else if(menu.getType().equals("page")){
				this.setType(MenuType.page);
			}else if(menu.getType().equals("button")){
				this.setType(MenuType.button);
			}
//			String manageHttp = "";
//			if(SystemManager.systemSetting!=null && SystemManager.systemSetting.getManageHttp()!=null){
//				manageHttp = SystemManager.systemSetting.getManageHttp();
//			}
			//为z-tree自定义图标
			if(this.getType().equals(MenuType.page)){
				this.setIcon(SystemManager.systemSetting.getManageLeftTreeLeafIcon());
//				this.setIcon("/myshop/resource/images/letter.gif");
//				this.setIcon(manageHttp+"../resource/images/letter.gif");
			}else if(this.getType().equals(MenuType.button)){
				this.setIcon(SystemManager.systemSetting.getManageLeftTreeLeafIcon());
//				this.setIcon(manageHttp+"../resource/images/reply.gif");
//				this.setIcon("/myshop/resource/images/reply.gif");
			}
		}
	}

	/**
	 * 判断是否是非按钮的功能
	 * 
	 * @return
	 */
	public boolean isButton() {
		if (this.type != null && this.type.equals(MenuType.button)) {
			return true;
		}
		return false;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<MenuItem> getChildren() {
		return children;
	}

	public void setChildren(List<MenuItem> children) {
		this.children = children;
	}

	public MenuItem(String name, List<MenuItem> children) {
		super();
		this.name = name;
		this.children = children;
	}

	public MenuItem() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public MenuType getType() {
		return type;
	}

	public void setType(MenuType type) {
		this.type = type;
	}

}

