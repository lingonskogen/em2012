<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ include file="./views/includes/header.jsp"%>


<div class="row">
	<div class="span8">
		<section>

			<legend>Topplista</legend>

			<table class="table">
			<tr><td><table class="table table-striped">
				<tbody>
					<tr><td colspan="4">${groupName} Topplista</td></tr>
					<tr>
						<th></th>
						<th>Groupp</th>
						<th>Namn</th>
						<th>Poäng</th>
					</tr>
					
					<c:forEach items="${groupToplist}" var="toplistData" varStatus="status">
					<tr>
						<td>${status.count}.</td>
						<td>${toplistData.groupName}</td>
						<td>${toplistData.userRealName}</td>
						<td>${toplistData.points}</td>
					</tr>				
					</c:forEach>					
				</tbody>
			</table>
			</td>
			<td></td>
			<td><table class="table table-striped">
				<tbody>
					<tr><td colspan="4">Global Topplista</td></tr>
					<tr>
						<th></th>
						<th>Groupp</th>
						<th>Namn</th>
						<th>Poäng</th>
					</tr>
					
					<c:forEach items="${globalToplist}" var="toplistData" varStatus="status">
					<tr>
						<td>${status.count}.</td>
						<td>${toplistData.groupName}</td>
						<td>${toplistData.userRealName}</td>
						<td>${toplistData.points}</td>
					</tr>				
					</c:forEach>				
				</tbody>
			</table>
			</td></tr>
			</table>
		</section>
	</div>

	<div class="span4">
		<section>
			<legend>Nästa match</legend>
			<table class="table">
				<tbody>
					<c:forEach items="${nextGames}" var="gameData" varStatus="status">
					<tr>
						<td></td>
						<td style="text-align: center;" colspan="3">${gameData.kickoff}</td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td style="text-align: center;"><img title="${gameData.homeTeam.name}"
							src="/images/${gameData.homeTeam.code}.png"></td>
						<td></td>
						<td style="text-align: center;"><img title="${gameData.awayTeam.name}"
							src="/images/${gameData.awayTeam.code}.png"></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td style="text-align: center;">${gameData.homeTeam.name}</td>
						<td>-</td>
						<td style="text-align: center;">${gameData.awayTeam.name}</td>
						<td></td>
					</tr>


					<tr>
						<td style="padding-top: 10px;" colspan="5"></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			</table>
		</section>
	</div>
</div>

<%@ include file="./views/includes/footer.jsp"%>