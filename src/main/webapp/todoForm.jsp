<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/meta.jsp" %>
		<title>TodoApp</title>
		<link rel="stylesheet" href="//fonts.googleapis.com/earlyaccess/nanumgothic.css">
		<style>
				<%@include file="/css/index.css" %>
				<%@include file="/css/todoForm.css" %>
		</style>
	</head>
	<body>
		<div class="container">
			<header class="page-header">
				<h1 class="page-header-title">할일등록</h1>
			</header>
			<div class="form-container">
				<form name='todo-form' onsubmit="onFormSubmit(event)">
					<section id="form-input-section">
						<div class="input-container">
							<label for="title">
								<h3 class="input-label-title">어떤일인가요?</h3>
								<input
									type="text"
									name="title"
									placeholder="SWIFT 공부하기(24자까지)"
									class="form-input-title"
									size=50
									maxlength=24 required/>
							</label>
						</div>
						<div class="input-container">
							<label for="name">
                                <h3 class="input-label-title">누가 할일인가요?</h3>
								<input
								type="text"
								name="name"
								placeholder="Put some name."
								class="form-input-name"
								size=20
								maxlength=24
								required/>
							</label>
						</div>
						<fieldset class="input-container">
							<legend class="input-label-title">우선순위를 선택하세요</legend>
							<div class="form-radio-button">
								<label class="form-radio-text" for="radio-sequence-1">
									<input type="radio" name="sequence" id="radio-sequence-1" value="1">1순위
								</label>
							</div>
							<div class="form-radio-button">
								<label class="form-radio-text" for="radio-sequence-2">
									<input type="radio" name="sequence" id="radio-sequence-2" value="2" checked="checked">2순위
								</label>
							</div>
							<div class="form-radio-button">
								<label class="form-radio-text" for="radio-sequence-3">
									<input type="radio" name="sequence" id="radio-sequence-3" value="3">3순위
								</label>
							</div>
						</fieldset>
					</section>

					<section id="form-control-section">
						<div class="page-controller">
							<a class="form-back-button" href="/secondproject/">&lt;이전</a>
						</div>
						<div class="form-controller">
							<button class="form-controller-button" type="submit">제출</button>
							<button class="form-controller-button" type="reset">내용지우기</button>
						</div>
					</section>
				</form>

			</div>
		</div>
		<script>
		<%@include file="/js/index.js" %>
		<%@include file="/js/form.js" %>
		</script>
	</body>
</html>
