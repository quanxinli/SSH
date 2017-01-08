<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../init/include.jsp" %>
 <%@include file="../init/easyui.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href='<c:url value="/images/favicon.ico"/>'/> 

<link rel="Bookmark" href='<c:url value="/images/favicon.ico"/>'>
<title>首页</title>
<script type="text/javascript">
	$(function(){
		$(".btn_menu").click(function () {
            var title = $(this).text();
            var url = $(this).attr("url");
            
            var isSelect = $("#content").tabs('exists', title);
            if (isSelect) {
                $("#content").tabs('select', title);
                return;
            }
            $("#content").tabs('add', {
                title: title,
               /*  selected: true, */
                content: CreateContent(url), 
                closable: true,
               
                
            });
           
        });
		
	});
	
	function CreateContent(url) {
        var strHtml = '<iframe src="' + url + '" scrolling="no" frameborder="0" fit="true" style="height:110%;width:100%;min-height:600px;"></iframe>';
        alert(strHtml);
        return strHtml;
    }
</script>
</head>
<body>
	<div title="网页头部" region="north" style="padding: 5px;height: 8%">12333</div>
	<div class="easyui-layout" style="width:100%;height:90%;">
		<div region="west" split="true" title="Menu" style="width:150px;">
			 <div id="menu" class="easyui-accordion" fit="true">
               <!--  a标签url属性中填写（/控制器名称/视图名称） -->
             <!--    <div title="快递服务" data-options="iconCls:'icon-print'" style=" overflow:auto;padding:10px;">
                    <ul class="easyui-tree">
                        <li><a href="javascript:;" class="btn_menu" url="list.html">申请记录</a></li>
                        <li><a href="javascript:;" class="btn_menu" url="apply.html">申请快递</a></li>
                        <li><a href="javascript:;" class="btn_menu" url="Replace_list.html">代填记录</a></li>
                        <li><a href="javascript:;" class="btn_menu" url="replace_apply.html">代填申请</a></li>
                        <li><a href="javascript:;" class="btn_menu" url="Audit_list.html">申请审批</a></li>
                        <li><a href="javascript:;" class="btn_menu" url="bill_list.html">账单记录</a></li>
                        <li><a href="javascript:;" class="btn_menu" url="service_list.html">服务统计</a></li>
                    </ul>
                </div> -->
          		
          		<c:forEach items="${firstList }" var="list" varStatus="index">
          			
          				<div title="${list.menuName}" data-options="iconCls:'icon-print'" style=" overflow:auto;padding:10px;">
                  	<ul class="easyui-tree">
                 	<c:forEach items="${SecondList}" var="p">
                 	  
                        <c:if test="${list.id==p.parent.id }">
                        	<li><a href="javascript:;" class="btn_menu" url='<c:url value="${p.menuUrl }"/>'>${p.menuName}</a></li>
                        </c:if>
                        
                   
                 	</c:forEach>
                  </ul>
                </div>
          			
          		</c:forEach> 
            </div>
		</div>
		
		 <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">
		 	<div id="content"  class="easyui-tabs" data-options="relize:true" ></div>
		 </div>  
		
		</div>

</body>
</html>