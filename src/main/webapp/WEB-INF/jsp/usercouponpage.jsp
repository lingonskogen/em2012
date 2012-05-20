<%@ include file="./views/includes/header.jsp"%>

<section>
	<form:form class="form-horizontal" action="coupon.html"
		commandName="form">
		<fieldset>
			<legend>
				<fmt:message key="couponPage.header" />
			</legend>

			<table class="table table-striped">
				<tr>
					<th></th>
					<th colspan="2">Match</th>
					<th>Resultat</th>
				</tr>

				<c:forEach items="${form.predictions}" var="predictionformdata"
					varStatus="status">
					<tr>
						<td align="center">${status.count}</td>

						<td><input type="hidden" name="gameId" id="gameId"
							value="${predictionformdata.gameId}" />
							${predictionformdata.homeTeamName} - ${predictionformdata.awayTeamName}</td>
						<td><input name="contacts[${status.index}].homeScore"
							value="${predictionformdata.homeScore}" /></td>
						<td><input name="contacts[${status.index}].awayScore"
							value="${predictionformdata.awayScore}" /></td>
					</tr>
				</c:forEach>
			</table>

			<div class="control-group">
				<div class="controls" style="margin-left: 300px">
					<button class="btn btn-primary" type="submit">${submitAction}</button>
				</div>
			</div>
		</fieldset>
	</form:form>
</section>

<%@ include file="./views/includes/footer.jsp"%>