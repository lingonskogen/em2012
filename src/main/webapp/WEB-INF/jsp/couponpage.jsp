<%@ include file="./views/includes/header.jsp"%>

<div class="content">

	<%@ include file="./views/includes/adminmenu.jsp"%>


	<H1>Coupon Page</H1>

	<c:if test="${errorMessage}">
		<div class="errorMessage">${errorMessage}</div>
	</c:if>
	<c:if test="${successMessage}">
		<div class="successMessage">${successMessage}</div>
	</c:if>

	<!-- Search Form -->
	<form:form action="couponpage.html" commandName="searchForm">
		<input type="hidden" name="tournamentId" value="${tournamentId}" />
		
		<table align="center">
			<tr>
				<td><form:select path="groupId" onChange="this.form.submit()">
						<form:options items="${availableGroups}" />
					</form:select></td>
			</tr>
		</table>
	</form:form>


	<!-- List all available games data -->
	<div class="sectionTitle">Available coupons</div>
	<table>
		<tr>
			<th><fmt:message key="global.tournament" /></th>
			<th><fmt:message key="global.group" /></th>
			<th><fmt:message key="global.user" /></th>
		</tr>
		<c:forEach var="entry" items="${availableCoupons}">
			<tr>
				<!-- <td>${entry.key}</td>  -->
				<td>${entry.value.tournamentName}</td>
				<td>${entry.value.groupName}</td>
				<td>${entry.value.userName}</td>
				<td><a href="${entry.value.deleteLink}"><fmt:message
							key="global.remove" /></a></td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@ include file="./views/includes/footer.jsp"%>