<%@ include file="../views/includes/header.jsp"%>

<div class="row">
	<div class="span12">
		<section>
			<legend>Tournament Page</legend>

			<c:if test="${errorMessage}">
				<div class="errorMessage">${errorMessage}</div>
			</c:if>
			<c:if test="${successMessage}">
				<div class="successMessage">${successMessage}</div>
			</c:if>


			<!-- List all available tournament data -->
			<H4>Available tournaments</H4>
			<table class="table table-striped">
				<tr>
					<th>Tournament name</th>
				</tr>
				<c:forEach var="entry" items="${availableTournaments}">
					<tr>
						<td>${entry.value}</td>
					</tr>
				</c:forEach>
			</table>

			<H4>Insert new tournament</H4>

			<form:form action="tournamentpage.html" class="form-horizontal"
				commandName="tournamentForm">
				<fieldset>
					<table class="table table-striped">
						<tr>
							<td><fmt:message key="global.tournamentName" />*:</td>
							<td><form:input path="name" /> <form:errors path="name" /></td>
						</tr>
						<tr>
							<td></td>
							<td><button class="btn btn-primary" type="submit">Skapa</button></td>
						</tr>

					</table>
				</fieldset>
			</form:form>
		</section>
	</div>
</div>
<%@ include file="../views/includes/footer.jsp"%>