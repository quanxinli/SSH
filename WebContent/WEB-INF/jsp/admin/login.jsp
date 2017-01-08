<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../init/include.jsp" %>
 <%@include file="../init/easyui.jsp" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
<script type="text/javascript">
 $(function(){
		$("#logindiv").dialog({    
			   title: '新增菜单',
			   width: 250,
			   height: 250,
			   closed: false,
			  buttons:[{text:'保存',iconCls:'icon-save',handler:function(){
				  //alert($("#ff").serialize())
				  
				   //$.post("menu_save.html?",$("#ff").serialize());
				  $.ajax({
						url:"menu_save.html",
						type:"POST",
						async: false,//异步请求，完成再执行下一句
						data:$("#ff").serialize(),
						dataType:"json",
						success:function(rel){
							
						}
					});
				  $("#addMenu").dialog({  closed: true });
				  $('#tt').datagrid('reload');
			  }}]
			 
			    
			});
	 
 });
</script>
</head>
<body>

	<div id="logindiv" >
		<form id="loginform" action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
			<table>
				<tr>
					<td id="show">用户名</td>
					<td id="data"><input type="text" name="username" required="required"></td>
				</tr>
				<br/>
				<tr>
					<td id="show">密&nbsp;&nbsp;码</td>
					<td id="data"><input type="password" name="password" required="required">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="提交"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>