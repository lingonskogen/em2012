<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ include file="./views/includes/header.jsp"%>

<div class="row">
	<div class="span8">
		<section>
			<div class="loginHeader">
				<h1 class="center">Välkommen till EM-tipset 2012!</h1>
			</div>
			<p style="text-align: center">Se till att registrera din tipsrad i tid ...</p>

			<%@ include file="./views/includes/countdown.jsp"%>

			<img alt="EM2012" src="/images/team.jpg">
		</section>
	</div>
	<div class="span4">
		<section>
		<div class="gamesTable">
  <h4>Grupp A</h4>
  <table class="table table-striped table-condensed">
    <thead>
      <tr><th>Lag</th><th>S</th><th>V</th><th>O</th><th>F</th><th>P</th></tr>
    </thead>
    <tbody>
      <c:forEach items="${groupsFrom.groupA.teams}" var="team">
      <tr><td>${team.name}</td><td>${team.s}</td><td>${team.v}</td><td>${team.o}</td><td>${team.f}</td><td>${team.p}</td></tr>
      </c:forEach>
    </tbody>
  </table>

  <h4>Grupp B</h4>
  <table class="table table-striped table-condensed">
    <thead>
      <tr><th>Lag</th><th>S</th><th>V</th><th>O</th><th>F</th><th>P</th></tr>
    </thead>
    <tbody>
      <c:forEach items="${groupsFrom.groupB.teams}" var="team">
      <tr><td>${team.name}</td><td>${team.s}</td><td>${team.v}</td><td>${team.o}</td><td>${team.f}</td><td>${team.p}</td></tr>
      </c:forEach>
    </tbody>
  </table>

  <h4>Grupp C</h4>
  <table class="table table-striped table-condensed">
    <thead>
      <tr><th>Lag</th><th>S</th><th>V</th><th>O</th><th>F</th><th>P</th></tr>
    </thead>
    <tbody>
      <c:forEach items="${groupsFrom.groupC.teams}" var="team">
      <tr><td>${team.name}</td><td>${team.s}</td><td>${team.v}</td><td>${team.o}</td><td>${team.f}</td><td>${team.p}</td></tr>
      </c:forEach>
    </tbody>
  </table>

  <h4>Grupp D</h4>
  <table class="table table-striped table-condensed">
    <thead>
      <tr><th>Lag</th><th>S</th><th>V</th><th>O</th><th>F</th><th>P</th></tr>
    </thead>
    <tbody>
      <c:forEach items="${groupsFrom.groupD.teams}" var="team">
      <tr><td>${team.name}</td><td>${team.s}</td><td>${team.v}</td><td>${team.o}</td><td>${team.f}</td><td>${team.p}</td></tr>
      </c:forEach>
    </tbody>
  </table>
</div>

		</section>
	</div>
</div>

<%@ include file="./views/includes/footer.jsp"%>