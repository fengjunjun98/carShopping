<%@page import="net.carshopping.core.front.SystemManager"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.*"%>
<%@page import="net.carshopping.services.front.news.bean.News"%>
<%@page import="net.carshopping.core.ManageContainer"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script src="<%=request.getContextPath() %>/resource/js/superSlide/jquery.SuperSlide.js"></script>
<script type="text/javascript">
jQuery(".picScroll-top").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"top",autoPlay:true,
	scroll:2,vis:5});
jQuery(".slideTxtBox").slide();
</script>

