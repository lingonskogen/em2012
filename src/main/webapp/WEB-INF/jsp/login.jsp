<%@ include file="./views/includes/header.jsp"%>

This is the login page
COUNTDOWN:

<script type="text/javascript">
$(function () {
	var austDay = new Date();
	austDay = new Date(2013, 0, 26);
	$('#defaultCountdown').countdown({until: austDay});
	$('#year').text(austDay.getFullYear());
});
</script>

<div id="defaultCountdown"></div>

<script type="text/javascript" src="/js/jquery.countdown.js"></script>

<%@ include file="./views/includes/footer.jsp"%>