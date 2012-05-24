<%@ include file="./views/includes/header.jsp"%>

<div class="row">
	<div class="span6">
		<section>

			<legend>Min sida</legend>

			<table class="table">
				<tr>
					<td>Namn:</td>
					<td>${user.realName}</td>
				</tr>
				<tr>
					<td>Användarnamn/email:</td>
					<td>${user.userName}</td>
				</tr>
				<tr>
					<td>Grupp:</td>
					<td>${groupName}</td>
				</tr>
				<tr>
					<td>Statistik:</td>
					<td><c:choose>
							<c:when test="${hasCoupon}">Du har plats ${position} av ${totalUsers}</c:when>
						</c:choose></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
			</table>

			<c:choose>
				<c:when test="${hasCoupon}">
				</c:when>
				<c:otherwise>
					<div class="obs">
						<b style="color: red;">OBS!</b> Du har inte skapat någon tipsrad
						än: <a href="${couponUrl}" class="btn btn-primary">Skapa tipsrad</a>
						</div>
				</c:otherwise>
			</c:choose>
		</section>
	</div>
	<div class="span6">
		<section>

			<c:choose>
				<c:when test="${hasCoupon}">
					<div class="myTipForm">
					<form:form class="form-horizontal" action="mypage.html"
						commandName="form">
						<fieldset>
							<legend>Min tipsrad</legend>

							<table class="table table-striped">
								<tr>
									<th>Datum</th>
									<th colspan="3">Match</th>
									<th>Resultat</th>
								</tr>

								<c:forEach items="${form.predictions}" var="predictionformdata"
									varStatus="status">
									<tr>
										<td>${predictionformdata.kickoff}</td>

										<td style="text-align: right;"><input type="hidden" name="gameId" id="gameId"
											value="${predictionformdata.gameId}" />
											${predictionformdata.homeTeamName}</td>
										<td>-</td>
										<td>${predictionformdata.awayTeamName}</td>
										<td><input name="contacts[${status.index}].homeScore"
											value="${predictionformdata.homeScore}" /> - <input
											name="contacts[${status.index}].awayScore"
											value="${predictionformdata.awayScore}" /></td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="5"></td>
								</tr>
								<tr>
									<td colspan="4"><b>Slutvinnare: </b> <select id="winner">
											<c:forEach items="${form.teams}" var="team">
												<option value="${team.id}"
													<c:if test="${form.winnerTeamId == team.id}">selected="selected"</c:if>>${team.name}</option>
											</c:forEach>
									</select></td>
									<td><button class="btn btn-primary" type="submit">${submitAction}</button></td>
								</tr>

							</table>
						</fieldset>
					</form:form>
					</div>
				</c:when>
				<c:otherwise>
					<img src="/images/zlatko.jpg" alt="kram">
					<div style="padding-bottom: 20px;"></div>
					<img src="/images/kimpa.jpg" alt="elamnder">
				</c:otherwise>
			</c:choose>
		</section>
	</div>
</div>

<%@ include file="./views/includes/footer.jsp"%>