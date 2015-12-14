<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传cebx文件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath%>js/jquery-1.11.2.js"></script>
	<script type="text/javascript">
		$().ready(function() {
			if ("${requestScope.error}" != "") {
				alert("${requestScope.error}");
			}
		})
	</script>
  </head>
  
  <body>
  	<form action="<%=basePath%>cebx/upload" method="post" enctype="multipart/form-data">
  		<table>
	  		<tr>
	  			<td>目录</td>
	  			<td>
		  			<select name="dirId" id="dirId">
		  				<c:forEach items="${requestScope.dirVos }" var="vo">
		  				<option value="${vo.dirId }">${vo.dirTitle }</option>
		  				</c:forEach>
		  			</select>
	  			</td>
	  		</tr>
	  		<tr>
	  			<td>标题</td>
	  			<td><input type="text" name="title" id="title"></td>
	  		</tr>
	  		<tr>
	  			<td>文号</td>
	  			<td><input type="text" name="fileNumber" id="fileNumber"></td>
	  		</tr>
	  		<tr>
	  			<td>密级</td>
	  			<td><input type="text" name="privLevel" id="privLevel"></td>
	  		</tr>
	  		<tr>
	  			<td>cebx文件</td>
	  			<td><input type="file" name="cebxFile" id="cebxFile"></td>
	  		</tr>
	  		<tr>
	  			<td colspan="2" align="center"><input type="submit" name="upload" id="upload" value="上传"></td>
	  		</tr>
	  	</table>
  	</form>
  </body>
</html>
