<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1>
		Hello world!  
	</h1>
	<div>
		<li>userid : ${user.userid}</li>
		<li>password : ${user.password}</li>
		<li>age : <%=request.getParameter("age") %></li>
		<li>num : ${num}</li>
	</div>
	<%--
		경로 시작할 때 /로 시작한다는 것은 http://localhost:9090/ 뒤에서 주소를 붙여나간다는 의미
		
		/ 없이 시작하면 현재 주소에서 뒷부분만 변경한다는 의미
	--%>
	<div>
		<%-- 모든 요청은 Controller로 가야 함 --%>
		<p><a href="/sample/basic">basic</a></p>
		<p><a href="/sample/login">login</a></p>
		<p><a href="/sample/doA">doA</a></p>
		<p><a href="/sample/insert">insert</a></p>
		
		<p><a href="/board/insert">insert</a></p>
		<p><a href="/board/list">list</a></p>
		<p><a href="/board/modify">modify</a></p>
		<p><a href="/board/read">read</a></p>
		
		<p><a href="/calc/add">add</a></p>
	</div>
</body>
</html>
