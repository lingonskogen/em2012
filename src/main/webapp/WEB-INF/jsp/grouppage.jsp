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


<!-- List all available group data -->
<div class="sectionTitle">Available groups</div>
<table>
	<tr>
		<th>Group name</th>
	</tr>
	<c:forEach var="entry" items="${availableGroups}">
		<tr>
			<td>${entry.value}</td>
		</tr>
	</c:forEach>
</table>

<div class="sectionTitle">Insert new groups</div>

<form:form action="grouppage.html" commandName="groupForm">
	<table>
		<tr>
			<td><fmt:message key="global.groupName" />*:</td>
			<td><form:input path="groupName" /> 
			<form:errors path="groupName" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Skapa" /></td>
		</tr>

	</table>

</form:form>
</div>

<%@ include file="./views/includes/footer.jsp"%>