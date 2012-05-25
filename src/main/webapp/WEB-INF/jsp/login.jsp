<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ include file="./views/includes/header.jsp"%>

<div class="row">
	<div class="span8">
		<section>
		  <div class="loginHeader"><h1 class="center">Väkommen till EM-tipset 2012!</h1></div>
		  <p style="text-align: center">Se till att registrera din kupong i tid ...</p>
		  <table id="cd_table" border="0">
		    <tr>
		        <td align="center" colspan="6"><div class="cd_numbers" id="count2" style="padding: 5px 0 0 0; "></div></td>
		    </tr>
		    <tr id="spacer1">
		        <td align="center" ><div class="cd_numbers" ></div></td>
		        <td align="center" ><div class="cd_numbers" id="dday"></div></td>
		        <td align="center" ><div class="cd_numbers" id="dhour"></div></td>
		        <td align="center" ><div class="cd_numbers" id="dmin"></div></td>
		        <td align="center" ><div class="cd_numbers" id="dsec"></div></td>
		        <td align="center" ><div class="cd_numbers" ></div></td>
		    </tr>
		    <tr id="spacer2">
		        <td align="center" ><div class="cd_title" ></div></td>
		        <td align="center" ><div class="cd_title" id="days">Days</div></td>
		        <td align="center" ><div class="cd_title" id="hours">Hours</div></td>
		        <td align="center" ><div class="cd_title" id="minutes">Minutes</div></td>
		        <td align="center" ><div class="cd_title" id="seconds">Seconds</div></td>
		        <td align="center" ><div class="cd_title" ></div></td>
		    </tr>
		</table>

			<img alt="EM2012" src="/images/team.jpg">
			<!-- 
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
       -->

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
<script src="/js/countdown.js"></script>

<%@ include file="./views/includes/footer.jsp"%>