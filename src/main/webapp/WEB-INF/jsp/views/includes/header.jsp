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
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="stylesheet" type="text/css" href="/css/countdown.css">
<!-- For IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="span4">
				<a class="brand" href="/start.html"><img
					src="/images/euro2012.png" />
				</a>
			</div>
			<div class="span4 offset4" style="text-align: right">
				<c:choose>
					<c:when test="${loggedIn}">
            <br />
            <p>
  					Inloggad som: ${userName}<br />
						<a href="/logout.html">Logga ut</a>
						</p>
					</c:when>
					<c:otherwise>
					  <br />
					  <p style="padding-right: 20px">
						<a href="/forgotten-password.html">Glömt lösenordet?</a><br />
						<c:if test="${registrationOpen}"><a href="/register.html">Registrera dig</a></c:if>
						</p>
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

		<div class="row">
			<div class="span12">
				<c:choose>
					<c:when test="${loggedIn}">
						<ul style="padding-left: 20px" class="nav nav-pills">
							<li <c:if test="${currentPage == 'start'}">class="active"</c:if>><a
								href="start.html">Hem</a>
							</li>
							<li <c:if test="${currentPage == 'mypage'}">class="active"</c:if>><a
								href="mypage.html">Min sida</a>
							</li>
							 <c:if test="${hasCoupon}">
								<li
									<c:if test="${currentPage == 'statistics'}">class="active"</c:if>><a
									href="statistics.html">Resultat</a>
								</li>
							</c:if>
							<li <c:if test="${currentPage == 'rules'}">class="active"</c:if>><a
								href="rules.html">Regler</a>
							</li>
						</ul>
					</c:when>
				</c:choose>
				<c:if test="${isAdmin}">
					<%@ include file="./adminmenu.jsp"%>
				</c:if>
			</div>
		</div>
