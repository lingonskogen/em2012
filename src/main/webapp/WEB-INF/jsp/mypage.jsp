<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ include file="./views/includes/header.jsp"%>

<div class="row">
	<div class="span5">
		<section>

			<legend>Min sida</legend>

			<table class="table">
				<tr>
					<td>Namn:</td>
					<td>${user.realName}</td>
				</tr>
				<tr>
					<td>email:</td>
					<td>${user.userName}</td>
				</tr>
				<tr>
					<td>Grupp:</td>
					<td>${groupName}</td>
				</tr>
				<tr>
					<td>Poäng:</td>
					<td><c:choose>
							<c:when test="${hasCoupon}">${userPoints}</c:when>
						</c:choose></td>
				</tr>
				<tr>
					<td>Statistik:</td>
					<td><c:choose>
							<c:when test="${hasCoupon && !registrationOpen}">Du har plats ${position} av ${totalUsers}</c:when>
						</c:choose></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
			</table>

			<c:choose>
				<c:when test="${hasCoupon && registrationOpen}">
				</c:when>
				<c:otherwise>
					<div class="obs">
						<b style="color: red;">OBS!</b> Du har inte skapat någon tipsrad
						än: <a href="${couponUrl}" class="btn btn-primary">Skapa
							tipsrad</a>
					</div>
				</c:otherwise>
			</c:choose>
		</section>
	</div>
	<div class="span7">
		<section>

			<c:choose>
				<c:when test="${hasCoupon}">
					<div class="myTipForm">
						<form:form class="form-horizontal" action="mypage.html"
							commandName="form">
							<fieldset>
								<legend>Min tipsrad</legend>
								<c:if test="${errorMessage != null}">
									<p class="alert alert-error">${errorMessage}</p>
								</c:if>
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

											<td style="text-align: right;">${predictionformdata.homeTeamName}</td>
											<td>-</td>
											<td>${predictionformdata.awayTeamName}</td>
											<td>
											<c:choose>											
											<c:when test="${registrationOpen}">
											<form:input path="predictionMap[${predictionformdata.gameId}].homeScore"
															value="${form.predictionMap[predictionformdata.gameId].homeScore}" />
										  	-
										  	<form:input path="predictionMap[${predictionformdata.gameId}].awayScore"
												value="${form.predictionMap[predictionformdata.gameId].awayScore}" />
											</c:when>
											<c:otherwise>										
                      						${form.predictionMap[predictionformdata.gameId].homeScore}
										  	-
										 	${form.predictionMap[predictionformdata.gameId].awayScore}
											</c:otherwise>
										</c:choose></td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="5"></td>
									</tr>
									<tr>
										<td colspan="4"><b>Slutvinnare: </b>
										
										<c:choose>											
											<c:when test="${registrationOpen}">
											<select id="winnerTeamId" name="winnerTeamId">
												<c:forEach items="${form.teams}" var="team">
													<option value="${team.id}"
														<c:if test="${form.winnerTeamId == team.id}">selected="selected"</c:if>>${team.name}</option>
												</c:forEach>
											</select>
											</c:when>
											<c:otherwise>										
                      						${form.winnerTeamId}
											</c:otherwise>
										</c:choose>
										</td>
										<td><c:if test="${registrationOpen}"><button class="btn btn-primary" type="submit">${submitAction}</button></c:if></td>
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