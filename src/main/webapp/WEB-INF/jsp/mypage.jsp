<%@ include file="./views/includes/header.jsp"%>

<div class="row">
	<div class="span8">
		<section>
  		<legend>Min sida</legend>

			<div class="control-group">
				<label class="control-label">Namn:</label>
				<div class="controls">
					<label class="control-label">${user.realName}</label>
				</div>
			</div>

			<div class="userInfo">
				<div class="name"></div>
				<div class="usrName">Användarnamn/email: ${user.userName}</div>
				<div class="group">Grupp: ${groupName}</div>
				<div class="paid">Betalat: ${user.paid}</div>
				<div class="statistics">Statistik: Plats 1</div>
				<div class="coupon">
					<c:choose>
						<c:when test="${coupon}">					
							LISTA PÅ ANVÄNDARENS MATCHER
						</c:when>
						<c:otherwise>
						Kupong: Du har inte skapat någon kupong än
						<a href="couponpage.html" class="btn">Skapa kupong</a>
						</c:otherwise>
					</c:choose>
				</div>
      </div>
		</section>
	</div>
	<div class="span4">
		<section>
  		<legend>sidinfo</legend>
    	<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
		</section>
	</div>
</div>
<%@ include file="./views/includes/footer.jsp"%>