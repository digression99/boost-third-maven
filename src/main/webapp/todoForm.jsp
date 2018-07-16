<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TodoApp</title>
</head>
<body>
	<!-- onsubmit="onSubmit()" -->
	<form name='todo-form' action="/secondproject/todo" method="post">
		<input type="text" name="title" placeholder="Put some title."/>
		<input type="text" name="name" placeholder="Put some name." />
		
		<input type="radio" name="sequence" value="1">HTML
		<input type="radio" name="sequence" value="2" checked="checked">CSS
		<input type="radio" name="sequence" value="3">웹디자인
		
		<input type="submit" value="submit" />
	</form>
	<script src="<c:url value="js/test.js" />"></script>
	<!-- <script src="/js/test.js"></script> -->
</body>
</html>