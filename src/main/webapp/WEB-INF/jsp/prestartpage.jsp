<%@ include file="./views/includes/header.jsp"%>

<section>
	<div class="startContent">
		<h2>Välkommen till EM-tipset 2012!</h2>


		<div class="">Statistik och rader blir synligt när det inte är
			tillåtet att registrera sig längre.</div>

		<div class="statistics">
			<div class="info">Antal registrerade rader hittills: 6 st</div>
			<div class="users">Halli, Susen, Björn</div>
		</div>

		<script type="text/javascript">
	$(function () {
		var austDay = new Date();
		austDay = new Date(2012, 5, 28,12,0,0);
		$('#defaultCountdown').countdown({until: austDay});
	});
	</script>

		<div id="defaultCountdown"></div>

		<script type="text/javascript" src="/js/jquery.countdown.js"></script>

	</div>
</section>

<%@ include file="./views/includes/footer.jsp"%>