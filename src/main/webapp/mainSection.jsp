<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<section id="${sectionType}">
	<div class="container-title">${sectionTitle}</div>
	<ul id="${listType}">
		<c:forEach var="todo" items="${nowList}">
			<li class="list-item">
				<article class="list-item-content-container">
					<h3 class="list-item-title">${todo.title}</h3>
					<div class="list-item-content">등록날짜:${todo.regDate} ${todo.name} 우선순위:${todo.sequence}</div>
				</article>
				<aside class="list-item-side">
					<input type="hidden" data-todo-id="${todo.id}" data-todo-type="${todo.type}"/>
					<c:if test="${sectionTitle != 'DONE'}">
						<button class="list-item-button"  onclick="onChangeType(this)">&rarr;</button> 
					</c:if>
				</aside>
			</li>
		</c:forEach>
	</ul>
</section>