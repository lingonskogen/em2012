<%@ include file="./views/includes/header.jsp"%>

<section>
	<legend>Statistik</legend>

	<table class="table table-striped">

		<c:forEach items="${data.topList}" var="statisticsformdata"
			varStatus="status">
			<tr>
				<td align="center">${status.count}</td>
				
				<td>${statisticsformdata.userRealName}</td>
				<td>${statisticsformdata.points}</td>
			</tr>
		</c:forEach>
	</table>
	
	<table class="table table-striped">
		<c:forEach items="${data.games}" var="statisticsformdata"
			varStatus="status">
			<tr>
				<td align="center">${status.count}</td>

				<td>${statisticsformdata.homeScore}</td>
				<td>${statisticsformdata.awayScore}</td>
			</tr>
		</c:forEach>	</table>


</section>
<%@ include file="./views/includes/footer.jsp"%>