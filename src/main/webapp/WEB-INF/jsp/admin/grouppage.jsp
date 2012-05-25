<%@ include file="../views/includes/header.jsp"%>

<div class="row">
	<div class="span12">
		<section>
			<legend>Group Page</legend>

			<c:if test="${errorMessage}">
				<div class="errorMessage">${errorMessage}</div>
			</c:if>
			<c:if test="${successMessage}">
				<div class="successMessage">${successMessage}</div>
			</c:if>


			<!-- List all available group data -->
			<H4>Available groups</H4>
			<table class="table table-striped">
				<tr>
					<th>Group id</th>
					<th>Group name</th>
				</tr>
				<c:forEach var="entry" items="${availableGroups}">
					<tr>
						<td>${entry.value}</td>
						<td>${entry.key}</td>
					</tr>
				</c:forEach>
			</table>

			<H4>Insert new groups</H4>

			<form:form action="grouppage.html" class="form-horizontal"
				commandName="groupForm">
				<table class="table table-striped">
					<tr>
						<td><fmt:message key="global.groupName" />*:</td>
						<td><form:input path="groupName" /> <form:errors
								path="groupName" /></td>
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