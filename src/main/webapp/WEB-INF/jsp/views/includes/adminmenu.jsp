
<div>
	<ul class="nav nav-pills">
		<li <c:if test="${pageId == 'tournament'}">class="active"</c:if>><a href="/admin/tournamentpage.html">Tournament page</a></li>
		<li <c:if test="${pageId == 'coupon'}">class="active"</c:if>><a href="/admin/couponpage.html">Coupon page</a></li>
		<li <c:if test="${pageId == 'game'}">class="active"</c:if>><a href="/admin/gamepage.html">Game page</a></li>
		<li <c:if test="${pageId == 'group'}">class="active"</c:if>><a href="/admin/grouppage.html">Group page</a></li>
		<li <c:if test="${pageId == 'team'}">class="active"</c:if>><a href="/admin/teampage.html">Team page</a></li>
		<li <c:if test="${pageId == 'prediction'}">class="active"</c:if>><a href="/admin/predictionpage.html">Prediction page</a></li>
		<li <c:if test="${pageId == 'user'}">class="active"</c:if>><a href="/admin/userpage.html">User page</a></li>
	</ul>
</div>
