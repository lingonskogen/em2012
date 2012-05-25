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
					<th>Datum</th>
					<th>Match</th>
					<th>Resultat</th>
				</tr>

				<c:forEach items="${form.predictions}" var="predictionformdata"
					varStatus="status">
					<tr>
						<td>${predictionformdata.kickoff}</td>

						<td><input type="hidden" name="gameId" id="gameId"
							value="${predictionformdata.gameId}" />
							${predictionformdata.homeTeamName} - ${predictionformdata.awayTeamName}</td>
						<td><input name="contacts[${status.index}].homeScore"
							value="${predictionformdata.homeScore}" /> - 
							<input name="contacts[${status.index}].awayScore" value="${predictionformdata.awayScore}" /></td>
					</tr>
				</c:forEach>
        <tr>
         <td />
         <td><b>Slutvinnare</b></td>
         <td>
           <select id="winner">
           <c:forEach items="${form.teams}" var="team">
             <option value="${team.id}" <c:if test="${form.winnerTeamId == team.id}">selected="selected"</c:if>>${team.name}</option>
            </c:forEach>
            </select>
         </td>
         <td />
        </tr>
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