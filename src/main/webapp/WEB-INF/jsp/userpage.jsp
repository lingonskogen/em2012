<%@ include file="./views/includes/header.jsp"%>

<div class="content">

	<%@ include file="./views/includes/adminmenu.jsp"%>


	<H1>User Page</H1>

	<c:if test="${errorMessage}">
		<div class="errorMessage">${errorMessage}</div>
	</c:if>
	<c:if test="${successMessage}">
		<div class="successMessage">${successMessage}</div>
	</c:if>

	<!-- Search Form -->
	<form:form action="userpage.html" commandName="searchForm">
			
		<table align="center">
			<tr>
				<td><form:select path="groupId" onChange="this.form.submit()">
						<form:options items="${availableGroups}" />
					</form:select></td>
			</tr>
		</table>
	</form:form>


	<!-- List all available games data -->
	<div class="sectionTitle">Available users</div>
	<table>
		<tr>
			<th><fmt:message key="global.group" /></th>
			<th><fmt:message key="global.userName" /></th>
			<th><fmt:message key="global.realName" /></th>
		</tr>
		<c:forEach var="entry" items="${availableUsers}">
			<tr>
				<!-- <td>${entry.key}</td>  -->
				<td>${entry.value.groupName}</td>
				<td>${entry.value.userName}</td>
				<td>${entry.value.realName}</td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@ include file="./views/includes/footer.jsp"%>