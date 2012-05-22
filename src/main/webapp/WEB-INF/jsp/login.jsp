<%@ include file="./views/includes/header.jsp"%>

<div class="row">
	<div class="span8">
		<section>
		  <div class="loginHeader"><h1 class="center">Väkommen till EM-tipset 2012!</h1></div>
			<img alt="EM2012" src="/images/team.jpg">
			<script type="text/javascript">
				$(function() {
					var austDay = new Date();
					austDay = new Date(2012, 5, 28, 12, 0, 0);
					$('#defaultCountdown').countdown({
						until : austDay
					});
				});
			</script>

			<div id="defaultCountdown"></div>

			<script type="text/javascript" src="/js/jquery.countdown.js"></script>

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