<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../init/include.jsp" %>
 <%@include file="../init/easyui.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>123</title>

<script type="text/javascript">
$(function(){
	//alert(333);
	$('#tt').datagrid({
		title:'DataGrid - GroupView',
		singleSelect:true,
		rownumbers:true,
		remoteSort:false,
		nowrap:false,
		fitColumns:true,
		url:'getMenuJson.html',
		columns:[[
			{field:'col_menu_id',title:'菜单ID',width:100,sortable:true,align:'center'},
			{field:'col_menu_name',title:'菜单名字',width:100,sortable:true,align:'center'},
			{field:'col_index',title:'菜单索引',width:100,sortable:true,align:'center'},
			{field:'col_ishide',title:'是否隐藏',width:100,sortable:true,align:'center'},
			{field:'col_top_name',title:'上级菜单',width:100,sortable:true,align:'center'},
			
		]],
		
		toolbar: [{
			text:'新增',
			iconCls: 'icon-add',
			handler: function(){
				$('#parent').combobox({    
				    url:'getFirstJson.html',    
				    valueField:'col_menu_id',    
				    textField:'col_menu_name',
				    editable:false
				});  
				$("#istop").change(function(){
					if($(this).is(":checked")){
						$("#remove-div").hide();
					}else{
						$("#remove-div").show();
					}
				});

				$("#addMenu").dialog({    
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

			}
		},{
			text:'删除',
			iconCls: 'icon-remove',
			handler: function(){
				var row = $('#tt').datagrid('getSelected');
				if(row){
					alert(row.col_menu_id);
					$.ajax({
						url:"menu_delete.html",
						type:"POST",
						async: false,//异步请求，完成再执行下一句
						data:{id:row.col_menu_id},
						dataType:"json",
						success:function(rel){}
					});
					$('#tt').datagrid('reload');
					alert('reload结束');
				  
				}else{
					alert('请选中行再操作！');
				}
			},
		}]

		
	});
});


	 

</script>

</head>
<body>
	<center>
	<div id="addMenu" class="easyui-dialog" closed="true" title="菜单新增"  style="margin: 0 auto" >
			<form id="ff" method="post">
				<div>
					<label for="name">菜单名称:</label> <input id="menuName" class="easyui-validatebox"
						type="text" name="menuName" data-options="required:true" />
				</div>
				<br />
				<div>
					<label for="email">菜单索引:</label> <input class="easyui-validatebox"
						type="text" name="index" data-options="" />
				</div>
				<br />
				<div>
					<label for="email">菜单 U rl:</label> <input id="menuUrl" class="easyui-validatebox"
						type="text" name="menuUrl" data-options="" />
				</div>
				<br />
				<div>
					<label for="email">是否顶级:</label> <input id='istop' class="easyui-validatebox"
						type="checkbox" name="istop" data-options="width:150" />
				</div>
				<br />
				 <div id="remove-div">
					<label for="email">上级菜单:</label> <input id='parent' class="easyui-validatebox"
						type="text" name="parent.id" data-options="width:150" />
				</div>

			</form>


		</div>
	<table id="tt" style="width: 80%;min-height: 500px;"></table>
	
	</center>
	
		
</body>
</html>