<%@ include file="../views/includes/header.jsp"%>

<div class="row">
	<div class="span12">
		<legend>User Page</legend>

		<c:if test="${errorMessage}">
			<div class="errorMessage">${errorMessage}</div>
		</c:if>
		<c:if test="${successMessage}">
			<div class="successMessage">${successMessage}</div>
		</c:if>

		<!-- Search Form -->
		<form:form action="userpage.html" class="form-horizontal" commandName="searchForm">

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
				<th><fmt:message key="global.paid" /></th>
			</tr>
			<c:forEach var="entry" items="${availableUsers}">
				<tr>
					<!-- <td>${entry.key}</td>  -->
					<td>${entry.value.groupName}</td>
					<td>${entry.value.userName}</td>
					<td>${entry.value.realName}</td>
					<td>${entry.value.paid}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<%@ include file="../views/includes/footer.jsp"%>