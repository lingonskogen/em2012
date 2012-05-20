<%@ include file="./views/includes/header.jsp"%>

<div class="row">
	<div class="span8">
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
							<c:when test="${hasCoupon}">
								<a href="${couponUrl}">Tipsrad</a>
							</c:when>
						</c:choose></td>
				</tr>
				<tr>
					<td>Statistik:</td>
					<td><c:choose>
							<c:when test="${hasCoupon}">Du har plats ${position} av ${totalUsers}</c:when>
						</c:choose></td>
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
					<div class="obs">
						<b style="color: red;">OBS!</b> Du har inte skapat någon tipsrad
						än
					</div>
					<div class="control-group">
						<div class="controls" style="margin-left: 300px">
							<a href="coupon.html" class="btn btn-primary">Skapa tipsrad</a>
						</div>
					</div>

				</c:otherwise>
			</c:choose>
		</section>
	</div>
	<div class="span4">
		<section>
			<legend>sidinfo</legend>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
				do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
				enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi
				ut aliquip ex ea commodo consequat. Duis aute irure dolor in
				reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
				pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
				culpa qui officia deserunt mollit anim id est laborum.</p>
		</section>
	</div>
</div>

<%@ include file="./views/includes/footer.jsp"%>