<%@ include file="./views/includes/header.jsp"%>

<div class="content">
<c:if test="${message!=null}">
	<div class="message">${message}</div>
</c:if>
</div>
<%@ include file="./views/includes/footer.jsp"%>