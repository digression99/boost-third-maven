<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="/meta.jsp" %>

<title>TodoApp</title>

<style>
		<%@include file="/css/index.css" %>
</style>
</head>
<body>
	<div class="container">
		<header>
			<a id="form-register-button" href="/secondproject/todo/form">새로운 TODO 등록</a>
		</header>
		<div class="list-container">
			<section id="types-todo-container">
				<div class="container-title">TODO</div>
				<ul id="types-todo-list">
					<c:forEach var="todo" items="${typesTodo}">
						<li class="list-item">
							<article class="list-item-content">
								<h3 class="list-item-title">title : ${todo.title}</h3>
								<div>date : ${todo.regDate}</div>
								<div>${todo.name}</div>
								<div>sequence : ${todo.sequence}</div>
							</article>
							<input type="hidden" value="${todo.id}" name="todo-id"/>
							<input type="hidden" value="${todo.type}" name="todo-type"/>
							<button class="list-item-button"  onclick="onChangeType(this)">Change</button>
						</li>
					</c:forEach>
				</ul>
			</section>
			<section id="types-doing-container">
				<div class="container-title">DOING</div>
				<ul id="types-doing-list">
					<c:forEach var="todo" items="${typesDoing}">
						<li class="list-item">
							<h3>title : ${todo.title}</h3>
							<div>name : ${todo.name}</div>
							<div>sequence : ${todo.sequence}</div>
							<div>date : ${todo.regDate}</div>
							<input type="hidden" value="${todo.id}" name="todo-id"/>
							<input type="hidden" value="${todo.type}" name="todo-type"/>
							<button onclick="onChangeType(this)">Change</button>
						</li>
					</c:forEach>
				</ul>
			</section>
			<section id="types-done-container">
				<div class="container-title">DONE</div>
				<ul id="types-done-list">
					<c:forEach var="todo" items="${typesDone}">
						<li class="list-item">
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
		</div>
	</div>

	<script>
	<%@include file="/js/test.js" %>
	</script>
</body>
</html>
