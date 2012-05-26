<%@ include file="../views/includes/header.jsp"%>

<div class="row">
	<div class="span12">
		<section>

			<legend>Team Page</legend>

			<c:if test="${errorMessage}">
				<div class="errorMessage">${errorMessage}</div>
			</c:if>
			<c:if test="${successMessage}">
				<div class="successMessage">${successMessage}</div>
			</c:if>


			<!-- List all available teams data -->
			<H4>Available teams</H4>
			<table class="table table-striped">
				<tr>
					<th>Tournament name</th>
					<th>Team name</th>
					<th>Team code</th>
				</tr>
				<c:forEach var="entry" items="${availableTeams}">
					<tr>
						<td>${entry.value.tournamentId}</td>
						<td>${entry.value.name}</td>
						<td>${entry.value.code}</td>						
					</tr>
				</c:forEach>
			</table>

			<H4>Insert new team</H4>

			<form:form action="/admin/teampage.html" class="form-horizontal"
				commandName="teamForm">
				<table class="table table-striped">
					<tr>
						<td>Landskod*:</td>
						<td><form:input path="code" /> <form:errors path="code" /></td>
						<td><fmt:message key="global.teamName" />*:</td>
						<td><form:input path="name" /> <form:errors path="name" /></td>
						<td><form:select path="tournamentId">
								<form:options items="${availableTournaments}" />
							</form:select></td>
					</tr>
					<tr>
						<td colspan="4"></td>
						<td><input type="submit" value="Skapa" /></td>
					</tr>

				</table>

			</form:form>
		</section>
	</div>
</div>

<%@ include file="../views/includes/footer.jsp"%>