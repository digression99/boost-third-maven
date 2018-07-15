<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TodoApp</title>
</head>
<body>
	<a href="/secondproject/todo/form">Todo Form</a>
	<section id="types-todo-container">
		<ul>
			<c:forEach var="todo" items="${typesTodo}">
				<li>
					<h3>title : ${todo.title}</h3>
					<div>name : ${todo.name}</div>
					<div>sequence : ${todo.sequence}</div>
					<div>date : ${todo.regDate}</div>
					<div>type : ${todo.type }</div>	
					<input type="hidden" value="${todo.id}"/>
					<button onclick="changeType()">Change</button>
				</li>
			</c:forEach>
		</ul>
	</section>
	<section id="types-doing-container">
		<ul>
			<c:forEach var="todo" items="${typesDoing}">
				<li>
					<h3>title : ${todo.title}</h3>
					<div>name : ${todo.name}</div>
					<div>sequence : ${todo.sequence}</div>
					<div>date : ${todo.regDate}</div>
					<div>type : ${todo.type }</div>	
					<input type="hidden" value="${todo.id}"/>
				</li>
			</c:forEach>
		</ul>
	</section>
	<section id="types-done-container">
		<ul>
			<c:forEach var="todo" items="${typesDone}">
				<li>
					<h3>title : ${todo.title}</h3>
					<div>name : ${todo.name}</div>
					<div>sequence : ${todo.sequence}</div>
					<div>date : ${todo.regDate}</div>
					<div>type : ${todo.type }</div>	
					<input type="hidden" value="${todo.id}"/>
				</li>
			</c:forEach>
		</ul>
	</section>
	<script src="http://localhost8080/secondproject/js/test.js"></script>
	<!-- <script src="<c:url value="/js/test.js" />" ></script> -->
</body>
</html>
