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
			<%@ include file="./views/includes/groupa.jsp"%>
			<%@ include file="./views/includes/groupb.jsp"%>
			<%@ include file="./views/includes/groupc.jsp"%>
			<%@ include file="./views/includes/groupd.jsp"%>

		</section>
	</div>
</div>

<%@ include file="./views/includes/footer.jsp"%>