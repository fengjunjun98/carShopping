/** * 文件名：OssAction.java 
 * * 版本信息： 
 * 日期：2015-2-6 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package net.carshopping.web.action.manage.oss;
import java.util.HashMap;
import java.util.Map;

import net.carshopping.core.BaseAction;
import net.carshopping.core.ManageContainer;
import net.carshopping.core.KeyValueHelper;
import net.carshopping.core.exception.NotThisMethod;
import net.carshopping.core.oscache.FrontCache;
import net.carshopping.core.oscache.ManageCache;
import net.carshopping.services.manage.oss.OssService;
import net.carshopping.services.manage.oss.bean.AliyunOSS;
import net.carshopping.services.manage.oss.bean.Oss;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
/** 项目名称：carShopping 
 * 类名称：OssAction 存储方式
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-2-6 下午03:07:16 
 * 修改人：凤军军 
 * 修改时间：2015-2-6 下午03:07:16 
 * 修改备注：
 * @version 1.0* */

public class OssAction extends BaseAction<Oss> {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(OssAction.class);
	private OssService ossService;
	private ManageCache manageCache;

	public ManageCache getManageCache() {
		return manageCache;
	}

	public void setManageCache(ManageCache manageCache) {
		this.manageCache = manageCache;
	}

	public OssService getOssService() {
		return ossService;
	}

	protected void selectListAfter() {
		pager.setPagerUrl("oss!selectList.action");
	}

	public void setOssService(OssService ossService) {
		this.ossService = ossService;
	}

	public Oss getE() {
		return this.e;
	}

	public void prepare() throws Exception {
		if (this.e == null) {
			this.e = new Oss();
		}
	}

	public void insertAfter(Oss e) {
		e.clear();
	}
	
	@Override
	public String insert() throws Exception {
		comm();
		getServer().insert(getE());
		e.clear();
		manageCache.loadOSS();
		return selectList();
	}
	
	@Override
	public String update() throws Exception {
		comm();
		getServer().update(getE());
		e.clear();
		manageCache.loadOSS();
		return selectList();
	}
	
	@Override
	public String deletes() throws Exception {
		throw new NotThisMethod(ManageContainer.not_this_method);
	}
	
	//根据code获取名称
	private void comm() {
		logger.error("comm..code="+e.getCode());
		
		if(StringUtils.isBlank(e.getCode())){
			throw new NullPointerException("code不能为空！");
		}
//		if(StringUtils.isBlank(e.getOssJsonInfo())){
//			throw new NullPointerException("配置信息不能为空！");
//		}
		
		String name = KeyValueHelper.get("oss_code_"+e.getCode());
		if(StringUtils.isBlank(name)){
			throw new NullPointerException("未配置"+e.getCode()+"的存储方式的键值对！");
		}
		
		e.setName(name);
		
		/*
		 * 对配置信息项进行检查
		 */
		if(e.getCode().equals(Oss.code_aliyun)){//阿里云
			logger.error("e.getAliyunOSS().toString()="+e.getAliyunOSS().toString());
			
			try {
				e.setOssJsonInfo(JSON.toJSONString(e.getAliyunOSS()));
			} catch (Exception e) {
				e.printStackTrace();
			}
//			AliyunOSS oss = JSON.parseObject(e.getOssJsonInfo(), AliyunOSS.class);
//			if(oss==null){
//				throw new RuntimeException("阿里云OSS的配置信息错误！");
//			}
		}
	}
	
	@Override
	public String toEdit() throws Exception {
		super.toEdit();
		if(StringUtils.isNotBlank(e.getOssJsonInfo())){
			try {
				e.setAliyunOSS(JSON.parseObject(e.getOssJsonInfo(), AliyunOSS.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return toEdit;
	}
	
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("ACCESS_ID", "xx");
		map.put("ACCESS_KEY", "xx");
		map.put("OSS_ENDPOINT", "http://oss.aliyuncs.com/");
		map.put("bucketName", "xx");
		System.out.println(JSON.toJSONString(map));
	}
}

