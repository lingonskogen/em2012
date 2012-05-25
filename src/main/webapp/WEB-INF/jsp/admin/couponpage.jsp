<%@ include file="../views/includes/header.jsp"%>

<div class="row">
	<div class="span12">
		<section>
			<legend>Coupon Page</legend>

			<c:if test="${errorMessage}">
				<div class="errorMessage">${errorMessage}</div>
			</c:if>
			<c:if test="${successMessage}">
				<div class="successMessage">${successMessage}</div>
			</c:if>

			<form:form action="couponpage.html" class="form-horizontal"
				commandName="searchForm">
				<input type="hidden" name="tournamentId" value="${tournamentId}" />

				<table class="table table-striped" align="center">
					<tr>
						<td><form:select path="groupId" onChange="this.form.submit()">
								<form:options items="${availableGroups}" />
							</form:select></td>
					</tr>
				</table>
			</form:form>


			<!-- List all available games data -->
			<h4>Available coupons</h4>
			<table class="table table-striped" >
				<tr>
					<th><fmt:message key="global.tournament" /></th>
					<th><fmt:message key="global.group" /></th>
					<th><fmt:message key="global.user" /></th>
					<th>Slutvinnare</th>
				</tr>
				<c:forEach var="entry" items="${availableCoupons}">
					<tr>
						<!-- <td>${entry.key}</td>  -->
						<td>${entry.value.tournamentName}</td>
						<td>${entry.value.groupName}</td>
						<td>${entry.value.userName}</td>
						<td>${entry.value.winnerTeam}</td>
					</tr>
				</c:forEach>
			</table>
		</section>
	</div>
</div>
<%@ include file="../views/includes/footer.jsp"%>