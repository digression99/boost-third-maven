<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<link rel="stylesheet" href="<c:url value="/css/index.css" />" />


</head>
<body>
<c:import url="/components/header.jsp" charEncoding="UTF-8"/>

<h1>${data1}</h1>

<h1>${data2}</h1>

<button onclick="func()">Push!</button>
<button onclick="onButtonClick()">Ajax!</button>
<script src="<c:url value="/js/test.js" />"></script>
</body>
</html>