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


<!-- List all available tournament data -->
<div class="sectionTitle">Available tournaments</div>
<table>
	<tr>
		<th>Tournament name</th>
	</tr>
	<c:forEach var="entry" items="${availableTournaments}">
		<tr>
			<td>${entry.value}</td>
		</tr>
	</c:forEach>
</table>

<div class="sectionTitle">Insert new tournament</div>

<form:form action="tournamentpage.html" commandName="tournamentForm">
	<table>
		<tr>
			<td><fmt:message key="global.tournamentName" />*:</td>
			<td><form:input path="name" /> 
			<form:errors path="name" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Skapa" /></td>
		</tr>

	</table>

</form:form>
</div>

<%@ include file="./views/includes/footer.jsp"%>