<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ include file="./views/includes/header.jsp"%>

<div class="row">
	<div class="span12">
		<section>
			<legend>Resultat/Tipsrader</legend>
			<c:choose>
			  <c:when test="${noCoupon}">
          <div class="obs">
            <b style="color: red;">OBS!</b> Du har inte skapat någon tipsrad än! Om det inte är försent kan du göra det på <a href="/mypage.html">Min Sida</a>
            </div>
			  </c:when>
				<c:when test="${registrationOpen}">
				 Resultat och tipsrader blir synliga när tiden för registrering har gått ut!
				</c:when>
				<c:otherwise>
					<table class="table table-striped">
						<tr>
							<th colspan="10" style="text-align: center;"><h4>
									Match <small>1 - 8</small>
									<h4></th>
						</tr>

<tr>
							<td colspan="10">
							<table width="100%"><tbody><tr><td style="padding-left: 100px;"></td><td style="background: #FFF;"></td><td>0 poäng</td>
							<td></td><td style="background: #CCFFCC;"></td><td>1 poäng</td>
							<td></td><td style="background: #FFE5FF;"></td><td>2 poäng</td>
							<td></td><td style="background: #FFFFCC;"></td><td>3 poäng</td>
							<td style="padding-left: 100px;"></td>
							</tr></tbody></table>
							</td>
						</tr>

						<tr>
							<td />
							<th>Avspark</th>
							<c:forEach items="${data.gameFormDataList1}" var="gameFormData"
								varStatus="status">
								<td style="text-align: center;">${gameFormData.kickoff}</td>
							</c:forEach>
						</tr>

						<tr>
							<td />
							<th>Match</th>
							<c:forEach items="${data.gameFormDataList1}" var="gameFormData"
								varStatus="status">
								<td style="text-align: center;"><img
									src="/images/${gameFormData.homeTeam.code}.png"
									title="${gameFormData.homeTeam.name}" /> - <img
									src="/images/${gameFormData.awayTeam.code}.png"
									title="${gameFormData.awayTeam.name}" /></td>
							</c:forEach>
						</tr>

						<tr>
							<td />
							<th>Resultat</th>
							<c:forEach items="${data.gameFormDataList1}" var="gameFormData"
								varStatus="status">
								<th style="text-align: center;">${gameFormData.game.homeScore}
									- ${gameFormData.game.awayScore}</th>
							</c:forEach>
						</tr>

						<tr>
							<td colspan="10"></td>
						</tr>

						<c:forEach items="${data.userScoreDataList}" var="userScoreData">
							<tr>
								<td>${userScoreData.score}</td>
								<td>${userScoreData.user.realName}</td>
								<c:forEach items="${userScoreData.predictionList1}"
									var="predictionScore" varStatus="status">
									<td class="score${predictionScore.score} }"
										style="text-align: center;">${predictionScore.prediction.homeScore}
										- ${predictionScore.prediction.awayScore}</td>
								</c:forEach>
							</tr>
						</c:forEach>

						<tr>
							<th colspan="10" style="text-align: center;"><h4>
									Match <small>9 - 16</small>
									<h4></th>
						</tr>

						<tr>
							<td />
							<th>Avspark</th>
							<c:forEach items="${data.gameFormDataList2}" var="gameFormData"
								varStatus="status">
								<td style="text-align: center;">${gameFormData.kickoff}</td>
							</c:forEach>
						</tr>

						<tr>
							<td />
							<th>Match</th>
							<c:forEach items="${data.gameFormDataList2}" var="gameFormData"
								varStatus="status">
								<td style="text-align: center;"><img
									src="/images/${gameFormData.homeTeam.code}.png"
									title="${gameFormData.homeTeam.name}" /> - <img
									src="/images/${gameFormData.awayTeam.code}.png"
									title="${gameFormData.awayTeam.name}" /></td>
							</c:forEach>
						</tr>

						<tr>
							<td />
							<th>Resultat</th>
							<c:forEach items="${data.gameFormDataList2}" var="gameFormData"
								varStatus="status">
								<th style="text-align: center;">${gameFormData.game.homeScore}
									- ${gameFormData.game.awayScore}</th>
							</c:forEach>
						</tr>

						<c:forEach items="${data.userScoreDataList}" var="userScoreData">
							<tr>
								<td>${userScoreData.score}</td>
								<td>${userScoreData.user.realName}</td>
								<c:forEach items="${userScoreData.predictionList2}"
									var="predictionScore" varStatus="status">
									<td class="score${predictionScore.score} }"
										style="text-align: center;">${predictionScore.prediction.homeScore}
										- ${predictionScore.prediction.awayScore}</td>
								</c:forEach>
							</tr>
						</c:forEach>

						<tr>
							<th colspan="10" style="text-align: center;"><h4>
									Match <small>17 - 24</small>
									<h4></th>
						</tr>

						<tr>
							<td />
							<th>Avspark</th>
							<c:forEach items="${data.gameFormDataList3}" var="gameFormData"
								varStatus="status">
								<td style="text-align: center;">${gameFormData.kickoff}</td>
							</c:forEach>
						</tr>

						<tr>
							<td />
							<th>Match</th>
							<c:forEach items="${data.gameFormDataList3}" var="gameFormData"
								varStatus="status">
								<td style="text-align: center;"><img
									src="/images/${gameFormData.homeTeam.code}.png"
									title="${gameFormData.homeTeam.name}" /> - <img
									src="/images/${gameFormData.awayTeam.code}.png"
									title="${gameFormData.awayTeam.name}" /></td>
							</c:forEach>
						</tr>

						<tr>
							<td />
							<th>Resultat</th>
							<c:forEach items="${data.gameFormDataList3}" var="gameFormData"
								varStatus="status">
								<th style="text-align: center;">${gameFormData.game.homeScore}
									- ${gameFormData.game.awayScore}</th>
							</c:forEach>
						</tr>

						<c:forEach items="${data.userScoreDataList}" var="userScoreData">
							<tr>
								<td>${userScoreData.score}</td>
								<td>${userScoreData.user.realName}</td>
								<c:forEach items="${userScoreData.predictionList3}"
									var="predictionScore" varStatus="status">
									<td class="score${predictionScore.score} }"
										style="text-align: center;">${predictionScore.prediction.homeScore}
										- ${predictionScore.prediction.awayScore}</td>
								</c:forEach>
							</tr>
						</c:forEach>

					</table>
		</section>
	</div>
</div>
</c:otherwise>
</c:choose>
<%@ include file="./views/includes/footer.jsp"%>