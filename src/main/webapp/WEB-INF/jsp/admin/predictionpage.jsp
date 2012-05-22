<%@ include file="../views/includes/header.jsp"%>

<div class="row">
	<div class="span12">

		<legend>Prediction Page</legend>

		<c:if test="${errorMessage}">
			<div class="errorMessage">${errorMessage}</div>
		</c:if>
		<c:if test="${successMessage}">
			<div class="successMessage">${successMessage}</div>
		</c:if>


		<form:form action="predictionpage.html" class="form-horizontal" commandName="searchForm">
			<input type="hidden" name="tournamentId" value="${tournamentId}" />
			<input type="hidden" name="couponId" value="${couponId}" />

			<table align="center">
				<tr>
					<td><form:select path="groupId" onChange="this.form.submit()">
							<form:options items="${availableGroups}" />
						</form:select></td>

					<td><form:select path="userId" onChange="this.form.submit()">
							<form:options items="${availableUsers}" />
						</form:select></td>
				</tr>
			</table>
		</form:form>
		<!-- List all available predictions data -->
		<div class="sectionTitle">Available predictions</div>
		<table>
			<tr>
				<!-- <th><fmt:message key="global.tournament" /></th>  -->
				<th><fmt:message key="global.group" /></th>
				<th><fmt:message key="global.user" /></th>
				<th><fmt:message key="global.coupon" /></th>
				<th><fmt:message key="global.game" /></th>
				<th><fmt:message key="global.score" /></th>
			</tr>

			<c:forEach var="entry" items="${availablePredictions}">
				<tr>
					<!-- <td>${entry.key}</td>  
			<td>${entry.value.tournamentName}</td>-->
					<td>${entry.value.groupName}</td>
					<td>${entry.value.userName}</td>
					<td>${entry.value.couponId}</td>
					<td>${entry.value.homeTeam} - ${entry.value.awayTeam}</td>
					<td>${entry.value.homeScore} - ${entry.value.awayScore}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<%@ include file="../views/includes/footer.jsp"%>