<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<title>新闻信息</title>
</head>
<%
	// 获取请求的上下文
	String context = request.getContextPath();
%>
<script type="text/javascript">
// 当前第几页数据
var currentPage = ${result.currentPage};

// 总页数
var totalPage = ${result.totalPage};

function submitForm(actionUrl){
	var formElement = document.getElementById("newsForm");
	formElement.action = actionUrl;
	formElement.submit();
}

// 第一页
function firstPage(){
	if(currentPage == 1){
		alert("已经是第一页数据");
		return false;
	}else{
		submitForm("<%=context %>/sublist/SublistServlet?pageNum=1");
		return true;
	}
}

// 下一页
function nextPage(){
	if(currentPage == totalPage){
		alert("已经是最后一页数据");
		return false;
	}else{
		submitForm("<%=context %>/sublist/SublistServlet?pageNum=" + (currentPage+1));
		return true;
	}
}

// 上一页
function previousPage(){
	if(currentPage == 1){
		alert("已经是第一页数据");
		return false;
	}else{
		submitForm("<%=context %>/sublist/SublistServlet?pageNum=" + (currentPage-1));
		return true;
	}
}

// 尾页
function lastPage(){
	if(currentPage == totalPage){
		alert("已经是最后一页数据");
		return false;
	}else{
		submitForm("<%=context %>/sublist/SublistServlet?pageNum=${result.totalPage}");
		return true;
	}
}
function initPage(){
	var genderRequest = "${gender}" ;
	var genderVal = 0;
	var genderElement = document.getElementById("topic");
	if(genderRequest != ""){
		genderVal = parseInt(genderRequest);
	}
	
	var options = genderElement.options;
	var i = 0;
	for(i = 0; i < options.length; i++){
		if(options[i].value == genderVal){
			options[i].selected=true;
			break;
		}
	}
	
}
</script>
<body onload="initPage();" class="p-3 mb-2 bg-light text-dark">
	<div style="margin-left: 100px; margin-top: 100px;" >
		<div>
			<font color="red">${errorMsg }</font>
		</div>
		<div>
			<form action="<%=context %>/sublist/SublistServlet"   id="newsForm"  method="post">
				标题&nbsp;&nbsp;
				<input type="text" class="form-control-lg" name="title" id="title" style="width:120px" placeholder="标题" value="${title }">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				主题&nbsp;&nbsp;
				<%--<select name="gender" id="gender" style="width:80px">
					<option value="0">全部</option>
					<option value="1">男</option>
					<option value="2">女</option>
				</select>--%>
				<input type="text" name="topic" class="form-control-lg" id="topic" style="width:120px" placeholder="主题" value="${topic }">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%--<input type="submit" value="查询">--%>
				<button type="submit" class="btn btn-outline-secondary">查询</button>
			</form>
		</div>
		<br>
		<!-- 后台返回结果为空 -->
		<c:if test="${fn:length(result.dataList) eq 0 }">
			<span>查询的结果不存在</span>
		</c:if>
		
		<!-- 后台返回结果不为空 -->
		<c:if test="${fn:length(result.dataList) gt 0 }">
			<table class="table table-striped table-dark" border="1px" cellspacing="0px"
				style="border-collapse: collapse">
				<thead>
					<tr height="30">
						<th scope="col" width="130">标题</th>
						<th scope="col" width="130">主题</th>
						<th scope="col" width="190">详情</th>
						<th scope="col" width="130">时间</th>
					</tr>
				</thead>
				<c:forEach items="${result.dataList }" var="news">
					<tr>
						<td><c:out value="${news.title }"></c:out></td>
						<td>
							<c:out value="${news.topic }"></c:out>
						</td>
						<td><c:out value="${news.info }"></c:out></td>
						<td><c:out value="${news.time }"></c:out></td>
					</tr>
				</c:forEach>
			</table>
			<br> <div class="text-primary">
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
				<div class="collapse navbar-collapse" id="navbarColor01">
					共${result.totalRecord }条记录&nbsp;&nbsp;共${result.totalPage }页&nbsp;&nbsp;当前第${result.currentPage }页
					<ul class="navbar-nav mr-auto">
						<li class="nav-item">
							<a class="nav-link" href="#" onclick="firstPage();">首页</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#" onclick="nextPage();">下一页</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#" onclick="previousPage();">上一页</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#" onclick="lastPage();">尾页</a>
						</li>
					</ul>
				</div>
			</nav>
				<%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共${result.totalRecord }条记录&nbsp;&nbsp;共${result.totalPage }页&nbsp;&nbsp;当前第${result.currentPage }页&nbsp;&nbsp;
			<a href="#" onclick="firstPage();" class="text-primary">首页</a>&nbsp;&nbsp;
			<a href="#" onclick="nextPage();" class="text-primary">下一页</a>&nbsp;&nbsp;
			<a href="#" onclick="previousPage();" class="text-primary">上一页</a>&nbsp;&nbsp;
			<a href="#" onblur="lastPage();" class="text-primary">尾页</a>--%>
		</div>
		</c:if>
	</div>
</body>
</html>