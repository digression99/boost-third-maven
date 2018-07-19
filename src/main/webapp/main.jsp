<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<%@include file="/meta.jsp" %>

		<title>TodoApp</title>

		<link rel="stylesheet" href="//fonts.googleapis.com/earlyaccess/nanumgothic.css">
		<style>
				<%@include file="/css/message.css" %>
				<%@include file="/css/index.css" %>
				<%@include file="/css/main.css" %>
		</style>
	</head>
	<body>
		<h1 id="main-title" class="page-header-title">나의 해야할 일들</h1>
		<div class="container">
			<%@include file="/message.jsp" %>
			<header>
				<a id="form-register-button" href="/secondproject/todo/form">새로운 TODO 등록</a>
			</header>
			<div class="list-container">
				<c:set var="sectionType" scope="request" value="types-todo-container" />
				<c:set var="sectionTitle" scope="request" value="TODO" />
				<c:set var="listType" scope="request" value="types-todo-list" />
				<c:set var="nowList" scope="request" value="${typesTodo}" />

				<%@include file="/mainSection.jsp" %>

				<c:set var="sectionType" scope="request" value="types-doing-container" />
				<c:set var="sectionTitle" scope="request" value="DOING" />
				<c:set var="listType" scope="request" value="types-doing-list" />
				<c:set var="nowList" scope="request" value="${typesDoing}" />
				<%@include file="/mainSection.jsp" %>

				<c:set var="sectionType" scope="request" value="types-done-container" />
				<c:set var="sectionTitle" scope="request" value="DONE" />
				<c:set var="listType" scope="request" value="types-done-list" />
				<c:set var="nowList" scope="request" value="${typesDone}" />
				<%@include file="/mainSection.jsp" %>

				<c:remove var="sectionType" scope="request" />
				<c:remove var="sectionTitle" scope="request" />
				<c:remove var="listType" scope="request" />
				<c:remove var="nowList" scope="request" />
			</div>
		</div>

		<script>
        <%@include file="/js/message.js" %>
		<%@include file="/js/index.js" %>
		</script>
	</body>
</html>
