<%@ include file="./views/includes/header.jsp"%>

<section>

	<legend>Min sida</legend>

	<table class="table">
		<tr>
			<td>Namn:</td>
			<td>${user.realName}</td>
		</tr>
		<tr>
			<td>Användarnamn/email:</td>
			<td>${user.userName}</td>
		</tr>
		<tr>
			<td>Grupp:</td>
			<td>${groupName}</td>
		</tr>
		<tr>
			<td>Betalat:</td>
			<td style="color: green;">Ja</td>
		</tr>
		<tr>
			<td>Tipsrad:</td>
			<td><c:choose>
				<c:when test="${hasCoupon}"><a href="${couponUrl}">Tipsrad</a></c:when></c:choose>
			</td>
		</tr>
		<tr>
			<td>Statistik:</td>
			<td><c:choose>
				<c:when test="${hasCoupon}">Du har plats ${position} av ${totalUsers}</c:when></c:choose>
			</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
	</table>

	<c:choose>
		<c:when test="${hasCoupon}">
		</c:when>
		<c:otherwise>
		 <div class="obs"><b style="color: red;">OBS!</b> Du har inte skapat någon tipsrad än</div>
		 			<div class="control-group">
				<div class="controls" style="margin-left: 300px">
					<a href="coupon.html" class="btn btn-primary">Skapa tipsrad</a>
				</div>
			</div>
					
		</c:otherwise>
	</c:choose>
</section>
<%@ include file="./views/includes/footer.jsp"%>