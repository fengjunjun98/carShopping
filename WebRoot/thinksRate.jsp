<%@page import="net.carshopping.services.front.attribute.bean.Attribute"%>
<%@page import="net.carshopping.core.front.SystemManager"%>
<%@page import="net.carshopping.services.front.product.bean.Product"%>
<%@page import="net.carshopping.services.front.product.ProductService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.*"%>
<%@page import="net.carshopping.core.FrontContainer"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html class="no-js">
<head>
<%@ include file="/resource/common_html_meat.jsp"%>
<%@ include file="/resource/common_css.jsp"%>
<style type="text/css">
.topCss {
	height: 28px;
	line-height: 28px;
	background-color: #f8f8f8;
	border-bottom: 1px solid #E6E6E6;
	padding-left: 9px;
	font-size: 14px;
	font-weight: bold;
	position: relative;
	margin-top: 0px;
}
.left_product{
	font-size: 12px;display: inline-block;overflow: hidden;text-overflow: ellipsis;-o-text-overflow: ellipsis;white-space: nowrap;max-width: 150px;
}
img.err-product {
<%
if(StringUtils.isNotBlank(SystemManager.systemSetting.getDefaultProductImg())){
%>
background: url(<%=SystemManager.systemSetting.getDefaultProductImg()%>) no-repeat 50% 50%;
<%}%>
}
</style>
</head>

<body>
	<%@ include file="/indexMenu.jsp"%>

	<div class="container">
	
		<div class="row">
			<div class="col-xs-12">
				<p class="text-success">感谢您的评价!</p>
				<hr style="margin-top: 10px;">
				
				
				<div class="panel panel-default">
		              <div class="panel-body" style="font-size: 16px;font-weight: normal;text-align: center;">
			              <div class="panel-body" style="font-size: 16px;font-weight: normal;text-align: center;">
			              		<div class="text-success" style="font-size: 16px;font-weight: 700;">
									<span class="glyphicon glyphicon-ok"></span>&nbsp;点评成功！您已成功获得了5个积分！  
								</div>
			              </div>
		              </div>
				</div>
			</div>
		</div>
	</div>
	
<%@ include file="foot.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/resource/js/jquery-1.4.2.min.js"></script>
</body>
</html>
