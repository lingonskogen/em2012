<%@ include file="../views/includes/header.jsp"%>

<div class="row">
	<div class="span12">
		<section>
			<legend>Game Page</legend>

			<c:if test="${errorMessage}">
				<div class="errorMessage">${errorMessage}</div>
			</c:if>
			<c:if test="${successMessage}">
				<div class="successMessage">${successMessage}</div>
			</c:if>


			<!-- List all available games data -->
			<H4>Available games</H4>
			<table class="table table-striped">
				<tr>
					<th>Tournament name</th>
					<th>Hemmalag</th>
					<th>Bortalag</th>
					<th>Avspark</th>
					<th>Resultat</th>
				</tr>
				<c:forEach var="entry" items="${availableGames}">
					<tr>
						<td>${entry.key}</td>
						<td>${entry.value.homeTeamId}</td>
						<td>${entry.value.awayTeamId}</td>
						<td>${entry.value.kickoff}</td>
						<td>${entry.value.homeScore} - ${entry.value.awayScore}</td>
					</tr>
				</c:forEach>
			</table>

			<H4>Insert new game</H4>

			<form:form action="gamepage.html" class="form-horizontal"
				commandName="gameForm">
				<table class="table table-striped">

					<tr>
						<td><fmt:message key="global.tournamentId" />*:</td>
						<td><form:select path="tournamentId">
								<form:options items="${availableTournaments}" />
							</form:select></td>
					</tr>
					<tr>
						<td><fmt:message key="global.homeTeamId" />*:</td>
						<td><form:select path="homeTeamId">
								<form:options items="${availableTeams}" />
							</form:select> <form:errors path="homeTeamId" /></td>
					</tr>
					<tr>
						<td><fmt:message key="global.awayTeamId" />*:</td>
						<td><form:select path="awayTeamId">
								<form:options items="${availableTeams}" />
							</form:select> <form:errors path="awayTeamId" /></td>
					</tr>
					<tr>
						<td><fmt:message key="global.kickoff" />*:</td>
						<td><form:input path="kickoff" /> <form:errors
								path="kickoff" /></td>
					</tr>
					<tr>
						<td><fmt:message key="global.homeScore" />:</td>
						<td><form:input path="homeScore" /> <form:errors
								path="homeScore" /></td>
					</tr>
					<tr>
						<td><fmt:message key="global.awayScore" />:</td>
						<td><form:input path="awayScore" /> <form:errors
								path="awayScore" /></td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="Skapa" /></td>
					</tr>

				</table>

			</form:form>
		</section>
	</div>
</div>

<%@ include file="../views/includes/footer.jsp"%>