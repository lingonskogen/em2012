<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>${pageTitle}</title>
<meta name="description" content="${pageDescription}" />
<meta name="keywords" content="${pageKeywords}" />

<!-- Le styles -->
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css">

<!-- For IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">
		<div class="header">
			<div class="span4">
				<a class="brand" href="start.html"><img src="/images/euro2012.png" /></a>
			</div>


			<div class="span4 offset4" style="text-align: right">
				<c:choose>
					<c:when test="${loggedIn}">					
  					Inloggad som: ${userName}<br />
						<a href="">Logga ut</a>
					</c:when>

					<c:otherwise>
						<div class="links">
							<div>
								<a href="/forgotten-password.html">Glömt lösenordet?</a>
							</div>
							<div>
								<a href="/register.html">Registrera dig</a>
							</div>
						</div>

						<form name='f' action='<c:url value='j_spring_security_check'/>'
							class="form-inline" method='POST'>
							<input type="text" class="input-small" name='j_username' value=""
								placeholder="Användarnamn/Email"> <input type="password"
								class="input-small" name='j_password' placeholder="Lösenord">
							<button type="submit" name="submit" value="Login" class="btn">Logga
								in</button>
						</form>
						<div>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
					</c:otherwise>
				</c:choose>

			</div>
		</div>

		<c:choose>
			<c:when test="${loggedIn}">
				<div>
					<ul class="nav nav-pills">
						<li <c:if test="${currentPage == 'start'}">class="active"</c:if>><a href="start.html">Hem</a></li>
						<li <c:if test="${currentPage == 'mypage'}">class="active"</c:if>><a href="mypage.html">Min sida</a></li>
						<li <c:if test="${currentPage == 'statistics'}">class="active"</c:if>><a href="statistics.html">Statistik</a></li>
						<li <c:if test="${currentPage == 'rules'}">class="active"</c:if>><a href="rules.html">Regler</a></li>
					</ul>
				</div>
			</c:when>
		</c:choose>
		
		<c:if test="isAdmin">
			<div>
				<ul class="nav nav-pills">
					<li class="active"><a href="#">Tournaments</a></li>
					<li><a href="#">Coupons</a></li>
					<li><a href="#">Games</a></li>
					<li><a href="#">Groups</a></li>
					<li><a href="#">Predictions</a></li>
					<li><a href="#">Users</a></li>
				</ul>
			</div>
		</c:if>