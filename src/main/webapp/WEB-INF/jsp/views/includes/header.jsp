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
<meta name="description" content="${pageDescription}" />
<meta name="keywords" content="${pageKeywords}" />
<meta name="author" content="">

<!-- Le styles -->
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css" >
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<style type="text/css">
body {
  padding-top: 140px;
  padding-bottom: 40px;
}
</style>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<body class="back">

  <div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container">
        <div class="pull-right">
          <ul class="nav">
            <li><a href="/logout.html">${username}</a></li>
            <li><a href="/logout.html">Logout</a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>

  <div class="container">
    <div class="row">
      <div class="span8 offset2">


<!-- 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta name="description" content="${pageDescription}" />
<meta name="keywords" content="${pageKeywords}" />
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheet/style.css" />
<title>${pageTitle}</title>

<body class="back">

	<div class="page">
		<div id="menuLevel2">
			<ul class="right">
				<li class=" " id="ml2_ne"><a href="/uefaeuro/news/index.html"><span>News</span></a>
				</li>
				<li class=" " id="ml2_vi"><a href="/uefaeuro/video/index.html"><span>Video</span></a>
				</li>
				<li class=" " id="ml2_ph"><a href="/uefaeuro/photos/index.html"><span>Photos</span></a>
				</li>
				<li class=" " id="ml2_ma"><a
					href="/uefaeuro/season=2012/matches/index.html"><span>Matches</span></a>
				</li>
        <li class=" " id="ml2_sd"><a
          href="/uefaeuro/season=2012/standings/index.html"><span>Standings</span></a>
        </li>
        <li class=" " id="ml2_un"><span>${username}</span>
        </li>
        <li class=" " id="ml2_lo"><a
          href="/logout.html"><span>Logout</span></a>
        </li>
			</ul>
		</div>
		
-->