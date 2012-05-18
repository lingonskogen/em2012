<%@ include file="./views/includes/header.jsp"%>

<section>

	<legend>Min sida</legend>

	<div class="control-group">
		<label class="control-label">Namn:</label>
		<div class="controls">
			<label class="control-label">${user.realName}</label>
		</div>
	</div>



	<div class="userInfo">
		<div class="name"> </div>
		<div class="usrName">Anv�ndarnamn/email: ${user.userName}</div>
		<div class="group">Grupp: ${groupName}</div>
		<div class="paid">Betalat: Ja</div>
		<div class="statistics">Statistik: Plats 1</div>
		<div class="coupon">
			<c:choose>
				<c:when test="${coupon}">					
						LISTA P� ANV�NDARENS MATCHER
					</c:when>
				<c:otherwise>
					Kupong: Du har inte skapat n�gon kupong �n
					<a href="couponpage.html" class="btn">Skapa kupong</a>
				</c:otherwise>
			</c:choose>

		</div>

	</div>
</section>
<%@ include file="./views/includes/footer.jsp"%>