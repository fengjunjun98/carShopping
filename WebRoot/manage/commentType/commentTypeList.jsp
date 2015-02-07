<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page session="false"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/resource/common_html_meat.jsp"%>
<%@ include file="/manage/system/common.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function() {
		
		$("input[type='radio']").click(function(){
			console.log($(this).attr("_id"));
			
			if(!confirm("确定设置该选项为系统默认的评论插件吗?")){
				return ;
			}
			var _url = "commentType!updateDefaultCommentType.action?e.id="+$(this).attr("_id");
			$.ajax({
			  type: 'POST',
			  url: _url,
			  data: {},
			  success: function(data){
				    alert("设置默认评论插件成功！");
				    
				    document.form1.submit();
			  },
			  dataType: "text",
			  error:function(){
				  alert("设置默认评论插件失败！");
			  }
			});
		});
	});
</script>
</head>

<body>
	<s:form action="commentType!selectList.action?init=y" namespace="/manage" method="post" theme="simple" name="form1">
		<table class="table table-bordered">
			<tr>
				<td colspan="6">
<%-- 					<s:submit method="selectList" value="查询" cssClass="btn btn-primary" /> --%>
					<s:a method="selectList" cssClass="btn btn-primary">
						<i class="icon-search icon-white"></i> 查询
					</s:a>
<%-- 					<s:submit method="toAdd" value="添加" cssClass="btn btn-success" />  --%>
<%-- 					<s:submit method="deletes" onclick="return deleteSelect();" value="删除" cssClass="btn btn-danger" /> --%>
					<div style="float: right;vertical-align: middle;bottom: 0px;top: 10px;">
						<%@ include file="/manage/system/pager.jsp"%>
					</div>
				</td>
			</tr>
		</table>
		
		<table class="table table-bordered table-hover">
			<tr style="background-color: #dff0d8">
				<th width="20"><input type="checkbox" id="firstCheckbox" /></th>
				<th style="display: none;">编号</th>
				<th >名称</th>
				<th >代码</th>
				<th >状态</th>
<!-- 				<th >操作</th> -->
			</tr>
			<s:iterator value="pager.list">
				<tr>
					<td><input type="checkbox" name="ids"
						value="<s:property value="id"/>" /></td>
					<td style="display: none;">&nbsp;<s:property value="id" /></td>
					<td>&nbsp;<s:property value="name" /></td>
					<td>&nbsp;<s:property value="code" /></td>
					<td>&nbsp;
						<s:if test="status.equals(\"y\")">
							<input type="radio" name="e.status" checked="checked" _id="<s:property value="id" />"/>
							<img src="<%=request.getContextPath() %>/resource/images/action_check.gif">
						</s:if>
						<s:else>
							<input type="radio" name="e.status" _id="<s:property value="id" />"/>
							<img src="<%=request.getContextPath() %>/resource/images/action_delete.gif">
						</s:else>
					</td>
<%-- 					<td><s:a href="commentType!toEdit.action?e.id=%{id}">编辑</s:a></td> --%>
				</tr>
			</s:iterator>

			<tr>
				<td colspan="17" style="text-align: center;"><%@ include
						file="/manage/system/pager.jsp"%></td>
			</tr>
		</table>

	</s:form>
</body>
</html>
