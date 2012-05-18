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


<!-- List all available teams data -->
<div class="sectionTitle">Available teams</div>
<table>
	<tr>
		<th>Team name</th>
		<th>Tournament name</th>
	</tr>
	<c:forEach var="entry" items="${availableTeams}">
		<tr>
			<td>${entry.value}</td>
			<td>${entry.key}</td>
		</tr>
	</c:forEach>
</table>

<div class="sectionTitle">Insert new team</div>

<form:form action="teampage.html" commandName="teamForm">
	<table>
		<tr>
			<td><fmt:message key="global.teamName" />*:</td>
			<td><form:input path="name" /> 
			<form:errors path="name" /></td>
			<td><form:select path="tournamentId">
					<form:options items="${availableTournaments}" />
				</form:select></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Skapa" /></td>
		</tr>

	</table>

</form:form>

</div>
<%@ include file="./views/includes/footer.jsp"%>