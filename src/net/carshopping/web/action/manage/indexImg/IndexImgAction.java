/** * 文件名：IndexImgAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.indexImg;
import java.io.File;
import java.io.IOException;

import net.carshopping.core.BaseAction;
import net.carshopping.services.manage.indexImg.bean.IndexImg;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
/** 项目名称：carShopping 
 * 类名称：IndexImgAction 
 * 滚动图片
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午02:59:56 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午02:59:56 
 * 修改备注：
 * @version 1.0* */

public class IndexImgAction extends BaseAction<IndexImg> {
	private static final long serialVersionUID = 1L;
	private File image;

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	@Override
	public IndexImg getE() {
		return this.e;
	}

	@Override
	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new IndexImg();
		}
	}

	@Override
	public void insertAfter(IndexImg e) {
		e.clear();
	}

	@Override
	public String selectList() throws Exception {
		super.selectList();
		return toList;
	}
	@Override
	protected void selectListAfter() {
		pager.setPagerUrl("indexImg!selectList.action");
	}
	@Override
	public String insert() throws Exception {
//		uploadImage();
		return super.insert();
	}
	
	@Override
	public String update() throws Exception {
//		uploadImage();
		return super.update();
	}
	
	//上传文件
	@Deprecated
	private void uploadImage() throws IOException{
		if(image==null){
			return;
		}
		String imageName = String.valueOf(System.currentTimeMillis()) + ".jpg";
		String realpath = ServletActionContext.getServletContext().getRealPath("/indexImg/");
		// D:\apache-tomcat-6.0.18\webapps\struts2_upload\images
		System.out.println("realpath: " + realpath);
		if (image != null) {
			File savefile = new File(new File(realpath), imageName);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			FileUtils.copyFile(image, savefile);
			ActionContext.getContext().put("message", "文件上传成功");
		}
//		SystemInfo sInfo = SystemSingle.getInstance().getSystemInfo();
//		String url = sInfo.getWww_ip() + "/file/img/" + imageName;
		String url = "/indexImg/" + imageName;
		e.setPicture(url);
		image = null;
	}
	
	/**
	 * 同步缓存
	 * @return
	 * @throws Exception 
	 */
	public String syncCache() throws Exception{
//		SystemSingle.getInstance().sync(Container.imgList);
		return super.selectList();
	}
}
