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
			
				<s:if test="e.rateOrderdetailList!=null">
					<p class="text-success">您可以对下面的商品进行点评，点评后还可以获得一定的积分哦!</p>
					<hr style="margin-top: 10px;">
					
					<form action="<%=request.getContextPath()%>/order/doRate.html?orderid=<%=request.getParameter("orderid") %>" id="form" method="post" onsubmit="return checkRate();">
						<s:iterator value="e.rateOrderdetailList" status="i">
							<div class="row">
								<div class="col-xs-12">
									<div class="media">
										
										<a class="pull-left" href="<%=request.getContextPath() %>/product/<s:property value="productID" />.html" target="_blank" title="<s:property value="productName" />">
											<img class="media-object err-product" style="width: 100px;height: 100px;border: 0px;" alt="" src="<%=SystemManager.systemSetting.getImageRootPath()%><s:property value="picture" />" onerror="nofind()"/>
										</a>
									  <div class="media-body">
									    <h5 class="media-heading">对【<s:property escape="false" value="productName" />】进行评价：</h5>
									    <textarea class="form-control" name="product_<s:property escape="false" value="productID" />" rows="4" cols="80" ></textarea>
									  </div>
									</div>
								</div>
							</div>
							<hr>
						</s:iterator>
						
						<div style="text-align: center;">
							<input type="submit" id="rateBtn2" class="btn btn-primary" value="我来点评"/>
						</div>
					</form>
				</s:if>
				<s:else>
					<p class="text-success">感谢您的评价!</p>
					<hr style="margin-top: 10px;">
					
					
					<div class="panel panel-default">
			              <div class="panel-body" style="font-size: 16px;font-weight: normal;text-align: center;">
				              <div class="panel-body" style="font-size: 16px;font-weight: normal;text-align: center;">
				              		<div class="text-success" style="font-size: 16px;font-weight: 700;">
										<span class="glyphicon glyphicon-ban-circle"></span>&nbsp;您好，您当前的订单已经被点评过了！  
									</div>
				              </div>
			              </div>
					</div>
				</s:else>
			</div>
		</div>
	</div>
	
<%@ include file="foot.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/resource/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
function checkRate(){
	var hasValue = false;
	$("textarea").each(function(){
		if($.trim($(this).val()).length>0){
			hasValue = true;
		}
	});
	if(!hasValue){
		alert("您还没有进行过任何点评！");
		$("textarea:eq(0)").focus();
		return false;
	}
	return true;
}
</script>
</body>
</html>
