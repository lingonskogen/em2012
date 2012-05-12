<%@ include file="./views/includes/header.jsp"%>

<div class="content">
         
<H1>Coupon Page</H1>

<div id="adminMenu">
	<ul class="menuList">
	<li><a href="/admin/tournamentpage.html">Tournament page</a></li>
	<li><a href="/admin/couponpage.html">Coupon page</a></li>
	<li><a href="/admin/gamepage.html">Game page</a></li>
	<li><a href="/admin/grouppage.html">Group page</a></li>
	<li><a href="/admin/predictionpage.html">Prediction page</a></li>
	<li><a href="/admin/userpage.html">User page</a></li>
	</ul>
</div>

<c:if test="${errorMessage}">
	<div class="errorMessage">${errorMessage}</div>
</c:if>
<c:if test="${successMessage}">
	<div class="successMessage">${successMessage}</div>
</c:if>


<!-- List all available games data -->
<div class="sectionTitle">Available coupons</div>
<table>
	<tr>
		<th><fmt:message key="global.tournament" /></th>
		<th><fmt:message key="global.group" /></th>
		<th><fmt:message key="global.user" /></th>									
	</tr>
	<c:forEach var="entry" items="${availableCoupons}">
		<tr>
			<!-- <td>${entry.key}</td>  -->
			<td>${entry.value.tournamentName}</td>
			<td>${entry.value.groupName}</td>
			<td>${entry.value.userName}</td>
			<td><a href="${entry.value.deleteLink}"><fmt:message key="global.remove" /></a></td>
		</tr>
	</c:forEach>
</table>
</div>
<%@ include file="./views/includes/footer.jsp"%>