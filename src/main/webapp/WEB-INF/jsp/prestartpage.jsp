<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ include file="./views/includes/header.jsp"%>


<div class="row">
	<div class="span8">
		<section>

			<h2>Välkommen till EM-tipset 2012!</h2>
				
			<c:choose>
			<c:when test="${!hasCoupon}"><p style="padding-top: 15px;"><div style="color: red; font-weight: bold;">OBS! </div>Du har inte skapat någon kupong ännu. Gå till min sida och gör det nu!</p></c:when>
			</c:choose>	
			
			<p style="padding-top: 15px;">
				<b>Antal ${groupName}-registrerade rader hittills: ${numRegistreredCoupons} st</b>
			</p>
			
			<p style="padding-top: 15px;">Statistik och rader blir synligt
				när det inte är tillåtet att registrera sig längre.</p>

			
			<p>Tid kvar av registreringen:</p>
			<%@ include file="./views/includes/countdown.jsp"%>

			<table class="table"><tr><td><img style="width: 300px;" alt="EM2012" src="/images/kram.jpg"></td><td><img style="width: 300px;" alt="EM2012" src="/images/team1.jpg"></td></tr></table>
		</section>
	</div>
	<div class="span4">
		<section>
			<H4>Nästa match</H4>
			<table class="table">
				<tbody>

					<tr>
						<td></td>
						<td style="text-align: center;" colspan="3">8 juni 18.00</td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td style="text-align: center;"><img title="Polen"
							src="/images/POL.png"></td>
						<td></td>
						<td style="text-align: center;"><img title="Grekland"
							src="/images/GRE.png"></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td style="text-align: center;">Polen</td>
						<td>-</td>
						<td style="text-align: center;">Grekland</td>
						<td></td>
					</tr>


					<tr>
						<td style="padding-top: 10px;" colspan="5"></td>
					</tr>

					<tr>
						<td></td>
						<td style="text-align: center;" colspan="3">8 juni 20.45</td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td style="text-align: center;"><img title="Ryssland"
							src="/images/RUS.png"></td>
						<td></td>
						<td style="text-align: center;"><img title="Tjeckien"
							src="/images/CZE.png"></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td style="text-align: center;">Ryssland</td>
						<td>-</td>
						<td style="text-align: center;">Tjeckien</td>
						<td></td>
					</tr>

				</tbody>
			</table>
		</section>
	</div>

</div>


<%@ include file="./views/includes/footer.jsp"%>