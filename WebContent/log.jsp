<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生列表</title>
<script type="text/javascript">
	
	$(function(){
		/* 		$("#nav a").click(function(){
			var title = $(this).text();
			var url = $(this).attr("url");
			if(title){
				if($('#tt').tabs('exists',title)){//选项页存在
					$('#tt').tabs('select',title);//选中
				}else{
					$('#tt').tabs('add',{    
					    title:title,    
					    content:"<iframe src='"+url+"' style='width: 100%;height: 100%' frameborder='0'></iframe>",
					    closable:true 
					});
				}
			}
		});
		 */
		/*  $("#nav").tree({
			 url:"menu/queryAll",
			 onClick: function(node){// 在用户点击的时候提示
			 	var title = node.text;
				var url = node.attributes.url;
				if(title&&url!=''){
					if($('#tt').tabs('exists',title)){//选项页存在
						$('#tt').tabs('select',title);//选中
					}else{
						$('#tt').tabs('add',{    
						    title:title,    
						    content:"<iframe src='"+url+"' style='width: 100%;height: 100%' frameborder='0'></iframe>",
						    closable:true 
						});
					}
				}
			 }
		 });
	});	 */
</script>

</head>
<body style="text-align: center;">
	<h1>欢迎访问xxxxx系统，请登录或注册</h1>
	<br>
	<form action="login" method="post">
	
			登录名：<input class="easyui-textbox" data-options="iconCls:'icon-man'" style="width:300px"> </br>
			    密码&nbsp;&nbsp;&nbsp;：<input class="easyui-passwordbox" data-options="iconCls:'icon-lock'" style="width:300px"></br>
				<a id="btn" href="index.jsp" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">登录</a>&nbsp;&nbsp;&nbsp;  
				<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">注册</a> 
			
			
			
	
	</form>


</body>
</html>