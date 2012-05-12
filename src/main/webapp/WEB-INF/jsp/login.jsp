<%@ include file="./views/includes/header.jsp"%>


<div class="content">
This is the login page
COUNTDOWN:

<script type="text/javascript">
$(function () {
	var austDay = new Date();
	austDay = new Date(2012, 5, 8,12,0,0);
	$('#defaultCountdown').countdown({until: austDay});
});
</script>

<div id="defaultCountdown"></div>

<script type="text/javascript" src="/js/jquery.countdown.js"></script>
</div>
<%@ include file="./views/includes/footer.jsp"%>