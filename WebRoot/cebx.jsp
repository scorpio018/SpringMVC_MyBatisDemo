<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/cebx.css">
	<script src="<%=basePath%>js/jquery-1.11.2.js"></script>
	<script type="text/javascript">
		$().ready(function() {
			
		})
	</script>
  </head>
  
  <body>
  	<table class="main_tab">
  		<tr>
  			<td class="tree_tr">123</td>
  			<td>
  				<a href="<%=basePath%>cebx/prepareUpload" class="upload_a">上传</a>
  				<table class="file_tab">
  					<thead>
  						<tr>
	  						<th>目录</th>
	  						<th>标题</th>
	  						<th>文号</th>
	  						<th>密级</th>
	  						<th>上传时间</th>
	  						<th>操作</th>
	  					</tr>
  					</thead>
  					<tbody>
  						<c:forEach items="${requestScope.page.results }" var="vo">
  							<tr>
  								<td>${vo.dirVo.dirTitle }</td>
  								<td>${vo.title }</td>
  								<td>${vo.fileNumber }</td>
  								<td>${vo.privLevel }</td>
  								<td><fmt:formatDate value="${vo.uploadDate }" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
  								<td>
  									<a href="#">预览</a>
  									|<a href="<%=basePath%>cebx/deleteCebx?fileId=${vo.fileId}&pageNo=${page.pageNo}&pageSize=${page.pageSize}">删除</a>
  								</td>
  							</tr>
  						</c:forEach>
  					</tbody>
  					<tfoot>
  						<tr>
  							<td colspan="3">
  								当前第${requestScope.page.pageNo }页，
  								一共${requestScope.page.totalPage }页，
  								每页${requestScope.page.pageSize }条，
  								共${requestScope.page.totalRecord }条
  							</td>
  							<td colspan="3">
  								<c:choose>
  									<c:when test="${requestScope.page.pageNo eq 1 }">
  										首页|上一页
  									</c:when>
  									<c:otherwise>
  										<a href="<%=basePath%>cebx/showCebxList?pageNo=1">首页</a>|
  										<a href="<%=basePath%>cebx/showCebxList?pageNo=${requestScope.page.pageNo - 1}">上一页</a>
  									</c:otherwise>
  								</c:choose>
  								<c:choose>
  									<c:when test="${requestScope.page.pageNo eq requestScope.page.totalPage }">
  										下一页|末页
  									</c:when>
  									<c:otherwise>
  										<a href="<%=basePath%>cebx/showCebxList?pageNo=${requestScope.page.pageNo + 1}">下一页</a>
  										<a href="<%=basePath%>cebx/showCebxList?pageNo=${requestScope.page.totalPage}">末页</a>
  									</c:otherwise>
  								</c:choose>
  							</td>
  						</tr>
  					</tfoot>
  				</table>
  			</td>
  		</tr>
  	</table>
  </body>
</html>
